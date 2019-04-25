package com.twodigits.jiraexport.external;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.twodigits.jiraexport.dataobjects.JiraSearchInputDO;
import com.twodigits.jiraexport.dataobjects.JiraSearchIssueDO;
import com.twodigits.jiraexport.dataobjects.JiraSearchResultDO;
import com.twodigits.jiraexport.dataobjects.JiraSearchWorklogDO;
import com.twodigits.jiraexport.dataobjects.JiraSearchWorklogResultDO;

/**
 * Jira service
 * @author robert.diers
 */
public class JiraService {

	private static final String api = "/jira/rest/api/2/search";
	private static final String api_worklog = "/jira/rest/api/2/issue/###JIRAKEY###/worklog";
	private static final String fields = "id,key,created,resolution,resolutiondate,summary,status,customfield_10401,customfield_10504,timetracking,comment,updated";
	private static final int maxresultsize = 1000; //jira maximum
	private static final String expands = "changelog";

	private String user = null;
	private String password = null;

	public JiraService(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public JiraSearchResultDO getJiraIssues (String jiraurl, String project){

		//create search object for post request
		JiraSearchInputDO input = new JiraSearchInputDO();
		input.setFields(Arrays.asList(fields.split(",")));
		input.setJql("project = "+project);
		input.setMaxResults(maxresultsize);
		input.setExpands(Arrays.asList(expands.split(",")));

		//create basic auth
		String userandpassword = this.user+":"+this.password;
		String auth = new String(Base64.getEncoder().encode(userandpassword.getBytes(Charset.forName("UTF-8"))));

		//#1 REST Service
		try {
			//full result
			List<JiraSearchIssueDO> allissues = new ArrayList<JiraSearchIssueDO>();
			long startAt = 0;

			//use genson
			ClientConfig clientCfg = new ClientConfig(GensonJsonConverter.class);

			//read first 1000
			input.setStartAt(startAt);
			JiraSearchResultDO result = ClientBuilder.newClient(clientCfg)
					.target(jiraurl)
					.path(api)
					.request(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + auth)
					.post(Entity.entity(input, MediaType.APPLICATION_JSON), new GenericType<JiraSearchResultDO>() {});
			allissues.addAll(result.getIssues());
			System.out.println("read: "+allissues.size());

			//read next ones
			while (result.getIssues().size() == maxresultsize) {
				startAt = startAt + 1000;
				input.setStartAt(startAt);
				result = ClientBuilder.newClient(clientCfg)
						.target(jiraurl)
						.path(api)
						.request(MediaType.APPLICATION_JSON)
						.header("Authorization", "Basic " + auth)
						.post(Entity.entity(input, MediaType.APPLICATION_JSON), new GenericType<JiraSearchResultDO>() {});
				allissues.addAll(result.getIssues());
				System.out.println("read: "+allissues.size());
			}

			//fill result object and return
			result.setTotal(allissues.size());
			result.setIssues(allissues);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		//nothing found...
		System.out.println("JiraServiceImpl: nothing found for "+project);
		return null;
	}

	public JiraSearchWorklogResultDO getJiraWorklog(String jiraurl, String jirakey) {

		//create basic auth
		String userandpassword = this.user+":"+this.password;
		String auth = new String(Base64.getEncoder().encode(userandpassword.getBytes(Charset.forName("UTF-8"))));

		//#1 REST Service
		try {
			//full result
			List<JiraSearchWorklogDO> allworklogs = new ArrayList<JiraSearchWorklogDO>();
			long startAt = 0;

			//use genson
			ClientConfig clientCfg = new ClientConfig(GensonJsonConverter.class);

			//read first 1000
			JiraSearchWorklogResultDO result = ClientBuilder.newClient(clientCfg)
					.target(jiraurl)
					.path(api_worklog.replaceAll("###JIRAKEY###", jirakey))
					.request(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + auth)
					.get(new GenericType<JiraSearchWorklogResultDO>() {});
			allworklogs.addAll(result.getWorklogs());
			//System.out.println("read: "+allworklogs.size());

			//read next ones
			while (result.getWorklogs().size() == maxresultsize) {
				startAt = startAt + 1000;
				result = ClientBuilder.newClient(clientCfg)
						.target(jiraurl)
						.path(api_worklog.replaceAll("###JIRAKEY###", jirakey))
						.request(MediaType.APPLICATION_JSON)
						.header("Authorization", "Basic " + auth)
						.get(new GenericType<JiraSearchWorklogResultDO>() {});
				allworklogs.addAll(result.getWorklogs());
				//System.out.println("read: "+allworklogs.size());
			}

			//fill result object and return
			result.setTotal(allworklogs.size());
			result.setWorklogs(allworklogs);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		//nothing found...
		System.out.println("JiraServiceImpl: nothing found for "+jirakey);
		return null;
	}
}
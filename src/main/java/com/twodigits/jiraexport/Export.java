package com.twodigits.jiraexport;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.twodigits.jiraexport.dataobjects.*;
import com.twodigits.jiraexport.external.JiraService;
import com.twodigits.jiraexport.util.ExcelXmlExportHelper;

/**
 * export data from Jira instances
 * @author robert.diers
 *
 */
public class Export {	
	
	private static final int THREADPOOLSIZE = 5;
	
	//example REST call
	//https://<jira-instance>/jira/rest/api/2/search?jql=project=<projectid>&fields=id,key,created,resolution,resolutiondate,summary,status,customfield_10401,customfield_10504
	//https://<jira-instance>/jira/rest/api/2/issue/<jirakey>/worklog?maxResults=1000

	public static void main(String[] args) {
		
		try {
			if (args.length != 5) {
				System.out.println("usage: <jira-instance-url> <project> <user> <password> <worklog: true/false> <changelog: true/false>");
			}
			
			String url = args[0];
			String project = args[1];
			String user = args[2];
			String password = args[3];
			String readworklog = args[4];
          	String readchangelog = args[5];
			
			System.out.println("reading issues from " + url + " for " + project + "...");
			JiraService service = new JiraService(user, password);
			JiraSearchResultDO result = service.getJiraIssues(url, project);
			System.out.println("issues: "+result.getTotal());
			
			System.out.println("writing issues to export xml...");
			StringWriter buf = new StringWriter();	
			
			//xml header
			ExcelXmlExportHelper.writeXmlHeader(buf, 2);	
			
			//headlines issues
			List<String> headlines = new ArrayList<String>();
			headlines.add("ID");
			headlines.add("KEY");
			headlines.add("STATUS");
			headlines.add("RESOLUTION");
			headlines.add("SIZE");
			headlines.add("EXTERNALID");
			headlines.add("CREATED");
			headlines.add("RESOLVED");
			headlines.add("ORIG_EST");
			headlines.add("REMAIN_EST");
			headlines.add("TIME_SPEND");
			headlines.add("ORIG_EST_SEC");
			headlines.add("REMAIN_EST_SEC");
			headlines.add("TIME_SPEND_SEC");
			headlines.add("SUMMARY");
			headlines.add("COMMENT_CREATED");
			headlines.add("COMMENT_UPDATED");
			headlines.add("COMMENT");
			ExcelXmlExportHelper.writeXmlWorksheetHeader(buf, "Issues", headlines, result.getIssues().size());
			
			//data issues
			int actual_rowcount = 0;		
			for(JiraSearchIssueDO temp : result.getIssues())
			{
				actual_rowcount++;
				ExcelXmlExportHelper.writeXmlRowHeader(buf, temp.getFields().getComment().getTotal());
				ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", temp.getId(), actual_rowcount);
				ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getKey(), actual_rowcount);
				if (temp.getFields().getStatus() != null)
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getStatus().getName(), actual_rowcount);
				else
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
				if (temp.getFields().getResolution() != null)
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getResolution().getName(), actual_rowcount);
				else
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
				if (temp.getFields().getCustomfield_10401() != null)
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getCustomfield_10401().getValue(), actual_rowcount);
				else
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
				ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getCustomfield_10504(), actual_rowcount);
				ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(temp.getFields().getCreated()), actual_rowcount);
				ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(temp.getFields().getResolutiondate()), actual_rowcount);
				if (temp.getFields().getTimetracking() != null) {
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getOriginalEstimate(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getRemainingEstimate(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getTimeSpent(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getOriginalEstimateSeconds(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getRemainingEstimateSeconds(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getTimetracking().getTimeSpentSeconds(), actual_rowcount);
				} else {
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", "", actual_rowcount);
				}
				ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getFields().getSummary(), actual_rowcount);
				if (temp.getFields().getComment().getComments() != null){
					StringBuilder comments_created = new StringBuilder();
					StringBuilder comments_updated = new StringBuilder();
					StringBuilder comments = new StringBuilder();
					long counter = 0;
					for (JiraSearchIssueFieldCommentCommentsDO comment : temp.getFields().getComment().getComments()){
						counter++;
						comments_created.append(counter + ") " + comment.getCreated()+" "+comment.getAuthor().getName() + System.lineSeparator());
						if (comment.getUpdated() != null){
							comments_updated.append(counter + ") " + comment.getUpdated()+" "+comment.getUpdateAuthor().getName() + System.lineSeparator());
						}else {
							comments_updated.append(counter +") none"+System.lineSeparator());
						}
						comments.append(counter + ") " + comment.getBody()+System.lineSeparator());
					}
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", comments_created.toString(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", comments_updated.toString(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", comments.toString(), actual_rowcount);
				}else {
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
				}

				ExcelXmlExportHelper.writeXmlRowFooter(buf);
			}
			ExcelXmlExportHelper.writeXmlWorksheetFooter(buf, headlines.size());

			if (readchangelog.equalsIgnoreCase("true")) {
				System.out.println("writing changelog to export xml...");

				//headlines worklog
				headlines = new ArrayList<String>();
				headlines.add("ID");
				headlines.add("KEY");
				headlines.add("ISSUE_CREATED");
				headlines.add("ISSUE_UPDATED");
				headlines.add("CHANGE_CREATED");
				headlines.add("AUTHOR");
				headlines.add("FIELD");
				headlines.add("CHANGED_FROM");
				headlines.add("CHANGED_TO");
				ExcelXmlExportHelper.writeXmlWorksheetHeader(buf, "Changelog", headlines, result.getIssues().size());

				//data changelog
				actual_rowcount = 0;
				for (JiraSearchIssueDO temp : result.getIssues()){
					actual_rowcount++;
					ExcelXmlExportHelper.writeXmlRowHeader(buf, temp.getChangelog().getTotal());
					ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", temp.getId(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "String", temp.getKey(), actual_rowcount);
					ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(temp.getFields().getCreated()), actual_rowcount);
					if (temp.getFields().getUpdated() != null){
						ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(temp.getFields().getUpdated()), actual_rowcount);
					}else {
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					}
					if (temp.getChangelog().getTotal() > 0){
						StringBuilder created_date = new StringBuilder();
						StringBuilder created_author = new StringBuilder();
						StringBuilder created_fields = new StringBuilder();
						StringBuilder created_from = new StringBuilder();
						StringBuilder created_to = new StringBuilder();
						long counter = 0;
						for (JiraSearchIssueChangelogHistoriesDO history : temp.getChangelog().getHistories()){
							counter++;
							created_date.append(counter + ") " + history.getCreated() + System.lineSeparator());
							created_author.append(counter + ") " + history.getAuthor().getName() + System.lineSeparator());
							for (JiraSearchIssueChangelogHistoriesItemDO item : history.getItems()){
								created_fields.append("to " + counter + " - " + item.getField() + System.lineSeparator());
								if (item.getFromString()!=null && !item.getFromString().equals("")){
									created_from.append("to " +counter + " - " + item.getFromString()+ System.lineSeparator());
								} else {
									created_from.append("to " +counter + " - none" + System.lineSeparator());
								}
								if (item.getToString()!=null && !item.getToString().equals("")){
									created_to.append("to " +counter + " - " + item.getToString() + System.lineSeparator());
								} else {
									created_to.append("to " +counter + " - none" + System.lineSeparator());
								}
							}
						}
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", created_date.toString(), actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", created_author.toString(), actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", created_fields.toString(), actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", created_from.toString(), actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", created_to.toString(), actual_rowcount);
					}else {
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
						ExcelXmlExportHelper.writeXmlRowCell(buf, "String", "", actual_rowcount);
					}
					ExcelXmlExportHelper.writeXmlRowFooter(buf);
				}

				ExcelXmlExportHelper.writeXmlWorksheetFooter(buf, headlines.size());
			}

			if (readworklog.equalsIgnoreCase("true")) {
				System.out.println("reading worklog...");		
				
				//thread pool to request worklog in parallel
				ExecutorService executor = Executors.newFixedThreadPool(THREADPOOLSIZE);
				long counter = 0;
		        for (JiraSearchIssueDO temp : result.getIssues()) {
		        	counter++;
		            Runnable worker = new WorklogRequestThread(counter, service, url, temp.getId(), temp.getKey());
		            executor.execute(worker);
		        }
		        executor.shutdown();
		        while (!executor.isTerminated()) {}	        
				
		        System.out.println("writing worklog to export xml...");
		        
		        //headlines worklog
				headlines = new ArrayList<String>();
				headlines.add("ID");
				headlines.add("KEY");
				headlines.add("AUTHOR");
				headlines.add("TIMESPEND");
				headlines.add("TIMESPENDSECONDS");
				headlines.add("STARTED");
				headlines.add("CREATED");
				headlines.add("UPDATED");
				ExcelXmlExportHelper.writeXmlWorksheetHeader(buf, "Worklog", headlines, WorklogResultStorage.getInstance().getTotal());
		        
				//data worklog
				actual_rowcount = 0;			
				Map<String,List<JiraSearchWorklogDO>> worklogs = WorklogResultStorage.getInstance().getWorklogs();
				for(String idandkey : worklogs.keySet())
				{
					String[] idandkeyarray = idandkey.split("#");
					List<JiraSearchWorklogDO> temp = worklogs.get(idandkey);
					if (temp != null) {
						for (JiraSearchWorklogDO wl : temp) {
							actual_rowcount++;		
							ExcelXmlExportHelper.writeXmlRowHeader(buf,0);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", idandkeyarray[0], actual_rowcount);		
							ExcelXmlExportHelper.writeXmlRowCell(buf, "String", idandkeyarray[1], actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "String", wl.getAuthor().getName(), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "String", wl.getTimeSpent(), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "Number", wl.getTimeSpentSeconds(), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(wl.getStarted()), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(wl.getCreated()), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowCell(buf, "DateTime", ExcelXmlExportHelper.formatDate(wl.getUpdated()), actual_rowcount);
							ExcelXmlExportHelper.writeXmlRowFooter(buf);
						}
					}
				}		
				ExcelXmlExportHelper.writeXmlWorksheetFooter(buf, headlines.size());
			}
			
			//xml footer
			ExcelXmlExportHelper.writeXmlFooter(buf);
			
			//write to disc
			Files.write(Paths.get("jira-export.xml"), buf.toString().getBytes());
			
			System.out.println("DONE!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search input for Jira
 * @author robert.diers
 * @project JiraExport
 */
@XmlRootElement
public class JiraSearchInputDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jql = "";
	private long maxResults;
	private List<String> fields;	
	private long startAt = 0;
	
	public JiraSearchInputDO() {}

	@XmlElement(name="jql")
	public String getJql() {
		return jql;
	}

	public void setJql(String jql) {
		this.jql = jql;
	}

	@XmlElement(name="maxResults")
	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	@XmlElement(name="fields")
	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	@XmlElement(name="startAt")
	public long getStartAt() {
		return startAt;
	}

	public void setStartAt(long startAt) {
		this.startAt = startAt;
	}	
	
}
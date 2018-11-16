package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search in Jira
 * @author robert.diers
 * @project ParMa
 */
@XmlRootElement
public class JiraSearchWorklogResultDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String expand;
	private long maxResults;
	private long total;
	private List<JiraSearchWorklogDO> worklogs;
	
	public JiraSearchWorklogResultDO() {}	
	
	@XmlElement(name="maxResults")
	public long getMaxResults() {
		return maxResults;
	}
	
	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	@XmlElement(name="total")
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@XmlElement(name="worklogs")
	public List<JiraSearchWorklogDO> getWorklogs() {
		return worklogs;
	}

	public void setWorklogs(List<JiraSearchWorklogDO> worklogs) {
		this.worklogs = worklogs;
	}

	@XmlElement(name="expand")
	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}	
	
}
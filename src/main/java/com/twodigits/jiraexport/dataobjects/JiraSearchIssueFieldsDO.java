package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.twodigits.jiraexport.adapter.JiraDateAdapter;
import com.twodigits.jiraexport.adapter.JiraDateTimeAdapter;

/**
 * search in Jira
 * @author robert.diers
 */
@XmlRootElement
public class JiraSearchIssueFieldsDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private JiraSearchIssueFieldSizeDO customfield_10401; 		//size

	private String customfield_10504 = ""; 						//externalId

	private String summary = "";
	
	private JiraSearchIssueAuthorDO assignee;

	private JiraSearchIssueFieldResolutionDO resolution;

	private Date resolutiondate;

	private Date created;
	
	private Date duedate;

	private JiraSearchIssueFieldStatusDO status;

	private JiraSearchIssueFieldTimetrackingDO timetracking;

	private Date updated;

	private JiraSearchIssueFieldCommentDO comment;
	
	//Story Points
	private int customfield_10002;
	
	private String environment;
	
	private String[] labels;

	private JiraDateTimeAdapter jdta = new JiraDateTimeAdapter();
	private JiraDateAdapter jda = new JiraDateAdapter();

	public JiraSearchIssueFieldsDO() {}

	@XmlElement(name = "customfield_10401")
	public JiraSearchIssueFieldSizeDO getCustomfield_10401 () { return customfield_10401; }

	public void setCustomfield_10401 (JiraSearchIssueFieldSizeDO customfield_10401) { this.customfield_10401 = customfield_10401; }

	@XmlElement(name = "customfield_10504")
	public String getCustomfield_10504 () { return customfield_10504; }

	public void setCustomfield_10504 (String customfield_10504) { this.customfield_10504 = customfield_10504; }

	@XmlElement(name = "summary")
	public String getSummary () { return summary; }

	public void setSummary (String summary) { this.summary = summary; }

	@XmlElement(name = "resolution")
	public JiraSearchIssueFieldResolutionDO getResolution () { return resolution; }

	public void setResolution (JiraSearchIssueFieldResolutionDO resolution) { this.resolution = resolution; }

	@XmlElement(name = "resolutiondate")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getResolutiondate () { return resolutiondate; }

	public void setResolutiondate (String resolutiondate) {
		try {
			this.resolutiondate = jdta.unmarshal(resolutiondate);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@XmlElement(name = "created")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getCreated () { return created; }

	public void setCreated (String created) {
		try {
			this.created = jdta.unmarshal(created);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@XmlElement(name="status")
	public JiraSearchIssueFieldStatusDO getStatus() {
		return status;
	}

	public void setStatus(JiraSearchIssueFieldStatusDO status) {
		this.status = status;
	}

	@XmlElement(name="timetracking")
	public JiraSearchIssueFieldTimetrackingDO getTimetracking() {
		return timetracking;
	}

	public void setTimetracking(JiraSearchIssueFieldTimetrackingDO timetracking) {
		this.timetracking = timetracking;
	}

	@XmlElement(name = "updated")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getUpdated () { return updated; }

	public void setUpdated (String updated) {
		try {
			this.updated = jdta.unmarshal(updated);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@XmlElement(name = "duedate")
	@XmlJavaTypeAdapter(JiraDateAdapter.class)
	public Date getDuedate() { return duedate;}
	
	public void setDuedate(String duedate)
	{
		try
		{
			this.duedate = jda.unmarshal(duedate);			
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@XmlElement(name = "comment")
	public JiraSearchIssueFieldCommentDO getComment () { return comment; }

	public void setComment (JiraSearchIssueFieldCommentDO comment) { this.comment = comment; }

	@XmlElement(name = "assignee")
    public JiraSearchIssueAuthorDO getAssignee () { return assignee; }

    public void setAssignee(JiraSearchIssueAuthorDO assignee) { this.assignee = assignee; }

	@XmlElement(name="customfield_10002")
	public int getCustomfield_10002() { return customfield_10002; }
	
	public void setCustomfield_10002(int customfield_10002)
	{
		this.customfield_10002 = customfield_10002;
	}
	
	@XmlElement(name = "environment")
	public String getEnvironment() { return environment; }
	
	public void setEnvironment(String environment) {this.environment =environment; }
	
	@XmlElement(name="labels")
	public String[] getLabels() { return labels; }
	
	public void setLabels(String[] labels) { this.labels = labels; }
	
}

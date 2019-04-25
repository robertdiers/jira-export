package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.twodigits.jiraexport.adapter.JiraDateAdapter;

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

	private JiraSearchIssueFieldResolutionDO resolution;

	private Date resolutiondate;

	private Date created;

	private JiraSearchIssueFieldStatusDO status;

	private JiraSearchIssueFieldTimetrackingDO timetracking;

	private Date updated;

	private JiraSearchIssueFieldCommentDO comment;

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
	@XmlJavaTypeAdapter(JiraDateAdapter.class)
	public Date getResolutiondate () { return resolutiondate; }

	public void setResolutiondate (String resolutiondate) {
		try {
			this.resolutiondate = jda.unmarshal(resolutiondate);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@XmlElement(name = "created")
	@XmlJavaTypeAdapter(JiraDateAdapter.class)
	public Date getCreated () { return created; }

	public void setCreated (String created) {
		try {
			this.created = jda.unmarshal(created);
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
	@XmlJavaTypeAdapter(JiraDateAdapter.class)
	public Date getUpdated () { return updated; }

	public void setUpdated (String updated) {
		try {
			this.updated = jda.unmarshal(updated);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@XmlElement(name = "comment")
	public JiraSearchIssueFieldCommentDO getComment () { return comment; }

	public void setComment (JiraSearchIssueFieldCommentDO comment) { this.comment = comment; }

}

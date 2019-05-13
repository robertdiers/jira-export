package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.twodigits.jiraexport.adapter.JiraDateTimeAdapter;

/**
 * search in Jira
 * @author robert.diers
 */
@XmlRootElement
public class JiraSearchWorklogDO implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	private JiraSearchWorklogAuthorDO author;	
	
	private Date started;
	
	private Date created;
	
	private Date updated;
	
	private String timeSpent;
	
	private Long timeSpentSeconds;	
	
	private JiraDateTimeAdapter jda = new JiraDateTimeAdapter();
	
	public JiraSearchWorklogDO() {}

	@XmlElement(name="author")
	public JiraSearchWorklogAuthorDO getAuthor() {
		return author;
	}

	public void setAuthor(JiraSearchWorklogAuthorDO author) {
		this.author = author;
	}

	@XmlElement(name="started")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getStarted() {
		return started;
	}

	public void setStarted(String started) {
		try {
			this.started = jda.unmarshal(started);
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	@XmlElement(name="created")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getCreated() {
		return created;
	}

	public void setCreated(String created) {
		try {
			this.created = jda.unmarshal(created);
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	@XmlElement(name="updated")
	@XmlJavaTypeAdapter(JiraDateTimeAdapter.class)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		try {
			this.updated = jda.unmarshal(updated);
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	@XmlElement(name="timeSpent")
	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	@XmlElement(name="timeSpentSeconds")
	public Long getTimeSpentSeconds() {
		return timeSpentSeconds;
	}

	public void setTimeSpentSeconds(Long timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}	
	
}
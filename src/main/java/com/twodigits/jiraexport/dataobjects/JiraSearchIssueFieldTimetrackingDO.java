package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search in Jira
 * @author robert.diers
 */
@XmlRootElement
public class JiraSearchIssueFieldTimetrackingDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String originalEstimate;
	
	private String remainingEstimate;
	
	private String timeSpent;
	
	private Long originalEstimateSeconds;
	
	private Long remainingEstimateSeconds;
	
	private Long timeSpentSeconds;
	
	public JiraSearchIssueFieldTimetrackingDO() {}

	@XmlElement(name="originalEstimate")
	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	@XmlElement(name="remainingEstimate")
	public String getRemainingEstimate() {
		return remainingEstimate;
	}

	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}

	@XmlElement(name="timeSpent")
	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	@XmlElement(name="originalEstimateSeconds")
	public Long getOriginalEstimateSeconds() {
		return originalEstimateSeconds;
	}

	public void setOriginalEstimateSeconds(Long originalEstimateSeconds) {
		this.originalEstimateSeconds = originalEstimateSeconds;
	}

	@XmlElement(name="remainingEstimateSeconds")
	public Long getRemainingEstimateSeconds() {
		return remainingEstimateSeconds;
	}

	public void setRemainingEstimateSeconds(Long remainingEstimateSeconds) {
		this.remainingEstimateSeconds = remainingEstimateSeconds;
	}

	@XmlElement(name="timeSpentSeconds")
	public Long getTimeSpentSeconds() {
		return timeSpentSeconds;
	}

	public void setTimeSpentSeconds(Long timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
	
}
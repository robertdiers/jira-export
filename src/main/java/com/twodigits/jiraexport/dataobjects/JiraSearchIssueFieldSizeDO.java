package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search in Jira
 * @author robert.diers
 */
@XmlRootElement
public class JiraSearchIssueFieldSizeDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;	
	
	public JiraSearchIssueFieldSizeDO() {}

	@XmlElement(name="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
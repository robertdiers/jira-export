package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search in Jira
 * @author robert.diers
 */
@XmlRootElement
public class JiraSearchIssueFieldResolutionDO implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public JiraSearchIssueFieldResolutionDO() {}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
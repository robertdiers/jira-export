package com.twodigits.jiraexport.dataobjects;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * search in Jira
 * @author robert.diers
 * @project ParMa
 */
@XmlRootElement
public class JiraSearchIssueDO implements Serializable {


    private static final long serialVersionUID = 1L;
    private String expand;
    private String id;
    private String key;
    private JiraSearchIssueFieldsDO fields;
    private JiraSearchIssueChangelogDO changelog;

    public JiraSearchIssueDO() {}

	@XmlElement(name="expand")
	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	@XmlElement(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name="key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlElement(name="fields")
	public JiraSearchIssueFieldsDO getFields() {
		return fields;
	}

    public void setFields (JiraSearchIssueFieldsDO fields) { this.fields = fields; }

    @XmlElement(name="changelog")
    public JiraSearchIssueChangelogDO getChangelog () { return changelog; }

    public void setChangelog (JiraSearchIssueChangelogDO changelog) { this.changelog = changelog; }

}
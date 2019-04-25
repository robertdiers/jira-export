package com.twodigits.jiraexport.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class JiraSearchIssueAuthorDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String key;
    private String emailAddress;
    private String displayName;
    private Boolean active;
    private String timeZone;

    public JiraSearchIssueAuthorDO() {}

    @XmlElement(name = "name")
    public String getName () { return name; }

    public void setName (String name) { this.name = name; }

    @XmlElement(name = "key")
    public String getKey () { return key; }

    public void setKey (String key) { this.key = key; }

    @XmlElement(name = "emailAddress")
    public String getEmailAddress () { return emailAddress; }

    public void setEmailAddress (String emailAddress) { this.emailAddress = emailAddress; }

    @XmlElement(name = "displayName")
    public String getDisplayName () { return displayName; }

    public void setDisplayName (String displayName) { this.displayName = displayName; }

    @XmlElement(name = "active")
    public Boolean getActive () { return active; }

    public void setActive (Boolean active) { this.active = active; }

    @XmlElement(name = "timeZone")
    public String getTimeZone () { return timeZone; }

    public void setTimeZone (String timeZone) { this.timeZone = timeZone; }

}

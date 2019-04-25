package com.twodigits.jiraexport.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class JiraSearchIssueChangelogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long maxResults;
    private long total;
    private List<JiraSearchIssueChangelogHistoriesDO> histories;

    public JiraSearchIssueChangelogDO() {}

    @XmlElement(name="maxResults")
    public long getMaxResults () { return maxResults; }

    public void setMaxResults (long maxResults) { this.maxResults = maxResults; }

    @XmlElement(name="total")
    public long getTotal () { return total; }

    public void setTotal (long total) { this.total = total; }

    @XmlElement (name="histories")
    public List<JiraSearchIssueChangelogHistoriesDO> getHistories () { return histories; }

    public void setHistories (List<JiraSearchIssueChangelogHistoriesDO> histories) { this.histories = histories; }

}

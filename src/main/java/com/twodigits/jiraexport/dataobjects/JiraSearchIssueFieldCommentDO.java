package com.twodigits.jiraexport.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class JiraSearchIssueFieldCommentDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<JiraSearchIssueFieldCommentCommentsDO> comments;
    private long maxResults;
    private long total;

    public JiraSearchIssueFieldCommentDO() {}

    @XmlElement(name="comments")
    public List<JiraSearchIssueFieldCommentCommentsDO> getComments() {
        return comments;
    }

    public void setComments(List<JiraSearchIssueFieldCommentCommentsDO> comments) {
        this.comments = comments;
    }

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

    public void setTotal(long total) { this.total = total; }

}

package com.twodigits.jiraexport.dataobjects;


import com.twodigits.jiraexport.adapter.JiraDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class JiraSearchIssueFieldCommentCommentsDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private JiraSearchIssueAuthorDO author;
    private String body;
    private JiraSearchIssueAuthorDO updateAuthor;
    private Date created;
    private Date updated;
    private JiraDateAdapter jda = new JiraDateAdapter();

    public JiraSearchIssueFieldCommentCommentsDO() {}

    @XmlElement(name = "id")
    public String getId () { return id; }

    public void setId (String id) { this.id = id; }

    @XmlElement(name = "author")
    public JiraSearchIssueAuthorDO getAuthor () { return author; }

    public void setAuthor (JiraSearchIssueAuthorDO author) { this.author = author; }

    @XmlElement(name = "body")
    public String getBody () { return body; }

    public void setBody (String body) { this.body = body; }

    @XmlElement(name = "updateAuthor")
    public JiraSearchIssueAuthorDO getUpdateAuthor () { return updateAuthor; }

    public void setUpdateAuthor (JiraSearchIssueAuthorDO updateAuthor) { this.updateAuthor = updateAuthor; }

    @XmlElement(name = "created")
    @XmlJavaTypeAdapter(JiraDateAdapter.class)
    public Date getCreated () { return created; }

    public void setCreated (String created) {
        try {
            this.created = jda.unmarshal(created);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @XmlElement(name = "updated")
    @XmlJavaTypeAdapter(JiraDateAdapter.class)
    public Date getUpdated () { return updated; }

    public void setUpdated (String updated) {
        try {
            this.updated = jda.unmarshal(updated);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

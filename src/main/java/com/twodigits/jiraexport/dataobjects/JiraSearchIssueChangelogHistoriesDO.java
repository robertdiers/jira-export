package com.twodigits.jiraexport.dataobjects;



import com.twodigits.jiraexport.adapter.JiraDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class JiraSearchIssueChangelogHistoriesDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private JiraSearchIssueAuthorDO author;
    private Date created;
    private List<JiraSearchIssueChangelogHistoriesItemDO> items;
    private JiraDateAdapter jda = new JiraDateAdapter();

    public JiraSearchIssueChangelogHistoriesDO() {}

    @XmlElement(name = "id")
    public String getId () { return id; }

    public void setId (String id) { this.id = id; }

    @XmlElement(name = "author")
    public JiraSearchIssueAuthorDO getAuthor () { return author; }

    public void setAuthor (JiraSearchIssueAuthorDO author) { this.author = author; }

    @XmlElement(name = "created")
    public Date getCreated () { return created; }

    public void setCreated (String created) {
        try {
            this.created = jda.unmarshal(created);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @XmlElement(name = "items")
    public List<JiraSearchIssueChangelogHistoriesItemDO> getItems () { return items; }

    public void setItems (List<JiraSearchIssueChangelogHistoriesItemDO> items) { this.items = items; }

}

package com.twodigits.jiraexport.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class JiraSearchIssueChangelogHistoriesItemDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;
    private String fieldtype;
    private String fromString;
    private String toString;

    public JiraSearchIssueChangelogHistoriesItemDO() {}

    @XmlElement(name = "field")
    public String getField () { return field; }

    public void setField (String field) { this.field = field; }

    @XmlElement(name = "fieldtype")
    public String getFieldtype () { return fieldtype; }

    public void setFieldtype (String fieldtype) { this.fieldtype = fieldtype; }

    @XmlElement(name = "fromString")
    public String getFromString () { return fromString; }

    public void setFromString (String fromString) { this.fromString = fromString; }

    @XmlElement(name = "toString")
    public String getToString () { return toString; }

    public void setToString (String toString) { this.toString = toString; }

}

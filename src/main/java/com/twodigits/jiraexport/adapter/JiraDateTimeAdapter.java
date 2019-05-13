package com.twodigits.jiraexport.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML / JSON Conversation adapter
 * @author robert.diers
 */
public class JiraDateTimeAdapter extends XmlAdapter<String, Date> {
	
	//2018-10-30T13:43:26.000+0100
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public String marshal(Date date) throws Exception 
    {
    	try 
    	{
    		if (date == null) return null;
    		return sdf.format(date);
    	} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		throw e;
    	}
    }

    public Date unmarshal(String dateString) throws Exception 
    {
    	try 
    	{
    		if (dateString == null || dateString.isEmpty()) return null;
    		return sdf.parse(dateString);
    	} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		throw e;
    	}
    }    
}
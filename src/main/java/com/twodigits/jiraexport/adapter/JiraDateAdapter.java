package com.twodigits.jiraexport.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XML / JSON Conversation adapter
 * @author jan.degen
 */
public class JiraDateAdapter extends XmlAdapter<String, Date> {
	
	//2018-10-30
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
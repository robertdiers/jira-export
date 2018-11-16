package com.twodigits.jiraexport;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twodigits.jiraexport.dataobjects.JiraSearchWorklogDO;

/**
 * singleton for thread handling
 * @author robert.diers
 *
 */
public class WorklogResultStorage {
	
	private static WorklogResultStorage instance = new WorklogResultStorage();
	
	private Map<String,List<JiraSearchWorklogDO>> worklogs = Collections.synchronizedMap(new HashMap<String,List<JiraSearchWorklogDO>>());
	
	public static WorklogResultStorage getInstance() {
		return instance;
	}

	public Map<String,List<JiraSearchWorklogDO>> getWorklogs() {
		return worklogs;
	}
	
	public void addWorklogs(String idandkey, List<JiraSearchWorklogDO> worklogs) {		
		this.worklogs.put(idandkey, worklogs);
	}
	
	public int getTotal() {
		int counter = 0;
		for (String key : this.worklogs.keySet()) {
			List<JiraSearchWorklogDO> tmp = this.worklogs.get(key);
			counter = counter + tmp.size();
		}
		return counter;
	}

}
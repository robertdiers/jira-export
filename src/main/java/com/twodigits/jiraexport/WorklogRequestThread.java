package com.twodigits.jiraexport;

import com.twodigits.jiraexport.dataobjects.JiraSearchWorklogResultDO;
import com.twodigits.jiraexport.external.JiraService;

/**
 * thread to request worklog in parallel
 * @author robert.diers
 *
 */
public class WorklogRequestThread implements Runnable {
	
	private long counter;
	private JiraService service;
	private String url;
	private String id;
	private String jirakey;
	
	public WorklogRequestThread(long counter, JiraService service, String url, String id, String jirakey) {
		this.counter = counter;
		this.service = service;
		this.url = url;
		this.id = id;
		this.jirakey = jirakey;
	}

	@Override
	public void run() {
		try {
			JiraSearchWorklogResultDO wlres = service.getJiraWorklog(url, jirakey);
			if (wlres != null) {
				String idandkey = id+"#"+jirakey;
				WorklogResultStorage.getInstance().addWorklogs(idandkey, wlres.getWorklogs());
				System.out.println(counter+" - "+jirakey+" done.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.notification.model;

import java.sql.Date;

public class NotificationVO implements java.io.Serializable{
	
	private Integer notifyno;
	private Integer memno;
	private String activitiesnotify;
	
	public Integer getNotifyno() {
		return notifyno;
	}
	public void setNotifyno(Integer notifyno) {
		this.notifyno = notifyno;
	}
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getActivitiesnotify() {
		return activitiesnotify;
	}
	public void setActivitiesnotify(String activitiesnotify) {
		this.activitiesnotify = activitiesnotify;
	}

}

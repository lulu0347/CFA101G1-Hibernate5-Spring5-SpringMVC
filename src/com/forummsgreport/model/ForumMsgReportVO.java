package com.forummsgreport.model;

import java.sql.Date;

public class ForumMsgReportVO {
	private Integer forumMsgReportNo;
	private Integer memNo;
	private Integer forumMsgNo;
	private Date forumMsgReportTime;
	private String forumMsgReportWhy;
	private Integer forumMsgReportType;
	public Integer getForumMsgReportNo() {
		return forumMsgReportNo;
	}
	public void setForumMsgReportNo(Integer forumMsgReportNo) {
		this.forumMsgReportNo = forumMsgReportNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getForumMsgNo() {
		return forumMsgNo;
	}
	public void setForumMsgNo(Integer forumMsgNo) {
		this.forumMsgNo = forumMsgNo;
	}
	public Date getForumMsgReportTime() {
		return forumMsgReportTime;
	}
	public void setForumMsgReportTime(Date forumMsgReportTime) {
		this.forumMsgReportTime = forumMsgReportTime;
	}
	public String getForumMsgReportWhy() {
		return forumMsgReportWhy;
	}
	public void setForumMsgReportWhy(String forumMsgReportWhy) {
		this.forumMsgReportWhy = forumMsgReportWhy;
	}
	public Integer getForumMsgReportType() {
		return forumMsgReportType;
	}
	public void setForumMsgReportType(Integer forumMsgReportType) {
		this.forumMsgReportType = forumMsgReportType;
	}
	
}

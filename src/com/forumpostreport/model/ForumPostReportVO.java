package com.forumpostreport.model;

import java.sql.Date;

public class ForumPostReportVO {
	private Integer forumPostReportNo;
	private Integer forumPostNo;
	private Integer memNo;
	private Date forumPostReportTime;
	private String forumPostReportWhy;
	private Integer forumPostReportType;
	public Integer getForumPostReportNo() {
		return forumPostReportNo;
	}
	public void setForumPostReportNo(Integer forumPostReportNo) {
		this.forumPostReportNo = forumPostReportNo;
	}
	public Integer getForumPostNo() {
		return forumPostNo;
	}
	public void setForumPostNo(Integer forumPostNo) {
		this.forumPostNo = forumPostNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Date getForumPostReportTime() {
		return forumPostReportTime;
	}
	public void setForumPostReportTime(Date forumPostReportTime) {
		this.forumPostReportTime = forumPostReportTime;
	}
	public String getForumPostReportWhy() {
		return forumPostReportWhy;
	}
	public void setForumPostReportWhy(String forumPostReportWhy) {
		this.forumPostReportWhy = forumPostReportWhy;
	}
	public Integer getForumPostReportType() {
		return forumPostReportType;
	}
	public void setForumPostReportType(Integer forumPostReportType) {
		this.forumPostReportType = forumPostReportType;
	}
	
}

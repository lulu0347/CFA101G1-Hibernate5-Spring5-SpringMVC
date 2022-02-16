package com.usedreport.model;

import java.sql.Date;

public class UsedReportVO implements java.io.Serializable{
	private Integer memNo;
	private Integer usedNo;
	private Integer reportedMemNo;
	private Integer usedReportState;
	private Date usedReportTime;
	private String usedReportReason;
	private String usedReportNotice;
	
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getUsedNo() {
		return usedNo;
	}
	public void setUsedNo(Integer usedNo) {
		this.usedNo = usedNo;
	}
	public Integer getReportedMemNo() {
		return reportedMemNo;
	}
	public void setReportedMemNo(Integer reportedMemNo) {
		this.reportedMemNo = reportedMemNo;
	}
	public Integer getUsedReportState() {
		return usedReportState;
	}
	public void setUsedReportState(Integer usedReportState) {
		this.usedReportState = usedReportState;
	}
	public Date getUsedReportTime() {
		return usedReportTime;
	}
	public void setUsedReportTime(Date usedReportTime) {
		this.usedReportTime = usedReportTime;
	}
	public String getUsedReportReason() {
		return usedReportReason;
	}
	public void setUsedReportReason(String usedReportReason) {
		this.usedReportReason = usedReportReason;
	}
	public String getUsedReportNotice() {
		return usedReportNotice;
	}
	public void setUsedReportNotice(String usedReportNotice) {
		this.usedReportNotice = usedReportNotice;
	}
	
}

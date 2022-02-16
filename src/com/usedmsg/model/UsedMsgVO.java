package com.usedmsg.model;



public class UsedMsgVO implements java.io.Serializable{
	private Integer usedMsgNo;
	private Integer memNo;
	private Integer usedNo;
	private String usedMsgText;
	
	public Integer getUsedMsgNo() {
		return usedMsgNo;
	}
	public void setUsedMsgNo(Integer usedMsgNo) {
		this.usedMsgNo = usedMsgNo;
	}
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
	public String getUsedMsgText() {
		return usedMsgText;
	}
	public void setUsedMsgText(String usedMsgText) {
		this.usedMsgText = usedMsgText;
	}
	
}

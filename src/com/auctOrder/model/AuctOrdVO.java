package com.auctOrder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctOrdVO implements Serializable {
	
	private Integer auctOrdNo;
	private Integer memNo;
	private Integer auctOrdAmount;
	private String receName;
	private String receAddr;
	private String recePhone;
	private String note;
	private Timestamp auctOrdTime;
	private Timestamp auctOrdModTime;
	private Integer auctOrdState;
	
	
	
	public AuctOrdVO() {
		super();
	}
	public AuctOrdVO(Integer auctOrdNo, Integer memNo, Integer auctOrdAmount, String receName, String receAddr,
			String recePhone, String note, Timestamp auctOrdTime, Timestamp auctOrdModTime, Integer auctOrdState) {
		super();
		this.auctOrdNo = auctOrdNo;
		this.memNo = memNo;
		this.auctOrdAmount = auctOrdAmount;
		this.receName = receName;
		this.receAddr = receAddr;
		this.recePhone = recePhone;
		this.note = note;
		this.auctOrdTime = auctOrdTime;
		this.auctOrdModTime = auctOrdModTime;
		this.auctOrdState = auctOrdState;
	}
	public Integer getAuctOrdNo() {
		return auctOrdNo;
	}
	public void setAuctOrdNo(Integer auctOrdNo) {
		this.auctOrdNo = auctOrdNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getAuctOrdAmount() {
		return auctOrdAmount;
	}
	public void setAuctOrdAmount(Integer auctOrdAmount) {
		this.auctOrdAmount = auctOrdAmount;
	}
	public String getReceName() {
		return receName;
	}
	public void setReceName(String receName) {
		this.receName = receName;
	}
	public String getReceAddr() {
		return receAddr;
	}
	public void setReceAddr(String receAddr) {
		this.receAddr = receAddr;
	}
	public String getRecePhone() {
		return recePhone;
	}
	public void setRecePhone(String recePhone) {
		this.recePhone = recePhone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Timestamp getAuctOrdTime() {
		return auctOrdTime;
	}
	public void setAuctOrdTime(Timestamp auctOrdTime) {
		this.auctOrdTime = auctOrdTime;
	}
	public Timestamp getAuctOrdModTime() {
		return auctOrdModTime;
	}
	public void setAuctOrdModTime(Timestamp auctOrdModTime) {
		this.auctOrdModTime = auctOrdModTime;
	}
	public Integer getAuctOrdState() {
		return auctOrdState;
	}
	public void setAuctOrdState(Integer auctOrdState) {
		this.auctOrdState = auctOrdState;
	}
	
	
	@Override
	public String toString() {
		return "AuctOrdVO [auctOrdNo=" + auctOrdNo + ", memNo=" + memNo + ", auctOrdAmount=" + auctOrdAmount
				+ ", receName=" + receName + ", receAddr=" + receAddr + ", recePhone=" + recePhone + ", note=" + note
				+ ", auctOrdTime=" + auctOrdTime + ", auctOrdModTime=" + auctOrdModTime + ", auctOrdState="
				+ auctOrdState + "]";
	}
	

}

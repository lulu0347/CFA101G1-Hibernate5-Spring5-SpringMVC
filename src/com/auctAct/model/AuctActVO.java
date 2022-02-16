package com.auctAct.model;


import java.io.Serializable;
import java.sql.Timestamp;

public class AuctActVO implements Serializable{
	private Integer auctActNo;
	private String auctActName;
	private String auctActDesc;
	private Integer auctActState;
	private Timestamp auctStartTime;
	private Timestamp auctEndTime;
	
	
	public AuctActVO() {
		super();
		
	}


	public AuctActVO(Integer auctActNo, String auctActName, String auctActDesc, Integer auctActState,
			Timestamp auctStartTime, Timestamp auctEndTime) {
		super();
		this.auctActNo = auctActNo;
		this.auctActName = auctActName;
		this.auctActDesc = auctActDesc;
		this.auctActState = auctActState;
		this.auctStartTime = auctStartTime;
		this.auctEndTime = auctEndTime;
	}


	public Integer getAuctActNo() {
		return auctActNo;
	}


	public void setAuctActNo(Integer auctActNo) {
		this.auctActNo = auctActNo;
	}


	public String getAuctActName() {
		return auctActName;
	}


	public void setAuctActName(String auctActName) {
		this.auctActName = auctActName;
	}


	public String getAuctActDesc() {
		return auctActDesc;
	}


	public void setAuctActDesc(String auctActDesc) {
		this.auctActDesc = auctActDesc;
	}


	public Integer getAuctActState() {
		return auctActState;
	}


	public void setAuctActState(Integer auctActState) {
		this.auctActState = auctActState;
	}


	public Timestamp getAuctStartTime() {
		return auctStartTime;
	}


	public void setAuctStartTime(Timestamp auctStartTime) {
		this.auctStartTime = auctStartTime;
	}


	public Timestamp getAuctEndTime() {
		return auctEndTime;
	}


	public void setAuctEndTime(Timestamp auctEndTime) {
		this.auctEndTime = auctEndTime;
	}


	@Override
	public String toString() {
		return "AuctActVO [auctActNo=" + auctActNo + ", auctActName=" + auctActName + ", auctActDesc=" + auctActDesc
				+ ", auctActState=" + auctActState + ", auctStartTime=" + auctStartTime + ", auctEndTime=" + auctEndTime
				+ "]";
	}
	
	
}

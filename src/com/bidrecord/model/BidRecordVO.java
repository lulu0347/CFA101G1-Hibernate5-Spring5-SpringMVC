package com.bidrecord.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class BidRecordVO implements Serializable {
	private Integer bidRecordNo;
	private Integer bidProdNo;
	private Integer memNo;
	private Integer bidPrice;
	private Timestamp bidTime;
	
	public Integer getBidRecordNo() {
		return bidRecordNo;
	}
	public void setBidRecordNo(Integer bidRecordNo) {
		this.bidRecordNo = bidRecordNo;
	}
	public Integer getBidProdNo() {
		return bidProdNo;
	}
	public void setBidProdNo(Integer bidProdNo) {
		this.bidProdNo = bidProdNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Timestamp getBidTime() {
		return bidTime;
	}
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}
}

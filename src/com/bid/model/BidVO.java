package com.bid.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class BidVO implements Serializable {
	private Integer bidProdNo;
	private Integer kindNo;
	private Integer transRecNo;
	private Integer bidWinnerNo;
	private String bidProdName;
	private String bidProdDescription;
	private Integer bidProdStartPrice;
	private Integer bidState;
	private Timestamp bidProdStartTime;
	private Timestamp bidProdEndTime;
	private Integer bidWinnerPrice;
	private Integer bidPriceIncrement;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private Integer bidProdState;
	
	public Integer getBidProdNo() {
		return bidProdNo;
	}
	public void setBidProdNo(Integer bidProdNo) {
		this.bidProdNo = bidProdNo;
	}
	public Integer getKindNo() {
		return kindNo;
	}
	public void setKindNo(Integer kindNo) {
		this.kindNo = kindNo;
	}
	public Integer getTransRecNo() {
		return transRecNo;
	}
	public void setTransRecNo(Integer transRecNo) {
		this.transRecNo = transRecNo;
	}
	public Integer getBidWinnerNo() {
		return bidWinnerNo;
	}
	public void setBidWinnerNo(Integer bidWinnerNo) {
		this.bidWinnerNo = bidWinnerNo;
	}
	public String getBidProdName() {
		return bidProdName;
	}
	public void setBidProdName(String bidProdName) {
		this.bidProdName = bidProdName;
	}
	public String getBidProdDescription() {
		return bidProdDescription;
	}
	public void setBidProdDescription(String bidProdDescription) {
		this.bidProdDescription = bidProdDescription;
	}
	public Integer getBidProdStartPrice() {
		return bidProdStartPrice;
	}
	public void setBidProdStartPrice(Integer bidProdStartPrice) {
		this.bidProdStartPrice = bidProdStartPrice;
	}
	public Integer getBidState() {
		return bidState;
	}
	public void setBidState(Integer bidState) {
		this.bidState = bidState;
	}
	public Timestamp getBidProdStartTime() {
		return bidProdStartTime;
	}
	public void setBidProdStartTime(Timestamp bidProdStartTime) {
		this.bidProdStartTime = bidProdStartTime;
	}
	public Timestamp getBidProdEndTime() {
		return bidProdEndTime;
	}
	public void setBidProdEndTime(Timestamp bidProdEndTime) {
		this.bidProdEndTime = bidProdEndTime;
	}
	public Integer getBidWinnerPrice() {
		return bidWinnerPrice;
	}
	public void setBidWinnerPrice(Integer bidWinnerPrice) {
		this.bidWinnerPrice = bidWinnerPrice;
	}
	public Integer getBidPriceIncrement() {
		return bidPriceIncrement;
	}
	public void setBidPriceIncrement(Integer bidPriceIncrement) {
		this.bidPriceIncrement = bidPriceIncrement;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public Integer getBidProdState() {
		return bidProdState;
	}
	public void setBidProdState(Integer bidProdState) {
		this.bidProdState = bidProdState;
	}
}

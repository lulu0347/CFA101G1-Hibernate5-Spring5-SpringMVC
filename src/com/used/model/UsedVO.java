package com.used.model;

import java.sql.Date;
import java.sql.Timestamp;

public class UsedVO implements java.io.Serializable{
	private Integer usedNo;
	private Integer kindNo;
	private Integer buyerNo;
	private Integer sellerNo;
	private Integer transRecNo;
	private String usedName;
	private Integer usedPrice;
	private Integer usedState;
	private Timestamp usedLaunchedTime;
	private Timestamp soldTime;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private String usedProdDescription;
	
	public Integer getUsedNo() {
		return usedNo;
	}
	public void setUsedNo(Integer usedNo) {
		this.usedNo = usedNo;
	}
	public Integer getKindNo() {
		return kindNo;
	}
	public void setKindNo(Integer kindNo) {
		this.kindNo = kindNo;
	}
	public Integer getBuyerNo() {
		return buyerNo;
	}
	public void setBuyerNo(Integer buyerNo) {
		this.buyerNo = buyerNo;
	}
	public Integer getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(Integer sellerNo) {
		this.sellerNo = sellerNo;
	}
	public Integer getTransRecNo() {
		return transRecNo;
	}
	public void setTransRecNo(Integer transRecNo) {
		this.transRecNo = transRecNo;
	}
	public String getUsedName() {
		return usedName;
	}
	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	public Integer getUsedPrice() {
		return usedPrice;
	}
	public void setUsedPrice(Integer usedPrice) {
		this.usedPrice = usedPrice;
	}
	public Integer getUsedState() {
		return usedState;
	}
	public void setUsedState(Integer usedState) {
		this.usedState = usedState;
	}
	public Timestamp getUsedLaunchedTime() {
		return usedLaunchedTime;
	}
	public void setUsedLaunchedTime(Timestamp usedLaunchedTime) {
		this.usedLaunchedTime = usedLaunchedTime;
	}
	public Timestamp getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(Timestamp soldTime) {
		this.soldTime = soldTime;
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
	public String getUsedProdDescription() {
		return usedProdDescription;
	}
	public void setUsedProdDescription(String usedProdDescription) {
		this.usedProdDescription = usedProdDescription;
	}

}

package com.itemIC.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemICVO implements Serializable{
	
	
	private Integer memNo;
	private Integer itemNo;
	private Integer kindNo;
	private String itemName;
	private	Integer itemPrice;
	private	Integer itemState;
	private Double warrantyDate;
	
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getKindNo() {
		return kindNo;
	}
	public void setKindNo(Integer kindNo) {
		this.kindNo = kindNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Integer getItemState() {
		return itemState;
	}
	public void setItemState(Integer itemState) {
		this.itemState = itemState;
	}
	public Double getWarrantyDate() {
		return warrantyDate;
	}
	public void setWarrantyDate(Double warrantyDate) {
		this.warrantyDate = warrantyDate;
	}
	
	
}

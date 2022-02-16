package com.itemIOD.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemIODVO implements Serializable{

	private Integer orderNo;
	private Integer memNo;
	private Timestamp tranTime;
	private Integer orderTotal;
	private Integer orderState;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private Integer itemNo;
	private	Integer itemSales;
	private	Integer itemPrice;
	private Integer kindNo;
	private String itemName;
	private Timestamp soldTime;
	private Timestamp launchedTime;
	private Double warrantyDate;
	private String itemProdDescription;
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Timestamp getTranTime() {
		return tranTime;
	}
	public void setTranTime(Timestamp tranTime) {
		this.tranTime = tranTime;
	}
	public Integer getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
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
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getItemSales() {
		return itemSales;
	}
	public void setItemSales(Integer itemSales) {
		this.itemSales = itemSales;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
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
	public Timestamp getSoldTime() {
		return soldTime;
	}
	public void setSoldTime(Timestamp soldTime) {
		this.soldTime = soldTime;
	}
	public Timestamp getLaunchedTime() {
		return launchedTime;
	}
	public void setLaunchedTime(Timestamp launchedTime) {
		this.launchedTime = launchedTime;
	}
	public Double getWarrantyDate() {
		return warrantyDate;
	}
	public void setWarrantyDate(Double warrantyDate) {
		this.warrantyDate = warrantyDate;
	}
	public String getItemProdDescription() {
		return itemProdDescription;
	}
	public void setItemProdDescription(String itemProdDescription) {
		this.itemProdDescription = itemProdDescription;
	}
	
	
}

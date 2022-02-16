package com.auctActProd.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctActProdVO implements Serializable{
	
	private Integer auctActProdNo;
	private Integer auctActNo;
	private Integer auctProdNo;
	private Integer auctProdQty;
	private Integer auctProdRemain;
	private Integer auctState;
	private Integer auctProdPrice;
	
	
	public AuctActProdVO() {
		super();
		
	}
	public AuctActProdVO(Integer auctActProdNo, Integer auctActNo, Integer auctProdNo, Integer auctProdQty,
			Integer auctProdRemain, Integer auctState, Integer auctProdPrice) {
		super();
		this.auctActProdNo = auctActProdNo;
		this.auctActNo = auctActNo;
		this.auctProdNo = auctProdNo;
		this.auctProdQty = auctProdQty;
		this.auctProdRemain = auctProdRemain;
		this.auctState = auctState;
		this.auctProdPrice = auctProdPrice;
	}
	public Integer getAuctActProdNo() {
		return auctActProdNo;
	}
	public void setAuctActProdNo(Integer auctActProdNo) {
		this.auctActProdNo = auctActProdNo;
	}
	public Integer getAuctActNo() {
		return auctActNo;
	}
	public void setAuctActNo(Integer auctActNo) {
		this.auctActNo = auctActNo;
	}
	public Integer getAuctProdNo() {
		return auctProdNo;
	}
	public void setAuctProdNo(Integer auctProdNo) {
		this.auctProdNo = auctProdNo;
	}
	public Integer getAuctProdQty() {
		return auctProdQty;
	}
	public void setAuctProdQty(Integer auctProdQty) {
		this.auctProdQty = auctProdQty;
	}
	public Integer getAuctProdRemain() {
		return auctProdRemain;
	}
	public void setAuctProdRemain(Integer auctProdRemain) {
		this.auctProdRemain = auctProdRemain;
	}
	public Integer getAuctState() {
		return auctState;
	}
	public void setAuctState(Integer auctState) {
		this.auctState = auctState;
	}
	public Integer getAuctProdPrice() {
		return auctProdPrice;
	}
	public void setAuctProdPrice(Integer auctProdPrice) {
		this.auctProdPrice = auctProdPrice;
	}
	@Override
	public String toString() {
		return "AuctActProdVO [auctActProdNo=" + auctActProdNo + ", auctActNo=" + auctActNo + ", auctProdNo="
				+ auctProdNo + ", auctProdQty=" + auctProdQty + ", auctProdRemain=" + auctProdRemain + ", auctState="
				+ auctState + ", auctProdPrice=" + auctProdPrice + "]";
	}
	
	
	
	
}

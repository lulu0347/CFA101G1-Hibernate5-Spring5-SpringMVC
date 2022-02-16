package com.auctOrderDetl.model;

import java.io.Serializable;

public class AuctOrdDetlVO implements Serializable{

	private Integer auctOrdNo;
	private Integer auctActProdNo;
	private Integer prodPurQty;
	private Integer price;
	private Integer sumPrice;
	
	public AuctOrdDetlVO() {
		super();
		
	}
	


	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
	}

	public AuctOrdDetlVO(Integer auctOrdNo, Integer auctActProdNo, Integer prodPurQty) {
		super();
		this.auctOrdNo = auctOrdNo;
		this.auctActProdNo = auctActProdNo;
		this.prodPurQty = prodPurQty;
	}

	public Integer getAuctOrdNo() {
		return auctOrdNo;
	}

	public void setAuctOrdNo(Integer auctOrdNo) {
		this.auctOrdNo = auctOrdNo;
	}

	public Integer getAuctActProdNo() {
		return auctActProdNo;
	}

	public void setAuctActProdNo(Integer auctActProdNo) {
		this.auctActProdNo = auctActProdNo;
	}

	public Integer getProdPurQty() {
		return prodPurQty;
	}

	public void setProdPurQty(Integer prodPurQty) {
		this.prodPurQty = prodPurQty;
	}

	@Override
	public String toString() {
		return "AuctOrdDetlVO [auctOrdNo=" + auctOrdNo + ", auctActProdNo=" + auctActProdNo + ", prodPurQty="
				+ prodPurQty + "]";
	}
	
	
	
}

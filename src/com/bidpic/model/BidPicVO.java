package com.bidpic.model;

import java.io.Serializable;

public class BidPicVO implements Serializable {
	private Integer bidProdPicNo;
	private Integer bidProdNo;
	private byte[] bidProdPicContent;
	
	public Integer getBidProdPicNo() {
		return bidProdPicNo;
	}
	public void setBidProdPicNo(Integer bidProdPicNo) {
		this.bidProdPicNo = bidProdPicNo;
	}
	public Integer getBidProdNo() {
		return bidProdNo;
	}
	public void setBidProdNo(Integer bidProdNo) {
		this.bidProdNo = bidProdNo;
	}
	public byte[] getBidProdPicContent() {
		return bidProdPicContent;
	}
	public void setBidProdPicContent(byte[] bidProdPicContent) {
		this.bidProdPicContent = bidProdPicContent;
	}
	
}

package com.auctProd.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctProdVO implements Serializable{

	private Integer auctProdNo;
	private String auctProdName;
	private Integer kindNo;
	private Integer auctProdState;
	private String auctProDesc;
	private Timestamp auctProdCreTime;
	private Timestamp auctProdModTime;
	
	public AuctProdVO() {
		super();
	}
	public AuctProdVO(Integer auctProdNo, String auctProdName, Integer kindNo, Integer auctProdState,
			String auctProDesc, Timestamp auctProdCreTime, Timestamp auctProdModTime) {
		super();
		this.auctProdNo = auctProdNo;
		this.auctProdName = auctProdName;
		this.kindNo = kindNo;
		this.auctProdState = auctProdState;
		this.auctProDesc = auctProDesc;
		this.auctProdCreTime = auctProdCreTime;
		this.auctProdModTime = auctProdModTime;
	}
	public Integer getAuctProdNo() {
		return auctProdNo;
	}
	public void setAuctProdNo(Integer auctProdNo) {
		this.auctProdNo = auctProdNo;
	}
	public String getAuctProdName() {
		return auctProdName;
	}
	public void setAuctProdName(String auctProdName) {
		this.auctProdName = auctProdName;
	}
	public Integer getKindNo() {
		return kindNo;
	}
	public void setKindNo(Integer kindNo) {
		this.kindNo = kindNo;
	}
	public Integer getAuctProdState() {
		return auctProdState;
	}
	public void setAuctProdState(Integer auctProdState) {
		this.auctProdState = auctProdState;
	}
	public String getAuctProdDesc() {
		return auctProDesc;
	}
	public void setAuctProdDesc(String auctProDesc) {
		this.auctProDesc = auctProDesc;
	}
	public Timestamp getAuctProdCreTime() {
		return auctProdCreTime;
	}
	public void setAuctProdCreTime(Timestamp auctProdCreTime) {
		this.auctProdCreTime = auctProdCreTime;
	}
	public Timestamp getAuctProdModTime() {
		return auctProdModTime;
	}
	public void setAuctProdModTime(Timestamp auctProdModTime) {
		this.auctProdModTime = auctProdModTime;
	}
	
	
	
	
}

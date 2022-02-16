package com.auctPic.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

public class AuctProdPicVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auctProdPicNo;
	private Integer auctProdNo;
	private String auctProdPicInfo;
	private byte[] auctProdPic;
	private String auctProdPicFormat;
	
	
	public AuctProdPicVO() {
		super();
	}


	public AuctProdPicVO(Integer auctProdPicNo, Integer auctProdNo, String auctProdPicInfo, byte[] auctProdPic,
			String auctProdPicFormat) {
		super();
		this.auctProdPicNo = auctProdPicNo;
		this.auctProdNo = auctProdNo;
		this.auctProdPicInfo = auctProdPicInfo;
		this.auctProdPic = auctProdPic;
		this.auctProdPicFormat = auctProdPicFormat;
	}


	public Integer getAuctProdPicNo() {
		return auctProdPicNo;
	}


	public void setAuctProdPicNo(Integer auctProdPicNo) {
		this.auctProdPicNo = auctProdPicNo;
	}


	public Integer getAuctProdNo() {
		return auctProdNo;
	}


	public void setAuctProdNo(Integer auctProdNo) {
		this.auctProdNo = auctProdNo;
	}


	public String getAuctProdPicInfo() {
		return auctProdPicInfo;
	}


	public void setAuctProdPicInfo(String auctProdPicInfo) {
		this.auctProdPicInfo = auctProdPicInfo;
	}


	public byte[] getAuctProdPic() {
		return auctProdPic;
	}


	public void setAuctProdPic(byte[] auctProdPic) {
		this.auctProdPic = auctProdPic;
	}


	public String getAuctProdPicFormat() {
		return auctProdPicFormat;
	}


	public void setAuctProdPicFormat(String auctProdPicFormat) {
		this.auctProdPicFormat = auctProdPicFormat;
	}


	@Override
	public String toString() {
		return "AuctProdPicVO [auctProdPicNo=" + auctProdPicNo + ", auctProdNo=" + auctProdNo + ", auctProdPicInfo="
				+ auctProdPicInfo + ", auctProdPic=" + Arrays.toString(auctProdPic) + ", auctProdPicFormat="
				+ auctProdPicFormat + "]";
	}

	
	

}

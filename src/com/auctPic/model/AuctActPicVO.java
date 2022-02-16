package com.auctPic.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;

public class AuctActPicVO implements Serializable {

	private Integer auctActPicNo;
	private Integer auctActNo;
	private String auctActPicInfo;
	private byte[] auctActPic;
	private String auctActPicFormat;

	public AuctActPicVO() {
		super();

	}

	public AuctActPicVO(Integer auctActPicNo, Integer auctActNo, String auctActPicInfo, byte[] auctActPic,
			String auctActPicFormat) {
		super();
		this.auctActPicNo = auctActPicNo;
		this.auctActNo = auctActNo;
		this.auctActPicInfo = auctActPicInfo;
		this.auctActPic = auctActPic;
		this.auctActPicFormat = auctActPicFormat;
	}

	public Integer getAuctActPicNo() {
		return auctActPicNo;
	}

	public void setAuctActPicNo(Integer auctActPicNo) {
		this.auctActPicNo = auctActPicNo;
	}

	public Integer getAuctActNo() {
		return auctActNo;
	}

	public void setAuctActNo(Integer auctActNo) {
		this.auctActNo = auctActNo;
	}

	public String getAuctActPicInfo() {
		return auctActPicInfo;
	}

	public void setAuctActPicInfo(String auctActPicInfo) {
		this.auctActPicInfo = auctActPicInfo;
	}

	public byte[] getAuctActPic() {
		return auctActPic;
	}

	public void setAuctActPic(byte[] auctActPic) {
		this.auctActPic = auctActPic;
	}

	public String getAuctActPicFormat() {
		return auctActPicFormat;
	}

	public void setAuctActPicFormat(String auctActPicFormat) {
		this.auctActPicFormat = auctActPicFormat;
	}

	@Override
	public String toString() {
		return "AuctActPicVO [auctActPicNo=" + auctActPicNo + ", auctActNo=" + auctActNo + ", auctActPicInfo="
				+ auctActPicInfo + ", auctActPic=" + Arrays.toString(auctActPic) + ", auctActPicFormat="
				+ auctActPicFormat + "]";
	}

	public String getBase64() {
		String format = "data:%s;base64,%s";

		return String.format(format, this.auctActPicFormat, Base64.getEncoder().encodeToString(this.auctActPic));
	}

}

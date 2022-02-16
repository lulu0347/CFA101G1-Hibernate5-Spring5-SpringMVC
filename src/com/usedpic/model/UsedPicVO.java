package com.usedpic.model;

import java.sql.Blob;


public class UsedPicVO implements java.io.Serializable{
	private Integer usedPicNo;
	private Integer usedNo;
	private byte[] usedPic;
	private String path;
	
	public Integer getUsedPicNo() {
		return usedPicNo;
	}
	public void setUsedPicNo(Integer usedPicNo) {
		this.usedPicNo = usedPicNo;
	}
	public Integer getUsedNo() {
		return usedNo;
	}
	public void setUsedNo(Integer usedNo) {
		this.usedNo = usedNo;
	}
	public byte[] getUsedPic() {
		return usedPic;
	}
	public void setUsedPic(byte[] usedPic) {
		this.usedPic = usedPic;
	}

}

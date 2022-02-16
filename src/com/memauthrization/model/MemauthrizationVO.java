package com.memauthrization.model;

import java.sql.Date;

public class MemauthrizationVO implements java.io.Serializable{
	
	private Integer memno;
	private Integer memauthfunno;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public Integer getMemauthfunno() {
		return memauthfunno;
	}
	public void setMemauthfunno(Integer memauthfunno) {
		this.memauthfunno = memauthfunno;
	}
}

package com.member.model;
// Generated 2022�~1��16�� �U��5:27:19 by Hibernate Tools 5.4.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mem generated by hbm2java
 */
public class MemberVO implements java.io.Serializable {

	private Integer memNo;
	private String memAccount;
	private String memPassword;
	private int memStatus;
	private Integer memVrfed;
	private Date memNoVrftime;
	private String memName;
	private String memMobile;
	private String memCity;
	private String memDist;
	private String memAdd;
	private String memEmail;
	private Date memBirth;
	private Date memJointime;
	private int usderStatus;
	private int ecash;
	private Set transRecs = new HashSet(0);
	private Set itemOrders = new HashSet(0);

	public MemberVO() {
	}

	public MemberVO(String memAccount, String memPassword, int memStatus, String memName, String memMobile, String memCity,
			String memDist, String memAdd, String memEmail, Date memBirth, int usderStatus, int ecash) {
		this.memAccount = memAccount;
		this.memPassword = memPassword;
		this.memStatus = memStatus;
		this.memName = memName;
		this.memMobile = memMobile;
		this.memCity = memCity;
		this.memDist = memDist;
		this.memAdd = memAdd;
		this.memEmail = memEmail;
		this.memBirth = memBirth;
		this.usderStatus = usderStatus;
		this.ecash = ecash;
	}

	public MemberVO(String memAccount, String memPassword, int memStatus, Integer memVrfed, Date memNoVrftime,
			String memName, String memMobile, String memCity, String memDist, String memAdd, String memEmail,
			Date memBirth, Date memJointime, int usderStatus, int ecash, Set transRecs, Set itemOrders) {
		this.memAccount = memAccount;
		this.memPassword = memPassword;
		this.memStatus = memStatus;
		this.memVrfed = memVrfed;
		this.memNoVrftime = memNoVrftime;
		this.memName = memName;
		this.memMobile = memMobile;
		this.memCity = memCity;
		this.memDist = memDist;
		this.memAdd = memAdd;
		this.memEmail = memEmail;
		this.memBirth = memBirth;
		this.memJointime = memJointime;
		this.usderStatus = usderStatus;
		this.ecash = ecash;
		this.transRecs = transRecs;
		this.itemOrders = itemOrders;
	}

	public Integer getMemNo() {
		return this.memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public String getMemAccount() {
		return memAccount;
	}

	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public int getMemStatus() {
		return this.memStatus;
	}

	public void setMemStatus(int memStatus) {
		this.memStatus = memStatus;
	}

	public Integer getMemVrfed() {
		return this.memVrfed;
	}

	public void setMemVrfed(Integer memVrfed) {
		this.memVrfed = memVrfed;
	}

	public Date getMemNoVrftime() {
		return this.memNoVrftime;
	}

	public void setMemNoVrftime(Date memNoVrftime) {
		this.memNoVrftime = memNoVrftime;
	}

	public String getMemName() {
		return this.memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemMobile() {
		return this.memMobile;
	}

	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}

	public String getMemCity() {
		return this.memCity;
	}

	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}

	public String getMemDist() {
		return this.memDist;
	}

	public void setMemDist(String memDist) {
		this.memDist = memDist;
	}

	public String getMemAdd() {
		return this.memAdd;
	}

	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}

	public String getMemEmail() {
		return this.memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Date getMemBirth() {
		return this.memBirth;
	}

	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}

	public Date getMemJointime() {
		return this.memJointime;
	}

	public void setMemJointime(Date memJointime) {
		this.memJointime = memJointime;
	}

	public int getUsderStatus() {
		return this.usderStatus;
	}

	public void setUsderStatus(int usderStatus) {
		this.usderStatus = usderStatus;
	}

	public int getEcash() {
		return this.ecash;
	}

	public void setEcash(int ecash) {
		this.ecash = ecash;
	}

	public Set getTransRecs() {
		return this.transRecs;
	}

	public void setTransRecs(Set transRecs) {
		this.transRecs = transRecs;
	}

	public Set getItemOrders() {
		return this.itemOrders;
	}

	public void setItemOrders(Set itemOrders) {
		this.itemOrders = itemOrders;
	}

}
package com.itemCollection.model;

import java.io.Serializable;

//雙主鍵綁成的POJO務必 務必 務必!!override equals和hashCode方法
public class ItemCollectionId implements java.io.Serializable {

	private int memNo;
	private int itemNo;

	public ItemCollectionId() {
	}

	public ItemCollectionId(int memNo, int itemNo) {
		this.memNo = memNo;
		this.itemNo = itemNo;
	}

	public int getMemNo() {
		return this.memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemCollectionId))
			return false;
		ItemCollectionId castOther = (ItemCollectionId) other;

		return (this.getMemNo() == castOther.getMemNo()) && (this.getItemNo() == castOther.getItemNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMemNo();
		result = 37 * result + this.getItemNo();
		return result;
	}

}

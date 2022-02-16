package com.itemDetail.model;


public class ItemDetailId implements java.io.Serializable {

	private int orderNo;
	private int itemNo;

	public ItemDetailId() {
	}

	public ItemDetailId(int orderNo, int itemNo) {
		this.orderNo = orderNo;
		this.itemNo = itemNo;
	}

	public int getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
		if (!(other instanceof ItemDetailId))
			return false;
		ItemDetailId castOther = (ItemDetailId) other;

		return (this.getOrderNo() == castOther.getOrderNo()) && (this.getItemNo() == castOther.getItemNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getOrderNo();
		result = 37 * result + this.getItemNo();
		return result;
	}

}

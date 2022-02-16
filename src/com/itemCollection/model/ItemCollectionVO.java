package com.itemCollection.model;

import com.itemCollection.model.ItemCollectionId;

public class ItemCollectionVO implements java.io.Serializable {

	//雙主鍵必須將兩個Id綁成一個POJO
	private ItemCollectionId id;

	public ItemCollectionVO() {
	}

	public ItemCollectionVO(ItemCollectionId id) {
		this.id = id;
	}

	public ItemCollectionId getId() {
		return id;
	}

	public void setId(ItemCollectionId id) {
		this.id = id;
	}

	

}

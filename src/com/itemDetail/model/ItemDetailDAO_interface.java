package com.itemDetail.model;

import java.sql.Connection;
import java.util.*;


public interface ItemDetailDAO_interface {
//------------------新增訂單明細(未實裝)--------------------------------
	public void addItemDetail(ItemDetailVO ItemDetailVO);
//------------------更新訂單明細--------------------------------
	public void updateItemDetail(ItemDetailVO itemDetailVO);
//------------------刪除訂單明細--------------------------------
	public void deleteItemDetail(Integer OrderNo, Integer ItemNo);
//------------------以訂單編號查詢單一訂單所有明細--------------------------------
	public List<ItemDetailVO> GetAllByOrderNo(Integer OrderNo);
//------------------查詢所有訂單明細--------------------------------
	public List<ItemDetailVO> getAll();
//------------------以訂單與商品編號查詢單一訂單明細--------------------------------	
	public ItemDetailVO findByPrimaryKey(Integer OrderNo, Integer ItemNo);
	
//--------------用於購物車的----------------------
	public void updateByShopping();
	
}
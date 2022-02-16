package com.itemPic.model;

import java.util.*;
import java.sql.Connection;

public interface ItemPicDAO_interface {
	//------------------新增商品照片--------------------------------
	public void addPic(ItemPicVO ItemPicVO);
	//------------------更新商品照片--------------------------------
	public void updatePic(ItemPicVO ItemPicVO);
	//------------------刪除商品照片--------------------------------
	public void deletePic(Integer ItemPicNo);
	//------------------依商品照片編號查詢--------------------------------
	public ItemPicVO findByItemPicNo(Integer ItemPicNo);
	//------------------查詢所有商品照片--------------------------------
	public List<ItemPicVO> getAllPics();
	//------------------依商品編號查詢該商品照片--------------------------------
	public List<ItemPicVO> getByItemNo(Integer itemNo);
	//用照片編號來顯示圖片
	public ItemPicVO findByItemPicNoForByte(Integer itemPicNo);
	//用商品編號來顯示圖片
	public ItemPicVO findByItemNoForByte(Integer itemNo);
}

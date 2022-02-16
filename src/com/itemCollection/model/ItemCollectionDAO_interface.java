package com.itemCollection.model;

import java.util.*;

public interface ItemCollectionDAO_interface {
	//------------------新增商品收藏--------------------------------
	public void insert(ItemCollectionVO ItemCollectionVO);
	//------------------更新商品收藏--------------------------------
	public void update(ItemCollectionVO ItemCollectionVO);
	//------------------刪除商品收藏--------------------------------
	public void delete(Integer ItemNo, Integer memNo);
	//------------------依照會員編號尋找該會員商品收藏--------------------------------
	public ItemCollectionVO  findOneByMemNo(Integer memNo);
	//------------------依照會員編號尋找該會員所有商品收藏--------------------------------
	public List<ItemCollectionVO> findByMemNo(Integer memNo);
	//------------------查詢全部會員商品收藏--------------------------------
	public List<ItemCollectionVO> getAll();
	
	public Long getcount(Integer itemNo, Integer memNo);

	

}

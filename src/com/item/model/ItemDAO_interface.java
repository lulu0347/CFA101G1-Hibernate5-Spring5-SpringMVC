package com.item.model;

import java.util.*;

public interface ItemDAO_interface {
	// -----------後台新增商品---------------
	public void insert(ItemVO ItemVO);

	// -----------後台新增商品與照片---------------
	public void insertWithPics(ItemVO itemVO);

	// -----------後台更新商品---------------
	public void update(ItemVO ItemVO);

	// -----------後台刪除商品---------------
	public void delete(Integer ItemNo);

	// -----------依商品編號查詢商品---------------
	public ItemVO findByItemNo(Integer ItemNo);

	// -----------取得所有商品---------------
	public List<ItemVO> getAll();

	// ----------- 手機---------------
	public List<ItemVO> getOneKind1();
	// ----------- 電腦---------------
	public List<ItemVO> getOneKind2();
	// ----------- 相機---------------
	public List<ItemVO> getOneKind3();
	// ----------- 手錶---------------
	public List<ItemVO> getOneKind4();
	// ----------- 配件---------------
	public List<ItemVO> getOneKind5();
	// -----------模糊搜尋----------------
	public List<ItemVO> getAll(Map<String, String[]> map);
}

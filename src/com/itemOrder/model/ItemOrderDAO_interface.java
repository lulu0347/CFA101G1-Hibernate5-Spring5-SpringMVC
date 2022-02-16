package com.itemOrder.model;

import java.sql.Connection;
import java.util.List;

import com.itemDetail.model.ItemDetailVO;
import com.transRec.model.TransRecVO;

public interface ItemOrderDAO_interface {
	//------------------新增訂單--------------------------------
	public void addOrder(ItemOrderVO ItemOrderVO);
	//------------------依照單一訂單編號更新某一訂單-----------------
	public void updateByOrderNo(ItemOrderVO ItemOrderVO);
	//------------------刪除訂單--------------------------------
	public void delete(Integer OrderNo);
	//------------------(前台)+(後台)取得某一會員所持有的訂單--------------------
	public List<ItemOrderVO> GetOrderByMemNo(Integer MemNo);
	//------------------依照單一訂單編號查詢某一訂單-----------------
	public ItemOrderVO  findByOrderNo(Integer OrderNo);
	//------------------(後台)查詢全部-----------------
	public List<ItemOrderVO> getAll();
	//------------------(後台)查詢未出貨的訂單-----------------
	public List<ItemOrderVO> findByOrderState();
	//------------------(後台)查詢已出貨的訂單-----------------
	public List<ItemOrderVO> findByOrderState1();
	//------------------(後台)查詢已收貨的訂單-----------------
	public List<ItemOrderVO> findByOrderState2();
	//------------------(後台)查詢被退貨的訂單-----------------
	public List<ItemOrderVO> findByOrderState3();
	//------------------(後台)查詢被取消的訂單-----------------
	public List<ItemOrderVO> findByOrderState4();

	
	//---同時新增部門與員工 (實務上並不常用, 可用在訂單主檔與明細檔一次新增成功)----------------
    public void insertWithItemDetails(ItemOrderVO itemOrderVO, List<ItemDetailVO> list, TransRecVO transRecVO);
}

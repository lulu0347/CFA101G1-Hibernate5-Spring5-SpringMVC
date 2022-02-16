package com.itemIOD.model;

import java.util.List;

public class ItemIODService {
	
	private ItemIODDAO_interface dao;
	
	public ItemIODService() {
		dao = new ItemIODDAO();
	}
	
	//----(前台)會員搜尋自己的訂單列表-----
	public List<ItemIODVO> getOrderByMemNo(Integer memNo){
		return dao.getOrderByMemNo(memNo);
	}
	
	//----(前台)會員取消訂單----------
	public void CancelOrder(Integer orderNo) {
		dao.CancelOrder(orderNo);
	}
	//----(前台)會員訂單退貨----------
	public void ReturnOrder(Integer orderNo) {
		dao.ReturnOrder(orderNo);
	}
	//----(後台)訂單出貨----------
	public void ShippedOrder(Integer orderNo) {
		dao.ShippedOrder(orderNo);
	}
	
	//----(前台)會員訂單收穫----------
	public void ReceiptOrder(Integer orderNo) {
		dao.ReceiptOrder(orderNo);
	}

	//----(前台)會員搜尋自己的訂單明細列表-----
	public List<ItemIODVO> getDetailByOrderNo(Integer orderNo){
		return dao.getDetailByOrderNo(orderNo);
	}
}

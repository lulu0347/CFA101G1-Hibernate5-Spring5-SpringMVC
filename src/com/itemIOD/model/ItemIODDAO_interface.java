package com.itemIOD.model;

import java.util.List;

public interface ItemIODDAO_interface {
	
	public List<ItemIODVO> getOrderByMemNo(Integer MemNo);
	
	public List<ItemIODVO> getDetailByOrderNo(Integer orderNo);
	
	public void CancelOrder(Integer orderNo);
	public void ReturnOrder(Integer orderNo);
	public void ReceiptOrder(Integer orderNo);
	public void ShippedOrder(Integer orderNo);
}

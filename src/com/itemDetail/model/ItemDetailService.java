package com.itemDetail.model;

import java.sql.Connection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;

public class ItemDetailService {
	
	private static ItemDetailDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ItemDetailDAO_interface) context.getBean("ItemDetailDAO");
	}
		
	public void addItemDetail(Integer orderNo, Integer itemNo, Integer itemSales, Integer itemPrice) {
		
		ItemDetailVO itemDetailVO = new ItemDetailVO();
		ItemDetailId itemDetailId = new ItemDetailId();
		itemDetailId.setItemNo(itemNo);
		itemDetailId.setOrderNo(orderNo);
		itemDetailVO.setId(itemDetailId);
		itemDetailVO.setItemSales(itemSales);
		itemDetailVO.setItemPrice(itemPrice);
		dao.addItemDetail(itemDetailVO);
		
	}
	//------------------更新訂單明細--------------------------------
	public ItemDetailVO updateItemDetail(Integer itemSales, Integer orderNo, Integer itemNo) {
		
		ItemDetailVO itemDetailVO = new ItemDetailVO();
		ItemDetailId itemDetailId = new ItemDetailId();
		itemDetailId.setItemNo(itemNo);
		itemDetailId.setOrderNo(orderNo);
		itemDetailVO.setId(itemDetailId);
		itemDetailVO.setItemSales(itemSales);
		dao.updateItemDetail(itemDetailVO);
		
		return itemDetailVO;
	}
	
	public void deleteItemDetail(Integer orderNo, Integer itemNo) {
		dao.deleteItemDetail(itemNo, orderNo);	

	}
	//------------------以訂單編號查詢單一訂單所有明細--------------------------------	
	public List<ItemDetailVO> GetAllByOrderNo(Integer orderNo) {
		return dao.GetAllByOrderNo(orderNo);
	}
	//------------------查詢所有訂單明細--------------------------------
	public List<ItemDetailVO> getAll(){
		return dao.getAll();
	}
	
	public ItemDetailVO findByPrimaryKey(Integer orderNo, Integer itemNo) {
		return dao.findByPrimaryKey(orderNo, itemNo);
	}
	
	//--------------同時新增訂單跟明細---------------
	public void updateByShopping() {
	}
		
	
}

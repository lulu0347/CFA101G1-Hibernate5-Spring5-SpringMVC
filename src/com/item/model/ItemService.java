package com.item.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itemPic.model.ItemPicDAO;
import com.itemPic.model.ItemPicVO;
import com.productKind.model.ProductKindVO;

public class ItemService {

	private static ItemDAO_interface dao;

	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ItemDAO_interface) context.getBean("ItemDAO");
	}

	// -----------後台新增商品--------------
	public ItemVO addItem(ProductKindVO productKindVO, String itemName, Integer itemPrice, Integer itemState,
			java.sql.Date launchedTime, BigDecimal warrantyDate, String itemProdDescription) {

		ItemVO itemVO = new ItemVO();

		itemVO.setProductKindVO(productKindVO);
		itemVO.setItemName(itemName);
		itemVO.setItemPrice(itemPrice);
		itemVO.setItemState(itemState);
		itemVO.setLaunchedTime(launchedTime);
		itemVO.setWarrantyDate(warrantyDate);
		itemVO.setItemProdDescription(itemProdDescription);
		dao.insert(itemVO);

		return itemVO;
	}
	
	// -----------後台新增商品ForMVC--------------
	public void addItemForMVC(ItemVO itemVO) {
		dao.insert(itemVO);
	}

	// -----------後台更新商品---------------
	public ItemVO updateItem(Integer itemNo, ProductKindVO productKindVO, String itemName, Integer itemPrice, Integer itemState,
			java.sql.Date soldTime,  java.sql.Date launchedTime, BigDecimal warrantyDate,
			String itemProdDescription) {

		ItemVO itemVO = new ItemVO();
		itemVO.setItemNo(itemNo);
		itemVO.setProductKindVO(productKindVO);
		itemVO.setItemName(itemName);
		itemVO.setItemPrice(itemPrice);
		itemVO.setItemState(itemState);
		itemVO.setSoldTime(soldTime);
		itemVO.setLaunchedTime(launchedTime);
		itemVO.setWarrantyDate(warrantyDate);
		itemVO.setItemProdDescription(itemProdDescription);
		dao.update(itemVO);

		return itemVO;
	}
	
	// -----------後台更新商品---------------
	public void updateItemForMVC(ItemVO itemVO) {
		dao.update(itemVO);
	}

	// -----------後台刪除商品---------------
	public void deleteItem(Integer ItemNo) {
		dao.delete(ItemNo);
	}

	// -----------查看商品詳情---------------
	public ItemVO getOneItem(Integer ItemNo) {
		return dao.findByItemNo(ItemNo);
	}

	// -----------取得所有商品---------------
	public List<ItemVO> getAll() {
		return dao.getAll();
	}

	// ----------- 手機---------------
	public List<ItemVO> getOneKind1(){
		return dao.getOneKind1();
	}

	// ----------- 電腦---------------
	public List<ItemVO> getOneKind2(){
		return dao.getOneKind2();
	}

	// ----------- 相機---------------
	public List<ItemVO> getOneKind3(){
		return dao.getOneKind3();
	}

	// ----------- 手錶---------------
	public List<ItemVO> getOneKind4(){
		return dao.getOneKind4();
	}

	// ----------- 配件---------------
	public List<ItemVO> getOneKind5(){
		return dao.getOneKind5();
	}
	
	// -----------模糊搜尋-------------
	public List<ItemVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}


}

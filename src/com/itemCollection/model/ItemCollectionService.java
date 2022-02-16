package com.itemCollection.model;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemCollectionService {

	private static ItemCollectionDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ItemCollectionDAO_interface) context.getBean("ItemCollectionDAO");
	}
	
	public ItemCollectionVO addCollection(ItemCollectionId itemCollectionId) {
		
		ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
		itemCollectionVO.setId(itemCollectionId);
		dao.insert(itemCollectionVO);
		
		return itemCollectionVO;
	}
	
	
	public ItemCollectionVO updateCollection(ItemCollectionId itemCollectionId) {
		
		ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
		itemCollectionVO.setId(itemCollectionId);
		dao.update(itemCollectionVO);
		
		return itemCollectionVO;
	}
	
	public void deleteCollection(Integer itemNo, Integer memNo) {
		dao.delete(itemNo, memNo);
	}
	
	public ItemCollectionVO findOneByMemNo(Integer memNo){
		ItemCollectionVO itemCollectionVO = new ItemCollectionVO();

		itemCollectionVO = dao.findOneByMemNo(memNo);
		
		return itemCollectionVO;
	}
	
	public List<ItemCollectionVO> getOneCollection(Integer memNo) {
		return dao.findByMemNo(memNo);
	}
	
	public List<ItemCollectionVO> getAll(){
		return dao.getAll();
	}
//-----------回傳一個數字0或1來計算此會員是否對於這個商品有加入過收藏列表----------------------
	public Long getcount(Integer itemNo, Integer memNo) {
		return dao.getcount(itemNo, memNo);
	}
	
}

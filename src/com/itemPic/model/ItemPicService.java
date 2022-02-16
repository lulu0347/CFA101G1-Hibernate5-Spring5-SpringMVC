package com.itemPic.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;

public class ItemPicService {
	
	private static  ItemPicDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ItemPicDAO_interface) context.getBean("ItemPicDAO");
	}
	
	public ItemPicVO addPic(ItemPicVO ItemPicVO) {
		
		ItemPicVO itemPicVO = new ItemPicVO();
		dao.addPic(itemPicVO);
		
		return itemPicVO;
	}
	
	public void addPicForMVC(ItemPicVO ItemPicVO) {
		dao.addPic(ItemPicVO);
	}
	
	public void addPicAfterInsertItem(ItemPicVO ItemPicVO) {
		dao.addPic(ItemPicVO);
	}
	
	public void updatePic(ItemPicVO ItemPicVO) {
	
		dao.updatePic(ItemPicVO);
		
	}
	
	public void deletePic(Integer itemPicNo) {
		dao.deletePic(itemPicNo);
		
	}
	
	public ItemPicVO findByItemPicNo(Integer itemPicNo) {
		ItemPicVO itemPicVO = new ItemPicVO();
		itemPicVO = dao.findByItemPicNo(itemPicNo);
		
		return itemPicVO;
	}
	
	public ItemPicVO findByItemItemNo(Integer itemNo) {
		ItemPicVO itemPicVO = new ItemPicVO();
		itemPicVO = dao.findByItemNoForByte(itemNo);
		
		return itemPicVO;
	}
	
	public List<ItemPicVO> getAllPics(){
		return dao.getAllPics();
	}
	
	public List<ItemPicVO> findByItemNo(Integer itemNo) {
		return dao.getByItemNo(itemNo);
	}
	
	//特別放在這邊，警惕你不要又跟findByItemPicNo看錯,浪費2小時
	public ItemPicVO findByItemPicNoForByte(Integer itemPicNo) {
		return dao.findByItemPicNoForByte(itemPicNo);
	}
}

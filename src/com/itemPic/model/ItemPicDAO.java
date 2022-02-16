package com.itemPic.model;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO_interface;

import hibernate.util.HibernateUtil;

@Transactional(readOnly = true)
public class ItemPicDAO implements ItemPicDAO_interface {

	// 搜尋全部
	private static final String GET_ALL_STMT = "FROM ItemPicVO ORDER BY ItemPicNo";
	// 依照商品編號尋找
	private static final String GET_BYITEMNO_STMT = "FROM ItemPicVO WHERE itemNo=:itemNo";
	// 用商品編號來顯示圖片
	private static final String GET_BYITEMNO_FORBYTE_STMT = "FROM ItemPicVO WHERE itemNo=:itemNo";
	// 用照片編號來顯示圖片
	private static final String GET_ALL_FORBYTE_STMT = "FROM ItemPicVO WHERE ItemPicNo=:ItemPicNo";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addPic(ItemPicVO ItemPicVO) {
		hibernateTemplate.saveOrUpdate(ItemPicVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePic(ItemPicVO ItemPicVO) {
		hibernateTemplate.update(ItemPicVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePic(Integer ItemPicNo) {
		ItemPicVO itemPicVO = new ItemPicVO();
		itemPicVO.setItemPicNo(ItemPicNo);
		hibernateTemplate.delete(itemPicVO);
	}

	@Override
	public ItemPicVO findByItemPicNo(Integer ItemPicNo) {
		ItemPicVO itemPicVO = hibernateTemplate.get(ItemPicVO.class, ItemPicNo);
		return itemPicVO;
	}

	@Override
	public List<ItemPicVO> getAllPics() {
		List<ItemPicVO> list = (List<ItemPicVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public List<ItemPicVO> getByItemNo(Integer itemNo) {
		List<ItemPicVO> list = (List<ItemPicVO>) hibernateTemplate.findByNamedParam(GET_BYITEMNO_STMT,"itemNo", itemNo);
		return list;
	}

	@Override
	// 特別放在這邊，警惕你不要又跟findByItemPicNo看錯,浪費2小時
	public ItemPicVO findByItemPicNoForByte(Integer itemPicNo) {
		ItemPicVO itemPicVO = (ItemPicVO) hibernateTemplate.findByNamedParam(GET_ALL_FORBYTE_STMT, "ItemPicNo",itemPicNo);
		return itemPicVO;
	}
	
	@Override
	public ItemPicVO findByItemNoForByte(Integer itemNo) {
		ItemPicVO itemPicVO = (ItemPicVO) hibernateTemplate.findByNamedParam(GET_BYITEMNO_FORBYTE_STMT, "itemNo",itemNo).iterator().next();
		return itemPicVO;
	}

	public static void main(String[] args) throws IOException {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemPicDAO_interface dao =(ItemPicDAO_interface) context.getBean("ItemPicDAO");
		
		//getByItemNo() ok
//		List<ItemPicVO> list = dao.getByItemNo(21011);
//		System.out.println(list);
		
		//findByItemPicNoForByte
//		System.out.println(dao.findByItemNoForByte(21011));
		
		((ClassPathXmlApplicationContext) context).close();
	}
}

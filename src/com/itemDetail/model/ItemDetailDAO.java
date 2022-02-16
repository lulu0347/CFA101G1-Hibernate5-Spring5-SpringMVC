package com.itemDetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import com.itemCollection.model.ItemCollectionId;
import com.itemCollection.model.ItemCollectionVO;

import hibernate.util.HibernateUtil;

@Transactional(readOnly = true)
public class ItemDetailDAO implements ItemDetailDAO_interface {

	// 刪除明細(基本上沒再用,增加熟練度才寫的)
	private static final String DELETE = "DELETE FROM ItemDetailVO WHERE orderno =:orderno AND itemno =:itemno";
	// 依照訂單編號查詢該訂單明細所有內容
	private static final String GetAllByOrderNo = "FROM ItemDetailVO WHERE OrderNo =:orderno";
	// 查詢所有訂單明細
	private static final String GET_ALL_STMT = "FROM ItemDetailVO ORDER BY orderno";
	// 查詢單一訂單明細
	private static final String GET_ONE_STMT = "FROM ItemDetailVO WHERE orderno =:orderno AND itemno =:itemno";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addItemDetail(ItemDetailVO ItemDetailVO) {
		hibernateTemplate.saveOrUpdate(ItemDetailVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateItemDetail(ItemDetailVO itemDetailVO) {
		hibernateTemplate.update(itemDetailVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteItemDetail(Integer OrderNo, Integer ItemNo) {
		ItemDetailId itemDetailId = new ItemDetailId();
		itemDetailId.setItemNo(ItemNo);
		itemDetailId.setOrderNo(OrderNo);
		ItemDetailVO itemDetailVO = (ItemDetailVO) hibernateTemplate.get(ItemDetailVO.class, itemDetailId);
		hibernateTemplate.delete(itemDetailVO);
	}

	@Override
	public List<ItemDetailVO> GetAllByOrderNo(Integer OrderNo) {
		List<ItemDetailVO> list = (List<ItemDetailVO>) hibernateTemplate.findByNamedParam(GetAllByOrderNo, "orderno", OrderNo);
		return list;
	}

	@Override
	public List<ItemDetailVO> getAll() {
		List<ItemDetailVO> list = (List<ItemDetailVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public ItemDetailVO findByPrimaryKey(Integer OrderNo, Integer ItemNo) {
		ItemDetailId itemDetailId = new ItemDetailId();
		itemDetailId.setItemNo(ItemNo);
		itemDetailId.setOrderNo(OrderNo);
		ItemDetailVO itemDetailVO = (ItemDetailVO) hibernateTemplate.get(ItemDetailVO.class, itemDetailId);
		return itemDetailVO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateByShopping() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemDetailDAO_interface dao =(ItemDetailDAO_interface) context.getBean("ItemDetailDAO");
		
		//addItemDetail() ok
//		ItemDetailVO itemDetailVO = new ItemDetailVO();
//		ItemDetailId itemDetailId = new ItemDetailId();
//		itemDetailId.setItemNo(21004);
//		itemDetailId.setOrderNo(24004);
//		itemDetailVO.setId(itemDetailId);
//		itemDetailVO.setItemPrice(1000);
//		itemDetailVO.setItemSales(1);
//		dao.addItemDetail(itemDetailVO);
		
		//delete() ok
//		dao.deleteItemDetail(24004, 21004);
		
		//findByPrimaryKey()
//		ItemDetailVO itemDetailVO = dao.findByPrimaryKey(24004, 21004);
//		System.out.println(itemDetailVO.getItemPrice());
		
		
		((ClassPathXmlApplicationContext) context).close();
	}

}

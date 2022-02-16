package com.productKind.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class ProductKindDAO implements ProductKindDAO_interface {

	//查詢所有商品類別
	private static final String GET_ALL_STMT = "from ProductKindVO order by kindNo";
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(ProductKindVO productKindVO) {
		hibernateTemplate.saveOrUpdate(productKindVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(ProductKindVO productKindVO) {
		hibernateTemplate.update(productKindVO);
	}

	@Override
	public ProductKindVO findByPrimaryKey(Integer kindNo) {
		ProductKindVO productKindVO = hibernateTemplate.get(ProductKindVO.class, kindNo);
		return productKindVO;
	}

	@Override
	public List<ProductKindVO> getAll() {
		List<ProductKindVO> list = (List<ProductKindVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}
	
	public static void main(String[] args) {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ProductKindDAO_interface dao =(ProductKindDAO_interface) context.getBean("ProductKindDAO");
		
		((ClassPathXmlApplicationContext) context).close();
	}
}

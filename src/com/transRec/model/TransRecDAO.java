package com.transRec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO_interface;
import com.itemDetail.model.ItemDetailVO;
import com.member.model.MemberVO;

import hibernate.util.HibernateUtil;

@Transactional(readOnly = true)
public class TransRecDAO implements TransRecDAO_interface {

	// 依照會員編號尋找交易紀錄
	private final String FIND_BY_MEMNO = "FROM TransRecVO WHERE MemNo=:MemNo ORDER BY TransRecNo desc";
	// 依照商城名稱與訂單編號查詢單一交易紀錄
	private final String FIND_BY_MALL_ORD = "FROM TransRecVO WHERE MallName=:MallName AND OrderNo=:OrderNo ";
	// 搜尋所有交易紀錄
	private final String GET_ALL = "FROM TransRecVO ORDER BY TransRecNo desc";
	// 依照訂單編號搜尋交易紀錄
	private final String FIND_BY_orderNo = "FROM TransRecVO WHERE OrderNo=:OrderNo";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer add(TransRecVO transRecVO) {
		Integer temp = 0;
		hibernateTemplate.save(transRecVO);
		temp = transRecVO.getTransRecNo();
		return temp;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(TransRecVO transRecVO) {
		hibernateTemplate.update(transRecVO);
	}

	@Override
	public List<TransRecVO> findByMemNo(Integer memNo) {
		List<TransRecVO> list = (List<TransRecVO>) hibernateTemplate.find(FIND_BY_MEMNO, memNo);
		return list;
	}

	@Override
	public TransRecVO findByMall_Order(String mallName, Integer orderNo) {
		TransRecVO transRecVO = (TransRecVO) hibernateTemplate.find(FIND_BY_MALL_ORD, mallName, orderNo);
		return transRecVO;
	}

	@Override
	public List<TransRecVO> getAll() {
		List<TransRecVO> list = (List<TransRecVO>) hibernateTemplate.find(GET_ALL);
		return list;
	}

	@Override
	public Integer getDeposit(Integer memNo) {
		return null;
	}

	@Override
	public Integer addInTransaction(Connection con, TransRecVO transRecVO) throws SQLException {
		return null;
	}

	@Override
	public List<TransRecVO> findByOrderNo(Integer orderNo) {
		List<TransRecVO> list = (List<TransRecVO>) hibernateTemplate.find(FIND_BY_orderNo, orderNo);
		return list;
	}

	public static void main(String[] args) {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		TransRecDAO_interface dao =(TransRecDAO_interface) context.getBean("TransRecDAO");
		
		((ClassPathXmlApplicationContext) context).close();
	}
}

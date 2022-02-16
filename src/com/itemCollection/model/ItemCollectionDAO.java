package com.itemCollection.model;

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

import com.itemCollection.model.ItemCollectionId;
import com.itemPic.model.ItemPicVO;
import com.item.model.ItemVO;
import com.member.model.MemberVO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import hibernate.util.HibernateUtil;

@Transactional(readOnly = true)
public class ItemCollectionDAO implements ItemCollectionDAO_interface {

	// (前台)會員查詢收藏列表
	private static final String GET_ONE_STMT = "FROM ItemCollectionVO WHERE Memno =:Memno";
	// 找尋所有收藏列表及會員
	private static final String GET_ALL_STMT = "FROM ItemCollectionVO ORDER BY Memno";
	// 取得會員對某商品收藏的count (用於判斷已收藏與否)
	private static final String GET_COUNT_COLLECT = "SELECT COUNT(itemNo) AS collect FROM ItemCollectionVO where itemNo =:itemNo and Memno =:Memno";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(ItemCollectionVO ItemCollectionVO) {
		hibernateTemplate.saveOrUpdate(ItemCollectionVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(ItemCollectionVO ItemCollectionVO) {
		hibernateTemplate.update(ItemCollectionVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer ItemNo, Integer memNo) {
		ItemCollectionId itemCollectionId = new ItemCollectionId();
		itemCollectionId.setItemNo(ItemNo);
		itemCollectionId.setMemNo(memNo);
		ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
		itemCollectionVO.setId(itemCollectionId);
		hibernateTemplate.delete(itemCollectionVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ItemCollectionVO findOneByMemNo(Integer memNo) {
		
		return null;
	}

	@Override
	//僅此註記 : 學會看文件了ｽﾞｲ₍₍ (ง ˘ω˘ )ว ⁾⁾ ⁽⁽ 〪ɾ( ˘ω˘ 〫ɩ ) ₎₎ｽﾞｲ
	public List<ItemCollectionVO> findByMemNo(Integer Memno) {
		List<ItemCollectionVO> list = (List<ItemCollectionVO>) hibernateTemplate.findByNamedParam(GET_ONE_STMT, "Memno", Memno);
		return list;
	}

	@Override
	public List<ItemCollectionVO> getAll() {
		List<ItemCollectionVO> list = (List<ItemCollectionVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	//雖然都是deprecated,還是要注意不要看錯方法名字
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Long getcount(Integer itemNo, Integer Memno) {
		List<Long> list = (List<Long>) hibernateTemplate.findByNamedParam(GET_COUNT_COLLECT, new String[]{"itemNo","Memno"}, new Object[]{itemNo,Memno});
		return list.get(0).longValue();
	}
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemCollectionDAO_interface dao =(ItemCollectionDAO_interface) context.getBean("ItemCollectionDAO");
		
		//getAll() ok
//		List<ItemCollectionVO> list = dao.getAll();
//		System.out.println(list);
//		for (ItemCollectionVO iCVo : list) {
//			System.out.print(iCVo.getId().getItemNo() + ",");
//			System.out.print(iCVo.getId().getMemNo());
//			System.out.println();
//		}
		
		//getcount() ok
//		Long count = dao.getcount(21002, 11001);
//		System.out.println(count);
		
		//findByMemNo() ok
//		List<ItemCollectionVO> list = dao.findByMemNo(11001);
//		System.out.println(list);
//		for (ItemCollectionVO iCVo : list) {
//			System.out.print(iCVo.getId().getMemNo() + ",");
//			System.out.print(iCVo.getId().getItemNo());
//			System.out.println();
//		}
//		
//		((ClassPathXmlApplicationContext) context).close();
	}
}

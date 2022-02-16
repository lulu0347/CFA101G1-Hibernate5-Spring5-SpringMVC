package com.item.model;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ItemDAO implements ItemDAO_interface {

	// 查詢所有商品
	private static final String GET_ALL_STMT = "FROM ItemVO order by itemNo";
	// 查詢所有手機
	private static final String GET_ONE_STMT1 = "FROM ItemVO WHERE KindNo = 1";
	// 查詢所有電腦
	private static final String GET_ONE_STMT2 = "FROM ItemVO WHERE KindNo = 2";
	// 查詢所有相機
	private static final String GET_ONE_STMT3 = "FROM ItemVO WHERE KindNo = 3";
	// 查詢所有手錶
	private static final String GET_ONE_STMT4 = "FROM ItemVO WHERE KindNo = 4";
	// 查詢所有配件
	private static final String GET_ONE_STMT5 = "FROM ItemVO WHERE KindNo = 5";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(ItemVO ItemVO) {
		hibernateTemplate.saveOrUpdate(ItemVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertWithPics(ItemVO itemVO) {
		hibernateTemplate.saveOrUpdate(itemVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(ItemVO ItemVO) {
		hibernateTemplate.update(ItemVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer ItemNo) {
		ItemVO itemVO = new ItemVO();
		itemVO.setItemNo(ItemNo);
		hibernateTemplate.delete(itemVO);
	}

	@Override
	public ItemVO findByItemNo(Integer ItemNo) {
		ItemVO itemVO = (ItemVO) hibernateTemplate.get(ItemVO.class, ItemNo);
		return itemVO;
	}

	@Override
	public List<ItemVO> getAll() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public List<ItemVO> getOneKind1() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ONE_STMT1);
		return list;
	}

	@Override
	public List<ItemVO> getOneKind2() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ONE_STMT2);
		return list;
	}

	@Override
	public List<ItemVO> getOneKind3() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ONE_STMT3);
		return list;
	}

	@Override
	public List<ItemVO> getOneKind4() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ONE_STMT4);
		return list;
	}

	@Override
	public List<ItemVO> getOneKind5() {
		List<ItemVO> list = (List<ItemVO>) hibernateTemplate.find(GET_ONE_STMT5);
		return list;
	}

	@Override
	// 模糊搜尋 先流空
	public List<ItemVO> getAll(Map<String, String[]> map) {
		return null;
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemDAO_interface dao = (ItemDAO_interface) context.getBean("ItemDAO");
		
		//getOneKind1() ok
//		List<ItemVO> list = dao.getOneKind1();
//		for (ItemVO iVo : list) {
//			System.out.print(iVo.getItemNo() + ",");
//			System.out.print(iVo.getItemName() + ",");
//			System.out.print(iVo.getItemPrice() + ",");
//			System.out.print(iVo.getItemState() + ",");
//			System.out.print(iVo.getLaunchedTime() + ",");
//			System.out.print(iVo.getWarrantyDate() + ",");
//			System.out.print(iVo.getProductKindVO().getKindName());
//			System.out.println();
//		}
		
		// getAll() ok
//		List<ItemVO> list = dao.getAll();
//		for (ItemVO iVo : list) {
//			System.out.print(iVo.getItemNo() + ",");
//			System.out.print(iVo.getItemName() + ",");
//			System.out.print(iVo.getItemPrice() + ",");
//			System.out.print(iVo.getItemState() + ",");
//			System.out.print(iVo.getLaunchedTime() + ",");
//			System.out.print(iVo.getWarrantyDate() + ",");
//			System.out.print(iVo.getProductKindVO().getKindName());
//			System.out.println();
//		}
		((ClassPathXmlApplicationContext) context).close();

	}
}

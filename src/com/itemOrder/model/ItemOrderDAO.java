package com.itemOrder.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO_interface;
import com.itemDetail.model.ItemDetailVO;
import com.member.model.MemberVO;
import com.transRec.model.TransRecVO;

import hibernate.util.HibernateUtil;

@Transactional(readOnly = true)
public class ItemOrderDAO implements ItemOrderDAO_interface {

	// 查詢所有訂單
	private static final String GET_ALL_STMT = "from ItemOrderVO order by orderNo";
	// 取得該會員所有訂單
	private static final String GET_BYMEMID_STMT = "FROM ItemOrderVO WHERE memno =:memno";
	// (後台)用於尋找未出貨的訂單
	private static final String GET_ORDER_BYSTATE = "FROM ItemOrderVO WHERE OrderState = 0";
	// (後台)用於尋找已出貨的訂單
	private static final String GET_ORDER_BYSTATE1 = "FROM ItemOrderVO WHERE OrderState = 1";
	// (後台)用於尋找已收貨的訂單
	private static final String GET_ORDER_BYSTATE2 = "FROM ItemOrderVO WHERE OrderState = 2";
	// (後台)用於尋找被退貨的訂單
	private static final String GET_ORDER_BYSTATE3 = "FROM ItemOrderVO WHERE OrderState = 3";
	// (後台)用於尋找被取消的訂單
	private static final String GET_ORDER_BYSTATE4 = "FROM ItemOrderVO WHERE OrderState = 4";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addOrder(ItemOrderVO ItemOrderVO) {
		hibernateTemplate.saveOrUpdate(ItemOrderVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateByOrderNo(ItemOrderVO ItemOrderVO) {
		hibernateTemplate.update(ItemOrderVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer OrderNo) {
		ItemOrderVO itemOrderVO = new ItemOrderVO();
		itemOrderVO.setOrderNo(OrderNo);
		hibernateTemplate.delete(itemOrderVO);
	}

	@Override
	public List<ItemOrderVO> GetOrderByMemNo(Integer MemNo) {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.findByNamedParam(GET_BYMEMID_STMT,"memno", MemNo);
		return list;
	}

	@Override
	public ItemOrderVO findByOrderNo(Integer OrderNo) {
		ItemOrderVO itemOrderVO = (ItemOrderVO) hibernateTemplate.get(ItemOrderVO.class, OrderNo);
		return itemOrderVO;
	}

	@Override
	public List<ItemOrderVO> getAll() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public List<ItemOrderVO> findByOrderState() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ORDER_BYSTATE);
		return list;
	}

	@Override
	public List<ItemOrderVO> findByOrderState1() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ORDER_BYSTATE1);
		return list;
	}

	@Override
	public List<ItemOrderVO> findByOrderState2() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ORDER_BYSTATE2);
		return list;
	}

	@Override
	public List<ItemOrderVO> findByOrderState3() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ORDER_BYSTATE3);
		return list;
	}

	@Override
	public List<ItemOrderVO> findByOrderState4() {
		List<ItemOrderVO> list = (List<ItemOrderVO>) hibernateTemplate.find(GET_ORDER_BYSTATE4);
		return list;
	}

	@Override
	public void insertWithItemDetails(ItemOrderVO itemOrderVO, List<ItemDetailVO> list, TransRecVO transRecVO) {
	}

	public static void main(String[] args) {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemOrderDAO_interface dao =(ItemOrderDAO_interface) context.getBean("ItemOrderDAO");
		
		((ClassPathXmlApplicationContext) context).close();
	}

}

package com.itemOrder.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;
import com.itemDetail.model.ItemDetailVO;
import com.member.model.MemberVO;
import com.transRec.model.TransRecVO;

public class ItemOrderService {

	private static ItemOrderDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ItemOrderDAO_interface) context.getBean("ItemOrderDAO");
	}
	
//--------------新增訂單---------------	
	public ItemOrderVO addOrder(MemberVO memberVO, Date tranTime, Integer orderTotal, Integer orderState, String receiverName, String receiverAddress, String receiverPhone) {
		
		ItemOrderVO itemOrderVO = new ItemOrderVO();
		
		itemOrderVO.setMemberVO(memberVO);
		itemOrderVO.setTranTime(tranTime);
		itemOrderVO.setOrderTotal(orderTotal);
		itemOrderVO.setOrderState(orderState);
		itemOrderVO.setReceiverName(receiverName);
		itemOrderVO.setReceiverAddress(receiverAddress);
		itemOrderVO.setReceiverPhone(receiverPhone);
		dao.addOrder(itemOrderVO);
		return itemOrderVO;
	}

//------------------依照單一訂單編號更新某一訂單-----------------	
	public ItemOrderVO updateByOrderNo(Integer orderNo, MemberVO memberVO, Date tranTime, Integer orderTotal, Integer orderState,
			String receiverName, String receiverAddress, String receiverPhone) {
		
		ItemOrderVO itemOrderVO = new ItemOrderVO();
		
		itemOrderVO.setOrderNo(orderNo);
		itemOrderVO.setMemberVO(memberVO);
		itemOrderVO.setTranTime(tranTime);
		itemOrderVO.setOrderTotal(orderTotal);
		itemOrderVO.setOrderState(orderState);
		itemOrderVO.setReceiverName(receiverName);
		itemOrderVO.setReceiverAddress(receiverAddress);
		itemOrderVO.setReceiverPhone(receiverPhone);
		dao.updateByOrderNo(itemOrderVO);
		
		return itemOrderVO;
		
	}
	
//------------------刪除訂單--------------------------------	
	public void deleteOrder(Integer orderNo) {
		dao.delete(orderNo);
	}
	
	
//------------------依照單一訂單編號查詢某一訂單-----------------		
	public ItemOrderVO findByOrderNo(Integer orderNo) {
		
		ItemOrderVO itemOrderVO = new ItemOrderVO();

		itemOrderVO = dao.findByOrderNo(orderNo);
		
		return itemOrderVO;
	}
	
//--------------查詢單一會員所持有的訂單---------------
	public List<ItemOrderVO> getAllOrderByMemNo(Integer memNo) {
		return dao.GetOrderByMemNo(memNo);
	}

	
//--------------查詢全部訂單---------------	
	public List<ItemOrderVO> getAll(){
		return dao.getAll();
		
	}
	
//--------------同時新增訂單跟明細---------------
	public void insertWithItemDetails(ItemOrderVO itemOrderVO, List<ItemDetailVO> list, TransRecVO transRecVO) {
		dao.insertWithItemDetails(itemOrderVO, list,transRecVO);
	}
	
//--------------查詢未出貨訂單---------------	
	public List<ItemOrderVO> findByOrderState(){
		return dao.findByOrderState();
		
	}
	
//--------------查詢已出貨訂單---------------	
	public List<ItemOrderVO> findByOrderState1(){
		return dao.findByOrderState1();
			
		}
//--------------查詢已收貨訂單---------------	
	public List<ItemOrderVO> findByOrderState2(){
		return dao.findByOrderState2();
		
	}
//--------------查詢被退貨的訂單---------------	
	public List<ItemOrderVO> findByOrderState3(){
		return dao.findByOrderState3();
		
	}
//--------------查詢被取消的訂單---------------	
	public List<ItemOrderVO> findByOrderState4(){
		return dao.findByOrderState4();
		
	}

//--------------更新錢包Table的紀錄用的---------------	
	public TransRecVO getTransRecVO(ItemOrderVO itemOrderVO) {
		TransRecVO transRecVO = new TransRecVO();
		transRecVO.setOrderNo(itemOrderVO.getOrderNo());
		transRecVO.setMallName("一般商城");//商城名稱
		transRecVO.setMemberVO(itemOrderVO.getMemberVO());
		transRecVO.setTransAmount(itemOrderVO.getOrderTotal() * -1);
		transRecVO.setTransCont("");
		transRecVO.setTransRecTime(itemOrderVO.getTranTime());
		transRecVO.setTransState(1); // 0:商品退款 1:訂單扣款成功 2:儲值成功
		return transRecVO;
	}
	
//--------------會員尋找自己目前的訂單---------------	
	public List<ItemOrderVO> gerOrderByMemNo(Integer MemNo){
		return dao.GetOrderByMemNo(MemNo);
	}
	
}

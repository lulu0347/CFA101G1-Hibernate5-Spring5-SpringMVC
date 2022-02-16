package com.transRec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;
import com.member.model.MemberService;

public class TransRecService {

	private static TransRecDAO_interface dao;
	private MemberService memSvc = new MemberService();
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(TransRecDAO_interface) context.getBean("TransRecDAO");
	}
	
	/**
	 * 取得會員錢包現有金額
	 * 
	 * @param memNo
	 * @return 金額
	 */
	public Integer getDeposit(Integer memNo) {
		Integer money = null;
		return money;
	}

	/**
	 * 儲值
	 * 
	 * @param transRecVO
	 */
	public void saveMoney(TransRecVO transRecVO) {
	}

	/**
	 * 建立交易扣款(未使用連線)
	 * 
	 * @param transRecVO
	 * @return 取得交易紀錄PK
	 */
	public Integer createTransRecord(TransRecVO transRecVO) {
		return null;
	}
	
	
	/**
	 * 建立交易扣款，二手商城用，同步新增賣家取款
	 * 
	 * @param transRecVO
	 * @return 取得交易紀錄PK
	 */
	public Integer createTransRecordWithSeller(TransRecVO transRecVO, Integer memNo) {
		return null;
	}

	/**
	 * 二手商城用，非同步允許賣家取款
	 * 
	 * @param transRecVO
	 * @return 取得交易紀錄PK
	 */
	public Integer createTransRecordForSeller(TransRecVO transRecVO) {
		return null;
	}
	

	private TransRecVO createSellerTransRec(TransRecVO transRecVO, Integer memNo) {
		TransRecVO sellerTransRecVO =new TransRecVO();
		return sellerTransRecVO;
	}

	/**
	 * 在交易狀態下建立扣款建立交易扣款
	 * 
	 * @param transRecVO
	 * @return 取得交易紀錄PK
	 */
	public Integer createTransRecordInTransaction(Connection con, TransRecVO transRecVO) {
		return null;
	}

	/**
	 * 取得會員錢包紀錄
	 * 
	 * @param MemNo
	 * @return
	 */
	public List<TransRecVO> memTransRec(Integer MemNo) {
		// 以會員編號取得錢包交易紀錄
		List<TransRecVO> transRecList = dao.findByMemNo(MemNo);

		return transRecList;
	}

	private void isValid(TransRecVO transRecVO) {
	}

	private void throwException(String info, TransRecVO vo) {
	}

	private void isValidForCreateTransRecord(TransRecVO transRecVO) {
	}

	public boolean cancelTransRecord(Integer orderNo) {
		boolean result = false;

		List<TransRecVO> recordList = dao.findByOrderNo(orderNo);

		if (recordList.size() == 1) {
			TransRecVO record = recordList.get(0);
			Integer transAmount = record.getTransAmount();
			record.setTransAmount(-transAmount);
			record.setTransState(0);
			record.setMallName(record.getMallName() + "退款");
			record.setTransRecTime(new Timestamp(new Date().getTime()));
			saveMoney(record);
			result = true;
		}

		return result;
	}

}

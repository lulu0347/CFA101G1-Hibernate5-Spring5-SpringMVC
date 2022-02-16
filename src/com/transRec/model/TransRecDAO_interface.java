package com.transRec.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TransRecDAO_interface {

	Integer add(TransRecVO transRecVO);

	void update(TransRecVO transRecVO);

	List<TransRecVO> findByMemNo(Integer memNo);

	TransRecVO findByMall_Order(String mallName, Integer orderNo);

	List<TransRecVO> getAll();

	
	Integer getDeposit(Integer memNo);
	//建立錢包與訂單連線
	Integer addInTransaction(Connection con, TransRecVO transRecVO) throws SQLException;
	
	List<TransRecVO> findByOrderNo(Integer orderNo);
}

package com.auctAct.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.auctActProd.model.AuctActProdVO;
import com.auctOrder.model.AuctOrdVO;
import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.auctPic.model.AuctActPicVO;


public interface AuctActDAO_interface {
	
	Integer add(AuctActVO auctAct);
	void addAct_Prod(AuctActVO auctAct, List<AuctActProdVO> actProdList);
	void update(AuctActVO auctAct);
	AuctActVO findByAuctActNo(Integer auctActNo);
	AuctActVO findByAuctTime(java.sql.Timestamp auctStartTime,java.sql.Timestamp auctEndTime);
	void delete(Integer auctActNo);
	List<AuctActVO> getAll();

	Connection getConnection() throws SQLException;
	Integer addActCon(Connection con, AuctActVO auctAct) throws SQLException;
	void updateActCon(Connection con, AuctActVO auctAct) throws SQLException;
}

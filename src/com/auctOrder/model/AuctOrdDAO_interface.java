package com.auctOrder.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.transRec.model.TransRecVO;

public interface AuctOrdDAO_interface {
	Integer add(AuctOrdVO auctOrd);

	void addOrderWithOrderDetl(AuctOrdVO auctOrd, List<AuctOrdDetlVO> ordDetlList, TransRecVO transRecVO);

	Integer addInTransaction(Connection con, AuctOrdVO auctOrd) throws SQLException;

	void update(AuctOrdVO auctOrd);

	AuctOrdVO findByOrdNo(Integer auctOrdNo);

	List<AuctOrdVO> getAll();

	Connection getConnection() throws SQLException;

	List<AuctOrdVO> findByMemNo(Integer memNo);
}

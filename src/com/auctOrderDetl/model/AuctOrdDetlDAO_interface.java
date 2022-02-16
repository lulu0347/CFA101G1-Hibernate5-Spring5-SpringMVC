package com.auctOrderDetl.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface AuctOrdDetlDAO_interface {
	
	void add(AuctOrdDetlVO auctOrdDetl);
	void update(AuctOrdDetlVO auctOrdDetl);
	AuctOrdDetlVO findByOrdNo_ProdNo(Integer auctOrdNo,Integer auctActProdNo);
	List<AuctOrdDetlVO> getAll();
	void addInTransaction(Connection con, Integer auctOrdNo,List<AuctOrdDetlVO> auctOrdDetlList) throws SQLException;
	List<AuctOrdDetlVO> findByOrdNo(Integer auctOrdNo);
	List<AuctOrdDetlVO> findByOrdNo_2(Integer auctOrdNo);
	
}

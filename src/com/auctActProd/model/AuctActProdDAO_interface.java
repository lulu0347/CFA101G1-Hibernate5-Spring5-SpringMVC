package com.auctActProd.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface AuctActProdDAO_interface {
	void add(AuctActProdVO auctActProd);
	void update(AuctActProdVO auctActProd);
	List<AuctActProdVO> findByActNo(int auctActNo);
	List<AuctActProdVO> getAll();
	void delete(Integer auctActProdNo);
	void addActCon(Connection con, Integer auctActNo,List<AuctActProdVO> actProdList) throws SQLException;
	void updateActCon(Connection con, Integer auctActNo,List<AuctActProdVO> actProdList) throws SQLException;
	AuctActProdVO findByActNoProdNo(int auctActNo, int auctProdNo);
	AuctActProdVO findByActProdNo(int auctActProdNo);
}

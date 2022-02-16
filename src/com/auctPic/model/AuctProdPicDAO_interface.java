package com.auctPic.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface AuctProdPicDAO_interface {
	void add(AuctProdPicVO auctProdPic);
	void update(AuctProdPicVO auctProdPic);
	AuctProdPicVO findByProdNo_PicInfo(Integer auctProdPicNo,String auctProdPicInfo);
	List<AuctProdPicVO> getAll();
	void addInConnection(Connection con, Integer auctProdNo, List<AuctProdPicVO> auctProdlList) throws SQLException;
}

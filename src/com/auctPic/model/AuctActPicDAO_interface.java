package com.auctPic.model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AuctActPicDAO_interface {

	void add(AuctActPicVO auctActPic);

	void update(AuctActPicVO auctActPic);
	
	List<AuctActPicVO> findPicwithProdNo(Integer auctActNo) ; 

	AuctActPicVO findByActNo_PicInfo(Integer auctActNo, String auctActPicInfo);

	List<AuctActPicVO> getAll();

	void addActCon(Connection con, Integer auctActNo, List<AuctActPicVO> auctActPicList) throws SQLException;

	void updateActCon(Connection con, Integer auctActNo, List<AuctActPicVO> auctActPicList) throws SQLException;

	void delete(Integer auctActPicNo);
}

package com.auctProd.model;

import java.util.List;

import com.auctPic.model.AuctProdPicVO;

public interface AuctProdDAO_interface {

	Integer add(AuctProdVO auctProd);
	void update(AuctProdVO auctProd);
	AuctProdVO findByProdNo(Integer auctProdNo);
	AuctProdVO findByCreTime(java.sql.Timestamp creTime);
	List<AuctProdVO> getAll();
	void addProdwithPic(AuctProdVO auctProd,List<AuctProdPicVO> auctProdlList);
	
}

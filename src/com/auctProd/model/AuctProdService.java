package com.auctProd.model;

import java.util.List;


public class AuctProdService {

	private  AuctProdDAO_interface auctProdDAO = new AuctProdDAO_JDBC();
	
	
	public AuctProdService() {
		auctProdDAO = new AuctProdDAO_JDBC();
	}
	
	public List<AuctProdVO> getallProd() {	
		return auctProdDAO.getAll();
	}
	
	
	public AuctProdVO getOneAct(Integer auctProdNo) {
		AuctProdVO auctProdVO =auctProdDAO.findByProdNo(auctProdNo); 
		return auctProdVO;
	}
	
	
}

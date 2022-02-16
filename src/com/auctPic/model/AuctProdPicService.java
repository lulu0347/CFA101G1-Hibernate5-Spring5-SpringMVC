package com.auctPic.model;

import java.util.List;

public class AuctProdPicService {
	
	private AuctProdPicDAO_interface auctProdPicDAO = new AuctProdPicDAO_JDBC();	
	
	
	public  AuctProdPicService() {
		auctProdPicDAO =new AuctProdPicDAO_JDBC();
	}

	public List<AuctProdPicVO> getAll() {
		return auctProdPicDAO.getAll();
	}

	public AuctProdPicVO getOneProdPic(Integer auctProdNo, String auctProdPicInfo) {
		return auctProdPicDAO.findByProdNo_PicInfo(auctProdNo, auctProdPicInfo);
				
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

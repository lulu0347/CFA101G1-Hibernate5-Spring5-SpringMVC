package com.auctOrderDetl.model;

import java.util.List;
import java.util.Map;


public class AuctOrdDetlService {

	
	AuctOrdDetlDAO_interface ordDetlDAO =new AuctOrdDetlDAO_JDBC();
	
	public AuctOrdDetlService() {
		ordDetlDAO = new AuctOrdDetlDAO_JDBC();
	}
	
	//單筆訂單明細 
	public AuctOrdDetlVO getbyOrdNoProdNo(Integer auctOrdNo, Integer auctActProdNo) {
		return ordDetlDAO.findByOrdNo_ProdNo(auctOrdNo, auctActProdNo);
	}
	
	
	
	//一筆訂單當中的多個明細
	public List<AuctOrdDetlVO> getbyProdNpOrdNo(Integer auctOrdNo) {
		List<AuctOrdDetlVO> auctOrdDetlList = ordDetlDAO.findByOrdNo(auctOrdNo);
		return auctOrdDetlList;
	}
	
	
	
	
}

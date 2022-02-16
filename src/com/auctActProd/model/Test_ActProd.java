package com.auctActProd.model;

import java.util.List;

import com.auctAct.model.AuctActVO;

public class Test_ActProd {
	
	
	static AuctActProdDAO_JDBC dao = new AuctActProdDAO_JDBC();
	public static void main(String[] args) {
		
//		getVO();
//		testInsert();
//		testUpdate();
//		testfindPK();
		testgetall();
		
	}
	private static AuctActProdVO getVO() {
		AuctActProdVO auctActProdVO =new AuctActProdVO();
		auctActProdVO.setAuctActProdNo(45016);;
		auctActProdVO.setAuctActNo(43004);
		auctActProdVO.setAuctProdNo(41019);
		auctActProdVO.setAuctProdQty(20);
		auctActProdVO.setAuctProdRemain(20);
		auctActProdVO.setAuctState(0);
		auctActProdVO.setAuctProdPrice(15600);
	    
		return auctActProdVO;
		
	}
	
	
	private static void testgetall() {
		dao=new AuctActProdDAO_JDBC();
		List<AuctActProdVO> auctActProdList =dao.getAll();
		for(AuctActProdVO VO:auctActProdList ) {
			System.out.println(VO);
		}
		
	}
//	private static void testfindPK() {
//		AuctActProdVO auctActProdVO= dao.findByActProdNo(45006);
//		System.out.println(auctActProdVO);
//		
		
//	}
	private static void testUpdate() {
		AuctActProdVO auctActProdVO=getVO();
		auctActProdVO.setAuctActProdNo(45015);
		dao.update(auctActProdVO);
		
	}
	private static void testInsert() {
		AuctActProdVO auctActProdVO=getVO();
		dao.add(auctActProdVO);
		
		
	}

}

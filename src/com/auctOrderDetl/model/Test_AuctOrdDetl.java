package com.auctOrderDetl.model;

import java.util.List;

import com.auctOrder.model.AuctOrdDAO_JDBC;
import com.auctOrder.model.AuctOrdVO;

public class Test_AuctOrdDetl {
	
	static AuctOrdDetlDAO_JDBC dao = new AuctOrdDetlDAO_JDBC();
	
	public static void main(String[] args) {
		
		testInsert();
//		testUpdate();
//		testgetAll();
//		testfindPK();
		
	}

	private static void testfindPK() {
		AuctOrdDetlVO auctOrdDetlVO = dao.findByOrdNo_ProdNo(47002,45008);
		System.out.println(auctOrdDetlVO);
		
	}

	private static void testgetAll() {
		List<AuctOrdDetlVO> auctOrdDetlList = dao.getAll();
		for (AuctOrdDetlVO auctOrdDetlVO : auctOrdDetlList) {
			System.out.println(auctOrdDetlVO);
		}
		
	}

	private static void testUpdate() {
		AuctOrdDetlVO auctOrdDetlVO = new AuctOrdDetlVO();

		auctOrdDetlVO.setAuctOrdNo(47006);
		auctOrdDetlVO.setAuctActProdNo(45007);
		auctOrdDetlVO.setProdPurQty(4);
		
	}

	private static void testInsert() {
		AuctOrdDetlVO auctOrdDetlVO = new AuctOrdDetlVO();
		auctOrdDetlVO.setAuctOrdNo(47006);
		auctOrdDetlVO.setAuctActProdNo(45004);
		auctOrdDetlVO.setProdPurQty(2);
		dao.add(auctOrdDetlVO);
	}

}

package com.auctAct.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.auctActProd.model.AuctActProdVO;
import com.auctPic.model.AuctProdPicVO;

public class TestAuctAct {

	static AuctActDAO_JDBC dao = new AuctActDAO_JDBC();
	static AuctActService auctSvc = new AuctActService();

	public static void main(String[] args) throws ParseException {

		
		List<AuctActVO> auctActList = auctSvc.getAll();
		for (AuctActVO auctActVO : auctActList) {
			System.out.println(auctActVO);
			}
//
//		testInsert();
//
//		testUpdate();
		
//		testInsertProd();

//		System.out.println(auctActVO);s

	}

	private static void testInsertProd() {
		AuctActVO auctActVO = getVO();
		List<AuctActProdVO> actProdList =new ArrayList <AuctActProdVO>();
		
		AuctActProdVO auctActProdVO =new AuctActProdVO();
		auctActProdVO.setAuctProdNo(41017);
		auctActProdVO.setAuctProdQty(30);
		auctActProdVO.setAuctProdRemain(20);
		auctActProdVO.setAuctState(1);
		auctActProdVO.setAuctProdPrice(12990);
		actProdList.add(auctActProdVO);
		
		AuctActProdVO auctActProdVO1 =new AuctActProdVO();
		auctActProdVO1.setAuctProdNo(41018);
		auctActProdVO1.setAuctProdQty(20);
		auctActProdVO1.setAuctProdRemain(20);
		auctActProdVO1.setAuctState(1);
		auctActProdVO1.setAuctProdPrice(19990);
		actProdList.add(auctActProdVO1);
		
		dao.addAct_Prod(auctActVO, actProdList);
		
		
	}

	private static void testUpdate() {
		AuctActVO auctActVO = getVO();
		auctActVO.setAuctActNo(43002);
		dao.update(auctActVO);
	}

	private static void testInsert() {
		AuctActVO auctActVO = getVO();
		Integer auctNo = dao.add(auctActVO);
	}

	private static AuctActVO getVO() {

		AuctActVO auctActVO = new AuctActVO();
		auctActVO.setAuctActName("Wonder Foto");
		auctActVO.setAuctActDesc("享受旅行，紀錄生活");
		auctActVO.setAuctActState(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startDate = null;
		try {
			startDate = sdf.parse("2021/8/10 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = startDate.getTime();
		Timestamp auctStartTime = new Timestamp(time);
		auctActVO.setAuctStartTime(auctStartTime);

		Date endDate = null;
		try {
			endDate = sdf.parse("2021/9/10 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time1 = endDate.getTime();
		Timestamp auctEndTime = new Timestamp(time1);
		auctActVO.setAuctEndTime(auctEndTime);
		return auctActVO;
	}

	private static void getall() {
		dao = new AuctActDAO_JDBC();
		List<AuctActVO> auctActList = dao.getAll();
		for (AuctActVO auctActVO : auctActList) {
			System.out.println(auctActVO);
		}

	}

}

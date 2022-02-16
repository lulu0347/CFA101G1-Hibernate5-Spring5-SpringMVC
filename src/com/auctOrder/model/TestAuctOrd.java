package com.auctOrder.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.auctAct.model.AuctActDAO_JDBC;
import com.auctAct.model.AuctActVO;
import com.auctOrderDetl.model.AuctOrdDetlDAO_JDBC;
import com.auctOrderDetl.model.AuctOrdDetlDAO_interface;
import com.auctOrderDetl.model.AuctOrdDetlVO;

public class TestAuctOrd {

	static AuctOrdDAO_JDBC dao = new AuctOrdDAO_JDBC();
	static AuctOrdDetlDAO_interface ordDl = new AuctOrdDetlDAO_JDBC();

	public static void main(String[] args) {

//		testInsert();
//		testUpdate();
//		testgetAll();
//		testfindPK();
		testInsertList();

	}

	private static void testInsertList() {
		List<AuctOrdVO> ordList = dao.getAll();
		List<AuctOrdDetlVO> ordDlList = ordDl.getAll();

		AuctOrdVO auctOrdVO = getVO();
		List<AuctOrdDetlVO> list = getOrdDlList();

		dao.addOrderWithOrderDetl(auctOrdVO, list, null);

		List<AuctOrdVO> ordListAfter = dao.getAll();
		List<AuctOrdDetlVO> ordDlListAfter = ordDl.getAll();
		if ((ordList.size() + 1) == ordListAfter.size() && (ordDlList.size() + list.size()) == ordDlListAfter.size()) {
			System.out.println("資料新增成功");
		} else {
			System.out.println("資料新增有誤");
		}

	}

	private static List<AuctOrdDetlVO> getOrdDlList() {
		List<AuctOrdDetlVO> auctOrdDetlList = new ArrayList<AuctOrdDetlVO>();
		AuctOrdDetlVO auctOrdDetlVO = new AuctOrdDetlVO();
		auctOrdDetlVO.setAuctActProdNo(45009);
		auctOrdDetlVO.setProdPurQty(1);
//		auctOrdDetlList.add(auctOrdDetlVO);

		AuctOrdDetlVO auctOrdDetlVO1 = new AuctOrdDetlVO();
		auctOrdDetlVO1.setAuctActProdNo(45010);
		auctOrdDetlVO1.setProdPurQty(1);
//		auctOrdDetlList.add(auctOrdDetlVO1);

		AuctOrdDetlVO auctOrdDetlVO2 = new AuctOrdDetlVO();
		auctOrdDetlVO2.setAuctActProdNo(45013);
		auctOrdDetlVO2.setProdPurQty(1);
//		auctOrdDetlList.add(auctOrdDetlVO2);
		return auctOrdDetlList;
	}

	private static void testfindPK() {
		AuctOrdVO auctOrdVO = dao.findByOrdNo(47002);
		System.out.println(auctOrdVO);
	}

	private static void testgetAll() {

		List<AuctOrdVO> auctOrdList = dao.getAll();
		for (AuctOrdVO auctOrdVO : auctOrdList) {
			System.out.println(auctOrdVO);
		}
	}

	private static void testUpdate() {
		AuctOrdVO auctOrdVO = getVO();
		auctOrdVO.setAuctOrdNo(47006);
		dao.update(auctOrdVO);
		System.out.println(auctOrdVO);
	}

	private static void testInsert() {
		AuctOrdVO auctOrdVO = getVO();
		Integer auctOrdNo = dao.add(auctOrdVO);
	}

	private static AuctOrdVO getVO() {
		AuctOrdVO auctOrdVO = new AuctOrdVO();

		auctOrdVO.setMemNo(11001);
		auctOrdVO.setAuctOrdAmount(4690);
		auctOrdVO.setReceName("Coco");
		auctOrdVO.setReceAddr("全家就是你家");
		auctOrdVO.setRecePhone("0987654321");
		auctOrdVO.setNote("青");

		// 先設立時間的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date ordTime = new Date();
		long time = ordTime.getTime();
		Timestamp auctOrdTime = new Timestamp(time);
		auctOrdVO.setAuctOrdTime(auctOrdTime);

		Date modTime = null;
		try {
			modTime = sdf.parse("2021/6/5 13:30:30");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time1 = modTime.getTime();
		Timestamp auctModTime = new Timestamp(time1);
		auctOrdVO.setAuctOrdModTime(auctModTime);

		auctOrdVO.setAuctOrdState(1);

		return auctOrdVO;
	}

}

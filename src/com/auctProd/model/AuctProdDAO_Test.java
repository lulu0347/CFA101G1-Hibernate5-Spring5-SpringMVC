package com.auctProd.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.auctPic.model.AuctProdPicVO;

public class AuctProdDAO_Test {
	static AuctProdDAO_interface dao = new AuctProdDAO_JDBC();

	public static void main(String[] args) {

		int auctProdNo = 41001;
//		testFindByPk(auctProdNo);
//
//		String creTime = "2019/10/15 13:25:35";
//		try {
//			testFindByTime(creTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
//		testGetAll();
	
		testInsertPic();
		

	}

	private static void testInsertPic() {
		AuctProdVO auctProdVO=getVO();
		List<AuctProdPicVO> ProdPicList =new ArrayList<AuctProdPicVO> ();
		
		AuctProdPicVO prodPicVO =new AuctProdPicVO();
		prodPicVO.setAuctProdPicInfo("ProdDetlInfo");
		prodPicVO.setAuctProdPic(null);
		prodPicVO.setAuctProdPicFormat(null);
		ProdPicList.add(prodPicVO);
	
		AuctProdPicVO prodPicVO1 =new AuctProdPicVO();
		prodPicVO1.setAuctProdPicInfo("DetlPic1");
		prodPicVO1.setAuctProdPic(null);
		prodPicVO1.setAuctProdPicFormat(null);
		ProdPicList.add(prodPicVO1);
		
		AuctProdPicVO prodPicVO2 =new AuctProdPicVO();
		prodPicVO2.setAuctProdPicInfo("DetlPic1");
		prodPicVO2.setAuctProdPic(null);
		prodPicVO2.setAuctProdPicFormat(null);
		ProdPicList.add(prodPicVO2);
		
		AuctProdPicVO prodPicVO3 =new AuctProdPicVO();
		prodPicVO3.setAuctProdPicInfo("DetlPic1");
		prodPicVO3.setAuctProdPic(null);
		prodPicVO3.setAuctProdPicFormat(null);
		ProdPicList.add(prodPicVO3);
		
		AuctProdPicVO prodPicVO4 =new AuctProdPicVO();
		prodPicVO4.setAuctProdPicInfo("DetlPic1");
		prodPicVO4.setAuctProdPic(null);
		prodPicVO4.setAuctProdPicFormat(null);
		ProdPicList.add(prodPicVO4);
		
		dao.addProdwithPic(auctProdVO, ProdPicList);
		
		
		
	}

	private static AuctProdVO getVO() {
		AuctProdVO auctProdVO =new AuctProdVO();
	
		auctProdVO.setAuctProdName("IPONE");
		auctProdVO.setKindNo(74001);
		auctProdVO.setAuctProdState(1);
		auctProdVO.setAuctProdDesc("好用");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date creTime = null;
		try {
			creTime = sdf.parse("2021/6/2 17:21:30");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = creTime.getTime();
		Timestamp auctcreProd = new Timestamp(time);
		auctProdVO.setAuctProdCreTime(auctcreProd);

		Date modTime = null;
		try {
			modTime = sdf.parse("2021/6/5 13:30:30");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = modTime.getTime();
		Timestamp auctModTime = new Timestamp(time1);
		auctProdVO.setAuctProdModTime(auctModTime);
		
		
		return auctProdVO;	
		
	}

	private static void testGetAll() {
		
		 List<AuctProdVO> list = dao.getAll();
		 
		 for(AuctProdVO vo:list) {
			 System.out.println(vo.getAuctProdName());
		 }
		
	}

	private static void testFindByTime(String creTime) throws ParseException {
		//製造一個SimpleDateFormat類別來將時間格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Date類別設定一種格式，parse方法將日期變成字串
		java.util.Date utilDate = sdf.parse(creTime);
		//以utilDate.getTime方法，將時間轉為LONG
		long timeInLong = utilDate.getTime();
	    //timestamp的類別將LONG
		java.sql.Timestamp time = new Timestamp(timeInLong);
		//VO
		AuctProdVO vo = dao.findByCreTime(time);
		valdateData(vo);
	}

	private static void testFindByPk(int auctProdNo) {

		AuctProdVO vo = dao.findByProdNo(auctProdNo);

		valdateData(vo);
	}

	private static void valdateData(AuctProdVO vo) {
		if (vo == null || vo.getAuctProdName() == null || vo.getAuctProdName().isEmpty()) {
			System.out.println("沒撈到資料");
		} else {
			System.out.println(vo.getAuctProdName().equals("iPhoneSE"));
		}
	}
}

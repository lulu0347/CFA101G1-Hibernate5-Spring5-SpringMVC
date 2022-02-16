package com.auctPic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;

public class TestPic {

	static AuctActPicDAO_JDBC actDao = new AuctActPicDAO_JDBC();
	static AuctProdPicDAO_JDBC prodDao = new AuctProdPicDAO_JDBC();

	public static void main(String[] args) throws IOException {

//		insertProd();
//		insertAct();
//		updateProdWithPic();
		updateProdPic();
//		updateAct();
//		getallProd();
//		getallAct();
//		findProdPK();
//		findActPK();

	}

	private static void findActPK() {
		AuctActPicVO auctActPicVO = actDao.findByActNo_PicInfo(43002, "Fun in Home");
		System.out.println(auctActPicVO);
	}

	private static void findProdPK() {
		AuctProdPicVO auctActPicVO = prodDao.findByProdNo_PicInfo(41001, "DetlPic1");
		System.out.println(auctActPicVO);

	}

	private static void getallAct() {
		List<AuctActPicVO> list = actDao.getAll();
		System.out.println(list);
	}

	private static void getallProd() {
		List<AuctProdPicVO> list = prodDao.getAll();
		System.out.println(list);

	}

	private static void insertAct() throws FileNotFoundException {
		AuctActPicVO auctActPicVO = new AuctActPicVO();

		auctActPicVO.setAuctActNo(43005);
		auctActPicVO.setAuctActPicInfo("phone");
		auctActPicVO.setAuctActPic(null);
		auctActPicVO.setAuctActPicFormat("jpg");
		actDao.add(auctActPicVO);
	}

	private static void updateAct() throws IOException {
		//找到圖片要輸入的位置
		String datapath = "./WebContent/resource/actPic/%s.jpg";
		//準備一個空的LIST物件，將圖片所有的物件放入，以DAO 拿取全部已有的圖片資訊
		List<AuctActPicVO> list =actDao.getAll();
		//將已有的項目全取出物件
		for(AuctActPicVO vo : list) {
		//設立圖片的檔案位置，因圖片名稱與圖片編號名稱一樣，所以放入時的圖片名稱等同於拍賣活動照片的編號時，就把照片讀取放入欄位
			File picfile =new File (String.format(datapath, vo.getAuctActPicNo()));
		//讀取照片的檔案位置
			FileInputStream fis = new FileInputStream(picfile);
		//照片大小位元，透過FileInputStream 或 BufferStream 裡的 available 方法可以估算可用的檔案大小
			byte[] pic = new byte[fis.available()];
		//開始讀取圖片檔案
			fis.read(pic);
		//將圖片物件重新塞入圖片及圖片格式
			vo.setAuctActPic(pic);
			vo.setAuctActPicFormat("image/jpeg");
		//以更新的方式讓活動圖片重新置入
			actDao.update(vo);
		//關閉檔案水管
			fis.close();
		}
		
		
	}

	private static void insertProd() {
		AuctProdPicVO auctProdPicVO = new AuctProdPicVO();

		auctProdPicVO.setAuctProdNo(41002);
		auctProdPicVO.setAuctProdPicInfo("XXXXX");
		auctProdPicVO.setAuctProdPic(null);
		auctProdPicVO.setAuctProdPicFormat("jpg");
		prodDao.add(auctProdPicVO);

	}

	private static void updateProdWithPic() throws IOException {

		List<AuctProdPicVO> list = prodDao.getAll();
		for (AuctProdPicVO vo : list) {
			File f = new File("./WebContent/resources/auctProdPic/" + vo.getAuctProdPicNo() + ".jpg");
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				byte[] data = new byte[fis.available()];
				fis.read(data);
				fis.close();
				vo.setAuctProdPic(data);
				vo.setAuctProdPicFormat("images/jpeg");
				prodDao.update(vo);
			} else {
				System.out.println(vo.getAuctProdPicNo() + "is not exist");
			}
		}
	}
	
	
	private static void updateProdPic() throws IOException {
		
		String datapath = "./WebContent/resource/auctProdPic/%s.jpg";
		List<AuctProdPicVO> list = prodDao.getAll();
		for (AuctProdPicVO vo : list) {
			File f = new File(String.format(datapath, vo.getAuctProdPicNo()));
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				byte[] data = new byte[fis.available()];
				fis.read(data);
				fis.close();
				vo.setAuctProdPic(data);
				vo.setAuctProdPicFormat("images/jpeg");
				prodDao.update(vo);
			} else {
				System.out.println(vo.getAuctProdPicNo() + "is not exist");
			}
		}
	}
	
	
	
	
	// 活動資訊
//	String auctActPicInfo = req.getParameter("auctActPicinfo");
//	String actPicInfoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//	if (auctActPicInfo == null || auctActPicInfo.trim().length() == 0) {
//		errorMsgs.add("活動照片資訊: 請勿空白");
//	} else if (!auctActPicInfo.trim().matches(actPicInfoReg)) { // 以下練習正則(規)表示式(regular-expression)
//		errorMsgs.add("活動照片資訊: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
//	}
//
//	// 上傳圖片
//	Part part = req.getPart("addimage");
//	String filename = getAuctActPic(part);
//	if (part != null && part.getContentType() != null) {
//		String name = part.getName();
//		String ContentType = part.getContentType();
//		long size = part.getSize();
//		errorMsgs.add("請選擇活動圖片");
//	}
//	if (!errorMsgs.isEmpty()) {
//		RequestDispatcher failureView = req
//				.getRequestDispatcher("/backend/auctAct/addActPic.jsp");
//		failureView.forward(req, res);
//		return;
//	}
	
	
}

package com.auctActProd.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.auctAct.model.AuctActService;
import com.auctAct.model.AuctActVO;
import com.auctActProd.model.AuctActProdService;
import com.auctActProd.model.AuctActProdVO;
import com.auctPic.model.ActPicService;
import com.auctPic.model.AuctActPicVO;
import com.auctProd.model.AuctProdService;
import com.auctProd.model.AuctProdVO;

public class AuctActProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action"); // form的執行行為 name=action 點擊[ 成功新增活動商品 ]按鈕
		// 新增商品

		if ("addProd".equals(action)) {
			// 活動商品的錯誤資訊
			Map<String, String> adderrorMsgs = new HashMap<>();
			req.setAttribute("adderrorMsgs", adderrorMsgs);

			// *************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

//			try {

			// 建立一個空的拍賣商品MAP
			Map<String, AuctActProdVO> auctActProdMap = new HashMap<String, AuctActProdVO>();

			// 商品清單列表，以商品編號作為參數引入
			String[] auctProdArray = req.getParameterValues("checkitem");
			if (auctProdArray == null || auctProdArray.length == 0) {
				adderrorMsgs.put("auctProdNoArray", "請至少選擇一件商品");
			}
			if (!adderrorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctActProd/addActProd.jsp");
				failView.forward(req, res);
				return;
			}

			// 取得的商品字串
			String prodQty = "%sNum";
			String prodPrice = "%sPrice";
			for (String prodNo : auctProdArray) {

				// 取得拍賣商品編號
				String Qty = new String(String.format(prodQty, prodNo));
				String Price = new String(String.format(prodPrice, prodNo));
				// 取得商品數量
				String QtyResult = req.getParameter(Qty);
				if (QtyResult == null || (QtyResult.trim()).length() == 0) {
					adderrorMsgs.put("auctActProdQty", "請輸入商品數量");
				}
				Integer auctProdQty = null;
				try {
					auctProdQty = new Integer(QtyResult);
					if (auctProdQty <= 0) {
						adderrorMsgs.put("auctActProdQty", "商品數量不得為零或負數");
					}
				} catch (Exception e) {
					adderrorMsgs.put("auctActProdQty", "商品數量格式錯誤");
				}

				// 取得商品價格
				String priceResult = req.getParameter(Price);
				if (priceResult == null || (priceResult.trim()).length() == 0) {
					adderrorMsgs.put("auctActProdPrice", "請輸入商品價格");
				}
				Integer auctProdPrice = null;
				try {
					auctProdPrice = new Integer(priceResult);
					if (auctProdPrice <= 0) {
						adderrorMsgs.put("auctActProdPrice", "商品價格不得為零或負數");
					}
				} catch (Exception e) {
					adderrorMsgs.put("auctActProdPrice", "商品價格格式錯誤");
				}

				// 建立一個拍賣商品的VO
				AuctActProdVO auctActProdVO = new AuctActProdVO();
				// 以vo放入拍賣商品的欄位
				auctActProdVO.setAuctProdNo(Integer.parseInt(prodNo));// 將字串改數字
				auctActProdVO.setAuctProdQty(auctProdQty);
				auctActProdVO.setAuctProdRemain(auctProdQty);// 新增時數量與輸入數量一致
				auctActProdVO.setAuctProdPrice(auctProdPrice);
				auctActProdVO.setAuctState(1);
				// 把拍賣商品物件跟從新增畫面的編號放入MAP物件
				auctActProdMap.put(prodNo, auctActProdVO);
			}

			// 放入未輸入完全的物件
			req.setAttribute("auctActProdMap", auctActProdMap);

			if (!adderrorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctActProd/addActProd.jsp");
				failView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 **************************************/
			// 導向新增完後的拍賣商品清單 資料庫已經輸入完成

			// 建立一個存放取得req的SESSION
			HttpSession session = req.getSession();
			// 取得從新增活動頁面的活動vo
			AuctActVO auctActVO = (AuctActVO) session.getAttribute("auctActVOForInsert");
			// 取得新增活動頁面中的照片map物件
			Map<String, AuctActPicVO> photos = (Map<String, AuctActPicVO>) session.getAttribute("photosForInsert");
			// 建立一個商品的SERVICE，取得三者的連線(活動&活動照片 活動&活動商品)
			AuctActService auctActSvc = new AuctActService();
			// 連線原本是LIST物件，要將傳入的MAP物件轉變成LIST物件
			auctActVO = auctActSvc.addActwithPicwithProd(auctActVO, new ArrayList<AuctActPicVO>(photos.values()),
					new ArrayList<AuctActProdVO>(auctActProdMap.values()));

			// 先移除從新增活動與新增照片來的SESSION物件
			session.removeAttribute("photosForInsert");
			session.removeAttribute("auctActVOForInsert");

			// 重新填裝這個新增活動商品頁面的REQ物件
			List<AuctActProdVO> auctActProdList = new ArrayList<AuctActProdVO>(auctActProdMap.values());
			// 從資料庫拿出新的物件
			AuctActProdService auctActProdSvc = new AuctActProdService();
			Integer auctActNo = auctActVO.getAuctActNo();
			auctActProdList = auctActProdSvc.getByActNo(auctActNo);

			req.setAttribute("auctActVO", auctActVO);
			req.setAttribute("auctActProdList", auctActProdList);
			// 移轉頁面
			String addurl = "/backend/auct/auctAct/listOneAct.jsp";
			// 放入已完成的物件

			RequestDispatcher sussessView = req.getRequestDispatcher(addurl);

			sussessView.forward(req, res);

			/*************************** 3.錯誤訊息 **************************************/
//			} catch (Exception e) {
//				adderrorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/auctActProd/listAllProd.jsp");
//				failView.forward(req, res);
//			}
		}

		// 取得單一商品詳情 addActProd listOne請求
		if ("getOneProd".equals(action)) {

			// 取得商品編號
			String auctProdNoStr = req.getParameter("auctProdNo");
			System.out.println("auctProdNoStr: " + auctProdNoStr);
			Integer auctProdNo = null;
			auctProdNo = new Integer(auctProdNoStr);

			String auctActNoStr = req.getParameter("auctActNo");
			Integer auctActNo = null;
			auctActNo = new Integer(auctActNoStr);

			/*************************** 2.開始查詢資料 *************************************/
			// 查詢商品
			AuctProdService auctProdSvc = new AuctProdService();
			AuctProdVO auctProdVO = auctProdSvc.getOneAct(auctProdNo);

			AuctActProdService auctActProdSvc = new AuctActProdService();
			AuctActProdVO auctActProdVO = auctActProdSvc.getByActNoProdNo(auctActNo, auctProdNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) **********/
			req.setAttribute("auctProdVO", auctProdVO);
			req.setAttribute("auctActProdVO", auctActProdVO);

			String getProdurl = "/backend/auct/auctActProd/getOneAuctProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(getProdurl);
			successView.forward(req, res);

		}

		if ("displayOneProd".equals(action)) {

			// 取得商品編號
			String auctProdNoStr = req.getParameter("auctProdNo");
			System.out.println("auctProdNoStr: " + auctProdNoStr);
			Integer auctProdNo = null;
			auctProdNo = new Integer(auctProdNoStr);

			String auctActNoStr = req.getParameter("auctActNo");
			Integer auctActNo = null;
			auctActNo = new Integer(auctActNoStr);

			/*************************** 2.開始查詢資料 *************************************/
			// 活動
			AuctActService auctActSvc = new AuctActService();
			AuctActVO auctActVO = auctActSvc.getOneAct(auctActNo);
			// 查詢商品
			AuctProdService auctProdSvc = new AuctProdService();
			AuctProdVO auctProdVO = auctProdSvc.getOneAct(auctProdNo);

			AuctActProdService auctActProdSvc = new AuctActProdService();
			AuctActProdVO auctActProdVO = auctActProdSvc.getByActNoProdNo(auctActNo, auctProdNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) **********/
			req.setAttribute("auctProdVO", auctProdVO);
			req.setAttribute("auctActProdVO", auctActProdVO);
			req.setAttribute("auctActVO", auctActVO);

			String getProdurl = "/frontend/auctAct/auctActDisplayOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(getProdurl);
			successView.forward(req, res);

		}

	}
}

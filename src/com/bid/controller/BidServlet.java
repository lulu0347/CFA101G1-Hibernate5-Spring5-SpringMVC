package com.bid.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/bid/BidServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bidProdNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// 測試查詢圖片
				BidPicService bidPicSvc = new BidPicService();
				List<BidPicVO> bidPicVO = bidPicSvc.getAllBidPic_bidProdNo(bidProdNo);
//				if (bidPicVO == null) {
//					errorMsgs.add("bidPicVO = null");
//				}
				
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bidVO", bidVO); // 資料庫取出的bidVO物件,存入req
				
				// 測試存入圖片
				req.setAttribute("bidPicVO", bidPicVO); // 資料庫取出的bidVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getAll_For_Display_KindNo".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bidProdNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// 測試查詢圖片
				BidPicService bidPicSvc = new BidPicService();
				List<BidPicVO> bidPicVO = bidPicSvc.getAllBidPic_bidProdNo(bidProdNo);
//				if (bidPicVO == null) {
//					errorMsgs.add("bidPicVO = null");
//				}
				
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bidVO", bidVO); // 資料庫取出的bidVO物件,存入req
				
				// 測試存入圖片
				req.setAttribute("bidPicVO", bidPicVO); // 資料庫取出的bidVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_DisplayFront".equals(action)) { // 測試前台使用
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bidProdNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/bid/listAllBid.html");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// 測試查詢圖片
				BidPicService bidPicSvc = new BidPicService();
				List<BidPicVO> bidPicVO = bidPicSvc.getAllBidPic_bidProdNo(bidProdNo);
//				if (bidPicVO == null) {
//					errorMsgs.add("bidPicVO = null");
//				}
				
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bidVO", bidVO); // 資料庫取出的bidVO物件,存入req
				
				// 測試存入圖片
				req.setAttribute("bidPicVO", bidPicVO); // 資料庫取出的bidVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display_transRecNo".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("transRecNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入錢包交易編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer transRecNo = null;
				try {
					transRecNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("錢包交易編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid_transRecNo(transRecNo);
				if (bidVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bidVO", bidVO); // 資料庫取出的bidVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBid.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo"));
				
				/***************************2.開始查詢資料****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("bidVO", bidVO);         // 資料庫取出的bidVO物件,存入req
				String url = "/backend/bid/update_bid_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_bid_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_bid_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				
				String bidProdName = req.getParameter("bidProdName");
				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (bidProdName == null || bidProdName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
	            }
				
Integer kindNo = new Integer(req.getParameter("kindNo").trim());
// 需要從資料庫撈出，才可省略錯誤處理。
				System.out.println(kindNo);
				String bidProdDescription = req.getParameter("bidProdDescription").trim();
				System.out.println(bidProdDescription);
				Integer bidProdStartPrice = null;
				try {
					bidProdStartPrice = new Integer(req.getParameter("bidProdStartPrice").trim());
				} catch (NumberFormatException e) {
					bidProdStartPrice = 0;
					errorMsgs.add("起標價應為數字");
				}
				if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
					errorMsgs.add("起標價應在0 - 99000之間");
				}
				System.out.println(bidProdStartPrice);
				Integer bidState = null;
				try {
					bidState = new Integer(req.getParameter("bidState").trim());
				} catch (NumberFormatException e) {
					bidState = 0;
					errorMsgs.add("競標狀態格式錯誤(0:未結束 1:截標 2:流標)");
				}
				if (bidState < 0 || bidState > 2) {
					errorMsgs.add("競標狀態輸入錯誤(0:未結束 1:截標 2:流標)");
				}
				System.out.println(bidState);
				Integer bidPriceIncrement = null;
				try {
					bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
				} catch (NumberFormatException e) {
					bidPriceIncrement = 0;
					errorMsgs.add("最低增額應為數字");
				}
				if (bidPriceIncrement <= 0) {
					errorMsgs.add("最低增額應大於0");
				}
				System.out.println(bidPriceIncrement);
				
				java.sql.Timestamp bidProdStartTime = null;
				try {
					bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入起標時間!");
				}
				System.out.println(bidProdStartTime);
//				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("起標時間早於目前時間，請重新輸入！");
//				}
				
				java.sql.Timestamp bidProdEndTime = null;
				try {
					bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入截標時間!");
				}
				if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
					errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
				}
				System.out.println(bidProdEndTime);
				
				System.out.println(req.getParameter("bidProdState"));
				Integer bidProdState = null;
				try {
					bidProdState = new Integer(req.getParameter("bidProdState").trim());
				} catch (NumberFormatException e) {
					bidProdState = 0;
					errorMsgs.add("競標商品狀態應為數字(0:未結帳 1:訂單處理中 2:已出貨 3:已收貨 4:退貨 5:棄標)");
				}
				if (bidProdState < 0 || bidProdState > 5) {
					bidProdState = 0;
					errorMsgs.add("競標商品狀態輸入錯誤(0:未結帳 1:訂單處理中 2:已出貨 3:已收貨 4:退貨 5:棄標)");
				}
				System.out.println(bidProdState);

				String receiverName = null;
				String receiverAddress = null;
				String receiverPhone = null;
				try {
					receiverName = new String(req.getParameter("receiverName").trim());
				} catch (IllegalArgumentException e) {
					receiverName = "";
				}
				try {
					receiverAddress = new String(req.getParameter("receiverAddress").trim());
				} catch (IllegalArgumentException e) {
					receiverAddress = "";
				}
				try {
					receiverPhone = new String(req.getParameter("receiverPhone").trim());
				} catch (IllegalArgumentException e) {
					receiverPhone = "";
				}

				BidVO bidVO = new BidVO();
				bidVO.setBidProdNo(bidProdNo);
				bidVO.setBidProdName(bidProdName);
				bidVO.setKindNo(kindNo);
				bidVO.setBidProdDescription(bidProdDescription);
				bidVO.setBidProdStartPrice(bidProdStartPrice);
				bidVO.setBidState(bidState);
				bidVO.setBidPriceIncrement(bidPriceIncrement);
				bidVO.setBidProdStartTime(bidProdStartTime);
				bidVO.setBidProdEndTime(bidProdEndTime);
				bidVO.setBidProdState(bidProdState);
				bidVO.setReceiverName(receiverName);
				bidVO.setReceiverAddress(receiverAddress);
				bidVO.setReceiverPhone(receiverPhone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bidVO", bidVO); // 含有輸入格式錯誤的bidVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BidService bidSvc = new BidService();
				bidSvc.updateBid7(bidVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bidVO", bidVO); // 資料庫update成功後,正確的的bidVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneBid.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addBid.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				String bidProdName = req.getParameter("bidProdName");
				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
				if (bidProdName == null || bidProdName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
	            }
				
//Integer kindNo = new Integer(2);
				Integer kindNo = null;
				try {
					kindNo = new Integer(req.getParameter("kindNo").trim());
					if (kindNo < 1 || kindNo > 5) {
						errorMsgs.add("商品類別無效，請重新選擇");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("未選擇商品類別");
				}


// 需要從資料庫撈出，才可省略錯誤處理。
				
				String bidProdDescription = null;
				try {
					bidProdDescription = req.getParameter("bidProdDescription").trim();
				} catch (IllegalArgumentException e) {
					bidProdDescription = "";
				}
				
				Integer bidProdStartPrice = null;
				try {
					bidProdStartPrice = new Integer(req.getParameter("bidProdStartPrice").trim());
				} catch (NumberFormatException e) {
					bidProdStartPrice = 0;
					errorMsgs.add("起標價應為數字");
				}
				if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
					errorMsgs.add("起標價應在0 - 99000之間");
				}
				
				Integer bidState = new Integer(0);
				
				Integer bidPriceIncrement = null;
				try {
					bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
				} catch (NumberFormatException e) {
					bidPriceIncrement = 0;
					errorMsgs.add("最低增額應為數字");
				}
				if (bidPriceIncrement <= 0) {
					errorMsgs.add("最低增額應大於0");
				}
				
				java.sql.Timestamp bidProdStartTime = null;
				try {
					bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
					errorMsgs.add("請輸入起標時間!");
				}
				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
					errorMsgs.add("起標時間早於目前時間，請重新輸入！");
				}
				
				java.sql.Timestamp bidProdEndTime = null;
				try {
					bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
					errorMsgs.add("請輸入截標時間!");
				}
				if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
					errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
				}
				
Integer bidProdState = new Integer(0);


				BidVO bidVO = new BidVO();

				bidVO.setBidProdName(bidProdName);
				bidVO.setKindNo(kindNo);
				bidVO.setBidProdDescription(bidProdDescription);
				bidVO.setBidProdStartPrice(bidProdStartPrice);
				bidVO.setBidState(bidState);
				bidVO.setBidPriceIncrement(bidPriceIncrement);
				bidVO.setBidProdStartTime(bidProdStartTime);
				bidVO.setBidProdEndTime(bidProdEndTime);
				bidVO.setBidProdState(bidProdState);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bidVO", bidVO); // 含有輸入格式錯誤的bidVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/addBid.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BidService bidSvc = new BidService();
				bidVO = bidSvc.addBid(kindNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidPriceIncrement, bidProdState);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/addBid.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("insert_with_pics".equals(action)) { // 來自addBid.jsp的請求  
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	// Store this set in the request scope, in case we need to
        	// send the ErrorPage view.
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
        		BidVO bidVO = new BidVO();
        		String bidProdName = req.getParameter("bidProdName");
        		String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
        		if (bidProdName == null || bidProdName.trim().length() == 0) {
        			errorMsgs.add("商品名稱: 請勿空白");
        		} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
        			errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
        		}
        		
        		Integer kindNo = null;
        		try {
        			kindNo = new Integer(req.getParameter("kindNo").trim());
        			if (kindNo < 1 || kindNo > 5) {
        				errorMsgs.add("商品類別無效，請重新選擇");
        			}
        		} catch (NumberFormatException e) {
        			e.getMessage();
        			errorMsgs.add("未選擇商品類別");
        		}
        		
        		
        		String bidProdDescription = null;
        		try {
        			bidProdDescription = req.getParameter("bidProdDescription").trim();
        		} catch (IllegalArgumentException e) {
        			bidProdDescription = "";
        		}
        		
        		Integer bidProdStartPrice = null;
        		try {
        			bidProdStartPrice = new Integer(req.getParameter("bidProdStartPrice").trim());
        		} catch (NumberFormatException e) {
        			bidProdStartPrice = 0;
        			errorMsgs.add("起標價應為數字");
        		}
        		if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
        			errorMsgs.add("起標價應在0 - 99000之間");
        		}
        		
        		Integer bidState = new Integer(0);
        		
        		Integer bidPriceIncrement = null;
        		try {
        			bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
        		} catch (NumberFormatException e) {
        			bidPriceIncrement = 0;
        			errorMsgs.add("最低增額應為數字");
        		}
        		if (bidPriceIncrement <= 0) {
        			errorMsgs.add("最低增額應大於0");
        		}
        		
        		java.sql.Timestamp bidProdStartTime = null;
        		try {
        			bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
        		} catch (IllegalArgumentException e) {
        			bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
        			errorMsgs.add("請輸入起標時間!");
        		}
        		if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
        			bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
        			errorMsgs.add("起標時間早於目前時間，請重新輸入！");
        		}
        		
        		java.sql.Timestamp bidProdEndTime = null;
        		try {
        			bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
        		} catch (IllegalArgumentException e) {
        			bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
        			errorMsgs.add("請輸入截標時間!");
        		}
        		if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
        			bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
        			errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
        		}
        		
        		Integer bidProdState = new Integer(0);
        		
        		bidVO.setBidProdName(bidProdName);
        		bidVO.setKindNo(kindNo);
        		bidVO.setBidProdDescription(bidProdDescription);
        		bidVO.setBidProdStartPrice(bidProdStartPrice);
        		bidVO.setBidState(bidState);
        		bidVO.setBidPriceIncrement(bidPriceIncrement);
        		bidVO.setBidProdStartTime(bidProdStartTime);
        		bidVO.setBidProdEndTime(bidProdEndTime);
        		bidVO.setBidProdState(bidProdState);
        		
//        		測試同時上傳圖片 2221
//        		BidPicService bidPicSvc = new BidPicService();
				
//				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
//				System.out.println(bidProdNo);
        		BidService bidSvc = new BidService();
				List<Part> list = (List<Part>) req.getParts();
				List<byte[]> listPic = new ArrayList<byte[]>();
				InputStream is = null;
				BufferedInputStream bis = null;
				byte[] bidProdPicContent = null;
				
//				Context ctx = new javax.naming.InitialContext();
//				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
//				Connection conn = ds.getConnection();
				for (Part part : list) {
					is = part.getInputStream();
					bis = new BufferedInputStream(is);
					if (is.available() > 1024) {
						bidProdPicContent = new byte[bis.available()];
						bis.read(bidProdPicContent);
						listPic.add(bidProdPicContent);
					}

				}
				
				
//				END HERE
        		
        		
        		// Send the use back to the form, if there were errors
        		if (!errorMsgs.isEmpty()) {
        			req.setAttribute("bidVO", bidVO); // 含有輸入格式錯誤的bidVO物件,也存入req
        			RequestDispatcher failureView = req
        					.getRequestDispatcher("/backend/bid/addBid.jsp");
        			failureView.forward(req, res);
        			return;
        		}
        		
        		bidSvc.addBid2(kindNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidPriceIncrement, bidProdState, listPic);
        		
        		/***************************2.開始新增資料***************************************/

//        		好像用不到
//        		BidService bidSvc = new BidService();
//        		bidVO = bidSvc.addBid(kindNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidPriceIncrement, bidProdState);
        		
        		/***************************3.新增完成,準備轉交(Send the Success view)***********/
        		String url = "/backend/bid/listAllBid.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
        		successView.forward(req, res);				
        		
        		/***************************其他可能的錯誤處理**********************************/
        	} catch (Exception e) {
        		e.printStackTrace();
        		errorMsgs.add(e.getMessage());
        		RequestDispatcher failureView = req
        				.getRequestDispatcher("/backend/bid/addBid.jsp");
        		failureView.forward(req, res);
        	}
        }
        
		
		if ("delete".equals(action)) { // 來自listAllBid.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo"));
				
				/***************************2.開始刪除資料***************************************/
				BidService bidSvc = new BidService();
				bidSvc.deleteBid(bidProdNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

package com.bidpic.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.bidpic.model.*;

/**
 * Servlet implementation class BidPicServlet
 */
@WebServlet("/bidpic/BidPicServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded";
	Connection con;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidPicServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
							.getRequestDispatcher("/backend/bidpic/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bidProdPicNo = null;
				try {
					bidProdPicNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = bidPicSvc.getOneBidPic(bidProdPicNo);
				if (bidPicVO == null) {
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
				req.setAttribute("bidPicVO", bidPicVO); // 資料庫取出的bidPicVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBidPic.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidpicservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBidPic.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				
				/***************************2.開始查詢資料****************************************/
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = bidPicSvc.getOneBidPic(bidProdPicNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("bidPicVO", bidPicVO);         // 資料庫取出的bidPicVO物件,存入req
				String url = "/bidpic/update_bidpic_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_bidpic_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/listAllBidPic.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("update".equals(action)) { // 來自update_bidpic_input.jsp的請求
		
		if ("insert".equals(action)) { // 來自addBidPic.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				
				Part part = req.getPart("upfile1");
//				String dir = "/images_uploaded";
//				String filename = getFileNameFromPart(part);
				byte[] bidProdPicContent = null;
				InputStream is = null;
				BufferedInputStream bis = null;
//				part.write(getServletContext().getRealPath(dir) + "/" + filename);
				
//				String realPath = getServletContext().getRealPath(dir)+ "/" + filename;

					// 單個檔案上傳
				try {
					is = part.getInputStream();
					bis = new BufferedInputStream(is);
					bidProdPicContent = new byte[bis.available()];
					bis.read(bidProdPicContent);

				} catch (IOException ie) {
					ie.printStackTrace();
				} finally {
					if (bis != null) {
						bis.close();
					}
					if (is != null) {
						is.close();
					}
				}
				
				BidPicVO bidPicVO = new BidPicVO();
				bidPicVO.setBidProdNo(bidProdNo);
				bidPicVO.setBidProdPicContent(bidProdPicContent);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("bidPicVO", bidPicVO); // 含有輸入格式錯誤的bidPicVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BidPicService bidPicSvc = new BidPicService();
				
				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/bidpic/listAllBidPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
				failureView.forward(req, res);
			}
			
		}
		
//		// 測試上傳多張圖片
		if ("insert_multi".equals(action)) { // 來自addBidPic.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = new BidPicVO();
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				System.out.println(bidProdNo);
				List<Part> list = (List<Part>) req.getParts();
				InputStream is = null;
				BufferedInputStream bis = null;
				byte[] bidProdPicContent = null;
				
				list.remove(0);
				list.remove((list.size()-1));
				System.out.println(list.size());
				for (Part part : list) {
					is = part.getInputStream();
					bis = new BufferedInputStream(is);
					bidProdPicContent = new byte[bis.available()];
					bis.read(bidProdPicContent);
					bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
					
				}
				
				
				bidPicVO.setBidProdNo(bidProdNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("bidPicVO", bidPicVO); // 含有輸入格式錯誤的bidPicVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				
//				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
				failureView.forward(req, res);
			}
			
		}
		if ("insert_multi_bid".equals(action)) { // 來自update_bid_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = new BidPicVO();
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				System.out.println(bidProdNo);
				List<Part> list = (List<Part>) req.getParts();
				InputStream is = null;
				BufferedInputStream bis = null;
				byte[] bidProdPicContent = null;
				
//				list.remove(0);
//				for (int i = list.size() - 1; i >= 1; i--) {
//					list.remove(i);
//				}
				System.out.println(list.size());
				for (Part part : list) {
					is = part.getInputStream();
					bis = new BufferedInputStream(is);
					if (is.available() > 1024) {
						bidProdPicContent = new byte[bis.available()];
						bis.read(bidProdPicContent);
						bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
					}
					
				}
				
				
				bidPicVO.setBidProdNo(bidProdNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("bidPicVO", bidPicVO); // 含有輸入格式錯誤的bidPicVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				
//				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("delete".equals(action)) { // 來自update_bid_input.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
//				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				// 測試checkbox
				BidPicService bidPicSvc = new BidPicService();
				String[] bidProdPicNos = req.getParameterValues("bidProdPicNos");
				if (bidProdPicNos != null) {
					for (String bidProdPicNo : bidProdPicNos) {
						bidPicSvc.deleteBidPic(new Integer(bidProdPicNo));
					}
				}
				
				// 測試轉交畫面
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(req.getParameter("bidProdNo"));
				} catch (NumberFormatException ne) {
					ne.getStackTrace();
				}
				System.out.println("商品編號"+bidProdNo);
				
				String bidProdName = req.getParameter("bidProdName");
				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
				if (bidProdName == null || bidProdName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
	            }
				
				Integer kindNo = new Integer(req.getParameter("kindNo").trim());
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
//				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("起標時間早於目前時間，請重新輸入！");
//				}
				
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
				
				Integer bidProdState = null;
				try {
					bidProdState = new Integer(req.getParameter("bidProdState").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("競標商品狀態錯誤");
				}
				if (bidProdState < 0 || bidProdState > 4) {
					errorMsgs.add("競標商品狀態錯誤(0:未出貨 1:已出貨 2:已收貨 3:退貨 4:棄標)");
				}
				
				Integer transRecNo = null;
				try {
					transRecNo = new Integer(req.getParameter("transRecNo").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("錢包交易編號錯誤");
				}
				
				Integer bidWinnerNo = null;
				try {
					bidWinnerNo = new Integer(req.getParameter("bidWinnerNo").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("最高價會員編號錯誤");
				}
				
				Integer bidWinnerPrice = null;
				try {
					bidWinnerPrice = new Integer(req.getParameter("bidWinnerPrice").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("目前最高出價錯誤");
				}
				
				String receiverName = null;
				try {
					receiverName = req.getParameter("receiverName").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("收件人姓名錯誤");
				}
				String receiverAddress = null;
				try {
					receiverAddress = req.getParameter("receiverAddress").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("收件人地址錯誤");
				}
				String receiverPhone = null;
				try {
					receiverPhone = req.getParameter("receiverPhone").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("收件人電話錯誤");
				}
						
				System.out.println("商品編號"+bidProdNo);
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
				bidVO.setTransRecNo(transRecNo);
				bidVO.setBidWinnerNo(bidWinnerNo);
				bidVO.setBidWinnerPrice(bidWinnerPrice);
				bidVO.setReceiverName(receiverName);
				bidVO.setReceiverAddress(receiverAddress);
				bidVO.setReceiverPhone(receiverPhone);
				
				/***************************2.開始刪除資料***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				bidPicSvc.deleteBidPic(bidProdPicNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				// 測試轉交畫面
				req.setAttribute("bidVO", bidVO);
				
				String url = "/backend/bid/update_bid_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete_a".equals(action)) { // 來自update_bid_input.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
//				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				// 測試checkbox
				BidPicService bidPicSvc = new BidPicService();
				String[] bidProdPicNos = req.getParameterValues("bidProdPicNos");
				if (bidProdPicNos != null) {
					for (String bidProdPicNo : bidProdPicNos) {
						bidPicSvc.deleteBidPic(new Integer(bidProdPicNo));
					}
				}
				
				// 測試轉交畫面
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(req.getParameter("bidProdNo"));
				} catch (NumberFormatException ne) {
					ne.getStackTrace();
				}
				
//				String bidProdName = req.getParameter("bidProdName");
//				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
//				if (bidProdName == null || bidProdName.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
//	            }
//				
//				Integer kindNo = new Integer(req.getParameter("kindNo").trim());
//				// 需要從資料庫撈出，才可省略錯誤處理。
//								
//				String bidProdDescription = null;
//				try {
//					bidProdDescription = req.getParameter("bidProdDescription").trim();
//				} catch (IllegalArgumentException e) {
//					bidProdDescription = "";
//				}
//				
//				Integer bidProdStartPrice = null;
//				try {
//					bidProdStartPrice = new Integer(req.getParameter("bidProdStartPrice").trim());
//				} catch (NumberFormatException e) {
//					bidProdStartPrice = 0;
//					errorMsgs.add("起標價應為數字");
//				}
//				if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
//					errorMsgs.add("起標價應在0 - 99000之間");
//				}
//				
//				Integer bidState = new Integer(0);
//				
//				Integer bidPriceIncrement = null;
//				try {
//					bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
//				} catch (NumberFormatException e) {
//					bidPriceIncrement = 0;
//					errorMsgs.add("最低增額應為數字");
//				}
//				if (bidPriceIncrement <= 0) {
//					errorMsgs.add("最低增額應大於0");
//				}
//				
//				java.sql.Timestamp bidProdStartTime = null;
//				try {
//					bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
//				} catch (IllegalArgumentException e) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("請輸入起標時間!");
//				}
//				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("起標時間早於目前時間，請重新輸入！");
//				}
//				
//				java.sql.Timestamp bidProdEndTime = null;
//				try {
//					bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
//				} catch (IllegalArgumentException e) {
//					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
//					errorMsgs.add("請輸入截標時間!");
//				}
//				if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
//					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
//					errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
//				}
//				
//				Integer bidProdState = null;
//				try {
//					bidProdState = new Integer(req.getParameter("bidProdState"));
//				} catch (NumberFormatException e) {
//					e.getMessage();
//					errorMsgs.add("競標商品狀態錯誤");
//				}
//				if (bidProdState < 0 || bidProdState > 4) {
//					errorMsgs.add("競標商品狀態錯誤(0:未出貨 1:已出貨 2:已收貨 3:退貨 4:棄標)");
//				}
				
				System.out.println("商品編號"+bidProdNo);
				BidVO bidVO = new BidVO();
				bidVO.setBidProdNo(bidProdNo);
//				bidVO.setBidProdName(bidProdName);
//				bidVO.setKindNo(kindNo);
//				bidVO.setBidProdDescription(bidProdDescription);
//				bidVO.setBidProdStartPrice(bidProdStartPrice);
//				bidVO.setBidState(bidState);
//				bidVO.setBidPriceIncrement(bidPriceIncrement);
//				bidVO.setBidProdStartTime(bidProdStartTime);
//				bidVO.setBidProdEndTime(bidProdEndTime);
//				bidVO.setBidProdState(bidProdState);
				
				/***************************2.開始刪除資料***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				bidPicSvc.deleteBidPic(bidProdPicNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				// 測試轉交畫面
				req.setAttribute("bidVO", bidVO);
				
				String url = "/backend/bid/update_bid_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
				failureView.forward(req, res);
			}
		}
	}


//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()]; // available()方法表示資料源頭的檔案大小
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName(); // 顧及IE，才需要new File().getName
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
//	public void init() throws ServletException {
//    	try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void destroy() {
//		try {
//			if (con != null) con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
}
package com.usedpic.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.used.model.UsedService;
import com.used.model.UsedVO;
import com.usedpic.*;
import com.usedpic.model.UsedPicService;
import com.usedpic.model.UsedPicVO;

import java.io.*;
import java.util.*;

/**
 * Servlet implementation class UsedServlet
 */
//@WebServlet("/used/UsedServlet")
@MultipartConfig
public class UsedPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsedPicServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");


		if ("getOne_Pic_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer usedPicNo = new Integer(request.getParameter("usedPicNo"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UsedPicService usedPicSvc = new UsedPicService();
				UsedPicVO usedPicVO = usedPicSvc.getOneUsedPic(usedPicNo);
				byte[] b = usedPicVO.getUsedPic();
				response.setContentType("image/jpeg"); 
				OutputStream os = response.getOutputStream();
				os.flush();
				os.write(b);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("usedPicVO", usedPicVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/used/listOneProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("get_Pics_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				UsedPicService usedPicSvc = new UsedPicService();
				List<UsedPicVO> list = usedPicSvc.getOneUsedPics(usedNo);
				response.setContentType("image/jpeg"); 
				for(int i = 0; i <= list.size(); i++) {
				byte[] b = list.get(i).getUsedPic();
				OutputStream os = response.getOutputStream();
				os.flush();
				os.write(b);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = null;
				if ("get_Pics_Display".equals(action))
				url = "/front-end/used/pic.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
				failureView.forward(request, response);
			}
			
		}
		
		if ("getOneBuyer_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = request.getParameter("buyerNo");
				Integer buyerNo = null;
				buyerNo = new Integer(str);
				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				List<UsedVO> list = usedSvc.getAllProdByBuyer(buyerNo);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/used/listAllProdByBuyer.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("getOneSeller_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = request.getParameter("sellerNo");
				Integer sellerNo = null;
				sellerNo = new Integer(str);
				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				List<UsedVO> list = usedSvc.getAllProdBySeller(sellerNo);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/used/listAllProdBySeller.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/used/selectPage.jsp");
				failureView.forward(request, response);
			}
			
		}
		
		if ("addPic".equals(action)) {
			response.setContentType("image/gif");
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				
				UsedPicService usedPicSvc = new UsedPicService();
				UsedPicVO usedPicVO = new UsedPicVO();
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				List<Part> list = (List<Part>)request.getParts();
				list.remove(0);
				list.remove((list.size()-1));
				for(Part part : list) {
				InputStream in = part.getInputStream();
				byte[] b = new byte[in.available()];
				in.read(b);
				usedPicVO = usedPicSvc.addUsedPic(usedNo, b);
				}
//				usedPicVO.setUsedNo(usedNo);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedPicVO", usedPicVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/used/addUsed.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
//				usedPicVO = usedPicSvc.addUsedPic(usedNo, b);
//				request.setAttribute("usedPicVO", usedPicVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("addPic".equals(action))
					url = "/back-end/used/listAllProd.jsp";                    

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("updateUsed".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				
				UsedVO usedVO = new UsedVO();
				String empty = "";
				String usedName = request.getParameter("usedName");
				String usedNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (usedName == null || usedName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if (!usedName.trim().matches(usedNameReg)) {
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字, 且長度必需在2到10之間");
				}
				
				Integer buyerNo = null;
				String buyerNoReg = "^[11(0-9)*]{5}$";
				try {
					buyerNo = new Integer(request.getParameter("buyerNo").trim());
					if (request.getParameter("buyerNo").trim().matches(empty)) {
						
					}
					else if (!request.getParameter("buyerNo").trim().matches(buyerNoReg)) {

						errorMsgs.add("請輸入買家會員編號格式11XXX，長度為5之數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("請輸入買家會員編號格式11XXX，長度為5之數字");
				}
				
				
				Integer sellerNo = null;
				String sellerNoReg = "^[11(0-9)*]{5}$";
				try {
					sellerNo = new Integer(request.getParameter("sellerNo").trim());
					if (!request.getParameter("sellerNo").trim().matches(sellerNoReg)) {

						errorMsgs.add("請輸入賣家會員編號格式11XXX，長度為5之數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("請輸入賣家會員編號格式11XXX，長度為5之數字");
				}
				
				Integer usedPrice = null;
				String usedPriceReg = "^[(1-9)(0-9)*]{1,6}$";
				try {
					usedPrice = new Integer(request.getParameter("usedPrice").trim());
					if (!request.getParameter("usedPrice").trim().matches(usedPriceReg)) {

						errorMsgs.add("售價請輸入1至6位數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("售價請輸入1至6位數字");
				}				
				
				Integer usedState = null;
				String usedStateReg = "^[(0-9)*]{1,2}$";
				try {
					usedState = new Integer(request.getParameter("usedState").trim());
					if (!request.getParameter("usedState").trim().matches(usedStateReg)) {

						errorMsgs.add("商品狀態請輸入1至2位數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("商品狀態請輸入1至2位數字");
				}				
				
				String receiverName = request.getParameter("receiverName");
				String receiverNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (receiverName.trim().matches(empty)) {
					
				} else if (!receiverName.trim().matches(receiverNameReg)) {
					errorMsgs.add("收件人: 只能是中、英文字母、數字, 且長度必需在2到10之間");
				}
				
				String receiverAddress = request.getParameter("receiverAddress");
				String receiverAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{8,100}$";				
				if (receiverAddress.trim().matches(empty)) {
					
				} else if (!receiverAddress.trim().matches(receiverAddressReg)) {
					errorMsgs.add("收件地址: 只能是中、英文字母、數字, 且長度必需在8到100之間");
				}
				
				String receiverPhone = request.getParameter("receiverPhone");
				String receiverPhoneReg = "^[09(0-9)*]{10}$";
				
				if (receiverPhone.trim().matches(empty)) {
					
				} else if (!receiverPhone.trim().matches(receiverPhoneReg)) {

					errorMsgs.add("收件人手機: 只能是數字09XXXXXXXX, 且長度必需為10");
				}
				String usedProdDescription = request.getParameter("usedProdDescription");
				String usedProdDescriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{8,1000}$";
				if (usedProdDescription == null || usedProdDescription.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				} else if (!usedProdDescription.trim().matches(usedProdDescriptionReg)) {
					errorMsgs.add("商品描述: 只能是中、英文字母、數字, 且長度必需在8到1000之間");
				}	
				
				Integer kindNo = null;
				String kindNoReg = "^[(1-9)(0-9)*]{1,16}$";
				try {
					kindNo = new Integer(request.getParameter("kindNo").trim());
					if (!request.getParameter("kindNo").trim().matches(kindNoReg)) {

						errorMsgs.add("商品種類請輸入1至16位數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("商品種類請輸入1至16位數字");
				}	
				
				Integer transRecNo = null;
				String transRecNoReg = "^[15(0-9)*]{5}$";
				try {
					transRecNo = new Integer(request.getParameter("transRecNo").trim());
					if (request.getParameter("transRecNo").trim().matches(empty)) {
						
					}
					else if (!request.getParameter("transRecNo").trim().matches(transRecNoReg)) {

						errorMsgs.add("請輸入交易紀錄編號格式15XXX，長度為5之數字");
					}
				} catch (NumberFormatException e){

					errorMsgs.add("請輸入交易紀錄編號格式15XXX，長度為5之數字");
				}
				
				java.sql.Timestamp usedLaunchedTime = null;
				try {
					usedLaunchedTime = java.sql.Timestamp.valueOf(request.getParameter("usedLaunchedTime").trim());
				} catch (IllegalArgumentException e) {
					usedLaunchedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp soldTime = null;
				try {
					soldTime = java.sql.Timestamp.valueOf(request.getParameter("soldTime").trim());
					if (request.getParameter("soldTime").trim().matches(empty)) {
						
					}
				} catch (IllegalArgumentException e) {
					soldTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				usedVO.setUsedName(usedName);
				usedVO.setBuyerNo(buyerNo);
				usedVO.setSellerNo(sellerNo);
				usedVO.setUsedPrice(usedPrice);
				usedVO.setUsedState(usedState);
				usedVO.setReceiverName(receiverName);
				usedVO.setReceiverAddress(receiverAddress);
				usedVO.setReceiverPhone(receiverPhone);
				usedVO.setUsedProdDescription(usedProdDescription);
				usedVO.setKindNo(kindNo);
				usedVO.setTransRecNo(transRecNo);
				usedVO.setUsedLaunchedTime(usedLaunchedTime);
				usedVO.setSoldTime(soldTime);
				usedVO.setUsedNo(usedNo);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedVO", usedVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/used/updateUsedInput.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
				UsedService usedSvc = new UsedService();
				usedVO = usedSvc.updateUsed(usedNo, kindNo, buyerNo, sellerNo, transRecNo, usedName, usedPrice, usedState, usedLaunchedTime, soldTime, receiverName, receiverAddress, receiverPhone, usedProdDescription);
				request.setAttribute("UsedVO", usedVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("updateUsed".equals(action))
					url = "/back-end/used/listAllProd.jsp";                    
				
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				
				/*************************** 2.開始新增資料 ****************************************/
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				request.setAttribute("usedVO", usedVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("getOne_For_Update".equals(action))
					url = "/back-end/used/updateUsedInput.jsp";                    
				
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}

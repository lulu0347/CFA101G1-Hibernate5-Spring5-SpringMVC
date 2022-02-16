package com.used.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.transRec.model.TransRecService;
import com.transRec.model.TransRecVO;
import com.used.model.UsedService;
import com.used.model.UsedVO;
import com.usedmsg.model.UsedMsgService;
import com.usedmsg.model.UsedMsgVO;
import com.usedpic.model.UsedPicService;
import com.usedpic.model.UsedPicVO;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Servlet implementation class UsedServlet
 */
//@WebServlet("/used/UsedServlet")
@MultipartConfig
public class UsedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsedServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = request.getParameter("usedNo");
				String usedNoReg = "^[3(0-9)*]{5}$";
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				} else if (!str.trim().matches(usedNoReg)) {
					errorMsgs.add("請輸入5位數商品編號3XXXX");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				Integer usedNo = null;
				try {
					usedNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				MemberService memSvc = new MemberService();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				List<MemberVO> memList = new ArrayList<MemberVO>();
				memList = memSvc.getAll();
				if (usedVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("usedVO", usedVO); // 資料庫取出的empVO物件,存入req
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);
				request.setAttribute("memList", memList);
				String url = "/frontend/used/listOneProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}

		if ("getOne_For_Manager".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = request.getParameter("usedNo");
				String usedNoReg = "^[3(0-9)*]{5}$";
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				} else if (!str.trim().matches(usedNoReg)) {
					errorMsgs.add("請輸入5位數商品編號3XXXX");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				Integer usedNo = null;
				try {
					usedNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				if (usedVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("usedVO", usedVO); // 資料庫取出的empVO物件,存入req
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);
				String url = "/backend/used/listOneProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}

		if ("search".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String name = request.getParameter("searchName");

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				List<UsedVO> list = new ArrayList<UsedVO>();
				list = usedSvc.searchProd(name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				request.setAttribute("list", list);
				String url = "/frontend/used/listAllSearch.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}

		if ("searchKind".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer kind = new Integer(request.getParameter("kind"));
				System.out.println(kind);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UsedService usedSvc = new UsedService();
				List<UsedVO> list = new ArrayList<UsedVO>();
				list = usedSvc.searchProdKind(kind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				request.setAttribute("list", list);
				String url = "/frontend/used/listAllByKind.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
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
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/used/listAllProdByBuyer.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
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
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/used/listAllProdBySeller.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}

		if ("addUsed".equals(action)) {

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

				Integer sellerNo = new Integer(request.getParameter("sellerNo"));

				Integer usedPrice = null;
				String usedPriceReg = "^[(1-9)(0-9)*]{1,6}$";
				try {
					usedPrice = new Integer(request.getParameter("usedPrice").trim());
					if (!request.getParameter("usedPrice").trim().matches(usedPriceReg)) {
						usedPrice = 0;
						errorMsgs.add("售價請輸入1至6位數字");
					}
				} catch (NumberFormatException e) {
					usedPrice = 0;
					errorMsgs.add("售價請輸入1至6位數字");
				}

				String usedProdDescription = request.getParameter("usedProdDescription");
				String usedProdDescriptionReg = "^[\\s\\S]{8,1000}$";
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
						kindNo = 1;
						errorMsgs.add("商品種類請輸入1至16位數字");
					}
				} catch (NumberFormatException e) {
					kindNo = 1;
					errorMsgs.add("商品種類請輸入1至16位數字");
				}

				usedVO.setUsedName(usedName);
				usedVO.setSellerNo(sellerNo);
				usedVO.setUsedPrice(usedPrice);
				usedVO.setUsedProdDescription(usedProdDescription);
				usedVO.setKindNo(kindNo);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedVO", usedVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/addUsed.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
				UsedService usedSvc = new UsedService();
				List<Part> list = (List<Part>) request.getParts();
//				list.remove(0);
//				list.remove((list.size()-1));
				List<byte[]> listPic = new ArrayList<byte[]>();
				for (Part part : list) {

					InputStream in = part.getInputStream();
					if (in.available() > 700) {
						byte[] b = new byte[in.available()];
						in.read(b);
						listPic.add(b);
					}
				}

				usedSvc.addUsed2(kindNo, sellerNo, usedName, usedPrice, usedProdDescription, listPic);
//				UsedPicService usedPicSvc = new UsedPicService();
//				UsedPicVO usedPicVO = new UsedPicVO();
//				request.setAttribute("addUsed", usedVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("addUsed".equals(action))
					url = "/frontend/used/listAllProdBySeller.jsp";

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

					} else if (!request.getParameter("buyerNo").trim().matches(buyerNoReg)) {

						errorMsgs.add("請輸入買家會員編號格式11XXX，長度為5之數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("請輸入買家會員編號格式11XXX，長度為5之數字");
				}

				Integer sellerNo = null;
				String sellerNoReg = "^[11(0-9)*]{5}$";
				try {
					sellerNo = new Integer(request.getParameter("sellerNo").trim());
					if (!request.getParameter("sellerNo").trim().matches(sellerNoReg)) {

						errorMsgs.add("請輸入賣家會員編號格式11XXX，長度為5之數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("請輸入賣家會員編號格式11XXX，長度為5之數字");
				}

				Integer usedPrice = null;
				String usedPriceReg = "^[(1-9)(0-9)*]{1,6}$";
				try {
					usedPrice = new Integer(request.getParameter("usedPrice").trim());
					if (!request.getParameter("usedPrice").trim().matches(usedPriceReg)) {

						errorMsgs.add("售價請輸入1至6位數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("售價請輸入1至6位數字");
				}

				Integer usedState = null;
				String usedStateReg = "^[(0-9)*]{1,2}$";
				try {
					usedState = new Integer(request.getParameter("usedState").trim());
					if (!request.getParameter("usedState").trim().matches(usedStateReg)) {

						errorMsgs.add("商品狀態請輸入1至2位數字");
					}
				} catch (NumberFormatException e) {

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

				if (usedProdDescription == null || usedProdDescription.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				} else if (usedProdDescription.trim().length() < 8) {
					errorMsgs.add("商品描述: 只能是中、英文字母、數字, 且長度必需在8到1000之間");
				}

				Integer kindNo = null;
				String kindNoReg = "^[(1-9)(0-9)*]{1,16}$";
				try {
					kindNo = new Integer(request.getParameter("kindNo").trim());
					if (!request.getParameter("kindNo").trim().matches(kindNoReg)) {

						errorMsgs.add("商品種類請輸入1至16位數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("商品種類請輸入1至16位數字");
				}

				Integer transRecNo = null;
				String transRecNoReg = "^[15(0-9)*]{5}$";
				try {
					transRecNo = new Integer(request.getParameter("transRecNo").trim());
					if (request.getParameter("transRecNo").trim().matches(empty)) {

					} else if (!request.getParameter("transRecNo").trim().matches(transRecNoReg)) {

						errorMsgs.add("請輸入交易紀錄編號格式15XXX，長度為5之數字");
					}
				} catch (NumberFormatException e) {

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
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/updateUsedInput.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
				UsedService usedSvc = new UsedService();
				usedVO = usedSvc.updateUsed(usedNo, kindNo, buyerNo, sellerNo, transRecNo, usedName, usedPrice,
						usedState, usedLaunchedTime, soldTime, receiverName, receiverAddress, receiverPhone,
						usedProdDescription);
				request.setAttribute("UsedVO", usedVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("updateUsed".equals(action))
					url = "/backend/used/listAllProd.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("updateUsed2".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				String empty = "";
				String usedName = request.getParameter("usedName");
				String usedNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (usedName == null || usedName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				} else if (!usedName.trim().matches(usedNameReg)) {
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字, 且長度必需在2到10之間");
				}

				Integer usedPrice = null;
				String usedPriceReg = "^[(1-9)(0-9)*]{1,6}$";
				try {
					usedPrice = new Integer(request.getParameter("usedPrice").trim());
					if (!request.getParameter("usedPrice").trim().matches(usedPriceReg)) {

						errorMsgs.add("售價請輸入1至6位數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("售價請輸入1至6位數字");
				}

				Integer usedState = null;
				String usedStateReg = "^[(0-9)*]{1,2}$";
				try {
					usedState = new Integer(request.getParameter("usedState").trim());
					if (!request.getParameter("usedState").trim().matches(usedStateReg)) {

						errorMsgs.add("商品狀態請輸入1至2位數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("商品狀態請輸入1至2位數字");
				}

				String usedProdDescription = request.getParameter("usedProdDescription");

				Integer kindNo = null;
				String kindNoReg = "^[(1-9)(0-9)*]{1,16}$";
				try {
					kindNo = new Integer(request.getParameter("kindNo").trim());
					if (!request.getParameter("kindNo").trim().matches(kindNoReg)) {

						errorMsgs.add("商品種類請輸入1至16位數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("商品種類請輸入1至16位數字");
				}

				UsedService usedSvc = new UsedService();
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				usedVO.setUsedName(usedName);
				usedVO.setUsedPrice(usedPrice);
				usedVO.setUsedState(usedState);
				usedVO.setUsedProdDescription(usedProdDescription);
				usedVO.setKindNo(kindNo);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedVO", usedVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/updateUsedInput.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
				usedSvc.updateUsed2(usedVO, kindNo, usedName, usedPrice, usedState, usedProdDescription);
				request.setAttribute("UsedVO", usedVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("updateUsed2".equals(action))
					url = "/frontend/used/listAllProdBySeller.jsp";

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

			Integer usedNo = new Integer(request.getParameter("usedNo"));

			try {
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				request.setAttribute("usedVO", usedVO);
				request.setAttribute("picList", picList);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("getOne_For_Update".equals(action))
					url = "/frontend/used/updateUsedInput.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("checkout".equals(action)) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				TransRecService transRecSvc = new TransRecService();
				HttpSession session = request.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("user");
				Integer buyerNo = memberVO.getMemNo();
				System.out.println(transRecSvc.getDeposit(buyerNo));
				System.out.println(usedVO.getUsedPrice());

				if (transRecSvc.getDeposit(buyerNo) < usedVO.getUsedPrice()) {
					String url = "/frontend/transRec/saveMoney.jsp";

					RequestDispatcher saveMoney = request.getRequestDispatcher(url);
					saveMoney.forward(request, response);
					return;
				}

				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				msgList = msgSvc.getMsgByUsedNo(usedNo);

				String url = null;
				if ("checkout".equals(action))
					url = "/frontend/used/checkout.jsp";

				request.setAttribute("usedVO", usedVO);
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("checkout_submit".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer usedNo = new Integer(request.getParameter("usedNo"));
				Integer buyerNo = new Integer(request.getParameter("memNo"));
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				TransRecService transRecSvc = new TransRecService();

				String receiverName = request.getParameter("receiverName");
				String receiverPhone = request.getParameter("receiverPhone");
				
				StringBuilder sb = new StringBuilder();
	            String receiverAddressCounty = null;
	            String receiverAddressCity = null;
	            String receiverAddressDetail = null;
	            String receiverAddress = null;
	            
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				request.setAttribute("picList", picList);
				
	            try {
	            receiverAddressCounty = request.getParameter("receiverAddressCounty");
	            receiverAddressCity = request.getParameter("receiverAddressCity");
	            receiverAddressDetail = request.getParameter("receiverAddressDetail");

	            if (receiverAddressDetail == null || receiverAddressDetail.trim().length() == 0) {
	                errorMsgs.add("收件者地址請勿空白");
	        }
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedVO", usedVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/checkout.jsp");
					failureView.forward(request, response);
					return;
				}
	            
	            sb.append(receiverAddressCounty);

	            sb.append(receiverAddressCity);

	            sb.append(receiverAddressDetail);

	            receiverAddress = sb.toString(); 
	            }catch(Exception e) {
	                System.out.println("錯誤");
	            }

				TransRecVO transRec = new TransRecVO();
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNo(buyerNo);
				transRec.setMallName("二手商城");
				transRec.setMemberVO(memberVO);
				transRec.setOrderNo(usedNo);
				transRec.setTransAmount(-usedVO.getUsedPrice());
				transRec.setTransCont("");
				transRec.setTransRecTime(new Timestamp(System.currentTimeMillis()));
				transRec.setTransState(1);

				Integer transRecNo = transRecSvc.createTransRecord(transRec);
				usedSvc.updateTrans(usedVO, transRecNo, buyerNo, receiverName, receiverAddress, receiverPhone);

				String url = null;
				if ("checkout_submit".equals(action))
					url = "/frontend/used/checkout_success.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("cancel".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer usedNo = new Integer(request.getParameter("usedNo"));

				UsedService usedSvc = new UsedService();
				usedSvc.cancel(usedNo);
				;

				String url = null;
				if ("cancel".equals(action))
					url = "/frontend/used/listAllProdByBuyer.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("delete_Prod".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer usedNo = new Integer(request.getParameter("usedNo"));

				UsedService usedSvc = new UsedService();
				usedSvc.deleteProd(usedNo);
				;

				String url = null;
				if ("delete_Prod".equals(action))
					url = "/backend/used/listAllProd.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}

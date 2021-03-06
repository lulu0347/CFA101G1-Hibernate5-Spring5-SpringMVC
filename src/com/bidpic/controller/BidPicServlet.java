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
		
		if ("getOne_For_Display".equals(action)) { // ??????select_page.jsp?????????

			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.?????????????????? - ???????????????????????????**********************/
				String str = req.getParameter("bidProdNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("?????????????????????");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/select_page.jsp");
					failureView.forward(req, res);
					return;//????????????
				}
				
				Integer bidProdPicNo = null;
				try {
					bidProdPicNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("???????????????????????????");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/select_page.jsp");
					failureView.forward(req, res);
					return;//????????????
				}
				
				/***************************2.??????????????????*****************************************/
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = bidPicSvc.getOneBidPic(bidProdPicNo);
				if (bidPicVO == null) {
					errorMsgs.add("????????????");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/select_page.jsp");
					failureView.forward(req, res);
					return;//????????????
				}
				
				/***************************3.????????????,????????????(Send the Success view)*************/
				req.setAttribute("bidPicVO", bidPicVO); // ??????????????????bidPicVO??????,??????req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????? listOneBidPic.jsp
				successView.forward(req, res);

				/***************************???????????????????????????*************************************/
			} catch (Exception e) {
				errorMsgs.add("??????????????????:" + e.getMessage() + "?????????bidpicservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // ??????listAllBidPic.jsp?????????

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????????????????****************************************/
				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				
				/***************************2.??????????????????****************************************/
				BidPicService bidPicSvc = new BidPicService();
				BidPicVO bidPicVO = bidPicSvc.getOneBidPic(bidProdPicNo);
								
				/***************************3.????????????,????????????(Send the Success view)************/
				req.setAttribute("bidPicVO", bidPicVO);         // ??????????????????bidPicVO??????,??????req
				String url = "/bidpic/update_bidpic_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_bidpic_input.jsp
				successView.forward(req, res);

				/***************************???????????????????????????**********************************/
			} catch (Exception e) {
				errorMsgs.add("??????????????????????????????:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/listAllBidPic.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("update".equals(action)) { // ??????update_bidpic_input.jsp?????????
		
		if ("insert".equals(action)) { // ??????addBidPic.jsp?????????
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.?????????????????? - ???????????????????????????*************************/
				
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
				
				Part part = req.getPart("upfile1");
//				String dir = "/images_uploaded";
//				String filename = getFileNameFromPart(part);
				byte[] bidProdPicContent = null;
				InputStream is = null;
				BufferedInputStream bis = null;
//				part.write(getServletContext().getRealPath(dir) + "/" + filename);
				
//				String realPath = getServletContext().getRealPath(dir)+ "/" + filename;

					// ??????????????????
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
					
					req.setAttribute("bidPicVO", bidPicVO); // ???????????????????????????bidPicVO??????,?????????req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.??????????????????***************************************/
				BidPicService bidPicSvc = new BidPicService();
				
				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.????????????,????????????(Send the Success view)***********/
				String url = "/backend/bidpic/listAllBidPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ?????????????????????listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************???????????????????????????**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
				failureView.forward(req, res);
			}
			
		}
		
//		// ????????????????????????
		if ("insert_multi".equals(action)) { // ??????addBidPic.jsp?????????
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.?????????????????? - ???????????????????????????*************************/
				
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
					
					req.setAttribute("bidPicVO", bidPicVO); // ???????????????????????????bidPicVO??????,?????????req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.??????????????????***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				
//				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.????????????,????????????(Send the Success view)***********/
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ?????????????????????listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************???????????????????????????**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bidpic/addBidPic.jsp");
				failureView.forward(req, res);
			}
			
		}
		if ("insert_multi_bid".equals(action)) { // ??????update_bid_input.jsp?????????
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.?????????????????? - ???????????????????????????*************************/
				
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
					
					req.setAttribute("bidPicVO", bidPicVO); // ???????????????????????????bidPicVO??????,?????????req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.??????????????????***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				
//				bidPicVO = bidPicSvc.addBidPic(bidProdNo, bidProdPicContent);
				
				/***************************3.????????????,????????????(Send the Success view)***********/
				String url = "/backend/bid/listAllBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ?????????????????????listAllBidPic.jsp
				successView.forward(req, res);
				
				/***************************???????????????????????????**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("delete".equals(action)) { // ??????update_bid_input.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????????????????***************************************/
//				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				// ??????checkbox
				BidPicService bidPicSvc = new BidPicService();
				String[] bidProdPicNos = req.getParameterValues("bidProdPicNos");
				if (bidProdPicNos != null) {
					for (String bidProdPicNo : bidProdPicNos) {
						bidPicSvc.deleteBidPic(new Integer(bidProdPicNo));
					}
				}
				
				// ??????????????????
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(req.getParameter("bidProdNo"));
				} catch (NumberFormatException ne) {
					ne.getStackTrace();
				}
				System.out.println("????????????"+bidProdNo);
				
				String bidProdName = req.getParameter("bidProdName");
				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
				if (bidProdName == null || bidProdName.trim().length() == 0) {
					errorMsgs.add("????????????: ????????????");
				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //??????????????????(???)?????????(regular-expression)
					errorMsgs.add("????????????: ???????????????????????????????????????_ , ???????????????1???50??????");
	            }
				
				Integer kindNo = new Integer(req.getParameter("kindNo").trim());
				// ??????????????????????????????????????????????????????
								
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
					errorMsgs.add("?????????????????????");
				}
				if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
					errorMsgs.add("???????????????0 - 99000??????");
				}
				
				Integer bidState = new Integer(0);
				
				Integer bidPriceIncrement = null;
				try {
					bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
				} catch (NumberFormatException e) {
					bidPriceIncrement = 0;
					errorMsgs.add("????????????????????????");
				}
				if (bidPriceIncrement <= 0) {
					errorMsgs.add("?????????????????????0");
				}
				
				java.sql.Timestamp bidProdStartTime = null;
				try {
					bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
					errorMsgs.add("?????????????????????!");
				}
//				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("???????????????????????????????????????????????????");
//				}
				
				java.sql.Timestamp bidProdEndTime = null;
				try {
					bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
				} catch (IllegalArgumentException e) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
					errorMsgs.add("?????????????????????!");
				}
				if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
					errorMsgs.add("??????????????????????????????????????????????????????");
				}
				
				Integer bidProdState = null;
				try {
					bidProdState = new Integer(req.getParameter("bidProdState").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("????????????????????????");
				}
				if (bidProdState < 0 || bidProdState > 4) {
					errorMsgs.add("????????????????????????(0:????????? 1:????????? 2:????????? 3:?????? 4:??????)");
				}
				
				Integer transRecNo = null;
				try {
					transRecNo = new Integer(req.getParameter("transRecNo").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("????????????????????????");
				}
				
				Integer bidWinnerNo = null;
				try {
					bidWinnerNo = new Integer(req.getParameter("bidWinnerNo").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("???????????????????????????");
				}
				
				Integer bidWinnerPrice = null;
				try {
					bidWinnerPrice = new Integer(req.getParameter("bidWinnerPrice").trim());
				} catch (NumberFormatException e) {
					e.getMessage();
					errorMsgs.add("????????????????????????");
				}
				
				String receiverName = null;
				try {
					receiverName = req.getParameter("receiverName").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("?????????????????????");
				}
				String receiverAddress = null;
				try {
					receiverAddress = req.getParameter("receiverAddress").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("?????????????????????");
				}
				String receiverPhone = null;
				try {
					receiverPhone = req.getParameter("receiverPhone").trim();
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("?????????????????????");
				}
						
				System.out.println("????????????"+bidProdNo);
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
				
				/***************************2.??????????????????***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				bidPicSvc.deleteBidPic(bidProdPicNo);
				
				/***************************3.????????????,????????????(Send the Success view)***********/
				// ??????????????????
				req.setAttribute("bidVO", bidVO);
				
				String url = "/backend/bid/update_bid_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("??????????????????:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete_a".equals(action)) { // ??????update_bid_input.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????????????????***************************************/
//				Integer bidProdPicNo = new Integer(req.getParameter("bidProdPicNo"));
				// ??????checkbox
				BidPicService bidPicSvc = new BidPicService();
				String[] bidProdPicNos = req.getParameterValues("bidProdPicNos");
				if (bidProdPicNos != null) {
					for (String bidProdPicNo : bidProdPicNos) {
						bidPicSvc.deleteBidPic(new Integer(bidProdPicNo));
					}
				}
				
				// ??????????????????
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(req.getParameter("bidProdNo"));
				} catch (NumberFormatException ne) {
					ne.getStackTrace();
				}
				
//				String bidProdName = req.getParameter("bidProdName");
//				String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(\\-\\)]{1,50}$";
//				if (bidProdName == null || bidProdName.trim().length() == 0) {
//					errorMsgs.add("????????????: ????????????");
//				} else if(!bidProdName.trim().matches(bidProdNameReg)) { //??????????????????(???)?????????(regular-expression)
//					errorMsgs.add("????????????: ???????????????????????????????????????_ , ???????????????1???50??????");
//	            }
//				
//				Integer kindNo = new Integer(req.getParameter("kindNo").trim());
//				// ??????????????????????????????????????????????????????
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
//					errorMsgs.add("?????????????????????");
//				}
//				if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
//					errorMsgs.add("???????????????0 - 99000??????");
//				}
//				
//				Integer bidState = new Integer(0);
//				
//				Integer bidPriceIncrement = null;
//				try {
//					bidPriceIncrement = new Integer(req.getParameter("bidPriceIncrement").trim());
//				} catch (NumberFormatException e) {
//					bidPriceIncrement = 0;
//					errorMsgs.add("????????????????????????");
//				}
//				if (bidPriceIncrement <= 0) {
//					errorMsgs.add("?????????????????????0");
//				}
//				
//				java.sql.Timestamp bidProdStartTime = null;
//				try {
//					bidProdStartTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdStartTime").trim());
//				} catch (IllegalArgumentException e) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("?????????????????????!");
//				}
//				if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
//					bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
//					errorMsgs.add("???????????????????????????????????????????????????");
//				}
//				
//				java.sql.Timestamp bidProdEndTime = null;
//				try {
//					bidProdEndTime = java.sql.Timestamp.valueOf(req.getParameter("bidProdEndTime").trim());
//				} catch (IllegalArgumentException e) {
//					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
//					errorMsgs.add("?????????????????????!");
//				}
//				if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
//					bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
//					errorMsgs.add("??????????????????????????????????????????????????????");
//				}
//				
//				Integer bidProdState = null;
//				try {
//					bidProdState = new Integer(req.getParameter("bidProdState"));
//				} catch (NumberFormatException e) {
//					e.getMessage();
//					errorMsgs.add("????????????????????????");
//				}
//				if (bidProdState < 0 || bidProdState > 4) {
//					errorMsgs.add("????????????????????????(0:????????? 1:????????? 2:????????? 3:?????? 4:??????)");
//				}
				
				System.out.println("????????????"+bidProdNo);
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
				
				/***************************2.??????????????????***************************************/
//				BidPicService bidPicSvc = new BidPicService();
//				bidPicSvc.deleteBidPic(bidProdPicNo);
				
				/***************************3.????????????,????????????(Send the Success view)***********/
				// ??????????????????
				req.setAttribute("bidVO", bidVO);
				
				String url = "/backend/bid/update_bid_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("??????????????????:"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/update_bid_input.jsp");
				failureView.forward(req, res);
			}
		}
	}


//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()]; // available()???????????????????????????????????????
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ?????????
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName(); // ??????IE????????????new File().getName
		System.out.println("filename=" + filename); // ?????????
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
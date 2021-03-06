package com.bid.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.transRec.model.TransRecService;
import com.transRec.model.TransRecVO;

/**
 * Servlet implementation class BidCheckoutServlet
 */
@WebServlet("/BidCheckoutServlet")
public class BidCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidCheckoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.getWriter().append("Served at: ").append(req.getContextPath());
//		doPost(req, res);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		MemberVO memVO = (MemberVO) session.getAttribute("user");
		
//		Vector<BidVO> bidCheckout = (Vector<BidVO>) session.getAttribute("bidCheckout");
		
		if ("beforeCHECKOUT".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			PrintWriter pw = res.getWriter();
			JSONArray errorMessage = new JSONArray();
			
			BidService bidSvc = new BidService();
			BidPicService bidPicSvc = new BidPicService();
			BidVO bidVO = new BidVO();
			BidPicVO bidPicVO = new BidPicVO();
//			MemberService memSvc = new MemberService();
			TransRecService transRecSvc = new TransRecService();

			try {
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo"));
				bidVO = bidSvc.getOneBid(bidProdNo);
				bidPicVO = bidPicSvc.getOneBidPicByBidProdNo(bidProdNo);
				Integer bidProdPicNo = null;
				try {
					bidProdPicNo = bidPicVO.getBidProdPicNo();
				} catch (NullPointerException ne) {
					bidProdPicNo = 0;
//					errorMsgs.add("????????????????????????");
//					errorMessage.put("????????????????????????");
//					System.out.println("????????????????????????");
				}
				String bidProdDescription = null;
				try {
					bidProdDescription = bidVO.getBidProdDescription().trim();
				} catch (NullPointerException ne) {
					bidProdDescription = "";
				}
				
				Integer memNo = memVO.getMemNo();
				
//				memVO = memSvc.getOneMem(memNo);
				Integer ecash = null;
				try {
					ecash = transRecSvc.getDeposit(memNo);
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					errorMsgs.add("??????????????????????????????");
					System.out.println("??????????????????????????????");
				}
				
				Integer bidWinnerNo = null;
				try {
					bidWinnerNo = bidVO.getBidWinnerNo();

				} catch (NullPointerException ne) {
					errorMsgs.add("?????????????????????");
					System.out.println("?????????????????????");
				}
				if (!memNo.equals(bidWinnerNo)) {
					System.out.println("????????????");
					errorMsgs.add("????????????");
				}
				
				Integer bidWinnerPrice = null;
				try {

					bidWinnerPrice = bidVO.getBidWinnerPrice();
				} catch (NullPointerException ne) {
					errorMsgs.add("???????????????????????????");
					System.out.println("???????????????????????????");
				}
				Integer bidProdState = null;
				try {
					bidProdState = bidVO.getBidProdState();
				} catch (NullPointerException ne) {
					errorMsgs.add("????????????????????????????????????");
					System.out.println("????????????????????????????????????");
				}
				if (bidProdState != 0) {
					errorMsgs.add("????????????????????????????????????");
					System.out.println("????????????????????????????????????");
				}
				
				bidVO = bidSvc.getOneBid(bidProdNo);
				req.setAttribute("bidVO", bidVO);
				
				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("bidVO", bidVO); // ???????????????????????????bidVO??????,?????????req
					RequestDispatcher failureView = req
							.getRequestDispatcher("../frontend/bid/listAllBid.html");
					System.out.println("????????????");
					failureView.forward(req, res);
//					res.sendRedirect("/frontend/bid/listAllBid.html");
					return;
				}
				
//				JSONObject json = new JSONObject();
//				JSONObject member = new JSONObject();
//				JSONObject bidProduct = new JSONObject();
//				bidProduct.put("bidProdNo", bidProdNo);
//				bidProduct.put("bidProdPicNo", bidProdPicNo);
//				bidProduct.put("bidProdDescription", bidProdDescription);
//				bidProduct.put("bidWinnerPrice", bidWinnerPrice);
//				member.put("memNo", memNo);
//				json.put("bidProduct", bidProduct);
//				json.put("member", memNo);
				
				String url = "/frontend/bid/checkout.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("??????????????????:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("../frontend/bid/listAllBid.html");
				failureView.forward(req, res);
			}
		}
		
		if ("CHECKOUT".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????????????????****************************************/
				Integer bidProdNo = new Integer(req.getParameter("bidProdNo"));
				
				/***************************2.??????????????????****************************************/
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
				MemberService memSvc = new MemberService();
//				MemberVO memVO = new MemberVO();
				TransRecService transRecSvc = new TransRecService();
				
				Integer bidProdState = null;
				try {
					bidProdState = bidVO.getBidProdState();
				} catch (Exception e) {
					e.getMessage();
					errorMsgs.add("????????????????????????????????????");
				}
				if (bidProdState != 0) {
					errorMsgs.add("????????????????????????????????????");
				}
				
				Integer memno = null;
				try {
					memno = memVO.getMemNo();
				} catch (Exception e) {
					e.getMessage();
					errorMsgs.add("????????????????????????");
				}
				
				Integer ecash = null;
				try {
					ecash = transRecSvc.getDeposit(memno);
				} catch (Exception e) {
					e.getMessage();
					errorMsgs.add("??????????????????");
				}
				
				Integer bidWinnerNo = null;
				try {
//					bidWinnerNo = 11004;
					bidWinnerNo = bidVO.getBidWinnerNo();
				} catch (NullPointerException ne) {
					errorMsgs.add("?????????????????????");
				}
				Integer bidWinnerPrice = null;
				try {
//					bidWinnerPrice = 6900;
					bidWinnerPrice = bidVO.getBidWinnerPrice();
				} catch (Exception e) {
					errorMsgs.add("???????????????????????????");
				}
				StringBuilder sb = new StringBuilder();
				String receiverAddressCounty = null;
				String receiverAddressCity = null;
				String receiverAddressDetail = null;
				String receiverName = null;
				String receiverAddress = null;
				String receiverPhone = null;
				try {
					receiverName = req.getParameter("receiverName").trim();
					
					receiverAddressCounty = req.getParameter("receiverAddressCounty");
					receiverAddressCity = req.getParameter("receiverAddressCity");
					receiverAddressDetail = req.getParameter("receiverAddressDetail");
					sb.append(receiverAddressCounty);
					
					sb.append(receiverAddressCity);
					
					sb.append(receiverAddressDetail);
					
					receiverAddress = sb.toString();
//					receiverAddress = req.getParameter("receiverAddress").trim();
					
					receiverPhone = req.getParameter("receiverPhone").trim();
				} catch (NullPointerException ne) {
					errorMsgs.add("????????????????????????!");
				}
				if (receiverName.length() == 0 || receiverAddress.length() == 0 || receiverPhone.length() == 0) {
					errorMsgs.add("????????????????????????!");
				}
				
//				??????????????????
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
				Connection conn = ds.getConnection();
				synchronized(this) {
//					conn.setAutoCommit(false);
				
					Integer transAmount = null;
					if (bidWinnerPrice > ecash) {
						errorMsgs.add("??????????????????????????????????????????!");
						
// ????????????????????????
						res.sendRedirect("../frontend/transRec/saveMoney.jsp");
						return;
					}
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("../../frontend/bid/listAllBid.html");
						failureView.forward(req, res);
						return;//????????????
					}
					
					TransRecService transRecService = new TransRecService();
					if (bidWinnerPrice <= ecash) {
						transAmount = new Integer(-bidWinnerPrice);
						ecash += transAmount;
					}
					
					
					bidVO.setBidProdState(1);
					bidVO.setReceiverName(receiverName);
					bidVO.setReceiverAddress(receiverAddress);
					bidVO.setReceiverPhone(receiverPhone);
					bidSvc.updateBid4(bidVO);
				
					TransRecVO transRecVO = new TransRecVO();
					MemberVO memberVO = new MemberVO();
					memberVO.setMemNo(bidWinnerNo);
					transRecVO.setTransAmount(transAmount);
					transRecVO.setMallName("????????????");
					transRecVO.setMemberVO(memberVO);
					transRecVO.setOrderNo(bidProdNo);
					transRecVO.setTransCont("");
					transRecVO.setTransRecTime(new Timestamp(System.currentTimeMillis()));
					transRecVO.setTransState(1);
					Integer transRecNo = null;
					try {
						transRecNo = transRecService.createTransRecord(transRecVO);
//						transRecNo = transRecService.createTransRecordInTransaction(conn, transRecVO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					BidVO bidVO1 = new BidVO();
					bidVO1.setTransRecNo(transRecNo);
					bidVO1.setBidProdNo(bidProdNo);
					bidSvc.updateBid5(bidVO1);
					
//					memSvc.updateEcash(memno, ecash);
					System.out.println("End of Checkout");
//					memSvc.updateMem(ecash);
					
//					conn.commit();
//					conn.setAutoCommit(true);
					
				}
				
				/***************************3.????????????,????????????(Send the Success view)************/
//				req.setAttribute("bidVO", bidVO);         // ??????????????????bidVO??????,??????req
//				String url = "/frontend/bid/listAllBid.html";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_bid_input.jsp
//				successView.forward(req, res);
					res.sendRedirect(req.getContextPath() + "/frontend/bid/listAllBid.html");

				/***************************???????????????????????????**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("??????????????????????????????:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/bid/listAllBid.html");
				failureView.forward(req, res);
			}
			
		}
	}

}

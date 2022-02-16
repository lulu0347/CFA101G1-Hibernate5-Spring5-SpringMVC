package com.bidrecord.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bid.model.*;
import com.bidrecord.model.*;
import com.member.model.MemberVO;

/**
 * Servlet implementation class BidRecordServlet
 */
@WebServlet("/bidrecord/BidRecordServlet")
public class BidRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidRecordServlet() {
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
		res.setContentType(getServletContext().getMimeType("application/json") + ";charset=utf-8");
		res.setCharacterEncoding("UTF-8");

		StringBuilder sb = new StringBuilder();
		BufferedReader br = req.getReader();
		PrintWriter pwt = res.getWriter();
		String action = null;
	    String line;
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
		JSONObject json = null;
		System.out.println(sb);
		try {
			json = new JSONObject(sb.toString());
			System.out.println(json);
			action = json.getString("action");
//			Integer bidPrice = json.getInt("bidPrice");
		} catch (JSONException e) {
			res.sendRedirect(req.getContextPath()+"/frontend/bid/listAllBid.html");
			e.printStackTrace();
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bidRecordNo");
				
				// 可再加入出價編號的格式判斷
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入出價編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bidRecordNo = null;
				try {
					bidRecordNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("出價編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bidrecord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidRecordService bidRecordSvc = new BidRecordService();
				BidRecordVO bidRecordVO = bidRecordSvc.getOneBidRecord(bidRecordNo);
				if (bidRecordVO == null) {
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
				req.setAttribute("bidRecordVO", bidRecordVO); // 資料庫取出的bidRecordVO物件,存入req
				String url = "/backend/bid/listOneBid.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidrecordservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display_By_BidProdNo".equals(action)) { // 來自select_page.jsp的請求
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bidProdNo");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("商品編號錯誤");
				}
				Integer bidProdNo = new Integer(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidRecordService bidRecordSvc = new BidRecordService();
				List<BidRecordVO> bidRecordVO = bidRecordSvc.findByBidProdNo(bidProdNo);
				if (bidRecordVO == null) {
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
				req.setAttribute("bidRecordVO", bidRecordVO); // 資料庫取出的bidRecordVO物件,存入req
				String url = "/backend/bid/listAllBidRecord_ByBidProdNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidrecordservlet");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bid/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insertBid".equals(action)) { // 來自addBidRecord.jsp的請求

			HttpSession session = req.getSession();
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			JSONArray errorMessage = new JSONArray();

			try {
				
				MemberVO memVO = (MemberVO) session.getAttribute("user");
				
				Integer memNo = null;
				try {
					memNo = new Integer(memVO.getMemNo());
				} catch (NullPointerException ne) {
					memNo = -1;
					ne.getMessage();
					errorMessage.put("未登入會員");
				}
				System.out.println("memNo="+memNo);
				
				BidRecordService bidRecordSvc = new BidRecordService();
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer bidProdNo = null;
				try {
					bidProdNo = new Integer(json.getInt("bidProdNo"));
				} catch (NullPointerException e) {
					e.getMessage();
					errorMessage.put("商品編號有誤");
				}
//				String prodPageURI = null;
//				try {
//					prodPageURI = json.getString("prodPageURI").trim();
//				} catch (NullPointerException e) {
//					e.getMessage();
//					errorMessage.put("導向網址有誤");
//				}
				
				// 取出截標時間等資料
				BidService bidSvc = new BidService();
				BidVO bidVO = bidSvc.getOneBid(bidProdNo);
				Timestamp bidProdStartTime = bidVO.getBidProdStartTime();
				Timestamp bidProdEndTime = bidVO.getBidProdEndTime();
				Integer bidProdStartPrice = bidVO.getBidProdStartPrice();
				Integer bidPriceIncrement = bidVO.getBidPriceIncrement();
//				Integer bidWinnerPrice = bidVO.getBidWinnerPrice();
				
				// 取出商品編號=?的最高價紀錄
				BidRecordVO bidRecordVO = bidRecordSvc.findByBidProdNoHighest(bidProdNo);
				Integer bidCurrentWinnerPrice = null;
				Integer bidCurrentWinnerNo = null;
				if (bidRecordVO != null) {
					try {
						bidCurrentWinnerPrice = bidRecordVO.getBidPrice();
						System.out.println("目前最高價="+bidCurrentWinnerPrice);
					} catch (NumberFormatException e) {
						e.getMessage();
						errorMsgs.add("最高金額取出有誤");
					}
					try {
						bidCurrentWinnerNo = bidRecordVO.getMemNo();
						System.out.println("目前最高價會員="+bidCurrentWinnerNo);
					} catch (NumberFormatException e) {
						e.getMessage();
						errorMsgs.add("最高價會員取出有誤");
					}
				} else {
					bidCurrentWinnerPrice = 0;
					bidCurrentWinnerNo = null;
				}

				
//				try {
//					memNo = new Integer(json.getInt("memNo"));
//				} catch (NumberFormatException e) {
//					memNo = -1;
//					e.getMessage();
////					errorMsgs.add("會員編號錯誤");
//					errorMessage.put("會員編號錯誤");
//				} catch (JSONException je) {
//					memNo = -1;
//					errorMessage.put("會員編號錯誤");
//				}
				Integer bidPrice = null;
				try {
//					bidPrice = new Integer(req.getParameter("bidPrice").trim());
					bidPrice = new Integer(json.getInt("bidPrice"));
				} catch (NumberFormatException e) {
					bidPrice = -1;
					e.getMessage();
//					errorMsgs.add("出價金額有誤!");
					errorMessage.put("出價金額格式有誤");
				} catch (JSONException je) {
					bidPrice = -1;
					errorMessage.put("出價金額格式有誤");
				}
				java.sql.Timestamp bidTime = null;
				try {
					bidTime = new java.sql.Timestamp(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
//					errorMsgs.add("出價時間有誤!");
					errorMessage.put("出價時間有誤");
				}
				if (bidTime.before(bidProdStartTime)) {
					System.out.println("尚未開始競標");
//					errorMsgs.add("尚未開始競標");
					errorMessage.put("尚未開始競標");
				};
				if (bidTime.after(bidProdEndTime)) {
					System.out.println("已超過截標時間");
//					errorMsgs.add("已超過截標時間");
					errorMessage.put("已超過競標時間");
				}
				Integer leastPrice = 0;
				leastPrice = ((bidProdStartPrice+bidPriceIncrement) > (bidCurrentWinnerPrice+bidPriceIncrement)) ? (bidProdStartPrice+bidPriceIncrement) : (bidCurrentWinnerPrice+bidPriceIncrement);
				if (bidPrice < leastPrice) {
//					System.out.println("出價金額未達起標價加上增額");
//					errorMsgs.add("出價金額未達起標價加上增額");
					errorMessage.put("出價金額應至少為" + leastPrice);
				}
//				if (bidPrice < (bidCurrentWinnerPrice+bidPriceIncrement)) {
//					System.out.println("出價金額未達目前標價加上增額");
////					errorMsgs.add("出價金額未達目前標價加上增額");
//					errorMessage.put("出價金額應至少為"+(bidCurrentWinnerPrice+bidPriceIncrement));
//				}

				
				if (memNo.equals(bidCurrentWinnerNo)) {
					System.out.println("已為目前最高價者，出價失敗");
//					errorMsgs.add("已為目前最高價者，出價失敗");
					errorMessage.put("已為目前最高價者，出價失敗");
				}

				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidProdNo(bidProdNo);
				bidRecordVO.setMemNo(memNo);
				bidRecordVO.setBidPrice(bidPrice);
				bidRecordVO.setBidTime(bidTime);
				
				// Send the use back to the form, if there were errors
				if (errorMessage.length() != 0) {
					
					req.setAttribute("bidRecordVO", bidRecordVO); // 含有輸入格式錯誤的bidRecordVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/bid/listOneBid.html?"+bidProdNo);
//					failureView.forward(req, res);
//					res.sendRedirect(prodPageURI);
					res.setContentType("application/json; charset=utf-8");
					try {
						PrintWriter pw = res.getWriter();
//						pw.append("false");
//						pw.append(errorMsgs.toString());
//						JSONArray result = new JSONArray(errorMessage);
//						result.put(errorMessage);
						pw.print(errorMessage);
//						res.sendRedirect(prodPageURI);
//						pw.append(result);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				
				bidRecordVO = bidRecordSvc.addBidRecord(bidProdNo, memNo, bidPrice, bidTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = req.getContextPath()+"/frontend/bid/listOneBid.html?bidProdNo="+bidProdNo;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
//				successView.forward(req, res);
//				res.sendRedirect(url);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/bid/listAllBid.html");
//				failureView.forward(req, res);
//				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"https://www.google.com/");
			}
			
		}
		

		if ("delete".equals(action)) { // 來自listAllBid.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer bidRecordNo = new Integer(req.getParameter("bidRecordNo"));
				
				/***************************2.開始刪除資料***************************************/
				BidRecordService bidRecordSvc = new BidRecordService();
				bidRecordSvc.deleteBidRecord(bidRecordNo);
				
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
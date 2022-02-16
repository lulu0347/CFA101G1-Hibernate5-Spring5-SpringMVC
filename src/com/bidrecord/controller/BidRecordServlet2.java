package com.bidrecord.controller;

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

import org.json.JSONArray;
import org.json.JSONObject;

import com.bid.model.*;
import com.bidrecord.model.*;

/**
 * Servlet implementation class BidRecordServlet
 */
@WebServlet("/bidrecord/BidRecordServlet2")
public class BidRecordServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded";
	Connection con;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidRecordServlet2() {
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
		
		System.out.println(action);
		
		// 專用於處理後台顯示競標出價紀錄
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
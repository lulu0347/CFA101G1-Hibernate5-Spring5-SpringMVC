package com.itemIOD.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itemIOD.model.*;
import com.member.model.MemberService;


public class ItemIODServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//-------------會員查詢自己的訂單列表-----------------------------------------		
		if ("viewMyOrder".equals(action)) {
			
			Integer memNo = new Integer(req.getParameter("memNo"));
		
			ItemIODService itemIODSvc = new ItemIODService();
			List<ItemIODVO> list = new ArrayList<ItemIODVO>();
			list = itemIODSvc.getOrderByMemNo(memNo);
			req.setAttribute("list", list);
			String url = "/frontend/listAllOrderByMemNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
//-------------會員取消訂單-----------------------------------------				
		if ("usercancel".equals(action)) {
			
			
			//這邊是更新取消訂單後會員的餘額
			Integer eCash = new Integer(req.getParameter("eCash"));
			
			Integer orderTotal = new Integer(req.getParameter("orderTotal"));
			
			Integer memNo = new Integer(req.getParameter("memNo"));
			
			Integer renewEcash = orderTotal + eCash;
			
			Integer orderNo = new Integer(req.getParameter("orderNo"));
			
			ItemIODService itemIODSvc = new ItemIODService();
			itemIODSvc.CancelOrder(orderNo);
			
			MemberService MemSvc = new MemberService();
			
			MemSvc.updateEcash(memNo, renewEcash);
			
			String url = "/frontend/listAllOrderByMemNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}	
//-------------會員訂單退貨-----------------------------------------				
		if ("userReturn".equals(action)) {
			
			
			//這邊是更新取消訂單後會員的餘額
			Integer eCash = new Integer(req.getParameter("eCash"));
			
			Integer orderTotal = new Integer(req.getParameter("orderTotal"));
			
			Integer memNo = new Integer(req.getParameter("memNo"));
			
			Integer renewEcash = orderTotal + eCash;
			
			Integer orderNo = new Integer(req.getParameter("orderNo"));
			
			ItemIODService itemIODSvc = new ItemIODService();
			itemIODSvc.ReturnOrder(orderNo);
			
			MemberService MemSvc = new MemberService();
			
			MemSvc.updateEcash(memNo, renewEcash);
			
			String url = "/frontend/listAllOrderByMemNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}	
//-------------會員訂單收貨-----------------------------------------				
		if ("userReceipt".equals(action)) {
			
			
			Integer memNo = new Integer(req.getParameter("memNo"));	
			
			Integer orderNo = new Integer(req.getParameter("orderNo"));
			
			ItemIODService itemIODSvc = new ItemIODService();
			itemIODSvc.ReceiptOrder(orderNo);
			
			String url = "/frontend/listAllOrderByMemNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}	
//-------------會員查詢自己的訂單明細列表-----------------------------------------	
		if ("viewMyDetail".equals(action)) {
			
			Integer orderNo = new Integer(req.getParameter("orderNo"));
		
			ItemIODService itemIODSvc = new ItemIODService();
			List<ItemIODVO> list = new ArrayList<ItemIODVO>();
			list = itemIODSvc.getDetailByOrderNo(orderNo);
			req.setAttribute("list", list);
			String url = "/frontend/listAllDetailByOrderNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
//-------------後台取消訂單-----------------------------------------				
				if ("admincancel".equals(action)) {		
					Integer orderNo = new Integer(req.getParameter("orderNo"));
					ItemIODService itemIODSvc = new ItemIODService();
					itemIODSvc.CancelOrder(orderNo);
					String url = "/backend/itemOrder/listAllOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}	
//-------------後台訂單出貨-----------------------------------------				
				if ("adminShipped".equals(action)) {		
					Integer orderNo = new Integer(req.getParameter("orderNo"));
					ItemIODService itemIODSvc = new ItemIODService();
					itemIODSvc.ShippedOrder(orderNo);
					String url = "/backend/itemOrder/listAllOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}	
//---------修改要在這之上---------------		
	}
}

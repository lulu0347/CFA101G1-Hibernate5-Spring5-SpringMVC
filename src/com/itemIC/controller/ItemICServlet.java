package com.itemIC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itemIC.model.*;
import com.member.model.MemberService;



public class ItemICServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//------讓會員搜尋收藏列表且查看時能同時顯示出商品資料------------------		
		if ("viewMyCollection".equals(action)) {
			
			Integer memNo = new Integer(req.getParameter("memNo"));
			System.out.println(memNo);
			ItemICService itemICSvc = new ItemICService();
			List<ItemICVO> list = new ArrayList<ItemICVO>();
			list = itemICSvc.getCollectionByMemNo(memNo);
			System.out.println("已取得list");
			req.setAttribute("list", list);
			System.out.println("Set  -  Success");
			String url = "/frontend/listAllCollectionByMemNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
//---------修改要在這之上---------------		
	}	
}

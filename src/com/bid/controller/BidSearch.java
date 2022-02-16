package com.bid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bid.model.BidService;
import com.bid.model.BidVO;

/**
 * Servlet implementation class BidSearch
 */
@WebServlet("/BidSearch")
public class BidSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
//		PrintWriter pw = response.getWriter();
		StringBuilder sb = new StringBuilder();
		String action = null;
		action = request.getParameter("action");
		if ("search".equals(action)) {
			
			List<BidVO> list = new ArrayList<BidVO>();
			
			try {
				
				String searchKeyword = null;
				try {
					searchKeyword = request.getParameter("searchbar2").trim();
				} catch (NullPointerException ne) {
					ne.getMessage();
				}
				
				
				BidService bidSvc = new BidService();
				list = bidSvc.findByKeyword(searchKeyword);
				
				request.setAttribute("searchResult", list);
//				JSONArray json = new JSONArray(list);
//				pw.print(json);
				
				String url = "/frontend/bid/listAllBid2.jsp" + "?search=" + searchKeyword;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
			
		}
//	    response.sendRedirect(request.getContextPath()+"/frontend/bid/listAllBid.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.admini.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.admini.model.AdminiService;
import com.admini.model.AdminiVO;

@WebServlet("/backend/Logout")
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.***************************************/
			req.getSession().setAttribute("adminiVO", "");
			
			/***************************2.***************************************/
			req.getSession().invalidate();				
			/***************************3.(Send the Success view)***********/								
			String url = "/backend/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

			/****************************************************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/login.jsp");
			failureView.forward(req, res);
		}
	}
}

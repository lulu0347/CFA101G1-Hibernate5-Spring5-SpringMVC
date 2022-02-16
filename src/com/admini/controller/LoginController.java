package com.admini.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.admini.model.AdminiService;
import com.admini.model.AdminiVO;

public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		AdminiVO admini = (AdminiVO) req.getSession().getAttribute("adminiVO");
//		if(admini != null) {
//			String url = "/backend/item.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//		}
		String adminAccount = req.getParameter("adminAccount");
		String adminPassword = req.getParameter("adminPassword");
		if (adminAccount!= null && adminPassword != null) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				AdminiService adminiSvc = new AdminiService();
				AdminiVO adminiVO = adminiSvc.getAccount(adminAccount);
				if (adminiVO == null) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/login.jsp");
					failureView.forward(req, res);
					return;
				}
				

				if (!adminiVO.getAdminPassword().equals(adminPassword)) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************3.(Send the Success view)*************/
				req.getSession().setAttribute("adminiVO", adminiVO);
				req.getSession().setMaxInactiveInterval(7200);
				String url = "/backend/item.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

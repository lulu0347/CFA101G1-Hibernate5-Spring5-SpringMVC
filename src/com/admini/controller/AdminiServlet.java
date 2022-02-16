package com.admini.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.google.gson.Gson;
@WebServlet("/AdminiServlet")
public class AdminiServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setCharacterEncoding("UTF-8");
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				String str = req.getParameter("adminNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("員工編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admini/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer adminNo = null;
				try {
					adminNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AdminiService adminiSvc = new AdminiService();
				AdminiVO adminiVO = adminiSvc.getOneAdmini(adminNo);
				if (adminiVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/admini/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("adminiVO", adminiVO); 
				String url = "/backend/admini/listOneAdmini.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(adminiVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/admini/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.****************************************/
				Integer adminNo = new Integer(req.getParameter("adminNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				AdminiService adminiSvc = new AdminiService();
				AdminiVO adminiVO = adminiSvc.getOneAdmini(adminNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("adminiVO", adminiVO);         
				String url = "/backend/admini/update_admini_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/admini/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer adminNo = new Integer(req.getParameter("adminNo").trim());
				
				String adminName = req.getParameter("adminName");
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!adminName.trim().matches(adminNameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String adminAccount = req.getParameter("adminAccount").trim();
				if (adminAccount == null || adminAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}
				
				String adminPassword = req.getParameter("adminPassword").trim();
				if (adminPassword == null || adminPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String adminPhone = req.getParameter("adminPhone").trim();
				if (adminPhone == null || adminPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				AdminiVO adminiVO = new AdminiVO();
				adminiVO.setAdminNo(adminNo);
				adminiVO.setAdminAccount(adminAccount);
				adminiVO.setAdminPassword(adminPassword);
				adminiVO.setAdminName(adminName);
				adminiVO.setAdminName(adminPhone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminiVO", adminiVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/admini/update_admini_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AdminiService adminiSvc = new AdminiService();
				adminiVO = adminiSvc.updateAdmini(adminNo, adminAccount, adminPassword, adminName, adminPhone);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("adminiVO", adminiVO); 
				String url = "/backend/admini/listOneAdmini.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/admini/update_admini_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String adminName = req.getParameter("adminName");
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!adminName.trim().matches(adminNameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				AdminiVO adminiVO = new AdminiVO();
				String adminAccount = req.getParameter("adminAccount").trim();
				AdminiService adminiSvc = new AdminiService();
				
				if (adminAccount == null || adminAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}
				
				String adminPassword = req.getParameter("adminPassword").trim();
				if (adminPassword == null || adminPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String adminPhone = req.getParameter("adminPhone").trim();
				if (adminPhone == null || adminPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				adminiVO.setAdminAccount(adminAccount);
				adminiVO.setAdminPassword(adminPassword);
				adminiVO.setAdminName(adminName);
				adminiVO.setAdminName(adminPhone);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminiVO", adminiVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/admini/addAdmini.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				adminiVO = adminiSvc.addAdmini(adminAccount, adminPassword, adminName, adminPhone);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("adminiVO", adminiVO); 
				String url = "/backend/admini/listOneAdmini.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
//				Gson gson = new Gson();
//				String json = gson.toJson(adminiVO);
//				res.getWriter().print(json);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/admini/update_admini_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer adminNo = new Integer(req.getParameter("adminNo"));
				
				/***************************2.�}�l�R�����***************************************/
				AdminiService adminiSvc = new AdminiService();
				adminiSvc.deleteAdmini(adminNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/admini/listAllAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/admini/update_admini_input.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("check".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String adminAccount = req.getParameter("adminAccount").trim();
				AdminiService adminiSvc = new AdminiService();
				AdminiVO adminiVO = adminiSvc.getAccount(adminAccount);
				if(adminiVO!=null) {
					errorMsgs.add("Account has been used!");
					Gson gson = new Gson();
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
				}else {
					errorMsgs.add("");
					Gson gson = new Gson();
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
				}
			} catch (Exception e) {
				errorMsgs.add("something error");
				Gson gson = new Gson();
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
	}
}

package com.adminauth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminauth.model.AdminAuthService;
import com.adminauth.model.AdminAuthVO;
import com.admini.model.AdminiService;
import com.admini.model.AdminiVO;

public class AdminAuthServlet extends HttpServlet {
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
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				String str = req.getParameter("adminAuthrizationNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("員工編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuth/select_page.jsp");
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
							.getRequestDispatcher("/adminAuth/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				AdminAuthService adminAuthSvc = new AdminAuthService();
				List<AdminAuthVO> adminAuthList = new LinkedList<AdminAuthVO>();
				adminAuthList = adminAuthSvc.getPersonByAdminAuthrization(adminNo);
				
				if (adminAuthList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuth/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("adminAuthList", adminAuthList); 
				String url = "/backend/adminAuth/listOneAdminAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuth/select_page.jsp");
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
				req.setAttribute("adminiVO", adminiVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/admini/update_admini_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admini/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.**********************/
				Integer adminNo = new Integer(req.getParameter("adminNo").trim());
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo").trim());
				System.out.println(adminNo+"1");
				System.out.println(adminAuthrizationNo+"1");
				AdminAuthVO adminAuthVO = new AdminAuthVO();
				adminAuthVO.setAdminNo(adminNo);
				adminAuthVO.setAdminNo(adminAuthrizationNo);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminAuthVO", adminAuthVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminiAuth/update_admini_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AdminAuthService adminAuthSvc = new AdminAuthService();
				AdminAuthVO adminiAuthVO = adminAuthSvc.updateAdminAuth(adminNo, adminAuthrizationNo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("adminAuthVO", adminAuthVO); 
				String url = "/backend/adminAuth/listOneAdminAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admini/update_admini_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.**********************/
				Integer adminNo = new Integer(req.getParameter("adminNo").trim());
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo").trim());
				AdminAuthVO adminAuthVO = new AdminAuthVO();
				adminAuthVO.setAdminNo(adminNo);
				adminAuthVO.setAdminAuthrizationNo(adminAuthrizationNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminAuthVO", adminAuthVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/adminiAuth/listAllAdminAuth.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.�}�l�s�W���***************************************/
				AdminAuthService adminAuthSvc = new AdminAuthService();
				adminAuthSvc.insert(adminAuthVO);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("adminAuthVO", adminAuthVO); 
				String url = "/backend/adminAuth/listOneAdminAuthByAdminNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("員工權限已存在,權限新增失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/adminAuth/listAllAdminAuth.jsp");
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
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo"));
				
				/***************************2.�}�l�R�����***************************************/
				AdminAuthService adminAuthSvc = new AdminAuthService();
				adminAuthSvc.delete(adminNo,adminAuthrizationNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/backend/adminAuth/listAllAdminAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuth/listAllAdminAuth.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

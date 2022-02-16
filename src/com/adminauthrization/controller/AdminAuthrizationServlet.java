package com.adminauthrization.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminauthrization.model.AdminAuthrizationService;
import com.adminauthrization.model.AdminAuthrizationVO;



@WebServlet("/AdminAuthrizationServlet")
public class AdminAuthrizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
					errorMsgs.add("權限功能編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuthrization/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer adminAuthrizationNo = null;
				try {
					adminAuthrizationNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("權限功能編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuthrization/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
				AdminAuthrizationVO adminAuthrizationVO = adminAuthrizationSvc.getOneAdminAuthrization(adminAuthrizationNo);
				if (adminAuthrizationVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuthrization/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("adminAuthrizationVO", adminAuthrizationVO); 
				String url = "/backend/adminAuthrization/listOneAdminAuthrization.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuthrization/select_page.jsp");
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
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo"));
				
				/***************************2.****************************************/
				AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
				AdminAuthrizationVO adminAuthrizationVO = adminAuthrizationSvc.getOneAdminAuthrization(adminAuthrizationNo);
								
				/***************************3.(Send the Success view)************/
				req.setAttribute("adminAuthrizationVO", adminAuthrizationVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/adminAuthrization/update_adminAuthrization_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuthrization/listAllAdminAuthrization.jsp");
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
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo").trim());
				
				String adminAuthrizationName = req.getParameter("adminAuthrizationName");
				String adminAuthrizationNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminAuthrizationName == null || adminAuthrizationName.trim().length() == 0) {
					errorMsgs.add("功能權限名稱: 請勿空白");
				} else if(!adminAuthrizationName.trim().matches(adminAuthrizationNameReg)) { 
					errorMsgs.add("功能權限名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
						
				
				AdminAuthrizationVO adminAuthrizationVO = new AdminAuthrizationVO();
				adminAuthrizationVO.setAdminAuthrizationNo(adminAuthrizationNo);
				adminAuthrizationVO.setAdminAuthrizationName(adminAuthrizationName);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminAuthrizationVO", adminAuthrizationVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adminAuthrization/update_adminAuthrization_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.**************************************/
				AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
				adminAuthrizationVO = adminAuthrizationSvc.updateAdminAuthrization(adminAuthrizationNo, adminAuthrizationName);
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("adminAuthrizationVO", adminAuthrizationVO); 
				String url = "/backend/adminAuthrization/listOneAdminAuthrization.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuthrization/update_adminAuthrization_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.*************************/
				String adminAuthrizationName = req.getParameter("adminAuthrizationName");
				String adminAuthrizationNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminAuthrizationName == null || adminAuthrizationName.trim().length() == 0) {
					errorMsgs.add("權限功能名稱: 請勿空白");
				} else if(!adminAuthrizationName.trim().matches(adminAuthrizationNameReg)) { 
					errorMsgs.add("權限功能名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				
				AdminAuthrizationVO adminAuthrizationVO = new AdminAuthrizationVO();

				adminAuthrizationVO.setAdminAuthrizationName(adminAuthrizationName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminAuthrizationVO", adminAuthrizationVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/adminAuthrization/listAllAdminAuthrization.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.***************************************/
				AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
				adminAuthrizationVO = adminAuthrizationSvc.insertAdminAuthrization(adminAuthrizationName);
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("adminAuthrizationVO", adminAuthrizationVO); 
				String url = "/backend/adminAuthrization/listAllAdminAuthrization.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/adminAuthrization/update_adminAuthrization_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.***************************************/
				Integer adminAuthrizationNo = new Integer(req.getParameter("adminAuthrizationNo"));
				
				/***************************2.***************************************/
				AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
				adminAuthrizationSvc.deleteAdminAuthrization(adminAuthrizationNo);
				
				/***************************3.(Send the Success view)***********/								
				String url = "/backend/adminAuthrization/listAllAdminAuthrization.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adminAuthrization/update_adminAuthrization_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
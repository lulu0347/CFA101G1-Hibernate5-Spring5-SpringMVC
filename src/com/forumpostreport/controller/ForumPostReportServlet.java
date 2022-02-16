package com.forumpostreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.forumpostreport.model.ForumPostReportService;
import com.forumpostreport.model.ForumPostReportVO;
import com.google.gson.Gson;


public class ForumPostReportServlet extends HttpServlet {
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

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				String str = req.getParameter("forumPostReportNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("文章檢舉編號編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer forumPostReportNo = null;
				try {
					forumPostReportNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				ForumPostReportVO forumPostReportVO = forumPostReportSvc.getOnePost(forumPostReportNo);
				if (forumPostReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumPostReportVO", forumPostReportVO); 
				String url = "/forumPostReport/listOneForumPost.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(forumPostReportVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostReport/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneMem_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("員工編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				List<ForumPostReportVO> list = new LinkedList<ForumPostReportVO>();
				list = forumPostReportSvc.getMemPostReport(memNo);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/forumPostReport/listOneForumPostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostReport/select_page.jsp");
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
				Integer forumPostReportNo = new Integer(req.getParameter("forumPostReportNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				ForumPostReportVO forumPostReportVO = forumPostReportSvc.getOnePost(forumPostReportNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("forumPostReportVO", forumPostReportVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/forumPostReport/update_ForumPostReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				String uri = req.getContextPath();
				res.sendRedirect(uri + "/backend/frontend.jsp");
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/frontend.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer forumPostReportNo = new Integer(req.getParameter("forumPostReportNo").trim());
				
				
				Integer forumPostNo = null;
				try {
					forumPostNo = new Integer(req.getParameter("forumPostNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				
				
				java.sql.Date forumPostReportTime = null;
				try {
					forumPostReportTime = java.sql.Date.valueOf(req.getParameter("forumPostReportTime").trim());
					
				} catch (IllegalArgumentException e) {
					forumPostReportTime =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("發文時間請勿空白!");
				}

				String forumPostReportWhy = req.getParameter("forumPostReportWhy").trim();
				
				if (forumPostReportWhy == null || forumPostReportWhy.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				Integer forumPostReportType = null;
				try {					
					forumPostReportType = Integer.parseInt(req.getParameter("forumPostReportType"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態錯誤");
				}
				
				ForumPostReportVO forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostReportNo(forumPostNo);
				forumPostReportVO.setForumPostNo(forumPostNo);
				forumPostReportVO.setMemNo(memNo);
				forumPostReportVO.setForumPostReportTime(forumPostReportTime);
				forumPostReportVO.setForumPostReportWhy(forumPostReportWhy);
				forumPostReportVO.setForumPostReportType(forumPostReportType);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostReportVO", forumPostReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/update_ForumPostReport_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.*****************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				forumPostReportVO = forumPostReportSvc.updatePost(forumPostReportNo, memNo, forumPostNo, forumPostReportTime, forumPostReportWhy, forumPostReportType);
				
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumPostReportVO", forumPostReportVO); 
				String url = "/forumPostReport/listUpdateForumPostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostReport/update_ForumPostReport_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.*********************/
				Integer forumPostNo = null;
				try {
					forumPostNo = new Integer(req.getParameter("forumPostNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				
				
				java.sql.Date forumPostReportTime = null;
				try {
					forumPostReportTime = java.sql.Date.valueOf(req.getParameter("forumPostReportTime").trim());
					
				} catch (IllegalArgumentException e) {
					forumPostReportTime =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("發文時間請勿空白!");
				}

				String forumPostReportWhy = req.getParameter("forumPostReportWhy").trim();
				
				if (forumPostReportWhy == null || forumPostReportWhy.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				Integer forumPostReportType = null;
				try {					
					forumPostReportType = Integer.parseInt(req.getParameter("forumPostReportType"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態錯誤");
				}
				
				ForumPostReportVO forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostNo(forumPostNo);
				forumPostReportVO.setMemNo(memNo);
				forumPostReportVO.setForumPostReportTime(forumPostReportTime);
				forumPostReportVO.setForumPostReportWhy(forumPostReportWhy);
				forumPostReportVO.setForumPostReportType(forumPostReportType);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostVO", forumPostReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/update_FourmPostReport_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				/***************************2.�}�l�s�W���***************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				forumPostReportVO = forumPostReportSvc.addPostReport(memNo, forumPostNo, forumPostReportTime, forumPostReportWhy, forumPostReportType);
				
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("forumPostReportVO", forumPostReportVO); 
//				String url = "/admini/listOneAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				Gson gson = new Gson();
				String json = gson.toJson(forumPostReportVO);
				res.getWriter().print(json);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostReport/update_FourmPostReport_input.jsp");
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
				Integer forumPostReportNo = new Integer(req.getParameter("forumPostReportNo"));
				
				/***************************2.�}�l�R�����***************************************/
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				forumPostReportSvc.deletePost(forumPostReportNo);;
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/forumPostReport/listAllForumPostReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				String uri = req.getContextPath();
				res.sendRedirect(uri + "/backend/frontend.jsp");
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(uri+"/forumPostReport/update_FourmPost_input.jsp");
//				failureView.forward(req, res);
			}
		}
	}
}

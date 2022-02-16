package com.forummsgreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forummsgreport.model.ForumMsgReportService;
import com.forummsgreport.model.ForumMsgReportVO;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.forumpostreport.model.ForumPostReportService;
import com.forumpostreport.model.ForumPostReportVO;
import com.google.gson.Gson;


public class ForumMsgReportServlet extends HttpServlet {
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
				String str = req.getParameter("forumMsgReportNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("文章檢舉編號編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer forumMsgReportNo = null;
				try {
					forumMsgReportNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
				ForumMsgReportVO forumMsgReportVO = forumMsgReportSvc.getOnePost(forumMsgReportNo);
				if (forumMsgReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumMsgReportVO", forumMsgReportVO); 
				String url = "/forumMsgReport/listOneForumMsg.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(forumMsgReportVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/select_page.jsp");
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
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
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
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
				List<ForumMsgReportVO> list = new LinkedList<ForumMsgReportVO>();
				list = forumMsgReportSvc.getAll(memNo);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsgReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/forumMsgReport/listOneForumMsgReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/select_page.jsp");
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
				Integer forumMsgReportNo = new Integer(req.getParameter("forumMsgReportNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
				ForumMsgReportVO forumMsgReportVO = forumMsgReportSvc.getOnePost(forumMsgReportNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("forumMsgReportVO", forumMsgReportVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/forumMsgReport/update_ForumMsgReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/select_page.jsp");
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
				Integer forumMsgReportNo = new Integer(req.getParameter("forumMsgReportNo").trim());
				
				
				Integer forumMsgNo = null;
				try {
					forumMsgNo = new Integer(req.getParameter("forumMsgNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("留言編號型別錯誤");
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				
				
				java.sql.Date forumMsgReportTime = null;
				try {
					forumMsgReportTime = java.sql.Date.valueOf(req.getParameter("forumMsgPostReportTime").trim());
					
				} catch (IllegalArgumentException e) {
					forumMsgReportTime =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("發文時間請勿空白!");
				}

				String forumMsgReportWhy = req.getParameter("forumMsgReportWhy").trim();
				
				if (forumMsgReportWhy == null || forumMsgReportWhy.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				Integer forumMsgReportType = null;
				try {					
					forumMsgReportType = Integer.parseInt(req.getParameter("forumPostReportType"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態錯誤");
				}
				
				ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(forumMsgReportNo);
				forumMsgReportVO.setForumMsgNo(forumMsgNo);
				forumMsgReportVO.setMemNo(memNo);
				forumMsgReportVO.setForumMsgReportTime(forumMsgReportTime);
				forumMsgReportVO.setForumMsgReportWhy(forumMsgReportWhy);
				forumMsgReportVO.setForumMsgReportType(forumMsgReportType);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumMsgReportVO", forumMsgReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsgReport/update_ForumMsgReport_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.*****************************************/
				ForumMsgReportService foruMsgReportSvc = new ForumMsgReportService();
				forumMsgReportVO = foruMsgReportSvc.updatePost(forumMsgReportNo, memNo, forumMsgNo, forumMsgReportTime, forumMsgReportWhy, forumMsgReportType);
				
				
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumMsgReportVO", forumMsgReportVO); 
				String url = "/forumMsgReport/listUpdateForumMsgReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/update_ForumMsgReport_input.jsp");
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
				Integer forumMsgNo = null;
				try {
					forumMsgNo = new Integer(req.getParameter("forumMsgNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				Integer memNo = null;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				
				
				java.sql.Date forumMsgReportTime = null;
				try {
					forumMsgReportTime = java.sql.Date.valueOf(req.getParameter("forumMsgPostReportTime").trim());
					
				} catch (IllegalArgumentException e) {
					forumMsgReportTime =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("發文時間請勿空白!");
				}

				String forumMsgReportWhy = req.getParameter("forumMsgReportWhy").trim();
				
				if (forumMsgReportWhy == null || forumMsgReportWhy.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				Integer forumMsgReportType = null;
				try {					
					forumMsgReportType = Integer.parseInt(req.getParameter("forumPostReportType"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態錯誤");
				}
				
				ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgNo(forumMsgNo);
				forumMsgReportVO.setMemNo(memNo);
				forumMsgReportVO.setForumMsgReportTime(forumMsgReportTime);
				forumMsgReportVO.setForumMsgReportWhy(forumMsgReportWhy);
				forumMsgReportVO.setForumMsgReportType(forumMsgReportType);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumMsgReportVO", forumMsgReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostReport/update_FourmPostReport_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				/***************************2.�}�l�s�W���***************************************/
				ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
				forumMsgReportVO = forumMsgReportSvc.addPost(memNo, forumMsgNo, forumMsgReportTime, forumMsgReportWhy, forumMsgReportType);
						
				
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("forumMsgReportVO", forumMsgReportVO); 
//				String url = "/admini/listOneAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				Gson gson = new Gson();
				String json = gson.toJson(forumMsgReportVO);
				res.getWriter().print(json);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/update_FourmMsgReport_input.jsp");
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
				Integer forumMsgReportNo = new Integer(req.getParameter("forumMsgReportNo"));
				
				/***************************2.�}�l�R�����***************************************/
				ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
				forumMsgReportSvc.deletePost(forumMsgReportNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/forumMsgReport/listAllForumMsgReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsgReport/update_FourmMsgReport_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

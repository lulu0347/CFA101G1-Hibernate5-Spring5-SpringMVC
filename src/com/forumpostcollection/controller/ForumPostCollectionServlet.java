package com.forumpostcollection.controller;

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
import com.forumpostcollection.model.ForumPostCollectionService;
import com.forumpostcollection.model.ForumPostCollectionVO;
import com.google.gson.Gson;


public class ForumPostCollectionServlet extends HttpServlet {
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
				String str = req.getParameter("memNo");
				String fpn = req.getParameter("forumPostNo");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				if (fpn == null || (str.trim()).length() == 0) {
					errorMsgs.add("文章編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer memNo = null;
				Integer forumPostNo = null;
				try {
					memNo = new Integer(str);
					forumPostNo = new Integer(fpn);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
				ForumPostCollectionVO forumPostCollectionVO = forumPostCollectionSvc.getOnePostCollection(memNo, forumPostNo);
				if (forumPostCollectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumPostCollectionVO", forumPostCollectionVO); 
				String url = "/forumPostCollection/listOneForumPost.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(forumPostCollectionVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostCollection/select_page.jsp");
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
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
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
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
				List<ForumPostCollectionVO> list = new LinkedList<ForumPostCollectionVO>();
				list = forumPostCollectionSvc.getMemPostReportCollection(memNo);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostCollection/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/forumPostCollection/listOneForumPostCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostCollection/select_page.jsp");
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
				Integer memNo = null;
				try {
					memNo = new Integer(req.getParameter("memNo").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號型別錯誤");
				}
				
				Integer forumPostNo = null;
				try {
					forumPostNo = new Integer(req.getParameter("forumPostNo").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("文章編號型別錯誤");
				}
				


				
				ForumPostCollectionVO forumPostCollectionVO = new ForumPostCollectionVO();
				forumPostCollectionVO.setMemNo(memNo);
				forumPostCollectionVO.setForumPostNo(forumPostNo);;


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostCollectionVO", forumPostCollectionVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPostCollection/update_ForumPostCollection_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				/***************************2.�}�l�s�W���***************************************/
				ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
				forumPostCollectionVO = forumPostCollectionSvc.addPostCollection(memNo, forumPostNo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("forumPostCollectionVO", forumPostCollectionVO); 
//				String url = "/admini/listOneAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				Gson gson = new Gson();
				String json = gson.toJson(forumPostCollectionVO);
				res.getWriter().print(json);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostCollection/update_ForumPostCollection_input.jsp");
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
				Integer memNo = new Integer(req.getParameter("memNo"));
				Integer forumPostNo = new Integer(req.getParameter("forumPostNo"));
				
				/***************************2.***************************************/
				ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
				forumPostCollectionSvc.deletePostCollection(memNo, forumPostNo);
				
				
				/***************************3.(Send the Success view)***********/								
//				String url = "/admini/listAllAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPostCollection/update_ForumPostCollection_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

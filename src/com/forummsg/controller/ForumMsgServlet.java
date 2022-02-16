package com.forummsg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forummsg.model.ForumMsgService;
import com.forummsg.model.ForumMsgVO;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.google.gson.Gson;


public class ForumMsgServlet extends HttpServlet {
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

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("回應編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer forumMsgNo = null;
				try {
					forumMsgNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("回應編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				ForumMsgVO forumMsgVO = forumMsgSvc.getOneMsg(forumMsgNo);
				if (forumMsgVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumMsgVO", forumMsgVO); 
				String url = "/backend/forumMsg/listOneForumMsg.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(forumMsgVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/forumMsg/select_page.jsp");
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
				String str = req.getParameter("commentator");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer commentator = null;
				try {
					commentator = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				List<ForumMsgVO> list = new LinkedList<ForumMsgVO>();
				list = forumMsgSvc.getMemMsg(commentator);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumMsg/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String url = "/backend/forumMsg/listOneForumMsg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsg/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getComment".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("問題編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumMsg/select_page.jsp");
//					failureView.forward(req, res);
//					return;
				}
				
				Integer parentId = null;
				try {
					parentId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumMsg/select_page.jsp");
//					failureView.forward(req, res);
//					return;
				}
				
				/***************************2.*****************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				List<ForumMsgVO> list = new LinkedList<ForumMsgVO>();
				list = forumMsgSvc.getPostMsg(parentId);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumMsg/select_page.jsp");
//					failureView.forward(req, res);
//					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String json = gson.toJson(list);
				res.getWriter().print(json);
//				String url = "/forumMsg/listOneForumMsg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/forumMsg/select_page.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.****************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.****************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				ForumMsgVO forumMsgVO = forumMsgSvc.getOneMsg(id);
								
				/***************************3.(Send the Success view)************/
				req.setAttribute("forumMsgVO", forumMsgVO);
				String url = "/backend/forumMsg/update_ForumMsg_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsg/select_page.jsp");
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
				Integer id = new Integer(req.getParameter("id").trim());
								
			    Integer parentId = null;
				try {
					parentId = new Integer(req.getParameter("parentId").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章編號型別錯誤");
				}
				
				String content = req.getParameter("content").trim();
				
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Integer type = null;
				try {
					type = new Integer(req.getParameter("type").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("類別型別錯誤");
				}
				
				Integer commentator = null;
				try {					
					commentator = Integer.parseInt(req.getParameter("memNo"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("會員型別錯誤");
				}
				
				Long gmtCreate = null;
				try {					
					gmtCreate = Long.parseLong(req.getParameter("gmtCreate"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("建立日期型別錯誤");
				}
				
				Long gmtModified = null;
				try {					
					gmtModified = Long.parseLong(req.getParameter("gmtModified"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("修改日期型別錯誤");
				}
				
				String forumMsg = req.getParameter("forumMsg").trim();
				
				if (forumMsg == null || forumMsg.trim().length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}
				
				
				Integer likeCount = null;
				try {					
					likeCount = Integer.parseInt(req.getParameter("likeCount"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("按讚數狀態錯誤");
				}
				
				Integer commentCount = null;
				try {					
					commentCount = Integer.parseInt(req.getParameter("commentCount"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("留言數狀態錯誤");
				}
				
				ForumMsgVO forumMsgVO = new ForumMsgVO();
				forumMsgVO.setId(id);
				forumMsgVO.setParentId(parentId);
				forumMsgVO.setContent(content);
				forumMsgVO.setType(type);
				forumMsgVO.setCommentator(commentator);
				forumMsgVO.setGmtCreate(gmtCreate);
				forumMsgVO.setGmtModified(gmtModified);
				forumMsgVO.setLikeCount(likeCount);
				forumMsgVO.setCommentCount(commentCount);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumMsgVO", forumMsgVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/forumPost/update_ForumPost_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.*****************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				forumMsgVO = forumMsgSvc.updateMsg(id, parentId, content, type, commentator, gmtCreate, gmtModified, likeCount, commentCount);
								
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumMsgVO", forumMsgVO); 
				String url = "/backend/forumMsg/listUpdateForumMsg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumMsg/update_ForumMsg_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
			
			    Integer parentId = null;
				try {
					parentId = new Integer(req.getParameter("parentId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("文章編號型別錯誤");
				}
				
				String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Integer type = null;
				try {
					type = new Integer(req.getParameter("type").trim());
					
				} catch (NumberFormatException e) {
					errorMsgs.add("類別型別錯誤");
				}
				
				Integer commentator = null;
				try {					
					commentator = Integer.parseInt(req.getParameter("memNo"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("會員型別錯誤");
				}
				
				ForumMsgVO forumMsgVO = new ForumMsgVO();
				forumMsgVO.setParentId(parentId);
				forumMsgVO.setContent(content);
				forumMsgVO.setType(type);
				forumMsgVO.setCommentator(commentator);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return; 
				}
				/***************************2.�}�l�s�W���***************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				forumMsgSvc.addMsg(parentId, content, type, commentator);				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				res.getWriter().print(1);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.**************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.***************************************/
				ForumMsgService forumMsgSvc = new ForumMsgService();
				forumMsgSvc.deleteMsg(id);
				
				/***************************3.(Send the Success view)***********/								
//				String url = "/admini/listAllAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/forumPost/update_FourmMsg_input.jsp");
				failureView.forward(req, res);
			}
		}

	}
}

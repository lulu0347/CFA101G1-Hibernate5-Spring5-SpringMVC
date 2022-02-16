package com.notify.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.notify.model.NotifyService;
import com.notify.model.NotifyVO;
import com.google.gson.Gson;

@WebServlet("/notify/notify.do")
public class NotifyServlet extends HttpServlet {
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
					errorMsgs.add("文章編號編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer id = null;
				try {
					id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				NotifyService notifySvc = new NotifyService();
				NotifyVO notifyVO = notifySvc.getOneNotify(id);
				if (notifyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("notifyVO", notifyVO); 
				Gson gson = new Gson();
				String json = gson.toJson(notifyVO);
				res.getWriter().print(json);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPost/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneMem_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1**********************/
				String str = req.getParameter("receiver");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				Integer receiver = null;
				try {
					receiver = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************2.*****************************************/
				NotifyService notifyService = new NotifyService();
				List<NotifyVO> list = new LinkedList<NotifyVO>();
				list = notifyService.getMemPost(receiver);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String json = gson.toJson(list);
				res.getWriter().print(json);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("getZeroStatus".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1**********************/
				String str = req.getParameter("receiver");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				Integer receiver = null;
				try {
					receiver = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************2.*****************************************/
				NotifyService notifyService = new NotifyService();
				List<NotifyVO> list = new LinkedList<NotifyVO>();
				list = notifyService.getZeroStatus(receiver);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String json = gson.toJson(list);
				res.getWriter().print(json);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.****************************************/
				Integer status = 1;
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.****************************************/
				NotifyService notifyService = new NotifyService();
				notifyService.updateOne(status,id);
								
				/***************************3.(Send the Success view)************/
				res.getWriter().print(1);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("QMID".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.****************************************/
				Integer status = 1;
				Integer receiver = new Integer(req.getParameter("receiver"));
				Integer outerid = new Integer(req.getParameter("outerid"));
				
				/***************************2.****************************************/
				NotifyService notifyService = new NotifyService();
				notifyService.qmid(status,receiver,outerid);
				
				/***************************3.(Send the Success view)************/
				res.getWriter().print(1);
				
				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer receiver = new Integer(req.getParameter("receiver"));
				Integer status = new Integer(req.getParameter("status"));
				
				NotifyVO notifyVO = new NotifyVO();
				notifyVO.setStatus(status);
				notifyVO.setReceiver(receiver);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notifyVO", notifyVO); 
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************2.*****************************************/
				NotifyService notifyService = new NotifyService();
				notifyService.update(status,receiver);
				
				/***************************3.(Send the Success view)*************/
				res.getWriter().print(1);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}

        if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			NotifyVO notifyVO = new NotifyVO();
			try {
				/***********************1.*************************/
				Integer notifier = Integer.parseInt(req.getParameter("notifier").trim());
				if (notifier == null || notifier == 0) {
					errorMsgs.add("通知編號請勿空白  ");
				}
				Integer receiver = Integer.parseInt(req.getParameter("receiver").trim());
				if (receiver == null || receiver == 0) {
					errorMsgs.add("接收通知會員請勿空白  ");
				}
				Integer outerid = Integer.parseInt(req.getParameter("outerid").trim());
				if (outerid == null || outerid == 0) {
					errorMsgs.add("文章編號請勿空白  ");
				}
				Integer type = Integer.parseInt(req.getParameter("type").trim());
				if (type == null || type == 0) {
					errorMsgs.add("文章回應請勿空白  ");
				}
				Long gmtCreate = System.currentTimeMillis();
				String notifierName = req.getParameter("notifierName").trim();
				if (notifierName == null || notifierName.trim().length() == 0) {
					errorMsgs.add("通知者請勿空白  ");
				}
				String outerTitle = req.getParameter("outerTitle").trim();
				if (outerTitle == null || outerTitle.trim().length() == 0) {
					errorMsgs.add("文章標題請勿空白  ");
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notifyVO", notifyVO); 
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return; 
				}
				notifyVO.setNotifier(notifier);
				notifyVO.setNotifier_name(notifierName);
				notifyVO.setReceiver(receiver);
				notifyVO.setOuterid(outerid);
				notifyVO.setOuter_title(outerTitle);
				notifyVO.setType(type);
				notifyVO.setGmt_create(gmtCreate);

				/***************************2.***************************************/
				NotifyService notifyService = new NotifyService();
				notifyService.addPost(notifier,notifierName,receiver,outerid,outerTitle,type,gmtCreate);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
				res.getWriter().print(1);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.�}�l�R�����***************************************/
				NotifyService notifyService = new NotifyService();
				notifyService.deletePost(id);;
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String json = gson.toJson("1");
				res.getWriter().print(json);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("getAll".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			Gson gson = new Gson();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				
				/***************************2.*****************************************/
				NotifyService notifyService = new NotifyService();
				List<NotifyVO> list = new LinkedList<NotifyVO>();
				list =  notifyService.getAll();
				if (list  == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String json = gson.toJson(list);
				res.getWriter().print(json);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("getOneMem".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1**********************/
				String str = req.getParameter("receiver");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}

				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				Integer receiver = null;
				try {
					receiver = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************2.*****************************************/
				NotifyService notifyService = new NotifyService();
				List<NotifyVO> list = new LinkedList<NotifyVO>();
				list = notifyService.getMemPost(receiver);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
				String json = gson.toJson(list);
				res.getWriter().print(json);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
	}
}

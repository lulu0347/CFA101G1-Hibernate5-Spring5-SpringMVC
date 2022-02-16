package com.forumpost.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.google.gson.Gson;
public class ForumPostServlet extends HttpServlet {
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
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPostVO = forumPostSvc.getOnePost(id);
				if (forumPostVO == null) {
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
				req.setAttribute("forumPostVO", forumPostVO); 
//				String url = "/forumPost/listOneForumPost.jsp";
				Gson gson = new Gson();
				String json = gson.toJson(forumPostVO);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

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

			try {
				/***************************1**********************/
				String str = req.getParameter("creator");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer creator = null;
				try {
					creator = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				List<ForumPostVO> list = new LinkedList<ForumPostVO>();
				list = forumPostSvc.getMemPost(creator);
				
				if (list == null) {
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
				req.setAttribute("list", list); 
				String url = "/backend/forumPost/listOneForumPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/forntend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneQuestion".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			
			try {
				/***************************1**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
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
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumPost/select_page.jsp");
//					failureView.forward(req, res);
//					return;
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
				}
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				Object object = (Object)forumPostSvc.getQuestionPost(id);
				
				if (object == null) {
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
				req.setAttribute("object", object); 
				String json = gson.toJson(object);
				res.getWriter().print(json);
//				String url = "/forumPost/listOneForumPost.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/forumPost/select_page.jsp");
//				failureView.forward(req, res);
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
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
				
				/***************************2.�}�l�d�߸��****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPostVO = forumPostSvc.getOnePost(id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("forumPostVO", forumPostVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/forumPost/update_ForumPost_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPost/select_page.jsp");
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
				Integer id = new Integer(req.getParameter("id").trim());
				Integer creator = new Integer(req.getParameter("creator").trim());
				Long gmtCreate = new Long(req.getParameter("gmtCreate").trim());
				Integer commentCount = new Integer(req.getParameter("commentCount").trim());
				Integer viewCount = new Integer(req.getParameter("viewCount").trim());
				Integer likeCount = new Integer(req.getParameter("likeCount").trim());
				
				
				String title = req.getParameter("title").trim();
				
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("文章標題請勿空白");
				}
				
				String description = req.getParameter("description").trim();
				
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				String tag = req.getParameter("tags").trim();
				
				if (tag == null || tag.trim().length() == 0) {
					errorMsgs.add("文章標籤請勿空白");
				}
				
				
				Long gmtModified = null;
				try {					
					gmtModified = Long.parseLong(req.getParameter("gmtModified"));
					
				} catch (NumberFormatException e) {
					errorMsgs.add("文章修改時間錯誤");
				}
				
				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setId(id);
				forumPostVO.setCreator(creator);
				forumPostVO.setTitle(title);
				forumPostVO.setDescription(description);
				forumPostVO.setGmtCreate(gmtCreate);
				forumPostVO.setGmtModified(gmtModified);
				forumPostVO.setCommentCount(commentCount);
				forumPostVO.setViewCount(viewCount);
				forumPostVO.setLikeCount(likeCount);
				forumPostVO.setTag(tag);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostVO", forumPostVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/update_ForumPost_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostVO = forumPostSvc.updatePost(id,title,description,gmtModified,tag);
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumPostVO", forumPostVO); 
				String url = "/forumPost/listUpdateForumPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPost/update_ForumPost_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updatePostContent".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer id = new Integer(req.getParameter("id").trim());		
				String description = req.getParameter("description").trim();				
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				}
				
				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setId(id);
				forumPostVO.setDescription(description);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostVO", forumPostVO); 
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
				}
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostVO = forumPostSvc.updatePostContent(id,description);
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("forumPostVO", forumPostVO); 
				String json = gson.toJson(forumPostVO);
				res.getWriter().print(json);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}

        if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			//將json字符串轉換爲json對象
			Gson gson = new Gson();
			ForumPostVO forumPostVO = new ForumPostVO();
			try {
				/***********************1.*************************/
				Integer creator = Integer.parseInt(req.getParameter("memNo").trim());
				Long gmtCreate = System.currentTimeMillis();
				Long gmtModified = gmtCreate.longValue();
				String title = req.getParameter("title").trim();
				System.out.println(title);
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("文章標題請勿空白  ");
				}
				String description = req.getParameter("description").trim();
				System.out.println(description);
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("文章內容請勿空白  ");
				}
				String tags = req.getParameter("tags").trim();
				System.out.println(tags);
				if (tags == null || tags.trim().length() == 0) {
					errorMsgs.add("文章標籤請勿空白  ");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostVO", forumPostVO); 
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumPost/update_FourmPost_input.jsp");
//					failureView.forward(req, res);
//					return; 
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
				}
				forumPostVO.setCreator(creator);
				forumPostVO.setTitle(title);
				forumPostVO.setDescription(description);
				forumPostVO.setGmtCreate(gmtCreate);
				forumPostVO.setGmtModified(gmtModified);		
				forumPostVO.setTag(tags);			
				// Send the use back to the form, if there were errors
				/***************************2.***************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPost = forumPostSvc.addPost(title,description,gmtCreate,gmtModified,creator,tags);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("forumPostVO", forumPost); 
//				String url = "/admini/listOneAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				
				String json = gson.toJson("1");
				res.getWriter().print(json);
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/forumPost/update_FourmPost_input.jsp");
//				failureView.forward(req, res);
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.�}�l�R�����***************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.deletePost(id);;
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/admini/listAllAdmini.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forumPost/update_FourmPost_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			Gson gson = new Gson();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				List<ForumPostVO> list = new LinkedList<ForumPostVO>();
				list =  forumPostSvc.getAll();
				if (list  == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/forumPost/select_page.jsp");
//					failureView.forward(req, res);
//					return;
				}
				
				/***************************3.(Send the Success view)*************/
				req.setAttribute("list", list); 
//				String url = "/forumPost/listOneForumPost.jsp";
				
				String json = gson.toJson(list);
				res.getWriter().print(json);
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/forumPost/select_page.jsp");
//				failureView.forward(req, res);
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("getHito".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			Gson gson = new Gson();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1**********************/
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				List<ForumPostVO> list = new LinkedList<ForumPostVO>();
				list =  forumPostSvc.getLike();
				if (list  == null) {
					errorMsgs.add("查無資料");
				}

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
		
		if ("viewcount".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
		
			try {
				/***************************1.**********************/
				Integer id = new Integer(req.getParameter("id").trim());

				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setId(id);
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.updateVC(id);
				
				/***************************3.(Send the Success view)*************/
				res.getWriter().print("1");

				/***************************catch*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		if ("likecount".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			
			try {
				/***************************1.**********************/
				Integer id = new Integer(req.getParameter("id").trim());
				Integer likeCount = new Integer(req.getParameter("likeCount").trim());
				
				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setId(id);
				forumPostVO.setLikeCount(likeCount);
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.updateLC(id,likeCount);
				
				/***************************3.(Send the Success view)*************/
				res.getWriter().print("1");
				
				/***************************catch*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("commentcount".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			
			try {
				/***************************1.**********************/
				Integer id = new Integer(req.getParameter("id").trim());
				Integer commentCount = new Integer(req.getParameter("commentCount").trim());
				
				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setId(id);
				forumPostVO.setViewCount(commentCount);
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.updateCC(id, commentCount);
				
				/***************************3.(Send the Success view)*************/
				res.getWriter().print("1");
				
				/***************************catch*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		if ("search".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			Gson gson = new Gson();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1**********************/
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				List<ForumPostVO> list = new LinkedList<ForumPostVO>();
				String search = req.getParameter("search");
				list =  forumPostSvc.getSearch(search);
				if (list  == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
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
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1**********************/
				String str = req.getParameter("creator");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;
				}
				
				Integer creator = null;
				try {
					creator = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/forumPost/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				List<ForumPostVO> list = new LinkedList<ForumPostVO>();
				list = forumPostSvc.getMemPost(creator);
				
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

package com.usedmsg.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.used.model.UsedService;
import com.used.model.UsedVO;
import com.usedmsg.model.UsedMsgService;
import com.usedmsg.model.UsedMsgVO;
import com.usedpic.model.UsedPicService;
import com.usedpic.model.UsedPicVO;

import java.io.*;
import java.util.*;

/**
 * Servlet implementation class UsedServlet
 */
//@WebServlet("/used/UsedServlet")
@MultipartConfig
public class UsedMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsedMsgServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("addMsg".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String usedMsgText = request.getParameter("usedMsgText");
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/listOneProd.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				String a = request.getParameter("usedNo");
				Integer usedNo = Integer.parseInt(a);
				request.setAttribute("usedNo", usedNo);
				UsedMsgService usedMsgSvc = new UsedMsgService();
				
				Integer memNo = new Integer(request.getParameter("memNo"));	//<---------------測試-------------------
				usedMsgSvc.addUsedMsg(memNo, usedNo, usedMsgText);
				
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				MemberService memSvc = new MemberService();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				List<MemberVO> memList = new ArrayList<MemberVO>();
				memList = memSvc.getAll();
				request.setAttribute("usedVO", usedVO); // 資料庫取出的empVO物件,存入req
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);
				request.setAttribute("memList", memList);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

				String url = "/frontend/used/listOneProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("deleteMsg".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer usedMsgNo = new Integer(request.getParameter("usedMsgNo"));
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/listOneProd.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				String a = request.getParameter("usedNo");
				Integer usedNo = Integer.parseInt(a);
				request.setAttribute("usedNo", usedNo);
				UsedMsgService usedMsgSvc = new UsedMsgService();
				usedMsgSvc.deleteMsg(usedMsgNo);
				
				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				MemberService memSvc = new MemberService();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				List<MemberVO> memList = new ArrayList<MemberVO>();
				memList = memSvc.getAll();
				request.setAttribute("usedVO", usedVO); // 資料庫取出的empVO物件,存入req
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);
				request.setAttribute("memList", memList);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				String url = "/frontend/used/listOneProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/selectPage.jsp");
				failureView.forward(request, response);
			}
			
		}

	}
}

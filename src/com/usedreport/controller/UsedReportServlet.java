package com.usedreport.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.used.model.UsedService;
import com.used.model.UsedVO;
import com.usedmsg.model.UsedMsgService;
import com.usedmsg.model.UsedMsgVO;
import com.usedpic.model.UsedPicService;
import com.usedpic.model.UsedPicVO;
import com.usedreport.model.UsedReportService;
import com.usedreport.model.UsedReportVO;

import java.io.*;
import java.util.*;

/**
 * Servlet implementation class UsedServlet
 */
//@WebServlet("/used/UsedServlet")
@MultipartConfig
public class UsedReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsedReportServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("report".equals(action)) { // 來自select_page.jsp的請求
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String usedReportReason = request.getParameter("usedReportReason");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/listOneProd.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				Integer usedNo = new Integer(request.getParameter("usedNo"));
				Integer sellerNo = new Integer(request.getParameter("sellerNo"));
//				request.setAttribute("usedNo", usedNo);
				UsedReportService usedReportSvc = new UsedReportService();
				Integer memNo = new Integer(request.getParameter("memNo")); // <---------------測試-------------------
				usedReportSvc.addUsedReport(usedNo, memNo, sellerNo, usedReportReason);

				UsedService usedSvc = new UsedService();
				UsedVO usedVO = usedSvc.getOneUsed(usedNo);
				UsedPicService picSvc = new UsedPicService();
				List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
				picList = picSvc.getOneUsedPics(usedNo);
				UsedMsgService msgSvc = new UsedMsgService();
				List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
				msgList = msgSvc.getMsgByUsedNo(usedNo);
				MemberService memSvc = new MemberService();
				List<MemberVO> memList = new ArrayList<MemberVO>();
				memList = memSvc.getAll();
				request.setAttribute("usedVO", usedVO); // 資料庫取出的empVO物件,存入req
				request.setAttribute("picList", picList);
				request.setAttribute("msgList", msgList);
				request.setAttribute("memList", memList);
//				response.setHeader("refresh","2; URL=listOneProd.jsp") ;
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
				RequestDispatcher failureView = request.getRequestDispatcher("/frontend/used/listOneProd.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				Integer usedMemNo = new Integer(request.getParameter("memNo"));
				Integer usedNo = new Integer(request.getParameter("usedNo"));

				/*************************** 2.開始新增資料 ****************************************/
				UsedReportService usedReportSvc = new UsedReportService();
				UsedReportVO usedReportVO = usedReportSvc.getOneUsedReport(usedMemNo, usedNo);
				request.setAttribute("usedReportVO", usedReportVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("getOne_For_Update".equals(action))
					url = "/backend/used/updateReportInput.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("updateReport".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				String empty = "";
				String usedReportNotice = request.getParameter("usedReportNotice");

				Integer usedReportState = null;
				String usedReportStateReg = "^[(0-9)*]{1}$";
				try {
					usedReportState = new Integer(request.getParameter("usedReportState").trim());
					if (request.getParameter("usedReportState").trim().matches(empty)) {

					} else if (!request.getParameter("usedReportState").trim().matches(usedReportStateReg)) {

						errorMsgs.add("請輸入狀態，長度為1之數字");
					}
				} catch (NumberFormatException e) {

					errorMsgs.add("請輸入狀態，長度為1之數字");
				}

				Integer usedNo = new Integer(request.getParameter("usedNo"));
				Integer memNo = new Integer(request.getParameter("memNo"));
				UsedReportService usedReportSvc = new UsedReportService();
				UsedReportVO usedReportVO = usedReportSvc.getOneUsedReport(memNo, usedNo);
				usedReportVO.setUsedReportState(usedReportState);
				usedReportVO.setUsedReportNotice(usedReportNotice);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("usedReportVO", usedReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/used/updateUsedInput.jsp");
					failureView.forward(request, response);
					return;
				}
				/*************************** 2.開始新增資料 ****************************************/
				usedReportSvc.updateUsedReport(usedReportVO);
				request.setAttribute("usedReportVO", usedReportVO);
				/*************************** 3.完成,準備轉交(Send the Success view) ************/
				String url = null;
				if ("updateReport".equals(action))
					url = "/backend/used/listAllReport.jsp";

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}

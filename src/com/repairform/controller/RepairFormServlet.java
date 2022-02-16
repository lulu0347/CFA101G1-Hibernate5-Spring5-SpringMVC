package com.repairform.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.repairform.model.*;

public class RepairFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("repairformno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入維修編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/RepairForm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer repairformno = null;
				try {
					repairformno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("維修編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/Repairform/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RepairFormService repairformSvc = new RepairFormService();
				RepairFormVO repairformVO = repairformSvc.getOneRepairForm(repairformno);
				if (repairformVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/RepairForm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repairformVO", repairformVO); // 資料庫取出的empVO物件,存入req
				String url ="/backend/RepairForm/listOneRepairForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/RepairForm/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//===============================    ↓ 前台單一查詢     ======================================		
		if ("getOne_For_Displayf".equals(action)) {  // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("repairformno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入維修編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/RepairForm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer repairformno = null;
				try {
					repairformno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("維修編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/Repairform/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RepairFormService repairformSvc = new RepairFormService();
				RepairFormVO repairformVO = repairformSvc.getOneRepairForm(repairformno);
				if (repairformVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/RepairForm/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/**************************3.查詢完成,準備轉交(Send the Success view)***************/
				req.setAttribute("repairformVO", repairformVO); // 資料庫取出的empVO物件,存入req
				String url ="/frontend/RepairForm/listOneRepairForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/**************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/RepairForm/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
//===============================  ↑ 前台單一查詢     ======================================			
		
		

		
		if ("getOne_For_Update".equals(action)) {// 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer repairformno = new Integer(req.getParameter("repairformno"));
				
				
				/***************************2.開始查詢資料*************************************/
				RepairFormService repairformSvc = new RepairFormService();;
				RepairFormVO repairformVO = repairformSvc.getOneRepairForm(repairformno);
								
				/****************************3.查詢完成,準備轉交(Send the Success view)************/			
				req.setAttribute("repairformVO", repairformVO);  // 資料庫取出的empVO物件,存入req
				String url ="/backend/RepairForm/update_RepairForm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/RepairForm/listAllRepairForm.jsp");
				failureView.forward(req, res);
			}
		}
		

		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理***********************/
				Integer repairformno = new Integer(req.getParameter("repairformno").trim());
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				
				
				//================================================================================分隔線↓是OrderNo
			
				Integer orderno = new Integer(req.getParameter("orderno").trim());
			
				
				//================================================================================分隔線↓是ItemNo
				Integer itemno = new Integer(req.getParameter("itemno").trim());
				
			
				

				//================================================================================分隔線↓是CreateTime
				java.sql.Date createtime = null;
				try {
					createtime = java.sql.Date.valueOf(req.getParameter("createtime").trim());
				} catch (IllegalArgumentException e) {
					createtime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				//================================================================================分隔線↓是RepairFormStatus
				String repairformstatus = req.getParameter("repairformstatus");
				if (repairformstatus == null || repairformstatus.trim().length() == 0){
					errorMsgs.add("維修單資訊: 請勿空白");
				
	            }
				
				//================================================================================分隔線↓是AdminNo
				Integer adminno = new Integer(req.getParameter("adminno").trim());
               
				
				//================================================================================分隔線↓是RepairInfo
				
                String repairinfo = req.getParameter("repairinfo");
				
				if (repairinfo == null || repairinfo.trim().length() == 0){
					errorMsgs.add("維修單資訊: 請勿空白");
				
	            }
				
				
				//================================================================================分隔線↓是RepairEnd
				
				java.sql.Date repairend = null;
				try {
					repairend = java.sql.Date.valueOf(req.getParameter("repairend").trim());
				} catch (IllegalArgumentException e) {
					repairend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//==========================================================================================
              

				RepairFormVO repairformVO = new RepairFormVO();
				
				repairformVO.setRepairformno(repairformno);
				repairformVO.setMemno(memno);
				repairformVO.setOrderno(orderno);
				repairformVO.setItemno(itemno);
				repairformVO.setCreatetime(createtime);
				repairformVO.setRepairformstatus(repairformstatus);
				repairformVO.setAdminno(adminno);
				repairformVO.setRepairinfo(repairinfo);
				repairformVO.setRepairend(repairend);
				
				
				//Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairformVO", repairformVO); //含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/RepairForm/update_RepairForm_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				//===========================================================================================
				
				/***************************2.開始修改資料*****************************************/
				RepairFormService repairformSvc = new RepairFormService();
				repairformVO = repairformSvc.updaterepairform(repairformno, memno, 
						       orderno, itemno, createtime, repairformstatus, adminno, repairinfo, repairend);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repairformVO", repairformVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url ="/backend/RepairForm/listOneRepairForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/**************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/RepairForm/update_RepairForm_input.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addEmp.jsp的 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理************************/
                Integer memno = new Integer(req.getParameter("memno").trim());
                
				
				//================================================================================分隔線↓是OrderNo
			
				Integer orderno = new Integer(req.getParameter("orderno").trim());
				
				//==============================================================================分隔線↓是ItemNo
				Integer itemno = new Integer(req.getParameter("itemno").trim());
				

				//================================================================================分隔線↓是CreateTime
				java.sql.Date createtime = null;
				try {
					createtime = java.sql.Date.valueOf(req.getParameter("createtime").trim());
				} catch (IllegalArgumentException e) {
					createtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				//================================================================================分隔線↓是RepairFormStatus
				String repairformstatus = req.getParameter("repairformstatus");
				if (repairformstatus == null || (repairformstatus.trim()).length() == 0) {
					errorMsgs.add("維修單狀態: 請勿空白");
				
	            }
				
				//===============================================================================分隔線↓是AdminNo
				Integer adminno = new Integer(req.getParameter("adminno").trim());

				
				//================================================================================分隔線↓是RepairInfo
				
                String repairinfo = req.getParameter("repairinfo");
				
				if (repairinfo == null || (repairinfo.trim()).length() == 0) {
					errorMsgs.add("維修單資訊: 請勿空白");
				
	            }
				
				
				//=============================================================分隔線↓是RepairEnd
				
				java.sql.Date repairend = null;
				try {
					repairend = java.sql.Date.valueOf(req.getParameter("repairend").trim());
				} catch (IllegalArgumentException e) {
					repairend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				RepairFormVO repairformVO = new RepairFormVO();
				repairformVO.setMemno(memno);
				repairformVO.setOrderno(orderno);
				repairformVO.setItemno(itemno);
				repairformVO.setCreatetime(createtime);
				repairformVO.setRepairformstatus(repairformstatus);
				repairformVO.setAdminno(adminno);
				repairformVO.setRepairinfo(repairinfo);
				repairformVO.setRepairend(repairend);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairformVO", repairformVO);// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/RepairForm/addRepairForm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************************2.開始新增資料***************************************/
				RepairFormService repairformSvc = new RepairFormService();
				repairformVO = repairformSvc.addrepairform(memno, orderno, itemno, createtime,repairformstatus, adminno, repairinfo,repairend);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url ="/backend/RepairForm/listAllRepairForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/RepairForm/addRepairForm.jsp");
				failureView.forward(req, res);
			}
		}

        
        
        
//來自前台addRepairForm============================================================================================
 if ("insertf".equals(action)) {// 來自addEmp.jsp的  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                Integer memno = new Integer(req.getParameter("memno").trim());
                
				
				//================================================================================OrderNo
			
				Integer orderno = new Integer(req.getParameter("orderno").trim());
				
				//================================================================================ItemNo
				Integer itemno = new Integer(req.getParameter("itemno").trim());
				

				//================================================================================CreateTime
				java.sql.Date createtime = null;
				try {
					createtime = java.sql.Date.valueOf(req.getParameter("createtime").trim());
				} catch (IllegalArgumentException e) {
					createtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				//================================================================================RepairFormStatus
				String repairformstatus = req.getParameter("repairformstatus");
				if (repairformstatus == null || (repairformstatus.trim()).length() == 0) {
					errorMsgs.add("維修單狀態:請勿空白");
				
	            }
				
				//================================================================================AdminNo
				Integer adminno = new Integer(req.getParameter("adminno").trim());

				
				//================================================================================RepairInfo
				
                String repairinfo = req.getParameter("repairinfo");
				
				if (repairinfo == null || (repairinfo.trim()).length() == 0) {
					errorMsgs.add("維修單資訊：請勿空白");
				
	            }
				
				
				//================================================================================RepairEnd
				
				java.sql.Date repairend = null;
				try {
					repairend = java.sql.Date.valueOf(req.getParameter("repairend").trim());
				} catch (IllegalArgumentException e) {
					repairend = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				RepairFormVO repairformVO = new RepairFormVO();
				repairformVO.setMemno(memno);
				repairformVO.setOrderno(orderno);
				repairformVO.setItemno(itemno);
				repairformVO.setCreatetime(createtime);
				repairformVO.setRepairformstatus(repairformstatus);
				repairformVO.setAdminno(adminno);
				repairformVO.setRepairinfo(repairinfo);
				repairformVO.setRepairend(repairend);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairformVO", repairformVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/RepairForm/addRepairForm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RepairFormService repairformSvc = new RepairFormService();
				repairformVO = repairformSvc.addrepairform(memno,  orderno,  itemno,
						 createtime,  repairformstatus,  adminno,  repairinfo,  repairend);
				
				/**************************3.新增完成,準備轉交(Send the Success view)************/
				String url ="/frontend/RepairForm/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/RepairForm/addRepairForm.jsp");
				failureView.forward(req, res);
			}
		}

 
         
//====================================↑  addRepairForm==================================================

 
//================================    ↓  delete=========================================================
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數**************************************/
				Integer repairformno = new Integer(req.getParameter("repairformno"));
				
				/***************************2.開始刪除資料**************************************/
				RepairFormService repairformSvc = new RepairFormService();
				repairformSvc.deleterepairform(repairformno);
				
				/**************************3.刪除完成,準備轉交(Send the Success view)************/								
				String url ="/backend/RepairForm/listAllRepairForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/RepairForm/listAllRepairForm.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

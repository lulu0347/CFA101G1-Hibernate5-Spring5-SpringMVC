package com.auctAct.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auctAct.model.AuctActService;
import com.auctAct.model.AuctActVO;
import com.auctPic.model.ActPicService;
import com.auctPic.model.AuctActPicDAO_JDBC;
import com.auctPic.model.AuctActPicVO;

import Utils.PrintUtils;

@MultipartConfig(fileSizeThreshold = 2 * 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 1024 * 1024)
public class AuctActServlet_V2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String className = "AuctActServlet";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Enumeration<String> names = req.getParameterNames();

		PrintUtils.alwaysPrint(className, action, "", "");

		// 來自select_page.jsp的請求
		if ("getOneAct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				String auctActNoStr = req.getParameter("auctActNo");
				if (auctActNoStr == null || (auctActNoStr.trim()).length() == 0) {
					errorMsgs.add("請輸入拍賣活動號碼");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/select_page.jsp");
					failView.forward(req, res);
					return;
				}

				Integer auctActNo = null;
				try {
					auctActNo = new Integer(auctActNoStr);
				} catch (Exception e) {
					errorMsgs.add("號碼格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/select_page.jsp");
					failView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 **************************************/
				AuctActService auctActSVC = new AuctActService();
				AuctActVO auctActVO = auctActSVC.getOneAct(auctActNo);
				if (auctActVO == null) {
					errorMsgs.add("查無此活動");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/select_page.jsp");
					failView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ***********/

				req.setAttribute("auctActVO", auctActVO);
				String url = "/backend/auctAct/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/select_page.jsp");
				failView.forward(req, res);

			}
		}

		// 來自listAll.jsp update 修改

		if ("get_One_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 *************************************/
				Integer auctActNo = new Integer(req.getParameter("auctActNo").trim());
				/*************************** 2.開始查詢資料 *************************************/
				AuctActService auctSvc = new AuctActService();
				AuctActVO auctActVO = auctSvc.getOneAct(auctActNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) **********/
				req.setAttribute("auctActVO", auctActVO);
				String updateurl = "/backend/auctAct/updateAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(updateurl);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/auctAct/listAllAct.jsp");
				failureView.forward(req, res);
			}

		}

		// list all delete

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 *************************************/
				Integer auctActNo = new Integer(req.getParameter("auctActNo"));
				/*************************** 2.開始查詢資料 *************************************/
				ActPicService actPicSvc = new ActPicService();
				actPicSvc.deleteActPic(auctActNo);
				AuctActService auctSvc = new AuctActService();
				auctSvc.deleteAct(auctActNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) **********/
				String listAllurl = "/backend/auctAct/listAllAct.jsp";
				RequestDispatcher sussesView = req.getRequestDispatcher(listAllurl);
				sussesView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/listAllAct.jsp");
				failView.forward(req, res);
			}

		}

		// 來自addAct.jsp

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			String auctActName = req.getParameter("auctActName");
			if (auctActName == null || auctActName.trim().length() == 0) {
				errorMsgs.add("活動名稱不可以空白");
			}

			String auctActDesc = req.getParameter("auctActDesc");
			if (auctActDesc == null || auctActDesc.trim().length() == 0) {
				errorMsgs.add("活動說明不可以空白");
			}

			String auctActStateStr = req.getParameter("auctActState");
			if (auctActStateStr == null || (auctActStateStr.trim()).length() == 0) {
				errorMsgs.add("請點選活動狀態");
			}

			Integer auctActState = null;
			try {
				auctActState = new Integer(auctActStateStr);
			} catch (Exception e) {
				errorMsgs.add("資料格式錯誤");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp auctStartTime;
			try {
				String auctStartTimeStr = req.getParameter("auctStartTime");
				PrintUtils.printWhenDebug(className, action, "auctStartTimeStr", auctStartTimeStr);
				Date startTime = sdf.parse(req.getParameter("auctStartTime"));
				long time = startTime.getTime();
				auctStartTime = new Timestamp(time);
			} catch (ParseException e) {
				auctStartTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入活動時間!");
			}

			Timestamp auctEndTime;
			try {
				String auctEndTimestr = req.getParameter("auctEndTime"); // 測試BUG
				PrintUtils.printWhenDebug(className, action, "auctEndTimeStr", auctEndTimestr);// 測試BUG
				Date endTime = sdf.parse(req.getParameter("auctEndTime"));
				long time = endTime.getTime();
				auctEndTime = new Timestamp(time);
			} catch (ParseException e) {
				auctEndTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入結束時間!");
			}
			
			Map<String, AuctActPicVO> photos = new HashMap<String, AuctActPicVO>();
			String[] paramArray = { "banner", "cart", "button" };
			for (String key : paramArray) {
				AuctActPicVO vo = getpic(req, key);
				if (vo == null) {
					errorMsgs.add(key + "請輸入圖片");
				} else {
					photos.put(key, vo);
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("photos", photos);
			
			AuctActVO auctActVO = new AuctActVO();
			auctActVO.setAuctActName(auctActName);
			auctActVO.setAuctActDesc(auctActDesc);
			auctActVO.setAuctActState(auctActState);
			auctActVO.setAuctStartTime(auctStartTime);
			auctActVO.setAuctEndTime(auctEndTime);
			req.setAttribute("auctActVO", auctActVO);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/addAct.jsp");
				failView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			AuctActService actSvc1 = new AuctActService();
			auctActVO = actSvc1.addActPic(auctActVO, photos);
			req.setAttribute("auctActVO", auctActVO);

			String addurl = "/backend/auctAct/listOneAct.jsp";
			session.removeAttribute("photos");
			RequestDispatcher successView = req.getRequestDispatcher(addurl); // 新增成功後轉交select_page.jsp
			successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add("新增活動失敗:" + e.getMessage());
//				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/addAct.jsp");
//				failView.forward(req, res);
//			}
		}

		// 來自updateAct.jsp的請求
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer auctActNo = new Integer(req.getParameter("auctActNo").trim());
				String auctActName = req.getParameter("auctActName");

				if (auctActName == null || auctActName.trim().length() == 0) {
					errorMsgs.add("活動名稱不可以空白");
				}

				String auctActDesc = req.getParameter("auctActDesc");
				if (auctActDesc == null || auctActDesc.trim().length() == 0) {
					errorMsgs.add("活動說明不可以空白");
				}

				String auctActStateStr = req.getParameter("auctActState");
				if (auctActStateStr == null || (auctActStateStr.trim()).length() == 0) {
					errorMsgs.add("請點選活動狀態");
				}

				Integer auctActState = null;
				try {
					auctActState = new Integer(auctActStateStr);
				} catch (Exception e) {
					errorMsgs.add("資料格式錯誤");
				}

				Timestamp auctStartTime;
				try {
					String auctStartTimeStr = req.getParameter("auctStartTime"); // 測試BUG
					PrintUtils.printWhenDebug(className, action, "auctStartTimeStr", auctStartTimeStr);// 測試BUG
					Date startTime = sdf.parse(req.getParameter("auctStartTime"));
					long time = startTime.getTime();
					auctStartTime = new Timestamp(time);
				} catch (ParseException e) {
					auctStartTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入活動時間!");
				}

				Timestamp auctEndTime;
				try {
					String auctEndTimestr = req.getParameter("auctEndTime"); // 測試BUG
					PrintUtils.printWhenDebug(className, action, "auctEndTimeStr", auctEndTimestr);// 測試BUG
					Date endTime = sdf.parse(req.getParameter("auctEndTime"));
					long time = endTime.getTime();
					auctEndTime = new Timestamp(time);
				} catch (ParseException e) {
					auctEndTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入結束時間!");
				}
				
				
				
				

				if ((req) == null) {
					errorMsgs.add("每張圖請輸入圖片");
				}

				// update預設值
				AuctActVO auctActVO = new AuctActVO();
				auctActVO.setAuctActNo(auctActNo);
				auctActVO.setAuctActName(auctActName);
				auctActVO.setAuctActDesc(auctActDesc);
				auctActVO.setAuctActState(auctActState);
				auctActVO.setAuctStartTime(auctStartTime);
				auctActVO.setAuctEndTime(auctEndTime);

				req.setAttribute("auctActVO", auctActVO);
				// 處理錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/updateAct.jsp");
					failView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 ****************************************/

				AuctActService actSvc = new AuctActService();
			
//				auctActVO = actSvc.updateAct(auctActVO, photos);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("auctActVO", auctActVO);

				String listurl = "/backend/auctAct/listOneAct.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(listurl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auctAct/updateAct.jsp");
				failView.forward(req, res);
			}
		}
	}

	
	

	private AuctActPicVO getpic(HttpServletRequest req, String paramName) throws IOException, ServletException {
		
		//從part物件拿出上傳圖片
		Part pic = req.getPart(paramName);
		AuctActPicVO picVO = new AuctActPicVO();
		String info = pic.getName();
		String contentType = pic.getContentType();
		InputStream is = pic.getInputStream();
		
		
		//如果沒上傳，則撈取 session 內的寄存圖片
		if (is.available() != 0) {
			byte[] photo = new byte[is.available()];
			is.read(photo);
			is.close();
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			picVO.setAuctActPic(photo);
		} else {
			//從 session 中撈取圖片
			picVO = getPicFromSession(req, paramName);
		}

		return picVO;
	}

	private AuctActPicVO getPicFromSession(HttpServletRequest req, String paramName) {
		HttpSession session = req.getSession();
		//從session 拿出 photos map
		Map<String, AuctActPicVO> photos = (Map<String, AuctActPicVO>) session.getAttribute("photos");

		//如果此 seesion 剛建立，則 photos == null
		if (photos == null) {
			return null;
		} else {
			//拿出 圖片物件，如果是 null 代表沒有寄存，也代表使用者未曾上傳，需警告使用者
			return photos.get(paramName);
		}
	}

	private List<AuctActPicVO> getPhotos(HttpServletRequest req) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		List<AuctActPicVO> list = new ArrayList<>();
		List<String> nameList = Arrays.asList("banner", "cart", "button");

		for (Part part : parts) {
			AuctActPicVO picVO = new AuctActPicVO();

			String info = part.getName();
			String contentType = part.getContentType();
			if (contentType == null || !contentType.startsWith("image/") || !nameList.contains(info)) {
				continue;
			}

			InputStream is = part.getInputStream();
			byte[] photo = new byte[is.available()];
			if (is.available() == 0) {
				continue;
			}
			is.read(photo);
			is.close();

			picVO.setAuctActPic(photo);
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			list.add(picVO);
		}

		return list;
	}
}
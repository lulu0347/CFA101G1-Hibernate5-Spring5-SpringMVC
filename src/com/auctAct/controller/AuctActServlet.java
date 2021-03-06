package com.auctAct.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
import javax.servlet.ServletContext;
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
import com.auctActProd.model.AuctActProdService;
import com.auctActProd.model.AuctActProdVO;
import com.auctPic.model.ActPicService;
import com.auctPic.model.AuctActPicDAO_JDBC;
import com.auctPic.model.AuctActPicDAO_interface;
import com.auctPic.model.AuctActPicVO;
import com.auctProd.model.AuctProdService;
import com.auctProd.model.AuctProdVO;
import Utils.PrintUtils;

@MultipartConfig(fileSizeThreshold = 2 * 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 1024 * 1024)
public class AuctActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String className = "AuctActServlet";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Enumeration<String> names = req.getParameterNames();

		PrintUtils.alwaysPrint(className, action, "", "");

		// ??????select_page.jsp?????????
		if ("getOneAct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String failurl ="/backend/auct/auctAct/select_page.jsp";

			/*************************** 1.?????????????????? - ??????????????????????????? **********************/
			try {
				String auctActNoStr = req.getParameter("auctActNo");
				if (auctActNoStr == null || (auctActNoStr.trim()).length() == 0) {
					errorMsgs.add("???????????????????????????");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher(failurl);
					failView.forward(req, res);
					return;
				}

				Integer auctActNo = null;
				try {
					auctActNo = new Integer(auctActNoStr);
				} catch (Exception e) {
					errorMsgs.add("??????????????????");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher(failurl);
					failView.forward(req, res);
					return;
				}

				/*************************** 2.?????????????????? **************************************/
				
				AuctActProdService auctActProdSvc = new AuctActProdService();
				List<AuctActProdVO> auctActProdList= auctActProdSvc.getByActNo(auctActNo);

	
				AuctActService auctActSVC = new AuctActService();
				AuctActVO auctActVO = auctActSVC.getOneAct(auctActNo);
				if (auctActVO == null) {
					errorMsgs.add("???????????????");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher(failurl);
					failView.forward(req, res);
					return;
				}

				/*************************** 3.????????????,????????????(Send the Success view) ***********/

				req.setAttribute("auctActVO", auctActVO);
				req.setAttribute("auctActProdList", auctActProdList);
				String url = "/backend/auct/auctAct/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ??????????????????????????? ************************************/

			} catch (Exception e) {
				errorMsgs.add("??????????????????:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher(failurl);
				failView.forward(req, res);

			}
		}

		// ??????listAll.jsp update ??????

		if ("get_One_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.?????????????????? *************************************/

			Integer auctActNo = new Integer(req.getParameter("auctActNo").trim());
			/*************************** 2.?????????????????? *************************************/
			AuctActService auctSvc = new AuctActService();
			AuctActVO auctActVO = auctSvc.getOneAct(auctActNo);
			/*************************** 3.????????????,????????????(Send the Success view) **********/
			req.setAttribute("auctActVO", auctActVO);
			// ??????????????????????????????????????????????????????

			ActPicService actPicSvc = new ActPicService();
			Map<String, AuctActPicVO> updatephotos = new HashMap<String, AuctActPicVO>();
			updatephotos = actPicSvc.getPicListByActNo(auctActNo);

			// ?????????????????????SESSION???
			HttpSession session = req.getSession();
			session.setAttribute("updatephotos", updatephotos);

			String updateurl = "/backend/auct/auctAct/updateAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(updateurl);
			successView.forward(req, res);
			/*************************** ??????????????????????????? **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("??????????????????????????????:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/auctAct/listAllAct.jsp");
//				failureView.forward(req, res);
//			}

		}

		// list all delete

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/*************************** 1.?????????????????? *************************************/
				Integer auctActNo = new Integer(req.getParameter("auctActNo"));
				/*************************** 2.?????????????????? *************************************/
				AuctActProdService  auctActProdSvc =new  AuctActProdService();
				auctActProdSvc.deleteActProd(auctActNo);
				ActPicService actPicSvc = new ActPicService();
				actPicSvc.deleteActPic(auctActNo);
				AuctActService auctSvc = new AuctActService();
				auctSvc.deleteAct(auctActNo);

				/*************************** 3.????????????,????????????(Send the Success view) **********/
				String listAllurl = "/backend/auct/auctAct/listAllAct.jsp";
				RequestDispatcher sussesView = req.getRequestDispatcher(listAllurl);
				sussesView.forward(req, res);
				/*************************** ??????????????????????????? **********************************/
			} catch (Exception e) {
				errorMsgs.add("??????????????????" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctAct/listAllAct.jsp");
				failView.forward(req, res);
			}

		}

		// ??????????????????addAct.jsp
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String errorPage = "/backend/auct/auctAct/addAct2.jsp";
			try {
				String auctActName = req.getParameter("auctActName");
				if (auctActName == null || auctActName.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				String auctActDesc = req.getParameter("auctActDesc");
				if (auctActDesc == null || auctActDesc.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				
				Integer auctActState = 0;

				Timestamp auctStartTime;
				try {
					String auctStartTimeStr = req.getParameter("auctStartTime");
					PrintUtils.printWhenDebug(className, action, "auctStartTimeStr", auctStartTimeStr);
					Date startTime = sdf.parse(req.getParameter("auctStartTime"));
					long time = startTime.getTime();
					auctStartTime = new Timestamp(time);
				} catch (ParseException e) {
					auctStartTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				Timestamp auctEndTime;
				try {
					String auctEndTimestr = req.getParameter("auctEndTime"); // ??????BUG
					PrintUtils.printWhenDebug(className, action, "auctEndTimeStr", auctEndTimestr);// ??????BUG
					Date endTime = sdf.parse(req.getParameter("auctEndTime"));
					long time = endTime.getTime();
					auctEndTime = new Timestamp(time);
				} catch (ParseException e) {
					auctEndTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				Map<String, AuctActPicVO> photos = new HashMap<String, AuctActPicVO>();
				String[] paramArray = { "banner1", "banner2", "banner3", "cart", "button" };
				for (String key : paramArray) {
					AuctActPicVO vo = getpic(req, key);
					if (vo == null) {
						errorMsgs.add(key + "???????????????");
					} else {
						photos.put(key, vo);
					}
				}
				HttpSession session = req.getSession();
				// photos ?????????MAP??????
				session.setAttribute("photos", photos);

				AuctActVO auctActVO = new AuctActVO();
				auctActVO.setAuctActName(auctActName);
				auctActVO.setAuctActDesc(auctActDesc);
				auctActVO.setAuctActState(auctActState);
				auctActVO.setAuctStartTime(auctStartTime);
				auctActVO.setAuctEndTime(auctEndTime);
				req.setAttribute("auctActVO", auctActVO);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher(errorPage);
					failView.forward(req, res);
					return;
				}

				/*************************** 2.?????????????????? ***************************************/
				AuctActService actSvc1 = new AuctActService();
				auctActVO = actSvc1.addActPic(auctActVO, photos);
				req.setAttribute("auctActVO", auctActVO);

				String addurl ="/backend/auct/auctActProd/addActProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(addurl); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("??????????????????:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher(errorPage);
				failView.forward(req, res);
			}
		}
		
		//???????????????????????????
		if ("insertThenAddProd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String auctActName = req.getParameter("auctActName");
				if (auctActName == null || auctActName.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				String auctActDesc = req.getParameter("auctActDesc");
				if (auctActDesc == null || auctActDesc.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				Integer auctActState = 0;

				Timestamp auctStartTime;
				try {
					String auctStartTimeStr = req.getParameter("auctStartTime");
					PrintUtils.printWhenDebug(className, action, "auctStartTimeStr", auctStartTimeStr);
					Date startTime = sdf.parse(req.getParameter("auctStartTime"));
					long time = startTime.getTime();
					auctStartTime = new Timestamp(time);
				} catch (ParseException e) {
					auctStartTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				Timestamp auctEndTime;
				try {
					String auctEndTimestr = req.getParameter("auctEndTime"); // ??????BUG
					PrintUtils.printWhenDebug(className, action,"auctEndTimeStr", auctEndTimestr);// ??????BUG
					Date endTime = sdf.parse(req.getParameter("auctEndTime"));
					long time = endTime.getTime();
					auctEndTime = new Timestamp(time);
				} catch (ParseException e) {
					auctEndTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				Map<String, AuctActPicVO> photos = new HashMap<String, AuctActPicVO>();
				String[] paramArray = { "banner1", "banner2", "banner3", "cart", "button" };
				for (String key : paramArray) {
					AuctActPicVO vo = getpic(req, key);
					if (vo == null) {
						errorMsgs.add(key + "???????????????");
					} else {
						photos.put(key, vo);
					}
				}
				HttpSession session = req.getSession();
				// photos ?????????MAP??????
				session.setAttribute("photos", photos);

				AuctActVO auctActVO = new AuctActVO();
				auctActVO.setAuctActName(auctActName);
				auctActVO.setAuctActDesc(auctActDesc);
				auctActVO.setAuctActState(auctActState);
				auctActVO.setAuctStartTime(auctStartTime);
				auctActVO.setAuctEndTime(auctEndTime);
				req.setAttribute("auctActVO", auctActVO);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctAct/addAct.jsp");
					failView.forward(req, res);
					return;
				}

				/*************************** 2.?????????????????? ***************************************/

				// ??????productList
				// ????????????????????????

				String addurl = "/backend/auct/auctActProd/addActProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(addurl); // ?????????????????????select_page.jsp
				successView.forward(req, res);
				//??????????????????????????????????????? ADDACT??????????????????JSP 
				req.removeAttribute("auctActVO");
				session.removeAttribute("photos");
				
				//????????????SESSION ?????????ADDPROD????????????????????????
				session.setAttribute("auctActVOForInsert", auctActVO);
				session.setAttribute("photosForInsert", photos);

			} catch (Exception e) {
				errorMsgs.add("??????????????????:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctAct/addAct.jsp");
				failView.forward(req, res);
			}
		}

		// ??????updateAct.jsp?????????
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer auctActNo = new Integer(req.getParameter("auctActNo").trim());
				String auctActName = req.getParameter("auctActName");

				if (auctActName == null || auctActName.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				String auctActDesc = req.getParameter("auctActDesc");
				if (auctActDesc == null || auctActDesc.trim().length() == 0) {
					errorMsgs.add("???????????????????????????");
				}

				String auctActStateStr = req.getParameter("auctActState");
				if (auctActStateStr == null || (auctActStateStr.trim()).length() == 0) {
					errorMsgs.add("?????????????????????");
				}

				Integer auctActState = null;
				try {
					auctActState = new Integer(auctActStateStr);
				} catch (Exception e) {
					errorMsgs.add("??????????????????");
				}

				Timestamp auctStartTime;
				try {
					String auctStartTimeStr = req.getParameter("auctStartTime"); // ??????BUG
					PrintUtils.printWhenDebug(className, action, "auctStartTimeStr", auctStartTimeStr);// ??????BUG
					Date startTime = sdf.parse(auctStartTimeStr);
					long time = startTime.getTime();
					auctStartTime = new Timestamp(time);
				} catch (ParseException e) {
					auctStartTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				Timestamp auctEndTime;
				try {
					String auctEndTimestr = req.getParameter("auctEndTime"); // ??????BUG
					PrintUtils.printWhenDebug(className, action, "auctEndTimeStr", auctEndTimestr);// ??????BUG
					Date endTime = sdf.parse(auctEndTimestr);
					long time = endTime.getTime();
					auctEndTime = new Timestamp(time);
				} catch (ParseException e) {
					auctEndTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("?????????????????????!");
				}

				// ?????????????????????

				Map<String, AuctActPicVO> updatephotos = new HashMap<String, AuctActPicVO>();
				String[] paramArray = { "banner1", "banner2", "banner3", "cart", "button" };
				for (String key : paramArray) {
					AuctActPicVO vo = getupdatepic(req, key);
					updatephotos.put(key, vo);
				}
				HttpSession session = req.getSession();
				session.setAttribute("updatephotos", updatephotos);

				// update?????????
				AuctActVO auctActVO = new AuctActVO();
				auctActVO.setAuctActNo(auctActNo);
				auctActVO.setAuctActName(auctActName);
				auctActVO.setAuctActDesc(auctActDesc);
				auctActVO.setAuctActState(auctActState);
				auctActVO.setAuctStartTime(auctStartTime);
				auctActVO.setAuctEndTime(auctEndTime);
				req.setAttribute("auctActVO", auctActVO);
				
				// ??????????????????
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctAct/updateAct.jsp");
					failView.forward(req, res);
					return;
				}
				/*************************** 2.?????????????????? ****************************************/

				AuctActService actSvc = new AuctActService();
				auctActVO = actSvc.updateAct(auctActVO, updatephotos);

				/*************************** 3.????????????,????????????(Send the Success view) *************/
				req.setAttribute("auctActVO", auctActVO);
				//?????????????????????
				// ????????????
			
	
	
				AuctActProdService auctActProdSvc = new AuctActProdService();
				List<AuctActProdVO> auctActProdList= auctActProdSvc.getByActNo(auctActNo);
				req.setAttribute("auctActProdList", auctActProdList);
				req.setAttribute("auctActVO", auctActVO);
				
				String listurl = "/backend/auct/auctAct/listOneAct.jsp";
				session.removeAttribute("updatephotos");

				RequestDispatcher successView = req.getRequestDispatcher(listurl);
				successView.forward(req, res);

				/*************************** ??????????????????????????? *************************************/
			} catch (Exception e) {
				errorMsgs.add("??????????????????:" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/backend/auct/auctAct/updateAct.jsp");
				failView.forward(req, res);
			}
		}
		
		//???????????????????????????????????????
		
		if("getAllProdwithPic".equals(action)) {
			/*************************** 1.?????????????????? *************************************/
			Integer auctActNo = new Integer(req.getParameter("auctActNo").trim());
			/*************************** 2.?????????????????? *************************************/
			//??????
			AuctActService auctActSVC = new AuctActService();
			AuctActVO auctActVO=auctActSVC.getOneAct(auctActNo);

			//????????????
			AuctActProdService  auctActProdSvc=new AuctActProdService();
			List<AuctActProdVO> auctActProdList = auctActProdSvc.getByActNo(auctActNo);
			for(AuctActProdVO actProdVo : auctActProdList) {
				actProdVo.getAuctProdNo();
				//??????
				AuctActProdVO auctActProdVO = new AuctActProdVO();
				auctActProdVO.setAuctProdNo(actProdVo.getAuctProdNo());
				req.setAttribute("auctActProdVO",auctActProdVO);				
			}
				
			
			//???????????????????????????
			req.setAttribute("auctActVO", auctActVO);
			req.setAttribute("auctActProdList", auctActProdList);
			
			String actProdurl="/frontend/auctAct/auctActProd.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(actProdurl);
			successView.forward(req, res);
			
		}
		

		
		
	}

	// ???????????????
	private AuctActPicVO getpic(HttpServletRequest req, String paramName) throws IOException, ServletException {

		// ???part????????????????????????
		Part pic = req.getPart(paramName);
		AuctActPicVO picVO = new AuctActPicVO();
		String info = pic.getName();
		String contentType = pic.getContentType();
		InputStream is = pic.getInputStream();

		// ??????????????????????????? session ??????????????????
		if (is.available() != 0) {
			byte[] photo = new byte[is.available()];
			is.read(photo);
			is.close();
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			picVO.setAuctActPic(photo);
		} else {
			// ??? session ???????????????
			picVO = getPicFromSession(req, paramName);
		}

		return picVO;
	}

	private AuctActPicVO getPicFromSession(HttpServletRequest req, String paramName) {
		HttpSession session = req.getSession();
		// ???session ?????? photos map
		Map<String, AuctActPicVO> photos = (Map<String, AuctActPicVO>) session.getAttribute("photos");
		// ????????? seesion ??????????????? photos == null
		if (photos == null) {
			return null;
		} else {
			// ?????? ???????????????????????? null ????????????????????????????????????????????????????????????????????????
			return photos.get(paramName);
		}
	}

	// ???????????????
	private AuctActPicVO getupdatepic(HttpServletRequest req, String paramName) throws IOException, ServletException {

		// ???part????????????????????????
		Part pic = req.getPart(paramName);
		AuctActPicVO picVO = new AuctActPicVO();
		String info = pic.getName();
		String contentType = pic.getContentType();
		InputStream is = pic.getInputStream();

		// ??????????????????????????? session ??????????????????
		if (is.available() != 0) {
			byte[] photo = new byte[is.available()];
			is.read(photo);
			is.close();
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			picVO.setAuctActPic(photo);
		} else {
			// ??? session ???????????????
			picVO = getupdatePicFromSession(req, paramName);
		}

		return picVO;
	}

	private AuctActPicVO getupdatePicFromSession(HttpServletRequest req, String paramName) {
		HttpSession session = req.getSession();
		// ???session ?????? photos map
		Map<String, AuctActPicVO> updatephotos = (Map<String, AuctActPicVO>) session.getAttribute("updatephotos");
		// ????????? seesion ??????????????? photos == null
		if (updatephotos == null) {
			return null;
		} else {
			// ?????? ???????????????????????? null ????????????????????????????????????????????????????????????????????????
			return updatephotos.get(paramName);
		}
	}

	// ??????????????????
	private List<AuctActPicVO> getPhotos(HttpServletRequest req) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		// ??????????????????
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
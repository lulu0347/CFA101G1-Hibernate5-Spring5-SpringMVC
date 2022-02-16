package com.auctPic.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.auctActProd.model.AuctActProdDAO_interface;
import com.auctPic.model.ActPicService;
import com.auctPic.model.AuctActPicDAO_JDBC;
import com.auctPic.model.AuctActPicDAO_interface;
import com.auctPic.model.AuctActPicVO;
import com.auctPic.model.AuctProdPicDAO_JDBC;
import com.auctPic.model.AuctProdPicDAO_interface;
import com.auctPic.model.AuctProdPicService;
import com.auctPic.model.AuctProdPicVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 40 * 1024
		* 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常

public class AuctActPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuctActPicServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");

		if ("getActPic".equals(action)) {
			ServletOutputStream out = res.getOutputStream();
			try {
				String str = req.getParameter("auctActNo");
				Integer auctActNo = Integer.parseInt(str);
				String auctActPicinfo = req.getParameter("auctActPicinfo");
				String auctActPicinfoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";

				/*************************** 2.開始查詢資料 *****************************************/
				ActPicService actSvc = new ActPicService();
				AuctActPicVO auctActPicVO = actSvc.getOneActPic(auctActNo, auctActPicinfo);
				res.setContentType(auctActPicVO.getAuctActPicFormat());
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				byte[] photo = auctActPicVO.getAuctActPic();
				out.write(photo);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
		} else {
			ServletOutputStream outProd = res.getOutputStream();
			try {
				
				String str = req.getParameter("auctProdNo");
				
				Integer auctProdNo = Integer.parseInt(str);
				String auctProdPicinfo = req.getParameter("auctProdPicinfo");
				
				System.out.println("auctProdNo:"+ auctProdNo);
				System.out.println("auctProdPicinfo:"+ auctProdPicinfo);
				/*************************** 2.開始查詢資料 *****************************************/
				AuctProdPicService prodSvc = new AuctProdPicService();
				AuctProdPicVO auctProdPicVO = prodSvc.getOneProdPic(auctProdNo, auctProdPicinfo);
				res.setContentType(auctProdPicVO.getAuctProdPicFormat());
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				byte[] prodPhoto = auctProdPicVO.getAuctProdPic();
				outProd.write(prodPhoto);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
				byte[] prodBuf = new byte[in.available()];
				in.read(prodBuf);
				outProd.write(prodBuf);
				in.close();

			}
		}
	}

}

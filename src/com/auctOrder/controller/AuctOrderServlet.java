package com.auctOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.auctActProd.model.AuctActProdService;
import com.auctActProd.model.AuctActProdVO;
import com.auctOrder.model.AuctOrdService;
import com.auctOrder.model.AuctOrdVO;
import com.auctOrderDetl.model.AuctOrdDetlService;
import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.auctProd.model.AuctProdService;
import com.auctProd.model.AuctProdVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;
import com.transRec.model.TransRecService;


public class AuctOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctActProdService auctProdSvc = new AuctActProdService();
	private AuctProdService auctProd = new AuctProdService();
	private TransRecService transRecSvc=new TransRecService();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		HttpSession session = req.getSession();
		MemberVO memVO = (MemberVO) session.getAttribute("user");
		boolean memVOIsNull = memVO == null;
		Integer memNo = null;
		if (!memVOIsNull) {
			memNo = memVO.getMemNo();
		}
		
		List<String> memLoginNeedPage = new ArrayList();
		memLoginNeedPage.add("displayCart");
		memLoginNeedPage.add("ajaxAddProd");
		memLoginNeedPage.add("checkOut");
		memLoginNeedPage.add("getOneDetl");
		memLoginNeedPage.add("shipment");
		memLoginNeedPage.add("cancel");
		memLoginNeedPage.add("delCartProd");
		memLoginNeedPage.add("ajaxNum");
		//contain包含+!  =>不包含
		if(memVOIsNull && !memLoginNeedPage.contains(action)) {
			//session取得使用者資訊失敗，導向首頁
			String ordRecurl = "/frontend/auctAct/auctActIndex.jsp";
			RequestDispatcher failView = req.getRequestDispatcher(ordRecurl);
			failView.forward(req, res);
			return;
		}

		// 商品選入購物車>>購物清單的頁面
		// 建立一個SESSION存放購物車的商品，cartList為點選商品加入購物車存放的籃子

		// 保留購物車的商品
		Map<Integer, AuctOrdDetlVO> auctCart = (Map<Integer, AuctOrdDetlVO>) session.getAttribute("auctCart");

		// 從後台訂單撈出訂單明細
		if ("getOneDetl".equals(action)) {
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);

			AuctOrdDetlService auctDetlSvc = new AuctOrdDetlService();
			List<AuctOrdDetlVO> auctOrdDetlList = auctDetlSvc.getbyProdNpOrdNo(auctOrdNo);

			req.setAttribute("auctOrdDetlList", auctOrdDetlList);
			String ordFDetlurl = "/backend/auct/auctOrder/orderRecDetl.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordFDetlurl);
			successView.forward(req, res);
		}

		// 從前台訂單中撈訂單明細
		if ("getOneProdDetl".equals(action)) {
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);

			AuctOrdDetlService auctDetlSvc = new AuctOrdDetlService();
			List<AuctOrdDetlVO> auctOrdDetlList = auctDetlSvc.getbyProdNpOrdNo(auctOrdNo);
			// 塞入商品名稱


			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);

			req.setAttribute("auctOrdDetlList", auctOrdDetlList);
			String ordFDetlurl = "/frontend/auctAct/orderRecDetl_front.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordFDetlurl);
			successView.forward(req, res);
		}

		// 從前/後台訂單判斷訂單狀態
		// 當後台出貨時
		if ("shipment".equals(action)) {
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);
			System.out.println(auctOrdNo);
			AuctOrdService auctOrdSvc = new AuctOrdService();
			AuctOrdVO auctOrd = auctOrdSvc.getOneOrd(auctOrdNo);

			// 取得原有訂單 將狀態改成 2:已出貨
			auctOrd.setAuctOrdState(2);
			// 塞入修改時間
			long time = System.currentTimeMillis();
			Timestamp auctOrdModTime = new Timestamp(time);
			auctOrd.setAuctOrdModTime(auctOrdModTime);
			auctOrdSvc.updateOrd(auctOrd);

			String ordFDetlurl = "/backend/auct/auctOrder/orderRecAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordFDetlurl);
			successView.forward(req, res);

		}

		// 後台取消訂單
		if ("cancel".equals(action)) {
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);
			AuctOrdService auctOrdSvc = new AuctOrdService();
			AuctOrdVO auctOrd = auctOrdSvc.getOneOrd(auctOrdNo);
			// 取得原有訂單 將狀態改成 0取消訂單
			auctOrd.setAuctOrdState(0);
			// 塞入修改時間
			long time = System.currentTimeMillis();
			Timestamp auctOrdModTime = new Timestamp(time);
			auctOrd.setAuctOrdModTime(auctOrdModTime);
			auctOrdSvc.updateOrd(auctOrd);

			String ordFDetlurl = "/backend/auct/auctOrder/orderRecAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordFDetlurl);
			successView.forward(req, res);

		}

		// 前台取消訂單
		if ("cancelOrd".equals(action)) {

			// 會員登入
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);
			AuctOrdService auctOrdSvc = new AuctOrdService();
			AuctOrdVO auctOrd = auctOrdSvc.getOneOrd(auctOrdNo);
			// 取得原有訂單 將狀態改成 0取消訂單
			auctOrd.setAuctOrdState(0);
			// 塞入修改時間
			long time = System.currentTimeMillis();
			Timestamp auctOrdModTime = new Timestamp(time);
			auctOrd.setAuctOrdModTime(auctOrdModTime);
			auctOrdSvc.updateOrd(auctOrd);

			List<AuctOrdVO> auctOrdList = new ArrayList<AuctOrdVO>();
			AuctOrdService OrdSvc = new AuctOrdService();
			auctOrdList = OrdSvc.getOrderRec(memNo);

			req.setAttribute("auctOrdList", auctOrdList);
			String ordRecurl = "/frontend/auctAct/orderRec.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordRecurl);
			successView.forward(req, res);
		}
		
		
		// 前台收貨
		if ("finishOrd".equals(action)) {
			// 會員登入
			if (memVOIsNull) {
				return;
			}
			String auctOrdNoStr = req.getParameter("auctOrdNo");
			Integer auctOrdNo = new Integer(auctOrdNoStr);
			AuctOrdService auctOrdSvc = new AuctOrdService();
			AuctOrdVO auctOrd = auctOrdSvc.getOneOrd(auctOrdNo);
			// 取得原有訂單 將狀態改成 0取消訂單
			auctOrd.setAuctOrdState(3);
			// 塞入修改時間
			long time = System.currentTimeMillis();
			Timestamp auctOrdModTime = new Timestamp(time);
			auctOrd.setAuctOrdModTime(auctOrdModTime);
			auctOrdSvc.updateOrd(auctOrd);
			List<AuctOrdVO> auctOrdList = new ArrayList<AuctOrdVO>();
			AuctOrdService OrdSvc = new AuctOrdService();
			auctOrdList = OrdSvc.getOrderRec(memNo);

			req.setAttribute("auctOrdList", auctOrdList);
			String ordRecurl = "/frontend/auctAct/orderRec.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordRecurl);
			successView.forward(req, res);
		}

		// 查詢會員個人的訂單明細
		// 前台從會員編號撈訂單列表
		if ("orderRec".equals(action)) {
			List<AuctOrdVO> auctOrdList = new ArrayList<AuctOrdVO>();
			AuctOrdService OrdSvc = new AuctOrdService();
			// 會員登入
			if (memVOIsNull) {
				return;		
			}
			auctOrdList = OrdSvc.getOrderRec(memNo);

			req.setAttribute("auctOrdList", auctOrdList);
			String ordRecurl = "/frontend/auctAct/orderRec.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordRecurl);
			successView.forward(req, res);
			// 0:取消訂單 1:待出貨 2:已出貨 3:完成訂單
		}

		// 驗證填寫會員訂單資訊
		if ("createOrd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String errorPage = "/frontend/auctAct/orderList.jsp";

//			try {
			// 會員登入
			if (memVOIsNull) {
				// TODO
				return;
			}

			// 驗證收件人姓名
			String receName = req.getParameter("receName");
			String receReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (receName == null || receName.trim().length() == 0) {
				errorMsgs.add("收件人姓名不可為空");
			} else if (!receName.trim().matches(receReg)) {
				errorMsgs.add("收件人姓名只能是中、英文字母、數字和_ ,且長度必須在2到10之間");
			}

			// 驗證收件人地址
			// 驗證select 選項
			String county = req.getParameter("county");
			if (county == "0000") {
				errorMsgs.add("請選擇縣市");
			}
			String city = req.getParameter("city");

			// 剩餘的地址
			String addr = req.getParameter("addr");
			String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			if (addr == null || addr.trim().length() == 0) {
				errorMsgs.add("收件人地址不可為空");
			} else if (!addr.trim().matches(addrReg)) {
				errorMsgs.add("收件人地址只能是中、英文字母、數字和_,且長度必須在2到100之間");
			}

			// 驗證收件人電話
			String recePhone = req.getParameter("recePhone");
			String recePhoneReg = "[0-9]{10}";
			if (recePhone == null || recePhone.trim().length() == 0) {
				errorMsgs.add("收件人電話不可為空");
			} else if (!recePhone.trim().matches(recePhoneReg)) {
				errorMsgs.add("格式錯誤，請輸入數字0-9");
			}

			// 驗證商品備註
			String note = req.getParameter("note");

			// 訂單時間
			long currentTime = System.currentTimeMillis();
			Timestamp auctOrdTime = new Timestamp(currentTime);
			Timestamp auctOrdModTime = new Timestamp(currentTime);

			// 合併收件人地址
			StringBuffer receAddr = new StringBuffer(county).append(city).append(addr);

			// 訂單明細的物件
			Integer sum = countTotalPrice(auctCart);

			// 建立一個訂單物件
			AuctOrdService auctOrdSvc = new AuctOrdService();
			AuctOrdVO auctOrdVO = new AuctOrdVO();
			auctOrdVO.setMemNo(memNo);
			auctOrdVO.setAuctOrdAmount(sum);
			auctOrdVO.setAuctOrdState(1);
			auctOrdVO.setReceName(receName);
			auctOrdVO.setReceAddr(receAddr.toString());// StringBuffer要再轉回Sting
			auctOrdVO.setRecePhone(recePhone);
			auctOrdVO.setNote(note);
			auctOrdVO.setAuctOrdTime(auctOrdTime);
			auctOrdVO.setAuctOrdModTime(auctOrdModTime);
			req.setAttribute("auctOrdVO", auctOrdVO);
			req.setAttribute("summary", sum);
			
			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);
			// 處理錯誤訊息
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher(errorPage);
				failView.forward(req, res);
				return;
			}

			/*************************** 2.訂單成立的導向 ***************************************/
			List<AuctOrdDetlVO> auctOrdDetlList = new ArrayList<AuctOrdDetlVO>(auctCart.values());
			auctOrdSvc.addOrderWithOrderDetl(auctOrdVO, auctOrdDetlList);
			session.removeAttribute("auctCart");
			req.setAttribute("auctOrdDetlList", auctOrdDetlList);
			
			String creOrdurl = "/frontend/auctAct/createOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(creOrdurl);
			successView.forward(req, res);

//			} 
//			catch (Exception e) {
//				errorMsgs.add("訂單建立失敗");
//				RequestDispatcher failView = req.getRequestDispatcher(errorPage);
//				failView.forward(req, res);
//			}
		}

		// JSP跳轉頁面的方法新增商品到購物車
		if ("addProdToCart".equals(action)) {

			// 讀取新加入的商品資訊
			String auctActprodNostr = req.getParameter("auctActProdNo");
			Integer auctActProdNo = new Integer(auctActprodNostr);

			// 用auctActProdNo從 service取出物件，找出單價
			AuctActProdService auctActProdSvc = new AuctActProdService();
			AuctActProdVO auctActProdVO = auctActProdSvc.getByActProdNo(auctActProdNo);

			AuctOrdDetlVO newOrdProd = new AuctOrdDetlVO();
			newOrdProd.setAuctActProdNo(auctActProdNo);
			newOrdProd.setPrice(auctActProdVO.getAuctProdPrice());
			newOrdProd.setProdPurQty(1);

			// 新增第一個商品
			if (auctCart == null) { // 購物車為空的情況
				//
				auctCart = new HashMap<Integer, AuctOrdDetlVO>();
				auctCart.put(newOrdProd.getAuctActProdNo(), newOrdProd);
			} else {
				// 不是第一件商品
				AuctOrdDetlVO inOrdProd = auctCart.get(newOrdProd.getAuctActProdNo());
				if (inOrdProd != null) {
					inOrdProd.setProdPurQty(inOrdProd.getProdPurQty() + newOrdProd.getProdPurQty());

				} else {
					inOrdProd = newOrdProd;
				}
				auctCart.put(inOrdProd.getAuctActProdNo(), inOrdProd);
				// 假設後來新增的商品跟購物車商品不同，避免再加入商品重複不可以放在ELSE判斷之內
			}
			session.setAttribute("auctCart", auctCart);
			String addProdurl = "/frontend/auctAct/auctActProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(addProdurl);
			successView.forward(req, res);

		}

		// AJAX不跳轉新增商品到購物車
		if ("ajaxAddProd".equals(action)) {

			// 讀取新加入的商品資訊
			String auctActprodNostr = req.getParameter("auctActProdNo");
			Integer auctActProdNo = new Integer(auctActprodNostr);

			// 用auctActProdNo從 service取出物件，找出單價
			AuctActProdService auctActProdSvc = new AuctActProdService();
			AuctActProdVO auctActProdVO = auctActProdSvc.getByActProdNo(auctActProdNo);

			AuctOrdDetlVO newOrdProd = new AuctOrdDetlVO();
			newOrdProd.setAuctActProdNo(auctActProdNo);
			newOrdProd.setPrice(auctActProdVO.getAuctProdPrice());
			newOrdProd.setProdPurQty(1);

			// 新增第一個商品
			if (auctCart == null) { // 購物車為空的情況
				//
				auctCart = new HashMap<Integer, AuctOrdDetlVO>();
				auctCart.put(newOrdProd.getAuctActProdNo(), newOrdProd);
			} else {
				// 不是第一件商品
				AuctOrdDetlVO inOrdProd = auctCart.get(newOrdProd.getAuctActProdNo());
				if (inOrdProd != null) {
					inOrdProd.setProdPurQty(inOrdProd.getProdPurQty() + newOrdProd.getProdPurQty());

				} else {
					inOrdProd = newOrdProd;
				}
				auctCart.put(inOrdProd.getAuctActProdNo(), inOrdProd);
				// 假設後來新增的商品跟購物車商品不同，避免再加入商品重複不可以放在ELSE判斷之內
			}
			session.setAttribute("auctCart", auctCart);
			PrintWriter out = res.getWriter();
			out.write("true");
			out.flush();
			out.close();
		}

		// 點選購物車
		if ("displayCart".equals(action)) {

			AuctActProdService auctProdSvc = new AuctActProdService();
			List<AuctActProdVO> auctProds = auctProdSvc.getAll();
			AuctProdService auctProd = new AuctProdService();
			List<AuctProdVO> prods = auctProd.getallProd();

			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);
			if (auctCart != null) {
				Integer sum = countTotalPrice(auctCart);
				req.setAttribute("summary", sum);
			}

			String ordUrl = "/frontend/auctAct/auctProdCart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(ordUrl);
			successView.forward(req, res);

		}

		// 在購物車中點選結帳前
		// 如果刪除商品
		if ("delCartProd".equals(action)) {
			String auctActProdNostr = req.getParameter("auctActProdNo");
			Integer auctActProdNo = new Integer(auctActProdNostr);
			auctCart.remove(auctActProdNo);
			Integer sum = countTotalPrice(auctCart);
			req.setAttribute("summary", sum);
			session.setAttribute("auctCart", auctCart);
			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);
			String addProdurl = "/frontend/auctAct/auctProdCart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(addProdurl);
			successView.forward(req, res);
		}

		if ("addCartProd".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 商品數量綁編號 =auctActProdNo數量
			String prodStr = "%sQty";
			for (AuctOrdDetlVO prodVO : auctCart.values()) {
				// 取得數量字串
				String Qty = new String(String.format(prodStr, prodVO.getAuctActProdNo()));
				String prodPurQtystr = req.getParameter(Qty);
				if (prodPurQtystr == null || prodPurQtystr.trim().length() == 0) {
					errorMsgs.put("prodPurQtystr", "請輸入購買數量");
				}
				// 取得輸入數量
				Integer prodPurQty = null;
				try {
					prodPurQty = new Integer(prodPurQtystr);
					if (prodPurQty < 0) {
						errorMsgs.put("prodPurQty", "購買數量不得為負值");
					}
				} catch (Exception e) {
					errorMsgs.put("prodPurQty", "數字格式錯誤");
				}
				// 購物車加入數量
				prodVO.setProdPurQty(prodPurQty);
				prodVO.setSumPrice(countTotalPrice(auctCart));
				auctCart.put(prodVO.getAuctOrdNo(), prodVO);
				Integer sum = countTotalPrice(auctCart);
				req.setAttribute("summary", sum);
				session.setAttribute("auctCart", auctCart);
				String addProdurl = "/frontend/auctAct/auctProdCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(addProdurl);
				successView.forward(req, res);

			}
		}

		// 取出單一商品修改商品數量
		if ("getCartProdUpdate".equals(action)) {
			// 塞入商品名稱
			AuctActProdService auctProdSvc = new AuctActProdService();
			List<AuctActProdVO> auctProds = auctProdSvc.getAll();
			AuctProdService auctProd = new AuctProdService();
			List<AuctProdVO> prods = auctProd.getallProd();

			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);

			String updateProdurl = "/frontend/auctAct/updateCart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(updateProdurl);
			successView.forward(req, res);
		}

		// 用AJAX傳遞輸入的數量

		if ("ajaxNum".equals(action)) {
			// 從商品編號找到相對要改的數量 1.
			Integer auctActProdNo = new Integer(req.getParameter("auctActProdNo"));
			Integer prodPurQty = new Integer(req.getParameter("prodPurQty"));

			// 2. session
			AuctOrdDetlVO auctOrdDetlVO = auctCart.get(auctActProdNo);
			Integer price = auctOrdDetlVO.getPrice();
			Integer sum = price * prodPurQty;
			auctOrdDetlVO.setProdPurQty(prodPurQty);
			auctOrdDetlVO.setSumPrice(sum);

			auctCart.put(auctActProdNo, auctOrdDetlVO);

			// 3. front end
			PrintWriter out = res.getWriter();
			JSONObject obj = new JSONObject();
			try {
				obj.put("sum", sum);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(obj);
			out.flush();
			out.close();

		}

		// 點選結帳
		if ("checkOut".equals(action)) {
			// 會員登入

			Integer cartSum = countTotalPrice(auctCart);
			TransRecService transRecSvc=new TransRecService();
			Integer nowMoney=transRecSvc.getDeposit(memNo);
			// 驗證會員是否登入
			if (memVOIsNull) {
				// 導入會員登入網址
				String failurl = "/frontend/member/login.html";
				RequestDispatcher failView = req.getRequestDispatcher(failurl);
				failView.forward(req, res);
				return;
				// 如果有登入就驗證會員錢包錢包是否夠錢
			} else if (cartSum>nowMoney) {
				session.setAttribute("from", "auctProd");//下訂單前的拍賣商品的物件
				req.setAttribute("cartSum", cartSum);//放入缺多少錢(至少需要儲值的錢)
				req.setAttribute("nowMoney", nowMoney);//放入缺多少錢(至少需要儲值的錢)
				String failurl = "/frontend/transRec/saveMoney1.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(failurl);
				failView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 **************************************/
			// 如果不為購物車空值導向填寫訂單資訊畫面
			// 更新商品數量

			AuctActProdService auctProdSvc = new AuctActProdService();
			List<AuctActProdVO> auctProds = auctProdSvc.getAll();
			AuctProdService auctProd = new AuctProdService();
			List<AuctProdVO> prods = auctProd.getallProd();

			Map<Integer, String> nameMap = generateProdNameMap();
			req.setAttribute("nameMap", nameMap);
			req.setAttribute("summary", cartSum);
			
			session.setAttribute("auctCart", auctCart);
			String addProdurl = "/frontend/auctAct/orderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(addProdurl);
			successView.forward(req, res);
		}
		
		
			//導回訂單頁面繼續填寫
		
		if("checkAgain".equals(action)){
			//從存錢頁面導轉
			String from = (String) session.getAttribute("from");
			session.removeAttribute("from");
			
			if("auctProd".equals(from)) { // 拍賣商城來的!!!
				Integer sum = countTotalPrice(auctCart);
				Map<Integer, String> nameMap = generateProdNameMap();
				req.setAttribute("nameMap", nameMap);
				req.setAttribute("summary", sum);
				session.setAttribute("auctCart", auctCart);
				String addProdurl = "/frontend/auctAct/orderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(addProdurl);
				successView.forward(req, res);
			} 
		}
		
		
		
		//給AJAX錢包餘額		
		if("ajaxCheckMoney".equals(action)) {
			Integer nowMoney=transRecSvc.getDeposit(memNo);
			//給ajax的值
			PrintWriter out = res.getWriter();
			//用JSON傳
			JSONObject moneyObj = new JSONObject();
			try {
				moneyObj.put("nowMoney", nowMoney);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(moneyObj);
			out.flush();
			out.close();
		}
	}

	private Map<Integer, String> generateProdNameMap() {
		List<AuctActProdVO> auctProds = auctProdSvc.getAll();
		List<AuctProdVO> prods = auctProd.getallProd();
		Map<Integer, String> nameMap = new HashMap<>();
		Map<Integer, AuctProdVO> prodMap = getProdMap(prods);

		for (AuctActProdVO auctProd : auctProds) {
			Integer auctActProdNo = auctProd.getAuctActProdNo();
			Integer prodNo = auctProd.getAuctProdNo();
			AuctProdVO prodVO = prodMap.get(prodNo);
			String prodName = prodVO.getAuctProdName();
			nameMap.put(auctActProdNo, prodName);
		}
		return nameMap;
	}

	private Map<Integer, AuctProdVO> getProdMap(List<AuctProdVO> prods) {
		Map<Integer, AuctProdVO> prodMap = new HashMap<>();
		for (AuctProdVO vo : prods) {
			prodMap.put(vo.getAuctProdNo(), vo);
		}

		return prodMap;
	}

	private Integer countTotalPrice(Map<Integer, AuctOrdDetlVO> cart) {
		Integer sum = 0;

		for (AuctOrdDetlVO detlVO : cart.values()) {
			Integer quantity = detlVO.getProdPurQty();
			Integer price = detlVO.getPrice();

			Integer prodSum = quantity * price;
			sum += prodSum;

			detlVO.setSumPrice(prodSum);
			cart.put(detlVO.getAuctActProdNo(), detlVO);
		}
		return sum;
	}
}

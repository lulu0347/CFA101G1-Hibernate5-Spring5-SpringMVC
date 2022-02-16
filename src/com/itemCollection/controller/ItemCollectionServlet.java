package com.itemCollection.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itemCollection.model.*;

@Controller
public class ItemCollectionServlet {

	// －－－－－－－－－－－－－(後端)以會員編號列出該會員所持有的所有收藏
	// request from select_page.jsp
	@RequestMapping("/listByMemNo")
	public String listByMemNo(@RequestParam("memNo") Integer memNo, Model model) {

		ItemCollectionService ICS = new ItemCollectionService();
		List<ItemCollectionVO> list = new ArrayList<ItemCollectionVO>();
		list = ICS.getOneCollection(memNo);

		model.addAttribute("list", list);

		return "backend/itemCollection/listOneCollection";
	}
	
	// －－－－－－－－－－－－－(前端)會員取消商品收藏
	// request from listAllCollectionByMemNo.jsp
	@RequestMapping("/deleteCollection")
	public String deleteCollection(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo, Model model) {
		
		ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
		ItemCollectionSvc.deleteCollection(itemNo, memNo);

		return "frontend/listAllCollectionByMemNo";
	}
	

	// ---------------------(前端)新增一個商品到收藏列表 一般商城首頁
	// request from EShop.jsp
	@RequestMapping("/addCollectionFromEshop")
	public String addCollectionFromEshop(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo,
			Model model) {
		System.out.println("addCollectionFromEshop : memNo" + memNo);
		System.out.println("addCollectionFromEshop : itemNo" + itemNo);
		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/EShop";
	}

	// ---------------------(前端)新增一個商品到收藏列表 Phone
	// request from listByPhone.jsp
	@RequestMapping("/addCollectionFromPhone")
	public String addCollectionFromPhone(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo,
			Model model) {

		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/listByPhone";
	}

	// ---------------------(前端)新增一個商品到收藏列表 Computer
	// request from listByComputer.jsp
	@RequestMapping("/addCollectionFromComputer")
	public String addCollectionFromComputer(@RequestParam("memNo") Integer memNo,
			@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/listByComputer";
	}

	// ---------------------(前端)新增一個商品到收藏列表 Camera
	// request from listByCamera.jsp
	@RequestMapping("/addCollectionFromCamera")
	public String addCollectionFromCamera(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo,
			Model model) {

		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/listByCamera";
	}

	// ---------------------(前端)新增一個商品到收藏列表 Watch
	// request from listByWatch.jsp
	@RequestMapping("/addCollectionFromWatch")
	public String addCollectionFromWatch(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo,
			Model model) {

		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/listByWatch";
	}

	// ---------------------(前端)新增一個商品到收藏列表 Other
	// request from listByOther.jsp
	@RequestMapping("/addCollectionFromOther")
	public String addCollectionFromOther(@RequestParam("memNo") Integer memNo, @RequestParam("itemNo") Integer itemNo,
			Model model) {

		ItemCollectionService itemCollectionSvc = new ItemCollectionService();

		Long collect = itemCollectionSvc.getcount(itemNo, memNo);

		if (collect == 0) {
			ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
			ItemCollectionId itemCollectionId = new ItemCollectionId();
			itemCollectionId.setMemNo(memNo);
			itemCollectionId.setItemNo(itemNo);
			itemCollectionVO.setId(itemCollectionId);
			itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
			itemCollectionSvc.getcount(itemNo, memNo);

			model.addAttribute("itemCollectionVO", itemCollectionVO);
		}

		else if (collect == 1) {
			ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
			ItemCollectionSvc.deleteCollection(itemNo, memNo);
			itemCollectionSvc.getcount(itemNo, memNo);
		}

		return "frontend/listByOthers";
	}

}


//---------------------(前端)新增一個商品到收藏列表  For 模糊搜尋---------------------------------
//		if ("addCollection2".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			HttpSession session = req.getSession();
//			MemberVO memVO = (MemberVO) session.getAttribute("user");
//			try {
//				Integer memNo = new Integer(req.getParameter("memNo"));
//
//				Integer itemNo = new Integer(req.getParameter("itemNo"));
//
//				ItemCollectionService itemCollectionSvc = new ItemCollectionService();
//
//				Long collect = itemCollectionSvc.getcount(itemNo, memNo);
//
//				if (collect == 0) {
//					ItemCollectionVO itemCollectionVO = new ItemCollectionVO();
//					ItemCollectionId itemCollectionId = new ItemCollectionId();
//					itemCollectionId.setMemNo(memNo);
//					itemCollectionId.setItemNo(itemNo);
//					itemCollectionVO.setId(itemCollectionId);
//					itemCollectionVO = itemCollectionSvc.addCollection(itemCollectionId);
//					itemCollectionSvc.getcount(itemNo, memNo);
//					Map<String, String[]> map = req.getParameterMap();
//
//					ItemService itemSvc = new ItemService();
//					List<ItemVO> list = itemSvc.getAll(map);
//					req.setAttribute("listItem_ByCompositeQuery", list);
//					String url = "/frontend/EShop.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFavorite.jsp
//					successView.forward(req, res);
//
//				} else if (collect == 1) {
//					ItemCollectionService ItemCollectionSvc = new ItemCollectionService();
//					ItemCollectionSvc.deleteCollection(itemNo, memNo);
//					itemCollectionSvc.getcount(itemNo, memNo);
//					Map<String, String[]> map = req.getParameterMap();
//
//					ItemService itemSvc = new ItemService();
//					List<ItemVO> list = itemSvc.getAll(map);
//					req.setAttribute("listItem_ByCompositeQuery", list);
//					String url = "/frontend/EShop.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFavorite.jsp
//					successView.forward(req, res);
//				}
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/listItem_ByCompositeQuery.jsp");
//				failureView.forward(req, res);
//			}
//		}


package com.item.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.item.model.*;
import com.itemPic.model.ItemPicService;
import com.itemPic.model.ItemPicVO;
import com.productKind.model.ProductKindVO;

@Controller
public class ItemServlet {

	// －－－－－－－－－－－－－(後端)輸入商品編號查詢單一商品
	// request from select_page.jsp
	@RequestMapping("/enterItemNo")
	public String getOne_For_Display_enterItemNo(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "backend/item/listOneItem2";
	}

	// －－－－－－－－－－－－－(後端)選擇商品編號查詢單一商品
	// request from select_page.jsp
	@RequestMapping("/selectItemNo")
	public String getOne_For_Display_selectItemNo(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "backend/item/listOneItem2";
	}
	
	// －－－－－－－－－－－－－(後端)選擇商品名稱查詢單一商品
	// request from select_page.jsp
	@RequestMapping("/selectItemName")
	public String getOne_For_Display_sselectItemName(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "backend/item/listOneItem2";
	}

	// －－－－－－－－－－－－－(後端)前置作業 : 選擇商品編號準備更新單一商品
	// request from listAllItem.jsp
	@RequestMapping("/getOneItemToUpdate")
	public String getOne_For_Update(@RequestParam("itemNo") Integer itemNo, ModelMap modelMap) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);
		System.out.println("itemServlet - getOneItemToUpdate取得的itemName" + itemVO.getItemName());

		modelMap.addAttribute("itemVO", itemVO);
		System.out.println("準備轉交update_item_input");
		return "backend/item/update_item_input";
	}

	// －－－－－－－－－－－－－(後端)取得單一商品後進行更新
	// request from update_item_input.jsp
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public String update(ModelMap modelMap, @Valid ItemVO itemVO) {
		System.out.println("進入update");
		ItemService itemSvc = new ItemService();
		System.out.println("itemServlet - update取得的itemNo" + itemVO.getItemNo());
		System.out.println("itemServlet - update取得的itemName" + itemVO.getItemName());
		System.out.println("itemServlet - update取得的itemPrice" + itemVO.getItemPrice());
		itemSvc.updateItemForMVC(itemVO);
		
		
		
		modelMap.addAttribute("success", "- (修改成功)");

		return "backend/item/listAllItem";
	}
	
	// －－－－－－－－－－－－－(後端)前置作業 : 進入上架商品頁面
	@RequestMapping("/additemPreWork")
	public String additemPreWork(ModelMap modelmap) {
		modelmap.addAttribute("itemVO", new ItemVO());
		System.err.println("itemVO設置完成 轉跳至新增頁面");
		return "backend/item/addItem";
	}
	
	// －－－－－－－－－－－－－(後端)前置作業 :將productKind製成map用於任一商品頁面的<form:select>中
	@ModelAttribute("kindData")
	protected Map<Integer, String> referenceKindData() {
		Map<Integer, String> mapData = new LinkedHashMap<Integer, String>();
		mapData.put(1, "手機");
		mapData.put(2, "電腦");
		mapData.put(3, "手錶");
		mapData.put(4, "相機");
		mapData.put(5, "配件");
		return mapData;
	}
	
	// －－－－－－－－－－－－－(後端)上架商品
	// request from addItem.jsp
	@RequestMapping(method = RequestMethod.POST, value = "addItem", produces = "text/plain;charset=UTF-8")
	public String addItem(ModelMap modelmap,@Valid ItemVO itemVO) {
		
		// 先新增商品
		ItemService itemSvc = new ItemService();
		itemSvc.addItemForMVC(itemVO);
		
		modelmap.addAttribute("success", "- (新增成功)");
		
		return "backend/item/listAllItem";
	}

	// －－－－－－－－－－－－－(後端)刪除商品
	// request from listAllItem.jsp
	@RequestMapping("/deleteItem")
	public String deleteItem(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		itemSvc.deleteItem(itemNo);

		return "backend/item/listAllItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 一般商城首頁
	// request from EShop.jsp
	@RequestMapping("/getOneItemForViewFromEShop")
	public String getOneItemForView_FromEShop(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 會員收藏列表頁面
	// request from listAllCollectionByMemNo.jsp
	@RequestMapping("/getOneItemForViewFromListAllCollection")
	public String getOneItemForView_FromListAllCollection(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 相機
	// request from listByCamera.jsp
	@RequestMapping("/getOneItemForViewFromListByCamera")
	public String getOneItemForView_FromListByCamera(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 電腦
	// request from listByComputer.jsp
	@RequestMapping("/getOneItemForViewFromListByComputer")
	public String getOneItemForView_FromListByComputer(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 其他
	// request from listByOthers.jsp
	@RequestMapping("/getOneItemForViewFromListByOthers")
	public String getOneItemForView_FromListByOthers(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 手機
	// request from listByPhone.jsp
	@RequestMapping("/getOneItemForViewFromListByPhone")
	public String getOneItemForView_FromListByPhone(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}

	// －－－－－－－－－－－－－(前端)查看商品 手錶
	// request from listByWatch.jsp
	@RequestMapping("/getOneItemForViewFromListByWatch")
	public String getOneItemForView_FromListByWatch(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemService itemSvc = new ItemService();
		ItemVO itemVO = itemSvc.getOneItem(itemNo);

		model.addAttribute("itemVO", itemVO);

		return "frontend/listOneItem";
	}
}

//-------------模糊搜尋------------------------------------
//		if ("listItem_ByCompositeQuery".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("取得的ItemName為 : ");
//			System.out.println(req.getParameter("ItemName"));
//			System.out.println("-------------------");
//			try {
//				Map<String, String[]> map = req.getParameterMap();
//				System.out.println("從ITServlet來的:" + map);
//				ItemService itemSvc = new ItemService();
//				List<ItemVO> list = itemSvc.getAll(map);
//
//				if (list.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/EShop.jsp");
//					failureView.forward(req, res);
//				}
//
//				req.setAttribute("listItem_ByCompositeQuery", list);
//				RequestDispatcher successView = req.getRequestDispatcher("/frontend/listItem_ByCompositeQuery.jsp");
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//			}
//		}

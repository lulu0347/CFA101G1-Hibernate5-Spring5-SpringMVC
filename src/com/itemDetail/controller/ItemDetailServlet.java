package com.itemDetail.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itemDetail.model.*;

@Controller
public class ItemDetailServlet {

	// －－－－－－－－－－－－－(後端)輸入商品編號與訂單編號準備更新單一訂單明細
	// request from updateDetail.jsp
	@RequestMapping("/selectOneDetailToUpdte")
	public String selectOneDetailToUpdte(@RequestParam("orderNo") Integer orderNo,
			@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemDetailService itemDetailSvc = new ItemDetailService();
		ItemDetailVO itemDetailVO = itemDetailSvc.findByPrimaryKey(orderNo, itemNo);

		model.addAttribute("itemDetailVO", itemDetailVO);

		return "backend/itemDetail/update_detail_input";
	}

	// －－－－－－－－－－－－－(後端)輸入商品編號與訂單編號準備更新單一訂單明細
	// request from update_detail_input.jsp
	@RequestMapping("/updateOneDetail")
	public String updateOneDetail(@RequestParam("orderNo") Integer orderNo, 
			@RequestParam("itemNo") Integer itemNo,
			@RequestParam("itemSales") Integer itemSales,
			Model model) {
		
		ItemDetailVO itemDetailVO = new ItemDetailVO();
		ItemDetailId itemDetailId = new ItemDetailId();
		itemDetailId.setItemNo(itemNo);
		itemDetailId.setOrderNo(orderNo);
		itemDetailVO.setItemSales(itemSales);
		itemDetailVO.setId(itemDetailId);
		
		ItemDetailService itemDetailSvc = new ItemDetailService();
		itemDetailVO = itemDetailSvc.updateItemDetail(itemSales, orderNo, itemNo);

		model.addAttribute("itemDetailVO", itemDetailVO);
		
		return"backend/itemDetail/listAll_ItemDetail";
	}
	
	// －－－－－－－－－－－－－(後端)以訂單編號查詢單一訂單所有明細
	// request from select_page.jsp
	@RequestMapping("/getAllDetailByOrderNo")
	public String getAllDetailByOrderNo(@RequestParam("orderNo") Integer orderNo, Model model) {
		
		ItemDetailService itemDetailSvc = new ItemDetailService();
		List<ItemDetailVO> list = new ArrayList<ItemDetailVO>();
		list = itemDetailSvc.GetAllByOrderNo(orderNo);
		
		model.addAttribute("list",list);
		
		return "backend/itemDetail/listOneOrderDetail";
	}
}

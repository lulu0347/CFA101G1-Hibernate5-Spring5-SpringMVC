package com.itemOrder.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itemOrder.model.ItemOrderService;
import com.itemOrder.model.ItemOrderVO;
import com.member.model.MemberVO;

@Controller
public class ItemOrderServlet {

	// －－－－－－－－－－－－－(後端)新增訂單 尚無需求未實裝
	// request from
//	@RequestMapping("/待補)
	public String addOrder(@RequestParam("memNo") Integer memNo, 
			@RequestParam("tranTime") Date tranTime,
			@RequestParam("orderTotal") Integer orderTotal, 
			@RequestParam("orderState") Integer orderState,
			@RequestParam("receiverName") String receiverName, 
			@RequestParam("receiverAddress") String receiverAddress,
			@RequestParam("receiverPhone") String receiverPhone, Model model) {

		ItemOrderVO itemOrderVO = new ItemOrderVO();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemNo(memNo);
		itemOrderVO.setMemberVO(memberVO);
		itemOrderVO.setTranTime(tranTime);
		itemOrderVO.setOrderTotal(orderTotal);
		itemOrderVO.setOrderState(orderState);
		itemOrderVO.setReceiverName(receiverName);
		itemOrderVO.setReceiverAddress(receiverAddress);
		itemOrderVO.setReceiverPhone(receiverPhone);

		ItemOrderService itemOrderSvc = new ItemOrderService();
		itemOrderSvc.addOrder(memberVO, tranTime, orderTotal, orderState, receiverName, receiverAddress, receiverPhone);

		return "backend/itemOrder/listAllOrder";

	}

	// －－－－－－－－－－－－－(後端)輸入訂單編號搜尋單一訂單
	// request from select_page.jsp
	@RequestMapping("/enterListByOrderNo")
	public String enterListByOrderNo(@RequestParam("orderNo") Integer orderNo, Model model) {

		ItemOrderService itemOrderSvc = new ItemOrderService();
		ItemOrderVO itemOrderVO = itemOrderSvc.findByOrderNo(orderNo);

		model.addAttribute("itemOrderVO", itemOrderVO);

		return "backend/itemOrder/listOneOrderByOrderNo";
	}

	// －－－－－－－－－－－－－(後端)選擇訂單編號搜尋單一訂單
	// request from select_page.jsp
	@RequestMapping("/selectListByOrderNo")
	public String selectListByOrderNo(@RequestParam("orderNo") Integer orderNo, Model model) {

		ItemOrderService itemOrderSvc = new ItemOrderService();
		ItemOrderVO itemOrderVO = itemOrderSvc.findByOrderNo(orderNo);

		model.addAttribute("itemOrderVO", itemOrderVO);

		return "backend/itemOrder/listOneOrderByOrderNo";
	}

	// －－－－－－－－－－－－－(後端)以訂單編號更新單一訂單
	// request from update_order_input.jsp
	@RequestMapping("/selectOneOrderToUpdate")
	public String selectOneOrderToUpdate(@RequestParam("orderNo") Integer orderNo, 
			@RequestParam("memNo") Integer memNo,
			@RequestParam("tranTime") Date tranTime, 
			@RequestParam("orderTotal") Integer orderTotal,
			@RequestParam("orderState") Integer orderState, 
			@RequestParam("receiverName") String receiverName,
			@RequestParam("receiverAddress") String receiverAddress,
			@RequestParam("receiverPhone") String receiverPhone, Model model) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemNo(memNo);
		ItemOrderService itemOrderSvc = new ItemOrderService();
		ItemOrderVO itemOrderVO = itemOrderSvc.findByOrderNo(orderNo);
		itemOrderVO.setMemberVO(memberVO);
		itemOrderVO.setTranTime(tranTime);
		itemOrderVO.setOrderTotal(orderTotal);
		itemOrderVO.setOrderState(orderState);
		itemOrderVO.setReceiverName(receiverName);
		itemOrderVO.setReceiverAddress(receiverAddress);
		itemOrderVO.setReceiverPhone(receiverPhone);

		itemOrderSvc.updateByOrderNo(orderNo, memberVO, tranTime, orderTotal, orderState, receiverName, receiverAddress,
				receiverPhone);

		model.addAttribute("itemOrderVO", itemOrderVO);

		return "backend/itemOrder/listOneOrder";
	}

	// －－－－－－－－－－－－－(後端)刪除一筆訂單
	/*
	 * request from deleteOrder.jsp , listAllOrder.jsp , listAllOrderByCancel.jsp ,
	 * listAllOrderByNotShipped.jsp , listAllOrderByReceipt.jsp ,
	 * listAllOrderByReturn.jsp , listAllOrderByShipped.jsp ,
	 * listOneOrderByMemNo.jsp , listOneOrderByOrderNo.jsp
	 */
	@RequestMapping("/deleteOneOrder")
	public String deleteOneOrder(@RequestParam("orderNo") Integer orderNo, Model model) {
		ItemOrderService itemOrderSvc = new ItemOrderService();
		itemOrderSvc.deleteOrder(orderNo);

		return "backend/itemOrder/listAllOrder";
	}

	// －－－－－－－－－－－－－(後端)輸入會員編號找尋此會員訂單
	// request from select_page.jsp
	@RequestMapping("/enterMemNoToFindOrder")
	public String enterMemNoToFindOrder(@RequestParam("memNo") Integer memNo, Model model) {
		ItemOrderService itemOrderSvc = new ItemOrderService();
		List<ItemOrderVO> list = new ArrayList<ItemOrderVO>();
		list = itemOrderSvc.gerOrderByMemNo(memNo);

		model.addAttribute("list", list);

		return "backend/itemOrder/listOneOrderByMemNo";
	}

	// －－－－－－－－－－－－－(後端)選擇會員編號找尋此會員訂單
	// request from select_page.jsp
	@RequestMapping("/selectMemNoToFindOrder")
	public String selectMemNoToFindOrder(@RequestParam("memNo") Integer memNo, Model model) {
		ItemOrderService itemOrderSvc = new ItemOrderService();
		List<ItemOrderVO> list = new ArrayList<ItemOrderVO>();
		list = itemOrderSvc.gerOrderByMemNo(memNo);

		model.addAttribute("list", list);

		return "backend/itemOrder/listOneOrderByMemNo";
	}

	// －－－－－－－－－－－－－(前端)會員尋找自己的訂單 尚無需求未實裝
	// request from
//	@RequestMapping("/待補)
	public String viewMyOrder(@RequestParam("memNo") Integer memNo, Model model) {

		ItemOrderService itemOrderSvc = new ItemOrderService();
		List<ItemOrderVO> list = new ArrayList<ItemOrderVO>();
		list = itemOrderSvc.getAllOrderByMemNo(memNo);

		model.addAttribute("list", list);

		return "frontend/listAllOrderByMemNo";
	}
}

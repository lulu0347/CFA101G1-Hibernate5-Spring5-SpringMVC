package com.itemPic.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.item.model.*;
import com.itemPic.model.ItemPicService;
import com.itemPic.model.ItemPicVO;

@Controller
public class ItemPicServlet {

	// －－－－－－－－－－－－－(後端)以商品照片編號顯示圖片
	/*
	 * request from deleteItem.jsp , listAllItem.jsp , listOneItem.jsp ,
	 * listOneItem2.jsp , updateItem.jsp , listAll_ItemPic.jsp , listByItemNo.jsp ,
	 * listOneItemPic.jsp , update_item_photo_input.jsp , Cart.jsp , Checkout.jsp ,
	 * EShop.jsp , listAllCollectionByMemNo.jsp , listAllDetailByOrderNo.jsp ,
	 * listByCamera.jsp , listByComputer.jsp , listByOthers.jsp , listByPhone.jsp ,
	 * listByWatch.jsp , listItem_ByCompositeQuery.jsp , listOneItem.jsp ,
	 * listOneItem2.jsp
	 */
	@RequestMapping("/showItemPic")
	public void showItemPic(@RequestParam("itemPicNo") Integer itemPicNo, Model model, HttpServletResponse res)
			throws IOException {

		ItemPicVO itemPicVO = new ItemPicVO();
		ItemPicService itemPicSvc = new ItemPicService();
		itemPicVO = itemPicSvc.findByItemPicNo(itemPicNo);
		byte[] itemPic = itemPicVO.getItemPic();

		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();

		out.write(itemPic);
		out.close();

	}

	// －－－－－－－－－－－－－(後端)以商品編號顯示圖片
	// request from Cart.jsp , CheckOut.jsp
	@RequestMapping("/showItemPicByItemNo")
	public void showItemPicByItemNo(@RequestParam("itemNo") Integer itemNo, Model model, HttpServletResponse res)
			throws IOException {

		ItemPicVO itemPicVO = new ItemPicVO();
		ItemPicService itemPicSvc = new ItemPicService();
		itemPicVO = itemPicSvc.findByItemItemNo(itemNo);
		byte[] itemPic = itemPicVO.getItemPic();

		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();

		out.write(itemPic);
		out.close();

	}

	// ----------------(後端)新增圖片前置作業,選擇單一商品並進入新增頁面
	// request from select_page.jsp
	@RequestMapping("/selectItemNoToAddPic")
	public String selectItemNoToAddPic(Model model, @RequestParam("itemNo") Integer itemNo) {

		// 測試中 暫定itemVO
		model.addAttribute("itemVO", new ItemService().getOneItem(itemNo));

		return "backend/itemPic/addItemPic";
	}

	// ----------------(後端)新增圖片
	// request from addItemPic.jsp
	@PostMapping("/addPic")
	public String addPic(@RequestParam("itemNo") Integer itemNo, @RequestParam("itemPic") MultipartFile[] parts,
			Model model, HttpServletRequest request) throws IOException, ServletException {
		ItemPicVO itemPicVO = new ItemPicVO();
		ItemVO itemVO = new ItemVO();

		itemVO.setItemNo(itemNo);
		itemPicVO.setItemVO(itemVO);

		if (!parts[0].isEmpty()) {
			for (MultipartFile multipartFile : parts) {
				String filename = multipartFile.getOriginalFilename();
				byte[] buf = multipartFile.getBytes();
				OutputStream out = new FileOutputStream(request.getServletContext().getRealPath("/images/") + filename);
				out.write(buf);
				itemPicVO.setItemPic(buf);
				out.close();
//				System.out.println("buf.length=" + buf.length);
			}
		}

		ItemPicService itemPicSvc = new ItemPicService();
		itemPicSvc.addPicForMVC(itemPicVO);
		model.addAttribute("success", "新增成功");
		return "backend/itemPic/listAll_ItemPic";
	}

	// ----------------(後端)依商品照片編號查詢並進入更新
	// request from listAll_ItemPic.jsp , listByItemNo.jsp , listOneItemPic.jsp
	@RequestMapping("/findByItemPicNoToUpdate")
	public String findByItemPicNoToUpdate(@RequestParam("itemPicNo") Integer itemPicNo, Model model) {

		ItemPicService itemPicSvc = new ItemPicService();
		ItemPicVO itemPicVO = itemPicSvc.findByItemPicNo(itemPicNo);

		model.addAttribute("itemPicVO", itemPicVO);

		return "backend/itemPic/update_item_photo_input";
	}

	// ------------------(後端)更新商品照片
	// request from update_item_photo_input.jsp
	@RequestMapping("/updatePic")
	public String updatePic(@RequestParam("itemPicNo") Integer itemPicNo, @RequestParam("itemNo") Integer itemNo,
			@RequestParam("itemPic") MultipartFile[] parts, HttpServletRequest request, Model model)
			throws IOException, ServletException {
		System.out.println("-----------");
		System.out.println(itemPicNo);
		System.out.println(itemNo);
		System.out.println(parts);
		System.out.println("-----------");
		ItemPicService itemPicSvc = new ItemPicService();
		ItemPicVO itemPicVO = itemPicSvc.findByItemPicNo(itemPicNo);

		// 更新照片
			if (!parts[0].isEmpty()) {
				for (MultipartFile multipartFile : parts) {
					String filename = multipartFile.getOriginalFilename();
					byte[] buf = multipartFile.getBytes();
					OutputStream out = new FileOutputStream(
							request.getServletContext().getRealPath("/images/") + filename);
					out.write(buf);
					itemPicVO.setItemPic(buf);
					out.close();
//				System.out.println("buf.length=" + buf.length);
				}
			}
		ItemVO itemVO = new ItemVO();
		itemVO.setItemNo(itemNo);
		itemPicVO.setItemPicNo(itemPicNo);
		itemPicVO.setItemVO(itemVO);

		itemPicSvc.updatePic(itemPicVO);

		model.addAttribute("itemPicVO", itemPicVO);

		return "backend/itemPic/listAll_ItemPic";
	}

	// ------------------(後端)刪除商品照片
	// request from listAll_ItemPic.jsp
	@RequestMapping("/deletePic")
	public String deletePic(@RequestParam("itemPicNo") Integer itemPicNo, Model model) {

		ItemPicService itemPicSvc = new ItemPicService();
		itemPicSvc.deletePic(itemPicNo);

		return "backend/itemPic/listAll_ItemPic";
	}

	// ------------------依商品照片編號查詢該商品資訊
	// request from select_page.jsp
	@RequestMapping("/findByItemPicNo")
	public String findByItemPicNo(@RequestParam("itemPicNo") Integer itemPicNo, Model model) {

		ItemPicService itemPicSvc = new ItemPicService();
		ItemPicVO itemPicVO = itemPicSvc.findByItemPicNo(itemPicNo);

		model.addAttribute("itemPicVO", itemPicVO);

		return "backend/itemPic/listOneItemPic";
	}

	// ------------------依商品編號查詢該商品資訊
	// request from select_page.jsp
	@RequestMapping("/findByItemNo")
	public String findByItemNo(@RequestParam("itemNo") Integer itemNo, Model model) {

		ItemPicService itemPicSvc = new ItemPicService();
		List<ItemPicVO> list = new ArrayList<ItemPicVO>();
		list = itemPicSvc.findByItemNo(itemNo);

		model.addAttribute("list", list);

		return "backend/itemPic/listByItemNo";
	}
}

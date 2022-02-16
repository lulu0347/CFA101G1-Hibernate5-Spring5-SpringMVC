package com.productKind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.productKind.model.*;

@Controller
public class ProductKindServlet {

	// ------新增商品類別
	// request from addProductKind.jsp
	@RequestMapping("/addProductKind")
	public String addProductKind(@RequestParam("kindName") String kindName, Model model) {

		ProductKindVO productKindVO = new ProductKindVO();
		productKindVO.setKindName(kindName);
		ProductKindService productKindSvc = new ProductKindService();
		productKindVO = productKindSvc.addProductKind(kindName);
		model.addAttribute("productKindVO", productKindVO);

		return "backend/productKind/listAllProductKind";
	}

}

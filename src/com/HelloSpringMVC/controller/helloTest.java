package com.HelloSpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloTest {
	
	@RequestMapping("t1")
	public String test(Model model) {
		model.addAttribute("msg","HelloSpringMVC");
		return "frontend/hello";
	}
	
}

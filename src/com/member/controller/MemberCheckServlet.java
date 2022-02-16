package com.member.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.model.*;

@Controller
public class MemberCheckServlet extends HttpServlet {
       
	//----驗證帳號是否被使用
	// request from ??
	@RequestMapping("/member/MemberCheckServlet")
	public void MemberCheckServlet(@RequestParam("memaccount") String memaccount, HttpServletResponse response) throws IOException {
		
		MemberService memSvc = new MemberService();
		MemberVO user = memSvc.checkaccount(memaccount);
		
		if(user == null) {
			response.getWriter().print("1");
		}else {
			response.getWriter().print("0");
		}
		
	}
}
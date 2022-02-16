package com.member.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.model.*;

@Controller
public class MailCheckServlet {
       
	//----驗證帳號是否被使用(驗證信箱是否被使用吧?)
	// request from ??
	@RequestMapping("/member/MailCheckServlet")
	public void MailCheckServlet(@RequestParam("mememail") String mememail,  HttpServletResponse response, Model model) throws IOException {
		
		MemberService memSvc = new MemberService();
		MemberVO email = memSvc.checkemail(mememail);
		
		if(email == null) {
			response.getWriter().print("1");
		}else {
			response.getWriter().print("0");
		}
		
	}
}
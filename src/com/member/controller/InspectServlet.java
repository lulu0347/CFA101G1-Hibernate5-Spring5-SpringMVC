package com.member.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.member.model.*;

@Controller
public class InspectServlet {
	
	//----驗證是否為登入狀態
	//--- request from ??
	@RequestMapping("/member/inspectServlet")
	public void inspectServlet(HttpSession session, HttpServletResponse response) throws IOException {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!= null) {
			response.getWriter().print(user.getMemAccount());
			//編譯器檢查 受檢例外checked exception
		}else {
			response.getWriter().print("0");
		}
	}
}
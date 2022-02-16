package com.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.model.MemberService;

import redis.clients.jedis.Jedis;

@Controller
public class ActiveServlet {
	
	//--------會員信箱驗證
	// request from SendEmail.java?
	@RequestMapping("/member/activeServlet")
	public String activeServlet(@RequestParam("code") String code,@RequestParam("mememail") String mememail,ModelMap modelMap) {
		
		Jedis jedis = new Jedis("localhost",6379);
		String checkCode = jedis.hget(mememail, "email");
		if(code.equals(checkCode)) {
			MemberService service = new MemberService();
			try {
				service.active(mememail);
				modelMap.addAttribute("msg", "帳號啟用成功，請重新登入");
				modelMap.addAttribute("status", true);
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.addAttribute("msg", "帳號啟用失敗請重新申請驗證");
				modelMap.addAttribute("status", false);
			}
		}else {
			modelMap.addAttribute("msg", "帳號啟用失敗請重新申請驗證");
			modelMap.addAttribute("status", false);
		}
		return "frontend/member/active";
	}
}

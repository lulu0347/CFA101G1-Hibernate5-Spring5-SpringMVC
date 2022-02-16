package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.member.model.*;

@Controller
public class MemberServlet {

	// ----(後端)輸入會員編號取得一名會員資訊
	// request from select_page.jsp
	@RequestMapping("/enterMemNoToFindOneMember")
	public String enterMemNoToFindOneMember(@RequestParam("memno") Integer memno, Model model) {

		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.getOneMem(memno);

		model.addAttribute("memVO", memVO);

		return "backend/member/listOneMember";
	}

	// ----(後端)選擇會員編號取得一名會員資訊
	// request from select_page.jsp
	@RequestMapping("/selectMemNoToFindOneMember")
	public String selectMemNoToFindOneMember(@RequestParam("memno") Integer memno, Model model) {

		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.getOneMem(memno);

		model.addAttribute("memVO", memVO);

		return "backend/member/listOneMember";
	}

	// ----(後端)選擇會員名稱取得一名會員資訊
	// request from select_page.jsp
	@RequestMapping("/selectMemNameToFindOneMember")
	public String selectMemNameToFindOneMember(@RequestParam("memno") Integer memno, Model model) {

		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.getOneMem(memno);

		model.addAttribute("memVO", memVO);

		return "backend/member/listOneMember";
	}

	// ----(後端)以會員編號更新一會員資訊
	// request from listAllEmp.jsp
	@RequestMapping("/getOneMemToUpdate")
	public String getOneMemToUpdate(@RequestParam("memno") Integer memno, Model model) {

		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.getOneMem(memno);

		model.addAttribute("memVO", memVO);

		return "backend/member/update_member_input";
	}

	// ----(後端)更新會員資訊
	// request from update_member_input.jsp
	@RequestMapping("/updateMember")
	public String updateMember(@RequestParam("memno") Integer memno, @RequestParam("memaccount") String memaccount,
			@RequestParam("mempassword") String mempassword, @RequestParam("memstatus") int memstatus,
			@RequestParam("memvrfed") Integer memvrfed, @RequestParam("memname") String memname,
			@RequestParam("memmobile") String memmobile, @RequestParam("memcity") String memcity,
			@RequestParam("memdist") String memdist, @RequestParam("memadd") String memadd,
			@RequestParam("mememail") String mememail, @RequestParam("membirth") java.sql.Date membirth,
			@RequestParam("usderstatus") int usderstatus, @RequestParam("ecash") int ecash, Model model) {

		MemberVO memVO = new MemberVO();
		memVO.setMemNo(memno);
		memVO.setMemAccount(memaccount);
		memVO.setMemPassword(mempassword);
		memVO.setMemStatus(memstatus);
		memVO.setMemVrfed(memvrfed);
		memVO.setMemName(memname);
		memVO.setMemMobile(memmobile);
		memVO.setMemCity(memcity);
		memVO.setMemDist(memdist);
		memVO.setMemAdd(memadd);
		memVO.setMemEmail(mememail);
		memVO.setMemBirth(membirth);
		memVO.setUsderStatus(usderstatus);
		memVO.setEcash(ecash);

		MemberService memSvc = new MemberService();
		memVO = memSvc.updateMem(memno, memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity,
				memdist, memadd, mememail, membirth, membirth, usderstatus, ecash);

		model.addAttribute("memVO", memVO);

		return "backend/member/listOneMember";
	}

	// ----(前端)會員自行更新資訊
	// request from memberUpdte.jsp
	@RequestMapping("/memberUpdte")
	public String memberUpdate(@RequestParam("memno") Integer memno, @RequestParam("memaccount") String memaccount,
			@RequestParam("mempassword") String mempassword, @RequestParam("memstatus") int memstatus,
			@RequestParam("memvrfed") Integer memvrfed, @RequestParam("memname") String memname,
			@RequestParam("memmobile") String memmobile, @RequestParam("memcity") String memcity,
			@RequestParam("memdist") String memdist, @RequestParam("memadd") String memadd,
			@RequestParam("mememail") String mememail, @RequestParam("membirth") java.sql.Date membirth,
			@RequestParam("usderstatus") int usderstatus, @RequestParam("ecash") int ecash, Model model,
			HttpServletRequest req) {

		MemberVO memVO = new MemberVO();

		memVO.setMemAccount(memaccount);
		memVO.setMemPassword(mempassword);
		memVO.setMemStatus(memstatus);
		memVO.setMemVrfed(memvrfed);
		memVO.setMemName(memname);
		memVO.setMemMobile(memmobile);
		memVO.setMemCity(memcity);
		memVO.setMemDist(memdist);
		memVO.setMemAdd(memadd);
		memVO.setMemEmail(mememail);
		memVO.setMemBirth(membirth);
		memVO.setUsderStatus(usderstatus);
		memVO.setEcash(ecash);
		memVO.setMemNo(memno);

		MemberService memSvc = new MemberService();
		memVO = memSvc.memberUpdate(memno, memaccount, mempassword, memstatus, memvrfed, membirth, memname, memmobile,
				memcity, memdist, memadd, mememail, membirth, membirth, usderstatus, ecash);

		HttpSession session = req.getSession();
		session.setAttribute("user", memVO);

		return "frontend/member/memberCenter";
	}

	// ----(前端)新增會員
	// request from addMember.jsp
	@RequestMapping("/insert")
	public String insert(@RequestParam("memaccount") String memaccount, @RequestParam("mempassword") String mempassword,
			@RequestParam("memstatus") int memstatus, @RequestParam("memvrfed") Integer memvrfed,
			@RequestParam("memname") String memname, @RequestParam("memmobile") String memmobile,
			@RequestParam("memcity") String memcity, @RequestParam("memdist") String memdist,
			@RequestParam("memadd") String memadd, @RequestParam("mememail") String mememail,
			@RequestParam("memjointime") java.sql.Date memjointime, @RequestParam("membirth") java.sql.Date membirth,
			@RequestParam("usderstatus") int usderstatus, @RequestParam("ecash") int ecash, Model model) {

		MemberVO memVO = new MemberVO();

		memVO.setMemAccount(memaccount);
		memVO.setMemPassword(mempassword);
		memVO.setMemStatus(memstatus);
		memVO.setMemVrfed(memvrfed);
		memVO.setMemName(memname);
		memVO.setMemMobile(memmobile);
		memVO.setMemCity(memcity);
		memVO.setMemDist(memdist);
		memVO.setMemAdd(memadd);
		memVO.setMemEmail(mememail);
		memVO.setMemBirth(membirth);
		memVO.setMemJointime(memjointime);
		memVO.setUsderStatus(usderstatus);
		memVO.setEcash(ecash);

		MemberService memSvc = new MemberService();
		memVO = memSvc.addMem(memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist,
				memadd, mememail, membirth, memjointime, usderstatus, ecash);

		return "backend/member/listAllMember";
	}

	// ----(前端)會員註冊
	// request from register.html
	@RequestMapping("/registerInsert")
	public void registerInsert(@RequestParam("memaccount") String memaccount,
			@RequestParam("mempassword") String mempassword, @RequestParam("memname") String memname,
			@RequestParam("memmobile") String memmobile, @RequestParam("memcity") String memcity,
			@RequestParam("memdist") String memdist, @RequestParam("memadd") String memadd,
			@RequestParam("mememail") String mememail, @RequestParam("membirth") java.sql.Date membirth, Model model,
			HttpServletRequest req, HttpServletResponse res) throws IOException {

		Integer memstatus = 0;
		Integer memvrfed = 0;
		java.sql.Date memjointime = null;
		memjointime = new java.sql.Date(System.currentTimeMillis());
		Integer usderstatus = 0;
		Integer ecash = 0;

		MemberVO memVO = new MemberVO();

		memVO.setMemAccount(memaccount);
		memVO.setMemPassword(mempassword);
		memVO.setMemStatus(memstatus);
		memVO.setMemVrfed(memvrfed);
		memVO.setMemName(memname);
		memVO.setMemMobile(memmobile);
		memVO.setMemCity(memcity);
		memVO.setMemDist(memdist);
		memVO.setMemAdd(memadd);
		memVO.setMemEmail(mememail);
		memVO.setMemBirth(membirth);
		memVO.setMemJointime(memjointime);
		memVO.setUsderStatus(usderstatus);
		memVO.setEcash(ecash);

		MemberService memSvc = new MemberService();
		int count = memSvc.register(memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist,
				memadd, mememail, membirth, memjointime, usderstatus, ecash);

		if (count == 1) {
			SendEmail se = new SendEmail(mememail);
			se.start();
			se = null;

			String url = "/frontend/member/login.html";
			res.sendRedirect(req.getContextPath() + url);

		} else {
			System.out.println("失敗");
			res.getWriter().print("0");
		}

	}
	
	//----刪除會員
	// request from listAllEmp.jsp
	@RequestMapping("/deleteMember")
	public String deleteMember(@RequestParam("memno") Integer memno) {
		
		MemberService memSvc = new MemberService();
		memSvc.deleteMem(memno);
		
		return "backend/member/listAllMember";
		
	}
	
	//----批准賣家狀態
	// request from agreeForm.jsp
	@RequestMapping("/approveUsderStatus")
	public String approveUsderStatus(@RequestParam("mememail") String mememail) {
		
		MemberService memSvc = new MemberService();
		memSvc.activeusder(mememail);
		SendEmailUsder se = new SendEmailUsder(mememail);
		se.start();
		se = null;
		
		return "frontend/member/memberCenter";
	}
}

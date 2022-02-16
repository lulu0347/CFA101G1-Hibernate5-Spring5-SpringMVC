package com.transRec.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.transRec.model.TransRecService;
import com.transRec.model.TransRecVO;

import Utils.ScenerioControl;

@Controller
public class TransRecServlet {

	// ----(前端)查詢錢包餘額
	// request from
	@RequestMapping("/deposit")
	public String check(HttpSession session, Model model) {

		MemberVO memVO = (MemberVO) session.getAttribute("user");
		Integer memNo = memVO.getMemNo();

		if (memVO != null) {
			TransRecService TranSvc = new TransRecService();
			Integer money = TranSvc.getDeposit(memNo);
			model.addAttribute("money", money);
		}

		return "frontend/transRec/deposit";
	}

	// ----(前端)錢包儲值
	// request from
	@RequestMapping("/saveMoney")
	public String saveMoney(@RequestParam("transAmount") Integer transAmount, ModelMap modelmap, HttpSession session) {

		MemberVO memVO = (MemberVO) session.getAttribute("user");
		Integer memNo = memVO.getMemNo();

		if (transAmount == null || transAmount <= 0) {
			return "frontend/transRec/deposit";
		}

		Timestamp transRecTime = new Timestamp(System.currentTimeMillis());
		TransRecService TranSvc = new TransRecService();
		MemberVO memberVO = new MemberVO();
		TransRecVO transRecVO = new TransRecVO();
		memberVO.setMemNo(memNo);
		transRecVO.setMemberVO(memberVO);
		transRecVO.setMallName("系統儲值");
		transRecVO.setOrderNo(null);
		transRecVO.setTransAmount(transAmount);
		transRecVO.setTransCont("");
		transRecVO.setTransState(2);
		transRecVO.setTransRecTime(transRecTime);

		TranSvc.saveMoney(transRecVO);
		modelmap.addAttribute("transRecVO", transRecVO);

		List<TransRecVO> transRecList = new ArrayList<TransRecVO>();
		TransRecService TranRecSvc = new TransRecService();

		TranRecSvc.memTransRec(memVO.getMemNo());
		transRecList.add(transRecVO);

		modelmap.addAttribute("transRecList", transRecList);

		return "frontend/transRec/transRec";
	}

	// ----(前端)查詢錢包交易
	// request from
	@RequestMapping("/transRec")
	public String transRec(HttpSession session, Model model) {

		MemberVO memVO = (MemberVO) session.getAttribute("user");

		TransRecService tranRecSvc = new TransRecService();
		List<TransRecVO> transRecList = tranRecSvc.memTransRec(memVO.getMemNo());

		model.addAttribute("transRecList", transRecList);

		return "frontend/transRec/transRec";
	}

	// ----(後端)後台查詢會員交易紀錄
	// request from
	@RequestMapping("/getOneMemRec")
	public String getOneMemRec(@RequestParam("memno") Integer memno, Model model) {

		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMem(memno);

		TransRecService tranRecSvc = new TransRecService();
		List<TransRecVO> transRecList = tranRecSvc.memTransRec(memberVO.getMemNo());

		model.addAttribute("transRecList", transRecList);

		return "backend/transRec/memRec";
	}

	// ----(前端)儲值金額
	// request from
	@RequestMapping("/ajaxSaveMoney")
	public void ajaxSaveMoney(@RequestParam("transAmount") Integer transAmount, Model model, HttpSession session,
			HttpServletResponse res, HttpServletRequest req) throws IOException {

		String action = req.getParameter("action");

		if ("ajaxSaveMoney".equals(action)) {
			MemberVO memVO = (MemberVO) session.getAttribute("user");
			Integer memNo = memVO.getMemNo();

			PrintWriter out = res.getWriter();
			if (memVO == null) {
				out.write("noMember");
			}
			Timestamp transRecTime = new Timestamp(System.currentTimeMillis());

			TransRecService tranSvc = new TransRecService();
			MemberVO memberVO = new MemberVO();
			TransRecVO transRecVO = new TransRecVO();
			memberVO.setMemNo(memNo);
			transRecVO.setMemberVO(memberVO);
			transRecVO.setMallName("系統儲值");
			transRecVO.setOrderNo(null);
			transRecVO.setTransCont("");
			transRecVO.setTransState(2);
			transRecVO.setTransRecTime(transRecTime);

			if (transAmount >= 0) {
				transRecVO.setTransAmount(transAmount);
				tranSvc.saveMoney(transRecVO);
				out.write("success");
			} else {
				out.write("fail");
			}
		}
	}

	// ----(前端)儲值金額1
	// request from saveMoney1.jsp
	@RequestMapping("/ajaxSaveMoney1")
	public void ajaxSaveMoney1(@RequestParam("transAmount") Integer transAmount, HttpServletResponse res, HttpServletRequest req, HttpSession session) throws IOException {
		
		String action = req.getParameter("action");
		if ("ajaxSaveMoney1".equals(action)) {
			MemberVO memVO = (MemberVO) session.getAttribute("user");
			PrintWriter out = res.getWriter();

			// 接收錢包輸入的金額
			Timestamp transRecTime = new Timestamp(System.currentTimeMillis());
			JSONObject moneyObj = new JSONObject();
			if (memVO == null) {
				try {
					moneyObj.put("status", "noMember");
				} catch (JSONException e) {
				}
				out.write(moneyObj.toString());
				return;
			}

			TransRecService tranSvc = new TransRecService();
			TransRecVO transRecVO = new TransRecVO();
			memVO.setMemNo(memVO.getMemNo());
			transRecVO.setMemberVO(memVO);
			transRecVO.setMallName("系統儲值");
			transRecVO.setOrderNo(null);
			transRecVO.setTransCont("");
			transRecVO.setTransState(2);
			transRecVO.setTransRecTime(transRecTime);

			try {
				// 錢包物件以字串方式傳遞[成功]的標記符號
				if (transAmount >= 0) {
					transRecVO.setTransAmount(transAmount);
					tranSvc.saveMoney(transRecVO);
					Integer nowMoney = tranSvc.getDeposit(transRecVO.getMemberVO().getMemNo());

					moneyObj.put("nowMoney", nowMoney);
					moneyObj.put("status", "success");

				} else {
					moneyObj.put("status", "fail");

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.write(moneyObj.toString());
		}
	}
}
package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;

public class MemberService {

	private static MemberDAO_interface dao;

	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(MemberDAO_interface) context.getBean("MemberDAO");
	}

	// 登入
	public MemberVO login(String memaccount, String mempassword) {
		return dao.confirmAccountAndPassword(memaccount, mempassword);
	}

	// 驗證帳號是否被使用
	public MemberVO checkaccount(String memaccount) {
		return dao.checkaccount(memaccount);
	}

	// 驗證信箱是否被使用
	public MemberVO checkemail(String mememail) {
		return dao.checkemail(mememail);
	}

	// 啟用信箱驗證
	public void active(String mememail) {
		dao.updateEmailStatus(mememail);
	}

	// 啟用賣家功能
	public void activeusder(String mememail) {
		dao.approveUsderStatus(mememail);
	}

	// 新會員註冊
	public int register(String memAccount, String memPassword, int memStatus, Integer memVrfed,
			String memName, String memMobile, String memCity, String memDist, String memAdd, String memEmail,
			Date memBirth, Date memJointime, int usderStatus, int ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemStatus(memStatus);
		memVO.setMemVrfed(memVrfed);
		memVO.setMemName(memName);
		memVO.setMemMobile(memMobile);
		memVO.setMemCity(memCity);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemEmail(memEmail);
		memVO.setMemBirth(memBirth);
		memVO.setMemJointime(memJointime);
		memVO.setUsderStatus(usderStatus);
		memVO.setEcash(ecash);

		return dao.registerInsert(memVO);

		// return memVO;
	}

	// 更新錢包餘額
	public void updateEcash(Integer memno, Integer ecash) {
		dao.updateEcash(ecash, memno);
	}

	public MemberVO addMem(String memAccount, String memPassword, int memStatus, Integer memVrfed,
			String memName, String memMobile, String memCity, String memDist, String memAdd, String memEmail,
			Date memBirth, Date memJointime, int usderStatus, int ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemStatus(memStatus);
		memVO.setMemVrfed(memVrfed);
		memVO.setMemName(memName);
		memVO.setMemMobile(memMobile);
		memVO.setMemCity(memCity);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemEmail(memEmail);
		memVO.setMemBirth(memBirth);
		memVO.setMemJointime(memJointime);
		memVO.setUsderStatus(usderStatus);
		memVO.setEcash(ecash);
		dao.insert(memVO);

		return memVO;
	}

	public MemberVO memberUpdate(Integer memno, String memAccount, String memPassword, int memStatus, Integer memVrfed,
			Date memNoVrftime, String memName, String memMobile, String memCity, String memDist, String memAdd,
			String memEmail, Date memBirth, Date memJointime, int usderStatus, int ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemNo(memno);
		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemStatus(memStatus);
		memVO.setMemVrfed(memVrfed);
		memVO.setMemName(memName);
		memVO.setMemMobile(memMobile);
		memVO.setMemCity(memCity);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemEmail(memEmail);
		memVO.setMemBirth(memBirth);
		memVO.setMemJointime(memJointime);
		memVO.setUsderStatus(usderStatus);
		memVO.setEcash(ecash);
		dao.memberUpdate(memVO);

		return memVO;
	}

	public MemberVO updateMem(Integer memno, String memAccount, String memPassword, int memStatus, Integer memVrfed,
			String memName, String memMobile, String memCity, String memDist, String memAdd, String memEmail,
			Date memBirth, Date memJointime, int usderStatus, int ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemNo(memno);
		memVO.setMemAccount(memAccount);
		memVO.setMemPassword(memPassword);
		memVO.setMemStatus(memStatus);
		memVO.setMemVrfed(memVrfed);
		memVO.setMemName(memName);
		memVO.setMemMobile(memMobile);
		memVO.setMemCity(memCity);
		memVO.setMemDist(memDist);
		memVO.setMemAdd(memAdd);
		memVO.setMemEmail(memEmail);
		memVO.setMemBirth(memBirth);
		memVO.setMemJointime(memJointime);
		memVO.setUsderStatus(usderStatus);
		memVO.setEcash(ecash);
		dao.update(memVO);

		return memVO;
	}

	public void deleteMem(Integer memno) {
		dao.delete(memno);
	}

	public MemberVO getOneMem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
}

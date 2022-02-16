package com.member.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO_interface;
import com.itemDetail.model.ItemDetailVO;

import hibernate.util.HibernateUtil;

import java.sql.*;

@Transactional(readOnly = true)
public class MemberDAO implements MemberDAO_interface {

	// 取得所有會員
	private static final String GET_ALL_STMT = "FROM MemberVO order by memno";
	// 驗證帳號密碼是否存在(Login)
	private static final String GET_ONE_ACCT_PWD = "FROM MemberVO where memaccount =:memaccount and mempassword =:mempassword";
	//// 驗證帳號是否被使用
	private static final String GET_ONE_ACCT = "FROM MemberVO where memaccount =:memaccount";
	// 驗證信箱是否被使用
	private static final String GET_ONE_EMAIL = "FROM MemberVO where mememail =:mememail";
	// (前台)新增會員資料
	private static final String INSERT_STMT = "INSERT INTO MemberVO (MemAccount, MemPassword, Memstatus, Memvrfed, MemName, MemMobile, MemCity, MemDist, MemAdd, MemEmail, MemBirth, memjointime, UsderStatus, ECash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 信箱更新會員驗證狀態
	private static final String UPDATE_EMAILSTATUS = "UPDATE MemberVO SET memstatus = 1, MemVrfed = 1 WHERE mememail =:mememail";
	// 開通會員賣家狀態
	private static final String UPDATE_APV_USDERSTATUS = "UPDATE MemberVO SET usderstatus = 1 WHERE mememail =:mememail";
	// 更新會員餘額
	private static final String updateMemEcash = "UPDATE MemberVO SET ecash=:ecash WHERE memno =:memno";
	// 取得會員餘額
	private static final String getMemEcash = "SELECT ecash FROM MemberVO where Memno =:Memno";

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = (List<MemberVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public MemberVO findByPrimaryKey(Integer MemNo) {
//		System.out.println("------------DAO--------------- : "+MemNo);
		MemberVO memberVO = hibernateTemplate.get(MemberVO.class, MemNo);
		return memberVO;
	}

	@Override
	public MemberVO confirmAccountAndPassword(String memaccount, String mempassword) {
//		MemberVO memberVO = (MemberVO) hibernateTemplate.findByNamedParam(GET_ONE_ACCT_PWD, new String[] {"memaccount","mempassword"},new Object[] {memaccount,mempassword});
		List<MemberVO> list = (List<MemberVO>) hibernateTemplate.findByNamedParam(GET_ONE_ACCT_PWD, new String[] {"memaccount","mempassword"},new Object[] {memaccount,mempassword});
		MemberVO memberVO = (MemberVO)list.iterator().next();
		return memberVO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberVO checkaccount(String memaccount) {
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO) hibernateTemplate.findByNamedParam(GET_ONE_ACCT,"memaccount", memaccount);
		return memberVO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MemberVO checkemail(String mememail) {
		MemberVO memberVO = (MemberVO) hibernateTemplate.findByNamedParam(GET_ONE_EMAIL, "mememail",mememail);
		return memberVO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(MemberVO MemberVO) {
		hibernateTemplate.saveOrUpdate(MemberVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int registerInsert(MemberVO MemberVO) {
		int result = 0;
		return result;
	}

	@Override // (後台) 更新
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MemberVO MemberVO) {
		hibernateTemplate.update(MemberVO);
	}

	@Override // (前台)更新
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void memberUpdate(MemberVO MemberVO) {
		hibernateTemplate.update(MemberVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer MemNo) {
			MemberVO memberVO = new MemberVO();
			memberVO.setMemNo(MemNo);
			hibernateTemplate.delete(memberVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateEmailStatus(String mememail) {
		hibernateTemplate.update(UPDATE_EMAILSTATUS, mememail);
	}

	@Override  
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void approveUsderStatus(String mememail) {
		hibernateTemplate.update(UPDATE_APV_USDERSTATUS, mememail);
	}

	@Override  
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateEcash(Integer ecash, Integer MemNo) {
		//先留白 可以用MemNo先取得某會員的VO,再將ecash放入
	}

	public static void main(String[] args) {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		MemberDAO_interface dao =(MemberDAO_interface) context.getBean("MemberDAO");
		
		//findByPrimaryKey() ok
//		MemberVO memberVO = dao.findByPrimaryKey(11001);
//		System.out.println(memberVO.getEcash());
		
		//confirmAccountAndPassword() ok
//		MemberVO memberVO = dao.confirmAccountAndPassword("abc101", "123456");
//		System.out.println(memberVO.getMemName());
//		System.out.println(memberVO.getMemCity());
		
		//getMemEcash() ok
//		int ecash = dao.getMemEcash(11001);
//		System.out.println(ecash);
		
		((ClassPathXmlApplicationContext) context).close();
	}
}

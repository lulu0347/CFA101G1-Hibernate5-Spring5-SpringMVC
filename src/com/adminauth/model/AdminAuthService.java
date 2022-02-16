package com.adminauth.model;

import java.util.List;
import java.util.Set;

import com.adminauthrization.model.AdminAuthrizationVO;
import com.admini.model.AdminiDAO_interface;
import com.admini.model.AdminiVO;

public class AdminAuthService {
	private AdminAuthDAO_interface dao;
	
	public AdminAuthService() {
		dao = new AdminAuthDAO();
	}
	
	public void update(AdminAuthVO adminAuthVO) {
		dao.update(adminAuthVO);
	}
	
	public void insert(AdminAuthVO adminAuthVO) {
		dao.insert(adminAuthVO);
	}

	public void delete(Integer adminNo,Integer adminAuthrizationNo) {
		dao.delete(adminNo,adminAuthrizationNo);
	}

	public List<AdminAuthVO> getAuthByAdminNO(Integer adminNo) {
		return dao.findByAdminNo(adminNo);
	}

	public List<AdminAuthVO> getPersonByAdminAuthrization(Integer adminAuthrizationNo) {
		return dao.findByAuthNo(adminAuthrizationNo);
	}

	public List<AdminAuthVO> getAll() {
		return dao.getAll();
	}

	public AdminAuthVO updateAdminAuth(Integer adminNo, Integer adminAuthrizationNo) {
		AdminAuthVO adminAuth = new AdminAuthVO();
		adminAuth.setAdminNo(adminNo);
		adminAuth.setAdminAuthrizationNo(adminAuthrizationNo);
		dao.update(adminAuth);
		return adminAuth;
	}
	
	
	
}

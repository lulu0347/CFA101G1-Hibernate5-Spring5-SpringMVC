package com.adminauthrization.model;

import java.util.List;

public class AdminAuthrizationService {
	private AdminAuthrizationDAO_interface dao;
	
	public AdminAuthrizationService() {
		dao = new AdminAuthrizationDAO();
	}

	public AdminAuthrizationVO insertAdminAuthrization(String adminAuthrizationName) {
		AdminAuthrizationVO adminAuthrizationVO = new AdminAuthrizationVO();
		adminAuthrizationVO.setAdminAuthrizationName(adminAuthrizationName);
		dao.insert(adminAuthrizationVO);
		return adminAuthrizationVO;
	}
	
	public void insertAdminAuthrization(AdminAuthrizationVO adminAuthrizationVO) {
		dao.insert(adminAuthrizationVO);
	}

	public AdminAuthrizationVO updateAdminAuthrization(Integer adminAuthrizationNo,String adminAuthrizationName) {
		AdminAuthrizationVO adminAuthrizationVO = new AdminAuthrizationVO();
		adminAuthrizationVO.setAdminAuthrizationNo(adminAuthrizationNo);
		adminAuthrizationVO.setAdminAuthrizationName(adminAuthrizationName);
		dao.update(adminAuthrizationVO);
		return dao.findByPrimaryKey(adminAuthrizationNo);
	}
	
	public void updateAdminAuthrization(AdminAuthrizationVO adminAuthrizationVO) {
		dao.update(adminAuthrizationVO);
	}

	public void deleteAdminAuthrization(Integer adminAuthrizationNo) {
		dao.delete(adminAuthrizationNo);
	}

	public AdminAuthrizationVO getOneAdminAuthrization(Integer adminAuthrizationNo) {
		return dao.findByPrimaryKey(adminAuthrizationNo);
	}

	public List<AdminAuthrizationVO> getAll() {
		return dao.getAll();
	}


	
	
}


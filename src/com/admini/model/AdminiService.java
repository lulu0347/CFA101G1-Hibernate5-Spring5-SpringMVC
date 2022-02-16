package com.admini.model;

import java.util.List;

import com.admini.model.AdminiDAO;
import com.admini.model.AdminiDAO_interface;

public class AdminiService {
	private AdminiDAO_interface dao;

	public AdminiService() {
		dao = new AdminiDAO();
	}

	public AdminiVO addAdmini(String adminAccount, String adminPassword, String adminName, String adminPhone) {

		AdminiVO adminiVO = new AdminiVO();

		adminiVO.setAdminAccount(adminAccount);
		adminiVO.setAdminPassword(adminPassword);
		adminiVO.setAdminName(adminName);
		adminiVO.setAdminPhone(adminPhone);

		dao.insert(adminiVO);

		return adminiVO;
	}
	
	public void addAdmini(AdminiVO adminiVO) {
		dao.insert(adminiVO);
	}
	
	public AdminiVO updateAdmini(Integer adminNo ,String adminAccount, String adminPassword, String adminName, String adminPhone) {

		AdminiVO adminiVO = new AdminiVO();
		adminiVO.setAdminNo(adminNo);
		adminiVO.setAdminAccount(adminAccount);
		adminiVO.setAdminPassword(adminPassword);
		adminiVO.setAdminName(adminName);
		adminiVO.setAdminPhone(adminPhone);
		dao.update(adminiVO);

		return dao.findByPrimaryKey(adminNo);
	}
	
	public void updateAdmini(AdminiVO adminiVO) {
		dao.update(adminiVO);
	}

	public void deleteAdmini(Integer adminNo) {
		dao.delete(adminNo);
	}

	public AdminiVO getOneAdmini(Integer adminNo) {
		return dao.findByPrimaryKey(adminNo);
	}
	
	
	public AdminiVO getAccount(String adminAccount) {
		return dao.findByAdminAccount(adminAccount);
	}

	public List<AdminiVO> getAll() {
		return dao.getAll();
	}
}

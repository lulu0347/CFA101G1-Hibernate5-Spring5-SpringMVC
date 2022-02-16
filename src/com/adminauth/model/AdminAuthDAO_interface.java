package com.adminauth.model;

import java.util.List;
import java.util.Set;

import com.adminauthrization.model.AdminAuthrizationVO;
import com.admini.model.AdminiVO;

public interface AdminAuthDAO_interface {
	
	public void insert(AdminAuthVO adminAuthVO);
    public void update(AdminAuthVO adminAuthVO);
    public void delete(Integer adminNo,Integer adminAuthrization);
    public List<AdminAuthVO> findByAdminNo(Integer adminNo);
    public List<AdminAuthVO> findByAuthNo(Integer adminAuthrizationNo);
    public List<AdminAuthVO> getAll();
}	

package com.admini.model;

import java.util.List;

public interface AdminiDAO_interface {
	public void insert(AdminiVO adminiVO);
    public void update(AdminiVO adminiVO);
    public void delete(Integer adminNo);
    public AdminiVO findByPrimaryKey(Integer adminNo);
    public List<AdminiVO> getAll();
	public AdminiVO findByAdminAccount(String adminAccount);
}

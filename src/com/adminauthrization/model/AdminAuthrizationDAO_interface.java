package com.adminauthrization.model;

import java.util.List;

public interface AdminAuthrizationDAO_interface {
	public void insert(AdminAuthrizationVO adminAuthrizationVO);
    public void update(AdminAuthrizationVO adminAuthrizationVO);
    public void delete(Integer adminiAuthrizationNo);
    public AdminAuthrizationVO findByPrimaryKey(Integer adminiAuthrizationNo);
    public List<AdminAuthrizationVO> getAll();
}

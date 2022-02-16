package com.repairform.model;

import java.util.*;


public interface RepairFormDAO_interface {
    public void insert(RepairFormVO repairformVO);
    public void update(RepairFormVO repairformVO);
    public void delete(Integer RepairFormNo);
    public RepairFormVO findByPrimaryKey(Integer repairformno);
    public List<RepairFormVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

package com.memauthrization.model;

import java.util.*;

public interface MemauthrizationDAO_interface {
    public void insert(MemauthrizationVO MemauthrizationVO);
    public void update(MemauthrizationVO MemauthrizationVO);
    public void delete(Integer Memauthfunno);
    public MemauthrizationVO findByPrimaryKey(Integer Memauthfunno);
    public List<MemauthrizationVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

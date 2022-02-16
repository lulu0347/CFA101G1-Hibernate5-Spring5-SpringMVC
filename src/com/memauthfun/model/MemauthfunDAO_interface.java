package com.memauthfun.model;

import java.util.*;

public interface MemauthfunDAO_interface {
    public void insert(MemauthfunVO MemauthfunVO);
    public void update(MemauthfunVO MemauthfunVO);
    public void delete(Integer MemauthfunNo);
    public MemauthfunVO findByPrimaryKey(Integer MemauthfunNo);
    public List<MemauthfunVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

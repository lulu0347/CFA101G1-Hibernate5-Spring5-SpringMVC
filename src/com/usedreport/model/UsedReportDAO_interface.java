package com.usedreport.model;

import java.util.*;

public interface UsedReportDAO_interface {
          public void insert(UsedReportVO usedReportVO);
          public void update(UsedReportVO usedReportVO);
          public UsedReportVO findByPrimaryKey(Integer memNo, Integer usedNo);
          public List<UsedReportVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}

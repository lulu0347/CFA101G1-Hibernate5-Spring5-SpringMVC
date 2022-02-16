package com.usedpic.model;

import java.sql.Connection;
import java.util.*;

import javax.servlet.http.Part;

public interface UsedPicDAO_interface {
          public void insert(UsedPicVO usedPicVO);
          public void insert2(UsedPicVO usedPicVO, Connection con);
          public void update(UsedPicVO usedPicVO);
          public UsedPicVO findByPrimaryKey(Integer usedPicNo);
          public List<UsedPicVO> findByUsedNo(Integer usedNo);
          public List<UsedPicVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}

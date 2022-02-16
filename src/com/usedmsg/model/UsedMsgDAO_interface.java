package com.usedmsg.model;

import java.util.*;

public interface UsedMsgDAO_interface {
          public void insert(UsedMsgVO usedMsgVO);
          public void update(UsedMsgVO usedMsgVO);
          public void delete(Integer usedMsgNo);
          public UsedMsgVO findByPrimaryKey(Integer usedMsgNo);
          public List<UsedMsgVO> getAll();
          public List<UsedMsgVO> getAllByUsedNo(Integer usedNo);
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}

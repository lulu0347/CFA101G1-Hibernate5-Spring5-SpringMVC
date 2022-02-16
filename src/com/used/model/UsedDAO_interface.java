package com.used.model;

import java.sql.Connection;
import java.util.*;

import com.usedpic.model.UsedPicVO;

public interface UsedDAO_interface {
          public void insert(UsedVO usedVO);
          public void insertWithPics(UsedVO usedVO , List<byte[]> list);
          public void update(UsedVO usedVO);
          public void update2(UsedVO usedVO);
          public UsedVO findByPrimaryKey(Integer usedNo);
          public List<UsedVO> getAll();
          public List<UsedVO> getAllProdByBuyer(Integer buyerNo);
          public List<UsedVO> getAllProdBySeller(Integer SellerNo);
          public List<UsedVO> getAllBuyer();
          public List<UsedVO> getAllSeller();
          public List<UsedVO> search(String name);
          public List<UsedVO> searchKind(Integer kindNo);
}

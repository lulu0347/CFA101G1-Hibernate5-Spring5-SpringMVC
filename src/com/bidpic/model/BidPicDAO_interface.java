package com.bidpic.model;

import java.util.*;

public interface BidPicDAO_interface {
	public void insert(BidPicVO bidPicVO);
	public void update(BidPicVO bidPicVO);
	public void delete(Integer bidProdPicNo);
	public BidPicVO findByPrimaryKey(Integer bidProdPicNo);
	public BidPicVO findFirstPicByBidProdNo(Integer bidProdNo);
	public List<BidPicVO> findByBidProdNo(Integer bidProdNo);
	public List<BidPicVO> getAll();
	
	// 測試同時新增圖片
	public void insert2(BidPicVO bidPicVO, java.sql.Connection con);

}

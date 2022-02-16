package com.bidrecord.model;

import java.util.*;

public interface BidRecordDAO_interface {
	public void insert(BidRecordVO bidRecordVO);
	public void update(BidRecordVO bidRecordVO);
	public void delete(Integer bidRecordNo);
	public BidRecordVO findByPrimaryKey(Integer bidRecordNo);
	public List<BidRecordVO> findByBidProdNo(Integer bidProdNo);
	public BidRecordVO findByBidProdNoHighest(Integer bidProdNo);
	public BidRecordVO findByMemNo(Integer memNo);
	public BidRecordVO findMaxBidPriceByBidProdNo(Integer bidProdNo);
	public List<BidRecordVO> getAll();
}

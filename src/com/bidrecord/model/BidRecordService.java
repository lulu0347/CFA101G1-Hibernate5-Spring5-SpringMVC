package com.bidrecord.model;

import java.util.List;

public class BidRecordService {

	private BidRecordDAO_interface dao;
	
	public BidRecordService() {
		dao = new BidRecordDAO();
	}
	
	public BidRecordVO addBidRecord(Integer bidProdNo, Integer memNo, Integer bidPrice, java.sql.Timestamp bidTime) {
		BidRecordVO bidRecordVO = new BidRecordVO();
		
		bidRecordVO.setBidProdNo(bidProdNo);
		bidRecordVO.setMemNo(memNo);
		bidRecordVO.setBidPrice(bidPrice);
		bidRecordVO.setBidTime(bidTime);
		dao.insert(bidRecordVO);
		
		return bidRecordVO;
	}
	
//	預留給 Struts 2 或 Spring MVC 用
	public void addBidRecord(BidRecordVO bidRecordVO) {
		dao.insert(bidRecordVO);
	}
	
	public BidRecordVO updateBidRecord(Integer bidRecordNo, Integer bidProdNo, Integer memNo, Integer bidPrice, java.sql.Timestamp bidTime) {
		BidRecordVO bidRecordVO = new BidRecordVO();
		
		bidRecordVO.setBidRecordNo(bidRecordNo);
		bidRecordVO.setBidProdNo(bidProdNo);
		bidRecordVO.setMemNo(memNo);
		bidRecordVO.setBidPrice(bidPrice);
		bidRecordVO.setBidPrice(bidPrice);
		dao.update(bidRecordVO);
		
		return dao.findByPrimaryKey(bidRecordNo);
	}
	
//	預留給 Struts 2 用的
	public void updateBidRecord(BidRecordVO bidRecordVO) {
		dao.update(bidRecordVO);
	}
	
	public void deleteBidRecord(Integer bidRecordNo) {
		dao.delete(bidRecordNo);
	}
	
	public BidRecordVO getOneBidRecord(Integer bidRecordNo) {
		return dao.findByPrimaryKey(bidRecordNo);
	}
	
	public List<BidRecordVO> findByBidProdNo(Integer bidProdNo) {
		return dao.findByBidProdNo(bidProdNo);
	}
	
	public BidRecordVO findByBidProdNoHighest(Integer bidProdNo) {
		return dao.findByBidProdNoHighest(bidProdNo);
	}
	
	public List<BidRecordVO> getAll() {
		return dao.getAll();
	}
}

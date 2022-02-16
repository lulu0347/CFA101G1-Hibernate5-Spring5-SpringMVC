package com.bid.model;

import java.util.List;

import com.bidpic.model.BidPicVO;

public class BidService {

	private BidDAO_interface dao;
	
	public BidService() {
		dao = new BidDAO();
	}
	
//	public BidVO addBid(Integer kindNo, Integer transRecNo, Integer bidWinnerNo, String bidProdName, String bidProdDescription, Integer bidProdStartPrice, Integer bidState, java.sql.Timestamp bidProdStartTime, java.sql.Timestamp bidProdEndTime, Integer bidWinnerPrice, Integer bidPriceIncrement, String receiverName, String receiverAddress, String receiverPhone, Integer bidProdState) {
//		BidVO bidVO = new BidVO();
//		
//		bidVO.setKindNo(kindNo);
//		bidVO.setTransRecNo(transRecNo);
//		bidVO.setBidWinnerNo(bidWinnerNo);
//		bidVO.setBidProdName(bidProdName);
//		bidVO.setBidProdDescription(bidProdDescription);
//		bidVO.setBidProdStartPrice(bidProdStartPrice);
//		bidVO.setBidState(bidState);
//		bidVO.setBidProdStartTime(bidProdStartTime);
//		bidVO.setBidProdEndTime(bidProdEndTime);
//		bidVO.setBidWinnerPrice(bidWinnerPrice);
//		bidVO.setBidPriceIncrement(bidPriceIncrement);
//		bidVO.setReceiverName(receiverName);
//		bidVO.setReceiverAddress(receiverAddress);
//		bidVO.setReceiverPhone(receiverPhone);
//		bidVO.setBidProdState(bidProdState);
//		
//		dao.insert(bidVO);
//		return bidVO;
//	}
	public BidVO addBid(Integer kindNo, String bidProdName, String bidProdDescription, Integer bidProdStartPrice, Integer bidState, java.sql.Timestamp bidProdStartTime, java.sql.Timestamp bidProdEndTime, Integer bidPriceIncrement, Integer bidProdState) {
		BidVO bidVO = new BidVO();
		
		bidVO.setKindNo(kindNo);
		bidVO.setBidProdName(bidProdName);
		bidVO.setBidProdDescription(bidProdDescription);
		bidVO.setBidProdStartPrice(bidProdStartPrice);
		bidVO.setBidState(bidState);
		bidVO.setBidProdStartTime(bidProdStartTime);
		bidVO.setBidProdEndTime(bidProdEndTime);
		bidVO.setBidPriceIncrement(bidPriceIncrement);
		bidVO.setBidProdState(bidProdState);
		
		dao.insert(bidVO);
		return bidVO;
	}
	
//	預留給 Struts 2 或 Spring MVC 用
	public void addBid(BidVO bidVO) {
		dao.insert(bidVO);
	}
	
	public BidVO updateBid(Integer bidProdNo, Integer kindNo, Integer transRecNo, Integer bidWinnerNo, String bidProdName, String bidProdDescription, Integer bidProdStartPrice, Integer bidState, java.sql.Timestamp bidProdStartTime, java.sql.Timestamp bidProdEndTime, Integer bidWinnerPrice, Integer bidPriceIncrement, String receiverName, String receiverAddress, String receiverPhone, Integer bidProdState) {
		BidVO bidVO = new BidVO();
		
		bidVO.setBidProdNo(bidProdNo);
		bidVO.setKindNo(kindNo);
		bidVO.setTransRecNo(transRecNo);
		bidVO.setBidWinnerNo(bidWinnerNo);
		bidVO.setBidProdName(bidProdName);
		bidVO.setBidProdDescription(bidProdDescription);
		bidVO.setBidProdStartPrice(bidProdStartPrice);
		bidVO.setBidState(bidState);
		bidVO.setBidProdStartTime(bidProdStartTime);
		bidVO.setBidProdEndTime(bidProdEndTime);
		bidVO.setBidWinnerPrice(bidWinnerPrice);
		bidVO.setBidPriceIncrement(bidPriceIncrement);
		bidVO.setReceiverName(receiverName);
		bidVO.setReceiverAddress(receiverAddress);
		bidVO.setReceiverPhone(receiverPhone);
		bidVO.setBidProdState(bidProdState);
		dao.update(bidVO);
		
		return dao.findByPrimaryKey(bidProdNo);
	}
	
//	測試更新競標狀態
//	public List<BidVO> updateBid(Integer bidState) {
//		BidVO bidVO = new BidVO();
//		
//		bidVO.setBidProdNo(bidProdNo);
//		bidVO.setKindNo(kindNo);
//		bidVO.setTransRecNo(transRecNo);
//		bidVO.setBidWinnerNo(bidWinnerNo);
//		bidVO.setBidProdName(bidProdName);
//		bidVO.setBidProdDescription(bidProdDescription);
//		bidVO.setBidProdStartPrice(bidProdStartPrice);
//		bidVO.setBidState(bidState);
//		bidVO.setBidProdStartTime(bidProdStartTime);
//		bidVO.setBidProdEndTime(bidProdEndTime);
//		bidVO.setBidWinnerPrice(bidWinnerPrice);
//		bidVO.setBidPriceIncrement(bidPriceIncrement);
//		bidVO.setReceiverName(receiverName);
//		bidVO.setReceiverAddress(receiverAddress);
//		bidVO.setReceiverPhone(receiverPhone);
//		bidVO.setBidProdState(bidProdState);
//		dao.update(bidVO);
//		
//		return dao.findByPrimaryKey(bidProdNo);
//	}
	
//	預留給 Struts 2 用的
	public void updateBid(BidVO bidVO) {

		dao.update2(bidVO);
	}
	public void updateBid3(BidVO bidVO) {
		
		dao.update3(bidVO);
	}
	public void updateBid4(BidVO bidVO) {
		
		dao.update4(bidVO);
	}
	
	public void updateBid5(BidVO bidVO) {
		
		dao.update5(bidVO);
	}
	public void updateBid6(BidVO bidVO) {
		
		dao.update6(bidVO);
	}
	public void updateBid7(BidVO bidVO) {
		
		dao.update7(bidVO);
	}
	
	public void deleteBid(Integer bidProdNo) {
		dao.delete(bidProdNo);
	}
	
	public BidVO getOneBid(Integer bidProdNo) {
		return dao.findByPrimaryKey(bidProdNo);
	}
	public BidVO getOneBid_transRecNo(Integer transRecNo) {
		return dao.findByTransRecNo(transRecNo);
	}
	
	public List<BidVO> getAll() {
		return dao.getAll();
	}
	
//	public void insertWithBidPics(BidVO bidVO, List<BidPicVO> list) {
//		
//		dao.insertWithBidPics(bidVO, list);
//	}
	
	public BidVO addBid2(Integer kindNo, String bidProdName, String bidProdDescription, Integer bidProdStartPrice, Integer bidState, java.sql.Timestamp bidProdStartTime, java.sql.Timestamp bidProdEndTime, Integer bidPriceIncrement, Integer bidProdState, List<byte[]> list) {
		BidVO bidVO = new BidVO();
		bidVO.setKindNo(kindNo);
		bidVO.setBidProdName(bidProdName);
		bidVO.setBidProdDescription(bidProdDescription);
		bidVO.setBidProdStartPrice(bidProdStartPrice);
		bidVO.setBidState(bidState);
		bidVO.setBidProdStartTime(bidProdStartTime);
		bidVO.setBidProdEndTime(bidProdEndTime);
		bidVO.setBidPriceIncrement(bidPriceIncrement);
		bidVO.setBidProdState(bidProdState);
		
		dao.insertWithBidPics(bidVO, list);
		
		return bidVO;
		
	}
	
	public List<BidVO> findBidStateToOne(Integer bidState) {
		return dao.findBidStateToOne(bidState);
	}
	public List<BidVO> findBidProdStateToFive(Integer bidProdState) {
		return dao.findBidProdStateToFive(bidProdState);
	}
	public List<BidVO> findByBidProdState(Integer bidProdState) {
		return dao.findByBidProdState(bidProdState);
	}
	public List<BidVO> findByBidKindNo(Integer kindNo) {
		return dao.findAllByKindNo(kindNo);
	}
	public List<BidVO> findByBidWinnerNoANDBidProdState(Integer bidWinnerNo, Integer bidProdState) {
		return dao.findByBidWinnerNoANDBidProdState(bidWinnerNo, bidProdState);
	}
	public List<BidVO> findByKeyword(String bidProdName) {
		return dao.findByKeyword(bidProdName);
	}
}

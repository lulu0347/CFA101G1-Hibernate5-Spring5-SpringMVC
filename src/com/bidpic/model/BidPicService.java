package com.bidpic.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BidPicService {

	private BidPicDAO_interface dao;
	
	public BidPicService() {
		dao = new BidPicDAO();
	}
	
	public BidPicVO addBidPic(Integer bidProdNo, byte[] bidProdPicContent) {
		BidPicVO bidPicVO = new BidPicVO();
		
		bidPicVO.setBidProdNo(bidProdNo);
		bidPicVO.setBidProdPicContent(bidProdPicContent);
		dao.insert(bidPicVO);
		return bidPicVO;
	}
	
//	// 測試上傳多張圖片
//	public List<BidPicVO> addBidPics(Integer bidProdNo, byte[] bidProdPicContent) {
//		List<BidPicVO> list = new ArrayList<BidPicVO>();
////		BidPicVO bidPicVO = new BidPicVO();
//		for (BidPicVO aBidPicVO : list) {
//			aBidPicVO.setBidProdNo(bidProdNo);
//			aBidPicVO.setBidProdPicContent(bidProdPicContent);
//			dao.insert(aBidPicVO);
//		}
//
//		return list;
//	}
	
//	預留給 Struts 2 或 Spring MVC 用
	public void addBidPic(BidPicVO bidPicVO) {
		dao.insert(bidPicVO);
	}
	
	public BidPicVO updateBidPic(Integer bidProdPicNo, Integer bidProdNo, byte[] bidProdPicContent) {
		BidPicVO bidPicVO = new BidPicVO();
		
		bidPicVO.setBidProdPicNo(bidProdPicNo);
		bidPicVO.setBidProdNo(bidProdNo);
		bidPicVO.setBidProdPicContent(bidProdPicContent);
		dao.update(bidPicVO);
		
		return dao.findByPrimaryKey(bidProdPicNo);
	}
	
//	預留給 Struts 2 用的
	public void updateBidPic(BidPicVO bidPicVO) {
		dao.update(bidPicVO);
	}
	
	public void deleteBidPic(Integer bidProdPicNo) {
		dao.delete(bidProdPicNo);
	}
	
	public BidPicVO getOneBidPic(Integer bidProdPicNo) {
		return dao.findByPrimaryKey(bidProdPicNo);
	}
	public BidPicVO getOneBidPicByBidProdNo(Integer bidProdNo) {
		return dao.findFirstPicByBidProdNo(bidProdNo);
	}
	// 測試查詢圖片
	public List<BidPicVO> getAllBidPic_bidProdNo(Integer bidProdNo) {
		return dao.findByBidProdNo(bidProdNo);
	}
	
	public List<BidPicVO> getAll() {
		return dao.getAll();
	}
	
//	測試同時上傳圖片
//	public void addBidPic2(byte[] bidPic, Connection conn) {
//		
//		BidPicVO bidPicVO = new BidPicVO();
//		
//		bidPicVO.setBidProdNo(bidProdNo);
//		
//		dao.insert2(bidPicVO, conn);
//	}
}

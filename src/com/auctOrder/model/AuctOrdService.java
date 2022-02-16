package com.auctOrder.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.auctOrderDetl.model.AuctOrdDetlDAO_JDBC;
import com.auctOrderDetl.model.AuctOrdDetlDAO_interface;
import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.transRec.model.TransRecDAO;
import com.transRec.model.TransRecDAO_interface;
import com.transRec.model.TransRecService;
import com.transRec.model.TransRecVO;

public class AuctOrdService {

	private AuctOrdDAO_interface auctOrdDao = new AuctOrdDAO_JDBC();
	private AuctOrdDetlDAO_interface auctOrdDetlDao = new AuctOrdDetlDAO_JDBC();
	private TransRecDAO_interface tranDAO = new TransRecDAO();
	private TransRecService transSvc = new TransRecService();
	private MemberService memSvc = new MemberService();
//	// 訂單成立設定在交易狀態後
//	public AuctOrdVO addOrderWithOrderDetl(AuctOrdVO auctOrdVO, List<AuctOrdDetlVO> auctOrdDetlVO) {
//		Connection con = null;
//		try {
//			con = auctOrdDao.getConnection();
//			con.setAutoCommit(false);
//			Integer auctOrdNo = auctOrdDao.addInTransaction(con, auctOrdVO);
//			auctOrdDetlDao.addInTransaction(con, auctOrdNo, auctOrdDetlVO);
//			auctOrdVO.setAuctOrdNo(auctOrdNo);
//
//			TransRecService transRecSvc = new TransRecService();
//			TransRecVO transRecVO = getTransRecVO(auctOrdVO);
//			transRecSvc.createTransRecordInTransaction(con, transRecVO);
//
//			con.commit();
//			con.setAutoCommit(true);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return auctOrdVO;
//	}

	// 訂單成立設定在交易狀態後
	public AuctOrdVO addOrderWithOrderDetl(AuctOrdVO auctOrdVO, List<AuctOrdDetlVO> auctOrdDetlVO) {

		TransRecVO transRecVO = getTransRecVO(auctOrdVO);//取得錢包物件
		auctOrdDao.addOrderWithOrderDetl(auctOrdVO,auctOrdDetlVO, transRecVO);
		
		Integer memNo = auctOrdVO.getMemNo();
		Integer deposit = transSvc.getDeposit(auctOrdVO.getMemNo());
		memSvc.updateEcash(memNo, deposit);
		
		return auctOrdVO;
	}

	public TransRecVO getTransRecVO(AuctOrdVO auctOrdVO) {
		TransRecVO transRecVO = new TransRecVO();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemNo(auctOrdVO.getMemNo());
		transRecVO.setOrderNo(auctOrdVO.getAuctOrdNo());
		transRecVO.setMallName("拍賣商城");// 商城名稱
		transRecVO.setMemberVO(memberVO);
		transRecVO.setTransAmount(auctOrdVO.getAuctOrdAmount() * -1);
		transRecVO.setTransCont("");
		transRecVO.setTransRecTime(auctOrdVO.getAuctOrdTime());
		transRecVO.setTransState(1); // 0:商品退款 1:訂單扣款成功 2:儲值成功

		return transRecVO;
	}

	// 從會員查詢訂單
	public List<AuctOrdVO> getOrderRec(Integer memNo) {
		List<AuctOrdVO> auctOrdList = auctOrdDao.findByMemNo(memNo);
		return auctOrdList;

	}

	// 取得全部訂單
	public List<AuctOrdVO> getAllOrd() {
		List<AuctOrdVO> auctOrdAllList = auctOrdDao.getAll();
		return auctOrdAllList;
	}

	// 以訂單編號撈取訂單
	public AuctOrdVO getOneOrd(Integer auctOrdNo) {
		AuctOrdVO auctOrdVO = auctOrdDao.findByOrdNo(auctOrdNo);
		return auctOrdVO;

	}

	// 更新訂單
	public void updateOrd(AuctOrdVO auctOrd) {
		auctOrdDao.update(auctOrd);
		
		if(auctOrd.getAuctOrdState()==0) {
			transSvc.cancelTransRecord(auctOrd.getAuctOrdNo());
		}
		
	}
	
}

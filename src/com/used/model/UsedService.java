package com.used.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.usedpic.model.UsedPicVO;

public class UsedService {
	private UsedDAO_interface dao;
	public UsedService() {
		dao = new UsedJDBCDAO();
	}
	public UsedVO addUsed(Integer kindNo,Integer sellerNo,String usedName, Integer usedPrice,String usedProdDescription) {
		UsedVO usedVO = new UsedVO();
		usedVO.setKindNo(kindNo);
//		usedVO.setBuyerNo(buyerNo);
		usedVO.setSellerNo(sellerNo);
//		usedVO.setTransRecNo(transRecNo);
		usedVO.setUsedName(usedName);
		usedVO.setUsedPrice(usedPrice);
//		usedVO.setUsedState(usedState);
//		usedVO.setUsedLaunchedTime(usedLaunchedTime);
//		usedVO.setSoldTime(soldTime);
//		usedVO.setReceiverName(receiverName);
//		usedVO.setReceiverAddress(receiverAddress);
//		usedVO.setReceiverPhone(receiverPhone);
		usedVO.setUsedProdDescription(usedProdDescription);
		dao.insert(usedVO);
		return usedVO;
	}
	
	public UsedVO addUsed2(Integer kindNo,Integer sellerNo,String usedName, Integer usedPrice,String usedProdDescription, List<byte[]> list) {
		UsedVO usedVO = new UsedVO();
		usedVO.setKindNo(kindNo);
		usedVO.setSellerNo(sellerNo);
		usedVO.setUsedName(usedName);
		usedVO.setUsedPrice(usedPrice);
		usedVO.setUsedProdDescription(usedProdDescription);
		dao.insertWithPics(usedVO, list);
		return usedVO;
	}
	
	public UsedVO updateUsed(Integer usedNo, Integer kindNo, Integer buyerNo, Integer sellerNo, Integer transRecNo, String usedName, Integer usedPrice, Integer usedState, java.sql.Timestamp usedLaunchedTime, java.sql.Timestamp soldTime, String receiverName, String receiverAddress, String receiverPhone, String usedProdDescription) {
		UsedVO usedVO = new UsedVO();

		usedVO.setUsedNo(usedNo);
		usedVO.setKindNo(kindNo);
		usedVO.setBuyerNo(buyerNo);
		usedVO.setSellerNo(sellerNo);
		usedVO.setTransRecNo(transRecNo);
		usedVO.setUsedName(usedName);
		usedVO.setUsedPrice(usedPrice);
		usedVO.setUsedState(usedState);
		usedVO.setUsedLaunchedTime(usedLaunchedTime);
		usedVO.setSoldTime(soldTime);
		usedVO.setReceiverName(receiverName);
		usedVO.setReceiverAddress(receiverAddress);
		usedVO.setReceiverPhone(receiverPhone);
		usedVO.setUsedProdDescription(usedProdDescription);
		dao.update(usedVO);
		return usedVO;
	}
	
	public void updateUsed2(UsedVO usedVO, Integer kindNo,String usedName, Integer usedPrice, Integer usedState, String usedProdDescription) {
	
		usedVO.setKindNo(kindNo);
		usedVO.setUsedName(usedName);
		usedVO.setUsedPrice(usedPrice);
		usedVO.setUsedState(usedState);
		usedVO.setUsedProdDescription(usedProdDescription);
		dao.update2(usedVO);
	}
	
	public void cancel(Integer usedNo) {
		
		UsedVO usedVO = dao.findByPrimaryKey(usedNo);
		usedVO.setKindNo(usedVO.getKindNo());
		usedVO.setUsedName(usedVO.getUsedName());
		usedVO.setUsedPrice(usedVO.getUsedPrice());
		usedVO.setUsedState(3);
		usedVO.setUsedProdDescription(usedVO.getUsedProdDescription());
		dao.update2(usedVO);
	}
	
	public void deleteProd(Integer usedNo) {
		
		UsedVO usedVO = dao.findByPrimaryKey(usedNo);
		usedVO.setKindNo(usedVO.getKindNo());
		usedVO.setUsedName(usedVO.getUsedName());
		usedVO.setUsedPrice(usedVO.getUsedPrice());
		usedVO.setUsedState(4);
		usedVO.setUsedProdDescription(usedVO.getUsedProdDescription());
		dao.update2(usedVO);
	}
	
	public void updateTrans(UsedVO usedVO, Integer transRecNo, Integer buyerNo, String receiverName, String receiverAddress, String receiverPhone) {
	
		usedVO.setTransRecNo(transRecNo);
		usedVO.setUsedState(1);
		usedVO.setBuyerNo(buyerNo);
		usedVO.setSoldTime(new Timestamp(System.currentTimeMillis()));
		usedVO.setReceiverName(receiverName);
		usedVO.setReceiverAddress(receiverAddress);
		usedVO.setReceiverPhone(receiverPhone);
		dao.update(usedVO);
	}
	
	public UsedVO getOneUsed(Integer usedNo) {
		return dao.findByPrimaryKey(usedNo);		
	}
	
	public List<UsedVO> getAllProdByBuyer(Integer buyerNo) {
		return dao.getAllProdByBuyer(buyerNo);		
	}
	
	public List<UsedVO> getAllProdBySeller(Integer sellerNo) {
		return dao.getAllProdBySeller(sellerNo);		
	}
	
	public List<UsedVO> getAllUsed() {
		return dao.getAll();		
	}
	
	public List<UsedVO> getAllByBuyer() {
		return dao.getAllBuyer();		
	}
	
	public List<UsedVO> getAllBySeller() {
		return dao.getAllSeller();		
	}
	
	public List<UsedVO> searchProd(String name) {
		return dao.search(name);
	}
	
	public List<UsedVO> searchProdKind(Integer kind) {
		return dao.searchKind(kind);
	}

}

package com.usedmsg.model;

import java.util.List;

public class UsedMsgService {
	private UsedMsgDAO_interface dao;
	public UsedMsgService() {
		dao = new UsedMsgJDBCDAO();
	}
	public UsedMsgVO addUsedMsg(Integer memNo, Integer usedNo, String usedMsgText) {
		UsedMsgVO usedMsgVO = new UsedMsgVO();
		usedMsgVO.setMemNo(memNo);
		usedMsgVO.setUsedNo(usedNo);
		usedMsgVO.setUsedMsgText(usedMsgText);
		dao.insert(usedMsgVO);
		return usedMsgVO;
	}
	
	public UsedMsgVO updateUsedMsg(Integer usedMsgNo, Integer memNo, Integer usedNo, String usedMsgText) {
		UsedMsgVO usedMsgVO = new UsedMsgVO();
		usedMsgVO.setUsedMsgNo(usedMsgNo);
		usedMsgVO.setMemNo(memNo);
		usedMsgVO.setUsedNo(usedNo);
		usedMsgVO.setUsedMsgText(usedMsgText);
		dao.insert(usedMsgVO);
		return usedMsgVO;
	}
	
	public UsedMsgVO getOneUsedMsg(Integer usedMsgNo) {
		return dao.findByPrimaryKey(usedMsgNo);		
	}
	
	public void deleteMsg(Integer usedMsgNo) {
		dao.delete(usedMsgNo);		
	}
	
	public List<UsedMsgVO> getAllUsedMsg() {
		return dao.getAll();		
	}
	
	public List<UsedMsgVO> getMsgByUsedNo(Integer usedNo) {
		return dao.getAllByUsedNo(usedNo);		
	}
}

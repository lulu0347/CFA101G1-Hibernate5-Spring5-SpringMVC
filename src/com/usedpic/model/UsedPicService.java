package com.usedpic.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UsedPicService {
	private UsedPicDAO_interface dao;
	public UsedPicService() {
		dao = new UsedPicJDBCDAO();
	}
	public UsedPicVO addUsedPic(Integer usedNo,byte[] usedPic) {
		UsedPicVO usedPicVO = new UsedPicVO();
		usedPicVO.setUsedNo(usedNo);
		usedPicVO.setUsedPic(usedPic);
		
		dao.insert(usedPicVO);
		return usedPicVO;
	}
//	public UsedPicVO addUsedPic2(byte[] usedPic, Connection con) {
//		UsedPicVO usedPicVO = new UsedPicVO();
//
//		usedPicVO.setUsedPic(usedPic);
//		
//		dao.insert2(usedPicVO, con);
//		return usedPicVO;
//	}
	
//	public UsedPicVO updateUsedPic(Integer usedPicNo, Integer usedNo, Part part) {
//		UsedPicVO usedPicVO = new UsedPicVO();
//		usedPicVO.setUsedPicNo(usedPicNo);
//		usedPicVO.setUsedNo(usedNo);
//		dao.insert(usedPicVO, part);
//		return usedPicVO;
//	}
	
	public UsedPicVO getOneUsedPic(Integer usedPicNo) {
			return dao.findByPrimaryKey(usedPicNo);
	}
	
	public List<UsedPicVO> getOneUsedPics(Integer usedNo) {
		return dao.findByUsedNo(usedNo);
	}
	
	public List<UsedPicVO> getAllUsedPic() {
		return dao.getAll();		
	}
}

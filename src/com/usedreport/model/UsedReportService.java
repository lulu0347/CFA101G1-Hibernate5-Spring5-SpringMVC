package com.usedreport.model;

import java.sql.Date;
import java.util.List;

public class UsedReportService {
	private UsedReportDAO_interface dao;
	public UsedReportService() {
		dao = new UsedReportJDBCDAO();
	}
	public UsedReportVO addUsedReport(Integer usedNo, Integer memNo, Integer reportedMemNo, String usedReportReason) {
		UsedReportVO usedReportVO = new UsedReportVO();
		usedReportVO.setUsedNo(usedNo);
		usedReportVO.setMemNo(memNo);
		usedReportVO.setReportedMemNo(reportedMemNo);
		usedReportVO.setUsedReportReason(usedReportReason);
		dao.insert(usedReportVO);
		return usedReportVO;
	}
	
	public void updateUsedReport(UsedReportVO usedReportVO) {
		dao.update(usedReportVO);
	}
	
	public UsedReportVO getOneUsedReport(Integer usedMemNo, Integer usedNo) {
		return dao.findByPrimaryKey(usedMemNo, usedNo);		
	}
	
	public List<UsedReportVO> getAllUsedReport() {
		return dao.getAll();		
	}
}

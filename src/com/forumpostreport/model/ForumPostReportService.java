package com.forumpostreport.model;

import java.util.List;

import com.forumpost.model.ForumPostVO;

public class ForumPostReportService {
	private ForumPostReportDAO_interface dao;
	
	public ForumPostReportService() {
		dao = new ForumPostReportDAO();
	}
	
	public ForumPostReportVO addPostReport(Integer memNo,Integer forumPostNo,java.sql.Date forumPostReportTime,String forumPostReportWhy,Integer forumPostReportType) {
		ForumPostReportVO forumPostReportVO = new ForumPostReportVO();
		
		forumPostReportVO.setMemNo(memNo);
		forumPostReportVO.setForumPostNo(forumPostNo);
		forumPostReportVO.setForumPostReportTime(forumPostReportTime);
		forumPostReportVO.setForumPostReportWhy(forumPostReportWhy);
		forumPostReportVO.setForumPostReportType(forumPostReportType);
		
		dao.insert(forumPostReportVO);
		return forumPostReportVO;
	}
	
	public ForumPostReportVO updatePost(Integer forumPostReportNo,Integer memNo,Integer forumPostNo,java.sql.Date forumPostReportTime,String forumPostReportWhy,Integer forumPostReportType) {

		ForumPostReportVO forumPostReportVO = new ForumPostReportVO();
		
		forumPostReportVO.setForumPostReportNo(forumPostReportNo);
		forumPostReportVO.setMemNo(memNo);
		forumPostReportVO.setForumPostNo(forumPostNo);
		forumPostReportVO.setForumPostReportTime(forumPostReportTime);
		forumPostReportVO.setForumPostReportWhy(forumPostReportWhy);
		forumPostReportVO.setForumPostReportType(forumPostReportType);
		dao.update(forumPostReportVO);

		return forumPostReportVO;
	}

	public void deletePost(Integer forumPostReportNo) {
		dao.delete(forumPostReportNo);
	}

	public ForumPostReportVO getOnePost(Integer forumPostReportNo) {
		return dao.findByPrimaryKey(forumPostReportNo);
	}

	public List<ForumPostReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumPostReportVO> getMemPostReport(Integer memNo) {
		return dao.getMemPostReport(memNo);
	}
}

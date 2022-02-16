package com.forummsgreport.model;

import java.util.List;


public class ForumMsgReportService {
	private ForumMsgReportDAO_interface dao;
	
	public ForumMsgReportService() {
		dao = new ForumMsgReportDAO();
	}
	
	public ForumMsgReportVO addPost(Integer memNo,Integer forumMsgNo,java.sql.Date forumMsgReportTime,String forumMsgReportWhy,
			Integer forumMsgReportType) {

		ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();

		forumMsgReportVO.setMemNo(memNo);;
		forumMsgReportVO.setForumMsgNo(forumMsgNo);
		forumMsgReportVO.setForumMsgReportTime(forumMsgReportTime);
		forumMsgReportVO.setForumMsgReportWhy(forumMsgReportWhy);
		forumMsgReportVO.setForumMsgReportType(forumMsgReportType);;
		dao.insert(forumMsgReportVO);

		return forumMsgReportVO;
	}

	public ForumMsgReportVO updatePost(Integer forumMsgReportNo,Integer memNo,Integer forumMsgNo,java.sql.Date forumMsgReportTime,String forumMsgReportWhy,
			Integer forumMsgReportType) {

		ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();
		
		forumMsgReportVO.setForumMsgReportNo(forumMsgReportNo);;
		forumMsgReportVO.setMemNo(memNo);;
		forumMsgReportVO.setForumMsgNo(forumMsgNo);
		forumMsgReportVO.setForumMsgReportTime(forumMsgReportTime);
		forumMsgReportVO.setForumMsgReportWhy(forumMsgReportWhy);
		forumMsgReportVO.setForumMsgReportType(forumMsgReportType);;
		dao.update(forumMsgReportVO);

		return forumMsgReportVO;
	}

	public void deletePost(Integer forumMsgReportNo) {
		dao.delete(forumMsgReportNo);
	}

	public ForumMsgReportVO getOnePost(Integer forumMsgReportNo) {
		return dao.findByPrimaryKey(forumMsgReportNo);
	}

	public List<ForumMsgReportVO> getAll() {
		return dao.getAll();
	}
	public List<ForumMsgReportVO> getAll(Integer memNo) {
		return dao.getMemReport(memNo);
	}
}

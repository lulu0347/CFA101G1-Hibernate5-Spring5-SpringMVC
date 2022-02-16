package com.forumpostcollection.model;

import java.util.List;


public class ForumPostCollectionService {
	private ForumPostCollectionDAO_interface dao;
	
	public ForumPostCollectionService() {
		dao = new ForumPostCollectionDAO();
	}
	
	public ForumPostCollectionVO addPostCollection(Integer memNo,Integer forumPostNo) {
		ForumPostCollectionVO forumPostCollectionVO = new ForumPostCollectionVO();
		
		forumPostCollectionVO.setMemNo(memNo);
		forumPostCollectionVO.setForumPostNo(forumPostNo);
		
		dao.insert(forumPostCollectionVO);
		return forumPostCollectionVO;
	}
	
	public ForumPostCollectionVO updatePostCollection(Integer memNo,Integer forumPostNo) {

		ForumPostCollectionVO forumPostCollectionVO = new ForumPostCollectionVO();
		
		forumPostCollectionVO.setMemNo(memNo);
		forumPostCollectionVO.setForumPostNo(forumPostNo);


		return forumPostCollectionVO;
	}

	public void deletePostCollection(Integer memNo,Integer forumPostNo) {
		dao.delete(memNo, forumPostNo);
	}

	public ForumPostCollectionVO getOnePostCollection(Integer memNo,Integer forumPostNo) {
		return dao.findByPrimaryKey(memNo, forumPostNo);
	}

	public List<ForumPostCollectionVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumPostCollectionVO> getMemPostReportCollection(Integer memNo) {
		return dao.getMemCollection(memNo);
	}
}


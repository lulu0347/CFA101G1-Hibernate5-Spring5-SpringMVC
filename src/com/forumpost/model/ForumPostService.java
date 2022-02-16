package com.forumpost.model;

import java.util.List;


public class ForumPostService {
	private ForumPostDAO_interface dao;
	
	public ForumPostService() {
		dao = new ForumPostDAO();
	}
	
	public ForumPostVO addPost(String title,String description,Long gmtCreate,Long gmtModified,Integer creator,String tag) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setTitle(title);
		forumPostVO.setDescription(description);
		forumPostVO.setGmtCreate(gmtCreate);
		forumPostVO.setGmtModified(gmtModified);
		forumPostVO.setCreator(creator);
		forumPostVO.setTag(tag);
		dao.insert(forumPostVO);

		return forumPostVO;
	}

	public ForumPostVO updatePost(Integer id,String title,String description,Long gmtModified,String tag) {

		ForumPostVO forumPostVO = new ForumPostVO();
		
		forumPostVO.setId(id);
		forumPostVO.setTitle(title);
		forumPostVO.setDescription(description);
		forumPostVO.setGmtModified(gmtModified);
		forumPostVO.setTag(tag);
		dao.update(forumPostVO);

		return forumPostVO;
	}
	
	public void updateVC(Integer id) {

		ForumPostVO forumPostVO = new ForumPostVO();
		forumPostVO = dao.findByPrimaryKey(id);
		dao.VCPlus(forumPostVO);
	}
	
	public void updateCC(Integer id,Integer commentCount) {
		
		ForumPostVO forumPostVO = new ForumPostVO();
		
		forumPostVO.setId(id);
		forumPostVO.setCommentCount(commentCount);
		dao.CCPlus(forumPostVO);
	}

	public void deletePost(Integer id) {
		dao.delete(id);
	}

	public ForumPostVO getOnePost(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	
	public Object getQuestionPost(Integer id) {
		return dao.InnerbyId(id);
	}

	public List<ForumPostVO> getAll() {
		return dao.getAll();
	}	
	
	public List<ForumPostVO> getMemPost(Integer creator) {
		return dao.getMemPost(creator);
	}

	public ForumPostVO updatePostContent(Integer id, String description) {
		ForumPostVO forumPostVO = new ForumPostVO();
		
		forumPostVO.setId(id);
		forumPostVO.setDescription(description);
		dao.updatePostContent(forumPostVO);
		
		return forumPostVO;
	}

	public List<ForumPostVO> getSearch(String search) {
		return dao.likelyPost(search);	
	}
	
	public List<ForumPostVO> getLike() {
		return dao.getLike();	
	}

	public void updateLC(Integer id, Integer likeCount) {
		dao.LCPlus(id, likeCount);
		
	}
}

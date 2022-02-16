package com.forummsg.model;

import java.util.List;


public class ForumMsgService {
	private ForumMsgDAO_interface dao;
	
	public ForumMsgService() {
		dao = new ForumMsgDAO();
	}
	
	public void addMsg(Integer parentId, String content, Integer type, Integer commentator) {

		ForumMsgVO forumMsgVO = new ForumMsgVO();

		forumMsgVO.setParentId(parentId);
		forumMsgVO.setContent(content);
		forumMsgVO.setType(type);
		forumMsgVO.setCommentator(commentator);

		dao.insert(forumMsgVO);
	}

	public ForumMsgVO updateMsg(Integer id, Integer parentId, String content, Integer type, Integer commentator, Long gmtCreate, Long gmtModified,
		    Integer likeCount,Integer commentCount) {

		ForumMsgVO forumMsgVO = new ForumMsgVO();
		
		forumMsgVO.setId(id);
		forumMsgVO.setParentId(parentId);
		forumMsgVO.setContent(content);
		forumMsgVO.setType(type);
		forumMsgVO.setCommentator(commentator);
		forumMsgVO.setGmtCreate(gmtCreate);
		forumMsgVO.setGmtModified(gmtModified);
		forumMsgVO.setLikeCount(likeCount);
		forumMsgVO.setCommentCount(commentCount);
		dao.update(forumMsgVO);

		return forumMsgVO;
	}

	public void deleteMsg(Integer id) {
		dao.delete(id);
	}

	public ForumMsgVO getOneMsg(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<ForumMsgVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumMsgVO> getMemMsg(Integer commentator) {
		return dao.getMemMsg(commentator);
	}	
	
	public List<ForumMsgVO> getPostMsg(Integer parentId) {
		return dao.getPostMsg(parentId);
	}	
}

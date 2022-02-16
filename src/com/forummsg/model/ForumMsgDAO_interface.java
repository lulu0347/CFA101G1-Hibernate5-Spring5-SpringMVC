package com.forummsg.model;

import java.util.List;

import com.forummsg.model.ForumMsgVO;


public interface ForumMsgDAO_interface {
	public void insert(ForumMsgVO forumMsgVO);
    public void update(ForumMsgVO forumMsgVO);
    public void delete(Integer forumMsgNo);
    public ForumMsgVO findByPrimaryKey(Integer forumMsgNo);
    public List<ForumMsgVO> getAll();
	public List<ForumMsgVO> getMemMsg(Integer memNo);
	public List<ForumMsgVO> getPostMsg(Integer parentId);
}

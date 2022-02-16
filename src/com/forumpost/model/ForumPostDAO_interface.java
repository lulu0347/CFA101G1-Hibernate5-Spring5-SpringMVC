package com.forumpost.model;

import java.util.List;


public interface ForumPostDAO_interface {
	public void insert(ForumPostVO forumPostVO);
    public void update(ForumPostVO forumPostVO);
    public void delete(Integer id);
    public ForumPostVO findByPrimaryKey(Integer id);
    public List<ForumPostVO> getAll();
	public List<ForumPostVO> getMemPost(Integer creator);
	public Object InnerbyId(Integer id);
	public void VCPlus(ForumPostVO forumPostVO);
	public void CCPlus(ForumPostVO forumPostVO);
	public ForumPostVO updatePostContent(ForumPostVO forumPostVO);
	public List<ForumPostVO> likelyPost(String search);
	public void LCPlus(Integer id,Integer likeCount);
	public List<ForumPostVO> getLike();
}

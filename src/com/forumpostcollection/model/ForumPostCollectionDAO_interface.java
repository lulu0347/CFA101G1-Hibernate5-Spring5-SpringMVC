package com.forumpostcollection.model;

import java.util.List;

public interface ForumPostCollectionDAO_interface {
	public void insert(ForumPostCollectionVO forumPostCollectionVO);
    public void update(ForumPostCollectionVO forumPostCollectionVO);
    public void delete(Integer memNo,Integer forumPostNo);    
    public ForumPostCollectionVO findByPrimaryKey(Integer memNo,Integer forumPostNo);
    public List<ForumPostCollectionVO> getAll();
    public List<ForumPostCollectionVO> getMemCollection(Integer memNo);
}

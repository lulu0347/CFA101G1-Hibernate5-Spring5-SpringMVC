package com.forumpostreport.model;

import java.util.List;


public interface ForumPostReportDAO_interface {
	public void insert(ForumPostReportVO forumPostVO);
    public void update(ForumPostReportVO forumPostVO);
    public void delete(Integer forumPostReportNo);
    public ForumPostReportVO findByPrimaryKey(Integer forumPostReportNo);
    public List<ForumPostReportVO> getAll();
	public List<ForumPostReportVO> getMemPostReport(Integer memNo);
}

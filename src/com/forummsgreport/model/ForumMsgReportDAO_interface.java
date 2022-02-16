package com.forummsgreport.model;

import java.util.List;

import com.forummsg.model.ForumMsgVO;


public interface ForumMsgReportDAO_interface {
	public void insert(ForumMsgReportVO forumMsgReportVO);
    public void update(ForumMsgReportVO forumMsgReportVO);
    public void delete(Integer forumMsgReportNo);
    public ForumMsgReportVO findByPrimaryKey(Integer forumMsgReportNo);
    public List<ForumMsgReportVO> getAll();
	public List<ForumMsgReportVO> getMemReport(Integer memNo);
}

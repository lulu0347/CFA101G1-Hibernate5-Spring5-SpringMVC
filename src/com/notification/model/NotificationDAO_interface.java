package com.notification.model;

import java.util.*;

public interface NotificationDAO_interface {
    public void insert(NotificationVO NotifyVO);
    public void update(NotificationVO NotifyVO);
    public void delete(Integer NotifyNo);
    public NotificationVO findByPrimaryKey(Integer NotifyNo);
    public List<NotificationVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

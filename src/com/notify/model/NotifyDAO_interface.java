package com.notify.model;

import java.util.List;


public interface NotifyDAO_interface {
	public void insert(NotifyVO notifyVO);
    public void update(NotifyVO notifyVO);
    public void delete(Integer id);
    public NotifyVO findByPrimaryKey(Integer id);
    public List<NotifyVO> getAll();
	public List<NotifyVO> getMemNotify(Integer receiver);
	public void ChangeStatus(NotifyVO notifyVO);
	public List<NotifyVO> getZeroStatus(Integer receiver);
	public void qmid(NotifyVO notifyVO);

}

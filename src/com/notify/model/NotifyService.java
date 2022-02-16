package com.notify.model;

import java.util.List;

public class NotifyService {
	private NotifyDAO_interface dao;
	
	public NotifyService() {
		dao = new NotifyDAO();
	}
	
	public void addPost(Integer notifier, String notifier_name, Integer receiver, Integer outerid, String outer_title, Integer type, Long gmt_create) 
	{

		NotifyVO notifyVO = new NotifyVO();

		notifyVO.setNotifier(notifier);
		notifyVO.setNotifier_name(notifier_name);
		notifyVO.setReceiver(receiver);
		notifyVO.setOuterid(outerid);
		notifyVO.setOuter_title(outer_title);
		notifyVO.setType(type);
		notifyVO.setGmt_create(gmt_create);
		dao.insert(notifyVO);

	}

	public void updateOne(Integer status,Integer id) {

		NotifyVO notifyVO = new NotifyVO();
		
		notifyVO.setStatus(status);
		notifyVO.setId(id);
	
		dao.update(notifyVO);
	}
	
	public void qmid(Integer status,Integer receiver,Integer outerid) {
		
		NotifyVO notifyVO = new NotifyVO();
		
		notifyVO.setStatus(status);
		notifyVO.setReceiver(receiver);
		notifyVO.setOuterid(outerid);
		
		dao.qmid(notifyVO);
	}
	
	public void update(Integer status,Integer receiver) {
		
		NotifyVO notifyVO = new NotifyVO();
		
		notifyVO.setStatus(status);
		notifyVO.setReceiver(receiver);
		
		dao.ChangeStatus(notifyVO);
	}

	public void deletePost(Integer id) {
		dao.delete(id);
	}

	public NotifyVO getOneNotify(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<NotifyVO> getAll() {
		return dao.getAll();
	}	
	
	public List<NotifyVO> getMemPost(Integer receiver) {
		return dao.getMemNotify(receiver);
	}
	
	public List<NotifyVO> getZeroStatus(Integer receiver) {
		return dao.getZeroStatus(receiver);
	}

}

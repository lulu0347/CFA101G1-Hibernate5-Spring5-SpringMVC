package com.notification.model;

import java.util.List;

public class NotificationService {

	private NotificationDAO_interface dao;

	public NotificationService() {
		dao = new NotificationDAO();
	}

	public NotificationVO addNotify(Integer memno, String activitiesnotify) {

		NotificationVO notifyVO = new NotificationVO();

		notifyVO.setMemno(memno);
		notifyVO.setActivitiesnotify(activitiesnotify);
		dao.insert(notifyVO);

		return notifyVO;
	}

	public NotificationVO updateNotify(Integer notifyno, Integer memno, String activitiesnotify) {

		NotificationVO notifyVO = new NotificationVO();

		notifyVO.setNotifyno(notifyno);
		notifyVO.setMemno(memno);
		notifyVO.setActivitiesnotify(activitiesnotify);
		dao.update(notifyVO);

		return notifyVO;
	}

	public void deleteNotify(Integer notifyno) {
		dao.delete(notifyno);
	}

	public NotificationVO getOneNotify(Integer notifyno) {
		return dao.findByPrimaryKey(notifyno);
	}

	public List<NotificationVO> getAll() {
		return dao.getAll();
	}
}

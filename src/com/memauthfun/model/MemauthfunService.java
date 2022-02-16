package com.memauthfun.model;

import java.util.List;

public class MemauthfunService {

	private MemauthfunDAO_interface dao;

	public MemauthfunService() {
		dao = new MemauthfunDAO();
	}

	public MemauthfunVO addMemauth(Integer memauthfunno, String authfunname) {

		MemauthfunVO memauthfunVO = new MemauthfunVO();

		memauthfunVO.setMemauthfunno(memauthfunno);
		memauthfunVO.setAuthfunname(authfunname);
		dao.insert(memauthfunVO);

		return memauthfunVO;
	}

	public MemauthfunVO updateMemauth(Integer memauthfunno, String authfunname) {

		MemauthfunVO memauthfunVO = new MemauthfunVO();

		memauthfunVO.setMemauthfunno(memauthfunno);
		memauthfunVO.setAuthfunname(authfunname);
		dao.update(memauthfunVO);

		return memauthfunVO;
	}

	public void deleteMem(Integer memauthfunno) {
		dao.delete(memauthfunno);
	}

	public MemauthfunVO getOneMem(Integer memauthfunno) {
		return dao.findByPrimaryKey(memauthfunno);
	}

	public List<MemauthfunVO> getAll() {
		return dao.getAll();
	}
}

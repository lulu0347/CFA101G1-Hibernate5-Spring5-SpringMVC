package com.memauthrization.model;

import java.util.List;

public class MemauthrizationService {

	private MemauthrizationDAO_interface dao;

	public MemauthrizationService() {
		dao = new MemauthrizationDAO();
	}

	public MemauthrizationVO addMemauthrization(Integer memno, Integer memauthfunno) {

		MemauthrizationVO memauthrizationVO = new MemauthrizationVO();

		memauthrizationVO.setMemno(memno);
		memauthrizationVO.setMemauthfunno(memauthfunno);
		dao.insert(memauthrizationVO);

		return memauthrizationVO;
	}

	public MemauthrizationVO updateMemauthrization(Integer memno, Integer memauthfunno) {

		MemauthrizationVO memauthrizationVO = new MemauthrizationVO();

		memauthrizationVO.setMemno(memno);
		memauthrizationVO.setMemauthfunno(memauthfunno);
		dao.update(memauthrizationVO);

		return memauthrizationVO;
	}

	public void deleteMemauthrization(Integer memauthfunno) {
		dao.delete(memauthfunno);
	}

	public MemauthrizationVO getOneMem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemauthrizationVO> getAll() {
		return dao.getAll();
	}
}

package com.repairform.model;

import java.sql.Date;
import java.util.List;

public class RepairFormService {

	private RepairFormDAO_interface dao;

	public RepairFormService() {
		dao = new RepairFormDAO();
	}

	
//==================================================================add
	public RepairFormVO addrepairform(Integer memno, Integer orderno, Integer itemno,
			Date createtime, String repairformstatus, Integer adminno, String repairinfo, Date repairend) {

		RepairFormVO repairformVO = new RepairFormVO();

		repairformVO.setMemno(memno);
		repairformVO.setOrderno(orderno);
		repairformVO.setItemno(itemno);
		repairformVO.setCreatetime(createtime);
		repairformVO.setRepairformstatus(repairformstatus);
		repairformVO.setAdminno(adminno);
		repairformVO.setRepairinfo(repairinfo);
		repairformVO.setRepairend(repairend);
		
		dao.insert(repairformVO);

		return repairformVO;
	}

	
	

	

	
	
	
	
	
	

	//====================================Update
	public RepairFormVO updaterepairform(Integer repairformno, Integer memno, Integer orderno, Integer itemno,
			Date createtime, String repairformstatus, Integer adminno, String repairinfo, Date repairend) {

		RepairFormVO repairformVO = new RepairFormVO();

		repairformVO.setRepairformno(repairformno);
		repairformVO.setMemno(memno);
		repairformVO.setOrderno(orderno);
		repairformVO.setItemno(itemno);
		repairformVO.setCreatetime(createtime);
		repairformVO.setRepairformstatus(repairformstatus);
		repairformVO.setAdminno(adminno);
		repairformVO.setRepairinfo(repairinfo);
		repairformVO.setRepairend(repairend);
		
		dao.update(repairformVO);

		return repairformVO;
	}
	//====================================delete
	public void deleterepairform(Integer repairformno) {
		dao.delete(repairformno);
	}
	
	public RepairFormVO getOneRepairForm(Integer repairformno) {
		return dao.findByPrimaryKey(repairformno);
	}
	
	public List<RepairFormVO> getAll() {
		return dao.getAll();
	}



}





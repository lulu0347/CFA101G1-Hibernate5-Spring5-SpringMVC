package com.auctActProd.model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auctPic.model.AuctActPicVO;

public class AuctActProdService {

	private AuctActProdDAO_interface auctActProdDAO = new AuctActProdDAO_JDBC();

	public AuctActProdService() {
		auctActProdDAO = new AuctActProdDAO_JDBC();
	}

	public List<AuctActProdVO> getAll() {
		return auctActProdDAO.getAll();
	}
	
	
	public AuctActProdVO addActProd(AuctActProdVO auctActProdVO) {
		auctActProdDAO.add(auctActProdVO);
		return auctActProdVO;
	}

	public AuctActProdVO updateActProd(AuctActProdVO auctActProdVO) throws FileNotFoundException {
		auctActProdDAO.update(auctActProdVO);
		return auctActProdVO;
	}
	
	public void deleteActProd(Integer auctActProdNo) {
		auctActProdDAO.delete(auctActProdNo);

	}
	
	public List<AuctActProdVO> getByActNo(Integer auctActNo){	
		List<AuctActProdVO> auctActProdList = auctActProdDAO.findByActNo(auctActNo);
		return auctActProdList;
		
	}
	
	public AuctActProdVO  getByActNoProdNo(Integer auctActNo,Integer auctProdNo) {
		AuctActProdVO auctActProdVO = new AuctActProdVO();
		auctActProdVO=auctActProdDAO.findByActNoProdNo(auctActNo, auctProdNo);
		return auctActProdVO;
		
	}
	public AuctActProdVO getByActProdNo(Integer auctActProdNo){	
		AuctActProdVO auctActProdVO = auctActProdDAO.findByActProdNo(auctActProdNo);
		return auctActProdVO;
		
	}
	
	
	
	
}

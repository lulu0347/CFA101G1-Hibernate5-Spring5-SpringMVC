package com.auctAct.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.auctActProd.model.*;
import com.auctPic.model.*;

public class AuctActService {

	private AuctActDAO_interface auctActDAO = new AuctActDAO_JDBC();
	private AuctActProdDAO_interface auctActProdDAO = new AuctActProdDAO_JDBC();
	private AuctActPicDAO_interface auctActPicDAO = new AuctActPicDAO_JDBC();

	public AuctActVO addAct(AuctActVO auctActVO) {
		Integer auctActNo = auctActDAO.add(auctActVO);
		return auctActVO;
	}

	public AuctActVO addActPic(final AuctActVO auctActVO, List<AuctActPicVO> photoVOList) {
		Integer auctActNo = auctActDAO.add(auctActVO);

		for (AuctActPicVO vo : photoVOList) {
			vo.setAuctActNo(auctActNo);
			auctActPicDAO.add(vo);
		}

		return auctActVO;
	}
	
	public AuctActVO addActPic(AuctActVO auctActVO, Map<String, AuctActPicVO> photoMap) {
		//將傳入的MAP物件轉為LIST物件的方式
		List<AuctActPicVO> photoVOList =new ArrayList<AuctActPicVO>(photoMap.values());
		addActPic(auctActVO, photoVOList);

		return auctActVO;
	}

	public AuctActVO addActwithPicwithProd(AuctActVO auctActVO, List<AuctActPicVO> auctActPicList,
			List<AuctActProdVO> actProdList) {
		Connection con = null;

		try {
			con = auctActDAO.getConnection();
			con.setAutoCommit(false);
			Integer auctActNo = auctActDAO.addActCon(con, auctActVO);
			auctActPicDAO.addActCon(con, auctActNo, auctActPicList);
			auctActProdDAO.addActCon(con, auctActNo, actProdList);
			auctActVO.setAuctActNo(auctActNo);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return auctActVO;
	}

	public AuctActVO update(Integer auctActNo, String auctActName, String auctActDesc, Integer auctActState,
			Timestamp auctStartTime, Timestamp auctEndTime) {

		AuctActVO auctActVO = new AuctActVO();
		auctActVO.setAuctActNo(auctActNo);
		auctActVO.setAuctActName(auctActName);
		auctActVO.setAuctActDesc(auctActDesc);
		auctActVO.setAuctActState(auctActState);
		auctActVO.setAuctStartTime(auctStartTime);
		auctActVO.setAuctEndTime(auctEndTime);
		auctActDAO.update(auctActVO);

		return auctActVO;
	}

	public AuctActVO updateAct(AuctActVO auctActVO, Map<String, AuctActPicVO> photoVOMap) {
		auctActDAO.update(auctActVO);

		for (AuctActPicVO vo : photoVOMap.values()) {
			vo.setAuctActNo(auctActVO.getAuctActNo());
			auctActPicDAO.update(vo);
		}

		return auctActVO;
	}

	public AuctActVO updateActwithPicwithProd(AuctActVO auctActVO, List<AuctActPicVO> auctActPicList,
			List<AuctActProdVO> actProdList) {

		boolean addActFinal = false;
		Connection con = null;
		try {
			con = auctActDAO.getConnection();

			auctActDAO.updateActCon(con, auctActVO);
			auctActPicDAO.updateActCon(con, auctActVO.getAuctActNo(), auctActPicList);
			auctActProdDAO.updateActCon(con, auctActVO.getAuctActNo(), actProdList);

			con.commit();
			addActFinal = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return auctActVO;
	}

	public List<AuctActVO> getAll() {
		return auctActDAO.getAll();

	}
	//TODO 取出當下時間的活動
	
	
	
	
	

	public AuctActVO getOneAct(Integer auctActNo) {
		AuctActDAO_JDBC actdao = new AuctActDAO_JDBC();
		AuctActVO auctActVO = actdao.findByAuctActNo(auctActNo);
		return auctActVO;
	}

	public void deleteAct(Integer auctActNo) {

		auctActDAO.delete(auctActNo);

	}

}

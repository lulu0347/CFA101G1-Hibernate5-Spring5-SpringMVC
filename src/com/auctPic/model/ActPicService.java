package com.auctPic.model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActPicService {

	private AuctActPicDAO_interface actPicDAO = new AuctActPicDAO_JDBC();

	public ActPicService() {
		actPicDAO = new AuctActPicDAO_JDBC();
	}

	public List<AuctActPicVO> getAll() {
		return actPicDAO.getAll();
	}

	public AuctActPicVO getOneActPic(Integer auctActNo, String auctActPicInfo) {
		return actPicDAO.findByActNo_PicInfo(auctActNo, auctActPicInfo);
	}

	/**
	 * 
	 * @param auctActNo
	 * @return Map型態的圖片物件 (key:AuctActPicInfo ,value: auctActPicVO)
	 */
	public Map<String, AuctActPicVO> getPicListByActNo(Integer auctActNo) {

		List<AuctActPicVO> auctActPicList = actPicDAO.findPicwithProdNo(auctActNo);
		Map<String, AuctActPicVO> photos = new HashMap<String, AuctActPicVO>();
		for (AuctActPicVO ActPicVO : auctActPicList) {
			photos.put(ActPicVO.getAuctActPicInfo(), ActPicVO);
		}
		return photos;
	}

	public AuctActPicVO updateActPic(AuctActPicVO auctActPicVO) throws FileNotFoundException {

		actPicDAO.update(auctActPicVO);

		return auctActPicVO;
	}

	public AuctActPicVO addActPic(AuctActPicVO auctActPicVO) {
		actPicDAO.add(auctActPicVO);

		return auctActPicVO;
	}

	public void deleteActPic(Integer auctActPicNo) {

		actPicDAO.delete(auctActPicNo);

	}

}

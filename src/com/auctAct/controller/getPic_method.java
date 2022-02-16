package com.auctAct.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auctPic.model.AuctActPicVO;

public class getPic_method {

	private List<AuctActPicVO> getPhotos(HttpServletRequest req) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		List<AuctActPicVO> list = new ArrayList<>();
		List<String> nameList = Arrays.asList("banner", "cart", "button");

		for (Part part : parts) {
			AuctActPicVO picVO = new AuctActPicVO();

			String info = part.getName();
			String contentType = part.getContentType();
			if (contentType == null || !contentType.startsWith("image/") || !nameList.contains(info)) {
				continue;
			}

			InputStream is = part.getInputStream();
			byte[] photo = new byte[is.available()];
			if (is.available() == 0) {
				continue;
			}
			is.read(photo);
			is.close();

			picVO.setAuctActPic(photo);
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			list.add(picVO);
		}

		return list;
	}

	private Map<String, String> getBaseMap(Map<String, AuctActPicVO> photos) {
		String prefixFormat = "data:%s;base64,";
		Map<String, String> result = new HashMap<>();
		for (String key : photos.keySet()) {
			if (photos.get(key) != null) {
				AuctActPicVO vo = photos.get(key);
				String baseStr = String.format(prefixFormat, vo.getAuctActPicFormat())
						+ Base64.getEncoder().encodeToString(vo.getAuctActPic());
				result.put(key + "Temp", baseStr);
			}
		}
		return result;
	}

	private List<AuctActPicVO> getActPhotos(HttpServletRequest req) throws IOException, ServletException {
		List<AuctActPicVO> list = new ArrayList<>();
		String[] paramArray = { "banner", "cart", "button" };
		for (String paramName : paramArray) {
			AuctActPicVO vo = getpic(req, paramName);
			if (vo.getAuctActPicFormat().startsWith("image/")) {
				list.add(vo);
			}
		}
		return list;
	}

	private AuctActPicVO getpic(HttpServletRequest req, String paramName) throws IOException, ServletException {

		// 從part物件拿出上傳圖片
		Part pic = req.getPart(paramName);
		AuctActPicVO picVO = new AuctActPicVO();
		String info = pic.getName();
		String contentType = pic.getContentType();
		InputStream is = pic.getInputStream();

		// 如果沒上傳，則撈取 session 內的寄存圖片
		if (is.available() != 0) {
			byte[] photo = new byte[is.available()];
			is.read(photo);
			is.close();
			picVO.setAuctActPicInfo(info);
			picVO.setAuctActPicFormat(contentType);
			picVO.setAuctActPic(photo);
		} else {
			// 從 session 中撈取圖片
			picVO = getPicFromSession(req, paramName);
		}

		return picVO;
	}

	private AuctActPicVO getPicFromSession(HttpServletRequest req, String paramName) {
		HttpSession session = req.getSession();
		// 從session 拿出 photos map
		Map<String, AuctActPicVO> photos = (Map<String, AuctActPicVO>) session.getAttribute("photos");

		// 如果此 seesion 剛建立，則 photos == null
		if (photos == null) {
			return null;
		} else {
			// 拿出 圖片物件，如果是 null 代表沒有寄存，也代表使用者未曾上傳，需警告使用者
			return photos.get(paramName);
		}
	}

}

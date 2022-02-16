package com.itemIC.model;

import java.util.ArrayList;
import java.util.List;

import com.item.model.ItemDAO;
import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO;
import com.itemCollection.model.ItemCollectionVO;

public class ItemICService {

	private ItemICDAO_interface dao;

	public ItemICService() {
		dao = new ItemICDAO();
	}

	// ------讓會員搜尋收藏列表且查看時能同時顯示出商品資料------------------
	public List<ItemICVO> getCollectionByMemNo(Integer memNo) {
		return dao.getCOllectionByMemNo(memNo);
		
	}

}

package com.itemIC.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;
import com.item.model.ItemVO;
import com.itemCollection.model.ItemCollectionDAO_interface;
import com.itemCollection.model.ItemCollectionVO;

/*
這邊非常重要,必須一定絕對要用Bean的概念,不能以自行new物件的方式執行
一來是搭配Spring
二來是會噴nullpoint(原因待查明,目前未知(´･_･`),推測是物件已經在Spring容器中建立好了,導致自己後續new的接不到memNo參數?)

過程 : 一開始new出ItemCollectionDAO及ItemDAO去執行其中方法會導致MemNo取不到進而噴出nullPoint的例外,但使用Bean注入則沒有這個問題
耗時 : 1個小時( ˘･з･).....
*/

public class ItemICDAO implements ItemICDAO_interface {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");

//	private static final String GET_ITEMCOLLECTIONBYMEMNO_STMT = "SELECT * FROM itemcollection AS itc INNER JOIN item AS it ON itc.itemno = it.itemno WHERE itc.memno = ?";
	@Override
	// ------讓會員搜尋收藏列表且查看時能同時顯示出商品資料------------------
	public List<ItemICVO> getCOllectionByMemNo(Integer memNo) {
		
		ItemDAO_interface daoForItem = (ItemDAO_interface) context.getBean("ItemDAO");
		ItemCollectionDAO_interface daoForItemCollection = (ItemCollectionDAO_interface) context.getBean("ItemCollectionDAO");
		
		// 將memNo方法傳入itemCollectionDAO的findByMemNo方法,取得此會員的收藏列表(List)
		List<ItemCollectionVO> list = daoForItemCollection.findByMemNo(memNo);
		// new一個Array裝ItemICVO
		List<ItemICVO> listForItemICVO = new ArrayList<ItemICVO>();
		
		// 遍歷放入ItemICVO中,再把ItemICVO放到Array中
		for (ItemCollectionVO icVo : list) {
			ItemICVO itemICVO = new ItemICVO();
			ItemVO itemVO = daoForItem.findByItemNo(icVo.getId().getItemNo());
			itemICVO.setItemName(itemVO.getItemName());
			itemICVO.setKindNo(itemVO.getProductKindVO().getKindNo());
			itemICVO.setItemNo(itemVO.getItemNo());
			itemICVO.setItemPrice(itemVO.getItemPrice());
			itemICVO.setItemState(itemVO.getItemState());
			itemICVO.setMemNo(memNo);
			itemICVO.setWarrantyDate(itemVO.getWarrantyDate().doubleValue());

			listForItemICVO.add(itemICVO);
		}
		return listForItemICVO;
	}

	public static void main(String[] args) {
		//
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config1-DriverManagerDataSource.xml");
		// 建立DAO物件
		ItemDAO_interface dao = (ItemDAO_interface) context.getBean("ItemDAO");
		ItemCollectionDAO_interface dao2 = (ItemCollectionDAO_interface) context.getBean("ItemCollectionDAO");

		// getCOllectionByMemNo() ok
		List<ItemCollectionVO> list = dao2.findByMemNo(11001);
		List<ItemICVO> listForItemICVO = new ArrayList<ItemICVO>();
		
		for (ItemCollectionVO icVo : list) {
			ItemICVO itemICVO = new ItemICVO();
			ItemVO itemVO = dao.findByItemNo(icVo.getId().getItemNo());
			itemICVO.setItemName(itemVO.getItemName());
			itemICVO.setKindNo(itemVO.getProductKindVO().getKindNo());
			itemICVO.setItemNo(itemVO.getItemNo());
			itemICVO.setItemPrice(itemVO.getItemPrice());
			itemICVO.setItemState(itemVO.getItemState());
			itemICVO.setMemNo(11001);
			itemICVO.setWarrantyDate(itemVO.getWarrantyDate().doubleValue());

			listForItemICVO.add(itemICVO);
		}
//		System.out.println(listForItemICVO);
		for(ItemICVO icvo : listForItemICVO) {
			System.out.print(icvo.getItemName()+",");
			System.out.print(icvo.getItemNo()+",");
			System.out.print(icvo.getItemPrice()+",");
			System.out.print(icvo.getItemState()+",");
			System.out.print(icvo.getKindNo()+",");
			System.out.print(icvo.getMemNo()+",");
			System.out.print(icvo.getWarrantyDate());
			System.out.println();
		}

		((ClassPathXmlApplicationContext) context).close();
	}

}

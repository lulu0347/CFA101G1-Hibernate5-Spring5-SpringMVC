package com.bid.model;

import java.util.*;

import com.bidpic.model.BidPicVO;

public interface BidDAO_interface {
        public void insert(BidVO bidVO);
        public void update(BidVO bidVO);
        // 測試更新競標狀態
        public void updateBidState(BidVO bidVO);
        
        public void delete(Integer bidProdNo);
        public BidVO findByPrimaryKey(Integer bidProdNo);
        public BidVO findByKindNo(Integer kindNo);
        public BidVO findByTransRecNo(Integer transRecNo);
        public BidVO findByBidWinnerNo(Integer bidWinnerNo);
        
        // 得標者和商品狀態
        public List<BidVO> findByBidWinnerNoANDBidProdState(Integer bidWinnerNo, Integer bidProdState);
        
        // 測試搜尋功能
        public List<BidVO> findByKeyword(String bidProdName);

        public BidVO findByBidState(Integer bidState);
        public List<BidVO> findByBidProdState(Integer bidProdState);
        public List<BidVO> findAllByKindNo(Integer kindNo);
        // 測試取得所有應更新競標狀態成 1.截標 的商品
        public List<BidVO> findBidStateToOne(Integer bidState);
        // 測試取得所有應更新競標商品狀態成 5.棄標 的商品
        public List<BidVO> findBidProdStateToFive(Integer bidProdState);
        
        public List<BidVO> getAll();
        // 萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<BidVO> getAll(Map<String, String[]> map);
//        同時新增
        public void insertWithBidPics(BidVO bidVO , List<byte[]> list);
        
        // 截標時間到時使用
		public void update2(BidVO bidVO);
		
		// 截標時間到且流標(無出價紀錄)時使用
		public void update3(BidVO bidVO);
		
		// 結帳時使用
		public void update4(BidVO bidVO);
		
		// 更新錢包交易編號時使用
		public void update5(BidVO bidVO);
		
		// 更新所有應更新競標商品狀態成 5.棄標 的商品時使用
		public void update6(BidVO bidVO);
		// 人工更新商品資料時使用 0803
		public void update7(BidVO bidVO);
}

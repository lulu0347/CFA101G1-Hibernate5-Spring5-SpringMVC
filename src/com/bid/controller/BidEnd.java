package com.bid.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.bid.model.BidService;
import com.bid.model.BidVO;

public class BidEnd {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA101G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CFA101G1";

	
	private static final String UPDATE = 
			"UPDATE bid set kindNo=?,transRecNo=?,bidWinnerNo=?,bidProdName=?,bidProdDescription=?,bidProdStartPrice=?,bidState=?,bidProdStartTime=?,bidProdEndTime=?,bidWinnerPrice=?,bidPriceIncrement=?,receiverName=?,receiverAddress=?,receiverPhone=?,bidProdState=? WHERE bidProdNo = ?";

	public static void bidStateToOne() {
		Timer timer = new Timer();
		Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 20, 0, 0, 0);
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
//                System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
//                System.out.println("工作執行的時間 = " + new Date() + "\n");                
				List<BidVO> list = new ArrayList<>();
				BidService bidSvc = new BidService();
				Integer bidState = 0;
				list = bidSvc.findBidStateToOne(bidState);
				for (BidVO bidVO : list) {
					bidVO.setBidState(bidState);
					bidSvc.updateBid(bidVO);
				}
//				bidSvc.updateBid(bidProdNo, kindNo, transRecNo, bidWinnerNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidWinnerPrice, bidPriceIncrement, receiverName, receiverAddress, receiverPhone, bidProdState);
			}
			
		}, cal.getTime(), 1000);
		
	}
}

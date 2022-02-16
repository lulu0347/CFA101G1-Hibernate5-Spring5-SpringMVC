package com.bid.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bid.model.BidService;
import com.bid.model.BidVO;
import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;

public class ScheduleServlet2 extends HttpServlet {    
	private static final long serialVersionUID = 1L;
    Timer timer; 
    int count = 0;        
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init() {        
        timer = new Timer();
        Calendar cal = new GregorianCalendar(2021, Calendar.JULY, 22, 0, 0, 0);        
        TimerTask task = new TimerTask(){
                
        	// 截標後更改競標狀態為1.截標 或 2.流標
            public void run(){
//                System.out.println("This is Task"+ count);
//                System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
//                System.out.println("工作執行的時間 = " + new Date() + "\n");                
//                count++;
//                System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
//                System.out.println("工作執行的時間 = " + new Date() + "\n");                
				List<BidVO> list = new ArrayList<>();
				BidService bidSvc = new BidService();
				BidRecordService bidRecordSvc = new BidRecordService();
				
				Integer bidState = 0;
				Integer bidWinnerPrice = null;
				Integer bidWinnerNo = null;
				
//				有無出價紀錄
				boolean flag = true;
				
				list = bidSvc.findBidStateToOne(bidState);
				for (BidVO bidVO : list) {
					Integer bidProdNo = bidVO.getBidProdNo();
					BidRecordVO bidRecordVO = new BidRecordVO();
					bidRecordVO = bidRecordSvc.findByBidProdNoHighest(bidProdNo);
					try {
						bidWinnerPrice = bidRecordVO.getBidPrice();
						bidWinnerNo = bidRecordVO.getMemNo();
						bidVO.setBidState(1);
					} catch (NullPointerException ne) {
						bidWinnerPrice = null;
						bidWinnerNo = null;
						flag = false;
						bidVO.setBidState(2);
					}

//Integer KindNo = bidVO.setKindNo(kindNo);


					bidVO.setTransRecNo(null);
					bidVO.setBidWinnerPrice(bidWinnerPrice);
					bidVO.setBidWinnerNo(bidWinnerNo);
					if (flag == true) {
						bidSvc.updateBid(bidVO);
					} else {
						bidSvc.updateBid3(bidVO);
					}
					
				}
            }
        };
        
        TimerTask task2 = new TimerTask() {

        	// 未結帳之後30分鐘轉變狀態
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<BidVO> list = new ArrayList<>();
				BidService bidSvc = new BidService();
				
				Integer bidProdState = 0;
				
				list = bidSvc.findBidProdStateToFive(bidProdState);
				for (BidVO bidVO : list) {
					bidVO.setBidProdState(5);
					bidSvc.updateBid6(bidVO);
				}
			}
        	
        };
        
        timer.scheduleAtFixedRate(task, cal.getTime(), 1000); 
        timer.scheduleAtFixedRate(task2, cal.getTime(), 1000); 
    }
}

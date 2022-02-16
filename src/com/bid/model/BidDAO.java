package com.bid.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.bidpic.model.BidPicDAO;
import com.bidpic.model.BidPicJDBCDAO;
import com.bidpic.model.BidPicVO;

public class BidDAO implements BidDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO bid (kindNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidPriceIncrement,bidProdState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String INSERT_STMT = 
//			"INSERT INTO bid (kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT BidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid ORDER BY bidProdNo";
	private static final String GET_ONE_STMT = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE bidProdNo = ?";
	private static final String GET_ONE_STMT_KINDNO = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE kindNo = ?";
	private static final String GET_ONE_STMT_TRANSRECNO = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE transRecNo = ?";
	private static final String GET_ONE_STMT_BIDWINNERNO = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE bidWinnerNo = ?";
//	private static final String GET_ONE_STMT_BIDPRODNAME = 
//			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE bidProdName LIKE '%?%'";
	private static final String GET_ONE_STMT_BIDSTATE = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE bidState = ?";
	private static final String GET_ALL_STMT_BIDPRODSTATE = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE bidProdState = ?";
	private static final String DELETE = 
			"DELETE FROM bid WHERE bidProdNo = ?";
	private static final String UPDATE = 
			"UPDATE bid set kindNo=?,transRecNo=?,bidWinnerNo=?,bidProdName=?,bidProdDescription=?,bidProdStartPrice=?,bidState=?,bidProdStartTime=?,bidProdEndTime=?,bidWinnerPrice=?,bidPriceIncrement=?,receiverName=?,receiverAddress=?,receiverPhone=?,bidProdState=? WHERE bidProdNo = ?";
//	測試1.取出所有應更改競標狀態的商品 2.更改其競標狀態
	private static final String GET_ALL_STMT_BIDSTATE_NEED_CHANGE = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE BidState = 0 AND BidProdEndTime <= NOW()";
	private static final String UPDATE_BIDSTATE = 
			"UPDATE bid set bidState = ? WHERE bidProdNo = ?";
	
//	測試取得所有應更改競標商品狀態為5的商品
	private static final String GET_ALL_STMT_BIDPRODSTATE_NEED_CHANGE_TO_FIVE = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM bid WHERE BidState = 1 AND BidProdState = 0 AND DATE_ADD(BidProdEndTime, INTERVAL 30 MINUTE) <= NOW()";
	private static final String UPDATE_BIDPRODSTATETOFIVE = 
			"UPDATE bid set bidProdState = ? WHERE bidProdNo = ?";
	
	private static final String UPDATE_EXCEPT_FK = 
			"UPDATE bid set bidWinnerNo=?,bidProdName=?,bidProdDescription=?,bidProdStartPrice=?,bidState=?,bidProdStartTime=?,bidProdEndTime=?,bidWinnerPrice=?,bidPriceIncrement=?,receiverName=?,receiverAddress=?,receiverPhone=?,bidProdState=? WHERE bidProdNo = ?";
	private static final String UPDATE_EXCEPT_FK_AND_BIDWINNER = 
			"UPDATE bid set bidProdName=?,bidProdDescription=?,bidProdStartPrice=?,bidState=?,bidProdStartTime=?,bidProdEndTime=?,bidPriceIncrement=?,receiverName=?,receiverAddress=?,receiverPhone=?,bidProdState=? WHERE bidProdNo = ?";

//	測試結帳使用，更改收件資訊與商品狀態 用於update4
	private static final String UPDATE_RECEIVER_AND_PRODSTATE = 
			"UPDATE bid set receiverName=?,receiverAddress=?,receiverPhone=?,bidProdState=? WHERE bidProdNo = ?";
//	測試結帳後使用，更改錢包交易編號 用於update5
	private static final String UPDATE_TRANSRECNO = 
			"UPDATE bid set transRecNo = ? WHERE bidProdNo = ?";
	
//	測試前台查詢訂單使用
	private static final String GET_ALL_STMT_BIDWINNERNO_AND_BIDPRODSTATE = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM CFA101G1.bid WHERE bidWinnerNo = ? AND bidProdState = ?";
	
	// 測試搜尋
	private static final String GET_ALL_STMT_KEYWORD = 
			"SELECT bidProdNo,kindNo,transRecNo,bidWinnerNo,bidProdName,bidProdDescription,bidProdStartPrice,bidState,bidProdStartTime,bidProdEndTime,bidWinnerPrice,bidPriceIncrement,receiverName,receiverAddress,receiverPhone,bidProdState FROM CFA101G1.bid WHERE bidProdName LIKE ? OR bidProdDescription LIKE ?";
	
	// 測試update7
	private static final String UPDATE_PRODUCT = 
			"UPDATE bid set bidProdName = ?, kindNo = ?, bidProdDescription = ?, bidProdStartPrice = ?, bidPriceIncrement = ?, bidState = ?, bidProdStartTime = ?, bidProdEndTime = ?, receiverName = ?, receiverAddress = ?, receiverPhone = ?, bidProdState = ? WHERE bidProdNo = ?";
	
	
	@Override
	public void insert(BidVO bidVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, bidVO.getKindNo());

			pstmt.setString(2, bidVO.getBidProdName());
			pstmt.setString(3, bidVO.getBidProdDescription());

			pstmt.setInt(4, bidVO.getBidProdStartPrice());
			pstmt.setInt(5, bidVO.getBidState());
			pstmt.setTimestamp(6, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(7, bidVO.getBidProdEndTime());

			pstmt.setInt(8, bidVO.getBidPriceIncrement());

			pstmt.setInt(9, bidVO.getBidProdState());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(BidVO bidVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setInt(1, bidVO.getKindNo());
			pstmt.setInt(2, bidVO.getTransRecNo());
			pstmt.setInt(3, bidVO.getBidWinnerNo());
			pstmt.setString(4, bidVO.getBidProdName());
			pstmt.setString(5, bidVO.getBidProdDescription());
			pstmt.setInt(6, bidVO.getBidProdStartPrice());
			pstmt.setInt(7, bidVO.getBidState());
			pstmt.setTimestamp(8, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(9, bidVO.getBidProdEndTime());
			pstmt.setInt(10, bidVO.getBidWinnerPrice());
			pstmt.setInt(11, bidVO.getBidPriceIncrement());
			pstmt.setString(12, bidVO.getReceiverName());
			pstmt.setString(13, bidVO.getReceiverAddress());
			pstmt.setString(14, bidVO.getReceiverPhone());
			pstmt.setInt(15, bidVO.getBidProdState());
			pstmt.setInt(16, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void update2(BidVO bidVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EXCEPT_FK);
			
			
			pstmt.setInt(1, bidVO.getBidWinnerNo());
			pstmt.setString(2, bidVO.getBidProdName());
			pstmt.setString(3, bidVO.getBidProdDescription());
			pstmt.setInt(4, bidVO.getBidProdStartPrice());
			pstmt.setInt(5, bidVO.getBidState());
			pstmt.setTimestamp(6, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(7, bidVO.getBidProdEndTime());
			pstmt.setInt(8, bidVO.getBidWinnerPrice());
			pstmt.setInt(9, bidVO.getBidPriceIncrement());
			pstmt.setString(10, bidVO.getReceiverName());
			pstmt.setString(11, bidVO.getReceiverAddress());
			pstmt.setString(12, bidVO.getReceiverPhone());
			pstmt.setInt(13, bidVO.getBidProdState());
			pstmt.setInt(14, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	// 無出價紀錄者轉成流標
	@Override
	public void update3(BidVO bidVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EXCEPT_FK_AND_BIDWINNER);
			
			
			pstmt.setString(1, bidVO.getBidProdName());
			pstmt.setString(2, bidVO.getBidProdDescription());
			pstmt.setInt(3, bidVO.getBidProdStartPrice());
			pstmt.setInt(4, bidVO.getBidState());
			pstmt.setTimestamp(5, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(6, bidVO.getBidProdEndTime());
			pstmt.setInt(7, bidVO.getBidPriceIncrement());
			pstmt.setString(8, bidVO.getReceiverName());
			pstmt.setString(9, bidVO.getReceiverAddress());
			pstmt.setString(10, bidVO.getReceiverPhone());
			pstmt.setInt(11, bidVO.getBidProdState());
			pstmt.setInt(12, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public void delete(Integer bidProdNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, bidProdNo);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public BidVO findByPrimaryKey(Integer bidProdNo) {
		
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, bidProdNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bidVO;
	}
	
	@Override
	public BidVO findByKindNo(Integer kindNo) {
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_KINDNO);
			
			pstmt.setInt(1, kindNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bidVO;
	}

	@Override
	public BidVO findByTransRecNo(Integer transRecNo) {
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_TRANSRECNO);
			
			pstmt.setInt(1, transRecNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bidVO;
	}

	@Override
	public BidVO findByBidWinnerNo(Integer bidWinnerNo) {
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDWINNERNO);
			
			pstmt.setInt(1, bidWinnerNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bidVO;
	}

	@Override
	public BidVO findByBidState(Integer bidState) {
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDSTATE);
			
			pstmt.setInt(1, bidState);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bidVO;
	}

	@Override
	public List<BidVO> findByBidProdState(Integer bidProdState) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BidVO> list = new ArrayList<BidVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODSTATE);
			
			pstmt.setInt(1, bidProdState);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BidVO bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<BidVO> getAll() {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = new BidVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public void insertWithBidPics(BidVO bidVO, List<byte[]> list) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			 
			con = ds.getConnection();
			 
			con.setAutoCommit(false);
			 
			String cols[] = {"BidProdNo"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, bidVO.getKindNo());
			pstmt.setString(2, bidVO.getBidProdName());
			pstmt.setString(3, bidVO.getBidProdDescription());
			pstmt.setInt(4, bidVO.getBidProdStartPrice());
			pstmt.setInt(5, bidVO.getBidState());
			pstmt.setTimestamp(6, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(7, bidVO.getBidProdEndTime());
			pstmt.setInt(8, bidVO.getBidPriceIncrement());
			pstmt.setInt(9, bidVO.getBidProdState());
			
//			Statement stmt=	con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=51001;");    //自增主鍵-初始值
			
			pstmt.executeUpdate();
			
			String next_bidProdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_bidProdNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_bidProdNo +"(剛新增成功的競標商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//對照DeptJDBCDAO.java的399行
			BidPicJDBCDAO dao = new BidPicJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (int i = 0; i < list.size(); i++) {
				BidPicVO bidPicVO = new BidPicVO();
				bidPicVO.setBidProdNo(new Integer(next_bidProdNo));
				bidPicVO.setBidProdPicContent(list.get(i));
				dao.insert2(bidPicVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增競標商品編號" + next_bidProdNo + "時,共有圖片" + list.size()
					+ "張同時被新增");
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-bid");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateBidState(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BIDSTATE);
			
			pstmt.setInt(1, bidVO.getBidState());

			pstmt.setInt(2, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<BidVO> findBidStateToOne(Integer bidState) {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDSTATE_NEED_CHANGE);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<BidVO> findBidProdStateToFive(Integer bidProdState) {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODSTATE_NEED_CHANGE_TO_FIVE);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<BidVO> findAllByKindNo(Integer kindNo) {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_KINDNO);
			
			pstmt.setInt(1, kindNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void update4(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RECEIVER_AND_PRODSTATE);
			
			pstmt.setString(1, bidVO.getReceiverName());
			pstmt.setString(2, bidVO.getReceiverAddress());
			pstmt.setString(3, bidVO.getReceiverPhone());
			pstmt.setInt(4, bidVO.getBidProdState());
			pstmt.setInt(5, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update5(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_TRANSRECNO);
			
			pstmt.setInt(1, bidVO.getTransRecNo());
			pstmt.setInt(2, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update6(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BIDPRODSTATETOFIVE);
			
			pstmt.setInt(1, bidVO.getBidProdState());
			pstmt.setInt(2, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<BidVO> findByBidWinnerNoANDBidProdState(Integer bidWinnerNo, Integer bidProdState) {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDWINNERNO_AND_BIDPRODSTATE);
			
			pstmt.setInt(1, bidWinnerNo);
			pstmt.setInt(2, bidProdState);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<BidVO> findByKeyword(String bidProdName) {
		List<BidVO> list = new ArrayList<BidVO>();
		BidVO bidVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_KEYWORD);
			
			pstmt.setString(1, "%" + bidProdName + "%");
			pstmt.setString(2, "%" + bidProdName + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidVO = new BidVO();
				bidVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidVO.setKindNo(rs.getInt("kindNo"));
				bidVO.setTransRecNo(rs.getInt("transRecNo"));
				bidVO.setBidWinnerNo(rs.getInt("bidWinnerNo"));
				bidVO.setBidProdName(rs.getString("bidProdName"));
				bidVO.setBidProdDescription(rs.getString("bidProdDescription"));
				bidVO.setBidProdStartPrice(rs.getInt("bidProdStartPrice"));
				bidVO.setBidState(rs.getInt("bidState"));
				bidVO.setBidProdStartTime(rs.getTimestamp("bidProdStartTime"));
				bidVO.setBidProdEndTime(rs.getTimestamp("bidProdEndTime"));
				bidVO.setBidWinnerPrice(rs.getInt("bidWinnerPrice"));
				bidVO.setBidPriceIncrement(rs.getInt("bidPriceIncrement"));
				bidVO.setReceiverName(rs.getString("receiverName"));
				bidVO.setReceiverAddress(rs.getString("receiverAddress"));
				bidVO.setReceiverPhone(rs.getString("receiverPhone"));
				bidVO.setBidProdState(rs.getInt("bidProdState"));
				list.add(bidVO);
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void update7(BidVO bidVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PRODUCT);
			
			pstmt.setString(1, bidVO.getBidProdName());
			pstmt.setInt(2, bidVO.getKindNo());
			pstmt.setString(3, bidVO.getBidProdDescription());
			pstmt.setInt(4, bidVO.getBidProdStartPrice());
			pstmt.setInt(5, bidVO.getBidPriceIncrement());
			pstmt.setInt(6, bidVO.getBidState());
			pstmt.setTimestamp(7, bidVO.getBidProdStartTime());
			pstmt.setTimestamp(8, bidVO.getBidProdEndTime());
			pstmt.setString(9, bidVO.getReceiverName());
			pstmt.setString(10, bidVO.getReceiverAddress());
			pstmt.setString(11, bidVO.getReceiverPhone());
			pstmt.setInt(12, bidVO.getBidProdState());
			pstmt.setInt(13, bidVO.getBidProdNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
}

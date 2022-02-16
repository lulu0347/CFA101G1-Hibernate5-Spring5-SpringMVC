package com.bidrecord.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BidRecordJDBCDAO implements BidRecordDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA101G1?serverTimezone=Asia/Taipei";
	String userid = "cfa101g1";
	String passwd = "cfa101g1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO bidrecord (bidProdNo,memNo,bidPrice,bidTime) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT bidRecordNo,bidProdNo,memNo,bidPrice,bidTime FROM bidRecord ORDER BY bidRecordNo";
	private static final String GET_ONE_STMT = 
			"SELECT bidRecordNo,bidProdNo,memNo,bidPrice,bidTime FROM bidRecord WHERE bidRecordNo = ?";
	private static final String GET_ALL_STMT_BIDPRODNO = 
			"SELECT bidRecordNo,bidProdNo,memNo,bidPrice,bidTime FROM bidRecord WHERE bidProdNo = ?";
//	private static final String GET_ALL_STMT_BIDPRODNO = 
//			"SELECT bidRecordNo,bidProdNo,memNo,bidPrice,bidTime FROM bidRecord WHERE bidProdNo = ?";
	private static final String GET_ONE_STMT_BIDPRODNO_HIGHEST = 
			"SELECT bidRecordNo, bidProdNo, memNo, bidPrice, bidTime FROM bidRecord WHERE bidProdNo = ? ORDER BY BidPrice DESC LIMIT 1";
	private static final String GET_ONE_STMT_MEMNO = 
			"SELECT bidRecordNo,bidProdNo,memNo,bidPrice,bidTime FROM bidRecord WHERE memNo = ?";
	private static final String GET_MAX_PRICE_STMT_BIDPRODNO = 
			"SELECT bidRecordNo, bidProdNo, memNo, bidPrice, bidTime FROM bidRecord b1 WHERE bidPrice = (SELECT MAX(bidPrice) FROM bidRecord b2 WHERE bidProdNo = ?)";
	private static final String DELETE = 
			"DELETE FROM bidRecord WHERE bidRecordNo = ?";
	private static final String UPDATE = 
			"UPDATE bidRecord SET bidProdNo=?, memNo=?, bidPrice=?, bidTime=? WHERE bidRecordNo = ?";

	@Override
	public void insert(BidRecordVO bidRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, bidRecordVO.getBidProdNo());
			pstmt.setInt(2, bidRecordVO.getMemNo());
			pstmt.setInt(3, bidRecordVO.getBidPrice());
			pstmt.setTimestamp(4, bidRecordVO.getBidTime());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(BidRecordVO bidRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, bidRecordVO.getBidProdNo());
			pstmt.setInt(2, bidRecordVO.getMemNo());
			pstmt.setInt(3, bidRecordVO.getBidPrice());
			pstmt.setTimestamp(4, bidRecordVO.getBidTime());
			pstmt.setInt(5, bidRecordVO.getBidRecordNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer bidRecordNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bidRecordNo);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public BidRecordVO findByPrimaryKey(Integer bidRecordNo) {
		
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, bidRecordNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}
	
	@Override
	public List<BidRecordVO> findByBidProdNo(Integer bidProdNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BidRecordVO> list = new ArrayList<BidRecordVO>();
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BIDPRODNO);
			
			pstmt.setInt(1, bidProdNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
				list.add(bidRecordVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	// 取出商品編號=?的最高價紀錄
	@Override
	public BidRecordVO findByBidProdNoHighest(Integer bidProdNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDPRODNO_HIGHEST);
			
			pstmt.setInt(1, bidProdNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}

	@Override
	public BidRecordVO findByMemNo(Integer memNo) {
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_MEMNO);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}
	
	@Override
	public BidRecordVO findMaxBidPriceByBidProdNo(Integer bidProdNo) {
		// TODO Auto-generated method stub
		BidRecordVO bidRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MAX_PRICE_STMT_BIDPRODNO);
			
			pstmt.setInt(1, bidProdNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return bidRecordVO;
	}

	@Override
	public List<BidRecordVO> getAll() {
		List<BidRecordVO> list = new ArrayList<BidRecordVO>();
		BidRecordVO bidRecordVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bidRecordVO = new BidRecordVO();
				bidRecordVO.setBidRecordNo(rs.getInt("bidRecordNo"));
				bidRecordVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidRecordVO.setMemNo(rs.getInt("memNo"));
				bidRecordVO.setBidPrice(rs.getInt("bidPrice"));
				bidRecordVO.setBidTime(rs.getTimestamp("bidTime"));
				list.add(bidRecordVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

}

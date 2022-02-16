package com.bidpic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class BidPicDAO implements BidPicDAO_interface {
	
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
			"INSERT INTO bidpic (bidProdNo,BidProdPicContent) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT bidProdPicNo,bidProdNo,bidProdPicContent FROM bidpic ORDER BY bidProdPicNo";
	private static final String GET_ONE_STMT = 
			"SELECT bidProdPicNo,bidProdNo,bidProdPicContent FROM bidpic WHERE bidProdPicNo = ?";
	private static final String GET_ONE_STMT_BIDPRODNO = 
			"SELECT bidProdPicNo,bidProdNo,bidProdPicContent FROM bidpic WHERE bidProdNo = ?";
	private static final String GET_ONE_PIC_STMT_BIDPRODNO = 
			"SELECT bidProdPicNo,bidProdNo,bidProdPicContent FROM bidpic WHERE bidProdNo = ? LIMIT 1";
	private static final String DELETE = 
			"DELETE FROM bidpic WHERE bidProdPicNo = ?";
	private static final String UPDATE = 
			"UPDATE bidpic SET bidProdNo=?, bidProdPicContent=? WHERE bidProdPicNo = ?";

	@Override
	public void insert(BidPicVO bidPicVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, bidPicVO.getBidProdNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());
			
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
	public void update(BidPicVO bidPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, bidPicVO.getBidProdNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());
			pstmt.setInt(3, bidPicVO.getBidProdPicNo());

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
	public void delete(Integer bidProdPicNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bidProdPicNo);

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
	public BidPicVO findByPrimaryKey(Integer bidProdPicNo) {

		BidPicVO bidPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, bidProdPicNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("bidProdPicNo"));
				bidPicVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("bidProdPicContent"));
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
		return bidPicVO;
	}
	
	@Override
	public List<BidPicVO> findByBidProdNo(Integer bidProdNo) {
		List<BidPicVO> list = new ArrayList<BidPicVO>();
		BidPicVO bidPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BIDPRODNO);

			pstmt.setInt(1, bidProdNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("bidProdPicNo"));
				bidPicVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("bidProdPicContent"));
				list.add(bidPicVO);
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
	public List<BidPicVO> getAll() {
		List<BidPicVO> list = new ArrayList<BidPicVO>();
		BidPicVO bidPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("bidProdPicNo"));
				bidPicVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("bidProdPicContent"));
				list.add(bidPicVO);
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

//	以下對應EmpJDBCDAO.java的295行 for auto increment
	public void insert2(BidPicVO bidPicVO, Connection con) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, bidPicVO.getBidProdNo());
			pstmt.setBytes(2, bidPicVO.getBidProdPicContent());
			
			Statement stmt = con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=51001;");
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-bidpic");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}
	public static void main(String[] args) throws IOException {

		BidPicJDBCDAO dao = new BidPicJDBCDAO();

		// 新增
		BidPicVO bidPicVO1 = new BidPicVO();
		byte[] pic = getPictureByteArray("C:\\CFA101_WebApp\\eclipse_WTP_workspace1\\CFA101G1\\WebContent\\resources\\images\\idk.png");
		bidPicVO1.setBidProdNo(51001);
		bidPicVO1.setBidProdPicContent(pic);
		dao.insert(bidPicVO1);
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()]; // available()方法表示資料源頭的檔案大小
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	@Override
	public BidPicVO findFirstPicByBidProdNo(Integer bidProdNo) {
		BidPicVO bidPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PIC_STMT_BIDPRODNO);

			pstmt.setInt(1, bidProdNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				bidPicVO = new BidPicVO();
				bidPicVO.setBidProdPicNo(rs.getInt("bidProdPicNo"));
				bidPicVO.setBidProdNo(rs.getInt("bidProdNo"));
				bidPicVO.setBidProdPicContent(rs.getBytes("bidProdPicContent"));
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
		return bidPicVO;
	}
}

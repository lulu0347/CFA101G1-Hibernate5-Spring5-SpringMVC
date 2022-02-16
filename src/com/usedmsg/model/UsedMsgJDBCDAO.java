package com.usedmsg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UsedMsgJDBCDAO implements UsedMsgDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO UsedMsg (memNo, usedNo, usedMsgText) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE UsedMsg SET usedMsgNo = ?, memNo = ?, usedNo = ?, usedMsgText = ?";
	private static final String DELETE = "DELETE FROM UsedMsg where usedMsgNo = ?";
	private static final String GET_ALL_STMT = "SELECT usedMsgNo, memNo, usedNo, usedMsgText FROM UsedMsg order by usedMsgNo";
	private static final String GET_ALL_MSG_USEDNO = "SELECT usedMsgNo, memNo, usedNo, usedMsgText FROM UsedMsg where usedNo = ? order by usedMsgNo";
	private static final String GET_ONE_STMT = "SELECT usedMsgNo, memNo, usedNo, usedMsgText FROM UsedMsg WHERE usedMsgNo = ?";

	@Override
	public void insert(UsedMsgVO usedMsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, usedMsgVO.getMemNo());
			pstmt.setInt(2, usedMsgVO.getUsedNo());
			pstmt.setString(3, usedMsgVO.getUsedMsgText());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(UsedMsgVO usedMsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, usedMsgVO.getMemNo());
			pstmt.setInt(2, usedMsgVO.getUsedNo());
			pstmt.setString(3, usedMsgVO.getUsedMsgText());
			pstmt.setInt(4, usedMsgVO.getUsedMsgNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(Integer usedMsgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		UsedMsgVO usedMsgVO = new UsedMsgVO();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, usedMsgNo);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public UsedMsgVO findByPrimaryKey(Integer usedMsgNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsedMsgVO usedMsgVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, usedMsgNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usedMsgVO = new UsedMsgVO();
				usedMsgVO.setUsedMsgNo(rs.getInt("usedMsgNo"));
				usedMsgVO.setMemNo(rs.getInt("MemNo"));
				usedMsgVO.setUsedNo(rs.getInt("usedNo"));
				usedMsgVO.setUsedMsgText(rs.getString("usedMsgText"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}

		return usedMsgVO;
	}

	@Override
	public List<UsedMsgVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		UsedMsgVO usedMsgVO = null;
		ResultSet rs = null;
		List<UsedMsgVO> list = new ArrayList<UsedMsgVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usedMsgVO = new UsedMsgVO();
				usedMsgVO.setUsedMsgNo(rs.getInt("usedMsgNo"));
				usedMsgVO.setMemNo(rs.getInt("memNo"));
				usedMsgVO.setUsedNo(rs.getInt("usedNo"));
				usedMsgVO.setUsedMsgText(rs.getString("usedMsgText"));
				list.add(usedMsgVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<UsedMsgVO> getAllByUsedNo(Integer usedNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		UsedMsgVO usedMsgVO = null;
		ResultSet rs = null;
		List<UsedMsgVO> list = new ArrayList<UsedMsgVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MSG_USEDNO);
			pstmt.setInt(1, usedNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				usedMsgVO = new UsedMsgVO();
				usedMsgVO.setUsedMsgNo(rs.getInt("usedMsgNo"));
				usedMsgVO.setMemNo(rs.getInt("memNo"));
				usedMsgVO.setUsedNo(rs.getInt("usedNo"));
				usedMsgVO.setUsedMsgText(rs.getString("usedMsgText"));
				list.add(usedMsgVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		UsedMsgJDBCDAO dao = new UsedMsgJDBCDAO();

//		//新增
//		UsedMsgVO usedMsgVO1 = new UsedMsgVO();
//		usedMsgVO1.setMemNo(11003);
//		usedMsgVO1.setUsedNo(30001);
//		usedMsgVO1.setUsedMsgText("留言1");
//		dao.insert(usedMsgVO1);

//		//修改
//		UsedMsgVO usedMsgVO2 = new UsedMsgVO();
//		usedMsgVO2.setUsedMsgNo(2);
//		usedMsgVO2.setMemNo(200);
//		usedMsgVO2.setUsedNo(2000);
//		usedMsgVO2.setUsedMsgText("留言");
//		dao.update(usedMsgVO2);
		
		//刪除

//		dao.delete(1);
//
//		//查詢一筆
//		UsedMsgVO usedMsgVO3 = dao.findByPrimaryKey(1);
//		System.out.print(usedMsgVO3.getUsedMsgNo() + ",");
//		System.out.print(usedMsgVO3.getMemNo() + ",");
//		System.out.print(usedMsgVO3.getUsedNo() + ",");
//		System.out.print(usedMsgVO3.getUsedMsgText());
//		System.out.println("---------------");
//
//		//查詢全部
//		List<UsedMsgVO> list = dao.getAll();
//		for (UsedMsgVO umv : list) {
//			System.out.print(umv.getUsedMsgNo() + ",");
//			System.out.print(umv.getMemNo() + ",");
//			System.out.print(umv.getUsedNo() + ",");
//			System.out.print(umv.getUsedMsgText());
//			System.out.println("---------------");
//		}
		List<UsedMsgVO> list = dao.getAllByUsedNo(30001);
		for (UsedMsgVO umv : list) {
			System.out.print(umv.getUsedMsgNo() + ",");
			System.out.print(umv.getMemNo() + ",");
			System.out.print(umv.getUsedNo() + ",");
			System.out.print(umv.getUsedMsgText());
			System.out.println("---------------");
			}
	}
}

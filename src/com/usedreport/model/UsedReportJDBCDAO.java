package com.usedreport.model;

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

public class UsedReportJDBCDAO implements UsedReportDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO UsedReport (memNo, usedNo, reportedMemNo,usedReportTime, usedReportReason) VALUES (?, ?, ?, NOW(), ?)";
	private static final String UPDATE = "UPDATE UsedReport SET usedReportState = ?,usedReportNotice = ? WHERE memNo = ? AND usedNo = ?";
	private static final String GET_ALL_STMT = "SELECT memNo, usedNo, reportedMemNo, usedReportState, usedReportTime, usedReportReason, usedReportNotice FROM UsedReport order by usedReportTime desc";
	private static final String GET_ONE_STMT = "SELECT memNo, usedNo, reportedMemNo, usedReportState, usedReportTime, usedReportReason, usedReportNotice FROM UsedReport WHERE memNo = ? AND usedNo = ?";

	@Override
	public void insert(UsedReportVO usedReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, usedReportVO.getMemNo());
			pstmt.setInt(2, usedReportVO.getUsedNo());
			pstmt.setInt(3, usedReportVO.getReportedMemNo());
//			pstmt.setInt(4, usedReportVO.getUsedReportState());
//			pstmt.setDate(4, usedReportVO.getUsedReportTime());
			pstmt.setString(4, usedReportVO.getUsedReportReason());
//			pstmt.setString(7, usedReportVO.getUsedReportNotice());
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
	public void update(UsedReportVO usedReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, usedReportVO.getUsedReportState());
//			pstmt.setDate(2, usedReportVO.getUsedReportTime());
//			pstmt.setString(3, usedReportVO.getUsedReportReason());
			pstmt.setString(2, usedReportVO.getUsedReportNotice());
			pstmt.setInt(3, usedReportVO.getMemNo());
			pstmt.setInt(4, usedReportVO.getUsedNo());
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
	public UsedReportVO findByPrimaryKey(Integer memNo, Integer usedNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsedReportVO usedReportVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, usedNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usedReportVO = new UsedReportVO();
				usedReportVO.setMemNo(rs.getInt("memNo"));
				usedReportVO.setUsedNo(rs.getInt("UsedNo"));
				usedReportVO.setReportedMemNo(rs.getInt("reportedMemNo"));
				usedReportVO.setUsedReportState(rs.getInt("usedReportState"));
				usedReportVO.setUsedReportTime(rs.getDate("usedReportTime"));
				usedReportVO.setUsedReportReason(rs.getString("usedReportReason"));
				usedReportVO.setUsedReportNotice(rs.getString("usedReportNotice"));
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
		return usedReportVO;
	}

	@Override
	public List<UsedReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsedReportVO usedReportVO = null;
		List<UsedReportVO> list = new ArrayList<UsedReportVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				usedReportVO = new UsedReportVO();
				usedReportVO.setMemNo(rs.getInt("memNo"));
				usedReportVO.setUsedNo(rs.getInt("UsedNo"));
				usedReportVO.setReportedMemNo(rs.getInt("reportedMemNo"));
				usedReportVO.setUsedReportState(rs.getInt("usedReportState"));
				usedReportVO.setUsedReportTime(rs.getDate("usedReportTime"));
				usedReportVO.setUsedReportReason(rs.getString("usedReportReason"));
				usedReportVO.setUsedReportNotice(rs.getString("usedReportNotice"));
				list.add(usedReportVO);
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

		UsedReportJDBCDAO dao = new UsedReportJDBCDAO();
		
		//新增
//		UsedReportVO usedReportVO1 = new UsedReportVO();
//		usedReportVO1.setMemNo(1);
//		usedReportVO1.setUsedNo(1);
//		usedReportVO1.setReportedMemNo(2);
//		usedReportVO1.setUsedReportState(1);
//		usedReportVO1.setUsedReportTime(java.sql.Date.valueOf("2020-01-01"));
//		usedReportVO1.setUsedReportReason("Reason");
//		usedReportVO1.setUsedReportNotice("通知");
//		dao.insert(usedReportVO1);
		//修改
//		UsedReportVO usedReportVO2 = new UsedReportVO();
//		usedReportVO2.setMemNo(1);
//		usedReportVO2.setUsedNo(1);
//		usedReportVO2.setUsedReportState(3);
//		usedReportVO2.setUsedReportTime(java.sql.Date.valueOf("2020-01-01"));
//		usedReportVO2.setUsedReportReason("Reason2");
//		usedReportVO2.setUsedReportNotice("通知2");
//		dao.update(usedReportVO2);
		//查詢一筆
//		UsedReportVO usedReportVO3 = dao.findByPrimaryKey(1, 1);
//		System.out.print(usedReportVO3.getMemNo() + ",");
//		System.out.print(usedReportVO3.getUsedNo() + ",");
//		System.out.print(usedReportVO3.getReportedMemNo() + ",");
//		System.out.print(usedReportVO3.getUsedReportState() + ",");
//		System.out.print(usedReportVO3.getUsedReportTime() + ",");
//		System.out.print(usedReportVO3.getUsedReportReason() + ",");
//		System.out.println(usedReportVO3.getUsedReportNotice());
//		System.out.println("---------------------");
		//查詢全部
		List<UsedReportVO> list = dao.getAll();
		for (UsedReportVO urv : list) {
			System.out.print(urv.getMemNo() + ",");
			System.out.print(urv.getUsedNo() + ",");
			System.out.print(urv.getReportedMemNo() + ",");
			System.out.print(urv.getUsedReportState() + ",");
			System.out.print(urv.getUsedReportTime() + ",");
			System.out.print(urv.getUsedReportReason() + ",");
			System.out.print(urv.getUsedReportNotice());
			System.out.println();
		}
	}
}

package com.admini.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AdminiDAO implements AdminiDAO_interface{
	
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
			"INSERT INTO admini (adminAccount,adminPassword,adminName,adminPhone) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adminNo,adminAccount,adminPassword,adminName,adminPhone FROM admini order by adminNo";
	private static final String GET_ONE_STMT = 
			"SELECT adminNo,adminAccount,adminPassword,adminName,adminPhone FROM admini where adminNo = ?";
	private static final String GET_ONE_ACCOUNT = 
			"SELECT adminNo,adminAccount,adminPassword,adminName,adminPhone FROM admini where adminAccount = ?";
	private static final String DELETE = 
			"DELETE FROM admini where adminNo = ?";
	private static final String UPDATE = 
			"UPDATE admini set adminAccount=? ,adminPassword=? ,adminName=? , adminPhone=? where adminNo = ?";
	@Override
	public void insert(AdminiVO adminiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminiVO.getAdminAccount());
			pstmt.setString(2, adminiVO.getAdminPassword());
			pstmt.setString(3, adminiVO.getAdminName());
			pstmt.setString(4, adminiVO.getAdminPhone());


			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(AdminiVO adminiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adminiVO.getAdminAccount());
			pstmt.setString(2, adminiVO.getAdminPassword());
			pstmt.setString(3, adminiVO.getAdminName());
			pstmt.setString(4, adminiVO.getAdminPhone());
			pstmt.setInt(5, adminiVO.getAdminNo());


			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer adminNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public AdminiVO findByPrimaryKey(Integer adminNo) {
		AdminiVO adminiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminiVO = new AdminiVO();
				adminiVO.setAdminNo(rs.getInt("adminNo"));
				adminiVO.setAdminAccount(rs.getString("adminAccount"));
				adminiVO.setAdminPassword(rs.getString("adminPassword"));
				adminiVO.setAdminName(rs.getString("adminName"));
				adminiVO.setAdminPhone(rs.getString("adminPhone"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return adminiVO;
	}
	
	@Override
	public AdminiVO findByAdminAccount(String adminAccount) {
		AdminiVO adminiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, adminAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminiVO = new AdminiVO();
				adminiVO.setAdminNo(rs.getInt("adminNo"));
				adminiVO.setAdminAccount(rs.getString("adminAccount"));
				adminiVO.setAdminPassword(rs.getString("adminPassword"));
				adminiVO.setAdminName(rs.getString("adminName"));
				adminiVO.setAdminPhone(rs.getString("adminPhone"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return adminiVO;
	}
	
	@Override
	public List<AdminiVO> getAll() {
		List<AdminiVO> list = new ArrayList<AdminiVO>();
		AdminiVO adminiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminiVO = new AdminiVO();
				adminiVO.setAdminNo(rs.getInt("adminNo"));
				adminiVO.setAdminAccount(rs.getString("adminAccount"));
				adminiVO.setAdminPassword(rs.getString("adminPassword"));
				adminiVO.setAdminName(rs.getString("adminName"));
				adminiVO.setAdminPhone(rs.getString("adminPhone"));
				list.add(adminiVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
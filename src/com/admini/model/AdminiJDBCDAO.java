package com.admini.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminiJDBCDAO implements AdminiDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/David?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO admini (adminiAccount,adminiPassword,adminiName,adminiPhone) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adminiNo,adminiAccount,adminiPassword,adminiName,adminiPhone FROM admini order by adminNo";
	private static final String GET_ONE_STMT = 
			"SELECT adminiNo,adminiAccount,adminiPassword,adminiName,adminiPhone FROM admini where adminNo = ?";
	private static final String DELETE = 
			"DELETE FROM admini where adminNo = ?";
	private static final String UPDATE = 
			"UPDATE admini set adminiAccount=? ,adminiPassword=? ,adminiName=? , adminiPhone=? where adminNo = ?";
	private static final String GET_ONE_ACCOUNT = 
			"SELECT adminNo,adminAccount,adminPassword,adminName,adminPhone FROM admini where adminAccount = ?";

	
	
	@Override
	public void insert(AdminiVO adminiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminiVO.getAdminAccount());
			pstmt.setString(2, adminiVO.getAdminPassword());
			pstmt.setString(3, adminiVO.getAdminName());
			pstmt.setString(4, adminiVO.getAdminPhone());


			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminNo);

			pstmt.executeUpdate();

			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
	public AdminiVO findByPrimaryKey(Integer adminNo) {
		AdminiVO adminiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			}catch (SQLException se) {
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
	@Override
	public AdminiVO findByAdminAccount(String adminAccount) {
		AdminiVO adminiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
}

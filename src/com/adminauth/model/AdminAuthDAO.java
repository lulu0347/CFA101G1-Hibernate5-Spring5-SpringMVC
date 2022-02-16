package com.adminauth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.adminauthrization.model.AdminAuthrizationVO;
import com.admini.model.AdminiVO;


public class AdminAuthDAO implements AdminAuthDAO_interface {

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
			"INSERT INTO AdminAuth (AdminNo,AdminAuthrizationNo) VALUES ( ?,?)";
	private static final String DELETE = 
			"DELETE FROM AdminAuth where AdminNo = ? and AdminAuthrizationNo = ?";
	private static final String UPDATE = 
			"UPDATE AdminAuth set AdminAuthrizationNo = ? where AdminNo = ? ";
	private static final String GET_ALL_STMT = "SELECT AdminNo,AdminAuthrizationNo FROM AdminAuth order by AdminNo";
	private static final String GET_ADMINONE_STMT = "SELECT AdminNo,AdminAuthrizationNo FROM AdminAuth where AdminNo = ?";	
	private static final String GET_AUTHONE_STMT = "SELECT AdminNo,AdminAuthrizationNo FROM AdminAuth where AdminAuthrizationNo = ?";	


	@Override
	public void insert(AdminAuthVO adminAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, adminAuthVO.getAdminNo());
			pstmt.setInt(2, adminAuthVO.getAdminAuthrizationNo());
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
	public void update(AdminAuthVO adminAuthVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, adminAuthVO.getAdminAuthrizationNo());
			pstmt.setInt(2, adminAuthVO.getAdminNo());

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
	public void delete(Integer adminNo,Integer adminiAuthrizationNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminNo);
			pstmt.setInt(2, adminiAuthrizationNo);

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
	public List<AdminAuthVO> findByAdminNo(Integer adminNo) {
		AdminAuthVO adminAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdminAuthVO> list = new LinkedList<AdminAuthVO>();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ADMINONE_STMT);
			System.out.println(adminNo);
			pstmt.setInt(1, adminNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adminAuthVO = new AdminAuthVO();
				adminAuthVO.setAdminNo(rs.getInt("adminNo"));
				adminAuthVO.setAdminAuthrizationNo(rs.getInt("adminAuthrizationNo"));
				list.add(adminAuthVO);
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
	
	@Override
	public List<AdminAuthVO> findByAuthNo(Integer adminAuthrizationNo) {
		AdminAuthVO adminAuthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdminAuthVO> list = new LinkedList<AdminAuthVO>();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTHONE_STMT);

			pstmt.setInt(1, adminAuthrizationNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				adminAuthVO = new AdminAuthVO();
				adminAuthVO.setAdminNo(rs.getInt("adminNo"));
				adminAuthVO.setAdminAuthrizationNo(rs.getInt("adminAuthrizationNo"));
				list.add(adminAuthVO);
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
	
	@Override
	public List<AdminAuthVO> getAll() {
		List<AdminAuthVO> list = new ArrayList<AdminAuthVO>();
		AdminAuthVO adminAuthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminAuthVO = new AdminAuthVO();
				adminAuthVO.setAdminNo(rs.getInt("AdminNo"));
				adminAuthVO.setAdminAuthrizationNo(rs.getInt("AdminAuthrizationNo"));
				list.add(adminAuthVO); // Store the row in the list
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
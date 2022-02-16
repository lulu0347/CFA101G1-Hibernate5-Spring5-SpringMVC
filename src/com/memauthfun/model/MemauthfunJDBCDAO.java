package com.memauthfun.model;

import java.util.*;
import java.sql.*;

public class MemauthfunJDBCDAO implements MemauthfunDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO memauthfun (MemauthfunNo, AuthFunName) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM memauthfun order by memauthfunno";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM memauthfun where memauthfunno = ?";
		private static final String DELETE = 
			"DELETE FROM memauthfun where memauthfunno = ?";
		private static final String UPDATE = 
			"UPDATE memauthfun set MemauthfunNo=?, AuthFunName=?) where Memauthfunno = ?";
		
	@Override
	public void insert(MemauthfunVO memauthfunVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,memauthfunVO.getMemauthfunno());
			pstmt.setString(2,memauthfunVO.getAuthfunname());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(MemauthfunVO memauthfunVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,memauthfunVO.getMemauthfunno());
			pstmt.setString(2,memauthfunVO.getAuthfunname());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer memauthfunno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memauthfunno);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MemauthfunVO findByPrimaryKey(Integer memauthfunno) {

		MemauthfunVO memauthfunVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memauthfunno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memauthfunVO = new MemauthfunVO();
				memauthfunVO.setMemauthfunno(rs.getInt("memauthfunno"));
				memauthfunVO.setAuthfunname(rs.getString("authfunname"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
		return memauthfunVO;
	}

	@Override
	public List<MemauthfunVO> getAll() {
		List<MemauthfunVO> list = new ArrayList<MemauthfunVO>();
		MemauthfunVO memauthfunVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memauthfunVO = new MemauthfunVO();
				memauthfunVO.setMemauthfunno(rs.getInt("memauthfunno"));
				memauthfunVO.setAuthfunname(rs.getString("authfunname"));

				list.add(memauthfunVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
		return list;
	}

	public static void main(String[] args) {

		MemauthfunJDBCDAO dao = new MemauthfunJDBCDAO();

		// 新增
		MemauthfunVO memauthfunVO1 = new MemauthfunVO();
		
		memauthfunVO1.setAuthfunname("聊天");
		dao.insert(memauthfunVO1);


		// 修改
		MemauthfunVO memauthfunVO2 = new MemauthfunVO();
		memauthfunVO1.setMemauthfunno(14001);
		memauthfunVO1.setAuthfunname("聊天");

		dao.update(memauthfunVO2);

		// 刪除
		dao.delete(14001);

		// 查詢
		MemauthfunVO memauthfunVO3 = dao.findByPrimaryKey(14001);
		System.out.print(memauthfunVO3.getMemauthfunno() + ",");
		System.out.print(memauthfunVO3.getAuthfunname());
		System.out.println("---------------------");

		// 查詢
		List<MemauthfunVO> list = dao.getAll();
		for (MemauthfunVO aMemauth : list) {
			System.out.print(aMemauth.getMemauthfunno() + ",");
			System.out.print(aMemauth.getAuthfunname());
			System.out.println();
		}
	}
}
package com.memauthrization.model;

import java.util.*;
import java.sql.*;

public class MemauthrizationJDBCDAO implements MemauthrizationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO memauthrization (MemNo, Memauthfunno) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM memauthrization order by memno";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM memauthrization where memno = ?";
		private static final String DELETE = 
			"DELETE FROM memauthrization where memauthfunno = ?";
		private static final String UPDATE = 
			"UPDATE memauthrization set memno=?, memauthfunno=?) where memno = ?";
		
	@Override
	public void insert(MemauthrizationVO memauthrizationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,memauthrizationVO.getMemno());
			pstmt.setInt(2,memauthrizationVO.getMemauthfunno());

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
	public void update(MemauthrizationVO memauthrizationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,memauthrizationVO.getMemno());
			pstmt.setInt(2,memauthrizationVO.getMemauthfunno());

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
	public MemauthrizationVO findByPrimaryKey(Integer memno) {

		MemauthrizationVO memauthrizationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memauthrizationVO = new MemauthrizationVO();
				memauthrizationVO.setMemno(rs.getInt("memno"));
				memauthrizationVO.setMemauthfunno(rs.getInt("memauthfunno"));
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
		return memauthrizationVO;
	}

	@Override
	public List<MemauthrizationVO> getAll() {
		List<MemauthrizationVO> list = new ArrayList<MemauthrizationVO>();
		MemauthrizationVO memauthrizationVO = null;

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
				memauthrizationVO = new MemauthrizationVO();
				memauthrizationVO.setMemno(rs.getInt("memno"));
				memauthrizationVO.setMemauthfunno(rs.getInt("memauthfunno"));
				list.add(memauthrizationVO); // Store the row in the list
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

		MemauthrizationJDBCDAO dao = new MemauthrizationJDBCDAO();

		// 新增
		MemauthrizationVO memauthrizationVO1 = new MemauthrizationVO();
		
		memauthrizationVO1.setMemno(11001);
		memauthrizationVO1.setMemauthfunno(13001);
		dao.insert(memauthrizationVO1);


		// 修改
		MemauthrizationVO memauthrizationVO2 = new MemauthrizationVO();
		
		memauthrizationVO2.setMemno(11001);
		memauthrizationVO2.setMemauthfunno(13001);
		dao.update(memauthrizationVO2);

		// 刪除
		dao.delete(13001);

		// 查詢
		MemauthrizationVO memauthrizationVO3 = dao.findByPrimaryKey(11001);
		System.out.print(memauthrizationVO3.getMemno() + ",");
		System.out.print(memauthrizationVO3.getMemauthfunno());
		System.out.println("---------------------");

		// 查詢
		List<MemauthrizationVO> list = dao.getAll();
		for (MemauthrizationVO aMemauthiz : list) {
			System.out.print(aMemauthiz.getMemno() + ",");
			System.out.print(aMemauthiz.getMemauthfunno());
			System.out.println();
		}
	}
}
package com.notification.model;

import java.util.*;
import java.sql.*;

public class NotificationJDBCDAO implements NotificationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO notification (memno, activitiesnotify) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM notification order by notifyno";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM notification where notifyno = ?";
		private static final String DELETE = 
			"DELETE FROM notification where notifyno = ?";
		private static final String UPDATE = 
			"UPDATE notification set notifyno=?, momno=?, activitiesnotify=?) where notifyno = ?";
		
	@Override
	public void insert(NotificationVO notifyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,notifyVO.getMemno());
			pstmt.setString(2,notifyVO.getActivitiesnotify());

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
	public void update(NotificationVO notifyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,notifyVO.getNotifyno());
			pstmt.setInt(2,notifyVO.getMemno());
			pstmt.setString(3,notifyVO.getActivitiesnotify());

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
	public void delete(Integer notifyno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notifyno);

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
	public NotificationVO findByPrimaryKey(Integer notifyno) {

		NotificationVO notifyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notifyno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				notifyVO = new NotificationVO();
				notifyVO.setNotifyno(rs.getInt("notifyno"));
				notifyVO.setMemno(rs.getInt("memno"));
				notifyVO.setActivitiesnotify(rs.getString("activitiesnotify"));
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
		return notifyVO;
	}

	@Override
	public List<NotificationVO> getAll() {
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notifyVO = null;

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
				notifyVO = new NotificationVO();
				notifyVO.setNotifyno(rs.getInt("notifyno"));
				notifyVO.setMemno(rs.getInt("memno"));
				notifyVO.setActivitiesnotify(rs.getString("activitiesnotify"));
				list.add(notifyVO); // Store the row in the list
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

		NotificationJDBCDAO dao = new NotificationJDBCDAO();

		// 新增
		NotificationVO notifyVO1 = new NotificationVO();
		
		notifyVO1.setMemno(11001);
		notifyVO1.setActivitiesnotify("你好嗎");
		dao.insert(notifyVO1);


		// 修改
		NotificationVO notifyVO2 = new NotificationVO();
		notifyVO2.setNotifyno(17001);
		notifyVO2.setMemno(11001);
		notifyVO2.setActivitiesnotify("你好嗎");
		dao.update(notifyVO2);

		// 刪除
		dao.delete(17001);

		// 查詢
		NotificationVO notifyVO3 = dao.findByPrimaryKey(17001);
		System.out.print(notifyVO3.getNotifyno() + ",");
		System.out.print(notifyVO3.getMemno() + ",");
		System.out.print(notifyVO3.getActivitiesnotify());
		System.out.println("---------------------");

		// 查詢
		List<NotificationVO> list = dao.getAll();
		for (NotificationVO aNotify : list) {
			System.out.print(aNotify.getNotifyno() + ",");
			System.out.print(aNotify.getMemno() + ",");
			System.out.println(aNotify.getActivitiesnotify());
			System.out.println();
		}
	}
}
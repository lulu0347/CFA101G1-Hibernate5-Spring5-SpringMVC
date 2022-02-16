package com.usedpic.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Blob;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class UsedPicJDBCDAO implements UsedPicDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO UsedPic (usedNo, usedPic) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE UsedPic SET usedNo = ?, usedPic = ? WHERE usedPicNo = ?";
	private static final String GET_ONE_STMT = "SELECT usedPicNo, usedNo, usedPic FROM UsedPic WHERE usedPicNo = ?";
	private static final String GET_ONE_USED_PICS = "SELECT usedPicNo, usedNo, usedPic FROM UsedPic WHERE usedNo = ?";
	private static final String GET_ALL_STMT = "SELECT usedPicNo, usedNo, usedPic FROM UsedPic order by usedPicNo";
	
	@Override
	public void insert(UsedPicVO usedPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;


		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, usedPicVO.getUsedNo());			
	        pstmt.setBytes(2, usedPicVO.getUsedPic());
	        pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
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
	public void insert2(UsedPicVO usedPicVO, Connection con) {

		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setInt(1, usedPicVO.getUsedNo());			
			pstmt.setBytes(2, usedPicVO.getUsedPic());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pic");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured." + se.getMessage());
		}finally {
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
	public void update(UsedPicVO usedPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, usedPicVO.getUsedNo());			
	        pstmt.setBytes(2, usedPicVO.getUsedPic());
	    	pstmt.setInt(3, usedPicVO.getUsedPicNo());
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
	public UsedPicVO findByPrimaryKey(Integer usedPicNo) {
		UsedPicVO usedPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, usedPicNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usedPicVO = new UsedPicVO();
				usedPicVO.setUsedPicNo(rs.getInt("usedPicNo"));
				usedPicVO.setUsedNo(rs.getInt("usedNo"));
				usedPicVO.setUsedPic(rs.getBytes("usedPic"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return usedPicVO;
	}
	
	@Override
	public List<UsedPicVO> findByUsedNo(Integer usedNo) {
		UsedPicVO usedPicVO = null;
		List<UsedPicVO> list = new ArrayList<UsedPicVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_USED_PICS);
			
			pstmt.setInt(1, usedNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				usedPicVO = new UsedPicVO();
				usedPicVO.setUsedPicNo(rs.getInt("usedPicNo"));
				usedPicVO.setUsedNo(rs.getInt("usedNo"));
				usedPicVO.setUsedPic(rs.getBytes("usedPic"));
				list.add(usedPicVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public List<UsedPicVO> getAll() {
		UsedPicVO usedPicVO = null;
		List<UsedPicVO> list = new ArrayList<UsedPicVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				usedPicVO = new UsedPicVO();
//				usedPicVO.setUsedPicNo(rs.getInt("usedPicNo"));
//				usedPicVO.setUsedNo(rs.getInt("usedNo"));
//				usedPicVO.setUsedPic(rs.getBlob("usedPic"));
//                BufferedReader br = new BufferedReader(new InputStreamReader(usedPicVO.getUsedPic().getBinaryStream()));
//                String str=null;
//                try {
//					while((str=br.readLine())!=null){
//					    System.out.println(str);
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				list.add(usedPicVO);
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
		return list;
	}
	
	public static void main(String[] args) {
		
		UsedPicJDBCDAO dao = new UsedPicJDBCDAO();
		
//		//新增
//		UsedPicVO usedPicVO1 = new UsedPicVO();
//		String path = "search.PNG";
//		usedPicVO1.setUsedNo(2);
//		usedPicVO1.setUsedPic();
//		
//		dao.insert(usedPicVO1);
//		
//		//修改
//		UsedPicVO usedPicVO2 = new UsedPicVO();
//		usedPicVO2.setUsedPicNo(3);
//		usedPicVO2.setUsedNo(2);
//		String path = "cart.PNG";
//		dao.update(usedPicVO2, path);
//		
//		//查詢一筆
//		UsedPicVO usedPicVO3 = dao.findByPrimaryKey(5);
//		System.out.print(usedPicVO3.getUsedPicNo() + ",");
//		System.out.print(usedPicVO3.getUsedNo() + ",");
//		System.out.print(usedPicVO3.getUsedPic());
//		System.out.println("---------------------------");
//		
//		List<UsedPicVO> list = dao.findByUsedNo(30001);
//		for(UsedPicVO upc:list) {
//		System.out.print(upc.getUsedPicNo() + ",");
//		System.out.print(upc.getUsedNo() + ",");
//		System.out.print(upc.getUsedPic());
//		System.out.println("---------------------------");
//		}
//		
//		//查詢全部
//		List<UsedPicVO2> list = dao.getAll();
//		for(UsedPicVO2 upc : list) {
//			System.out.print(upc.getUsedPicNo() + ",");
//			System.out.print(upc.getUsedNo() + ",");
//			System.out.print(upc.getUsedPic());
//			System.out.println();
//		}
		
	}
}

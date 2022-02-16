package com.repairform.model;

import java.util.*;
import java.sql.*;
//import java.sql.Date;

public class RepairFormJDBCDAO implements RepairFormDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA101G1?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = 
	    "INSERT INTO RepairForm (memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend) VALUES (?, ?, ?, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT repairformno,memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend FROM RepairForm order by repairformno";
	private static final String GET_ONE_STMT = 
		"SELECT repairformno,memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend FROM RepairForm where repairformno = ?";
	private static final String DELETE = 
		"DELETE FROM repairform where repairformno = ?";
	private static final String UPDATE = 
		"UPDATE RepairForm set memno=?, orderno=?, itemno=?, createtime=?, repairformstatus=?, adminno=?, repairinfo=?, repairend=? where repairformno = ?";

//	@Override
	public void insert(RepairFormVO repairformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setInt(1, repairformVO.getRepairFormNo());
			pstmt.setInt(1, repairformVO.getMemno());
			pstmt.setInt(2, repairformVO.getOrderno());
			pstmt.setInt(3, repairformVO.getItemno());
			pstmt.setDate(4, repairformVO.getCreatetime());
			pstmt.setString(5, repairformVO.getRepairformstatus());
			pstmt.setInt(6, repairformVO.getAdminno());
			pstmt.setString(7, repairformVO.getRepairinfo());
			pstmt.setDate(8, repairformVO.getRepairend());
			
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

//	@Override
	public void update(RepairFormVO repairformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, repairformVO.getMemno());
			pstmt.setInt(2, repairformVO.getOrderno());
			pstmt.setInt(3, repairformVO.getItemno());
			pstmt.setDate(4, repairformVO.getCreatetime());
			pstmt.setString(5, repairformVO.getRepairformstatus());
			pstmt.setInt(6, repairformVO.getAdminno());
			pstmt.setString(7, repairformVO.getRepairinfo());
			pstmt.setDate(8, repairformVO.getRepairend());
			pstmt.setInt(9, repairformVO.getRepairformno());
			
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

////	@Override
	public void delete(Integer repairformno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, repairformno);

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

//	//@Override
	public RepairFormVO findByPrimaryKey(Integer repairformno) {

		RepairFormVO repairformVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, repairformno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
		
				repairformVO = new RepairFormVO();                               
				repairformVO.setRepairformno(rs.getInt("repairformno")); //1               
				repairformVO.setMemno(rs.getInt("memno"));               //2             
				repairformVO.setOrderno(rs.getInt("orderno"));           //3      
				repairformVO.setItemno(rs.getInt("itemno"));             //4          
				repairformVO.setCreatetime(rs.getDate("createtime"));    //5                    
				repairformVO.setRepairformstatus(rs.getString("repairformstatus")); //6         
				repairformVO.setAdminno(rs.getInt("adminno"));           //7             
				repairformVO.setRepairinfo(rs.getString("repairinfo"));  //8       
				repairformVO.setRepairend(rs.getDate("repairend"));      //9
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
		return repairformVO;
	}

	//@Override
	public List<RepairFormVO> getAll() {
		List<RepairFormVO> list = new ArrayList<RepairFormVO>();
		RepairFormVO repairformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				repairformVO = new RepairFormVO();
				repairformVO.setRepairformno(rs.getInt("repairformno")); //1               
				repairformVO.setMemno(rs.getInt("memno"));               //2             
				repairformVO.setOrderno(rs.getInt("orderno"));           //3      
				repairformVO.setItemno(rs.getInt("itemno"));             //4          
				repairformVO.setCreatetime(rs.getDate("createtime"));    //5                    
				repairformVO.setRepairformstatus(rs.getString("repairformstatus")); //6         
				repairformVO.setAdminno(rs.getInt("adminno"));           //7             
				repairformVO.setRepairinfo(rs.getString("repairinfo"));  //8       
				repairformVO.setRepairend(rs.getDate("repairend"));      //9
				list.add(repairformVO); // Store the row in the list
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

		RepairFormJDBCDAO dao = new RepairFormJDBCDAO();	
		
		// 新增
		RepairFormVO repairformVO1 = new RepairFormVO();
		
		repairformVO1.setMemno(11001);
		repairformVO1.setOrderno(22001);
		repairformVO1.setItemno(1111);
		repairformVO1.setCreatetime(java.sql.Date.valueOf("2020-09-27"));
		repairformVO1.setRepairformstatus("維修中");
		repairformVO1.setAdminno(11002);
		repairformVO1.setRepairinfo("無法開機");
		repairformVO1.setRepairend(java.sql.Date.valueOf("2020-09-27"));
		dao.insert(repairformVO1);

		// 修改
		RepairFormVO repairformVO2 = new RepairFormVO();
		repairformVO2.setRepairformno(1002);
		repairformVO2.setMemno(11001);
		repairformVO2.setOrderno(22001);
		repairformVO2.setItemno(1111);
		repairformVO2.setCreatetime(java.sql.Date.valueOf("2020-09-27"));
		repairformVO2.setRepairformstatus("測試維修狀態");
		repairformVO2.setAdminno(11002);
		repairformVO2.setRepairinfo("修改無法開機");
		repairformVO2.setRepairend(java.sql.Date.valueOf("2020-09-27"));
		
		dao.update(repairformVO2);

//		//刪除
		dao.delete(1008);

		
		//查詢
		RepairFormVO repairformVO3 = dao.findByPrimaryKey(1001);
		System.out.print(repairformVO3.getRepairformno() + ",");
		System.out.print(repairformVO3.getMemno() + ",");
		System.out.print(repairformVO3.getOrderno() + ",");
		System.out.print(repairformVO3.getItemno() + ",");
		System.out.print(repairformVO3.getCreatetime() + ",");
		System.out.print(repairformVO3.getRepairformstatus() + ",");
		System.out.print(repairformVO3.getAdminno() + ",");
		System.out.print(repairformVO3.getRepairinfo() + ",");
		System.out.println(repairformVO3.getRepairend() + ",");

		System.out.println("-------------------------------------------------------------------------------------");

		// 查詢
		List<RepairFormVO> list = dao.getAll();
		for (RepairFormVO aRepairForm : list) {
			System.out.print(aRepairForm.getRepairformno() + ",");
			System.out.print(aRepairForm.getMemno() + ",");
			System.out.print(aRepairForm.getOrderno() + ",");
			System.out.print(aRepairForm.getItemno() + ",");
			System.out.print(aRepairForm.getCreatetime() + ",");
			System.out.print(aRepairForm.getRepairformstatus() + ",");
			System.out.print(aRepairForm.getAdminno() + ",");
			System.out.print(aRepairForm.getRepairinfo() + ",");
			System.out.print(aRepairForm.getRepairend() + ",");
			System.out.println();
		}
	}
}
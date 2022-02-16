package com.repairform.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RepairFormDAO implements RepairFormDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		    "INSERT INTO RepairForm (memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend) VALUES (?, ?, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT repairformno,memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend FROM RepairForm order by repairformno";
		private static final String GET_ONE_STMT = 
			"SELECT repairformno,memno,orderno,itemno,createtime,repairformstatus,adminno,repairinfo,repairend FROM RepairForm where repairformno = ?";
		private static final String DELETE = 
			"DELETE FROM repairform where repairformno = ?";
		private static final String UPDATE = 
			"UPDATE RepairForm set memno=?, orderno=?, itemno=?, createtime=?, repairformstatus=?, adminno=?, repairinfo=?, repairend=? where repairformno = ?";

	@Override
	public void insert(RepairFormVO repairformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, repairformVO.getMemno());
			pstmt.setInt(2, repairformVO.getOrderno());
			pstmt.setInt(3, repairformVO.getItemno());
			pstmt.setDate(4, repairformVO.getCreatetime());
			pstmt.setString(5, repairformVO.getRepairformstatus());
			pstmt.setInt(6, repairformVO.getAdminno());
			pstmt.setString(7, repairformVO.getRepairinfo());
			pstmt.setDate(8, repairformVO.getRepairend());
			
			pstmt.executeUpdate();

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
			//這裡的try catch很重要 一定要複製至程式中
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
	public void update(RepairFormVO repairformVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public void delete(Integer repairformno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, repairformno);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public RepairFormVO findByPrimaryKey(Integer repairformno) {

		RepairFormVO repairformVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

	@Override
	public List<RepairFormVO> getAll() {
		List<RepairFormVO> list = new ArrayList<RepairFormVO>();
		RepairFormVO repairformVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
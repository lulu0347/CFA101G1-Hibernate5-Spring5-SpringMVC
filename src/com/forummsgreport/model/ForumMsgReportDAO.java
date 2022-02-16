package com.forummsgreport.model;

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

public class ForumMsgReportDAO implements ForumMsgReportDAO_interface{
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
			"INSERT INTO ForumMsgReport (memNo,forumMsgNo,forumMsgReportTime,forumMsgReportWhy,forumMsgReportType) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT forumMsgReportNo,memNo,forumMsgNo,forumMsgReportTime,forumMsgReportWhy,forumMsgReportType FROM ForumMsgReport order by forumMsgReportNo";
	private static final String GET_ONE_STMT = 
			"SELECT forumMsgReportNo,memNo,forumMsgNo,forumMsgReportTime,forumMsgReportWhy,forumMsgReportType FROM ForumMsgReport where forumMsgReportNo = ?";
	private static final String GET_ALL_MEMNO = 
			"SELECT forumMsgReportNo,memNo,forumMsgNo,forumMsgReportTime,forumMsgReportWhy,forumMsgReportType FROM ForumMsgReport where memNo = ?";
	private static final String DELETE = 
			"DELETE FROM ForumMsgReport where forumMsgReportNo = ?";
	private static final String UPDATE = 
			"UPDATE ForumPostReport set memNo=? ,forumMsgNo=? ,forumMsgReportTime=? ,forumMsgReportWhy=? ,forumMsgReportType=? where forumMsgReportNo = ?";
	
	
	
	@Override
	public void insert(ForumMsgReportVO forumMsgReportVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,forumMsgReportVO.getMemNo());
			pstmt.setInt(2,forumMsgReportVO.getForumMsgNo());
			pstmt.setDate(3,forumMsgReportVO.getForumMsgReportTime());
			pstmt.setString(4,forumMsgReportVO.getForumMsgReportWhy());
			pstmt.setInt(5,forumMsgReportVO.getForumMsgReportType());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
		}
		
	}
	
	public void update(ForumMsgReportVO forumMsgReportVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,forumMsgReportVO.getMemNo());
			pstmt.setInt(2,forumMsgReportVO.getForumMsgNo());
			pstmt.setDate(3, forumMsgReportVO.getForumMsgReportTime());
			pstmt.setString(4, forumMsgReportVO.getForumMsgReportWhy());
			pstmt.setInt(5, forumMsgReportVO.getForumMsgReportType());
			pstmt.setInt(6, forumMsgReportVO.getForumMsgReportNo());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt!= null) {
				try {
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			}
		}
		
	}

	@Override
	public void delete(Integer forumMsgReportNo) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, forumMsgReportNo);
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace(System.err);
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			}
		}
	}


	@Override
	public ForumMsgReportVO findByPrimaryKey(Integer forumMsgReportNo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgReportVO forumMsgReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, forumMsgReportNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(rs.getInt("forumMsgReportNo"));
				forumMsgReportVO.setMemNo(rs.getInt("memNo"));
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setForumMsgReportTime(rs.getDate("forumMsgReportTime"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
			}
		}catch(SQLException se) {
			se.printStackTrace(System.err);
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			}
		}
		return forumMsgReportVO;
	}

	@Override
	public List<ForumMsgReportVO> getAll() {
		List<ForumMsgReportVO> list = new ArrayList<ForumMsgReportVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgReportVO forumMsgReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(rs.getInt("forumMsgReportNo"));
				forumMsgReportVO.setMemNo(rs.getInt("memNo"));
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setForumMsgReportTime(rs.getDate("forumMsgReportTime"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
				list.add(forumMsgReportVO);
			}
		}catch(SQLException se) {
			se.printStackTrace(System.err);
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			}
		}
		return list;
	}
	@Override
	public List<ForumMsgReportVO> getMemReport(Integer memNo) {
		List<ForumMsgReportVO> list = new ArrayList<ForumMsgReportVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgReportVO forumMsgReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgReportVO = new ForumMsgReportVO();
				forumMsgReportVO.setForumMsgReportNo(rs.getInt("forumMsgReportNo"));
				forumMsgReportVO.setMemNo(rs.getInt("memNo"));
				forumMsgReportVO.setForumMsgNo(rs.getInt("forumMsgNo"));
				forumMsgReportVO.setForumMsgReportTime(rs.getDate("forumMsgReportTime"));
				forumMsgReportVO.setForumMsgReportWhy(rs.getString("forumMsgReportWhy"));
				forumMsgReportVO.setForumMsgReportType(rs.getInt("forumMsgReportType"));
				list.add(forumMsgReportVO);
			}
		}catch(SQLException se) {
			se.printStackTrace(System.err);
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			}
		}
		return list;
	}
}

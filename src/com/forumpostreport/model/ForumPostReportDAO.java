package com.forumpostreport.model;

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


public class ForumPostReportDAO implements ForumPostReportDAO_interface {
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
			"INSERT INTO ForumPostReport (ForumPostNo,memNo,forumPostReportTime,forumPostReportWhy,forumPostReportType) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT forumPostReportNo,forumPostNo,memNo,forumPostReportTime,forumPostReportWhy,forumPostReportType FROM ForumPostReport order by forumPostReportNo";
	private static final String GET_ONE_STMT = 
			"SELECT forumPostReportNo,forumPostNo,memNo,forumPostReportTime,forumPostReportWhy,forumPostReportType FROM ForumPostReport where forumPostReportNo = ?";
	private static final String GET_ONE_MEMNO = 
			"SELECT forumPostReportNo,forumPostNo,memNo,forumPostReportTime,forumPostReportWhy,forumPostReportType FROM ForumPostReport where memNo = ?";
	private static final String DELETE = 
			"DELETE FROM ForumPostReport where forumPostReportNo = ?";
	private static final String UPDATE = 
			"UPDATE ForumPostReport set forumPostNo=? , memNo=? , forumPostReportTime=? , forumPostReportWhy=? , forumPostReportType=? where forumPostReportNo = ?";
	
	
	@Override
	public void insert(ForumPostReportVO forumPostReportVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,forumPostReportVO.getForumPostNo());
			pstmt.setInt(2,forumPostReportVO.getMemNo());
			pstmt.setDate(3,forumPostReportVO.getForumPostReportTime());
			pstmt.setString(4,forumPostReportVO.getForumPostReportWhy());
			pstmt.setInt(5,forumPostReportVO.getForumPostReportType());
			
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

	@Override
	public void update(ForumPostReportVO forumPostReportVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,forumPostReportVO.getForumPostNo());
			pstmt.setInt(2,forumPostReportVO.getMemNo());
			pstmt.setDate(3,forumPostReportVO.getForumPostReportTime());
			pstmt.setString(4,forumPostReportVO.getForumPostReportWhy());
			pstmt.setInt(5,forumPostReportVO.getForumPostReportType());
			pstmt.setInt(6, forumPostReportVO.getForumPostReportNo());
			
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
	public void delete(Integer forumPostReportNo) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, forumPostReportNo);
			
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
	public ForumPostReportVO findByPrimaryKey(Integer forumPostReportNo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostReportVO forumPostReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, forumPostReportNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostReportVO = new ForumPostReportVO();
				
				forumPostReportVO.setForumPostReportNo(rs.getInt("forumPostReportNo"));
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setMemNo(rs.getInt("memNo"));
				forumPostReportVO.setForumPostReportTime(rs.getDate("forumPostReportTime"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				
				
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
		return forumPostReportVO;
	}

	@Override
	public List<ForumPostReportVO> getAll() {
		List<ForumPostReportVO> list = new ArrayList<ForumPostReportVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostReportVO forumPostReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostReportNo(rs.getInt("forumPostReportNo"));
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setMemNo(rs.getInt("memNo"));
				forumPostReportVO.setForumPostReportTime(rs.getDate("forumPostReportTime"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				list.add(forumPostReportVO);
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
	public List<ForumPostReportVO> getMemPostReport(Integer memNo) {
		List<ForumPostReportVO> list = new ArrayList<ForumPostReportVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostReportVO forumPostReportVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			if(rs==null) {
				return list;
			}
			while(rs.next()) {
				forumPostReportVO = new ForumPostReportVO();
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostReportNo"));
				forumPostReportVO.setForumPostNo(rs.getInt("forumPostNo"));
				forumPostReportVO.setMemNo(rs.getInt("memNo"));
				forumPostReportVO.setForumPostReportTime(rs.getDate("forumPostReportTime"));
				forumPostReportVO.setForumPostReportWhy(rs.getString("forumPostReportWhy"));
				forumPostReportVO.setForumPostReportType(rs.getInt("forumPostReportType"));
				list.add(forumPostReportVO);
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

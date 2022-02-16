package com.forumpostcollection.model;

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

import com.forumpost.model.ForumPostVO;

public class ForumPostCollectionDAO implements ForumPostCollectionDAO_interface {

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
			"INSERT INTO ForumPostCollection (memNo,forumPostNo) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT memNo,forumPostNo FROM ForumPostCollection order by memNo";
	private static final String GET_ONE_STMT = 
			"SELECT memNo,forumPostNo FROM ForumPostCollection where forumPostNo = ?";
	private static final String GET_ONE_COLLECTION = 
			"SELECT memNo,forumPostNo FROM ForumPostCollection where memNo = ?";
	private static final String DELETE = 
			"DELETE FROM ForumPostCollection where memNo = ? and forumPostNo = ?";
	private static final String UPDATE = 
			"UPDATE ForumPostCollection set forumPostNo=? where memNo = ?";
	
	
	@Override
	public void insert(ForumPostCollectionVO forumPostCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,forumPostCollectionVO.getMemNo());
			pstmt.setInt(2,forumPostCollectionVO.getForumPostNo());

			
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
	public void update(ForumPostCollectionVO forumPostVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, forumPostVO.getForumPostNo());
			pstmt.setInt(2,forumPostVO.getMemNo());

			
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
	public void delete(Integer memNo,Integer forumPostNo) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, forumPostNo);
			
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
	public ForumPostCollectionVO findByPrimaryKey(Integer memNo,Integer forumPostNo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostCollectionVO forumPostCollectionVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, forumPostNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostCollectionVO = new ForumPostCollectionVO();
				forumPostCollectionVO.setMemNo(rs.getInt("memNo"));
				forumPostCollectionVO.setForumPostNo(rs.getInt("forumPostNo"));

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
		return forumPostCollectionVO;
	}

	@Override
	public List<ForumPostCollectionVO> getAll() {
		List<ForumPostCollectionVO> list = new ArrayList<ForumPostCollectionVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostCollectionVO forumPostCollectionVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostCollectionVO = new ForumPostCollectionVO();
				forumPostCollectionVO.setMemNo(rs.getInt("memNo"));
				forumPostCollectionVO.setForumPostNo(rs.getInt("forumPostNo"));
				list.add(forumPostCollectionVO);
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
	public List<ForumPostCollectionVO> getMemCollection(Integer memNo) {
		List<ForumPostCollectionVO> list = new ArrayList<ForumPostCollectionVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostCollectionVO forumPostCollectionVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_COLLECTION);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostCollectionVO = new ForumPostCollectionVO();
				forumPostCollectionVO.setMemNo(rs.getInt("memNo"));
				forumPostCollectionVO.setForumPostNo(rs.getInt("forumPostNo"));
				list.add(forumPostCollectionVO);
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

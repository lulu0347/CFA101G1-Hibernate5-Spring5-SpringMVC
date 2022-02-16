package com.forummsg.model;

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

public class ForumMsgDAO implements ForumMsgDAO_interface {
	
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
			"INSERT INTO Comment (parent_Id,content,type,commentator,gmt_Create,gmt_Modified) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT id,parent_Id,content,type,commentator,gmt_Create,gmt_Modified,like_Count,comment_Count FROM Comment order by id";
	private static final String GET_ONE_STMT = 
			"SELECT id,parent_Id,content,type,commentator,gmt_Create,gmt_Modified,like_Count,comment_Count FROM Comment where id = ?";
	private static final String GET_ONE_MEMNO = 
			"SELECT id,parent_Id,content,type,commentator,gmt_Create,gmt_Modified,like_Count,comment_Count FROM Comment where commentator = ?";
	private static final String GET_ONE_PARENTID = 
			"SELECT id,parent_Id,content,type,commentator,gmt_Create,gmt_Modified,like_Count,comment_Count FROM Comment where parent_Id = ?";
	private static final String DELETE = 
			"DELETE FROM Comment where id = ?";
	private static final String UPDATE = 
			"UPDATE Comment set content,type=?,gmtModified=?,like_Count=?,comment_Count=? where id = ?";
	
	
	
	@Override
	public void insert(ForumMsgVO forumMsgVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			Long currentTime = System.currentTimeMillis(); 
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,forumMsgVO.getParentId());
			pstmt.setString(2,forumMsgVO.getContent());
			pstmt.setInt(3,forumMsgVO.getType());
			pstmt.setInt(4,forumMsgVO.getCommentator());
			pstmt.setLong(5,currentTime);
			pstmt.setLong(6,currentTime);
			
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
	public void update(ForumMsgVO forumMsgVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,forumMsgVO.getType());
			pstmt.setLong(2,forumMsgVO.getGmtModified());
			pstmt.setInt(3,forumMsgVO.getLikeCount());
			pstmt.setInt(4,forumMsgVO.getCommentCount());
			pstmt.setString(5,forumMsgVO.getContent());
			
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
	public void delete(Integer id) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			
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
	public ForumMsgVO findByPrimaryKey(Integer id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgVO forumMsgVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgVO = new ForumMsgVO();
				
				forumMsgVO.setId(rs.getInt("id"));
				forumMsgVO.setParentId(rs.getInt("parent_Id"));
				forumMsgVO.setContent(rs.getString("content"));
				forumMsgVO.setType(rs.getInt("type"));
				forumMsgVO.setCommentator(rs.getInt("commentator"));
				forumMsgVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumMsgVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumMsgVO.setLikeCount(rs.getInt("like_Count"));
				forumMsgVO.setCommentCount(rs.getInt("comment_Count"));
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
		return forumMsgVO;
	}

	@Override
	public List<ForumMsgVO> getAll() {
		List<ForumMsgVO> list = new ArrayList<ForumMsgVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgVO forumMsgVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setId(rs.getInt("id"));
				forumMsgVO.setParentId(rs.getInt("parent_Id"));
				forumMsgVO.setContent(rs.getString("content"));
				forumMsgVO.setType(rs.getInt("type"));
				forumMsgVO.setCommentator(rs.getInt("commentator"));
				forumMsgVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumMsgVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumMsgVO.setLikeCount(rs.getInt("like_Count"));
				forumMsgVO.setCommentCount(rs.getInt("comment_Count"));
				list.add(forumMsgVO);
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
	public List<ForumMsgVO> getMemMsg(Integer commentator) {
		List<ForumMsgVO> list = new ArrayList<ForumMsgVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgVO forumMsgVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMNO);
			pstmt.setInt(1, commentator);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setId(rs.getInt("id"));
				forumMsgVO.setParentId(rs.getInt("parent_Id"));
				forumMsgVO.setContent(rs.getString("content"));
				forumMsgVO.setType(rs.getInt("type"));
				forumMsgVO.setCommentator(rs.getInt("commentator"));
				forumMsgVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumMsgVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumMsgVO.setLikeCount(rs.getInt("like_Count"));
				forumMsgVO.setCommentCount(rs.getInt("comment_Count"));
				list.add(forumMsgVO);
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
	public List<ForumMsgVO> getPostMsg(Integer parentId) {
		List<ForumMsgVO> list = new ArrayList<ForumMsgVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumMsgVO forumMsgVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PARENTID);
			pstmt.setInt(1, parentId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumMsgVO = new ForumMsgVO();
				forumMsgVO.setId(rs.getInt("id"));
				forumMsgVO.setParentId(rs.getInt("parent_Id"));
				forumMsgVO.setContent(rs.getString("content"));
				forumMsgVO.setType(rs.getInt("type"));
				forumMsgVO.setCommentator(rs.getInt("commentator"));
				forumMsgVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumMsgVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumMsgVO.setLikeCount(rs.getInt("like_Count"));
				forumMsgVO.setCommentCount(rs.getInt("comment_Count"));
				list.add(forumMsgVO);
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
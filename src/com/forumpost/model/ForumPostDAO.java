package com.forumpost.model;

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

import com.admini.model.AdminiVO;
import com.mysql.cj.protocol.Resultset;

public class ForumPostDAO implements ForumPostDAO_interface {
	
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
			"INSERT INTO Question (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT ID, TITLE,	DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, COMMENT_COUNT , VIEW_COUNT,	LIKE_COUNT, TAG FROM Question order by id desc";
	private static final String GET_ALL_Like = 
			"SELECT * FROM Question order by like_count desc limit 10";
	private static final String GET_ONE_STMT = 
			"SELECT ID, TITLE,	DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, COMMENT_COUNT , VIEW_COUNT,	LIKE_COUNT, TAG FROM Question where id = ?";
	private static final String GET_ONE_MEMNO = 
			"SELECT ID, TITLE,	DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, COMMENT_COUNT , VIEW_COUNT,	LIKE_COUNT, TAG FROM Question where creator = ? order by id desc";
	private static final String DELETE = 
			"DELETE FROM Question where id = ?";
	private static final String UPDATE = 
			"UPDATE Question set TITLE=?, DESCRIPTION=?, GMT_MODIFIED=?, TAG=? where id = ?";
	private static final String UPDATE_DESCRIPTION = 
			"UPDATE Question set DESCRIPTION=?, GMT_MODIFIED=? where id = ?";
	private static final String UPDATE_VIEWCOUNT = 
			"UPDATE Question set VIEW_COUNT=? where id = ?";
	private static final String UPDATE_LIKECOUNT = 
			"UPDATE Question set LIKE_COUNT=? where id = ?";
	private static final String UPDATE_COMMENTCOUNT = 
			"UPDATE Question set COMMENT_COUNT=? where id = ?";
	private static final String GET_ONE_INNERMEM = 
			"SELECT * FROM Question inner join MEM on Question.creator = MEM.MemNo where id = ?";
	
	
	@Override
	public void insert(ForumPostVO forumPostVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,forumPostVO.getTitle());
			pstmt.setString(2,forumPostVO.getDescription());
			pstmt.setLong(3,forumPostVO.getGmtCreate());
			pstmt.setLong(4,forumPostVO.getGmtModified());
			pstmt.setInt(5,forumPostVO.getCreator());
			pstmt.setString(6,forumPostVO.getTag());
			
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
	public void update(ForumPostVO forumPostVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,forumPostVO.getTitle());
			pstmt.setString(2, forumPostVO.getDescription());
			pstmt.setLong(3, forumPostVO.getGmtModified());
			pstmt.setString(4, forumPostVO.getTag());
			pstmt.setInt(5, forumPostVO.getId());
			
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
	public void VCPlus(ForumPostVO forumPostVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VIEWCOUNT);
			
			pstmt.setInt(1,forumPostVO.getViewCount()+1);
			pstmt.setInt(2,forumPostVO.getId());
			
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
	public void CCPlus(ForumPostVO forumPostVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COMMENTCOUNT);
			
			pstmt.setInt(1,forumPostVO.getCommentCount());
			pstmt.setInt(2, forumPostVO.getId());
			
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
	public ForumPostVO findByPrimaryKey(Integer id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostVO forumPostVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setId(rs.getInt("id"));
				forumPostVO.setCreator(rs.getInt("creator"));
				forumPostVO.setTitle(rs.getString("title"));
				forumPostVO.setDescription(rs.getString("description"));
				forumPostVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumPostVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumPostVO.setCommentCount(rs.getInt("comment_Count"));
				forumPostVO.setViewCount(rs.getInt("view_Count"));
				forumPostVO.setLikeCount(rs.getInt("like_Count"));
				forumPostVO.setTag(rs.getString("tag"));

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
		return forumPostVO;
	}

	@Override
	public List<ForumPostVO> getAll() {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostVO forumPostVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setId(rs.getInt("id"));
				forumPostVO.setCreator(rs.getInt("creator"));
				forumPostVO.setTitle(rs.getString("title"));
				forumPostVO.setDescription(rs.getString("description"));
				forumPostVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumPostVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumPostVO.setCommentCount(rs.getInt("comment_Count"));
				forumPostVO.setViewCount(rs.getInt("view_Count"));
				forumPostVO.setLikeCount(rs.getInt("like_Count"));
				forumPostVO.setTag(rs.getString("tag"));
				list.add(forumPostVO);
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
	public List<ForumPostVO> getLike() {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostVO forumPostVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_Like);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setId(rs.getInt("id"));
				forumPostVO.setCreator(rs.getInt("creator"));
				forumPostVO.setTitle(rs.getString("title"));
				forumPostVO.setDescription(rs.getString("description"));
				forumPostVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumPostVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumPostVO.setCommentCount(rs.getInt("comment_Count"));
				forumPostVO.setViewCount(rs.getInt("view_Count"));
				forumPostVO.setLikeCount(rs.getInt("like_Count"));
				forumPostVO.setTag(rs.getString("tag"));
				list.add(forumPostVO);
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
	public List<ForumPostVO> getMemPost(Integer memNo) {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostVO forumPostVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMNO);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setId(rs.getInt("id"));
				forumPostVO.setCreator(rs.getInt("creator"));
				forumPostVO.setTitle(rs.getString("title"));
				forumPostVO.setDescription(rs.getString("description"));
				forumPostVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumPostVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumPostVO.setCommentCount(rs.getInt("comment_Count"));
				forumPostVO.setViewCount(rs.getInt("view_Count"));
				forumPostVO.setLikeCount(rs.getInt("like_Count"));
				forumPostVO.setTag(rs.getString("tag"));
				list.add(forumPostVO);
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
	public Object InnerbyId(Integer id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		Object object =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_INNERMEM);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				object = rs;
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
		return object;
	}

	@Override
	public ForumPostVO updatePostContent(ForumPostVO forumPostVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DESCRIPTION);
			Long modified = System.currentTimeMillis();
					
			pstmt.setString(1, forumPostVO.getDescription());
			pstmt.setLong(2, modified);
			pstmt.setInt(3, forumPostVO.getId());
			
			pstmt.executeUpdate();
			
			forumPostVO.setGmtModified(modified);
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
		return forumPostVO;
	}
	
	
	@Override
	public List<ForumPostVO> likelyPost(String search) {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ForumPostVO forumPostVO =null;
		ResultSet rs =null;
		String point = search;
		String str = "SELECT * FROM Question where title LIKE '%"+ point +"%' order by id desc";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(str);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setId(rs.getInt("id"));
				forumPostVO.setCreator(rs.getInt("creator"));
				forumPostVO.setTitle(rs.getString("title"));
				forumPostVO.setDescription(rs.getString("description"));
				forumPostVO.setGmtCreate(rs.getLong("gmt_Create"));
				forumPostVO.setGmtModified(rs.getLong("gmt_Modified"));
				forumPostVO.setCommentCount(rs.getInt("comment_Count"));
				forumPostVO.setViewCount(rs.getInt("view_Count"));
				forumPostVO.setLikeCount(rs.getInt("like_Count"));
				forumPostVO.setTag(rs.getString("tag"));
				list.add(forumPostVO);
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
	public void LCPlus(Integer id,Integer likeCount) {
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_LIKECOUNT);
			
			pstmt.setInt(1, likeCount+1);
			pstmt.setInt(2, id);
			
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

}

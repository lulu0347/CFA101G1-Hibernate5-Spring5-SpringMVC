package com.notify.model;

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
import com.mysql.cj.protocol.Resultset;

public class NotifyDAO implements NotifyDAO_interface {
	
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
			"INSERT INTO notification (notifier, notifier_name, receiver, outerid, outer_title, type, gmt_create, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM notification order by id desc";
	private static final String GET_MEM_STMT = 
			"SELECT * FROM notification where receiver = ? and notifier != ? and status != 1 order by id desc";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM notification where id = ?";
	private static final String GET_ZERO_STATUS = 
			"SELECT * FROM notification where status = 0 and receiver = ?";
	private static final String DELETE = 
			"DELETE FROM notification where id = ?";
	private static final String UPDATE = 
			"UPDATE notification set status=? where id = ?";
	private static final String ALLREAD = 
			"UPDATE notification set status=? where receiver = ?";
	private static final String UPDATE_QMID = 
			"UPDATE notification set status=? where receiver = ? and outerid = ?";
	
	@Override
	public void insert(NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,notifyVO.getNotifier());
			pstmt.setString(2,notifyVO.getNotifier_name());
			pstmt.setInt(3,notifyVO.getReceiver());
			pstmt.setInt(4,notifyVO.getOuterid());
			pstmt.setString(5,notifyVO.getOuter_title());
			pstmt.setInt(6,notifyVO.getType());
			pstmt.setLong(7,notifyVO.getGmt_create());
			pstmt.setInt(8, 0);
			
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
	public void update(NotifyVO notifyVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,notifyVO.getStatus());
			pstmt.setInt(2,notifyVO.getId());
			
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
	public NotifyVO findByPrimaryKey(Integer id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		NotifyVO notifyVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notifyVO = new NotifyVO();
				notifyVO.setId(rs.getInt("id"));
				notifyVO.setNotifier(rs.getInt("notifier"));
				notifyVO.setNotifier_name(rs.getString("notifier_name"));
				notifyVO.setReceiver(rs.getInt("receiver"));
				notifyVO.setOuterid(rs.getInt("outerid"));
				notifyVO.setOuter_title(rs.getString("outer_title"));
				notifyVO.setType(rs.getInt("type"));
				notifyVO.setGmt_create(rs.getLong("gmt_create"));
				notifyVO.setStatus(rs.getInt("status"));
				
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
		return notifyVO;
	}

	@Override
	public List<NotifyVO> getAll() {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		NotifyVO notifyVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notifyVO = new NotifyVO();
				notifyVO.setId(rs.getInt("id"));
				notifyVO.setNotifier(rs.getInt("notifier"));
				notifyVO.setNotifier_name(rs.getString("notifier_name"));
				notifyVO.setReceiver(rs.getInt("receiver"));
				notifyVO.setOuterid(rs.getInt("outerid"));
				notifyVO.setOuter_title(rs.getString("outer_title"));
				notifyVO.setType(rs.getInt("type"));
				notifyVO.setGmt_create(rs.getLong("gmt_create"));
				notifyVO.setStatus(rs.getInt("status"));
				list.add(notifyVO);
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
	public List<NotifyVO> getMemNotify(Integer receiver) {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		NotifyVO notifyVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_STMT);
			
			pstmt.setInt(1, receiver);
			pstmt.setInt(2, receiver);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notifyVO = new NotifyVO();
				notifyVO.setId(rs.getInt("id"));
				notifyVO.setNotifier(rs.getInt("notifier"));
				notifyVO.setNotifier_name(rs.getString("notifier_name"));
				notifyVO.setReceiver(rs.getInt("receiver"));
				notifyVO.setOuterid(rs.getInt("outerid"));
				notifyVO.setOuter_title(rs.getString("outer_title"));
				notifyVO.setType(rs.getInt("type"));
				notifyVO.setGmt_create(rs.getLong("gmt_create"));
				notifyVO.setStatus(rs.getInt("status"));
				list.add(notifyVO);
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
	public List<NotifyVO> getZeroStatus(Integer receiver) {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		Connection con =null;
		PreparedStatement pstmt = null;
		NotifyVO notifyVO =null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ZERO_STATUS);
			
			pstmt.setInt(1, receiver);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notifyVO = new NotifyVO();
				notifyVO.setId(rs.getInt("id"));
				notifyVO.setNotifier(rs.getInt("notifier"));
				notifyVO.setNotifier_name(rs.getString("notifier_name"));
				notifyVO.setReceiver(rs.getInt("receiver"));
				notifyVO.setOuterid(rs.getInt("outerid"));
				notifyVO.setOuter_title(rs.getString("outer_title"));
				notifyVO.setType(rs.getInt("type"));
				notifyVO.setGmt_create(rs.getLong("gmt_create"));
				notifyVO.setStatus(rs.getInt("status"));
				list.add(notifyVO);
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
	public void ChangeStatus(NotifyVO notifyVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ALLREAD);
			
			pstmt.setInt(1,notifyVO.getStatus());
			pstmt.setInt(2,notifyVO.getReceiver());
			
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
	public void qmid(NotifyVO notifyVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_QMID);
			
			pstmt.setInt(1,notifyVO.getStatus());
			pstmt.setInt(2,notifyVO.getReceiver());
			pstmt.setInt(3,notifyVO.getOuterid());
			
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

package com.auctOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auctOrderDetl.model.AuctOrdDetlDAO_JDBC;
import com.auctOrderDetl.model.AuctOrdDetlVO;
import com.transRec.model.TransRecDAO;
import com.transRec.model.TransRecDAO_interface;
import com.transRec.model.TransRecVO;

import DbUtil.BaseDAO;

public class AuctOrdDAO_JDBC extends BaseDAO implements AuctOrdDAO_interface {

	private final String INSERT_STMT = "insert into AUCTORD(MemNo,AuctOrdAmount,ReceName,ReceAddr,RecePhone,Note,AuctOrdTime,AuctOrdModTime,AuctOrdState) VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? )";
	private final String INSERT_LIST = "insert into AUCTORDDETL(AuctOrdNo,AuctActProdNo,ProdPurQty) VALUES (?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTORD set MemNo=?,AuctOrdAmount=?,ReceName=?,ReceAddr=?,RecePhone=?,Note=?,AuctOrdTime=?,AuctOrdModTime=?,AuctOrdState=? where AuctOrdNo=?";
	private final String FIND_BY_PK = "Select * from AUCTORD where AuctOrdNo=?";
	private final String GET_ALL = "Select * from AUCTORD order by AuctOrdNo";
	private final String FIND_BY_MEMPK = "Select * from AUCTORD where MemNo=? order by AuctOrdNo desc";
	
	

	static AuctOrdDetlDAO_JDBC detlJdbc = new AuctOrdDetlDAO_JDBC();
	static TransRecDAO_interface transRecDAO = new TransRecDAO();
	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
		return con;
	}

	@Override
	public Integer add(AuctOrdVO auctOrd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctOrdNo = null;
		try {

			con = getConnection();

			String cols[] = { "AuctOrdNo" };

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, auctOrd.getMemNo());
			pstmt.setInt(2, auctOrd.getAuctOrdAmount());
			pstmt.setString(3, auctOrd.getReceName());
			pstmt.setString(4, auctOrd.getReceAddr());
			pstmt.setString(5, auctOrd.getRecePhone());
			pstmt.setString(6, auctOrd.getNote());
			pstmt.setTimestamp(7, auctOrd.getAuctOrdTime());
			pstmt.setTimestamp(8, auctOrd.getAuctOrdModTime());
			pstmt.setInt(9, auctOrd.getAuctOrdState());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctOrdNo = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return next_AuctOrdNo;
	}

	@Override
	public void update(AuctOrdVO auctOrd) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, auctOrd.getMemNo());
			pstmt.setInt(2, auctOrd.getAuctOrdAmount());
			pstmt.setString(3, auctOrd.getReceName());
			pstmt.setString(4, auctOrd.getReceAddr());
			pstmt.setString(5, auctOrd.getRecePhone());
			pstmt.setString(6, auctOrd.getNote());
			pstmt.setTimestamp(7, auctOrd.getAuctOrdTime());
			pstmt.setTimestamp(8, auctOrd.getAuctOrdModTime());
			pstmt.setInt(9, auctOrd.getAuctOrdState());
			pstmt.setInt(10, auctOrd.getAuctOrdNo());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
	public AuctOrdVO findByOrdNo(Integer auctOrdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctOrdVO auctOrdVO = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, auctOrdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdVO = new AuctOrdVO();
				auctOrdVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdVO.setMemNo(rs.getInt("memNo"));
				auctOrdVO.setAuctOrdAmount(rs.getInt("auctOrdAmount"));
				auctOrdVO.setReceName(rs.getString("receName"));
				auctOrdVO.setReceAddr(rs.getString("receAddr"));
				auctOrdVO.setRecePhone(rs.getString("recePhone"));
				auctOrdVO.setNote(rs.getString("note"));
				auctOrdVO.setAuctOrdTime(rs.getTimestamp("auctOrdTime"));
				auctOrdVO.setAuctOrdModTime(rs.getTimestamp("auctOrdModTime"));
				auctOrdVO.setAuctOrdState(rs.getInt("auctOrdState"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return auctOrdVO;
	}

	@Override
	public List<AuctOrdVO> getAll() {
		List<AuctOrdVO> auctOrdList = new ArrayList<>();
		AuctOrdVO auctOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdVO = new AuctOrdVO();

				auctOrdVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdVO.setMemNo(rs.getInt("memNo"));
				auctOrdVO.setAuctOrdAmount(rs.getInt("auctOrdAmount"));
				auctOrdVO.setReceName(rs.getString("receName"));
				auctOrdVO.setReceAddr(rs.getString("receAddr"));
				auctOrdVO.setRecePhone(rs.getString("recePhone"));
				auctOrdVO.setNote(rs.getString("note"));
				auctOrdVO.setAuctOrdTime(rs.getTimestamp("auctOrdTime"));
				auctOrdVO.setAuctOrdModTime(rs.getTimestamp("auctOrdModTime"));
				auctOrdVO.setAuctOrdState(rs.getInt("auctOrdState"));

				auctOrdList.add(auctOrdVO);

			}

		} catch (SQLException se) {
			se.printStackTrace();

		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return auctOrdList;
	}

	@Override
	public void addOrderWithOrderDetl(AuctOrdVO auctOrd, List<AuctOrdDetlVO> ordDetlList, TransRecVO transRecVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctOrdNo = null;
		try {

			con = getConnection();

			String cols[] = { "AuctOrdNo" };
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, auctOrd.getMemNo());
			pstmt.setInt(2, auctOrd.getAuctOrdAmount());
			pstmt.setString(3, auctOrd.getReceName());
			pstmt.setString(4, auctOrd.getReceAddr());
			pstmt.setString(5, auctOrd.getRecePhone());
			pstmt.setString(6, auctOrd.getNote());
			pstmt.setTimestamp(7, auctOrd.getAuctOrdTime());
			pstmt.setTimestamp(8, auctOrd.getAuctOrdModTime());
			pstmt.setInt(9, auctOrd.getAuctOrdState());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctOrdNo = rs.getInt(1);
			}
			rs.close();
			detlJdbc.addInTransaction(con, next_AuctOrdNo, ordDetlList);
			
			
			//將訂單編號放入錢包
			transRecVO.setOrderNo(next_AuctOrdNo);
			transRecDAO.addInTransaction(con, transRecVO);
			
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
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
	public Integer addInTransaction(Connection con, AuctOrdVO auctOrd) throws SQLException {
		
		Integer next_AuctOrdNo = null;
		String cols[] = { "AuctOrdNo" };
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, cols);

		pstmt.setInt(1, auctOrd.getMemNo());
		pstmt.setInt(2, auctOrd.getAuctOrdAmount());
		pstmt.setString(3, auctOrd.getReceName());
		pstmt.setString(4, auctOrd.getReceAddr());
		pstmt.setString(5, auctOrd.getRecePhone());
		pstmt.setString(6, auctOrd.getNote());
		pstmt.setTimestamp(7, auctOrd.getAuctOrdTime());
		pstmt.setTimestamp(8, auctOrd.getAuctOrdModTime());
		pstmt.setInt(9, auctOrd.getAuctOrdState());

		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			next_AuctOrdNo = rs.getInt(1);
		}
		rs.close();
		return next_AuctOrdNo;
	}
	
	@Override
	//用會員編號查詢訂單紀錄
	public  List<AuctOrdVO> findByMemNo(Integer memNo) {
		List<AuctOrdVO> auctOrdList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctOrdVO auctOrdVO = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEMPK);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdVO = new AuctOrdVO();
				auctOrdVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdVO.setMemNo(rs.getInt("memNo"));
				auctOrdVO.setAuctOrdAmount(rs.getInt("auctOrdAmount"));
				auctOrdVO.setReceName(rs.getString("receName"));
				auctOrdVO.setReceAddr(rs.getString("receAddr"));
				auctOrdVO.setRecePhone(rs.getString("recePhone"));
				auctOrdVO.setNote(rs.getString("note"));
				auctOrdVO.setAuctOrdTime(rs.getTimestamp("auctOrdTime"));
				auctOrdVO.setAuctOrdModTime(rs.getTimestamp("auctOrdModTime"));
				auctOrdVO.setAuctOrdState(rs.getInt("auctOrdState"));
				auctOrdList.add(auctOrdVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return auctOrdList;
	}
	
	
	
	
	
	


	
}

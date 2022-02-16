package com.auctAct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.auctActProd.model.AuctActProdDAO_JDBC;
import com.auctActProd.model.AuctActProdVO;
import com.auctPic.model.AuctActPicVO;

import DbUtil.BaseDAO;

public class AuctActDAO_JDBC extends BaseDAO implements AuctActDAO_interface {

	private final String INSERT_STMT = "insert into AUCTACT(AuctActName,AuctActDesc,AuctActState,AuctStartTime,AuctEndTime) VALUES (?, ?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTACT set AuctActName=?,AuctActDesc=?,AuctActState=?,AuctStartTime=?,AuctEndTime=? where AuctActNo=?";
	private final String FIND_BY_PK = "Select * from AUCTACT where AuctActNo=?";
	private final String FIND_BY_TIME = "Select * from AUCTACT where AuctStartTime=? and AuctEndTime=?";
	private final String GET_ALL = "Select * from AUCTACT order by AuctActNo";
	private final String DELETE = "Delete from AUCTACT where AuctActNo=?";

	static AuctActProdDAO_JDBC actProdJDBC = new AuctActProdDAO_JDBC();

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
	public Integer add(AuctActVO auctAct) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctActNo = null;

		try {
			String cols[] = { "AuctActNo" };
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, auctAct.getAuctActName());
			pstmt.setString(2, auctAct.getAuctActDesc());
			pstmt.setInt(3, auctAct.getAuctActState());
			pstmt.setTimestamp(4, auctAct.getAuctStartTime());
			pstmt.setTimestamp(5, auctAct.getAuctEndTime());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctActNo = rs.getInt(1);
			}
			rs.close();
			con.commit();
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
		return next_AuctActNo;

	}

	@Override
	public void update(AuctActVO auctAct) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, auctAct.getAuctActName());
			pstmt.setString(2, auctAct.getAuctActDesc());
			pstmt.setInt(3, auctAct.getAuctActState());
			pstmt.setTimestamp(4, auctAct.getAuctStartTime());
			pstmt.setTimestamp(5, auctAct.getAuctEndTime());
			pstmt.setInt(6, auctAct.getAuctActNo());

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
	public AuctActVO findByAuctActNo(Integer auctActNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AuctActVO auctActVO = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, auctActNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				auctActVO = new AuctActVO();
				auctActVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActVO.setAuctActName(rs.getString("auctActName"));
				auctActVO.setAuctActDesc(rs.getString("auctActDesc"));
				auctActVO.setAuctActState(rs.getInt("auctActState"));
				auctActVO.setAuctStartTime(rs.getTimestamp("auctStartTime"));
				auctActVO.setAuctEndTime(rs.getTimestamp("auctEndTime"));
			}

		} catch (SQLException e) {
			System.out.println("SQL錯誤: " + e.getMessage());
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("連線關閉錯誤: " + e.getMessage());
			}
		}

		return auctActVO;
	}

	@Override
	public AuctActVO findByAuctTime(Timestamp auctStartTime, Timestamp auctEndTime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AuctActVO auctActVO = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_TIME);

			pstmt.setTimestamp(1, auctStartTime);
			pstmt.setTimestamp(2, auctEndTime);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				auctActVO = new AuctActVO();
				auctActVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActVO.setAuctActName(rs.getString("auctActName"));
				auctActVO.setAuctActDesc(rs.getString("auctActDesc"));
				auctActVO.setAuctActState(rs.getInt("auctActState"));
				auctActVO.setAuctStartTime(rs.getTimestamp("auctStartTime"));
				auctActVO.setAuctEndTime(rs.getTimestamp("auctEndTime"));
			}

		} catch (SQLException e) {
			System.out.println("SQL錯誤: " + e.getMessage());
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("連線關閉錯誤: " + e.getMessage());
			}
		}

		return auctActVO;
	}

	@Override
	public List<AuctActVO> getAll() {
		List<AuctActVO> auctActList = new ArrayList<AuctActVO>();
		AuctActVO auctActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctActVO = new AuctActVO();
				auctActVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActVO.setAuctActName(rs.getString("auctActName"));
				auctActVO.setAuctActDesc(rs.getString("auctActDesc"));
				auctActVO.setAuctActState(rs.getInt("auctActState"));
				auctActVO.setAuctStartTime(rs.getTimestamp("auctStartTime"));
				auctActVO.setAuctEndTime(rs.getTimestamp("auctEndTime"));

				auctActList.add(auctActVO);

			}
		} catch (SQLException e) {
			System.out.println("SQL錯誤: " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("連線關閉錯誤: " + e.getMessage());
				}
			}
		}
		return auctActList;
	}

	@Override
	public void delete(Integer auctActNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, auctActNo);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			System.out.println("SQL錯誤: " + se.getMessage());
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("連線關閉錯誤: " + e.getMessage());
			}
		}

		return;
	}

	@Override
	public void addAct_Prod(AuctActVO auctAct, List<AuctActProdVO> actProdList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctActNo = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);
			String cols[] = { "AuctActNo" };

			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, auctAct.getAuctActName());
			pstmt.setString(2, auctAct.getAuctActDesc());
			pstmt.setInt(3, auctAct.getAuctActState());
			pstmt.setTimestamp(4, auctAct.getAuctStartTime());
			pstmt.setTimestamp(5, auctAct.getAuctEndTime());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctActNo = rs.getInt(1);

			}
			rs.close();
//			actProdJDBC.addInConnection(con, next_AuctActNo, actProdList);

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
	public Integer addActCon(Connection con, AuctActVO auctAct) throws SQLException {
		Integer next_AuctActNo = null;
		String cols[] = { "AuctActNo" };
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, cols);

		
			pstmt.setString(1, auctAct.getAuctActName());
			pstmt.setString(2, auctAct.getAuctActDesc());
			pstmt.setInt(3, auctAct.getAuctActState());
			pstmt.setTimestamp(4, auctAct.getAuctStartTime());
			pstmt.setTimestamp(5, auctAct.getAuctEndTime());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctActNo = rs.getInt(1);

			}
			rs.close();
		return next_AuctActNo;
	

}

	@Override
	public void updateActCon(Connection con, AuctActVO auctAct) throws SQLException {
		Integer AuctActNo = null;
	
		PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
		
		pstmt.setString(1, auctAct.getAuctActName());
		pstmt.setString(2, auctAct.getAuctActDesc());
		pstmt.setInt(3, auctAct.getAuctActState());
		pstmt.setTimestamp(4, auctAct.getAuctStartTime());
		pstmt.setTimestamp(5, auctAct.getAuctEndTime());
	}
}

package com.auctProd.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.auctPic.model.AuctProdPicDAO_JDBC;
import com.auctPic.model.AuctProdPicVO;

import DbUtil.BaseDAO;

public class AuctProdDAO_JDBC extends BaseDAO  implements AuctProdDAO_interface {


	private final String INSERT_STMT = "insert into AUCTPROD(AuctProdName,KindNo,AuctProdState,AuctProdDesc,AuctProdCreTime,AuctProdModTime) VALUES (?, ?, ?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTPROD set AuctProdName=?,KindNo=?,AuctProdState=?,AuctProdDesc=?,AuctProdCreTime=?,AuctProdModTime=? where AuctProdNo=?";
	private final String FIND_BY_PK = "Select * from AUCTPROD where AuctProdNo=?";
	private final String FIND_BY_Time = "Select * from AUCTPROD where AuctProdCreTime=?";
	private final String GET_ALL = "Select * from AUCTPROD order by AuctProdNo";

	static AuctProdPicDAO_JDBC  prodPicjdbc =new AuctProdPicDAO_JDBC();
	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public Integer add(AuctProdVO auctProd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctProdNo = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			String cols[] = { "AuctProdNo" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, auctProd.getAuctProdName());
			pstmt.setInt(2, auctProd.getKindNo());
			pstmt.setInt(3, auctProd.getAuctProdState());
			pstmt.setString(4, auctProd.getAuctProdDesc());
			pstmt.setTimestamp(5, auctProd.getAuctProdCreTime());
			pstmt.setTimestamp(6, auctProd.getAuctProdModTime());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctProdNo = rs.getInt(1);
			}
			rs.close();

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
		return next_AuctProdNo;
	}

	@Override
	public void update(AuctProdVO auctProd) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, auctProd.getAuctProdName());
			pstmt.setInt(2, auctProd.getKindNo());
			pstmt.setInt(3, auctProd.getAuctProdState());
			pstmt.setString(4, auctProd.getAuctProdDesc());
			pstmt.setTimestamp(5, auctProd.getAuctProdCreTime());
			pstmt.setTimestamp(6, auctProd.getAuctProdModTime());
			pstmt.setInt(7, auctProd.getAuctProdNo());

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
	public AuctProdVO findByProdNo(Integer auctProdNo) {
		Connection con = null;

		AuctProdVO auctProdVO = null;
		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, auctProdNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				auctProdVO = new AuctProdVO();
				auctProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctProdVO.setAuctProdName(rs.getString("auctProdName"));
				auctProdVO.setKindNo(rs.getInt("kindNo"));
				auctProdVO.setAuctProdState(rs.getInt("auctProdState"));
				auctProdVO.setAuctProdDesc(rs.getString("auctProdDesc"));
				auctProdVO.setAuctProdCreTime(rs.getTimestamp("auctProdCreTime"));
				auctProdVO.setAuctProdModTime(rs.getTimestamp("auctProdModTime"));
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

		return auctProdVO;
	}

	@Override
	public List<AuctProdVO> getAll() {
		List<AuctProdVO> auctProdList = new ArrayList<>();
		AuctProdVO auctProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctProdVO = new AuctProdVO();
				auctProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctProdVO.setAuctProdName(rs.getString("auctProdName"));
				auctProdVO.setKindNo(rs.getInt("kindNo"));
				auctProdVO.setAuctProdState(rs.getInt("auctProdState"));
				auctProdVO.setAuctProdDesc(rs.getString("auctProdDesc"));
				auctProdVO.setAuctProdCreTime(rs.getTimestamp("auctProdCreTime"));
				auctProdVO.setAuctProdModTime(rs.getTimestamp("auctProdModTime"));

				auctProdList.add(auctProdVO);

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

		return auctProdList;
	}

	@Override
	public AuctProdVO findByCreTime(Timestamp creTime) {
		Connection con = null;
		AuctProdVO auctProdVO = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_Time);
			pstmt.setTimestamp(1, creTime);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				auctProdVO = new AuctProdVO();

				auctProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctProdVO.setAuctProdName(rs.getString("auctProdName"));
				auctProdVO.setKindNo(rs.getInt("kindNo"));
				auctProdVO.setAuctProdState(rs.getInt("auctProdState"));
				auctProdVO.setAuctProdDesc(rs.getString("auctProdDesc"));
				auctProdVO.setAuctProdCreTime(rs.getTimestamp("auctProdCreTime"));
				auctProdVO.setAuctProdModTime(rs.getTimestamp("auctProdModTime"));
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

		return auctProdVO;
	}

	@Override
	public void addProdwithPic(AuctProdVO auctProd, List<AuctProdPicVO> auctProdPicList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_AuctProdNo = null;
		
		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			String cols[] = { "AuctProdNo" };
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, auctProd.getAuctProdName());
			pstmt.setInt(2, auctProd.getKindNo());
			pstmt.setInt(3, auctProd.getAuctProdState());
			pstmt.setString(4, auctProd.getAuctProdDesc());
			pstmt.setTimestamp(5, auctProd.getAuctProdCreTime());
			pstmt.setTimestamp(6, auctProd.getAuctProdModTime());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_AuctProdNo = rs.getInt(1);
			}
			rs.close();
			prodPicjdbc.addInConnection(con, next_AuctProdNo, auctProdPicList);
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

}

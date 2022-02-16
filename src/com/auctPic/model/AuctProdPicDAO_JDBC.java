package com.auctPic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.auctProd.model.AuctProdVO;

import DbUtil.BaseDAO;

public class AuctProdPicDAO_JDBC extends BaseDAO  implements AuctProdPicDAO_interface {

	private final String INSERT_STMT = "insert into AUCTPRODPIC(AuctProdNo,AuctProdPicInfo,AuctProdPic,AuctProdPicFormat) VALUES (?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTPRODPIC set AuctProdNo=?,AuctProdPicInfo=?,AuctProdPic=?,AuctProdPicFormat=? where AuctProdPicNo=?";
	private final String FIND_ProdNo_PicInfo = "Select * from AUCTPRODPIC where AuctProdNo=? and AuctProdPicInfo=? ";
	private final String GET_ALL = "Select * from AUCTPRODPIC order by AuctProdPicNo";

	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(AuctProdPicVO auctProdPic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);
		
			pstmt.setInt(1, auctProdPic.getAuctProdNo());
			pstmt.setString(2, auctProdPic.getAuctProdPicInfo());
			pstmt.setBytes(3, auctProdPic.getAuctProdPic());
			pstmt.setString(4, auctProdPic.getAuctProdPicFormat());

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
	public void update(AuctProdPicVO auctProdPic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, auctProdPic.getAuctProdNo());
			pstmt.setString(2, auctProdPic.getAuctProdPicInfo());
			pstmt.setBytes(3, auctProdPic.getAuctProdPic());
			pstmt.setString(4, auctProdPic.getAuctProdPicFormat());
			pstmt.setInt(5, auctProdPic.getAuctProdPicNo());

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
	public AuctProdPicVO findByProdNo_PicInfo(Integer auctProdPicNo, String auctProdPicInfo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctProdPicVO auctProdPicVO = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_ProdNo_PicInfo);
			pstmt.setInt(1, auctProdPicNo);
			pstmt.setString(2, auctProdPicInfo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctProdPicVO = new AuctProdPicVO();

				auctProdPicVO.setAuctProdPicNo(rs.getInt("auctProdPicNo"));
				auctProdPicVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctProdPicVO.setAuctProdPicInfo(rs.getString("auctProdPicInfo"));
				auctProdPicVO.setAuctProdPic(rs.getBytes("auctProdPic"));
				auctProdPicVO.setAuctProdPicFormat(rs.getString("auctProdPicFormat"));

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
		return auctProdPicVO;

	}

	@Override
	public List<AuctProdPicVO> getAll() {
		List<AuctProdPicVO> auctProdPicList = new ArrayList<>();
		AuctProdPicVO auctProdPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctProdPicVO = new AuctProdPicVO();
				auctProdPicVO.setAuctProdPicNo(rs.getInt("auctProdPicNo"));
				auctProdPicVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctProdPicVO.setAuctProdPicInfo(rs.getString("auctProdPicInfo"));
				auctProdPicVO.setAuctProdPic(rs.getBytes("auctProdPic"));
				auctProdPicVO.setAuctProdPicFormat(rs.getString("auctProdPicFormat"));

				auctProdPicList.add(auctProdPicVO);
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
		return auctProdPicList;

	}

	@Override
	public void addInConnection(Connection con, Integer auctProdNo, List<AuctProdPicVO> auctProdPicList) throws SQLException {
		
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
		for(AuctProdPicVO prodPic :auctProdPicList) {
			pstmt.setInt(1, auctProdNo);
			pstmt.setString(2, prodPic.getAuctProdPicInfo());
			pstmt.setBytes(3, prodPic.getAuctProdPic());
			pstmt.setString(4, prodPic.getAuctProdPicFormat());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		
	}
}

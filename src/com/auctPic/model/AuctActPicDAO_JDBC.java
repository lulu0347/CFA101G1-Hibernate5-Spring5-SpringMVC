package com.auctPic.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import DbUtil.BaseDAO;

public class AuctActPicDAO_JDBC  extends BaseDAO implements AuctActPicDAO_interface {

	private final String INSERT_STMT = "insert into AUCTACTPIC(AuctActNo,AuctActPicInfo,AuctActPic,AuctActPicFormat) VALUES (?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTACTPIC set AuctActPic=?,AuctActPicFormat=? where AuctActNo=? and AuctActPicInfo=?";
	private final String FIND_ProdNo_PicInfo = "Select AuctActPicNo,AuctActNo,AuctActPicInfo,AuctActPic,AuctActPicFormat from AUCTACTPIC where AuctActNo=? and AuctActPicInfo=? ";
	private final String GET_ALL = "Select * from AUCTACTPIC order by AuctActPicNo";
	private final String DELETE = "Delete from AUCTACTPIC where AuctActNo=?";
	private final String FINDPIC_ACTNO = "Select AuctActPicNo,AuctActNo,AuctActPicInfo,AuctActPic,AuctActPicFormat from AUCTACTPIC where AuctActNo=?";

	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(AuctActPicVO auctActPic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, auctActPic.getAuctActNo());
			pstmt.setString(2, auctActPic.getAuctActPicInfo());
			pstmt.setBytes(3, auctActPic.getAuctActPic());
			pstmt.setString(4, auctActPic.getAuctActPicFormat());
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
	public void update(AuctActPicVO auctActPic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setBytes(1, auctActPic.getAuctActPic());
			pstmt.setString(2, auctActPic.getAuctActPicFormat());
			pstmt.setInt(3, auctActPic.getAuctActNo());
			pstmt.setString(4, auctActPic.getAuctActPicInfo());

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
	public AuctActPicVO findByActNo_PicInfo(Integer auctActNo, String auctActPicInfo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctActPicVO auctActPicVO = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_ProdNo_PicInfo);
			pstmt.setInt(1, auctActNo);
			pstmt.setString(2, auctActPicInfo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctActPicVO = new AuctActPicVO();
				auctActPicVO.setAuctActPicNo(rs.getInt("auctActPicNo"));
				auctActPicVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActPicVO.setAuctActPicInfo(rs.getString("auctActPicInfo"));
				auctActPicVO.setAuctActPic(rs.getBytes("auctActPic"));
				auctActPicVO.setAuctActPicFormat(rs.getString("auctActPicFormat"));

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
		return auctActPicVO;
	}

	@Override
	public List<AuctActPicVO> getAll() {
		List<AuctActPicVO> auctActPicList = new ArrayList<>();
		AuctActPicVO auctActPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctActPicVO = new AuctActPicVO();
				auctActPicVO.setAuctActPicNo(rs.getInt("auctActPicNo"));
				auctActPicVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActPicVO.setAuctActPicInfo(rs.getString("auctActPicInfo"));
				auctActPicVO.setAuctActPic(rs.getBytes("auctActPic"));
				auctActPicVO.setAuctActPicFormat(rs.getString("auctActPicFormat"));

				auctActPicList.add(auctActPicVO);
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
		return auctActPicList;

	}

	@Override
	public void addActCon(Connection con, Integer auctActNo, List<AuctActPicVO> auctActPicList) throws SQLException {
		
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
		
		for(AuctActPicVO actPicVO :auctActPicList) {
		
				pstmt.setInt(1, auctActNo);
				pstmt.setString(2, actPicVO.getAuctActPicInfo());
				pstmt.setBytes(3, actPicVO.getAuctActPic());
				pstmt.setString(4, actPicVO.getAuctActPicFormat());
				pstmt.addBatch();
		}
		pstmt.executeBatch();
			
	}

	@Override
	public void updateActCon(Connection con, Integer auctActNo, List<AuctActPicVO> auctActPicList) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
		for(AuctActPicVO actPicVO :auctActPicList) {
			
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1,auctActNo);
			pstmt.setString(2, actPicVO.getAuctActPicInfo());
			pstmt.setBytes(3, actPicVO.getAuctActPic());
			pstmt.setString(4, actPicVO.getAuctActPicFormat());
			pstmt.setInt(5, actPicVO.getAuctActPicNo());
	
	}
	pstmt.executeBatch();
		
		
	}

	@Override
	public void delete(Integer auctActPicNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, auctActPicNo);

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
	public List<AuctActPicVO> findPicwithProdNo(Integer auctActNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctActPicVO  auctActPicVO= null;
		List<AuctActPicVO> auctActPicList =new ArrayList<AuctActPicVO>();
		
		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FINDPIC_ACTNO);
			pstmt.setInt(1, auctActNo);
			rs = pstmt.executeQuery();
			
		while (rs.next()) {
			auctActPicVO = new AuctActPicVO();
			auctActPicVO.setAuctActPicNo(rs.getInt("auctActPicNo"));
			auctActPicVO.setAuctActNo(rs.getInt("auctActNo"));
			auctActPicVO.setAuctActPicInfo(rs.getString("auctActPicInfo"));
			auctActPicVO.setAuctActPic(rs.getBytes("auctActPic"));
			auctActPicVO.setAuctActPicFormat(rs.getString("auctActPicFormat"));
			auctActPicList.add(auctActPicVO);
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
		return auctActPicList;
	}
	

}

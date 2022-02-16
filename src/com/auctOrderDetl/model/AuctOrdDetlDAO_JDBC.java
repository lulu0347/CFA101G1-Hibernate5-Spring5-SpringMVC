package com.auctOrderDetl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auctOrder.model.AuctOrdVO;

import DbUtil.BaseDAO;

public class AuctOrdDetlDAO_JDBC extends BaseDAO implements AuctOrdDetlDAO_interface {
	private final String INSERT_STMT = "insert into AUCTORDDETL(AuctOrdNo,AuctActProdNo,ProdPurQty,Price,SumPrice) VALUES (?, ?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTORDDETL set AuctActProdNo=?,ProdPurQty=? Price=?,SumPrice=? where AuctOrdNo=?";
	private final String FIND_BY_PK = "Select * from AUCTORDDETL where AuctOrdNo=? and AuctActProdNo=? ";
	private final String GET_ALL = "Select * from AUCTORDDETL order by AuctOrdNo";
	private final String FIND_BY_ORDNO = "Select * from AUCTORDDETL where AuctOrdNo=?";
	private final String FIND_BY_ORDNO_2 = "select * from (Select d.AuctOrdNo,d.AuctActProdNo,d.ProdPurQty,d.Price,d.SumPrice,a.AuctActNo,a.AuctProdNo from AUCTORDDETL d INNER JOIN AuctActProd a ON d.AuctActProdNo = a.AuctActProdNo AND d.AuctOrdNo=?) ad INNER JOIN AuctProd p ON ad.AuctProdNo=p.AuctProdNo";

	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(AuctOrdDetlVO auctOrdDetl) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, auctOrdDetl.getAuctOrdNo());
			pstmt.setInt(2, auctOrdDetl.getAuctActProdNo());
			pstmt.setInt(3, auctOrdDetl.getProdPurQty());
			pstmt.setInt(4, auctOrdDetl.getPrice());
			pstmt.setInt(5, auctOrdDetl.getSumPrice());

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
	public void update(AuctOrdDetlVO auctOrdDetl) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, auctOrdDetl.getAuctActProdNo());
			pstmt.setInt(2, auctOrdDetl.getProdPurQty());
			pstmt.setInt(3, auctOrdDetl.getAuctOrdNo());
			pstmt.setInt(4, auctOrdDetl.getPrice());
			pstmt.setInt(5, auctOrdDetl.getSumPrice());
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
	public List<AuctOrdDetlVO> findByOrdNo(Integer auctOrdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctOrdDetlVO auctOrdDetlVO = null;
		List<AuctOrdDetlVO> auctOrdDetlList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_ORDNO);
			pstmt.setInt(1, auctOrdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdDetlVO = new AuctOrdDetlVO();
				auctOrdDetlVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdDetlVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctOrdDetlVO.setProdPurQty(rs.getInt("prodPurQty"));
				auctOrdDetlVO.setPrice(rs.getInt("price"));
				auctOrdDetlVO.setSumPrice(rs.getInt("sumPrice"));
				auctOrdDetlList.add(auctOrdDetlVO);
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

		return auctOrdDetlList;
	}
	
	@Override
	public List<AuctOrdDetlVO> findByOrdNo_2(Integer auctOrdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctOrdDetlVO auctOrdDetlVO = null;
		List<AuctOrdDetlVO> auctOrdDetlList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_ORDNO_2);
			pstmt.setInt(1, auctOrdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdDetlVO = new AuctOrdDetlVO();
				auctOrdDetlVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdDetlVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctOrdDetlVO.setProdPurQty(rs.getInt("prodPurQty"));
				auctOrdDetlVO.setPrice(rs.getInt("price"));
				auctOrdDetlVO.setSumPrice(rs.getInt("sumPrice"));
				auctOrdDetlList.add(auctOrdDetlVO);
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

		return auctOrdDetlList;
	}

	
	
	
	
	

	@Override
	public List<AuctOrdDetlVO> getAll() {
		List<AuctOrdDetlVO> auctOrdDetlList = new ArrayList<>();
		AuctOrdDetlVO auctOrdDetlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdDetlVO = new AuctOrdDetlVO();
				auctOrdDetlVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdDetlVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctOrdDetlVO.setProdPurQty(rs.getInt("prodPurQty"));
				auctOrdDetlVO.setPrice(rs.getInt("price"));
				auctOrdDetlVO.setSumPrice(rs.getInt("sumPrice"));

				auctOrdDetlList.add(auctOrdDetlVO);
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
		return auctOrdDetlList;
	}
	
	public AuctOrdDetlVO findByOrdNo_ProdNo(Integer auctOrdNo, Integer auctActProdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctOrdDetlVO auctOrdDetlVO = null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, auctOrdNo);
			pstmt.setInt(2, auctActProdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				auctOrdDetlVO = new AuctOrdDetlVO();
				auctOrdDetlVO.setAuctOrdNo(rs.getInt("auctOrdNo"));
				auctOrdDetlVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctOrdDetlVO.setProdPurQty(rs.getInt("prodPurQty"));
				auctOrdDetlVO.setPrice(rs.getInt("price"));
				auctOrdDetlVO.setSumPrice(rs.getInt("sumPrice"));

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

		return auctOrdDetlVO;
	}
	
	
	


	@Override
	public void addInTransaction(Connection con, Integer auctOrdNo, List<AuctOrdDetlVO> auctOrdDetlList)
			throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);

		for (AuctOrdDetlVO detlVO : auctOrdDetlList) {
			pstmt.setInt(1, auctOrdNo);
			pstmt.setInt(2, detlVO.getAuctActProdNo());
			pstmt.setInt(3, detlVO.getProdPurQty());
			pstmt.setInt(4, detlVO.getPrice());
			pstmt.setInt(5, detlVO.getSumPrice());
			pstmt.addBatch();
		}
		pstmt.executeBatch();

	}
}

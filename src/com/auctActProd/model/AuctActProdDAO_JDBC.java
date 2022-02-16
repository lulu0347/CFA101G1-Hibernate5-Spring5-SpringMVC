package com.auctActProd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auctPic.model.AuctActPicVO;

import DbUtil.BaseDAO;



public class AuctActProdDAO_JDBC extends BaseDAO implements AuctActProdDAO_interface {

	private final String INSERT_STMT = "insert into AUCTACTPROD(AuctActNo,AuctProdNo,AuctProdQty,AuctProdRemain,AuctState,AuctProdPrice) VALUES (?, ?, ?, ?, ?, ?)";
	private final String UPDATE_STMT = "Update AUCTACTPROD set AuctActNo=?,AuctProdNo=?,AuctProdQty=?,AuctProdRemain=?,AuctState=?,AuctProdPrice=? where AuctActProdNo=?";
	private final String FIND_BY_ACTPK = "Select * from AUCTACTPROD where AuctActNo=?"; //拍賣活動中的拍賣商品清單
	private final String FIND_BY_ACTPRODPK = "Select * from AUCTACTPROD where AuctActNo=? and AuctProdNo=?";
	private final String FIND_BY_PRODPK = "Select * from AUCTACTPROD where AuctActProdNo=?";
	private final String GET_ALL = "Select * from AUCTACTPROD order by AuctActProdNo";
	private final String DELETE = "Delete from AUCTACTPROD where AuctActNo=?";

	
	

	static {
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(AuctActProdVO auctActProd) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, auctActProd.getAuctActNo());
			pstmt.setInt(2, auctActProd.getAuctProdNo());
			pstmt.setInt(3, auctActProd.getAuctProdQty());
			pstmt.setInt(4, auctActProd.getAuctProdRemain());
			pstmt.setInt(5, auctActProd.getAuctState());
			pstmt.setInt(6, auctActProd.getAuctProdPrice());

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
	public void update(AuctActProdVO auctActProd) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, auctActProd.getAuctActNo());
			pstmt.setInt(2, auctActProd.getAuctProdNo());
			pstmt.setInt(3, auctActProd.getAuctProdQty());
			pstmt.setInt(4, auctActProd.getAuctProdRemain());
			pstmt.setInt(5, auctActProd.getAuctState());
			pstmt.setInt(6, auctActProd.getAuctProdPrice());
			pstmt.setInt(7, auctActProd.getAuctActProdNo());

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
	public AuctActProdVO findByActProdNo(int auctActProdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctActProdVO  auctActProdVO= null;

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_PRODPK);
			pstmt.setInt(1, auctActProdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				auctActProdVO = new AuctActProdVO();
				auctActProdVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctActProdVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctActProdVO.setAuctProdQty(rs.getInt("auctProdQty"));
				auctActProdVO.setAuctProdRemain(rs.getInt("auctProdRemain"));
				auctActProdVO.setAuctState(rs.getInt("auctState"));
				auctActProdVO.setAuctProdPrice(rs.getInt("auctProdPrice"));
				
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

		return auctActProdVO;
	}
	
	@Override
	public List<AuctActProdVO> findByActNo(int auctActNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctActProdVO  auctActProdVO= null;
		List<AuctActProdVO> auctActProdList =new ArrayList<AuctActProdVO>();

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_ACTPK);
			pstmt.setInt(1, auctActNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				auctActProdVO = new AuctActProdVO();
				auctActProdVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctActProdVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctActProdVO.setAuctProdQty(rs.getInt("auctProdQty"));
				auctActProdVO.setAuctProdRemain(rs.getInt("auctProdRemain"));
				auctActProdVO.setAuctState(rs.getInt("auctState"));
				auctActProdVO.setAuctProdPrice(rs.getInt("auctProdPrice"));
				auctActProdList.add(auctActProdVO);
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

		return auctActProdList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	//用活動編號及商品編號找到拍賣商品
	@Override
	public AuctActProdVO findByActNoProdNo(int auctActNo, int auctProdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctActProdVO  auctActProdVO= null;
		

		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(FIND_BY_ACTPRODPK);
			pstmt.setInt(1, auctActNo);
			pstmt.setInt(2, auctProdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				auctActProdVO = new AuctActProdVO();
				auctActProdVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctActProdVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctActProdVO.setAuctProdQty(rs.getInt("auctProdQty"));
				auctActProdVO.setAuctProdRemain(rs.getInt("auctProdRemain"));
				auctActProdVO.setAuctState(rs.getInt("auctState"));
				auctActProdVO.setAuctProdPrice(rs.getInt("auctProdPrice"));
			
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

		return auctActProdVO;
	}
	
	
	
	
	
	@Override
	public List<AuctActProdVO> getAll() {
		
		List<AuctActProdVO> auctActProdList = new ArrayList<>();
		
		AuctActProdVO auctActProdVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			con=DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt=con.prepareStatement(GET_ALL);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				auctActProdVO =new AuctActProdVO();
				auctActProdVO.setAuctActProdNo(rs.getInt("auctActProdNo"));
				auctActProdVO.setAuctActNo(rs.getInt("auctActNo"));
				auctActProdVO.setAuctProdNo(rs.getInt("auctProdNo"));
				auctActProdVO.setAuctProdQty(rs.getInt("auctProdQty"));
				auctActProdVO.setAuctProdRemain(rs.getInt("auctProdRemain"));
				auctActProdVO.setAuctState(rs.getInt("auctState"));
				auctActProdVO.setAuctProdPrice(rs.getInt("auctProdPrice"));
				
				auctActProdList.add(auctActProdVO);
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
		return auctActProdList;
	}

	@Override
	public void addActCon(Connection con, Integer auctActNo, List<AuctActProdVO> actProdList) throws SQLException {
		
		PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
		for(AuctActProdVO actProdVO : actProdList) {
			
			pstmt.setInt(1, auctActNo);
			pstmt.setInt(2, actProdVO.getAuctProdNo());
			pstmt.setInt(3, actProdVO.getAuctProdQty());
			pstmt.setInt(4, actProdVO.getAuctProdRemain());
			pstmt.setInt(5, actProdVO.getAuctState());
			pstmt.setInt(6, actProdVO.getAuctProdPrice());
			pstmt.addBatch();

		}
		pstmt.executeBatch();
		
	}

	@Override
	public void updateActCon(Connection con, Integer auctActNo, List<AuctActProdVO> actProdList) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
		for(AuctActProdVO actProdVO : actProdList) {
			
			pstmt.setInt(1, auctActNo);
			pstmt.setInt(2, actProdVO.getAuctProdNo());
			pstmt.setInt(3, actProdVO.getAuctProdQty());
			pstmt.setInt(4, actProdVO.getAuctProdRemain());
			pstmt.setInt(5, actProdVO.getAuctState());
			pstmt.setInt(6, actProdVO.getAuctProdPrice());
			pstmt.setInt(7, actProdVO.getAuctActProdNo());
			pstmt.addBatch();
			
		}
		pstmt.executeBatch();
	}

	@Override
	public void delete(Integer auctActProdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, auctActProdNo);

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
}

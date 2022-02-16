package com.used.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.usedpic.model.UsedPicJDBCDAO;
import com.usedpic.model.UsedPicVO;

public class UsedJDBCDAO implements UsedDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Used (kindNo,sellerNo,usedName,usedPrice,usedLaunchedTime,usedProdDescription) VALUES (?, ?, ?, ?, NOW(), ?)";
	private static final String GET_ALL_STMT = "SELECT usedNo,kindNo,buyerNo,sellerNo,transRecNo,usedName,usedPrice,usedState,usedLaunchedTime,soldTime,receiverName,receiverAddress,receiverPhone,usedProdDescription FROM Used order by usedNo";
	private static final String GET_ONE_STMT = "SELECT usedNo,kindNo,buyerNo,sellerNo,transRecNo,usedName,usedPrice,usedState,usedLaunchedTime,soldTime,receiverName,receiverAddress,receiverPhone,usedProdDescription FROM Used WHERE usedNo = ?";
	private static final String UPDATE = "UPDATE Used SET kindNo = ?, buyerNo = ?, sellerNo = ?, transRecNo = ?, usedName = ?, usedPrice = ?, usedState = ?, usedLaunchedTime = ?, soldTime = ?, receiverName = ?, receiverAddress = ?, receiverPhone = ?, usedProdDescription = ? WHERE usedNo = ?";
	private static final String UPDATE_BY_SELLER = "UPDATE Used SET kindNo = ?, usedName = ?, usedPrice = ?, usedState = ?, usedProdDescription = ? WHERE usedNo = ?";
	private static final String GET_All_PROD_BY_BUYER = "SELECT usedNo,kindNo,buyerNo,sellerNo,transRecNo,usedName,usedPrice,usedState,usedLaunchedTime,soldTime,receiverName,receiverAddress,receiverPhone,usedProdDescription FROM Used WHERE buyerNo = ?";
	private static final String GET_All_PROD_BY_SELLER = "SELECT usedNo,kindNo,buyerNo,transRecNo,usedName,usedPrice,usedState,usedLaunchedTime,soldTime,receiverName,receiverAddress,receiverPhone,usedProdDescription FROM Used WHERE sellerNo = ?";
	private static final String GET_ALL_BUYER = "SELECT DISTINCT buyerNo FROM Used order by buyerNo";
	private static final String GET_ALL_SELLER = "SELECT DISTINCT sellerNo FROM Used order by sellerNo";
	private static final String SEARCH = "select * from Used where UsedName like concat('%',?,'%') or UsedProdDescription like concat('%',?,'%')";
	private static final String SEARCH_KIND = "select * from Used where kindNo = ?";

	@Override
	public void insert(UsedVO usedVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, usedVO.getKindNo());
//			pstmt.setInt(2, usedVO.getBuyerNo());
			pstmt.setInt(2, usedVO.getSellerNo());
//			pstmt.setInt(4, usedVO.getTransRecNo());
			pstmt.setString(3, usedVO.getUsedName());
			pstmt.setInt(4, usedVO.getUsedPrice());
//			pstmt.setInt(5, usedVO.getUsedState());
//			pstmt.setDate(5, usedVO.getUsedLaunchedTime());
//			pstmt.setDate(9, usedVO.getSoldTime());
//			pstmt.setString(10, usedVO.getReceiverName());
//			pstmt.setString(11, usedVO.getReceiverAddress());
//			pstmt.setString(12, usedVO.getReceiverPhone());
			pstmt.setString(5, usedVO.getUsedProdDescription());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void insertWithPics(UsedVO usedVO, List<byte[]> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
    		con.setAutoCommit(false);
			String cols[] = {"usedNo"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, usedVO.getKindNo());
			pstmt.setInt(2, usedVO.getSellerNo());
			pstmt.setString(3, usedVO.getUsedName());
			pstmt.setInt(4, usedVO.getUsedPrice());
			pstmt.setString(5, usedVO.getUsedProdDescription());

			pstmt.executeUpdate();
			
			String next_usedNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_usedNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_usedNo +"(剛新增成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			UsedPicJDBCDAO dao = new UsedPicJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (int i = 0; i < list.size(); i++) {
				UsedPicVO usedPicVO = new UsedPicVO();
				usedPicVO.setUsedNo(new Integer(next_usedNo)) ;
				usedPicVO.setUsedPic(list.get(i)) ;
				dao.insert2(usedPicVO,con);
			}

			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增商品編號" + next_usedNo + "時,共有照片" + list.size()
					+ "張同時被新增");
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-used");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update(UsedVO usedVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, usedVO.getKindNo());
			pstmt.setInt(2, usedVO.getBuyerNo());
			pstmt.setInt(3, usedVO.getSellerNo());
			pstmt.setInt(4, usedVO.getTransRecNo());
			pstmt.setString(5, usedVO.getUsedName());
			pstmt.setInt(6, usedVO.getUsedPrice());
			pstmt.setInt(7, usedVO.getUsedState());
			pstmt.setTimestamp(8, usedVO.getUsedLaunchedTime());
			pstmt.setTimestamp(9, usedVO.getSoldTime());
			pstmt.setString(10, usedVO.getReceiverName());
			pstmt.setString(11, usedVO.getReceiverAddress());
			pstmt.setString(12, usedVO.getReceiverPhone());
			pstmt.setString(13, usedVO.getUsedProdDescription());
			pstmt.setInt(14, usedVO.getUsedNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update2(UsedVO usedVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BY_SELLER);
			
			pstmt.setInt(1, usedVO.getKindNo());
			pstmt.setString(2, usedVO.getUsedName());
			pstmt.setInt(3, usedVO.getUsedPrice());
			pstmt.setInt(4, usedVO.getUsedState());
			pstmt.setString(5, usedVO.getUsedProdDescription());
			pstmt.setInt(6, usedVO.getUsedNo());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public UsedVO findByPrimaryKey(Integer usedNo) {

		UsedVO usedVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, usedNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return usedVO;
	}

	@Override
	public List<UsedVO> getAll() {

		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
				list.add(usedVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<UsedVO> getAllProdByBuyer(Integer buyerNo) {

		UsedVO usedVO = null;
		List<UsedVO> list = new ArrayList<UsedVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_All_PROD_BY_BUYER);
			pstmt.setInt(1, buyerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
				list.add(usedVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<UsedVO> getAllProdBySeller(Integer sellerNo) {
		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_All_PROD_BY_SELLER);
			pstmt.setInt(1, sellerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
				list.add(usedVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<UsedVO> getAllBuyer() {

		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BUYER);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				if (usedVO.getBuyerNo() != 0) {
					list.add(usedVO);
				}
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<UsedVO> getAllSeller() {
		
		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SELLER);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				usedVO = new UsedVO();
				
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				if (usedVO.getSellerNo() != 0) {
					list.add(usedVO);
				}
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<UsedVO> search(String name) {
		
		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				usedVO = new UsedVO();

				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
				list.add(usedVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<UsedVO> searchKind(Integer kindNo) {
		
		List<UsedVO> list = new ArrayList<UsedVO>();
		UsedVO usedVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_KIND);
			pstmt.setInt(1, kindNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				usedVO = new UsedVO();
				
				usedVO.setUsedNo(rs.getInt("usedNo"));
				usedVO.setKindNo(rs.getInt("kindNo"));
				usedVO.setBuyerNo(rs.getInt("buyerNo"));
				usedVO.setSellerNo(rs.getInt("sellerNo"));
				usedVO.setTransRecNo(rs.getInt("transRecNo"));
				usedVO.setUsedName(rs.getString("usedName"));
				usedVO.setUsedPrice(rs.getInt("usedPrice"));
				usedVO.setUsedState(rs.getInt("usedState"));
				usedVO.setUsedLaunchedTime(rs.getTimestamp("usedLaunchedTime"));
				usedVO.setSoldTime(rs.getTimestamp("soldTime"));
				usedVO.setReceiverName(rs.getString("receiverName"));
				usedVO.setReceiverAddress(rs.getString("receiverAddress"));
				usedVO.setReceiverPhone(rs.getString("receiverPhone"));
				usedVO.setUsedProdDescription(rs.getString("usedProdDescription"));
				list.add(usedVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		UsedJDBCDAO dao = new UsedJDBCDAO();
		List<UsedVO> list = new ArrayList<UsedVO>();
		list = dao.search("二手");
		for (UsedVO aUsed : list) {
		System.out.print(aUsed.getUsedNo() + ",");
		System.out.print(aUsed.getKindNo() + ",");
		System.out.print(aUsed.getBuyerNo() + ",");
		System.out.print(aUsed.getSellerNo() + ",");
		System.out.print(aUsed.getTransRecNo() + ",");
		System.out.print(aUsed.getUsedName() + ",");
		System.out.print(aUsed.getUsedPrice() + ",");
		System.out.print(aUsed.getUsedState() + ",");
		System.out.print(aUsed.getUsedLaunchedTime() + ",");
		System.out.print(aUsed.getSoldTime() + ",");
		System.out.print(aUsed.getReceiverName() + ",");
		System.out.print(aUsed.getReceiverAddress() + ",");
		System.out.print(aUsed.getReceiverPhone() + ",");
		System.out.print(aUsed.getUsedProdDescription());
		System.out.println();
	}
//		//新增
//		UsedVO usedVO1 = new UsedVO();
//		long millis = System.currentTimeMillis();
//		java.util.Date date = new java.util.Date(millis);
//		java.sql.Date sdate = new java.sql.Date(date.getTime());
//		List<byte[]> list = null;
//		File file1 = new File("logo.png");
//		byte[] a;
//		try {
//			a = Files.readAllBytes(file1.toPath());
//			list.add(a);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		File file2 = new File("logo1.png");
//		byte[] b;
//		try {
//			b = Files.readAllBytes(file2.toPath());
//			list.add(b);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		usedVO1.setKindNo(2);
//		usedVO1.setBuyerNo(2000);
//		usedVO1.setSellerNo(11007);
//		usedVO1.setTransRecNo(3000);
//		usedVO1.setUsedName("商品名");
//		usedVO1.setUsedPrice(4000);
//		usedVO1.setUsedState(1);
//		usedVO1.setUsedLaunchedTime(sdate);
//		usedVO1.setSoldTime(java.sql.Date.valueOf("2020-02-02"));
//		usedVO1.setReceiverName("收件人");
//		usedVO1.setReceiverAddress("收件地址");
//		usedVO1.setReceiverPhone("0900000000");
//		usedVO1.setUsedProdDescription("商品描述");
//		dao.insertWithPics(usedVO1, list);
////		
////		//修改
//		UsedVO usedVO2 = new UsedVO();
//		usedVO2.setUsedNo(1001);
//		usedVO2.setKindNo(1000);
//		usedVO2.setBuyerNo(2000);
//		usedVO2.setSellerNo(2000);
//		usedVO2.setTransRecNo(3000);
//		usedVO2.setUsedName("商品名");
//		usedVO2.setUsedPrice(4000);
//		usedVO2.setUsedState(1);
//		usedVO2.setUsedLaunchedTime(java.sql.Date.valueOf("2020-01-01"));
//		usedVO2.setSoldTime(java.sql.Date.valueOf("2020-02-02"));
//		usedVO2.setReceiverName("收件人");
//		usedVO2.setReceiverAddress("收件地址");
//		usedVO2.setReceiverPhone("0900000022");
//		usedVO2.setUsedProdDescription("商品描述");
//		dao.update(usedVO2);

//		//查詢一筆
//		UsedVO usedVO3 = dao.findByPrimaryKey(30001);
//		System.out.print(usedVO3.getUsedNo() + ",");
//		System.out.print(usedVO3.getKindNo() + ",");
//		System.out.print(usedVO3.getBuyerNo() + ",");
//		System.out.print(usedVO3.getSellerNo() + ",");
//		System.out.print(usedVO3.getTransRecNo() + ",");
//		System.out.print(usedVO3.getUsedName() + ",");
//		System.out.print(usedVO3.getUsedPrice() + ",");
//		System.out.print(usedVO3.getUsedState() + ",");
//		System.out.print(usedVO3.getUsedLaunchedTime() + ",");
//		System.out.print(usedVO3.getSoldTime() + ",");
//		System.out.print(usedVO3.getReceiverName() + ",");
//		System.out.print(usedVO3.getReceiverAddress() + ",");
//		System.out.print(usedVO3.getReceiverPhone() + ",");
//		System.out.print(usedVO3.getUsedProdDescription());
//		System.out.print("-------------------------------");
//
//		//查詢全部
//		List<UsedVO> list = dao.getAllProdByBuyer(11001);
//		for (UsedVO aUsed : list) {
//			System.out.print(aUsed.getUsedNo() + ",");
//			System.out.print(aUsed.getKindNo() + ",");
//			System.out.print(aUsed.getBuyerNo() + ",");
//			System.out.print(aUsed.getSellerNo() + ",");
//			System.out.print(aUsed.getTransRecNo() + ",");
//			System.out.print(aUsed.getUsedName() + ",");
//			System.out.print(aUsed.getUsedPrice() + ",");
//			System.out.print(aUsed.getUsedState() + ",");
//			System.out.print(aUsed.getUsedLaunchedTime() + ",");
//			System.out.print(aUsed.getSoldTime() + ",");
//			System.out.print(aUsed.getReceiverName() + ",");
//			System.out.print(aUsed.getReceiverAddress() + ",");
//			System.out.print(aUsed.getReceiverPhone() + ",");
//			System.out.print(aUsed.getUsedProdDescription());
//			System.out.println();
//		}
//
//		List<UsedVO> list = dao.getAllBuyer();
//		for (UsedVO aUsed : list) {
//
//			System.out.print(aUsed.getBuyerNo() + ",");
//
//			System.out.println();
//		}
	}



}

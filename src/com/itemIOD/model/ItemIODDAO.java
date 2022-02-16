package com.itemIOD.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ItemIODDAO implements ItemIODDAO_interface{

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//----測試join後給會員取得訂單資料的結果-------------
	private static final String GET_BYMEMIDFORORDER_STMT = "select * from itemorder as ito inner join itemdetail as itd on ito.orderno = itd.orderno inner join item as it on itd.itemno = it.itemno where ito.memno = ? Order BY ito.orderno";
	//----測試join後給會員取得明細資料的結果-------------
	private static final String GET_BYORDERNOFORDETAIL_STMT = "select * from itemorder as ito inner join itemdetail as itd on ito.orderno = itd.orderno inner join item as it on itd.itemno = it.itemno where ito.orderno = ? Order BY ito.orderno";
	//----(前台)會員取消訂單-------------
	private static final String CancelOrder_STMT = "UPDATE itemorder SET orderstate = 4 WHERE orderNo = ?";
	//----(前台)會員訂單退貨-------------
	private static final String ReturnOrder_STMT = "UPDATE itemorder SET orderstate = 3 WHERE orderNo = ?";
	//----(前台)會員訂單收貨-------------
	private static final String ReceiptOrder_STMT = "UPDATE itemorder SET orderstate = 2 WHERE orderNo = ?";
	//----(後台)訂單出貨-------------
	private static final String ShippedOrder_STMT = "UPDATE itemorder SET orderstate = 1 WHERE orderNo = ?";
	
	@Override
	public List<ItemIODVO> getOrderByMemNo(Integer MemNo) {
		
		List<ItemIODVO> list = new ArrayList<ItemIODVO>();
		ItemIODVO itemIODVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMEMIDFORORDER_STMT);
			
			pstmt.setInt(1, MemNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				itemIODVO = new ItemIODVO();
				
				itemIODVO.setOrderNo(rs.getInt("orderNo"));
				itemIODVO.setTranTime(rs.getTimestamp("tranTime"));
				itemIODVO.setOrderTotal(rs.getInt("orderTotal"));
				itemIODVO.setOrderState(rs.getInt("orderState"));
				itemIODVO.setItemSales(rs.getInt("itemSales"));
				itemIODVO.setItemNo(rs.getInt("itemNo"));
				itemIODVO.setItemPrice(rs.getInt("itemPrice"));
				itemIODVO.setItemName(rs.getString("itemName"));
				itemIODVO.setWarrantyDate(rs.getDouble("warrantyDate"));
				
				list.add(itemIODVO);
			}
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
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
	public List<ItemIODVO> getDetailByOrderNo(Integer orderNo) {
		List<ItemIODVO> list = new ArrayList<ItemIODVO>();
		ItemIODVO itemIODVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYORDERNOFORDETAIL_STMT);
			
			pstmt.setInt(1, orderNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				itemIODVO = new ItemIODVO();
				
				itemIODVO.setTranTime(rs.getTimestamp("tranTime"));
				itemIODVO.setOrderTotal(rs.getInt("orderTotal"));
				itemIODVO.setOrderState(rs.getInt("orderState"));
				itemIODVO.setItemSales(rs.getInt("itemSales"));
				itemIODVO.setItemNo(rs.getInt("itemNo"));
				itemIODVO.setItemPrice(rs.getInt("itemPrice"));
				itemIODVO.setItemName(rs.getString("itemName"));
				itemIODVO.setWarrantyDate(rs.getDouble("warrantyDate"));
				
				list.add(itemIODVO);
			}
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. " + se.getMessage());
		// Clean up JDBC resources
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
	public void CancelOrder(Integer orderNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CancelOrder_STMT);
			
			pstmt.setInt(1, orderNo);
			System.out.println(orderNo);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void ReturnOrder(Integer orderNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ReturnOrder_STMT);
			
			pstmt.setInt(1, orderNo);
			System.out.println(orderNo);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void ReceiptOrder(Integer orderNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ReceiptOrder_STMT);
			
			pstmt.setInt(1, orderNo);
			System.out.println(orderNo);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		public void ShippedOrder(Integer orderNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(ShippedOrder_STMT);
				
				pstmt.setInt(1, orderNo);
				System.out.println(orderNo);
				pstmt.executeUpdate();
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
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
	

	

}

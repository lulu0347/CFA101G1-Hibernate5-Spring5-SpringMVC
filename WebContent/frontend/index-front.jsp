<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject"%>
<%
//載入JDBC驅動程式
String user = "David";
String password = "123456";
String dbname = "CFA101G1";
String dsn = "jdbc:mysql://localhost/" + dbname + "?serverTimezone=Asia/Taipei";

Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection(dsn, user, password); 
  
//取得前端送來的資料 
	Integer bidProdNo = Integer.parseInt(request.getParameter("bidProdNo"));
	// int empno = 7001;
//建立PreparedStatement物件 
	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CFA101G1.Bid WHERE BidProdNo = ?");
//代入資料    
 	stmt.setInt(1, bidProdNo);
//執行PreparedStatement
 	ResultSet rs = stmt.executeQuery();

//將資料放入JSONObject
 	JSONObject bid = new JSONObject();
  
//取回一筆資料	
 	if (rs.next()) {
		bid.put("bidProdNo", rs.getInt("bidProdNo"));
		bid.put("ename", rs.getString("ename"));
		bid.put("hiredate", rs.getString("hiredate"));
		bid.put("sal", rs.getInt("sal"));
 	}	 
 	
//輸出JSONObject
	out.print(bid);
//關閉ResultSet物件
	
	if (rs != null) {
		rs.close();
	}

//關閉Statement物件
	if (stmt != null) {
		stmt.close();
	}
//關閉Connection物件
	if (conn != null) {
		conn.close();
	}

%>



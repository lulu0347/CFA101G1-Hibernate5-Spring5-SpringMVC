<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.*"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*,java.text.SimpleDateFormat"  %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Calendar" %>
<%@page import="com.member.model.*" %>
<%@page import="com.transRec.model.*" %>
<%
//載入JDBC驅動程式
request.setCharacterEncoding("UTF-8");
String user = "cfa101g1";
String password = "cfa101g1";
String dbname = "CFA101G1";
String dsn = "jdbc:mysql://localhost/" + dbname + "?serverTimezone=Asia/Taipei";

Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection(dsn, user, password); 
TransRecService transRecSvc = new TransRecService();
MemberVO memVO = (MemberVO) session.getAttribute("user");
Integer memNo = null;
try {
	memNo = memVO.getMemNo();
} catch (NullPointerException e) {
	memNo = -1;
}
String memAccount = null;
try {
	memAccount = memVO.getMemAccount();
} catch (NullPointerException e) {
	memAccount = "";
}
String memName = null;
try {
	memName = memVO.getMemName();
} catch (NullPointerException e) {
	memName = "";
}
Integer ECash = null;
try {
	ECash = transRecSvc.getDeposit(memNo);
} catch (NullPointerException e) {
	ECash = -1;
} catch (Exception e) {
	ECash = -1;
}

//取得前端送來的資料 
Integer kindNo = null;
try {
	kindNo = new Integer(request.getParameter("kindNo"));
} catch (Exception e) {
	e.getMessage();
	kindNo = 0;
}

String searchKeyword = "bbbb";
try {
	searchKeyword = request.getParameter("searchKeyword");
} catch (NullPointerException e) {
	e.getMessage();
	searchKeyword = "";
}

//建立PreparedStatement物件 
PreparedStatement stmt = null;
if (kindNo == 0 && "null".equals(searchKeyword)) {
	System.out.println("route1");
	System.out.println("kindNo="+kindNo+",searchKeyword="+searchKeyword);
	stmt = conn.prepareStatement
			("SELECT b1.BidProdNo, BidProdPicNo, BidProdPicContent, KindNo, BidProdName, BidProdDescription, BidProdStartPrice, BidState, BidProdStartTime, BidProdEndTime, BidProdState FROM Bid b1 LEFT JOIN BidPic b2 ON b2.BidProdNo = b1.BidProdNo WHERE (BidProdStartTime <= NOW()) AND (b1.BidState = 0 OR DATE_ADD(b1.BidProdEndTime, INTERVAL 30 MINUTE) > NOW()) ORDER BY ABS(b1.BidProdEndTime - NOW())");	
} else if (kindNo != 0 && "null".equals(searchKeyword)) {
	System.out.println("route2");
	System.out.println("kindNo="+kindNo+",searchKeyword="+searchKeyword);
	stmt = conn.prepareStatement
			("SELECT b1.BidProdNo, BidProdPicNo, BidProdPicContent, KindNo, BidProdName, BidProdDescription, BidProdStartPrice, BidState, BidProdStartTime, BidProdEndTime, BidProdState FROM Bid b1 LEFT JOIN BidPic b2 ON b2.BidProdNo = b1.BidProdNo WHERE (BidProdStartTime <= NOW()) AND (b1.BidState = 0 OR DATE_ADD(b1.BidProdEndTime, INTERVAL 30 MINUTE) > NOW()) AND b1.kindNo = ? ORDER BY ABS(b1.BidProdEndTime - NOW())");
	stmt.setInt(1, kindNo);
} else if (kindNo == 0 && !("null".equals(searchKeyword))) {
	System.out.println("route3");
	System.out.println("kindNo="+kindNo+",searchKeyword="+searchKeyword);
	stmt = conn.prepareStatement("SELECT b1.BidProdNo, BidProdPicNo, BidProdPicContent, KindNo, BidProdName, BidProdDescription, BidProdStartPrice, BidState, BidProdStartTime, BidProdEndTime, BidProdState FROM Bid b1 LEFT JOIN BidPic b2 ON b2.BidProdNo = b1.BidProdNo WHERE (BidProdStartTime <= NOW()) AND (b1.BidState = 0 OR DATE_ADD(b1.BidProdEndTime, INTERVAL 30 MINUTE) > NOW()) AND (b1.bidProdName LIKE ? OR b1.bidProdDescription LIKE ?) ORDER BY ABS(b1.BidProdEndTime - NOW())");
	stmt.setString(1, "%"+searchKeyword+"%");
	stmt.setString(2, "%"+searchKeyword+"%");
} else if (kindNo != 0 && !("null".equals(searchKeyword))) {
	System.out.println("route4");
	System.out.println("kindNo="+kindNo+",searchKeyword="+searchKeyword);
	stmt = conn.prepareStatement("SELECT b1.BidProdNo, BidProdPicNo, BidProdPicContent, KindNo, BidProdName, BidProdDescription, BidProdStartPrice, BidState, BidProdStartTime, BidProdEndTime, BidProdState FROM Bid b1 LEFT JOIN BidPic b2 ON b2.BidProdNo = b1.BidProdNo WHERE (BidProdStartTime <= NOW()) AND (b1.BidState = 0 OR DATE_ADD(b1.BidProdEndTime, INTERVAL 30 MINUTE) > NOW()) AND b1.kindNo = ? AND (b1.bidProdName LIKE ? OR b1.bidProdDescription LIKE ?) ORDER BY ABS(b1.BidProdEndTime - NOW())");
	stmt.setInt(1, kindNo);
	stmt.setString(2, "%"+searchKeyword+"%");
	stmt.setString(3, "%"+searchKeyword+"%");
}


//代入資料
	
//執行PreparedStatement
 	ResultSet rs = stmt.executeQuery();

//將資料放入JSONObject
//  	JSONObject bidObj = new JSONObject();
	JSONObject json = new JSONObject();
 	JSONArray bidObjArr = new JSONArray();
 	JSONObject member = new JSONObject();
 	member.put("memNo", memNo);
 	member.put("memAccount", memAccount);
 	member.put("memName", memName);
 	member.put("ECash", ECash);
 	
 	
 	ArrayList<Integer> referenceList = new ArrayList<Integer>();
  
//取回一筆資料

	int i = 0;
	
	 	while (rs.next()) {
	 		if (!referenceList.contains(rs.getInt("bidProdNo"))) {
		 		referenceList.add(i, rs.getInt("bidProdNo"));
		 		JSONObject bidObj = new JSONObject();
		 		bidObj.put("bidProdNo", rs.getInt("bidProdNo"));
		 		bidObj.put("bidProdPicNo", rs.getInt("bidProdPicNo"));
				bidObj.put("bidProdName", rs.getString("bidProdName"));
				bidObj.put("bidProdDescription", rs.getString("bidProdDescription"));
				bidObj.put("bidState", rs.getInt("bidState"));
				bidObj.put("bidProdStartTime", Long.toString(rs.getTimestamp("bidProdStartTime").getTime()));
				bidObj.put("bidProdEndTime", Long.toString(rs.getTimestamp("bidProdEndTime").getTime()));
				if (rs.getTimestamp("bidProdStartTime").getTime() > System.currentTimeMillis()) {
					bidObj.put("bidProdStart", 0);
					bidObj.put("bidProdEnd", 0);
					bidObj.put("bidLeftTime", ( rs.getTimestamp("bidProdStartTime").getTime() - System.currentTimeMillis() ) );
				} else if (rs.getTimestamp("bidProdStartTime").getTime() <= System.currentTimeMillis() && rs.getTimestamp("bidProdEndTime").getTime() > System.currentTimeMillis()) {
					bidObj.put("bidProdStart", 1);
					bidObj.put("bidProdEnd", 0);
					bidObj.put("bidLeftTime", ( rs.getTimestamp("bidProdEndTime").getTime() - System.currentTimeMillis() ) );
				} else {
					bidObj.put("bidProdStart", 1);
					bidObj.put("bidProdEnd", 1);
					bidObj.put("bidLeftTime", ( rs.getTimestamp("bidProdEndTime").getTime() - System.currentTimeMillis() ) );
				}
// 				bidObj.put("bidProdLeftTime", (rs.getTimestamp("bidProdEndTime").getTime() - System.currentTimeMillis()));
				bidObjArr.put(bidObj);
	 		}
	 	}
	 	
	 	
	 	
	 	json.put("bidProduct", bidObjArr);
	 	json.put("member", member);
	 	out.print(json.toString());
	 	out.flush();
// 	 	out.close();

 	
//輸出JSONObject
// 	out.print(bidObj);
// 	out.print(bidObjArr);
//關閉ResultSet物件
	if (rs != null)
	rs.close();
//關閉Statement物件
	if (stmt != null)
	stmt.close();

//關閉Connection物件
	if (conn != null)
 	conn.close();
%>
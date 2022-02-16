<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.*"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Calendar" %>
<%@page import="com.member.model.*" %>
<%@page import="com.transRec.model.*" %>
<%
//載入JDBC驅動程式
String user = "cfa101g1";
String password = "cfa101g1";
String dbname = "CFA101G1";
String dsn = "jdbc:mysql://localhost/" + dbname + "?serverTimezone=Asia/Taipei";

Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection(dsn, user, password); 
  
//取得前端送來的資料
MemberVO memVO = (MemberVO) session.getAttribute("user");
TransRecService transRecSvc = new TransRecService();
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
} catch (NullPointerException ne) {
	ECash = -1;
} catch (Exception e) {
	ECash = -1;
}
// PreparedStatement stmt = conn.prepareStatement("SELECT MemNo, MemAccount, MemName, ECash FROM MEM WHERE MemNo = ?");
// stmt.setInt()
JSONObject member = new JSONObject();
member.put("memNo", memNo);
member.put("memAccount", memAccount);
member.put("memName", memName);
member.put("ECash", ECash);

Integer bidProdNo = null;
try {
	bidProdNo = new Integer(request.getParameter("bidProdNo"));
} catch (NumberFormatException e) {
	bidProdNo = -1;
	e.getMessage();
}
JSONObject bidAll = new JSONObject();
JSONArray bidProdObjArr = new JSONArray();
JSONArray bidRecordObjArr = new JSONArray();
PreparedStatement stmt = conn.prepareStatement("SELECT b1.BidProdNo, b1.KindNo, b1.BidProdName, b1.BidProdDescription, b1.BidProdStartPrice, b1.BidWinnerNo, b1.BidWinnerPrice, b1.BidPriceIncrement, b1.BidState, b1.BidProdStartTime, b1.BidProdEndTime, b1.BidProdState FROM Bid b1 WHERE b1.bidProdNo = ?");
stmt.setInt(1, bidProdNo);
ResultSet rs = stmt.executeQuery();

while (rs.next()) {
	JSONObject bidProdObj = new JSONObject();
	bidProdObj.put("bidProdNo", rs.getInt("bidProdNo"));
	bidProdObj.put("kindNo", rs.getInt("kindNo"));
	bidProdObj.put("bidProdName", rs.getString("bidProdName"));
	bidProdObj.put("bidProdDescription", rs.getString("bidProdDescription"));
	bidProdObj.put("bidProdStartPrice", rs.getInt("bidProdStartPrice"));
	bidProdObj.put("bidWinnerNo", rs.getInt("bidWinnerNo"));
	bidProdObj.put("bidWinnerPrice", rs.getInt("bidWinnerPrice"));
	bidProdObj.put("bidPriceIncrement", rs.getInt("bidPriceIncrement"));
	bidProdObj.put("bidState", rs.getInt("bidState"));
	bidProdObj.put("bidProdStartTime", rs.getTimestamp("bidProdStartTime"));
	bidProdObj.put("bidProdEndTime", rs.getTimestamp("bidProdEndTime"));
	bidProdObj.put("bidProdStartTimeUnix", rs.getTimestamp("bidProdStartTime").getTime());
	bidProdObj.put("bidProdEndTimeUnix", rs.getTimestamp("bidProdEndTime").getTime());
	bidProdObj.put("bidProdState", rs.getInt("bidProdState"));
	bidAll.put("bidProduct", bidProdObj);
}

stmt = conn.prepareStatement("SELECT b3.BidProdNo, b3.BidRecordNo, b3.MemNo, b3.BidPrice, b3.BidTime FROM BidRecord b3 LEFT JOIN Bid b1 ON b1.BidProdNo = b3.BidProdNo WHERE b3.BidProdNo = ? ORDER BY BidRecordNo");
stmt.setInt(1, bidProdNo);

//執行PreparedStatement
 	rs = stmt.executeQuery();

//將資料放入JSONObject
//  	JSONObject bidObj = new JSONObject();
 	
 	ArrayList<Integer> referenceList = new ArrayList<Integer>();
  
//取回一筆資料
int columns = rs.getMetaData().getColumnCount();


		int i = 0;
	
	 	while (rs.next()) {
// 	 		if (!referenceList.contains(rs.getInt("bidProdNo"))) {
// 		 		referenceList.add(i, rs.getInt("bidProdNo"));
		 		JSONObject bidRecordObj = new JSONObject();
		 		bidRecordObj.put("bidRecordNo", rs.getInt("bidRecordNo"));
		 		bidRecordObj.put("memNo", rs.getInt("memNo"));
				bidRecordObj.put("bidPrice", rs.getInt("bidPrice"));
				bidRecordObj.put("bidTime", rs.getTimestamp("bidTime"));
				bidRecordObj.put("bidTimeUnix", rs.getTimestamp("bidTime").getTime());
				
				bidRecordObjArr.put(bidRecordObj);
				i++;
// 	 		}
	 	}
	 	bidAll.put("bidRecord", bidRecordObjArr);
	 	
	 	JSONArray bidProdPicNoArr = new JSONArray();
	 	stmt = conn.prepareStatement("SELECT * FROM BidPic b2 WHERE b2.BidProdNo = ? ORDER BY b2.BidProdNo");
	 	stmt.setInt(1, bidProdNo);
	 	rs = stmt.executeQuery();
		JSONObject bidProdPicNoObj = new JSONObject();
	 	while (rs.next()) {
	 		bidProdPicNoArr.put(rs.getInt("bidProdPicNo"));
	 	}
	 	bidAll.put("bidProdPicNo",bidProdPicNoArr);
// 	 	bidProdObjArr.put(bidProdPicNoObj);
// bidAll.put("bidProdPicNoObj", bidProdPicNoObj);
	 	bidAll.put("member", member);
	 	
	 	out.print(bidAll.toString());
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
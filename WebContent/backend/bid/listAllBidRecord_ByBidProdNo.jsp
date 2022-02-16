<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%@ page import="com.bidrecord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
request.getAttribute("bidProdNo");
	Integer bidProdNo = null;
	try {
		bidProdNo = new Integer(request.getParameter("bidProdNo"));
	} catch (Exception e) {
		e.getMessage();
	}
	if (bidProdNo == null) {
		try {
			bidProdNo = (Integer) request.getAttribute("bidProdNo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    BidRecordService bidRecordSvc = new BidRecordService();
    List<BidRecordVO> list = bidRecordSvc.findByBidProdNo(bidProdNo);
    pageContext.setAttribute("list",list);
    request.setAttribute("bidProdNo", bidProdNo);
%>


<html>
<head>
<title>競標出價資料 - listAllBidRecord.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size: 24px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .showPanel tr:nth-child(even){
  	background-color:#e8e8e8;
  }
  .showPanel tr:nth-child(odd){
  	background-color:#ccc;
  }
  .showPanel tr:nth-child(1){
  	background-color:#7788aa;color:#ffffff;
  }
</style>

</head>
<body bgcolor='white'>

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>競標出價資料 - listAllBidRecord.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/bid.jsp"><img src="<%=request.getContextPath()%>/resources/icon/logo.png" width="32px" height="32px" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="showPanel">
	<tr>
		<th>商品編號</th>
		<th>出價編號</th>
		<th>出價會員</th>
		<th>出價金額</th>
		<th>出價時間</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page3.file" %> 
	<c:if test="${list == null}">
	<tr>
		<td colspan="5">
		目前尚無資料!
		</td>
	</tr>
	</c:if>
	<c:forEach var="bidRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${bidRecordVO.bidProdNo}</td>
			<td>${bidRecordVO.bidRecordNo}</td>
			<td>
				${bidRecordVO.memNo}
			</td>
			<td>${bidRecordVO.bidPrice}</td>
			<td><fmt:formatDate value="${bidRecordVO.bidTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
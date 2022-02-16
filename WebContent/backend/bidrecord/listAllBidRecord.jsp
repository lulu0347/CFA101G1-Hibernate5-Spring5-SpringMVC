<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidrecord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    BidRecordService bidRecordSvc = new BidRecordService();
    List<BidRecordVO> list = bidRecordSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有競標出價資料 - listAllBidRecord.jsp</title>

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
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size: 13px;
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
		 <h3>所有競標商品資料 - listAllBidRecord.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/bid.jsp"><img src="<%=request.getContextPath()%>/resources/images/rollingCat.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>商品名稱</th>
		<th>商品類別</th>
		<th>商品敘述</th>
		<th>起標價</th>
		<th>最低增額</th>
		<th>競標狀態</th>
		<th>起標時間</th>
		<th>截標時間</th>
		<th>目前最高價</th>
		<th>最高價會員編號</th>
		<th>收件人姓名</th>
		<th>收件人地址</th>
		<th>收件人電話</th>
		<th>錢包交易編號</th>
		<th>商品狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="bidVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${bidVO.bidProdNo}</td>
			<td>${bidVO.bidProdName}</td>
			<td>${bidVO.kindNo}</td>
			<td>${bidVO.bidProdDescription}</td>
			<td>${bidVO.bidProdStartPrice}</td>
			<td>${bidVO.bidPriceIncrement}</td>
			<td>
				<c:if test="${bidVO.getBidState() == 0}" var="condition">
					<c:out value="0<br>未結束" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidState() == 1}" var="condition">
					<c:out value="1<br>截標" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidState() == 2}" var="condition">
					<c:out value="2<br>流標" escapeXml="false"></c:out>
				</c:if>
			</td>
			<td><fmt:formatDate value="${bidVO.bidProdStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${bidVO.bidProdEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${bidVO.bidWinnerPrice}</td>
			<td>${bidVO.bidWinnerNo}</td> 
			<td>${bidVO.receiverName}</td>
			<td>${bidVO.receiverAddress}</td>
			<td>${bidVO.receiverPhone}</td>
			<td>${bidVO.transRecNo}</td>
			<td>
				<c:if test="${bidVO.getBidProdState() == 0}">
					<c:out value="0<br>未出貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 1}">
					<c:out value="1<br>已出貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 2}">
					<c:out value="2<br>已收貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 3}">
					<c:out value="3<br>退貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 4}">
					<c:out value="4<br>棄標" escapeXml="false"></c:out>
				</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    BidService bidSvc = new BidService();
    List<BidVO> list = bidSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有競標商品資料 - listAllBid.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/bid/css/bidBackend.css"/>

</head>
<body bgcolor='white'>

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>所有競標商品資料 - listAllBid.jsp</h3>
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
		<th>商品名稱</th>
		<th>商品類別</th>
		<th>商品敘述</th>
		<th>起標價</th>
		<th>最低增額</th>
		<th>競標狀態</th>
		<th>起標時間</th>
		<th>截標時間</th>
		<th>得標價</th>
		<th>得標價會員編號</th>
		<th>收件人姓名</th>
		<th>收件人地址</th>
		<th>收件人電話</th>
		<th>錢包交易編號</th>
		<th>商品狀態</th>
		<th>出價紀錄</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="bidVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${bidVO.bidProdNo}</td>
			<td>${bidVO.bidProdName}</td>
			<td>
				<c:if test="${bidVO.kindNo == 1}" var="kind">
					<c:out value="1<br>手機" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 2}" var="kind">
					<c:out value="2<br>電腦" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 3}" var="kind">
					<c:out value="3<br>手錶" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 4}" var="kind">
					<c:out value="4<br>相機" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 5}" var="kind">
					<c:out value="5<br>其他" escapeXml="false"></c:out>
				</c:if>
			</td>
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
					<c:out value="0<br>未結帳" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 1}">
					<c:out value="1<br>訂單處理中" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 2}">
					<c:out value="2<br>已出貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 3}">
					<c:out value="3<br>已收貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 4}">
					<c:out value="4<br>退貨" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.getBidProdState() == 5}">
					<c:out value="5<br>棄標" escapeXml="false"></c:out>
				</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidrecord/bidrecord2.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看">
			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}">
			     <input type="hidden" name="action"	value="getOne_For_Display_By_BidProdNo"></FORM>
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
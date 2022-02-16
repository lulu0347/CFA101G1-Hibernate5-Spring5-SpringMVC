<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  BidVO bidVO = (BidVO) request.getAttribute("bidVO"); //BidServlet.java(Controller), 存入req的bidVO物件
  BidPicService bidPicSvc = new BidPicService();
  List<BidPicVO> list = bidPicSvc.getAllBidPic_bidProdNo(bidVO.getBidProdNo());
  pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>競標商品資料 - listOneBid.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/bid/css/bidBackend.css"/>


</head>
<body bgcolor='white'>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>競標商品資料 - ListOneBid.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/bid.jsp"><img src="<%=request.getContextPath()%>/resources/icon/logo.png" width="24" height="24" border="0">回首頁</a> || <a href="<%=request.getContextPath()%>/backend/bid/listAllBid.jsp">回競標商品列表</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>競標商品編號</th>
		<th>競標商品名稱</th>
		<th>商品類別</th>
		<th>商品敘述</th>
		<th>起標價</th>
		<th>最低增額</th>
		<th>競標狀態</th>
		<th>起標時間</th>
		<th>截標時間</th>
		<th>得標價</th>
		<th>得標會員編號</th>
		<th>收件人姓名</th>
		<th>收件人地址</th>
		<th>收件人電話</th>
		<th>錢包交易編號</th>
		<th>競標商品狀態</th>

	</tr>
	<tr>
		<td><%=bidVO.getBidProdNo()%></td>
		<td><%=bidVO.getBidProdName()%></td>
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
		<td><%=bidVO.getBidProdDescription()%></td>
		<td><%=bidVO.getBidProdStartPrice()%></td>
		<td><%=bidVO.getBidPriceIncrement()%></td>
		<td>
			<c:if test="${bidVO.getBidState() == 0}" var="condition">
				<c:out value="未結束"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidState() == 1}" var="condition">
				<c:out value="截標"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidState() == 2}" var="condition">
				<c:out value="流標"></c:out>
			</c:if>
		</td>
		<td><%=bidVO.getBidProdStartTime()%></td>
		<td><%=bidVO.getBidProdEndTime()%></td>
		<td>${bidVO.getBidWinnerPrice()}</td>
        <td>${bidVO.getBidWinnerNo()}</td>
		<td>${bidVO.getReceiverName()}</td>
		<td>${bidVO.getReceiverAddress()}</td>
		<td>${bidVO.getReceiverPhone()}</td>
        <td>${bidVO.getTransRecNo()}</td>
		<td>
			<c:if test="${bidVO.getBidProdState() == 0}">
				<c:out value="未結帳"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidProdState() == 1}">
				<c:out value="訂單處理中"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidProdState() == 2}">
				<c:out value="已出貨"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidProdState() == 3}">
				<c:out value="已收貨"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidProdState() == 4}">
				<c:out value="退貨"></c:out>
			</c:if>
			<c:if test="${bidVO.getBidProdState() == 5}">
				<c:out value="棄標"></c:out>
			</c:if>
		</td>
<!-- 		<td> -->
<%-- 			<c:forEach var="bidPicVO" items="${list}"> --%>
<%-- 				<img src="<%=request.getContextPath()%>/bidpic/DBGifReader4?bidProdPicNo=${bidPicVO.getBidProdPicNo()}" height="32px" width="32px"> --%>
<%-- 			</c:forEach> --%>
<!-- 		</td> -->
		
	</tr>
</table>

</body>
</html>
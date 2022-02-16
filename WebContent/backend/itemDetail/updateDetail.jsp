<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.itemDetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%	

	ItemDetailService itemDetailSvc = new ItemDetailService();
	List<ItemDetailVO> list = itemDetailSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>全部訂單明細清單</title>

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
	width: 1900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
	font-size: 14px;
}

th, td {
	padding: 3px;
	text-align: center;
}
</style>

</head>

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>修改訂單明細資料</h3>
				<h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商城管理</a></h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>一般商城訂單編號</th>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品銷量</th>
			<th>商品單價</th>
			<th>修改</th>
		</tr>

		<jsp:useBean id="itemSvc" scope="page"
			class="com.item.model.ItemService" />

		<%@ include file="page1.file"%>
		<c:forEach var="itemDetailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${itemDetailVO.orderNo}</td>
				<td>${itemDetailVO.itemNo}</td>
				<td>${itemSvc.getOneItem(itemDetailVO.itemNo).itemName}</td>
				<td>${itemDetailVO.itemSales}</td>
				<td>${itemDetailVO.itemPrice}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectOneDetailToUpdte" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="orderNo"  value="${itemDetailVO.orderNo}">
			     <input type="hidden" name="itemNo"  value="${itemDetailVO.itemNo}">
			     <input type="hidden" name="action"	value="selectOneDetailToUpdte"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="page2.file" %>
</body>
</html>			
				
				
				
				
				
				
				
				
				
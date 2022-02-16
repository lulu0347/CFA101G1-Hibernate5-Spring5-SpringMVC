<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemOrder.model.*"%>


<%
	ItemOrderService itemOrderSvc = new ItemOrderService();
    List<ItemOrderVO> list = itemOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<title>全部訂單清單</title>

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
	<tr><td>
		 <h3>全部訂單清單 </h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商品管理</a></h4>
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

<table>
	<tr>
		<th>一般商城訂單編號</th>
		<th>購買會員編號</th>
		<th>錢包交易紀錄編號</th>
		<th>商品售出日期</th>
		<th>訂單金額</th>
		<th>訂單狀態</th>
		<th>收件人姓名</th>
		<th>收件人地址</th>
		<th>收件人電話</th>
		<th>操作</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
	
		<td>${itemOrderVO.orderNo}</td>
		<td>${itemOrderVO.memNo}</td>
		<td><fmt:formatDate value="${itemOrderVO.tranTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		<td>${itemOrderVO.orderTotal}</td>
				<c:choose>
			<c:when test="${itemOrderVO.orderState == '0'}">
			<td>
			未出貨
			</td>
			</c:when>
			<c:when test="${itemOrderVO.orderState == '1'}">
			<td>
			已出貨
			</td>
			</c:when>
			<c:when test="${itemOrderVO.orderState == '2'}">
			<td>
			已收貨
			</td>
			</c:when>
			<c:when test="${itemOrderVO.orderState == '3'}">
			<td>
			退貨
			</td>
			</c:when>
			<c:otherwise>
			<td>
			取消
			</td>
			</c:otherwise>
		</c:choose>
		<td>${itemOrderVO.receiverName}</td>
		<td>${itemOrderVO.receiverAddress}</td>
		<td>${itemOrderVO.receiverPhone}</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/deleteOneOrder" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="orderNo"  value="${itemOrderVO.orderNo}">
			     <input type="hidden" name="action" value="deleteOneOrder"></FORM>
		</td>
	</tr>
	
	</c:forEach>

</table>
<%@ include file="page2.file" %>

</body>
</html>
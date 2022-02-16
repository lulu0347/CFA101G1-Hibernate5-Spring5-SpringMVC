<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemDetail.model.*"%>

<%
  ItemDetailVO itemDetailVO = (ItemDetailVO) request.getAttribute("itemDetailVO");
%>  

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單明細資料修改</title>

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
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單明細資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商城管理</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/updateOneDetail" name="form1">

		<table>
			<tr>
				<td>一般商城訂單編號:<font color=red><b></b></font></td>
				<td><input type="TEXT" name="orderNo" size="45" 
			 value="<%=itemDetailVO.getId().getOrderNo()%>" /></td>
			</tr>
			<tr>
				<td>商品編號:<font color=red><b></b></font></td>
				<td><input type="TEXT" name="itemNo" size="45" 
			 value="<%=itemDetailVO.getId().getItemNo()%>" /></td>
			</tr>

			<tr>
				<td>商品銷量:</td>
				<td><input type="TEXT" name="itemSales" size="45" 
			 value="<%=itemDetailVO.getItemSales()%>" /></td>
			</tr>

		</table>
		
		<br> 
		<input type="hidden" name="action" value="updateOneDetail"> 
		<input type="hidden" name="orderNo" value="${itemDetailVO.orderNo}">
		<input type="hidden" name="itemNo" value="${itemDetailVO.itemNo}">
		<input type="submit" value="送出修改"></FORM>
</body>
</html>		
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productKind.model.*"%>
<%
    ProductKindService productKindSvc = new ProductKindService();
	List<ProductKindVO> list = productKindSvc.getAllProductKind();
    pageContext.setAttribute("list",list);
%>



<html>
<head>
<title>所有商品類別資料</title>
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
	width: 100%;
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
  #th-1 #th-2 #th-3{
  	width:10px;
  }

</style>
</head>


<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有商品類別資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商品管理首頁</a></h4>
	</td></tr>
</table>

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
		<th>商品類別編號</th>
		<th>商品類別名稱</th>
		
	</tr>
	<c:forEach var="productKindVO" items="${list}">
	<tr>
		<td>${productKindVO.kindNo}</td>
		<td>${productKindVO.kindName}</td>							
	</tr>
	</c:forEach>
</table>


</body>


</html>
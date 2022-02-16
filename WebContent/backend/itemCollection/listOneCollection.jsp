<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemCollection.model.*" %>
<%@ page import="java.util.*"%>



<html>
<head>
<title>商品收藏列表資料 - listOneCollection.jsp</title>

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
  #th-1 #th-2 #th-3{
  	width:10px;
  }

</style>


</head>
<body bgcolor='white'>
<table id="table-1">
<tr><td>
		 <h3>照會員編號查詢收藏列表</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/itemCollection/select_page.jsp">回首頁</a></h4>
</td></tr>
<%-- 錯誤表列 --%>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

</table>


 <table>
	<tr>
 		<th>會員編號</th>
 		<th>商品編號</th>
 	</tr> 
 	
 	<c:forEach var="itemCollectionVO" items="${list}">
 	<tr>
 		<td>${itemCollectionVO.memNo}</td>
 		<td>${itemCollectionVO.itemNo}</td>		
	</tr>
 	</c:forEach>
</table>
 </body> 
</html>
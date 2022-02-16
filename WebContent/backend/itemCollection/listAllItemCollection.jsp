<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemCollection.model.*"%>
<%@ page import="java.util.*"%>


<%
	ItemCollectionService itemCollectionSvc = new ItemCollectionService();
	List<ItemCollectionVO> list = itemCollectionSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員的商品收藏列表資料</title>
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
		 <h3>所有會員的商品收藏列表資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/frontend/collection_Index.jsp">回首頁</a></h4>
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
		<th>會員編號</th>
		<th>商品編號</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemCollectionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>	
			<td>${itemCollectionVO.getId().memNo}</td>
			<td>${itemCollectionVO.getId().itemNo}</td>
<!-- 			<td> -->
<!-- 			修改功能保留,應該是不需要 -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemCollection/itemCollection.do" style="margin-bottom: 0px;"> --%>
<!-- 				<input type="submit" value="修改"> -->
<%-- 				<input type="hidden" name="memNo"  value="${itemCollectionVO.memNo}"> --%>
<!-- 		    	<input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
	</tr>
	
	</c:forEach>
	
</table>
<%@ include file="page2.file" %>

</body>


</html>
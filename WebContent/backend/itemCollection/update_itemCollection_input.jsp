<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.itemCollection.model.*"%>

<%
  ItemCollectionVO itemCollectionVO = (ItemCollectionVO) request.getAttribute("itemCollectionVO"); //ItemServlet.java (Concroller) 存入req的itemVO物件 (包括幫忙取出的itemVO, 也包括輸入資料錯誤時的itemVO物件)
%> 

<!-- 修改部分保留,雙主鍵部分難以修改且可能不需要修改收藏列表 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品收藏列表資料修改 - update_itemCollection_input.jsp</title>

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
		 <h3>商品收藏列表資料修改 - update_itemCollection_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/itemCollection/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>商品收藏列表修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemCollection/itemCollection.do" name="form1">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="memName" size="45" value="<%=itemCollectionVO.getId().getMemNo()%>" /></td>
	</tr>
	
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="itemName" size="45" value="<%=itemCollectionVO.getId().getItemNo()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="TEXT" name="memNo" value="<%=itemCollectionVO.getId().getMemNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>
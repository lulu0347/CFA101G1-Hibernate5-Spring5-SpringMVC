<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productKind.model.*"%>
<%@ page import="java.util.*"%>

<%
    ProductKindService productKindSvc = new ProductKindService();
	List<ProductKindVO> list = productKindSvc.getAllProductKind();
    pageContext.setAttribute("list",list);
%>
<%
	ProductKindVO productKindVO = (ProductKindVO) request.getAttribute("productKindVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增商品類別</title>

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
		 <h3>新增商品類別</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商品管理首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/addProductKind" 
		name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品類別名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="kindName" size="45" 
			 value="<%= (productKindVO==null)? "" : productKindVO.getKindName()%>" /></td>
	</tr>

	<tr>
		<td>
			<input type="submit" value="新增">
			<input type="hidden" name="action" value="addProductKind">
		</td>
	</tr>
</table>
</FORM>
</body>

</html>
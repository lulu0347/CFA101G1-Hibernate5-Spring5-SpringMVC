<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adminauthrization.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AdminAuthrizationVO adminAuthrizationVO = (AdminAuthrizationVO) request.getAttribute("adminAuthrizationVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>權限功能資料 - listOneAdminAuthrization.jsp</title>

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
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>權限功能資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/admini.jsp">回員工管理</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>權限功能編號</th>
		<th>權限功能名稱</th>
		<th></th>
		<th></th>
	</tr>
	<tr>
			<td>${adminAuthrizationVO.adminAuthrizationNo}</td>
			<td>${adminAuthrizationVO.adminAuthrizationName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuthrization/adminAuthrization.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adminAuthrizationNo"  value="${adminAuthrizationVO.adminAuthrizationNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuthrization/adminAuthrization.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adminAuthrizationNo"  value="${adminAuthrizationVO.adminAuthrizationNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>
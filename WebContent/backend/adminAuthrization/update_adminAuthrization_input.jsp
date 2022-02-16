<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adminauthrization.model.*"%>

<%
	AdminAuthrizationVO adminAuthrizationVO = (AdminAuthrizationVO) request.getAttribute("adminAuthrizationVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限功能資料修改 - update_adminAuthrization_input.jsp</title>

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
	width: 450px;
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
		<tr>
			<td>
				<h3>權限功能資料修改</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/backend/admini.jsp">回員工管理</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="adminAuthrization.do" name="form1">
		<table>
			<tr>
				<td>權限功能編號:<font color=red><b>*</b></font></td>
				<td><%=adminAuthrizationVO.getAdminAuthrizationNo()%></td>
			</tr>
			<tr>
				<td>權限功能名稱:</td>
				<td><input type="TEXT" name="adminAuthrizationName" size="45"
					value="<%=adminAuthrizationVO.getAdminAuthrizationName()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="adminAuthrizationNo" value="<%=adminAuthrizationVO.getAdminAuthrizationNo()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>
</html>
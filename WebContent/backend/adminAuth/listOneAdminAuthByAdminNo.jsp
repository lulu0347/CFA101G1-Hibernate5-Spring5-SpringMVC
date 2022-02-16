<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adminauthrization.model.*"%>
<%@ page import="com.adminauth.model.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	AdminAuthService adminAuthSvc = new AdminAuthService();
	String admin = request.getParameter("adminNo");
	Integer adminNo = Integer.parseInt(admin);
	List<AdminAuthVO> list = new LinkedList<AdminAuthVO>();
	list = adminAuthSvc.getAuthByAdminNO(adminNo);
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>權限功能資料</title>

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
		 <h3>員工權限資料 </h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/admini.jsp">回員工管理</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>權限功能編號</th>
		<th>權限功能名稱</th>
		<th></th>
	</tr>
	<jsp:useBean id="adminiSvc" scope="page" class="com.admini.model.AdminiService" />
	<jsp:useBean id="adminAuthrizationSvc" scope="page" class="com.adminauthrization.model.AdminAuthrizationService" />
	<c:forEach var="adminAuthVO" items="${list}">
	<tr>
			<td>${adminAuthVO.adminNo}</td>
			<td>${adminiSvc.getOneAdmini(adminAuthVO.adminNo).adminName }</td>
			<td>${adminAuthVO.adminAuthrizationNo}</td>
			<td>${adminAuthrizationSvc.getOneAdminAuthrization(adminAuthVO.adminAuthrizationNo).adminAuthrizationName}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuth/adminAuth.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adminNo"  value="${adminAuthVO.adminNo}">
			     <input type="hidden" name="adminAuthrizationNo"  value="${adminAuthVO.adminAuthrizationNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</c:forEach>
</table>

</body>
</html>
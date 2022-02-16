<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminauth.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdminAuthService adminAuthSvc = new AdminAuthService();
    List<AdminAuthVO> list = adminAuthSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="adminAuthrizationSvc" scope="page" class="com.adminauthrization.model.AdminAuthrizationService" />

<html>
<head>
<title>所有權限功能資料 </title>

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
	width: 800px;
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
		 <h3>所有權限功能資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/admini.jsp">回員工管理</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>員工編號</th>
		<th>權限功能編號</th>
		<th>權限功能名稱</th>
		<th></th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="adminAuthVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${adminAuthVO.adminNo}</td>
			<td>${adminAuthVO.adminAuthrizationNo}</td>
			<td><c:forEach var="adminAuthrizationVO" items="${adminAuthrizationSvc.all}">
                    <c:if test="${adminAuthVO.adminAuthrizationNo==adminAuthrizationVO.adminAuthrizationNo}">
	                    	【${adminAuthrizationVO.adminAuthrizationName}】
                    </c:if>
                </c:forEach></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuth/adminAuth.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" id="delete">
			     <input type="hidden" name="adminNo"  value="${adminAuthVO.adminNo}">
			     <input type="hidden" name="adminAuthrizationNo" value="${adminAuthVO.adminAuthrizationNo}" >
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
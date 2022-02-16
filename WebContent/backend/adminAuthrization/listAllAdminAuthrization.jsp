<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adminauthrization.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdminAuthrizationService adminAuthrizationSvc = new AdminAuthrizationService();
    List<AdminAuthrizationVO> list = adminAuthrizationSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有權限功能資料</title>

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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="table-1">
	<tr><td>
		 <h3>所有權限資料</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="adminAuthrizationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${adminAuthrizationVO.adminAuthrizationNo}</td>
			<td>${adminAuthrizationVO.adminAuthrizationName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuthrization/adminAuthrization.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="change">
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
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	var userSelection = document.getElementsByClassName("change");
	
	for(let i = 0; i < userSelection.length; i++) {
		  userSelection[i].addEventListener("click", function() {
			  swal("Good job!", "YAYA!", "success");
		  })
		};
</script>
</body>
</html>
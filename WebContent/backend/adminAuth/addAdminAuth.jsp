<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adminauthrization.model.*"%>
<%@ page import="com.admini.model.*" %>
<%@ page import="java.util.*"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工權限資料新增</title>

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

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<jsp:useBean id="adminiSvc" scope="page" class="com.admini.model.AdminiService" />
<jsp:useBean id="adminAuthrizationSvc" scope="page" class="com.adminauthrization.model.AdminAuthrizationService" />
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adminAuth/adminAuth.do" >
	<b>選擇員工:</b>	
		<select size="1" name="adminNo" id="adminNo">
			  <c:forEach var="adminiVO" items="${adminiSvc.all}" > 
			      <option value="${adminiVO.adminNo}">${adminiVO.adminName}
			  </c:forEach>   
		</select>
	<b>選擇權限:</b>	
		<select size="1" name="adminAuthrizationNo" id="selectNo">
			  <c:forEach var="adminAuthrizationVO" items="${adminAuthrizationSvc.all}" > 
			      <option value="${adminAuthrizationVO.adminAuthrizationNo}">${adminAuthrizationVO.adminAuthrizationName}
			  </c:forEach>   
		</select>
	 	<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
</FORM>		  
<br>

</body>

</html>

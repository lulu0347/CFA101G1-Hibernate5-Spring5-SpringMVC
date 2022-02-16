<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumpost.model.*"%>

<%
	ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章資料新增 </title>

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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostreport/forumpostreport.do" name="form1">
<table>
	<tr>
		<td>文章檢舉內容:</td>
		<td><textarea  name="forumPostReportWhy" style="resize:none;width:600px;height:200px;" required="required"></textarea></td>
		<td><input type="hidden" name="memNo" value="${memVO.memNo}"/></td>			 
		<td><input type="hidden" name="forumPostNo" value="${forumPostVO.forumPostNo}"/></td>			 
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input id="demo1" type="submit" value="送出新增"></FORM>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>

</body>

</html>

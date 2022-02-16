<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forummsg.model.*"%>

<%
	ForumMsgVO forumMsgVO = (ForumMsgVO) request.getAttribute("forumMsgVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addAdmini.jsp</title>

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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forummsg/forummsg.do" name="form1">
<table>
	<tr>
		<td>留言內容:</td>
		<td><textarea  name="forumMsg" required="required"></textarea></td>
		<td><input type="hidden" name="commentator" value="${commentator}"/></td>			 
		<td><input type="hidden" name="id" value="${forumPostVO.id}"/></td>			 
		<td><input type="hidden" name="gmtCreate" value="${dates.format(System.currentTimeMillis(),'yyyy-MM-dd HH:mm')}"/></td>			 
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input id="demo1" type="submit" value="送出新增"></FORM>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>

</body>

</html>

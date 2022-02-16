<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumpostcollection.model.*"%>

<%
	ForumPostCollectionVO forumPostCollectionVO = (ForumPostCollectionVO) request.getAttribute("forumPostCollectionVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員文章收藏資料新增 </title>

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
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumPostSvc" scope="page" class="com.forumpost.model.ForumPostService" />
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostcollection/forumpostcollection.do" >
	<b>選擇員工:</b>	
		<select size="1" name="memNo" id="memNo">
			  <c:forEach var="memVO" items="${memSvc.all}" > 
			      <option value="${memVO.memNo}">${memVO.memNo}
			  </c:forEach>   
		</select>
	<b>選擇權限:</b>	
		<select size="1" name="forumPostNo" id="forumPostNo">
			  <c:forEach var="forumPostVO" items="${forumPostSvc.all}" > 
			      <option value="${forumPostVO.forumPostNo}">${forumPostVO.forumPostNo}
			  </c:forEach>   
		</select>
	 	<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
</FORM>	
<br>
<input type="hidden" name="action" value="insert">
<input id="demo1" type="submit" value="送出新增"></FORM>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>

</body>

</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumpostcollection.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
	List<ForumPostCollectionVO> list = forumPostCollectionSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
		 <h3>權限功能資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/frontend.jsp">回前端頁面管理</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>會員編號</th>
		<th>文章編號</th>
		<th></th>
		<th></th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="forumPostCollectionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${forumPostCollectionVO.memNo}</td>
			<td>${forumPostCollectionVO.forumPostNo}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostcollection/forumpostcollection.do" style="margin-bottom: 0px;">
			     <input id="demo1" type="submit" value="刪除">
			     <input type="hidden" name="memNo"  value="${forumPostCollectionVO.memNo}">
			     <input type="hidden" name="forumPostNo"  value="${forumPostCollectionVO.forumPostNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<script>
	document.getElementById("demo1").addEventListener("click",function(){
	  swal("刪除成功!", "你已刪除一筆資料!", "success");
	});
</script>
</body>
</html>
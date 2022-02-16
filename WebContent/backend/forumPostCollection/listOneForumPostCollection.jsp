<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<title>文章收藏資料</title>

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
		 <h3>文章資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/frontend.jsp">回前端頁面管理</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>文章編號</th>
		<th>刪除</th>
	</tr>
	
		<c:forEach var="forumPostCollectionVO" items="${list}" > 
		<tr>		      
		<td>${forumPostVO.memNo}</td>
		<td>${forumPostVO.forumPostNo}</td>

		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostcollection/forumpostcollection.do" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="memNo"  value="${forumPostCollectionVO.memNo}">
		     <input type="hidden" name="forumPostNo"  value="${forumPostCollectionVO.forumPostNo}">
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
		</tr>
		</c:forEach>
	
</table>

</body>
</html>
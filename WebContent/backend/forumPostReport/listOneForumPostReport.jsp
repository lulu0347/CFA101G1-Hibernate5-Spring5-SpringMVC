<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<title>文章檢舉資料</title>

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
		 <h3>文章檢舉資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/frontend.jsp">回前端頁面管理</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章檢舉編號</th>
		<th>文章編號</th>
		<th>會員編號</th>
		<th>文章內容</th>
		<th>發文時間</th>
		<th>文章狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
		<c:forEach var="forumPostVO" items="${list}" > 
		<tr>		      
		<td>${forumPostReportVO.forumPostReportNo}</td>
		<td>${forumPostReportVO.forumPostNo}</td>
		<td>${forumPostReportVO.memNo}</td>
		<td>${forumPostReportVO.forumPostReportTime}</td>
		<td>${forumPostReportVO.forumPostReportWhy}</td>
		<td>${forumPostReportVO.forumPostReportType}</td> 
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostreport/forumpostreport.do" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="forumPostReportNo"  value="${forumPostReportVO.forumPostReportNo}">
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostreport/forumpostreport.do" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="forumPostReportNo"  value="${forumPostReportVO.forumPostReportNo}">
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
		</tr>
		</c:forEach>
	
</table>

</body>
</html>
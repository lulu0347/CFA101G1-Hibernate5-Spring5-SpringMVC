<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumpostreport.model.*"%>

<%
	ForumPostReportVO forumPostReportVO = (ForumPostReportVO) request.getAttribute("forumPostReportVO"); 
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章狀態修改 </title>

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

	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/backend/frontend.jsp">回前端頁面管理</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpostreport/forumpostreport.do" name="form1">
		<table>
			<tr>
				<td>文章檢舉編號:<font color=red><b>*</b></font></td>
				<td><%=forumPostReportVO.getForumPostReportNo()%>
				<input type="hidden" name="forumPostReportNo" value="<%=forumPostReportVO.getForumPostReportNo()%>"></td>
			</tr>
			<tr>
				<td>文章編號:<font color=red><b>*</b></font></td>
				<td><%=forumPostReportVO.getForumPostNo()%>
				<input type="hidden" name="forumPostNo" value="<%=forumPostReportVO.getForumPostNo()%>"></td>
			</tr>
			<tr>
				<td>會員帳號:<font color=red><b>*</b></font></td>
				<td><%=forumPostReportVO.getMemNo()%>
				<input type="hidden" name="memNo" value="<%=forumPostReportVO.getMemNo()%>"></td>
			</tr>
			<tr>
				<td>發文時間:<font color=red><b>*</b></font></td>
				<td><%=forumPostReportVO.getForumPostReportTime()%>
				<input type="hidden" name="forumPostReportTime" value="<%=forumPostReportVO.getForumPostReportTime()%>"></td>
			</tr>
			<tr>
				<td>檢舉內容:<font color=red><b>*</b></font></td>
				<td><%=forumPostReportVO.getForumPostReportWhy()%>
				<input type="hidden" name="forumPostReportWhy" value="<%=forumPostReportVO.getForumPostReportWhy()%>"></td>
			</tr>
			<tr>
				<td>文章檢舉狀態:</td>
				<td><select name="forumPostReportType">
						<option value="0">0</option>
						<option value="1" selected>1</option>
					</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
			 <input type="submit" value="送出修改">
	</FORM>
</body>
</html>
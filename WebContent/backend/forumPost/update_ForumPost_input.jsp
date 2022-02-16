<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
	ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO"); 
	Timestamp timestamp = new Timestamp(forumPostVO.getGmtCreate());
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
	width: 100%;
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumpost/forumpost.do" name="form1">
		<table>
			<tr>
				<td>文章編號:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getId()%>
				<input type="hidden" name="id" value="<%=forumPostVO.getId()%>"></td>
			</tr>
			<tr>
				<td>會員帳號:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getCreator()%>
				<input type="hidden" name="creator" value="<%=forumPostVO.getCreator()%>"></td>
			</tr>
			<tr>
				<td>文章標題:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getTitle()%>
				<input type="hidden" name="title" value="<%=forumPostVO.getTitle()%>"></td>
			</tr>
			<tr>
				<td>文章內容:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getDescription()%>
				<input type="hidden" name="description" value="<%=forumPostVO.getDescription()%>"></td>
			</tr>
			<tr>
				<td>發文時間:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getGmtCreate()%>
				<input type="hidden" name="gmtCreate" value="<%=forumPostVO.getGmtCreate()%>"></td>
			</tr>
			<tr>
				<td>修改時間:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getGmtModified()%>
				<input type="hidden" name="gmtModified" value="<%=forumPostVO.getGmtModified()%>"></td>
			</tr>
			<tr>
				<td>文章回應數:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getCommentCount()%>
				<input type="hidden" name="commentCount" value="<%=forumPostVO.getCommentCount()%>"></td>
			</tr>
			<tr>
				<td>文章觀看數:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getViewCount()%>
				<input type="hidden" name="viewCount" value="<%=forumPostVO.getViewCount()%>"></td>
			</tr>
			<tr>
				<td>文章按讚數:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getLikeCount()%>
				<input type="hidden" name="likeCount" value="<%=forumPostVO.getLikeCount()%>"></td>
			</tr>
			<tr>
				<td>文章標籤:<font color=red><b>*</b></font></td>
				<td><%=forumPostVO.getTag()%>
				<input type="hidden" name="tag" value="<%=forumPostVO.getTag()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		 	 <input type="submit" value="送出修改">
	</FORM>
</body>
</html>
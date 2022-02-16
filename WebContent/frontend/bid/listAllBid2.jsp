<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.*"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*,java.text.SimpleDateFormat"  %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Calendar" %>
<%@page import="com.member.model.*" %>
<%@ page import="com.bid.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<BidVO> list = (List<BidVO>) request.getAttribute("searchResult");
	StringBuilder sb = new StringBuilder();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>helloworld.jsp</title>
</head>
<body>
<c:forEach var="bidVO" items="${list}">
${bidVO.bidProdNo}
</c:forEach>
<script>

</script>
</body>
</html>
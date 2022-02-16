<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="com.member.model.*"%>



<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="./component/headerCDN.txt"%>
<%@ include file="./css/auctAct.css"%>
<style>
a {
	text-decoration: none
}

a:hover {
	text-decoration: none
}
</style>
</head>

<body>
	<%@ include file="./component/header.txt"%>
	<session>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
			<br>
				<caption>
					<h4>查詢會員錢包交易紀錄</h4>
				</caption>
				<br>
					<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
				<hr>
				<FORM method="get"action="<%=request.getContextPath()%>/getOneMemRec">
					<p>選擇會員帳號:</p>
					<select name="memno">
						<c:forEach var="MemberVO" items="${memSvc.all}">
							<option value="${MemberVO.memno}">${MemberVO.memaccount}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOneMemRec">
					<button class="btn btn-outline-success  btn-sm" type="submit">送出</button>
				</FORM>
			</div>
		</div>
	</div>
	</session>
	<%@include file="./component/footer.txt"%>
</body>

</html>
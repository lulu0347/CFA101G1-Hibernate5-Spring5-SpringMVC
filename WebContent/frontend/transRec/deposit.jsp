<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
<style>
	a{text-decoration:none}
	a:hover{text-decoration:none}
</style>
</head>

<body>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>


	<session>
		<div class="container">
			<div class="row">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red"></font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="row" style="height: 80px"></div>
			<img
				src="<%=request.getContextPath()%>/frontend/transRec/images/deposit.jpg"
				style="height: 200px" class="rounded mx-auto d-block" alt="...">
		</div>


	<c:if test="${not empty user}">
		<br>
		<h3 style="text-align: center">帳戶餘額：${money}</h3>
		<br>
		<br>

		<form name="depositform"
			action="<%=request.getContextPath()%>/transRec"
			method="POST">
			<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
				<button class="btn btn-outline-info" type="submit"
					style="font-size: 1.5rem">
					$ 查詢交易紀錄 <i class="bi bi-arrow-right-circle"
						style="font-size: 1.8rem"></i>
				</button>
				<input type="hidden" name="action" value="transRec">
			</div>
		</form>
		<br>
		<a href="<%=request.getContextPath()%>/frontend/transRec/saveMoney.jsp">
		<div class="d-grid gap-2 col-4 mx-auto">
					<button class="btn btn-warning" type="button"
						style="font-size: 1.5rem">
						$ 儲值 Go <i class="bi bi-arrow-right-circle"
							style="font-size: 1.8rem"></i>
					</button>
		</div>
		</a>
	</c:if>
	<c:if test="${empty user }">
	<div class="container">
			<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
				<button class="btn btn-outline-info" type="submit"
					style="font-size: 1.5rem" disabled="disabled">
					請先登入會員查看錢包紀錄
				</button>
			</div>
		</div>
	</c:if>
	</session>
	<%@ include file="component/footer.txt"%>
</body>

</html>
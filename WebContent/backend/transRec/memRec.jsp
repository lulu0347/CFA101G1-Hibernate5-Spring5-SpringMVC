<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<div class="col">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>會員交易紀錄</h4>
						</caption>
						<thead>
							<tr>
								<th scope="col">紀錄編號</th>
								<th scope="col">會員編號</th>
								<th scope="col">交易金額</th>
								<th scope="col">交易時間</th>
								<th scope="col">交易狀態</th>
								<th scope="col">交易商城 / 系統儲值</th>
								<th scope="col">訂單編號</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="transRecVO" items="${transRecList}">
								<tr>
									<td>${transRecVO.transRecNo}</td>
									<td>${transRecVO.memNo}</td>
									<td>${transRecVO.transAmount}</td>
									<td><fmt:formatDate value="${transRecVO.transRecTime}"
									pattern="yyyy-MM-dd hh:mm:ss" /></td>
									<td>${transRecVO.transStateString}</td>
									<td>${transRecVO.mallName}</td>
									<c:choose>
									<c:when test="${transRecVO.orderNo==0}"><td></td></c:when>
									<c:when test="${transRecVO.orderNo!=null}"><td>${transRecVO.orderNo}</td></c:when>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</session>

<%@include file="./component/footer.txt"%>
</body>

</html>
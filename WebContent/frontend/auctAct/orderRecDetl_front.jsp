<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctOrder.model.*"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
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
			<div class="col">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>拍賣訂單明細</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">訂單編號</th>
							<th scope="col">商品編號</th>
							<th scope="col">商品名稱</th>
							<th scope="col">購買商品數量</th>
							<th scope="col">商品單價</th>
							<th scope="col">商品總額</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="auctOrdDetlVO" items="${auctOrdDetlList}">
							<tr>
								<td>${auctOrdDetlVO.auctOrdNo}</td>
								<td>${auctOrdDetlVO.auctActProdNo}</td>
								<c:forEach var="pairs" items="${nameMap}">
									<c:if test="${auctOrdDetlVO.auctActProdNo== pairs.key}">
										<td>${pairs.value}</td>
									</c:if>
								</c:forEach>
								<td>${auctOrdDetlVO.prodPurQty}</td>
								<td>${auctOrdDetlVO.price}</td>
								<td>${auctOrdDetlVO.sumPrice}</td>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</session>
		<%@ include file="component/footer.txt"%>
</body>

</html>
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>拍賣活動訂單清單</title>
<%@ include file="../component/headerCDN.txt"%>
<%@ include file="../css/auctAct.css"%>
<style>
	a{text-decoration:none}
	a:hover{text-decoration:none}
</style>
</head>

<body>
<%@ include file="../component/header.txt"%>
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
							<th scope="col">拍賣商品編號</th>
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
	<%@include file="../component/footer.txt"%>
</body>

</html>
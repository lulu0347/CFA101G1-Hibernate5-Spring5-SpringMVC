<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctProd.model.*"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>拍賣商品</title>
<%@ include file="../component/headerCDN.txt"%>
<%@ include file="../css/auctAct.css"%>
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
	<%@ include file="../component/header.txt"%>
	<session>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>拍賣商品</h4>
					</caption>
					<thead>

						<th scope="col">商品編號</th>
						<th scope="col">商品名稱</th>
						<th scope="col">商品類別</th>
						<th scope="col">活動商品拍賣數量</th>
						<th scope="col">活動商品拍賣價格</th>
						</tr>
					</thead>
					<tbody>
						<th scope="row">${auctProdVO.auctProdNo}</th>
						<td>${auctProdVO.auctProdName}</td>
						<td>${auctProdVO.kindNo}</td>
						<td>${auctActProdVO.auctProdQty}</td>
						<td>${auctActProdVO.auctProdPrice}</td>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<caption>
					<h5>商品照片</h5>
				</caption>
				<img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdDetlInfo&action=''"
					width='300px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=DetlPic1&action=''"
					width='300px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=DetlPic2&action=''"
					width='300px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdFront&action=''"
					width='300px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdBack&action=''"
					width='300px'>
			</div>
		</div>
		<a
			href="<%=request.getContextPath()%>/backend/auct/auctAct/select_page.jsp">
			<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
				<button class="btn btn-outline-info" type="button"
					style="font-size: 1.2rem">
					返回活動列表<i class="bi bi-arrow-right-circle" style="font-size: 1.3rem"></i>
				</button>
			</div>
		</a>
	</div>
	<%@include file="../component/footer.txt"%>
</body>
</html>
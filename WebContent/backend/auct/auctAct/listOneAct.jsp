<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctActProd.model.*"%>
<%@ page import="com.auctProd.model.*"%>
<%@ page import="com.auctPic.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="auctProdSvc" scope="page"
	class="com.auctProd.model.AuctProdService" />

<jsp:useBean id="auctActProdSvc" scope="page"
	class="com.auctActProd.model.AuctActProdService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>拍賣活動</title>
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
						<h4>拍賣活動</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">活動編號</th>
							<th scope="col">活動名稱</th>
							<th scope="col">活動說明</th>
							<th scope="col">活動狀態</th>
							<th scope="col">活動起始日期</th>
							<th scope="col">活動結束日期</th>
						</tr>
					</thead>
					<tbody>
						<th scope="row">${auctActVO.auctActNo}</th>
						<td>${auctActVO.auctActName}</td>
						<td>${auctActVO.auctActDesc}</td>
						<c:choose>
							<c:when test="${auctActVO.auctActState==-1}">
								<td>移除活動</td>
							</c:when>
							<c:when test="${auctActVO.auctActState==0}">
								<td>下架狀態</td>
							</c:when>
							<c:when test="${auctActVO.auctActState==1}">
								<td>上架狀態</td>
							</c:when>
						</c:choose>

						<td><fmt:formatDate value="${auctActVO.auctStartTime}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${auctActVO.auctEndTime}"
								pattern="yyyy-MM-dd" /></td>
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
					<h5>Banner</h5>
				</caption>
				<img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner1&action=getActPic"
					width='350px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner2&action=getActPic"
					width='350px'> <img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner3&action=getActPic"
					width='350px'>
			</div>
			<div class="row" style="height: 50px"></div>
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2">
					<caption>
						<h5>Cart</h5>
					</caption>
					<img
						src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=cart&action=getActPic"
						width='50px'>
				</div>
				<div class="col-2">
					<caption>
						<h5>Button</h5>
					</caption>
					<img
						src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=button&action=getActPic"
						width='50px'>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="height: 50px"></div>
	<div class="container">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>拍賣活動商品列表</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">活動商品編號</th>
							<th scope="col">拍賣商品編號</th>
							<th scope="col">活動商品名稱</th>
							<th scope="col">活動商品數量（個）</th>
							<th scope="col">商品目前數量（個）</th>
							<th scope="col">活動商品金額（元)</th>
							<th scope="col">查看商品詳情</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="auctActProdVO" items="${auctActProdList}">
							<tr>

								<th scope="row">${auctActProdVO.auctActProdNo}</th>
								<td>${auctActProdVO.auctProdNo}</td>
								<td><c:forEach var="auctProdVO"
										items="${auctProdSvc.allProd}">
										<c:if
											test="${auctActProdVO.auctProdNo==auctProdVO.auctProdNo}">
								 ${auctProdVO.auctProdName}
					</c:if>
									</c:forEach></td>
								<td>${auctActProdVO.auctProdQty}</td>
								<td>${auctActProdVO.auctProdRemain}</td>
								<td>${auctActProdVO.auctProdPrice}</td>
								<td>

									<form method="post"
										action="<%=request.getContextPath()%>/auctActProd/auctactprod.do">
										<button type="submit" class="btn btn-outline-success btn-sm">商品詳情</button>
										<input type="hidden" name="auctProdNo"
											value="${auctActProdVO.auctProdNo}"> <input
											type="hidden" name="auctActNo"
											value="${auctActProdVO.auctActNo}"> <input
											type="hidden" name="action" value="getOneProd">
										<%-- 					 <input type="hidden" name="action" onclick="openProdInNewWindow('${auctProdVO.auctProdNo}')" value="getOneProd"> --%>
									</form>
								</td>
							</tr>
						</c:forEach>
				</table>
				<a
					href="<%=request.getContextPath()%>/backend/auct/auctAct/select_page.jsp">
					<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
						<button class="btn btn-outline-info" type="button"
							style="font-size: 1.2rem">
							返回活動列表<i class="bi bi-arrow-right-circle"
								style="font-size: 1.3rem"></i>
						</button>
					</div>
				</a>

			</div>
		</div>
	</div>
	<%@include file="../component/footer.txt"%>
</body>
</html>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="auctActProdSvc" scope="page"
	class="com.auctActProd.model.AuctActProdService" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
</head>
<body onload="setTotalPrice()">
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container">
		<% MemberVO memVO = (MemberVO) session.getAttribute("user");%>
		<%
			Map<Integer, AuctOrdDetlVO> auctCart = (Map<Integer, AuctOrdDetlVO>) session.getAttribute("auctCart");
		%>
		<c:if test="${not empty auctCart}">

			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>購買商品清單</h4>
						</caption>
						<thead>
							<tr>
								<th scope="col">商品圖片</th>
								<th scope="col">商品編號</th>
								<th scope="col">商品名稱</th>
								<th scope="col">商品數量</th>
								<th scope="col">商品單價</th>
								<th scope="col">商品總價</th>
								<th scope="col"></th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="auctOrdDetlVO" items="${auctCart.values()}">
								<tr>
									<td><c:forEach var="auctActProdVO"
											items="${auctActProdSvc.all}">
											<c:if
												test="${auctOrdDetlVO.auctActProdNo==auctActProdVO.auctActProdNo}">
												<img
													src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctActProdVO.auctProdNo}&auctProdPicinfo=DetlPic2&action=''"
													alt="Prod" style="height: 100px;">
											</c:if>
										</c:forEach></td>
									<td>${auctOrdDetlVO.auctActProdNo}</td>
									<c:forEach var="pairs" items="${nameMap}">
										<c:if test="${auctOrdDetlVO.auctActProdNo== pairs.key}">
											<td>${pairs.value}</td>
										</c:if>
									</c:forEach>

									<td>
										<form name="confirmOrder"
											action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
											method="POST">
											<div class="input-group" style="width: 120px">
												<input class="input-group-text" type="button" id="minus"
													name="minus" value="-"
													onclick="changeValue('${auctOrdDetlVO.auctActProdNo}', -1)">
												<input class="form-control prodPurQtyNum"
													aria-describedby="button-addon2"
													id="${auctOrdDetlVO.auctActProdNo}Value"
													onchange="setValue(${auctOrdDetlVO.auctActProdNo})"
													name="prodPurQty" value="${auctOrdDetlVO.prodPurQty}">
												<input class="input-group-text" type="button" id="plus"
													name="plus" value="+"
													onclick="changeValue('${auctOrdDetlVO.auctActProdNo}', 1)">
											</div>
										</form>
									</td>
									<td>${auctOrdDetlVO.price}</td>
									<td class="sum" id="${auctOrdDetlVO.auctActProdNo}Sum">${auctOrdDetlVO.price*auctOrdDetlVO.prodPurQty}</td>
									<td></td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
											name="auctCart">
											<button type="submit" class="btn btn-outline-danger btn-sm">刪除
											</button>
											<input type="hidden" name="action" value="delCartProd">
											<input type="hidden" name="auctActProdNo"
												value="${auctOrdDetlVO.auctActProdNo}">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row" id="sum"></div>
					<div class="row">
						<div class="col-2"></div>
						<div class="col-5">
							<a href="<%=request.getContextPath()%>${param.from.replace("-","&")}">
								<button type="submit" class="btn btn-outline-info">繼續購物</button>
							</a>
						</div>
						<div class="col-5">
						<c:if test="${empty user}">
						<div class="container">
							<div class="col-12 mx-auto  text-decoration:none;">
							<a href="<%=request.getContextPath()%>/frontend/member/login.html">
								<button class="btn btn-warning" type="submit"style="font-size: 1.3rem" disabled="disabled">
									請先登入會員才能結帳
									<i class="bi bi-arrow-right-circle"style="font-size: 1.2rem"></i>
									</button>
								</a>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty user}">
							<form name="confirmOrder"
								action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
								method="POST" id="creOrd">
								<div class="d-grid gap-2 d-md-flex justify-content-md-end">
									<input type="hidden" name="action" value="checkOut">
									<button type="button" class="btn btn-outline-success"
										onclick="checkMoney()">確認結帳</button>
								</div>
							</form>
						</c:if>
						</div>
						<div class="modal fade show" style="display: none; top: 35%"
							id="askSaveMoney">
							<div class="row">
								<div class="modal-dialog">
									<div class="modal-content" style="display:flex;align-items: center;">
										<div class="modal-header">
											<h5 class="modal-title" id="staticBackdropLabel">錢包金額不足!要前往儲值畫面嗎?</h5>
										</div>
										<div class="row">
											<div class="modal-body">
												<div class="container-fluid">
													<button type="button" class="btn btn-outline-danger"
														onclick="closeAsk()">取消</button>
													<button type="button" class="btn btn-outline-warning"
														onclick="submit()">前往儲值</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<c:if test="${empty auctCart}">
		<%@ include file="./includePage/noProdPage.txt"%>
	</c:if> </session>
	<%@ include file="component/footer.txt"%>

	<script>
		<%Gson gson = new Gson();
			String cart = gson.toJson(auctCart);%>
		const cart = <%=cart%>
	</script>
	<script src="<%=request.getContextPath()%>/frontend/auctAct/js/cart.js"></script>
	<script>
	//將拿到頁面上的值轉給servlet 不能寫在js會變成字串找不到頁面放入值
		var projectName = "<%=request.getContextPath()%>";	
	</script>


</body>
</html>
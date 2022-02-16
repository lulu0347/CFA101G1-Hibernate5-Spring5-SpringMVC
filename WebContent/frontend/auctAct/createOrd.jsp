<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
</head>

<body>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<form method="post"
		action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
		name="orderList">
		<div class="container">
			<div class="row">
				<div class="col">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>商品明細</h4>
						</caption>
						<thead>
							<tr>
								<th scope="col">商品名稱</th>
								<th scope="col">商品數量</th>
								<th scope="col">商品單價</th>
								<th scope="col">商品總價</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="auctOrdDetlVO" items="${auctOrdDetlList}">
								<tr>
									<c:forEach var="pairs" items="${nameMap}">
									<c:if test="${auctOrdDetlVO.auctActProdNo== pairs.key}">
										<td>${pairs.value}</td>
									</c:if>
									</c:forEach>
									<td>${auctOrdDetlVO.prodPurQty}</td>
									<td>${auctOrdDetlVO.price}</td>
									<td>${auctOrdDetlVO.price*auctOrdDetlVO.prodPurQty}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					總額: ${summary}
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>訂單資訊</h4>
						</caption>
						<tr>
							<th scope="col">收件人姓名:</th>
							<td>${auctOrdVO.receName}</td>
						</tr>


						<tr>
							<th scope="col">收件地址:</th>
							<td>${auctOrdVO.receAddr}</td>
						</tr>

						<tr>
							<th scope="col">收件人電話:</th>
							<td>${auctOrdVO.recePhone}</td>
						</tr>
						<tr>
							<th scope="col">商品備註:</th>
							<td>${auctOrdVO.note}</td>
						</tr>
						<tr>
							<th scope="col">訂單狀態:</th>
							<c:choose>
									<c:when test="${auctOrdVO.auctOrdState==0}"><td>取消訂單</td></c:when>
									<c:when test="${auctOrdVO.auctOrdState==1}"><td>待出貨</td></c:when>
									<c:when test="${auctOrdVO.auctOrdState==2}"><td>已出貨</td></c:when>
									<c:when test="${auctOrdVO.auctOrdState==3}"><td>完成訂單</td></c:when>
							</c:choose>
						</tr>

						<tr>
							<th scope="col">訂單成立時間:</th>
							<td><fmt:formatDate value="${auctOrdVO.auctOrdTime}"
									pattern="yyyy-MM-dd hh:mm:ss" /></td>

						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-10"></div>
				<div class="col-2">
					<a
						href="<%=request.getContextPath()%>/frontend/auctAct/auctActIndex.jsp">
						<img
						src="<%=request.getContextPath()%>/frontend/auctAct/images/boxOK.png"
						style="height: 100px">
					</a>
					<h4>訂單完成!</h4>
				</div>
				<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
					<button class="btn btn-outline-info" type="submit"
						style="font-size: 1.5rem">
						$ 查詢訂單紀錄 <i class="bi bi-arrow-right-circle"
							style="font-size: 1.8rem"></i>
					</button>
					<input type="hidden" name="action" value="orderRec">
				</div>
			</div>
		</div>
	</form>
	</session>

	<%@ include file="component/footer.txt"%>
</body>

</html>
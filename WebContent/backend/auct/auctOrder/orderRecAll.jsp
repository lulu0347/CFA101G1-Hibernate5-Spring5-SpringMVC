<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="com.auctOrder.model.*"%>
<%@ page import="java.util.List"%>

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
						<h4>拍賣活動訂單紀錄</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">訂單編號</th>
							<th scope="col">訂單總額</th>
							<th scope="col">收件人</th>
							<th scope="col">收件地址</th>
							<th scope="col">收件人電話</th>
							<th scope="col">訂單成立時間</th>
							<th scope="col">訂單狀態</th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col">查看訂單明細</th>
						</tr>
					</thead>
					<jsp:useBean id="auctOrdService" scope="page"
						class="com.auctOrder.model.AuctOrdService" />

					<tbody>
						<c:forEach var="auctOrdVO" items="${auctOrdService.allOrd}">
							<tr>
								<td>${auctOrdVO.auctOrdNo}</td>
								<td>${auctOrdVO.auctOrdAmount}</td>
								<td>${auctOrdVO.receName}</td>
								<td>${auctOrdVO.receAddr}</td>
								<td>${auctOrdVO.recePhone}</td>
								<td><fmt:formatDate value="${auctOrdVO.auctOrdTime}"
										pattern="yyyy-MM-dd hh:mm:ss" /></td>


								<c:choose>
									<c:when test="${auctOrdVO.auctOrdState==0}">
										<td>取消訂單</td>
										<td></td>
										<td></td>
									</c:when>
									<c:when test="${auctOrdVO.auctOrdState==1}">
										<td>待出貨</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/auctOrder/auctOrder.do">
												<button type="submit" class="btn btn-outline-primary btn-sm">出貨</button>
												<input type="hidden" name="auctOrdNo"
													value="${auctOrdVO.auctOrdNo}"> <input
													type="hidden" name="action" value="shipment">
											</form>
										</td>
										<td>
											<form method="post"
												action="<%=request.getContextPath()%>/auctOrder/auctOrder.do">
												<button type="submit" class="btn btn-outline-danger btn-sm">取消訂單</button>
												<input type="hidden" name="auctOrdNo"
													value="${auctOrdVO.auctOrdNo}"> <input
													type="hidden" name="action" value="cancel">
											</form>
										</td>
									</c:when>
									<c:when test="${auctOrdVO.auctOrdState==2}">
										<td>已出貨</td>
										<td></td>
										<td></td>
									</c:when>
									<c:when test="${auctOrdVO.auctOrdState==3}">
										<td>完成訂單</td>
										<td></td>
										<td></td>
									</c:when>
								</c:choose>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
										target="_blank">
										<button type="submit" class="btn btn-outline-success btn-sm">購買明細</button>
										<input type="hidden" name="auctOrdNo"
											value="${auctOrdVO.auctOrdNo}"> <input type="hidden"
											name="action" value="getOneDetl">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
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
	</session>
	<%@include file="../component/footer.txt"%>
</body>

</html>
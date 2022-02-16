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
	<div class="container">
		<div class="row">
			<div class="col">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>交易紀錄</h4>
					</caption>
					<c:if test="${not empty user}">
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
								<th scope="col">查詢商品明細</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="auctOrdVO" items="${auctOrdList}">
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
										</c:when>
										<c:when test="${auctOrdVO.auctOrdState==1}">
											<td>待出貨</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
													name="auctOrdform" id="refund">
													<button type="submit" class="btn btn-outline-danger btn-sm">取消訂單
													</button>
													<input type="hidden" name="action" value="cancelOrd">
													<input type="hidden" name="auctOrdNo"
														value="${auctOrdVO.auctOrdNo}">
												</form>
											</td>
										</c:when>
										<c:when test="${auctOrdVO.auctOrdState==2}">
											<td>已出貨</td>
											<td>
												<form method="post"
													action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
													name="auctOrdform">
													<button type="submit"
														class="btn btn-outline-primary btn-sm">收貨</button>
													<input type="hidden" name="action" value="finishOrd">
													<input type="hidden" name="auctOrdNo"
														value="${auctOrdVO.auctOrdNo}">
												</form>
											</td>
										</c:when>
										<c:when test="${auctOrdVO.auctOrdState==3}">
											<td>完成訂單</td>
											<td></td>
										</c:when>
									</c:choose>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
											name="auctOrdform" target="_blank">
											<button type="submit" class="btn btn-outline-success btn-sm">購買明細
											</button>

											<input type="hidden" name="auctOrdNo"
												value="${auctOrdVO.auctOrdNo}"> <input type="hidden"
												name="action" value="getOneProdDetl">
										</form>
									</td>

								</tr>
							</c:forEach>
					</c:if>
					</tbody>
					<c:if test="${empty user}">
						<div class="container">
							<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
								<button class="btn btn-outline-info" type="submit"
									style="font-size: 1.5rem" disabled="disabled">
									請先登入會員查看交易紀錄</button>
							</div>
						</div>
					</c:if>

					<c:if test="${param.action =='cancelOrd'}">
						<div class="modal fade show" style="display: block; top: 35%"
							id="getRefund">
							<div class="row">
								<div class="modal-dialog">
									<div class="modal-content" style="display:flex;align-items: center;">
										<div class="modal-header">
										<img
										src="<%=request.getContextPath()%>/frontend/auctAct/images/money.png"
										style="width: 250px";>
											<h5 class="modal-title" id="staticBackdropLabel">請至錢包查詢退款</h5>
										</div>
										<div class="row">
											<div class="modal-body">
												<div class="container-fluid">
													<button type="button" class="btn btn-outline-danger"
														onclick="closeRefund()">取消</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	</session>

	<%@ include file="component/footer.txt"%>
</body>

<script>

	function closeRefund() {
		close(getRefund);
	}
	
	//關閉視窗的方法
	function close(element) {
		element.style.display = "none";
	}


</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
				<c:if test="${not empty errorMsgs}"> 
			<div class="modal fade show" style="display: block; top: 25% "id="errorMsgs">
				<div class="row">
					<div class="modal-dialog">
						<div class="modal-content" style="display:flex;align-items: center; width: 300px">
							<div class="modal-header">
								<img src="<%=request.getContextPath()%>/backend/auct/images/cancel.png" style="width: 50px";>
										</div>
										<div class="row">
											<div class="modal-body">
											<h5 class="modal-title" id="staticBackdropLabel">請修正以下錯誤</h5>
											<c:forEach var="message" items="${errorMsgs}">
												<li>${message}</li>
											</c:forEach>
											<br>
												<div class="container-fluid">
													<button type="button" class="btn btn-outline-danger"
														onclick="closeError()">取消</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
			</div>
			<div class="row">
				<div class="col">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>商品訂單</h4>
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
							<c:forEach var="auctOrdDetlVO" items="${auctCart.values()}">
								<tr>
									<c:forEach var="pairs" items="${nameMap}">
										<c:if test="${auctOrdDetlVO.auctActProdNo== pairs.key}">
											<td>${pairs.value}</td>
										</c:if>
									</c:forEach>
									<td>${auctOrdDetlVO.prodPurQty}</td>
									<td>${auctOrdDetlVO.price}</td>
									<td>${auctOrdDetlVO.price*auctOrdDetlVO.prodPurQty}</td>
							</c:forEach>
						</tbody>
					</table>
					總額: ${summary}
				</div>
			</div>
		</div>

		<br>
		<div class="container">
			<div class="row">
				<div class="col">
					<br>
					<caption>
						<h4>填寫收件人資訊</h4>
					</caption>
					<br>
					<div class="input-group input-group-md">
						<span class="input-group-text " id="inputGroup-sizing-lg">收件人姓名</span>
						<input type="text" class="form-control" placeholder="請填入姓名"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-lg" name="receName"
							size="100" value="${auctOrdVO.receName}">
					</div>
					<br>
					<div class="input-group input-group-md">
						<span class="input-group-text " id="inputGroup-sizing-lg">收件地址</span>
						<select id="county" onchange="changeCounty(event.target.value)"
							name="county">
							<option></option>
						</select> 
						<select id="city" name="city">
							<option></option>
						</select> 
						<input type="text" class="form-control" placeholder="請填入地址"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-lg" name="addr"
							size="50">
						<input type="hidden" name="receAddr" value="${auctOrdVO.receAddr}">
					</div>
					<br>
					<div class="input-group input-group-md">
						<span class="input-group-text " id="inputGroup-sizing-lg">收件人電話</span>
						<input type="text" class="form-control"
							placeholder="請填入09XXXXXXXX" aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-lg" name="recePhone"
							size="100" value="${auctOrdVO.recePhone}">
					</div>
					<br>
					<div class="input-group input-group-md">
						<span class="input-group-text " id="inputGroup-sizing-lg">商品備註</span>
						<input type="text" class="form-control" placeholder="請填入商品型號、顏色"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-lg" name="note" size="100"
							value="${auctOrdVO.note}">
					</div>
					<br>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
						<input type="hidden" name="action" value="createOrd">
						<button type="submit" class="btn btn-outline-success">完成送出</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	</session>
	<%@ include file="component/footer.txt"%>
<script src="<%=request.getContextPath()%>/frontend/auctAct/js/database.js"></script>
<script src="<%=request.getContextPath()%>/frontend/auctAct/js/addr.js"></script>
</body>
<script>

	function closeError() {
		close(errorMsgs);
	}
	
	//關閉視窗的方法
	function close(element) {
		element.style.display = "none";
	}


</script>
</html>
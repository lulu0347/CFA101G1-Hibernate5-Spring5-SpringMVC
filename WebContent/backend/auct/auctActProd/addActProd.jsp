<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctProd.model.*"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>


<%
	AuctProdService auctProdSvc = new AuctProdService();
	List<AuctProdVO> auctProdlist = auctProdSvc.getallProd();
	pageContext.setAttribute("auctProdlist", auctProdlist);
%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增拍賣活動商品</title>
<%@ include file="../component/headerCDN.txt"%>
<%@ include file="../css/auctAct.css"%>
</head>
<body>
	<%@ include file="../component/header.txt"%>
	<form method="post"
		action="<%=request.getContextPath()%>/auctActProd/auctactprod.do">
		<session> 
		<c:if test="${not empty adderrorMsgs}"> 
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
											<c:forEach var="message" items="${adderrorMsgs.values()}">
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
		<div class="container">
			<div class="row">
				<div class="col">
					<br>
					<table class="table caption-top">
						<caption>
							<h4>選擇拍賣活動商品</h4>
						</caption>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">商品編號</th>
								<th scope="col">商品名稱</th>
								<th scope="col">商品類別編號</th>
								<th scope="col">商品數量</th>
								<th scope="col">商品價格</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="auctProdVO" items="${auctProdlist}">
								<c:set var="auctProdNo">${auctProdVO.auctProdNo}</c:set>
								<%-- 				<c:set var="auctProdNum">${auctProdVO.auctProdNo}Num</c:set> --%>
								<tr>

									<td><input type="checkbox" name="checkitem"
										value="${auctProdNo}"
										${not empty auctActProdMap[auctProdNo]?'checked':''} /></td>
									<td>${auctProdVO.auctProdNo}</td>
									<td>${auctProdVO.auctProdName}</td>
									<c:choose>
										<c:when test="${auctProdVO.kindNo==1}">
											<td>Phone</td>
										</c:when>
										<c:when test="${auctProdVO.kindNo==2}">
											<td>Computer</td>
										</c:when>
										<c:when test="${auctProdVO.kindNo==3}">
											<td>Camera</td>
										</c:when>
										<c:when test="${auctProdVO.kindNo==4}">
											<td>Watch</td>
										</c:when>
										<c:when test="${auctProdVO.kindNo==5}">
											<td>Others</td>
										</c:when>
									</c:choose>

									<%-- 						<td><input name="${auctProdVO.auctProdNo}Num" value="${param[auctProdNum]}"></td> --%>
									<td><input name="${auctProdVO.auctProdNo}Num"
										value="${auctActProdMap[auctProdNo].auctProdQty}"></td>
									<td><input name="${auctProdVO.auctProdNo}Price"
										value="${auctActProdMap[auctProdNo].auctProdPrice}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="d-grid gap-2  col-6 mx-auto">
						<button class="btn btn-success" type="submit">新增為活動商品</button>
						<input type="hidden" name="action" value="addProd">
					</div>
				</div>
			</div>
		</div>
	</form>
	<%@include file="../component/footer.txt"%>
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



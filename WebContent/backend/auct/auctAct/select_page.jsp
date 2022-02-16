<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>拍賣活動列表</title>
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
		</div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">

				<FORM method="post"
					action="<%=request.getContextPath()%>/auctAct/auctact.do">
					<br>
					<caption>
						<h4>查詢拍賣活動</h4>
					</caption>
					<br>
					<p>輸入拍賣活動號碼(ex:43001)</p>
					<input type="text" name="auctActNo"> <input type="hidden"
						name="action" value="getOneAct">
					<button class="btn btn-outline-success  btn-sm" type="submit">送出</button>
				</FORM>

				<jsp:useBean id="auctActSvc" scope="page"
					class="com.auctAct.model.AuctActService" />
				<hr>
				<FORM method="get"
					action="<%=request.getContextPath()%>/auctAct/auctact.do">
					<p>選擇活動名稱:</p>
					<select name="auctActNo">
						<c:forEach var="auctActVO" items="${auctActSvc.all}">
							<option value="${auctActVO.auctActNo}">${auctActVO.auctActName}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOneAct">
					<button class="btn btn-outline-success  btn-sm" type="submit">送出</button>

				</FORM>

				<hr>
				<form method="post"
					action="<%=request.getContextPath()%>/auctAct/auctact.do">
					<p>選擇活動起始日期</p>
					<select name="auctActNo">
						<c:forEach var="auctActVO" items="${auctActSvc.all}">
							<option value="${auctActVO.auctActNo}">
								<fmt:formatDate value="${auctActVO.auctStartTime}"
									pattern="yyyy-MM-dd" />
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOneAct">
					<button class="btn btn-outline-success btn-sm" type="submit">送出</button>

				</form>
				<hr>
				<a
					href='<%=request.getContextPath()%>/backend/auct/auctAct/listAllAct.jsp'>
					<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
						<button class="btn btn-outline-info" type="button"
							style="font-size: 1.2rem">活動清單</button>
					</div>
				</a>
				<hr>
				<a
					href='<%=request.getContextPath()%>/backend/auct/auctAct/addAct.jsp'>
					<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
						<button class="btn btn-outline-info" type="button"
							style="font-size: 1.2rem">
							新增拍賣活動 <i class="bi bi-arrow-right-circle"
								style="font-size: 1.3rem"></i>
						</button>
					</div>
					<hr>
				</a> <a
					href="<%=request.getContextPath()%>/backend/auct/auctOrder/orderRecAll.jsp">
					<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
						<button class="btn btn-outline-info" type="button"
							style="font-size: 1.2rem">
							返回訂單列表 <i class="bi bi-arrow-right-circle"
								style="font-size: 1.3rem"></i>
						</button>
					</div>
				</a>
			</div>
		</div>
		</div>
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
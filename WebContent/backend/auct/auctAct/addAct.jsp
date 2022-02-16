<%@page import="com.auctPic.model.AuctActPicVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增拍賣活動</title>
<%@ include file="../component/headerCDN.txt" %> 
<%@ include file="../css/auctAct.css" %> 
</head>
<body>
<%@ include file="../component/header.txt"%>
<session>
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
	<form method="post" action="<%=request.getContextPath()%>/auctAct/auctact.do" name="auctActform"
		enctype="multipart/form-data">
	<div class="container">
				<br>
				<caption>
					<h4>新增拍賣活動</h4>
				</caption>
				<br>
				<div class="input-group input-group-lg">
					<span class="input-group-text " id="inputGroup-sizing-lg">拍賣活動名稱</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-lg" name="auctActName"
						size="100" value="${auctActVO.auctActName}">
				</div>
				<br>
				<div class="input-group input-group-lg">
					<span class="input-group-text " id="inputGroup-sizing-lg">拍賣活動說明</span>
					<input type="text" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-lg" name="auctActDesc"
						size="100" value="${auctActVO.auctActDesc}">
				</div>
				<br>
				<div class="input-group input-group-lg">
					<span class="input-group-text " id="inputGroup-sizing-lg">活動起始日期</span>
	
					<input type="text" name="auctStartTime" id="pickdate" 
						value="<fmt:formatDate value="${auctActVO.auctStartTime}"
	                                                pattern="yyyy-MM-dd" />"
						autocomplete="off" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-lg">
	
				</div>
				<br>
				<div class="input-group input-group-lg">
					<span class="input-group-text " id="inputGroup-sizing-lg">活動結束日期</span>
	
					<input type="text" name="auctEndTime" id="pickdate1" type="text"
						size="100"
						value="<fmt:formatDate value="${auctActVO.auctEndTime}"
	                                                pattern="yyyy-MM-dd" />"
						autocomplete="off" class="form-control"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-lg">
	
				</div>
				<br>
				<div class="input-group mb-3">
					<button class="btn btn-outline-primary" type="button"
						id="inputGroupFileAddon03" disabled >活動宣傳Banner1</button>
					<input type="file" class="form-control"
						aria-describedby="inputGroupFileAddon03" aria-label="Upload"
						accept="image/*" name="banner1"
						onchange="loadFile(event,'banner1')">
	
				</div>
				<img id="banner1Show" width="300px"
					src="${photos.banner1!=null? photos.banner1.base64:''}" />
				<hr>
	
				<div class="input-group mb-3">
					<button class="btn btn-outline-primary" type="button"
						id="inputGroupFileAddon03" disabled>活動宣傳Banner2</button>
					<input type="file" class="form-control"
						aria-describedby="inputGroupFileAddon03" aria-label="Upload"
						accept="image/*" name="banner2"
						onchange="loadFile(event,'banner2')">
				</div>
				<img id="banner2Show" width="300px"
					src="${photos.banner2!=null? photos.banner2.base64:''}" />
				<hr>
				<div class="input-group mb-3">
					<button class="btn btn-outline-primary" type="button"
						id="inputGroupFileAddon03" disabled>活動宣傳Banner3</button>
					<input type="file" class="form-control"
						aria-describedby="inputGroupFileAddon03" aria-label="Upload"
						accept="image/*" name="banner3"
						onchange="loadFile(event,'banner3')">
				</div>
				<img id="banner3Show" width="300px"
					src="${photos.banner3!=null? photos.banner3.base64:''}" />
				<hr>
				<div class="input-group mb-3">
					<button class="btn btn-outline-primary" type="button"
						id="inputGroupFileAddon03" disabled>活動限定樣式cart</button>
					<input type="file" class="form-control"
						aria-describedby="inputGroupFileAddon03" aria-label="Upload"
						accept="image/*" name="cart" onchange="loadFile(event,'cart')">
				</div>
				<img id="cartShow" width="100px"
					src="${photos.banner3!=null? photos.cart.base64:''}" />
				<hr>
				<div class="input-group mb-3">
					<button class="btn btn-outline-primary" type="button"
						id="inputGroupFileAddon03"disabled>活動限定 Button</button>
					<input type="file" class="form-control"
						aria-describedby="inputGroupFileAddon03" aria-label="Upload"
						accept="image/*" name="button" onchange="loadFile(event,'button')">
				</div>
				<img id="buttonShow" width="100px"
					src="${photos.banner3!=null? photos.button.base64:''}" />
	
				<div class="d-grid gap-2  col-6 mx-auto">
					<button class="btn btn-success" type="submit">新增活動商品</button>
				</div>
			</div>
			<input type="hidden" name="action" value="insertThenAddProd">
		</form>
	
		<%@ include file="../component/jsCDN.txt" %>
		<%@ include file="../js/auctAct.js" %>
	</session>
	<div class="row" style="height: 50px;"></div>
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
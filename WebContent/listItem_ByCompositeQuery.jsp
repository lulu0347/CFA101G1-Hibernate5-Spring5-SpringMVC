<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有商品資料</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/template.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.icon{
position : absolute;
right:0;
bottom:200px;
}

</style>

</head>

<body>

	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container-fluid">
			
			<button class="btn"><i class="fa fa-home"></i></button>
				<a class="navbar-brand" href="#">CountMEIn</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" href="#">維修服務</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								客服中心 </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">檢舉商品</a></li>
								<li><a class="dropdown-item" href="#">檢舉賣家</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#">查看更多</a></li>
							</ul></li>

					</ul>
					<form class="d-flex">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit">註冊</button>
						<button class="btn btn-outline-success btn-sm" type="submit" onclick="window.location.href('<%=request.getContextPath()%>/frontend/member/login.html')">登入會員</button>
					</form>
				</div>
			</div>
		</nav>
		<div class="MallTop">
			<div style="margin-top: 56px">
				<div class="container">
					<div class="row">
						<div class="col" style="height: 50px;"></div>
					</div>
					<div class="row">
						<div class="col-3">
							<a href="#page-top"> <img
								src="<%=request.getContextPath()%>/resource/Images/logo.png"
								alt="logo" style="height: 100px;" margin-bottom=10px;>
							</a>
						</div>
						<div class="col-6">
							<div class="row ">
								<div class="input-group mb-3">
									<input type="text" class="form-control"
										placeholder="Search Product" aria-label="Search Product"
										aria-describedby="button-addon2">
									<button class="btn btn-outline-warning" type="button"
										id="button-addon2">
										<span class="material-icons-outlined">search</span>
									</button>
								</div>
							</div>
							<div class="row align-items-end" style="height: 80px;">
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="location.href='<%=request.getContextPath()%>/frontend/EShop.jsp'">主要商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >二手商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >拍賣商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >討論交流</button>
								</div>
							</div>
						</div>
						<br>
					</div>
					<div class="row" style="height: 20px"></div>
				</div>
			</div>
		</div>
		<!-- 		</div> -->
	</header>
	<session>
	<div class="container">
		<div class="prod" style="width: 100%;">
			<div class="row">
				<div class="col-3">
					<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
						style="width: 220px;">
						<a href=""
							class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
							<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
							<h4>&nbsp;商品類別</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="<%=request.getContextPath()%>/frontend/listByPhone.jsp" class="nav-link link-dark">
							<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>手機</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByComputer.jsp" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>電腦</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByCamera.jsp" class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>相機</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByWatch.jsp" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>手錶</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByOthers.jsp" class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>配件</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				
<%-- 				<%@ include file="page1.file"%> --%>
				
					
			
			<c:forEach var="itemVO" items="${listItem_ByCompositeQuery}">
					<jsp:useBean id="itemPicSvc" scope="page"
						class="com.itemPic.model.ItemPicService" />
					<div class="col-3">
					<c:forEach var="itemPicVO" items="<%=itemPicSvc.getAllPics()%>">
						<c:if test="${itemVO.itemNo==itemPicVO.itemNo}">
								
								<div  class="card col-3">
									<img  class="card-img-top"
												src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
									<div class="card-body">
										<h5 class="card-title">${itemVO.itemName}</h5>
										<p class="card-text">價格 : ${itemVO.itemPrice}</p>
										<p class="card-text">保固期限 : ${itemVO.warrantyDate}年</p>
										<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
										<input class="btn btn-quantity" type="text" name="quantity" size="3" placeholder="點此填寫數量"> 
										<input type="hidden" name="itemName" value="${itemVO.itemName}"> 
										<input type="hidden" name="itemPrice" value="${itemVO.itemPrice}"> 
										<input type="hidden" name="warrantyDate" value="${itemVO.warrantyDate}"> 
										<input type="hidden" name="itemNo" value="${itemVO.itemNo}"> 
										<input type="hidden" name="action" value="ADD">
										<input class="btn btn-primary" type="submit" name="Submit" value="放入購物車">
										</form>
									</div>
								</div>	
								
						</c:if>
					</c:forEach>
					</div>
			</c:forEach>
					</div>
					
					
					<a class="icon" href="<%=request.getContextPath()%>/frontend/Cart.jsp"><img src="<%=request.getContextPath()%>/resource/Images/nigin.png" width="80" 
					height="50"></a>	
<%-- 			<%@ include file="page2.file"%> --%>
				
			</div>
		</div>
	</div>
	</session>
	<footer>
		<div class="foot">
			<div class="container">
				<div class="row" style="height: 200px;">
					<div class="col-3"></div>
					<div class="col-9"></div>
				</div>
			</div>
		</div>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
		
	</script>
</body>


</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemPic.model.*"%>

<%
	ItemPicVO itemPicVO = (ItemPicVO) request.getAttribute("itemPicVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/template.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<title>單一商品照片</title>

<style>
.div1 {
	width: 240px;
	height: 240px;
}

.div2 {
	height: 60px;
	text-align: left;
	font-size: 18px;
	line-height: 60px;
	border-top: solid #666 1px;
}

.div3 {
	height: 60px;
	text-align: left;
	font-size: 18px;
	line-height: 60px;
	border-top: solid #666 1px;
	border-bottom: solid #666 1px;
}

.div4 {
	margin: 10px 0px 0px 0px;
}

.btn-primary {
	margin: 0px 10px 0px 0px;
	color: #fff;
	background-color: #0d6efd;
	border-color: #0d6efd;
}

#row1 {
	border-top: solid #666 1px;
}
</style>
</head>

<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<button class="btn">
					<i class="fa fa-home"></i>
				</button>
				<a class="navbar-brand" href="#">CountMEIn</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0"></ul>
					<form class="d-flex">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit">註冊</button>
						<button class="btn btn-outline-success btn-sm" type="submit"
							onclick="window.location.href('<%=request.getContextPath()%>/frontend/member/login.html')">
							登入會員</button>
					</form>
				</div>
			</div>
		</nav>
		<div class="MallTop">
			<div style="margin-top: 56px">
				<div class="container">
					<div class="row">
						<div class="col" style="height: 50px"></div>
					</div>
					<div class="row">
						<div class="col-3">
							<a href="#page-top"> <img
								src="<%=request.getContextPath()%>/resource/Images/logo.png"
								alt="logo" style="height: 100px" margin-bottom="10px;" />
							</a>
						</div>
						<div class="col-6">
							<div class="row">
								<div class="input-group mb-3"></div>
							</div>
						</div>
						<br />
					</div>
					<div class="row" style="height: 20px"></div>
				</div>
			</div>
		</div>
		<!-- 		</div> -->
	</header>
	<br />
	<session>
	<div class="container">
		<h3>單一商品照片資料 :</h3>
		<br> <br>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
			<div class="row" id="row1">
				<div class="col-6">
					<div class="div1">
						<c:if test="${itemPicVO.itemPic != null}">
							<img class="img-fluid"
								src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
						</c:if>

						<c:if test="${itemPicVO.itemPic == null }">
							查無圖片
					</c:if>

					</div>
					<div class="col-6"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="div2">商品照片編號 : ${itemPicVO.itemPicNo}</div>
					<div class="div3">商品編號 : ${itemPicVO.itemVO.itemNo}</div>
					<div class="div4">
						<button id="bt2" type="button" class="btn btn-primary"
							onclick="location.href='<%=request.getContextPath()%>/backend/item.jsp'">
							回首頁</button>

						<button id="bt2" type="button" class="btn btn-success"
							onclick="location.href=
						'<%=request.getContextPath()%>/findByItemPicNoToUpdate?itemPicNo=${itemPicVO.itemPicNo}'">
							修改商品照片</button>
					</div>
				</div>

			</div>
			<br>
	</div>
	</session>
	<footer>
		<div class="foot">
			<div class="container">
				<div class="row" style="height: 200px">
					<div class="col-3"></div>
					<div class="col-9"></div>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
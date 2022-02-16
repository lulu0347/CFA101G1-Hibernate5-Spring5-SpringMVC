<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%
    ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
    pageContext.setAttribute("list",list);
%>



<html>
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

	#col-3-1{
		width: 300px;
		height: 300px;
	}
	div-1{
		background-color: #faf;
		width: 300px;
		height: 300px;
		float: left;

	}

	#div-2	{
	
	height: 30px;
	}

	.div-5{
		height:50px;
		line-height: 50px;
		border-bottom: #666 solid 1px;
		
		
	}
	.div-3{
		height:50px;
		line-height: 50px;
		border-bottom: #666 solid 1px;
	}
	.div-4{
		height:50px;
		line-height: 50px;
		border-bottom: #666 solid 1px;
	}

	.span1{
		background-color: rgb(255, 224, 224);
	}
	.span2{
		background-color: rgb(255, 224, 224);
		margin : 0px 20px 0px 0px;
	}
	.span3{
		background-color: rgb(255, 224, 224);
	}
	.span4{
		background-color: rgb(255, 224, 224);
		margin : 0px 20px 0px 0px;
	}
	.span5{
		background-color: rgb(255, 224, 224);
		margin : 0px 20px 0px 0px;
		
	}
	.div-6{
		height:50px;
		line-height: 50px;
		border-bottom: #666 solid 1px;
	}	
	.div-7{
		height:50px;
		line-height: 50px;
		border-top: #666 solid 1px;
		border-bottom: #666 solid 1px;
	}
	.div-8{
		height:50px;
		line-height: 50px;
		border-bottom: #666 solid 1px;
	}

</style>
</head>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
<jsp:useBean id="itemPicSvc" scope="page" class="com.itemPic.model.ItemPicService" />
		<div class="prod" style="width: 100%;">
			<c:forEach var="itemVO" items="${list}">
			<br>
			<div class="row">
				<br>

				<div class="col-3" id="col-3-1">
					<div class="div-1">
							<c:forEach var="itemPicVO" items="<%= itemPicSvc.getAllPics() %>">
								<c:if test="${itemVO.itemNo==itemPicVO.itemNo}">
								<img class="img-fluid" src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
								</c:if>
							</c:forEach>

					</div>
				</div>
				<div class="col-9" id="col-9-1">
					<div class="div-7">
						
							商品編號 : ${itemVO.itemNo}
						
					</div>
					
					<div class="div-3">
						
						<span class="span2">
					<c:choose>
						<c:when test="${itemVO.kindNo == '1'}">
							
							商品分類: 手機
						
						</c:when>
						<c:when test="${itemVO.kindNo == '2'}">
			
							商品分類: 電腦
			
						</c:when>
						<c:when test="${itemVO.kindNo == '3'}">
			
							商品分類: 相機
			
						</c:when>
						<c:when test="${itemVO.kindNo == '4'}">
			
							商品分類: 手錶
			
						</c:when>
						<c:when test="${itemVO.kindNo == '5'}">
			
							商品分類: 配件
						
						</c:when>
		
					</c:choose>
						</span>
						
						<span class="span4">
					<c:choose>
						<c:when test="${itemVO.itemState == '0'}">
							尚未上架
						</c:when>
						<c:when test="${itemVO.itemState == '1'}">
							銷售中
						</c:when>
						
					</c:choose>
						</span>
						<span class="span5">
							商品保固期限 :${itemVO.warrantyDate} 年
						</span>
					</div>
					<div class="div-4">
						商品名稱 : ${itemVO.itemName}
					</div>
					<div class="div-8">
							商品單價 : $ ${itemVO.itemPrice}
					</div>
					<div class="div-5">
						下架時間 : <fmt:formatDate value="${itemVO.soldTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
					<div class="div-6">
						上架時間 : <fmt:formatDate value="${itemVO.launchedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				</div>
				<div class = "row">
				<div class="col-12">
				<hr>
				</div>
				</div>
				
				
				
				<div class="div7">
					${itemVO.itemProdDescription}
				</div>
				
				<div class = "row"></div>
				
				<hr>

				<div class="row">
					<div class="col-2">
						<button id="bt2" type="button" class="btn btn-success" onclick="location.href='<%=request.getContextPath()%>/backend/item.jsp'">回首頁</button>
					</div>
					<div class="col-2">
						<button id="bt2" type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/item/item.do?action=delete&itemNo=${itemVO.itemNo}'">刪除商品</button>
					</div>
					<div class="col-8">
					</div>
				</div>
				<br>
				<div class="row"></div>
				<br>
				<hr>
			</div>
			</c:forEach>
		</div>
	</div>
	</session>
	<footer>
		<div class="foot">
			<div class="container">
				<div class="row" style="height: 200px;">
					<div class="col-3">
					</div>
					<div class="col-9"></div>
				</div>
			</div>
		</div>
	</footer>
</body>


</html>
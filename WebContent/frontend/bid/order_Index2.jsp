<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>訂單功能列表</title>
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
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/Frontpage.jsp">CountMEIn</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <c:if test="${not empty user}">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
							查看訂單
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllOrderByMemNo.jsp">商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdByBuyer.jsp">二手商城買家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdBySeller.jsp">二手商城賣家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec">拍賣商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp">競標商城訂單頁面</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllCollectionByMemNo.jsp">查看收藏商品</a></li>
                            </ul>
                        </li>
 						<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">會員中心</a>
                        </li>
                       	<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/chat/insta.jsp">聊天室</a>
                        </li>
                    </ul>
                    </c:if>
                    
                    
                    <form class="d-flex">
                    <c:if test="${empty user}">
	                     	<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit">註冊</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-outline-success btn-sm" type="submit">登入會員</button>
	                        </a>
	                </c:if>
                    <c:if test="${not empty user}">	                        
	                    	<a href="<%=request.getContextPath()%>/deposit">
	                     	 <button class="btn btn-outline-success btn-sm me-md-4" type="button">我的錢包</button>
	                     	 <input type="hidden" name="action" value="deposit">
	                     	</a>
	                </c:if> 
                    </form>
                    <c:if test="${not empty user}">                    
                    <form class="d-flex" action="../../member/LoginServlet">
                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">登出</button>
                    </form>
                    </c:if>
                    
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
							<a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html"> <img
								src="<%=request.getContextPath()%>/resources/icon/logo.png"
								alt="logo" style="height: 100px;" margin-bottom=10px;>
							</a>
						</div>
						<div class="col-6">
							<div class="row ">
								<div class="input-group mb-3">
								</div>
							</div>
							<div class="row align-items-end" style="height: 80px;">
								<div class="col">
									<a href="../../frontend/EShop.jsp">
										<button type="button" class="btn btn-outline-warning btn-md">主要商城</button>
									</a>
								</div>
								<div class="col">
									<a href="../../frontend/used/listAllProd.jsp">
										<button type="button" class="btn btn-outline-warning btn-md">二手商城</button>
									</a>
								</div>
								<div class="col">
									<a href="../../frontend/auctAct/auctActIndex.jsp">
										<button type="button" class="btn btn-outline-warning btn-md">拍賣商城</button>
									</a>
								</div>
								<div class="col">
									<a href="../../frontend/bid/listAllBid.html">
										<button type="button" class="btn btn-outline-warning btn-md" style="background-color: #dba604;color: white;">競標商城</button>
									</a>
								</div>
								<div class="col">
									<a href="../../forum/index.html">
										<button type="button" class="btn btn-outline-warning btn-md">討論交流</button>
									</a>
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
							<h4>&nbsp;功能列表</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>未結帳的得標商品</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index2.jsp" class="nav-link link-dark" style="background-color: #69d0f5"> <i
									class=""> &nbsp;</i>查詢處理中訂單</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index3.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢未出貨訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index4.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢已出貨訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index5.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢已收貨的訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/bid/order_Index6.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢棄標商品</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				<div class="col-9" id="showPanel">
					
					<%@ include file="order_tabs/listAllBidOrder_ByBidWinnerNo1.jsp" %>
					
				</div>
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
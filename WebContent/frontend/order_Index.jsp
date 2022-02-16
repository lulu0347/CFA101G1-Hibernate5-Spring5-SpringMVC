<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
						<button class="btn btn-outline-success btn-sm" type="submit">員工登入</button>
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
							<div class="row align-items-end" style="height: 80px;">
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="window.location.href('<%=request.getContextPath()%>/frontend/item_Index.jsp')">商品功能</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="window.location.href('<%=request.getContextPath()%>/frontend/order_Index.jsp')">訂單功能</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="window.location.href('<%=request.getContextPath()%>/frontend/detail_Index.jsp')">明細功能</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="window.location.href('<%=request.getContextPath()%>/frontend/collection_Index.jsp')">會員收藏功能</button>
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
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrder.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢所有訂單</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>不同條件搜索訂單</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrderByNotShipped.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢未出貨訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrderByShipped.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢已出貨訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrderByReceipt.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢已收貨的訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrderByReturn.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢被退貨的訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/listAllOrderByCancel.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>查詢被取消的訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/updateOrder.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>修改訂單</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/itemOrder/deleteOrder.jsp" class="nav-link link-dark"> <i
									class=""> &nbsp;</i>刪除訂單</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				<div class="col-9" id="showPanel">
					
					
					
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
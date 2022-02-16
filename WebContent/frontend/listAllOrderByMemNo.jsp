<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemOrder.model.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.itemIOD.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
	Integer memno = memberVO.getMemNo();

	ItemIODService itemIODSvc = new ItemIODService();
	List<ItemIODVO> list = itemIODSvc.getOrderByMemNo(memno);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有訂單資料</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>訂單總覽</title>
<style>
#div1 {
	text-align: center;
	line-height: 48px;
	height: 48px;
	border-bottom: #666 solid 1px;
	border-top: #666 solid 1px;
	text-align: center;
}

#div2 {
	text-align: center;
	line-height: 48px;
	height: 48px;
	border-bottom: #666 solid 1px;
	border-top: #666 solid 1px;
	text-align: center;
}

#div3 {
	text-align: center;
	line-height: 48px;
	height: 48px;
	border-bottom: #666 solid 1px;
	border-top: #666 solid 1px;
	text-align: center;
}

#div4 {
	text-align: center;
	line-height: 48px;
	height: 48px;
	border-bottom: #666 solid 1px;
	border-top: #666 solid 1px;
	text-align: center;
}

#div5 {
	text-align: center;
	line-height: 48px;
	height: 48px;
	border-bottom: #666 solid 1px;
	border-top: #666 solid 1px;
	text-align: center;
}

#row1 {
	margin: 10px 0px 10px 0px;
}

.span1 {
	margin: 0px 0px 0px 42px;
	text-align: center;
}

.span2 {
	margin: 0px 0px 0px 60px;
}

#bt1 {
	margin: 0px 0px 0px 70px;
}

#bt2 {
	margin: 0px 0px 0px 70px;
}

#div6 {
	margin: 20px 0px 0px 0px;
}

#div7 {
	margin: 20px 0px 0px 0px;
}

#div8 {
	margin: 20px 0px 0px 0px;
}

#div9 {
	margin: 20px 0px 0px 0px;
}

#div10 {
	margin: 20px 0px 0px 0px;
}

#hr1 {
	border: #666 solid 1px;
}
</style>
</head>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/frontend/Frontpage.jsp">CountMEIn</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<c:if test="${not empty user}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								查看訂單 </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/frontend/listAllOrderByMemNo.jsp">商城訂單頁面</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/frontend/used/listAllProdByBuyer.jsp">二手商城買家訂單頁面</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/frontend/used/listAllProdBySeller.jsp">二手商城賣家訂單頁面</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec">拍賣商城訂單頁面</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp">競標商城訂單頁面</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/frontend/listAllCollectionByMemNo.jsp">查看收藏商品</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">會員中心</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/chat/insta.jsp">聊天室</a></li>
					</c:if>
				</ul>


				<c:if test="${empty user}">
					<a
						href="<%=request.getContextPath()%>/frontend/member/register.jsp">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit">註冊</button>
					</a>
					<a href="<%=request.getContextPath()%>/frontend/member/login.html">
						<button class="btn btn-outline-success btn-sm">登入會員</button>
					</a>
				</c:if>
				<c:if test="${not empty user}">
					<a
						href="<%=request.getContextPath()%>/deposit">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="button">我的錢包</button> <input type="hidden" name="action"
						value="deposit">
					</a>
				</c:if>
				<c:if test="${not empty user}">
					<form class="d-flex"
						action="<%=request.getContextPath()%>/member/LoginServlet">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit" value="logout" name="action">登出</button>
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
						<a href="#page-top"> <img
							src="<%=request.getContextPath()%>/resource/Images/logo.png"
							alt="logo" style="height: 100px;" margin-bottom=10px;>
						</a>
					</div>
					<div class="col-6">
						<div class="row align-items-end" style="height: 80px;">
							<div class="col">
								<button type="button" class="btn btn-outline-warning btn-md"
									onclick="location.href='<%=request.getContextPath()%>/frontend/EShop.jsp'">主要商城</button>
							</div>
							<div class="col">
								<button type="button" class="btn btn-outline-warning btn-md">二手商城</button>
							</div>
							<div class="col">
								<button type="button" class="btn btn-outline-warning btn-md">拍賣商城</button>
							</div>
							<div class="col">
								<button type="button" class="btn btn-outline-warning btn-md">討論交流</button>
							</div>
						</div>
					</div>
					<br>
				</div>
				<div id="row1" class="row" style="height: 20px"></div>
			</div>
		</div>
	</div>
	</div>
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
						<h4>&nbsp;會員專區</h4>
					</a>
					<hr>
					<ul class="nav nav-pills flex-column mb-auto ">
						<li class="nav-item"><a href="#" class="nav-link link-dark">
								<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>瀏覽訂單頁面</i><br>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>瀏覽商品頁面</i><br>
						</a></li>
						<li><a
							href="<%=request.getContextPath()%>/frontend/member/memberUpdate.jsp"
							class="nav-link link-dark"> <i class="bi bi-camera fs-2 mb-3">
									&nbsp;</i>會員資料管理</i><br>
						</a></li>
						<li><a
							href="<%=request.getContextPath()%>/frontend/member/agreeForm.jsp"
							class="nav-link link-dark"> <i
								class="bi bi-headset fs-2 mb-3"> &nbsp;</i>申請賣家功能</i>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>查看通知</i>
						</a></li>
					</ul>
					<hr>
					<div class="dropdown">
						<a href="#" class="d-flex align-items-center link-dark"> </a>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-9">
				<div class="row">
					<div class="col-2" id="div1">訂單編號</div>
					<div class="col-2" id="div2">購買時間</div>
					<div class="col-2" id="div5">訂單狀態</div>
					<div class="col-3" id="div3">訂單明細</div>
					<div class="col-3" id="div4">操作</div>
				</div>
				<c:forEach var="itemIODVO" items="${list}">
					<div class="row">
						<div class="col-1" id="div6">
							<span class="span1"> ${itemIODVO.orderNo} </span>
						</div>
						<div class="col-4" id="div7">
							<span class="span2"> <fmt:formatDate
									value="${itemIODVO.tranTime}" pattern="yyyy-MM-dd HH:mm:ss" />

							</span>
						</div>
						<div class="col-1" id="div8">
							<c:choose>
								<c:when test="${itemIODVO.orderState == '0'}">
									<td>尚未出貨</td>
								</c:when>
								<c:when test="${itemIODVO.orderState == '1'}">
									<td>已出貨</td>
								</c:when>
								<c:when test="${itemIODVO.orderState == '2'}">
									<td>已收貨</td>
								</c:when>
								<c:when test="${itemIODVO.orderState == '3'}">
									<td>已退貨</td>
								</c:when>
								<c:otherwise>
									<td>已取消</td>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-3" id="div9">
							<button id="bt1" type="button" class="btn btn-light"
								onclick="location.href='<%=request.getContextPath()%>/itemIOD/itemIOD.do?action=viewMyDetail&orderNo=${itemIODVO.orderNo}'">查看明細</button>
						</div>
						<c:choose>
							<c:when test="${itemIODVO.orderState == '2'}">
								<td>
									<div class="col-3" id="div10">
										<button id="bt2" type="button" class="btn btn-info"
											onclick="location.href='<%=request.getContextPath()%>/itemIOD/itemIOD.do?action=userReturn&orderNo=${itemIODVO.orderNo}&memNo=${user.memno}&orderTotal=${itemIODVO.orderTotal}&eCash=${user.ecash}'">退貨</button>
									</div>
								</td>
							</c:when>
							<c:when test="${itemIODVO.orderState == '0'}">
								<div class="col-3" id="div10">
									<button id="bt2" type="button" class="btn btn-success"
										onclick="location.href='<%=request.getContextPath()%>/itemIOD/itemIOD.do?action=usercancel&orderNo=${itemIODVO.orderNo}&memNo=${user.memno}&orderTotal=${itemIODVO.orderTotal}&eCash=${user.ecash}'">取消訂單</button>
								</div>
							</c:when>
							<c:when test="${itemIODVO.orderState == '1'}">
									<div class="col-3" id="div10">
									<button id="bt2" type="button" class="btn btn-danger"
										onclick="location.href='<%=request.getContextPath()%>/itemIOD/itemIOD.do?action=userReceipt&orderNo=${itemIODVO.orderNo}&memNo=${user.memno}'">確認收貨</button>
								</div>
								</c:when>
							<c:otherwise>
									<div class="col-3" id="div10">
									</div>
							</c:otherwise>
						</c:choose>

					</div>
				</c:forEach>
				<hr id="hr1">
			</div>

		</div>
	</div>
</div>
<img src="" alt=""> </session>

<footer>
	<div class="foot">
		<div class="container">
			<div class="row" style="height: 200px;"></div>
		</div>
	</div>
</footer>
<body>
</body>
</html>

















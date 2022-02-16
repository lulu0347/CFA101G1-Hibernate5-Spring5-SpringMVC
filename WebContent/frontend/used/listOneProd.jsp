<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.usedpic.model.*"%>
<%@ page import="com.usedmsg.model.*"%>
<%@ page import="com.used.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	List<UsedPicVO> picList = new ArrayList<UsedPicVO>();
	picList = (ArrayList) request.getAttribute("picList");
	List<UsedMsgVO> msgList = new ArrayList<UsedMsgVO>();
	msgList = (ArrayList) request.getAttribute("msgList");
	List<MemberVO> memList = new ArrayList<MemberVO>();
	memList = (ArrayList) request.getAttribute("memList");
	UsedVO usedVO = (UsedVO) request.getAttribute("usedVO");
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../used/component/headComponent.txt"%>
<%@ include file="../css/index.css"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/slick-theme.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/slick.css">
<title>listOneProd</title>
<style>
div.b {
	margin-left: 1.2rem;
}

table {
	width: 100%;
}

th.back {
	display: none;
}

div.side-bar {
	text-align: center;
	background-color:#F8F8F8;
	padding-top:10px;
}

div.side-bar span {
	word-wrap: break-word;
}

button.slick-next:before, .slick-prev:before {
	color: black;
}

button.slick-next.slick-arrow {
	display: none;
}

div.item-detail {
	font-size: 2rem;
	color: grey;
}

img.items {
	width: 300px;
	height: 200px;
}

div#reportSpace {
	display: none;
}

button.btn-outline-danger {
	width: 3rem;
}

button.mall {
	font-size: 5px;
}

div.msg-form {
	text-align: right;
}

input.text {
	height: 90px;
	width: 48.4rem;
}

td.msg {
	border-left: 1px black solid;
	border-bottom: 5px white solid;
	height: 90px;
	width: 49.3rem;
	padding: 5px;
	background-color: #F8F8F8;
}

@media ( max-width : 768px) {
	button.mall {
		font-size: 5px;
	}
}

@media ( max-width : 576px) {
	input.text {
		height: 90px;
		width: 25rem;
	}
}

@media ( min-width : 576px ) and ( max-width : 768px ) {
	input.text {
		height: 90px;
		width: 20rem;
	}
}

@media ( min-width : 768px ) and ( max-width : 992px ) {
	input.text {
		height: 90px;
		width: 28rem;
	}
}

@media ( min-width : 992px ) and ( max-width : 1200px ) {
	input.text {
		height: 90px;
		width: 39.8rem;
	}
}
td.name {
	width:4rem;
}
</style>
</head>
<body>
	<header>
		<%@ include file="../used/component/navComponent.txt"%>
		<%@ include file="../used/component/searchComponent.txt"%>
	</header>
	<!-- /header -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-3">
				<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
					style="width: 220px;">
					<a href=""
						class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
						<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
						<h4>&nbsp;Category</h4>
					</a>
					<hr>
					<ul class="nav nav-pills flex-column mb-auto ">
						<li class="nav-item"><a href="#" class="nav-link link-dark">
								<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>Phone</i><br>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>Computer</i><br>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-camera fs-2 mb-3"> &nbsp;</i>Camera</i><br>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>Watch</i>
						</a></li>
						<li><a href="#" class="nav-link link-dark"> <i
								class="bi bi-headset fs-2 mb-3"> &nbsp;</i>Others</i>
						</a></li>
					</ul>
					<hr>
					<div class="dropdown">
						<a href="#" class="d-flex align-items-center link-dark"> </a>
						</ul>
					</div>
				</div>
			</div>
			<div class="b col-sm-8 col-10">
				<div class="f">
					<c:forEach var="usedPicVO" items="${picList}">
						<img
							src="<%=request.getContextPath()%>/used/DBGifReader5?usedPicNo=${usedPicVO.usedPicNo}">
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row justify-items-center">
			<div class="side-bar col-sm-3 col-12">

				<div class="autoplay">
				<c:forEach var="usedPicVO" items="${picList}">
					<img class="side-pic"
						src="<%=request.getContextPath()%>/used/DBGifReader5?usedPicNo=${usedPicVO.usedPicNo}" alt="">
				</c:forEach>
				</div>
				<span>${usedVO.usedName}</span><br> <span>NT$:${usedVO.usedPrice}</span><br>
			</div>
			<div class="col-sm-9 col-12">
				<div class="item-detail">商品簡介</div>
				<c:if test="${usedVO.sellerNo!=sessionScope.user.memno}">
					<form method="post" action="<%=request.getContextPath()%>/used/usedServlet.do">
					<input type="hidden" name="action" value="checkout">
					<input type="hidden" name="usedNo" value="${usedVO.usedNo}">
					<input type="hidden" name="memNo" value="${sessionScope.user.memno}">
						<label id="buy" class="btn btn-info"> <i class="far fa-credit-card"></i>
							<input type="submit" style="display: none">立即購買
						</label>
					</form>
				</c:if>
				<div>${usedVO.usedProdDescription}</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row justify-content-end">
			<div class="col-sm-9 col-12">
				<table>
					<c:forEach var="usedMsgVO" items="${msgList}">
						<tr>
						<c:forEach var="memberVO" items="${memList}">
						<c:if test="${memberVO.memno==usedMsgVO.memNo}">
						<td class="name">${memberVO.memname}</td>
						</c:if>
						</c:forEach>
							<td class="msg">${usedMsgVO.usedMsgText}</td>
							<c:if test="${usedMsgVO.memNo==sessionScope.user.memno}">
								<td class="del-td">
									<form method="post"
										action="<%=request.getContextPath()%>/usedMsg/usedMsgServlet.do">
										<button type="submit" class="btn btn-outline-danger btn-sm">刪除</button>
										<input type="hidden" name="usedMsgNo"
											value="${usedMsgVO.usedMsgNo}"> <input type="hidden"
											name="usedNo" value="${usedVO.usedNo}"> <input
											type="hidden" name="action" value="deleteMsg">
									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

			<c:if test="${sessionScope.user.memno!=null}">
		<div class="row justify-content-end">
			<div class="col-sm-9 col-12 msg-form">
				<form METHOD="post"
					ACTION="<%=request.getContextPath()%>/usedMsg/usedMsgServlet.do">
					<input class="text" type="text" name="usedMsgText"
						placeholder="請輸入50字以內">
					<button class="btn btn-outline-warning btn-sm" type="submit">留言</button>
					<input type="hidden" name="action" value="addMsg"> <input
						type="hidden" name="usedNo" value="${usedVO.usedNo}">
						<input type="hidden" name="memNo" value="${sessionScope.user.memno}">
				</form>
			</div>
			<div class="col-sm-9 col-12 msg-form">
				<div id="reportSpace">
					<form method="post"
						action="<%=request.getContextPath()%>/usedReport/usedReportServlet.do">
						<input class="text" type="text" name="usedReportReason"
							placeholder="請輸入檢舉原因">
						<button class="btn btn-outline-warning btn-sm" id="reportSubmit"
							type="submit">送出</button>
						<input type="hidden" name="sellerNo" value="${usedVO.sellerNo}">
						<input type="hidden" name="usedNo" value="${usedVO.usedNo}">
						<input type="hidden" name="memNo" value="${sessionScope.user.memno}">
						<input type="hidden" name="action" value="report">
					</form>
				</div>
				<div>
					<button class="btn btn-outline-warning btn-sm" id="reportBtn">檢舉</button>
				</div>
			</div>
		</div>
			</c:if>
	</div>
	<footer> </footer>
	<script defer
		src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"
		integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/jquery-3.6.0.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/slick.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/package/dist/sweetalert2.all.min.js"></script>
	<script>
		$('.f').slick({
			dots : true,
			infinite : true,
			adaptiveHeight : true,
			speed : 500,
			fade : true,
			cssEase : 'linear'
		})
		$('.autoplay').slick({
			arrows : false,
			adaptiveHeight : true,
			slidesToShow : 1,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
		});
		let reportDiv = document.getElementById("reportSpace");
		let reportB = document.getElementById("reportBtn");
		reportBtn.onclick = function() {
			reportDiv.setAttribute("style", "display:block");
			reportB.setAttribute("style", "display:none");
		}
		let reportS = document.getElementById("reportSubmit");
		reportSubmit.onclick = function() {
			Swal.fire({
				title : '您的檢舉已送出！',
				text : '3秒後自動關閉',
				timer : 3000,
				showConfirmButton : false
			});
		}
		let buying = document.getElementById("buy");
		buy.onclick = function() {
			Swal.fire({
				title : '錢包餘額確認中...',
				text : '3秒後自動關閉',
				timer : 3000,
				showConfirmButton : false
			});
		}
	</script>
</body>
</html>
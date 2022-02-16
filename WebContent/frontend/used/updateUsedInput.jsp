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
	background-color: #F8F8F8;
	padding-top: 10px;
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
	padding: 10px;
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
</style>
</head>
<body>


	<header>
		<%@ include file="../used/component/navComponent.txt"%>
		<%@ include file="../used/component/searchComponent.txt"%>
	</header>
	<!-- /header -->
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-5">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div class="f">
					<c:forEach var="usedPicVO" items="${picList}">
						<img
							src="<%=request.getContextPath()%>/used/DBGifReader5?usedPicNo=${usedPicVO.usedPicNo}">
					</c:forEach>
				</div>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/used/usedServlet.do"
					style="margin-bottom: 0px;">
					<table>
						<tr>
							<td>商品名稱:</td>
							<td><input type="text" name="usedName"
								value="<%=usedVO.getUsedName()%>"></td>
						</tr>
						<c:if test='${usedVO.buyerNo!=0}'>
							<tr>
								<td>買家編號:</td>
								<td><input type="text" name="buyerNo"
									value="<%=usedVO.getBuyerNo()%>"></td>
							</tr>
						</c:if>
						<tr>
							<td>商品價格:</td>
							<td><input type="text" name="usedPrice"
								value="<%=usedVO.getUsedPrice()%>"></td>
						</tr>
						<tr>
							<td>商品狀態:</td>
							<c:if test="${usedVO.usedState==0}">
								<td><select name="usedState">
										<option value="0" selected>上架中</option>
										<option value="1">已下單</option>
										<option value="2">已出貨</option>
										<option value="3">已取消</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.usedState==1}">
								<td><select name="usedState">
										<option value="0">上架中</option>
										<option value="1" selected>已下單</option>
										<option value="2">已出貨</option>
										<option value="3">已取消</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.usedState==3}">
								<td><select name="usedState">
										<option value="0">上架中</option>
										<option value="3" selected>已取消</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.usedState==2}">
					<td>已出貨<input type="hidden" name="usedState"
									value="<%=usedVO.getUsedState()%>"></td>
							</c:if>
							<c:if test="${usedVO.usedState==4}">
								<td><select name="usedState">
										<option value="0">上架中</option>
										<option value="4" selected>已下架</option>
								</select></td>
							</c:if>
						</tr>
						<tr>
							<td>商品描述:</td>
							<td><textArea rows="8" cols="36" name="usedProdDescription"><%=usedVO.getUsedProdDescription()%></textArea></td>
						</tr>
						<tr>
							<td>商品種類:</td>
							<c:if test="${usedVO.kindNo==1}">
								<td><select name="kindNo">
										<option value="1" selected>手機</option>
										<option value="2">電腦</option>
										<option value="3">手錶</option>
										<option value="4">相機</option>
										<option value="5">配件類</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.kindNo==2}">
								<td><select name="kindNo">
										<option value="1">手機</option>
										<option value="2" selected>電腦</option>
										<option value="3">手錶</option>
										<option value="4">相機</option>
										<option value="5">配件類</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.kindNo==3}">
								<td><select name="kindNo">
										<option value="1">手機</option>
										<option value="2">電腦</option>
										<option value="3" selected>手錶</option>
										<option value="4">相機</option>
										<option value="5">配件類</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.kindNo==4}">
								<td><select name="kindNo">
										<option value="1">手機</option>
										<option value="2">電腦</option>
										<option value="3">手錶</option>
										<option value="4" selected>相機</option>
										<option value="5">配件類</option>
								</select></td>
							</c:if>
							<c:if test="${usedVO.kindNo==5}">
								<td><select name="kindNo">
										<option value="1">手機</option>
										<option value="2">電腦</option>
										<option value="3">手錶</option>
										<option value="4">相機</option>
										<option value="5" selected>配件類</option>
								</select></td>
							</c:if>
						</tr>
						<tr>
							<td><label class="btn btn-info">送出<input
									type="submit" style="display: none"></label><input
								type="hidden" name="action" value="updateUsed2"> <input
								type="hidden" name="usedNo" value="<%=usedVO.getUsedNo()%>">
							</td>
						</tr>
					</table>
				</FORM>

			</div>
		</div>
	</div>
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
	</script>
</body>
</html>
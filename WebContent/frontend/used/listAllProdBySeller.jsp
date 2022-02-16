<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.used.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	UsedService usedSvc = new UsedService();
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
	Integer sellerNo = null;
	sellerNo = new Integer(memberVO.getMemNo());
	List<UsedVO> list = usedSvc.getAllProdBySeller(sellerNo);
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../used/component/headComponent.txt"%>
<%@ include file="../css/index.css"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/css/bootstrap.min.css">
<title>listAllprod.jsp</title>
<style>
img {
	max-width: 100%;
}

img.card-img-top {
	width: 20rem;
	height: 15rem;
	object-fit: contain;
}

img.logo {
	height: 2rem;
}

ul.action-list {
	float: right;
	display: block;
	margin-bottom: 0;
	margin-right: 25px;
}

ul.action-list li {
	list-style: none;
	display: inline-block;
	vertical-align: middle;
	margin-right: 15px;
}

ul.action-list li a {
	text-decoration: none;
}

ul.action-list li span {
	font-size: 1rem;
	color: #505050;
	margin-left: 5px;
}

a.signin, a.signup, a.cart {
	display: inline-block;
	margin-right: 15px;
}

img.signin, img.signup, img.cart {
	height: 2rem;
}

div.b {
	margin-left: 1.2rem;
}

button.slick-next:before, .slick-prev:before {
	color: black;
}

div.side-bar {
	border: 1px solid red;
}

input.header-ham {
	border: 0;
	background-color: transparent;
	float: right;
	display: none;
}

label.ham {
	float: right;
	display: none;
}

img.ham {
	height: 2rem;
	display: none;
	float: right;
}

@media ( max-width : 768px) {
	ul.action-list {
		max-height: 0px;
		overflow: hidden;
	}
	label.ham, img.ham {
		display: block;
	}
	button.mall {
		font-size: 5px;
	}
}

table.prods {
	border-top: 3px #FFD382 solid;
	border-bottom: 3px #82FFFF solid;
}

table.prods th, table.prods td {
	/*     border: 1px dotted #F00; */
	padding: 5px;
}

tr.buttom {
	border-bottom: 3px #82FFFF solid;
}

div.add {
	text-align:center;
	background-color:#FFD382;
	height:7rem;
}
</style>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<header>
		<%@ include file="../used/component/navComponent.txt"%>
		<%@ include file="../used/component/searchComponent.txt"%>
	</header>
	<div class="countainer">
		<div class="row justify-content-center">
		<div class="col-1 add">新增商品<a href="<%=request.getContextPath()%>/frontend/used/addUsed.jsp"><img alt="" src="<%=request.getContextPath()%>/frontend/used/add.PNG"></a></div>
			<div class="col-8">

				<table class="prods" border="1">

					<%@ include file="pages/page1.file"%>
					<c:forEach var="usedVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>產品編號</td>
							<td>${usedVO.usedNo}</td>
						</tr>
						<tr>
							<td>產品種類</td>
							<td><c:if test="${usedVO.kindNo==1}">手機</c:if> <c:if
									test="${usedVO.kindNo==2}">電腦</c:if> <c:if
									test="${usedVO.kindNo==3}">手錶</c:if> <c:if
									test="${usedVO.kindNo==4}">相機</c:if> <c:if
									test="${usedVO.kindNo==5}">配件類</c:if></td>
						</tr>
						<c:if test='${usedVO.buyerNo!=0}'>
							<tr>
								<td>買家編號</td>
								<td>${usedVO.buyerNo}</td>
							</tr>
						</c:if>
						<tr>
							<td>產品名稱</td>
							<td>${usedVO.usedName}</td>
						</tr>
						<tr>
							<td>產品價格</td>
							<td>${usedVO.usedPrice}</td>
						</tr>
						<tr>
							<td>產品狀態</td>
							<td><c:if test="${usedVO.usedState==1}">已下單</c:if> <c:if
									test='${usedVO.usedState==""}'>上架中</c:if> <c:if
									test='${usedVO.usedState==2}'>已出貨</c:if> <c:if
									test='${usedVO.usedState==3}'>已取消</c:if>
									<c:if test='${usedVO.usedState==4}'>已下架</c:if>
									</td>
						</tr>
						<tr>
							<td>上架時間</td>
							<td><fmt:formatDate value="${usedVO.usedLaunchedTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<c:if test='${usedVO.soldTime!=null}'>
							<tr>
								<td>下架時間</td>
								<td><fmt:formatDate value="${usedVO.soldTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:if>
						<c:if test='${usedVO.receiverName!=null}'>
							<tr>
								<td>收件人</td>
								<td>${usedVO.receiverName}</td>
							</tr>
						</c:if>
						<c:if test='${usedVO.receiverAddress!=null}'>
							<tr>
								<td>收件地址</td>
								<td>${usedVO.receiverAddress}</td>
							</tr>
						</c:if>
						<c:if test='${usedVO.receiverPhone!=null}'>
							<tr>
								<td>收件人電話</td>
								<td>${usedVO.receiverPhone}</td>
							</tr>
						</c:if>
						<td>商品描述</td>
						<td>${usedVO.usedProdDescription}</td>

						<tr class="buttom">

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/used/usedServlet.do"
									style="margin-bottom: 0px;">
									<label class="btn btn-info">預覽 <input type="submit"
										style="display: none">
									</label> <input type="hidden" name="usedNo" value="${usedVO.usedNo}">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>"> <input
										type="hidden" name="whichPage" value="<%=whichPage%>">
									<input type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/used/usedServlet.do"
									style="margin-bottom: 0px;">
									<label class="btn btn-outline-warning">修改 <input
										type="submit" style="display: none">
									</label> <input type="hidden" name="usedNo" value="${usedVO.usedNo}">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>"> <input
										type="hidden" name="whichPage" value="<%=whichPage%>">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/page2.file"%>
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

<script type="text/javascript">
	$('.f').slick({
		dots : true,
		infinite : true,
		speed : 500,
		fade : true,
		cssEase : 'linear'
	});
	var filereader_support = typeof FileReader != 'undefined';

	if (!filereader_support) {
		alert("No FileReader support");
	}

	acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
	};

	function previewImage() {
		var upfile = document.getElementById("upfile");
		upfile.addEventListener("change", function(event) {
			var files = event.target.files || event.dataTransfer.files;
			for (var i = 0; i < files.length; i++) {
				previewfile(files[i])
			}
		}, false);
	}

	function previewfile(file) {
		if (filereader_support === true && acceptedTypes[file.type] === true) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var image = new Image();
				image.src = event.target.result;
				image.width = 100;
				holder.appendChild(image);
			};
			reader.readAsDataURL(file);
		} else {
			holder.innerHTML += "<p>" + "filename: <strong>" + file.name
					+ "</strong><br>" + "ContentTyp: <strong>" + file.type
					+ "</strong><br>" + "size: <strong>" + file.size
					+ "</strong> bytes</p>";
		}
	}
</script>
</body>
</html>
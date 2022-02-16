<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.used.model.*"%>
<%@ page import="com.usedpic.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	UsedService usedSvc = new UsedService();
	List<UsedVO> list = (ArrayList<UsedVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list);
	UsedPicService usedPicSvc = new UsedPicService();
	List<UsedPicVO> picList = usedPicSvc.getAllUsedPic();
	pageContext.setAttribute("picList", picList);
	MemberVO memberVO = (MemberVO)session.getAttribute("user");
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
}

@media ( max-width : 768px) {
	button.mall {
		font-size: 5px;
	}
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
	<div class="container-fluid">
		<div class="row align-items-center justify-content-between">
<div class="col-3">
					<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
						style="width:220px;">
						<a href=""
							class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
							<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
							<h4>&nbsp;Category</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="<%=request.getContextPath()%>/used/usedServlet.do?action=searchKind&name=kind&value=1" class="nav-link link-dark">
									<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>Phone</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/used/usedServlet.do?action=searchKind&name=kind&value=2" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>Computer</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/used/usedServlet.do?action=searchKind&name=kind&value=4" class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>Camera</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/used/usedServlet.do?action=searchKind&name=kind&value=3" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>Watch</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/used/usedServlet.do?action=searchKind&name=kind&value=5" class="nav-link link-dark"> <i
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
				<c:forEach var="usedVO" items="${list}">
				<c:if test="${usedVO.usedState==''}">
					<div class="col-md-4 col-sm-6">
						<div class="card align-items-center">
							<img
								src="<%=request.getContextPath()%>/used/DBGifReader4?usedNo=${usedVO.usedNo}"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">${usedVO.usedName}</h5>
								<p class="card-text">NT ${usedVO.usedPrice}</p>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/used/usedServlet.do">
										<input type="hidden" name="usedNo" value="${usedVO.usedNo}"> <input
											type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="查看商品">
									</FORM>
							</div>
						</div>
					</div>
					</c:if>
				</c:forEach>
		</div>
	</div>

</body>
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
</html>
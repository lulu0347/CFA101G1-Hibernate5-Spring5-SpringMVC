<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.used.model.*"%>
<%@ page import="com.usedreport.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	UsedReportService usedReportSvc = new UsedReportService();
	List<UsedReportVO> list = usedReportSvc.getAllUsedReport();
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
			<div class="col-6">
				<table class="prods">
					<%@ include file="pages/page1.file"%>
					<c:forEach var="usedReportVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>檢舉人編號</td>
								<td>${usedReportVO.memNo}</td>
							</tr>
							<tr>
								<td>產品編號</td>
								<td>${usedReportVO.usedNo}</td>
							</tr>
							<tr>
								<td>賣家編號</td>
								<td>${usedReportVO.reportedMemNo}</td>
							</tr>
							<tr>
								<td>檢舉狀態</td>
								<td>
								<c:if test="${usedReportVO.usedReportState==''}">
								未審核
								</c:if>
								<c:if test="${usedReportVO.usedReportState==1}">
								未審核
								</c:if>
								<c:if test="${usedReportVO.usedReportState==2}">
								已審核
								</c:if>
								</td>
							</tr>
							<tr>
								<td>檢舉時間</td>
								<td>${usedReportVO.usedReportTime}</td>
							</tr>
							<tr>
								<td>檢舉原因</td>
								<td>${usedReportVO.usedReportReason}</td>
							</tr>
							<td>通知賣家</td>
							<td>${usedReportVO.usedReportNotice}</td>
							<tr>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/usedReport/usedReportServlet.do"
										style="margin-bottom: 0px;">
										<label class="btn btn-outline-warning">審核<input type="submit" style="display:none"></label> <input
											type="hidden" name="memNo" value="${usedReportVO.memNo}">
										<input type="hidden" name="usedNo"
											value="${usedReportVO.usedNo}"> <input type="hidden"
											name="action" value="getOne_For_Update">
									</FORM>
								</td>
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/slick.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/package/dist/sweetalert2.all.min.js"></script>
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
			if (filereader_support === true
					&& acceptedTypes[file.type] === true) {
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

		let delProd = document.getElementById("deleteProd");
		deleteProd.onclick = function() {
			Swal.fire({
				title : 'Are you sure?',
				text : "You won't be able to revert this!",
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Yes, delete it!'
			});
		}
	</script>
</body>
</html>
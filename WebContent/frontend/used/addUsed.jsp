<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.used.model.*"%>
<%@ page import="com.usedpic.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	UsedService usedSrv = new UsedService();
	List<UsedVO> list = usedSrv.getAllUsed();
	pageContext.setAttribute("list", list);
%>
<%
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
<style>
label.btn-info {
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
}

label.btn {
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

input.text {
	height: 90px;
	width: 48.8rem;
}

div#holder {
	background-color:#F8F8F8;
}
</style>
<title>addUsed.jsp</title>
</head>
<body>
	<header>
		<%@ include file="../used/component/navComponent.txt"%>
		<%@ include file="../used/component/searchComponent.txt"%>
	</header>
	<div class="countainer">
		<div class="row justify-content-center">
			<div class="col-4">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
				<FORM METHOD="post" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/used/usedServlet.do"
					style="margin-bottom: 0px;">
					<table>
						<tr>
							<td>商品名稱:</td>
							<td><input type="text" name="usedName"
								value="<%=(usedVO == null) ? "測試商品" : usedVO.getUsedName()%>"></td>
						</tr>
						<tr>
							<td><input type="hidden" name="sellerNo"
								value="${sessionScope.user.memno}"></td>
						</tr>
						<tr>
							<td>商品價格:</td>
							<td><input type="text" name="usedPrice"
								value="<%=(usedVO == null) ? "1000" : usedVO.getUsedPrice()%>"></td>
						</tr>
						<tr>							<td>商品種類:</td>
							<td><select name="kindNo">
							<option value="1" selected>手機</option>
							<option value="2">電腦</option>
							<option value="3">手錶</option>
							<option value="4">相機</option>
							<option value="5">配件類</option>
					</select></td>
						</tr>
						<tr>
							<td>商品描述:</td>
							<td><textArea rows="8" cols="36" name="usedProdDescription"><%=(usedVO == null) ? "" : usedVO.getUsedProdDescription()%></textArea></td>
						</tr>
						<tr>
							<td><label class="btn btn-info"> <i
									class="far fa-image"></i>上傳圖片 <input style="display: none"
									type="file" name="path" multiple onclick="previewImage()"
									id="upfile">
							</label></td>
							<td>
								<button class="btn btn-outline-warning btn-sm" type="submit">新增商品</button>
								<input type="hidden" name="action" value="addUsed">
							</td>
						</tr>
					</table>
				</FORM>
			</div>
			<div><div class="col-4"><h4>圖片預覽:</h4></div>
			<div class="row justify-content-center" id="holder"></div>
			</div>
		</div>
	</div>

	<%
		if (request.getAttribute("addUsed") != null) {
	%>
	<jsp:include page="/frontend/used/listAllProd.jsp" />
	<%
		}
	%>
	<script defer
		src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"
		integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/jquery-3.6.0.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/css/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		// 	$('.f').slick({
		// 		dots : true,
		// 		infinite : true,
		// 		speed : 500,
		// 		fade : true,
		// 		cssEase : 'linear'
		// 	});
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
					image.width = 300;
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
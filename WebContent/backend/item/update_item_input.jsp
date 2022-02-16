<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="java.util.*"%>

<%
	ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //ItemServlet.java (Concroller) 存入req的itemVO物件 (包括幫忙取出的itemVO, 也包括輸入資料錯誤時的itemVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改商品</title>
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

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<button class="btn">
					<i class="fa fa-home"></i>
				</button>
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
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit">註冊</button>
						<button class="btn btn-outline-success btn-sm" type="submit">登入會員</button>
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
							<div class="row "></div>
						</div>
						<br>
					</div>
					<div class="row" style="height: 20px"></div>
				</div>
			</div>
		</div>
	</header>

	<h3>商品資料修改</h3>

	<h3>資料修改:</h3>

	<form:form method="POST" action="update" modelAttribute="itemVO">
		<table>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><form:input type="TEXT" path="itemNo" size="45" value="${itemVO.itemNo}" readonly="true"/></td>
			</tr>

			<tr>
				<td>商品類別編號:<font color=red><b>*</b></font></td>
				<td><form:select path="productKindVO.kindNo" id="productKindVO.kindNo" items="${kindData}" /></td>
			</tr>

			<tr>
				<td>商品名稱:<font color=red><b>*</b></font></td>
				<td><form:input type="TEXT" path="itemName" size="45" /></td>
			</tr>

			<tr>
				<td>商品單價:<font color=red><b>*</b></font></td>
				<td><form:input type="TEXT" path="itemPrice" size="45" /></td>
			</tr>

			<tr>
				<td>上架狀態:<font color=red><b>*</b></font></td>
				<td><form:input type="TEXT" path="itemState" size="1" /></td>
			</tr>

			<tr>
				<td>下架時間:<font color=red><b>*</b></font></td>
				<td><form:input path="soldTime" id="f_date1" type="text" /></td>

			</tr>

			<tr>
				<td>上架時間:<font color=red><b>*</b></font></td>
				<td><form:input path="launchedTime" id="f_date2" type="text" /></td>
			</tr>

			<tr>
				<td>商品保固年限:<font color=red><b>*</b></font></td>
				<td><form:input type="TEXT" path="warrantyDate" size="5" /></td>
			</tr>

			<tr>
				<td>商品描述:</td>
				<td><form:textarea path="itemProdDescription" rows="10" cols="30"></form:textarea></td>
			</tr>
		</table><br>
		<input type="submit" value="送出修改">
	</form:form>
	<div class="row">
		<div class="col-12">
			<button id="bt2" type="button" class="btn btn-success"
				onclick="location.href='<%=request.getContextPath()%>/backend/item.jsp'">回首頁</button>
		</div>
	</div>
	<br>
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

</body>
<br>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date2').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker:true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s',
	       value: '<%=itemVO.getLaunchedTime()%>'
           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	        '2021/07',  // 起始日
//            minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker:true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s'
	      
           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	        '2021/07',  // 起始日
//            minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
        </script>


</body>


</html>
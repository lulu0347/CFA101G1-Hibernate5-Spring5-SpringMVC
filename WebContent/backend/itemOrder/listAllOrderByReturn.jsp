<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemOrder.model.*"%>
<!-- 被取消 -->
<%
	ItemOrderService itemOrderSvc = new ItemOrderService();
    List<ItemOrderVO> list = itemOrderSvc.findByOrderState3();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<title>查詢被退貨的訂單</title>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
#row1{
  margin: 10px 0px 10px 0px;
}
#row2{
  margin: 10px 0px 10px 0px;
}
#row3{
  margin: 10px 0px 10px 0px;
 
}
#div1{
border-top: #666 1px solid;
border-bottom: #666 1px solid;

}

</style>

</head>
<body>
<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<button class="btn"><i class="fa fa-home"></i></button>
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
							<a href="#page-top"> <img id="img1"
								src="<%=request.getContextPath()%>/resource/Images/logo.png"
								alt="logo" style="height: 100px;" margin-bottom=10px;>
							</a>
						</div>
						<div class="col-6">
							<div class="row ">
							</div>
						</div>
						<br>
					</div>
					<div class="row" style="height: 20px"></div>
				</div>
			</div>
		</div>
	</header>

	<br>
		 <p>目前被退貨的訂單 : </p>

     <%-- 錯誤表列 --%>
     <c:if test="${not empty errorMsgs}">
       <font style="color:red">請修正以下錯誤:</font>
       <ul>
         <c:forEach var="message" items="${errorMsgs}">
           <li style="color:red">${message}</li>
         </c:forEach>
       </ul>
     </c:if>

  <div class="container">
  <c:forEach var="itemOrderVO" items="${list}">
        <div class="row">
          <div class="col-12" id="div1">
            一般商城訂單編號 : ${itemOrderVO.orderNo}
          </div>
        </div> 
        <div class="row" id="row1">
          <div class="col-3" >購買會員編號 : ${itemOrderVO.memberVO.memNo}</div>
          <div class="col-3">商品售出日期 : <fmt:formatDate value="${itemOrderVO.tranTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
          <div class="col-6"></div>
        </div>
        <div class="row" id="row2">
          <div class="col-3">訂單總金額 : $ ${itemOrderVO.orderTotal}</div>
          <div class="col-3">訂單狀態 :
          <c:choose>
			<c:when test="${itemOrderVO.orderState == '3'}">
			<td>
			已退貨
			</td>
			</c:when>
		</c:choose>
		</div>
          <div class="col-6"></div>
        </div>
        <div class="row" id="row3">
          <div class="col-3">收件人姓名 : ${itemOrderVO.receiverName}</div>
          <div class="col-4">收件人地址 : ${itemOrderVO.receiverAddress}</div>
          <div class="col-3">收件人電話 : ${itemOrderVO.receiverPhone}</div>
          <div class="col-2"></div>        
        </div>       
        <hr>
        <br>
         <div class="row">
					<div class="col-2">
						<button id="bt2" type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/backend/item.jsp'">回首頁</button>
					</div>
					
					<div class="col-2">
					<button id="bt2" type="button" class="btn btn-danger" onclick="location.href=
						'<%=request.getContextPath()%>/deleteOneOrder?orderNo=${itemOrderVO.orderNo}'">刪除此訂單</button>
					</div>
					
					<div class="col-2">
					</div>
					<div class="col-6">
					</div>
		</div>
		<br>
     </c:forEach>
  </div>



</html>
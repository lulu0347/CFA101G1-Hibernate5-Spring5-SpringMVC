<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.member.model.*"%>

<%	
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>最終確認畫面</title>
<!-- 字型 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Courier+Prime&display=swap" rel="stylesheet">

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
.tr-1{
	border-bottom: 1px #666 solid;
	border-top: 1px #666 solid;
}
.tr-2 {
	text-align: center;
	border-bottom: 1px #666 solid;
}

.tr-3 {
	border-bottom: 1px #666 solid;
}

element.style {
    margin: 30px 0px 0px 0px;
}

.btn-outline-primary {
    color: #0d6efd;
    border-color: #0d6efd;
    margin: 10px 0px 0px 0px;
}

.monospace{
	font-family: 'Courier Prime', monospace;
	font-size: 32px;
}
</style>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/CFA101G1/frontend/homepage/homepage.html">CountMEIn</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${not empty user}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
							查看訂單
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllOrderByMemNo.jsp">商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdByBuyer.jsp">二手商城買家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdBySeller.jsp">二手商城賣家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec">拍賣商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp">競標商城訂單頁面</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllCollectionByMemNo.jsp">查看收藏商品</a></li>
                            </ul>
                        </li>
 						<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">會員中心</a>
                        </li>
                       	<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/chat/insta.jsp">聊天室</a>
                        </li>
                    </c:if>
                    </ul>
                    
                    
                    <c:if test="${empty user}">
	                     	<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit">註冊</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-outline-success btn-sm">登入會員</button>
	                        </a>
	                </c:if>	
                    <c:if test="${not empty user}">	                        
	                    	<a href="<%=request.getContextPath()%>/deposit">
	                     	 <button class="btn btn-outline-success btn-sm me-md-4" type="button">我的錢包</button>
	                     	 <input type="hidden" name="action" value="deposit">
	                     	</a>
	                </c:if> 
                    <c:if test="${not empty user}">                    
                    <form class="d-flex" action="<%=request.getContextPath()%>/member/LoginServlet">
                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">登出</button>
                    </form>
                    </c:if>
                    
                </div>
            </div>
        </nav>
		<div class="MallTop">
            <div style="margin-top:56px">
                <div class="container">
                    <div class="row">
                        <div class="col" style="height:50px;">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <a href="#page-top">
                                <img src="<%=request.getContextPath()%>/frontend/auctAct/images/logo.jpg" alt="logo" style="height:100px;" margin-bottom=10px;>
                            </a>
                        </div>
                        <div class="col-6">
                            <div class="row ">
							<table>
								<div class="input-group mb-3">
								<form ACTION="<%=request.getContextPath()%>/item/item.do" method="POST">
									<input type="text" name="ItemName" class="form-control"
										placeholder="請輸入商品名稱" aria-label="Search Product"
										aria-describedby="button-addon2">
									<button class="btn btn-outline-warning" type="submit"id="button-addon2">
										<span class="material-icons-outlined">search</span>
									</button>
									<input type="hidden" name="action" value="listItem_ByCompositeQuery">
								</form>
								</div>
								</table>
							</div>
                            <div class="row align-items-end" style="height:80px;">
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/EShop.jsp">	
                                    <button type="button" class="btn btn-outline-warning btn-md">主要商城</button>
                                  </a>  
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/frontend/used/listAllProd.jsp">
                                    <button type="button" class="btn btn-outline-warning btn-md">二手商城</button>
                                 </a>
                                </div>
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/auctAct/auctActIndex.jsp">	
                                   <button type="button" class="btn btn-outline-warning btn-md">拍賣商城</button>
                                   </a>
                                </div>
                                 <div class="col">
                                 	<a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html">
                                    <button type="button" class="btn btn-outline-warning btn-md">競標商城</button>
                                	</a>
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/forum/index.html">	
                                    <button type="button" class="btn btn-outline-warning btn-md">討論交流</button>
                                  </a>  
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="row" style="height:20px"></div>
                    </div>
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
							<h4>&nbsp;Category</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="#" class="nav-link link-dark">
									<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>手機</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>電腦</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>相機</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>手錶</i>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>配件</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				<div class="col-9" id="showPanel">
				<font class="monospace"><p>Thank You!</p></font>
					
					<p>
					<table>
						<tr class="tr-1">
							<th style="text-align: center">商品照片</th>
							<th style="text-align: center">商品名稱</th>
							<th style="text-align: center">商品數量</th>
							<th style="text-align: center">商品單價</th>
							<th style="text-align: center">商品保固年限</th>
							<th style="text-align: center">單一商品總額</th>
						</tr>
						<jsp:useBean id="itemPicSvc" scope="page"
							class="com.itemPic.model.ItemPicService" />
						<%
							@SuppressWarnings("unchecked")
							Vector<ItemVO> buylist = (Vector<ItemVO>) session.getAttribute("shoppingcheckout");
							String amount = (String) request.getAttribute("amount");
						%>
						<%
							for (int i = 0; i < buylist.size(); i++) {
								ItemVO order = buylist.get(i);
								Integer itemNo = order.getItemNo();
								String itemName = order.getItemName();
								Integer quantity = order.getQuantity();
								Integer itemPrice = order.getItemPrice();
								java.math.BigDecimal warrantyDate = order.getWarrantyDate();
								Integer oneItemTotal = order.getItemPrice() * order.getQuantity();
						%>
						<tr class="tr-2">
							<td width="200"><img
								src="<%=request.getContextPath()%>/showItemPicByItemNo?itemNo=<%=order.getItemNo()%>" width="240px" height="240px">
							</td>
							<td width="200"><b><%=itemName%></b></td>
							<td width="100"><b>共<%=quantity%>個</b></td>
							<td width="100"><b>$<%=itemPrice%></b></td>
							<td width="100"><b><%=warrantyDate%>年</b></td>
							<td width="100"><b><%=oneItemTotal%></b></td>
						</tr>
						<%
						}
						%>
						<tr class="tr-3">
							<td colspan="6" style="text-align: right;"><font>此次購買總金額：
									<h4>
										$<%=amount%></h4>
							</font></td>
						</tr>
					
						<tr class="tr-4">
						<td colspan="6">
							<button type="button" class="btn btn-outline-primary" onclick="location.href='<%=request.getContextPath()%>/frontend/EShop.jsp'">回到一般商城首頁</button>
						</td>
						</tr>
					</table>
					
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		if ("${check}" === "fail") {
			swal("扣款失敗！餘額不足，請進行儲值。", "", "error");
		} else if ("${check}" === "success") {
			swal("購買成功！", "", "success");
		}
	</script>
</body>
</html>

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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>購物車</title>

<style>
.tr-1 {
	border-top: 1px #666 solid;
	border-bottom: 1px #666 solid;
}

.tr-2 {
	border-bottom: 1px #666 solid;
}

#inputGroup-sizing-lg {
	border: none;
}

element.style {
	color: red;
	font-size: 18px;
}

.h3, h3 {
	margin: 0px 0px 0px 120px;
}
.div10{
width:100%;
height:100%;
}
#p1{
font-size:20px;
}
</style>

</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container-fluid">
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/CFA101G1/frontend/homepage/homepage.html">CountMEIn</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
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
					</ul>


					<a
						href="<%=request.getContextPath()%>/frontend/member/register.jsp">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit">註冊</button>
					</a> <a href="<%=request.getContextPath()%>/frontend/member/login.html">
						<button class="btn btn-outline-success btn-sm">登入會員</button>
					</a> <a
						href="<%=request.getContextPath()%>/deposit">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="button">我的錢包</button> <input type="hidden" name="action"
						value="deposit">
					</a>
					<form class="d-flex"
						action="<%=request.getContextPath()%>/member/LoginServlet">
						<button class="btn btn-outline-success btn-sm me-md-4"
							type="submit" value="logout" name="action">登出</button>
					</form>

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
		<br>
		<div class="prod" style="width: 100%;">
			<div class="row">
				<div class="col-3">
					<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
						style="width: 220px;">
						<a href=""
							class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
							<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
							<h4>&nbsp;商品類別</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a
								href="<%=request.getContextPath()%>/frontend/listByPhone.jsp"
								class="nav-link link-dark"> <i class="bi bi-phone fs-2 mb-3">
										&nbsp;</i>手機</i><br>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/listByComputer.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>電腦</i><br>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/listByCamera.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>相機</i><br>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/listByWatch.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>手錶</i>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/listByOthers.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>配件</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				<c:if test="${empty user}">
				<div class="col-9">
				<p id="p1">請先登入會員</p>
				<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-primary" type="submit">註冊會員</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-secondary">登入會員</button>
	                        </a>
				
				</div>
				</c:if>
				<c:if test="${not empty user}">
				<div class="col-9" id="showPanel">
				
					<%
						Vector<ItemVO> buylist = (Vector<ItemVO>) session.getAttribute("shoppingcart");
					%>
					
						<%
							if (buylist != null && (buylist.size() > 0)) {
						%>
					
						<div>
							<font style="text-align: left"><b>目前的購物車中商品如下 : </b></font>
						</div>
						<p>
						<table class="table-1">
							<tr class="tr-1">
								<th style="text-align: center">商品照片</th>
								<th style="text-align: center">商品名稱</th>
								<th style="text-align: center">商品數量</th>
								<th style="text-align: center">商品單價</th>
								<th style="text-align: center">商品保固年限</th>
								<th style="text-align: center">單一商品總額</th>
								<th style="text-align: center">操作</th>
							</tr>
							
						
							<%
								for (int index = 0; index < buylist.size(); index++) {
											ItemVO order = buylist.get(index);
											Integer oneItemTotal = order.getItemPrice() * order.getQuantity();
											Integer allTotal = 0;
							%>
							<tr class="tr-2">
								<jsp:useBean id="itemPicSvc" scope="page"
									class="com.itemPic.model.ItemPicService" />
								<td width="200">
									<div align="center">
										<img
											src="<%=request.getContextPath()%>/showItemPicByItemNo?itemNo=<%=order.getItemNo()%>"
											width="240px" height="240px">
									</div>
								</td>
								<td width="200"><div align="center">
										<b><%=order.getItemName()%></b>
									</div></td>
								<td width="100"><div align="center">
										<b>共<%=order.getQuantity()%>個
										</b>
									</div></td>
								<td width="100"><div align="center">
										<b>$<%=order.getItemPrice()%></b>
									</div></td>
								<td width="100"><div align="center">
										<b><%=order.getWarrantyDate()%>年</b>
									</div></td>
								<td width="100"><div align="center">
										<b ><%=oneItemTotal%></b>
									</div></td>
								<td width="100"><div align="center">
										<form name="deleteForm"
											action="<%=request.getContextPath()%>/shopping/shopping.do"
											method="POST">
											<input type="hidden" name="action" value="DELETE"> <input
												type="hidden" name="del" value="<%=index%>"> <input
												type="submit" value="刪除">
										</form>
									</div>
							</td>
								
							</tr>
							<%
								}
							%>
							
							
							
						</table>
						
						<br>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${not empty user}">
						<form name="checkoutForm"
							action="<%=request.getContextPath()%>/shopping/shopping.do"
							method="POST">
							<div>

								<span class="input-group-text " id="inputGroup-sizing-lg">收件者姓名
									: </span> <input readonly="readonly" id="receiverName" type="TEXT"
									name="receiverName" size="45" value="${user.memName}">


							</div>
							<br>
							<div class="input-group input-group-md">
								<span class="input-group-text " id="inputGroup-sizing">收件者地址
									: </span> <select id="county"
									onchange="changeCounty(event.target.value)"
									name="receiverAddressCounty">
									<option></option>
								</select> <select id="city" name="receiverAddressCity">
									<option></option>
								</select> <input id="memadd" type="text" class="form-control"
									placeholder="請填入地址" aria-label="Sizing example input"
									aria-describedby="inputGroup-sizing-lg"
									name="receiverAddressDetail" size="50">
								<div class="col-9">
									<h3 id="a-prompt"></h3>
								</div>

							</div>
							<br>
							<div>
								<span class="input-group-text " id="inputGroup-sizing-lg">收件者聯絡電話
									: </span> <input readonly="readonly" id="receiverPhone" type="TEXT"
									name="receiverPhone" size="45" maxlength="10"
									value="${user.memMobile}">

							</div>

							<br> 
							<input type="hidden" name="action" value="CHECKOUT">
							<input id="submit" type="submit" value="付款結帳" disabled>
						</form>
						</c:if>
						<c:if test="${empty user}">
							<p id="p1">請先登入會員</p>
							<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-primary" type="submit">註冊會員</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-secondary">登入會員</button>
	                        </a>
						</c:if>
						<form name="clearForm"
							action="<%=request.getContextPath()%>/shopping/shopping.do"
							method="POST">

							<input type="hidden" name="action" value="clear"> <input
								type="submit" value="清空購物車">

						</form>


						<%
							}
						%>
						
						<%
							if (buylist.size() == 0) {
						%>
						<div>
							<h2>目前購物車為空</h2>
							<img
								src="<%=request.getContextPath()%>/resource/Images/error.png"
								alt="">
						</div>

						<%
							}
						%>
					
				</div>
				</c:if>
			</div>
		</div>
	</div>
	</session>
	<footer>
	<div class="container">
		<div class="foot">
			
				<div class="row" style="height: 200px;">
				</div>
			
		</div>
		</div>
	</footer>
	<script src="../resource/Images/database.js">
	
	</script>
	<script>
		function init(){
    let options = "";
    const template =`<option></option>`;
    Object.keys(database).forEach(county=>{
        options+=("<option value='" + county + "'>" + county + "</option>");
    })
   
    generateCity(Object.keys(database)[0])
    document.getElementById("county").innerHTML = options;
    
}

function generateCity(county){
    let cityOptions = "";
    Object.keys(database[county]).forEach(city=>{
        cityOptions+=("<option value='" + city + "'>" + city + "</option>");
    })
    document.getElementById("city").innerHTML = cityOptions;
}


function changeCounty(county){
    generateCity(county);
}
//執行函式
init();
</script>

	<!-- 來驗證購物車吧! --正則表達 -->
	<script>

function validateAdd() {
    let memadd = $('#memadd').val();
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,30}$/ ;
    return re.test(memadd);
}

</script>

	<!-- 來驗證購物車吧! --驗證表達 -->
	<script>
$(function() {
    //設置驗證旗幟
    let a_flag = false;

    //確認旗幟均為true按鈕才能按
    function checkFlag() {
        if (a_flag) {
            $("#submit").removeAttr("disabled")
        } else {
            $("#submit").attr("disabled", "disabled")
        }
    }
    
  //驗證地址格式
$('#memadd').on('input', function() {
    $('#a-prompt').text("");
    if (validateAdd()) {
        $('#memadd').css('border', '2px solid #27da80')
        a_flag = true;
    } else {
        $('#a-prompt').text("請確認輸入格式");
        $('#a-prompt').css('color', 'red');
        $('#a-prompt').css('font-size', '10px');
        $('#memadd').css('border', '2px solid red')
        a_flag = false;
    }
    checkFlag();
})
});
</script>

</body>

</html>

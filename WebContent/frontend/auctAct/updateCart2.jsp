<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="auctActProdSvc" scope="page"
	class="com.auctActProd.model.AuctActProdService" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
</head>
<body>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container">
	<% Map<Integer, AuctOrdDetlVO> auctCart = (Map<Integer, AuctOrdDetlVO>) session.getAttribute("auctCart");%>
	<c:if test="${not empty auctCart}">

		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>修改購買商品</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">商品圖片</th>
							<th scope="col">商品編號</th>
							<th scope="col">商品名稱</th>
							<th scope="col">商品數量</th>
							<th scope="col">商品單價</th>
							<th scope="col">商品總價</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="auctOrdDetlVO" items="${auctCart.values()}">
						<tr>
								<td><c:forEach var="auctActProdVO"
										items="${auctActProdSvc.all}">
										<c:if
											test="${auctOrdDetlVO.auctActProdNo==auctActProdVO.auctActProdNo}">
											<img
												src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctActProdVO.auctProdNo}&auctProdPicinfo=DetlPic2&action=''"
												alt="Prod" style="height: 100px; margin-botton=10px;">
										</c:if>
									</c:forEach>
								</td>
								<td>${auctOrdDetlVO.auctActProdNo}</td>
								<c:forEach var="pairs" items="${nameMap}">
									<c:if test="${auctOrdDetlVO.auctActProdNo== pairs.key}">
										<td>${pairs.value}</td>
									</c:if>
								</c:forEach>
								
								<td>
								<form name="confirmOrder"action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"method="POST">
									<div class="input-group" style="width:100px">
										<input class="input-group-text" type="button" id="minus" name="minus" value="-"onclick="changeValue('${auctOrdDetlVO.auctActProdNo}', -1)">
										<input class="form-control prodPurQtyNum" aria-describedby="button-addon2" 
											name="prodPurQty" value="${auctOrdDetlVO.prodPurQty}">
										<input class="input-group-text" type="button" id="plus" name="plus" value="+"onclick="changeValue('${auctOrdDetlVO.auctActProdNo}', 1)">
									</div>
								</form>
								</td>
								<td>${auctOrdDetlVO.price}</td>
								<td class="sum">${auctOrdDetlVO.price*auctOrdDetlVO.prodPurQty}</td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"
										name="auctCart">
										<button type="submit" class="btn btn-outline-danger btn-sm">刪除
										</button>
										<input type="hidden" name="action" value="delCartProd">
										<input type="hidden" name="auctActProdNo"
											value="${auctOrdDetlVO.auctActProdNo}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row">
					<div class="col-10"></div>
				<div class="col-2">
					<form name="confirmOrder"action="<%=request.getContextPath()%>/auctOrder/auctOrder.do"method="POST">
						<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							<input type="hidden" name="action" value="updateProdNum">
							<button type="submit" class="btn btn-outline-success">確認修改</button>
						</div>
					</form>
				</div>
			</div>
	</c:if>
	</div>
	<c:if test="${empty auctCart}">
	<%@ include file="./includePage/noProdPage.txt"%>
	</c:if>
	</session>
	<%@ include file="component/footer.txt"%>
	

</body>
<script>
//取得增加或減少的數量
 	function changeValue(auctActProdNo, value){
	//取出物件的字串
//  		const prodPurQty=document.getElementById("prodPurQtyNum");
	const dom = event.target; // + / - buttions
	const prodPurQty = dom.parentElement.querySelector('.prodPurQtyNum');
	//將物件的值指定存一個變數
		let num=prodPurQty.value;
	//最後的數量為要把字串轉為數字加上改變的值
		let finallyvalue = parseInt(num)+value
	//如果最後數字大於0
		if(finallyvalue >= 1){
			prodPurQty.value=finallyvalue;
			// ajax
			ajaxProdNum(auctActProdNo, finallyvalue, dom);
		}
		
 	}
 	
 	function ajaxProdNum(auctActProdNo, prodPurQty, dom) {
 	    // Create an XMLHttpRequest object
 	    const xhr = new XMLHttpRequest();
 	    // Define a callback function
 	    xhr.onload = function () {
 	    	//資料回傳以後的內容
 	        const result = this.responseText;
 	    	const obj = JSON.parse(result);

 	    	const sum = dom.closest('tr').querySelector('.sum');
 	    	sum.innerText = obj.sum;
 	    }

 	    const data = {
 	        "action": "ajaxNum",
 	        "auctActProdNo": auctActProdNo,
 	       	"prodPurQty": prodPurQty
 	    };
 	    console.log(data);
 	    const sendingStr = generateDataString(data);

 	   	xhr.open("post","<%=request.getContextPath()%>/auctOrder/auctOrder.do", true); //先open a connection
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded"); //再設定requestHeader為urlencoded (POST method)
		xhr.send(sendingStr);

 	}
 	
 	function generateDataString(data) {
 	    let str = "";
 	    const keys = Object.keys(data);
 	    keys.forEach(key => {
 	        str += key + "=" + data[key] + "&";
 	    })
 	    return str;
 	}

 	
 	
 	
 	
</script>
</html>
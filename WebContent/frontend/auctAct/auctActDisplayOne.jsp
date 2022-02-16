<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctProd.model.*"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
<style>
.box {
	width: 400px;
}

#show_L img {
	width: 100%;
}

.all_img {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
}

.all_img img {
	width: 100%;
	cursor: pointer;
}
</style>

</head>

<body>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container" style="rid-template-rows: 1fr auto;">
		<div style="position: fixed; right: 20px; top: 50%; z-index: 30;">
			<a
				href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=displayCart&from=/auctActProd/auctactprod.do?auctProdNo=${param.auctProdNo}-auctActNo=${param.auctActNo}-action=${param.action}">
				<img
				src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=cart&action=getActPic"
				style="height: 50px">
			</a>
		</div>
		<div style="position: fixed; right: 20px; top: 65%; z-index: 30;">
			<c:if test="${not empty user}">
				<a 
					href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec"  title="查看拍賣訂單紀錄">
					<img
					src="<%=request.getContextPath()%>/frontend/auctAct/images/box.png"
					style="height: 50px">
				</a>
			</c:if>
		</div>

	</div>
	<div class="container">
		<div class="row" style="height: 50px;"></div>
		<div class="row">
			<div class="col-5">
				<div class="box">
					<div id='show_L'>
						<img
							src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=DetlPic1&action=''">
					</div>
					<div class="all_img">
						<img
							src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=DetlPic1&action=''">
						<img
							src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=DetlPic2&action=''">
						<img
							src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdFront&action=''">
					</div>
				</div>
			</div>
			<div class="col-7">
				<div class="info" style="margin-bottom: 50px;">
					<img
						src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdDetlInfo&action=''"
						style="height: 400px;">
				</div>
				<ul>
					<li>
						<h5>價格：${auctActProdVO.auctProdPrice}元</h5>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row" style="height: 50px;">
			<div class="col-8"></div>
			<div class="d-grid gap-3 col-3  align-content-lg-end">
			<span style="cursor:pointer">
					<img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=button&action=getActPic"
					style="height: 80px; margin-left: 150px" title="加入購物車" onclick="addProductToCart(${auctActProdVO.auctActProdNo})" >
			</span>
			</div>
			<div class="row" style="height: 40px;"></div>
			<hr>
			<div class="row">
				<img
					src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctProdVO.auctProdNo}&auctProdPicinfo=ProdBack&action=''"
					style="width: 1200px;">
			</div>
		</div>
	</div>
	</session>
	<div class="row" style="height: 800px"></div>
	</session>

	<%@ include file="component/footer.txt"%>
</body>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function change_img(e){
        let new_sre = e.target.getAttribute('src');
        document.querySelector('#show_L img').setAttribute('src',new_sre)
    }
    function init(){
        document.querySelectorAll('.all_img img').forEach(element=>element.addEventListener('click',change_img));
    }
    window.addEventListener('load',init);
</script>
<script>
		var projectName = "<%=request.getContextPath()%>";
	</script>
	<script src="<%=request.getContextPath()%>/frontend/auctAct/js/auct.js">
</script>
</html>
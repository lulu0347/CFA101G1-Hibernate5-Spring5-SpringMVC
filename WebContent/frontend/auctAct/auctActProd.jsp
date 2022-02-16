<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctActProd.model.*"%>
<%@ page import="com.auctOrderDetl.model.*"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="auctProdSvc" scope="page" class="com.auctProd.model.AuctProdService" />

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
</head>


<body>

<%Map<Integer, AuctOrdDetlVO> auctCart = (Map<Integer, AuctOrdDetlVO>) session.getAttribute("auctCart");%>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container">
		<div style="position: fixed; right: 20px; top: 50%; z-index: 30;">
			<a
				href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=displayCart&from=/auctAct/auctact.do?auctActNo=${param.auctActNo}-action=${param.action}">
				<img
				src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=cart&action=getActPic"
				style="height: 50px" title="GO結帳">
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
		<div class="prod">
			<div class="row">
				<div class="col-3">
					<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
						style="width: 220px;">
						<a href=""
							class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none"title="所有商品類別">
							<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
							<h4>&nbsp;Category</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="#" class="nav-link link-dark" title="手機">
									<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>Phone</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark" title="電腦"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>Computer</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"title="相機"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>Camera</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"title="手錶"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>Watch</i>
							</a></li>
							<li><a href="#" class="nav-link link-dark"title="其他配件"> <i
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
				<div class="col-9" style="margin-top: 50px">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-bs-ride="carousel">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="0" class="active" aria-current="true"
								aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img
									src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner1&action=getActPic"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner2&action=getActPic"
									class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img
									src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner3&action=getActPic"
									class="d-block w-100" alt="...">
							</div>
						</div>
						<div class=time style="color: rgb(94, 152, 215);">
							<h3>${auctActVO.auctActDesc}</h3>
						</div>
						<div class=time style="color: rgb(94, 152, 215);">
							<h4>活動期間:
								<fmt:formatDate value="${auctActVO.auctStartTime}"
									pattern="yyyy-MM-dd" />
								~
								<fmt:formatDate value="${auctActVO.auctEndTime}"
									pattern="yyyy-MM-dd" />
							</h4>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<hr />
						<div class="row">
							<c:forEach var="auctActProdVO" items="${auctActProdList}">
							<div class="card col-3" style="width: 15rem;">
								<img
									src="<%=request.getContextPath()%>/auct/auctpic.do?auctProdNo=${auctActProdVO.auctProdNo}&auctProdPicinfo=DetlPic1&action=''"
									class="card-img-top" alt="phone11">
								<div class="card-body">
									<h5 class="card-title">
										<c:forEach var="auctProdVO" items="${auctProdSvc.allProd}">
											<c:if
												test="${auctActProdVO.auctProdNo==auctProdVO.auctProdNo}">${auctProdVO.auctProdName}
											</c:if>
										</c:forEach>
									</h5>
									<span class="input-group">$${auctActProdVO.auctProdPrice}元</span>
									<div class="d-grid flex justify-content-end" >
									
<%-- 										<a href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=addProdToCart&auctActProdNo=${auctActProdVO.auctActProdNo}&auctActNo=${auctActProdVO.auctActNo}">  --%>
<%-- 										<img src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=button&action=getActPic" --%>
<!-- 											style="height: 30px; margin-left: 40px"  title="加入購物車"></a> -->
										<span style="cursor:pointer">
										<img src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=button&action=getActPic"
											style="height: 30px; margin-left: 40px" title="加入購物車" onclick="addProductToCart(${auctActProdVO.auctActProdNo})" >
										</span>
										<br>
										<form method="post" action="<%=request.getContextPath()%>/auctActProd/auctactprod.do"> 
											<button type="submit" class="btn btn-outline-secondary btn-sm justify-content-end">查看商品</button>
											<input type="hidden" name="action" value="displayOneProd">
											<input type="hidden" name="auctProdNo" value="${auctActProdVO.auctProdNo}">
											<input type="hidden" name="auctActNo" value="${auctActProdVO.auctActNo}">  
										</form>
									</div>
								</div>
							</div>
							</c:forEach>
						</div>

				</div>
			</div>
		</div>
	</div>
	</session>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
// 	將servlet的路徑放入
		var projectName = "<%=request.getContextPath()%>";
	</script>
	<script src="<%=request.getContextPath()%>/frontend/auctAct/js/auct.js"></script>
	<%@ include file="component/footer.txt"%>
</body>

</html>
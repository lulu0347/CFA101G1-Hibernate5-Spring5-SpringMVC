<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctActProd.model.*"%>
<%@ page import="com.auctProd.model.*"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="auctProdSvc" scope="page"
	class="com.auctProd.model.AuctProdService" />
<!DOCTYPE html>
<html lang="en">

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
		<div style="position: fixed; right: 20px; top: 50%; z-index: 30;">
			<a
				href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=displayCart&from=<%=request.getServletPath() %>"  title="GO結帳">
				<img src="<%=request.getContextPath()%>/frontend/auctAct/images/44014.jpg"
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
		<div class="prod">
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
				<div class="col-9">
					<%
					AuctActService actSvc = new AuctActService();
					List<AuctActVO> actList = actSvc.getAll();
					pageContext.setAttribute("actList", actList);
					%>
				
					<c:forEach var="auctActVO" items="${actList}">
						<div class="row">
							<div class="pic" style="margin-top: 50px">
								<div class="ad" style="position: relative">
									<div style="position: absolute; margin-left: 30px;">
									<form method="post" action="<%=request.getContextPath()%>/auctAct/auctact.do" name="auctActform">
										<button type="submit" class="btn btn-secondary btn-lg" style="margin-top: 250px">
											立即選購 
											<i class="bi bi-arrow-right-circle " style="font-size: 20px; color: white"></i>
										</button>
										<input type="hidden" name="action" value="getAllProdwithPic">
										<input type="hidden" name="auctActNo" value="${auctActVO.auctActNo}">
									</form>
									</div>
									<a href="<%=request.getContextPath()%>/auctAct/auctact.do?auctActNo=${auctActVO.auctActNo}&action=getAllProdwithPic">
									<img
										src="<%=request.getContextPath()%>/auct/auctpic.do?auctActNo=${auctActVO.auctActNo}&auctActPicinfo=banner1&action=getActPic"
										alt="..." style="width: 835px">
									</a>
								</div>
							</div>
						</div>
						<div class="row" style="font-size: 20px; color: rgb(48, 124, 172)">
							<h4>
								活動時間：
								<fmt:formatDate value="${auctActVO.auctStartTime}"
									pattern="yyyy-MM-dd" />~
								<fmt:formatDate value="${auctActVO.auctEndTime}"
									pattern="yyyy-MM-dd" />
							</h4>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</session>
	
	<%@ include file="component/footer.txt"%>
</body>

</html>
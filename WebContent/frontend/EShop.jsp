<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="itemCollectionSvc" scope="page" class="com.itemCollection.model.ItemCollectionService" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>δΈθ¬εε</title>
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
.icon{
position : absolute;
right:0;
bottom:200px;
}
.card-title {
    font-size: 16px;
    margin-bottom: .5rem;
}
#collection-img{
margin : 10px 0px 0px 0px;
}

</style>

</head>

<body>

	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/Frontpage.jsp">CountMEIn</a>
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
							ζ₯ηθ¨ε?
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllOrderByMemNo.jsp">εεθ¨ε?ι ι’</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdByBuyer.jsp">δΊζεεθ²·ε?Άθ¨ε?ι ι’</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdBySeller.jsp">δΊζεεθ³£ε?Άθ¨ε?ι ι’</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec">ζθ³£εεθ¨ε?ι ι’</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp">η«Άζ¨εεθ¨ε?ι ι’</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllCollectionByMemNo.jsp">ζ₯ηζΆθεε</a></li>
                            </ul>
                        </li>
 						<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">ζε‘δΈ­εΏ</a>
                        </li>
                       	<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/chat/insta.jsp">θε€©ε?€</a>
                        </li>
                    </c:if>
                    </ul>
                    
                    
                    <c:if test="${empty user}">
	                     	<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit">θ¨»ε</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-outline-success btn-sm">η»ε₯ζε‘</button>
	                        </a>
	                </c:if>	
                    <c:if test="${not empty user}">	                        
	                    	<a href="<%=request.getContextPath()%>/deposit">
	                     	 <button class="btn btn-outline-success btn-sm me-md-4" type="button">ζηι’ε</button>
	                     	 <input type="hidden" name="action" value="deposit">
	                     	</a>
	                </c:if> 
                    <c:if test="${not empty user}">                    
                    <form class="d-flex" action="<%=request.getContextPath()%>/member/LoginServlet">
                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">η»εΊ</button>
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
                            <a href="http://34.81.72.90/CFA101G1/frontend/EShop.jsp">
                                <img src="<%=request.getContextPath()%>/frontend/auctAct/images/logo.jpg" alt="logo" style="height:100px;" margin-bottom=10px;>
                            </a>
                        </div>
                        <div class="col-6">
                           <div class="row ">
							<table>
								<div class="input-group mb-3">
								<form ACTION="<%=request.getContextPath()%>/item/item.do" method="POST">
									<input type="text" name="ItemName" class="form-control"
										placeholder="θ«θΌΈε₯εεεη¨±" aria-label="Search Product"
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
                                    <button type="button" class="btn btn-outline-warning btn-md">δΈ»θ¦εε</button>
                                  </a>  
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/frontend/used/listAllProd.jsp">
                                    <button type="button" class="btn btn-outline-warning btn-md">δΊζεε</button>
                                 </a>
                                </div>
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/auctAct/auctActIndex.jsp">	
                                   <button type="button" class="btn btn-outline-warning btn-md">ζθ³£εε</button>
                                   </a>
                                </div>
                                 <div class="col">
                                 	<a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html">
                                    <button type="button" class="btn btn-outline-warning btn-md">η«Άζ¨εε</button>
                                	</a>
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/forum/index.html">	
                                    <button type="button" class="btn btn-outline-warning btn-md">θ¨θ«δΊ€ζ΅</button>
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
							<h4>&nbsp;εει‘ε₯</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="<%=request.getContextPath()%>/frontend/listByPhone.jsp" class="nav-link link-dark">
							<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>ζζ©</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByComputer.jsp" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>ι»θ¦</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByCamera.jsp" class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>ηΈζ©</i><br>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByWatch.jsp" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>ζιΆ</i>
							</a></li>
							<li><a href="<%=request.getContextPath()%>/frontend/listByOthers.jsp" class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>ιδ»Ά</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
						</div>
					</div>
				</div>
				<div class="col-9" id="showPanel">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">θ«δΏ?ζ­£δ»₯δΈι―θͺ€:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<table>
			<%@ include file="page1.file"%>
			<c:forEach var="itemVO" items="${list}">
					<jsp:useBean id="itemPicSvc" scope="page"
						class="com.itemPic.model.ItemPicService" />
					<td>
					<c:forEach var="itemPicVO" items="<%=itemPicSvc.getAllPics()%>" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:if test="${itemVO.itemNo==itemPicVO.itemVO.itemNo}">
								<div class="card" style="width: 18rem;">
									<img  class="card-img-top"
												src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
									<div class="card-body">
										<h5 class="card-title">${itemVO.itemName}</h5>
										<p class="card-text">εΉζ Ό : ${itemVO.itemPrice}</p>
										<p class="card-text">δΏεΊζι : ${itemVO.warrantyDate}εΉ΄</p>
										
										<form  name="shoppingForm" action="<%=request.getContextPath()%>/getOneItemForViewFromEShop" method="POST">
										<input type="hidden" name="itemNo" value="${itemVO.itemNo}">
										<input type="hidden" name="memNo" value="${user.memNo}">
										<input type="hidden" name="action" value="getOneItemForViewFromEShop">
										<input class="btn btn-primary" type="submit" name="Submit" value="ζ₯ηεεθ©³ζ">
										</form>
										<c:if test="${user.memNo != null}">
 
										<c:set var="checkcount" scope="page" value="${itemCollectionSvc.getcount(itemVO.itemNo,user.memNo)}"/>
<%-- 										<p>η?εθ¨ζΈηΊ :  <c:out value="${checkcount}"/></p> --%>
										<c:choose>
										 <c:when test="${checkcount <= 0}">
											<a id ="collection-a" href="<%=request.getContextPath()%>/addCollectionFromEshop?memNo=${user.memNo}&itemNo=${itemVO.itemNo}">
											<img id="collection-img" src="<%=request.getContextPath()%>/resource/Images/uncollect.png" width="50" height="50">
											</a>
										 </c:when>
										 <c:when test="${checkcount > 0}">
											 <a id ="collection-a" href="<%=request.getContextPath()%>/addCollectionFromEshop?memNo=${user.memNo}&itemNo=${itemVO.itemNo}">
											<img id="collection-img" src="<%=request.getContextPath()%>/resource/Images/collected.png" width="50" height="50">
											</a>
										 </c:when>
										 </c:choose>
										 </c:if>
									</div>
								</div>		
						</c:if>
					</c:forEach>
					</td>
			</c:forEach>
					</table>
					<a class="icon" href="<%=request.getContextPath()%>/frontend/Cart.jsp"><img src="<%=request.getContextPath()%>/resource/Images/cart.png" 
					width="80" 
					height="80"></a>	
					<%@ include file="page2.file"%>
				</div>
			</div>
		</div>
	</div>
	</session>
	<footer>
		<div class="foot">
			<div class="container">
				<div class="row" style="height: 200px;">
					<div class="col-3">
				
					</div>
					<div class="col-9"></div>
				</div>
			</div>
		</div>
	</footer>
	<script>
	
	</script>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</html>
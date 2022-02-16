<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemOrder.model.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.itemIOD.model.*"%>
<%@ page import="com.member.model.*"%>

<%	
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
	Integer memno = memberVO.getMemNo();
	
	ItemIODService itemIODSvc = new ItemIODService();
	List<ItemIODVO> list = itemIODSvc.getOrderByMemNo(memno);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有訂單明細</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>訂單明細總覽</title>
<style>
    .col-3{
        height: 100%;
    }
    .div1{
    	padding :30px 0px 10px 0px;
    	border-top: #666 solid 1px;
        margin: 20px 0px 15px 0px ;
    }
    .div2{
    	border-top: #666 solid 1px;
        float: left;
        width: 240px;
        height: 240px;
        margin: 0px 20px 0px 0px;
    }
    .div3{
    	border-top: #666 solid 1px;
        height: 80px;
        line-height:80px;
    }

    .div5{
    	border-top: #666 solid 1px;	
        height: 80px;
        line-height:80px;
    }
    .span1{
       float: right; 
    }
    .div6{
   		border-top: #666 solid 1px;	
        height: 80px;
        line-height:80px;
    }
    .div7{
        background-color: rgb(248, 250, 221);
        height: 50px;
        line-height:50px;
        margin: 10px 0px 10px 0px;
    }
    .span2{
    
        float: right;
    }
    .span3{
     	background-color: rgb(224, 220, 220);
        margin: 0px 0px 0px 20px ;
    }
    .p1{
         float: right;
    }
    .img-fluid{
        width: 240px;
        height: 240px;
    }
    .span4{
    	background-color: rgb(245, 236, 238);
    	margin:0px 0px 0px 20px;
    }
    .divforp{
    	margin:10px 0px 10px 0px;
    }
    .span5{
   	 	background-color: rgb(225, 251, 255);
    }
   
</style>
</head>

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
			<div style="margin-top: 56px">
				<div class="container">
					<div class="row">
						<div class="col" style="height: 50px;"></div>
					</div>
					<div class="row">
						<div class="col-3">
							<a href="#page-top"> <img src="<%=request.getContextPath()%>/resource/Images/logo.png" alt="logo"
								style="height: 100px;" margin-bottom=10px;>
							</a>
						</div>
						<div class="col-6">
							<div class="row align-items-end" style="height: 80px;">
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" onclick="location.href='<%=request.getContextPath()%>/frontend/EShop.jsp'">主要商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >二手商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >拍賣商城</button>
								</div>
								<div class="col">
									<button type="button" class="btn btn-outline-warning btn-md" >討論交流</button>
								</div>
							</div>
						</div>
						<br>
					</div>
					<div class="row" style="height: 20px"></div>
				</div>
			</div>
		</div>
		</div>
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
							<h4>&nbsp;會員專區</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="#" class="nav-link link-dark">
									<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>瀏覽訂單頁面</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>瀏覽商品頁面</i><br>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/member/memberUpdate.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>會員資料管理</i><br>
							</a></li>
							<li><a
								href="<%=request.getContextPath()%>/frontend/member/agreeForm.jsp"
								class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>申請賣家功能</i>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>查看通知</i>
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
				<div class="divforp">
				</div>
                    <c:forEach var="itemIODVO" items="${list}">
                    <jsp:useBean id="itemPicSvc" scope="page" class="com.itemPic.model.ItemPicService" />
                    <div class="div1">
                      	 <span class="span5">
                           	訂單編號為 : ${itemIODVO.orderNo}
                        </span>  
                        <span class="span3">
                            <c:choose>
                                <c:when test="${itemIODVO.orderState == '0'}">
                                <td>
                             		訂單狀態 : 尚未出貨
                                </td>
                                </c:when>
                                <c:when test="${itemIODVO.orderState == '1'}">
                                <td>
                              		訂單狀態 : 已出貨
                                </td>
                                </c:when>
                                <c:when test="${itemIODVO.orderState == '2'}">
                                <td>
                           		  	  訂單狀態 : 已收貨
                                </td>
                                </c:when>
                                <c:when test="${itemIODVO.orderState == '3'}">
                                <td>
                          		              訂單狀態 : 已退貨
                                </td>
                                </c:when>
                                <c:otherwise>
                                <td>
                             		  訂單狀態 : 此訂單已取消
                                </td>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        <span class="span4">
                         	   保固期限共 ${itemIODVO.warrantyDate} 年
                        </span>
                    </div>
                    
                    <span>
                       
                            <div class="div2">
                            <c:forEach var="itemPicVO" items="<%= itemPicSvc.getAllPics() %>">
                                <c:if test="${itemIODVO.itemNo==itemPicVO.itemNo}">
                                    <img class="img-fluid" src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
                                </c:if>  
                            </c:forEach>  
                            </div>
                       
                       	<div class="div3">
                       			${itemIODVO.itemName}
                       	</div>
                      	 <div class="div5">
                    			   共 ${itemIODVO.itemSales} 個
                        <span class="span1">
                        		   商品單價 : $ <p class="p1">${itemIODVO.itemPrice}</p>
                   		 </span>
                       </div>
                       <div class="div6">
                        		 購買時間 : <fmt:formatDate value="${itemIODVO.tranTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                       </div>
                   </span>
                   <div class="div7">
                       
                       <span class="span2">
                           		  單一品項總金額 :$ <p class="p1">${itemIODVO.itemPrice * itemIODVO.itemSales}</p>
                       </span>
                   </div> 
                </c:forEach> 
				</div>
			</div>
		</div>
	</div>

	</session>

	<footer>
		<div class="foot">
			<div class="container">
				<div class="row" style="height: 200px;">
					
				</div>
			</div>
		</div>
	</footer>
<body>
</body>
</html>
<!-- view出單一商品的頁面 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.itemPic.model.*"%>
<%@ page import="com.member.model.*"%>

<%
ItemVO itemOrderVO = (ItemVO) request.getAttribute("itemVO");
MemberVO memberVO = (MemberVO) session.getAttribute("user");
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>   
    <title>商品詳情</title>
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />


    <style>
        #div1 {
            height: 70px;
            border-bottom: solid #666 1px ;
            border-top: solid #666 1px ;
        }

        #div2 {
            background-color: #afa;
            height: 60px;
        }

        #lulu3 {
          width:380px;
          height:356px;
        }

        #lulu4 {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        #div3 {
            background-color: #faa;
            height: 60px;
        }

        ul,
        li {
            margin: 0;
            padding: 0;
        }

        .counter li {
            float: left;
            list-style-type: none;
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border: #999 thin solid;
            background-color: #fff
        }

        .counter li input {
            font-size: 20px;
            width: 100%;
            height: 100%;
            outline: none;
            -webkit-appearance: none;
            background: none;
            margin: 0;
            padding: 0;
            border: 1px solid transparent;
            border-radius: 0;
        }

        #countnum {
            border-left: hidden;
            border-right: hidden;
            color: #666
        }

        #div5 {
            background-color: rgb(224, 105, 151);
            height: 60px;

        }

        #input1 {
            margin: 0px 0px 0px 50px;
        }

        #div6 {
          
        }

        #div7 {
            margin : 10px 0px 0px 0px;
            height: 25px;
        }

        #div8 {
            height: 10px;
        }

        #div9 {
            height: 10px;
        }

        #div10 {
            margin : 10px 0px 0px 0px;
            height: 25px;
            border-left: 1px solid #666;
        }

        #div11{
           margin: 30px 0px 30px 0px;
           border-top: solid #666 1px ;
           border-bottom: solid #666 1px ;
        }

        #div13{
           
            display: flex;
            height: 47px;
            border-bottom: solid #666 1px ;
            border-top: solid #666 1px ;
        }
        
        #div13-1{
            
            display: flex;
            height: 47px;
            border-bottom: solid #666 1px ;
            border-top: solid #666 2px ;
        }

        #div14{
            background-color: rgb(141, 51, 51);
            display: flex;
        }
        
        #margindiv1{
        	margin : 8px 10px 0px 70px;
        }
        
        #margindiv2{
        	margin : 8px 10px 0px 40px;
        }
        
        #margindiv3{
       	 	margin : 8px 10px 0px 35px;
        }
        
        #label1{
        	margin: 8px 0px 0px 0px ; 
        }
        
        #blankdiv{
       	 	width: 100%;
        	height : 15px;
    
        }
       
		#quan{
			margin : 10px 10px 0px 0px;
			padding:0;
			border: #666 solid 1px;
			border-radius: 2%;
			width:106px;
			height : 38px;
		}      
		
		#divforbutton{
			border-top: solid #666 1px ;
			display: flex;
        	height: 47px;
   
		}
		 
		#submit{
			margin : 10px 10px 0px 0px ;
		}
		
		#div12{
			border-bottom: solid #666 1px ;
            border-top: solid #666 1px ;
        }
        
        #col-6-1{
        	margin : 30px 0px 0px 0px;
        }
        
        .col-4 .img-fluid{
        width:100%;
        height:100%;
        }
        
        .icon{
		position : absolute;
		right:0;
		bottom:200px;
		}
		
		#inputforcoll{
		align:right;
		}
		#m-prompt{
		font-size:12px;
		}
    </style>
</head>

<body style="">
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
								<div class="input-group mb-3">
									<input type="text" class="form-control"
										placeholder="Search Product" aria-label="Search Product"
										aria-describedby="button-addon2">
									<button class="btn btn-outline-warning" type="button"
										id="button-addon2">
										<span class="material-icons-outlined">search</span>
									</button>
								</div>
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
                                   <button type="button" class="btn btn-warning  btn-md">拍賣商城</button>
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
    </header>

    <session id="session1">
        <div class="container">
    		<div class="row">
        		<div id="blankdiv" class="con-12"></div>
    		</div>
		</div>
        <div class="container">
            <div class="prod" style="width:100%;">
                <div class="row">
                    <div class="col-2">

                    </div>
        <jsp:useBean id="itemPicSvc" scope="page" class="com.itemPic.model.ItemPicService" />
                    <div class="col-4" id="lulu3">
            <c:forEach var="itemPicVO" items="<%= itemPicSvc.getAllPics() %>">
				<c:if test="${itemVO.itemNo==itemPicVO.itemVO.itemNo}">
					<img class="img-fluid" src="<%=request.getContextPath()%>/showItemPic?itemPicNo=${itemPicVO.itemPicNo}">
				</c:if>
			</c:forEach>
                    </div>
                    <div id="col-6-1" class="col-6">
                        <div id="div13-1">
                        <label id="label1">
                               	 商品名稱
                            </label>
                            <div id="margindiv3">
                             	${itemVO.itemName}
                            </div>
                            
                        </div>
							
                        <div id="div13">
                         	<label id="label1">
                               	 價格
                            </label>
                            <div id="margindiv1">
                             	${itemVO.itemPrice} $NT
                            </div>
                           
                        </div>
                        
                        <div id="div13">
                            <label id="label1">
                               	 運費
                            </label>
                            <div id="margindiv1">
                             	   免運費
                            </div>
                        </div>
                        
                        <div id="div13">
                            <label id="label1">
                               	 保固期限
                            </label>
                            <div id="margindiv2">
                              	共${itemVO.warrantyDate}年
                            </div>
                        </div>
                        <div id="divforbutton">
                        	<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
								<input id="quan" class="btn btn-quantity" type="text" name="quantity" size="3" placeholder="輸入數量"> 
								<input type="hidden" name="itemName" value="${itemVO.itemName}"> 
								<input type="hidden" name="itemPrice" value="${itemVO.itemPrice}"> 
								<input type="hidden" name="warrantyDate" value="${itemVO.warrantyDate}"> 
								<input type="hidden" name="itemNo" value="${itemVO.itemNo}"> 
								<input type="hidden" name="action" value="ADD">
								<input id="submit" class="btn btn-primary" type="submit" name="Submit" value="放入購物車" disabled>
								<div class="col-10"><h3 id="m-prompt"></h3></div>
							</form>
                        </div>	
                    <div class="row">
                        <div id="div8" class="col-12"></div>
                    </div>
                    <div class="row">
                        <div id="div6" class="col-2">
                           <a class="icon" href="<%=request.getContextPath()%>/frontend/Cart.jsp"><img src="<%=request.getContextPath()%>/resource/Images/cart.png"
                            width="80" 
							height="80"></a>
                        </div>
                        <div class="col-10" id="div12">
                           	 商品規格
                        </div>
                    </div>
                    <div class="row">
                        <div id="div6" class="col-2">
                          
                        </div>

                        <div class="col-2" id="div7">
                           	 品牌
                        </div>

                        <div class="col-8" id="div10">
                            CountMEIn
                        </div>

                            <div id="div6" class="col-2">
                               
                            </div>

                             <div class="col-2" id="div7">
                                	商品狀況
                             </div>

                             <div class="col-8" id="div10">
                               	 全新
                            </div>

                        <div id="div6" class="col-2">
                            
                        </div>

                        <div class="col-2" id="div7">
                           	 保固種類
                        </div>

                        <div class="col-8" id="div10">
                            	原廠保固
                        </div>

                            <div id="div6" class="col-2">
                          
                            </div>

                            <div class="col-2" id="div7">
                           	 儲存容量
                            </div>

                            <div class="col-8" id="div10">
                            64GB
                            </div>

                        <div id="div6" class="col-2">
                          
                        </div>

                        <div class="col-2" id="div7">
                            RAM
                        </div>

                        <div class="col-8" id="div10">
                            8GB
                        </div>

                            <div id="div6" class="col-2">
                          
                            </div>

                            <div class="col-2" id="div7">
                            	電池容量
                            </div>

                            <div class="col-8" id="div10">
                            6000mAh
                            </div>

                        <div id="div6" class="col-2">
                            
                        </div>

                        <div class="col-2" id="div7">
                            	作業系統
                        </div>

                        <div class="col-8" id="div10">
                            Bndroid
                        </div>
                        
                    </div>
                    <div class="row">
                        <div id="div6" class="col-2">
                           
                        </div>
                        <div class="col-10" id="div11">
                            <span>
 								${itemVO.itemProdDescription}
                            </span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        </div>
    </session>
    <footer>
        <div class="foot">
            <div class="container">
                <div class="row" style="height:200px;">
                    <div class="col-3"></div>
                    <div class="col-9"></div>
                </div>
            </div>
        </div>
    </footer>
    <script src="//cdnjs.cloudflare.com/ajax/libs/validate.js/0.13.1/validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function adder() {
            var count = document.getElementById("countnum").innerHTML;
            count = parseInt(count) + 1;
            document.getElementById("countnum").innerHTML = count;
        }
        function minuser() {
            var count = document.getElementById("countnum").innerHTML;
            if (count <= 0) {
                count = 0;
            } else {
                count = parseInt(count) - 1;
            }
            document.getElementById("countnum").innerHTML = count;
        }
    </script>
    <script>
    
    function validateMobile() {
        let quan = $('#quan').val();
        const re = /^[0-9]{1,2}$/;
        return re.test(quan);
    }
    
    $(function() {
        //設置驗證旗幟
        let m_flag = false;

        //確認旗幟均為true按鈕才能按
        function checkFlag() {
            if (m_flag) {
                $("#submit").removeAttr("disabled")
            } else {
                $("#submit").attr("disabled", "disabled")
            }
        }
    $('#quan').on('input', function() {
        $('#m-prompt').text("");
        if (validateMobile()) {
            $('#quan').css('border', '2px solid #27da80')
            m_flag = true;
        } else {
            $('#m-prompt').text("請確認數量格式");
            $('#m-prompt').css('color', 'red');
            $('#m-prompt').css('font-size', '10px');
            $('#quan').css('border', '2px solid red')
            m_flag = false;
        }
        checkFlag();
    })
    });
    
    </script>
</body>

</html>
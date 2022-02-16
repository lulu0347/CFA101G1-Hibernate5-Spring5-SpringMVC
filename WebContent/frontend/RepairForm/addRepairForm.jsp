<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repairform.model.*"%>

<%
RepairFormVO repairformVO = (RepairFormVO) request.getAttribute("repairformVO");
%>

<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>維修單首頁</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />

    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/template.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
 
      
      
<style>

h2{
        margin-top: 20px; 
        color:#008CBA ;
        font-family:"Lucida Console", "Courier New", monospace;
   }

    .form-group {
        margin-top: 20px;
        font-family:"Lucida Console", "Courier New", monospace;
        /* color: #008CBA; */
   }

   .email-bt {
    /* max-width:900px; */
     /* margin-left: 200px; */
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 30px;
    font-size: 18px;  
    padding: 20px;
    margin-top: 5px;

   }
   .massage-bt {
    /* max-width:900px; */
     /* margin-left: 200px; */
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 150px;
    font-size: 22px;
    padding: 70px 20px 10px 20px;
  }
   .button {
    border-radius: 25px;
    font-size: 20px;
	font-weight: 700;
	text-transform: uppercase;
    background-color: transparent;
	display: inline-block;
	padding: 8px 20px;
	color: #008CBA;
	border: 2px solid #008CBA;
	transition: all 0.5s;
    font-family:"Lucida Console", "Courier New", monospace;
}

  .button:hover {
    color: white;
	background-color: #008CBA;
}
</style> 


<!-- ↓是HEADER========================================================================================================== -->
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
                                <img src="<%=request.getContextPath()%>/frontend/images/logo.png" alt="logo" style="height:100px;" margin-bottom=10px;>
                            </a>
                        </div>
                        <div class="col-6">
                            <div class="row ">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search Product"
                                        aria-label="Search Product" aria-describedby="button-addon2">
                                    <button class="btn btn-outline-warning" type="button" id="button-addon2">
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
    </header>
    <session>
        <div class="container">
            <div class="prod" style="width:100%;">
                <div class="row">
                    <div class="col-3">
                        <div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 220px;">
                            <a href=""
                                class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
                                <i class="bi bi-house fs-3 mb-3"></i> &nbsp; <h4> &nbsp;Category</h4>
                            </a>
                            <hr>
                            <ul class="nav nav-pills flex-column mb-auto ">
                                <li class="nav-item">
                                    <a href="#" class="nav-link link-dark">
                                        <i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>Phone</i><br>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="nav-link link-dark">
                                        <i class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>Computer</i><br>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="nav-link link-dark">
                                        <i class="bi bi-camera fs-2 mb-3"> &nbsp;</i>Camera</i><br>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="nav-link link-dark">
                                        <i class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>Watch</i>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="nav-link link-dark">
                                        <i class="bi bi-headset fs-2 mb-3"> &nbsp;</i>Others</i>
                                    </a>
                                </li>
                            </ul>
                            <hr>
                            <div class="dropdown">
                                <a href="#" class="d-flex align-items-center link-dark">
                                </a>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-9">
                         
                        <h2>維修單資料新增:</h2>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RepairForm/RepairForm.do" name="form1">


<table id="table1">
<!-- ==================================================2.會員編號-->	
<div class="form-group">
		<h4>會員編號:</h4>
		<input type="TEXT" name="memno"  placeholder="輸入會員編號"  class="email-bt"
	    value="<%= (repairformVO==null)? "" : repairformVO.getMemno()%>">
</div>
<!-- ==================================================3.一般商城訂單編號-->	
<div class="form-group">
	    <h4>一般商城訂單編號:</h4>
		<input type="TEXT" name="orderno"  placeholder="輸入一般商城訂單編號" class="email-bt"
			 value="<%= (repairformVO==null)? "" : repairformVO.getOrderno()%>" />
</div>
<!-- ==================================================4.商品編號-->	
<div class="form-group">
	    <h4>商品編號:</h4>
		<input type="TEXT" name="itemno"  placeholder="輸入商品編號" class="email-bt"
			 value="<%= (repairformVO==null)? "" : repairformVO.getItemno()%>" />
</div>

<!-- ==================================================5.表單建立時間-->	
 <div class="form-group">
		<h4>表單建立時間:</h4>
		  <input name="createtime" id="f_date1" type="text"  class="email-bt" 
                   placeholder="輸入日期EX:2021-01-01">
</div>
<!-- ===================================================6.維修狀態 -->	
<div class="form-group">
	    <h4>維修狀態:</h4>
		   <input type="TEXT" name="repairformstatus" onfocus="this.blur()" class="email-bt" 
            value="<%= (repairformVO==null)? "維修狀態會由專人填寫" : repairformVO.getRepairformstatus()%>" />
</div>
<!-- ===================================================7.確認維修單員工編號-->	
	<div class="form-group">
	    <h4>維修員工編號(預設為0):</h4>
		   <input type="TEXT" name="adminno" onfocus="this.blur()"placeholder="維修員工編號會由專人填寫" class="email-bt"
           value="<%= (repairformVO==null)? "0" : repairformVO.getAdminno()%>" />
	</div>
<!-- =====================================================8.修理備註-->	
	<div class="form-group">
	    <h4>修理備註:</h4>
        <textarea type="TEXT" name="repairinfo" placeholder=" 修理備註 Massage" rows="5" 
           id="comment"  class="massage-bt" 
                value="<%= (repairformVO==null)? "" : repairformVO.getRepairinfo()%>">
        </textarea>
	</div>
	
<!-- ====================================================9.表單完成時間-->	
		<div class="form-group">
		<h4>表單完成時間:</h4>
		<input name="repairend" id="f_date2" type="text" class="email-bt"
               placeholder="輸入日期EX:2021-01-01">
	</div>
	
</table>
<br>

<input type="hidden" name="action" value="insertf">
<input type="submit" class="button" value="送出新增" onclick="alert('新增成功');">
</body>	


<!-- ============================================以下為createtime的時間設定============================================== -->

<% 
  java.sql.Date createtime = null;
  try {
	  createtime = repairformVO.getCreatetime();
   } catch (Exception e) {
	   createtime = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
       
        $.datetimepicker.setLocale('hh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=createtime%>', // value:   new Date(),
       
        });

 </script>


<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<!-- ============================================以下為Repairend的時間設定============================================== -->

<% 
  java.sql.Date repairend = null;
  try {
	  repairend = repairformVO.getRepairend();
   } catch (Exception e) {
	   repairend = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=repairend%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });


        </script>
        
        <style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </session>
    
<!-- ↑是HEADER========================================================================================================== --> 







<!-- ↓是HEADER========================================================================================================== -->        
 <br>
 <br>
 <br>
 
 
 
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
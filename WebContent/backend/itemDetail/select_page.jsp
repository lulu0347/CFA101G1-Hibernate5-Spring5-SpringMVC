<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>訂單明細首頁</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  #li-1{
  margin-bottom : 30px;
  }
</style>

</head>
<body bgcolor='white'>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li>
  	<div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseSix" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示所有商品訂單明細</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseSix" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/itemDetail/listAll_ItemDetail.jsp'>所有訂單明細</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
  
  
  <li>
  	 <div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingTwo">
	      <h2 class="mb-0">
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一訂單明細查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseSeven" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/getAllDetailByOrderNo" >
	        	<b>輸入訂單編號(以訂單編號查詢此訂單所有細項):</b>
	        	<input type="text" name="orderNo">
	        	<input type="hidden" name="action" value="getAllDetailByOrderNo">
	        	<input type="submit" value="送出">
    		</FORM>

		  </div> 
	    </div>
	  </div>
	</div>
  </li>
 </ul>

</body>
</html>
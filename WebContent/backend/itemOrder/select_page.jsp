<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂單管理 : 首頁</title>
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseEight" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示所有商品訂單</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseEight" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/itemOrder/listAllOrder.jsp'>查詢所有訂單</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseNine" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一商品訂單查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseNine" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/enterListByOrderNo" >
	        	<b>輸入訂單編號 (如24001):</b>
	        	<input type="text" name="orderNo">
	        	<input type="hidden" name="action" value="enterListByOrderNo">
	        	<input type="submit" value="送出">
	    	</FORM>

		  </div> 
	      <div class="card-body" id="searchbyName">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/enterMemNoToFindOrder" >
	        	<b>輸入會員編號 (如11001):</b>
	        	<input type="text" name="memNo">
	        	<input type="hidden" name="action" value="enterMemNoToFindOrder">
	        	<input type="submit" value="送出">
	    	</FORM>  	      	
	      </div>
		<jsp:useBean id="itemOrderSvc" scope="page" class="com.itemOrder.model.ItemOrderService"/>
	      <div class="card-body">
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectListByOrderNo" >
		       <b>選擇訂單編號:</b>
		       <select size="1" name="orderNo">
		         <c:forEach var="itemOrderVO" items="${itemOrderSvc.all}" > 
		          <option value="${itemOrderVO.orderNo}">${itemOrderVO.orderNo}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="selectListByOrderNo">
		       <input type="submit" value="送出">
		    </FORM> 	      	
	      </div>
	      <div class="card-body">
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectMemNoToFindOrder" >
		       <b>選擇會員編號:</b>
		       <select size="1" name="memNo">
		         <c:forEach var="itemOrderVO" items="${itemOrderSvc.all}" > 
		          <option value="${itemOrderVO.memberVO.memNo}">${itemOrderVO.memberVO.memNo}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="selectMemNoToFindOrder">
		       <input type="submit" value="送出">
		    </FORM> 	      	
	      </div>
	     </div> 
	   </div>
	</li>     
<li>
  	<div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse11" aria-expanded="true" aria-controls="collapseOne">
	          	<b>依狀態搜尋訂單</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapse11" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>
							/backend/itemOrder/listAllOrderByNotShipped.jsp'>查詢未出貨訂單</a></b><br>	      
	      </div>
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>
							/backend/itemOrder/listAllOrderByShipped.jsp'>查詢已出貨訂單</a></b><br>	      
	      </div>
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>
							/backend/itemOrder/listAllOrderByReceipt.jsp'>查詢已收貨的訂單</a></b><br>	      
	      </div>
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/
							backend/itemOrder/listAllOrderByReturn.jsp'>查詢被退貨的訂單</a></b><br>	      
	      </div>
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>
							/backend/itemOrder/listAllOrderByCancel.jsp'>查詢被取消的訂單</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
</div>
  
 </ul>
</body>

</html>
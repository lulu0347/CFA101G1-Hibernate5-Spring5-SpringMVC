<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>ProductKind</title>

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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部商品類別</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapse1" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/productKind/listAllProductKind.jsp'>列出所有商品類別</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>新增商品類別</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapse2" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/productKind/addProductKind.jsp'>新增商品類別</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
 </ul>
</body>
</html>
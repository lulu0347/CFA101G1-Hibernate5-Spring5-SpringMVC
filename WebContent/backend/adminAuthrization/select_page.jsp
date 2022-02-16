<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>AdminAuthrization</title>

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

<%-- 錯誤表列 --%>
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部權限功能</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseSeven" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <jsp:include page="/backend/adminAuthrization/listAllAdminAuthrization.jsp" flush="true"/><br><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
  
  	 <li>
	  	<div class="accordion" id="accordionExample">
		  <div class="card">
	   		 <div class="card-header" id="headingThree">
		        <h2 class="mb-0">
		          <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseNine" aria-expanded="false" aria-controls="collapseThree">
		          		<b>新增權限功能</b>
		          </button>
		        </h2>
		     </div>
		  	<div id="collapseNine" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
	      		<div class="card-body">
	      			<jsp:include page="/backend/adminAuthrization/addAdminAuthrization.jsp" flush="true"/><br>
	      		</div>
	    	</div>
	  	  </div>
		</div>
	 </li>
</ul>

</body>
</html>
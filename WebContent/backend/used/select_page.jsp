<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二手商品管理</title>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<ul>
  <li>
  	<div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部商品</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/used/listAllProd.jsp'>列出所有二手商品</a></b><br>	      
	      </div>
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/used/listAllReport.jsp'>列出所有二手商品檢舉</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
 </ul>

</body>
</html>
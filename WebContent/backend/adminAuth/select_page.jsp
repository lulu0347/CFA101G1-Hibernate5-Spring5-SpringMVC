<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
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
<title>員工權限</title>
</head>
<body bgcolor='white'>

	<%-- 錯誤列表 --%>
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部權限功能</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseFour" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	      	<b><a href="<%=request.getContextPath()%>/backend/adminAuth/listAllAdminAuth.jsp">列出所有員工權限</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一員工查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseFive" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <jsp:useBean id="adminiSvc" scope="page" class="com.admini.model.AdminiService"/>
	      <div class="card-body" id="searchbyid">
	      		<FORM method="get" action="<%=request.getContextPath()%>/backend/adminAuth/listOneAdminAuthByAdminNo.jsp">
					<b>選擇員工編號:</b>
	        		<select size="1" name="adminNo">
						<c:forEach var="adminiVO" items="${adminiSvc.all}">
							<option value="${adminiVO.adminNo}">${adminiVO.adminNo}
						</c:forEach>
					</select>
				 <input type="hidden" name="action" value="getOne_For_Display">
				 <input type="submit" value="送出">
				</FORM>
		    <table id="result">

		    </table>
		    </div>
		     <jsp:useBean id="adminAuthrizationSvc" scope="page" class="com.adminauthrization.model.AdminAuthrizationService"/>
	        	<div class="card-body" id="searchbyName">
					<form method="post" action="<%=request.getContextPath()%>/adminAuth/adminAuth.do">
						<b>權限選擇:</b>
						<select size="1" name="adminAuthrizationNo">
							<c:forEach var="adminAuthrizationVO" items="${adminAuthrizationSvc.all}">
								<option value="${adminAuthrizationVO.adminAuthrizationNo}">${adminAuthrizationVO.adminAuthrizationName}
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="submit" value="送出">
					</form>
		    <table id="resultname">

		    </table>
	        	
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
		          <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseThree">
		          		<b>新增員工權限</b>
		          </button>
		        </h2>
		     </div>
		  	<div id="collapseSix" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
	      		<div class="card-body">
	      			<jsp:include page="/backend/adminAuth/addAdminAuth.jsp" flush="true"/><br>
	      		</div>
	    	</div>
	  	  </div>
		</div>
	 </li>
</ul>
</body>
</html>
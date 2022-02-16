<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Admini</title>

<style>
  table#table-1 {
	width: 100%;
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部員工</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href="<%=request.getContextPath()%>/backend/admini/listAllAdmini.jsp">列出所有員工</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一員工查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <jsp:useBean id="adminiSvc" scope="page" class="com.admini.model.AdminiService" />
	      <div class="card-body" id="searchbyid">
	      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admini/admini.do" >
		        <b>選擇員工編號 :</b>
		        <select size="1" name="adminNo" id="adminNO">
			         <c:forEach var="adminiVO" items="${adminiSvc.all}" > 
			          <option value="${adminiVO.adminNo}">${adminiVO.adminNo}
			         </c:forEach>   
			       </select>
		        <input class="oneAdmin" type="hidden" name="action" value="getOne_For_Display">
		        <input id="ibutton" type="button" value="送出">
		    </FORM>
		    <table id="result">

		    </table>
		   </div>   
	        <div class="card-body" id="searchbyName">
			     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admini/admini.do" >
			       <b>選擇員工姓名:</b>
			       <select size="1" name="adminNo" id="selectNo">
			         <c:forEach var="adminiVO" items="${adminiSvc.all}" > 
			          <option value="${adminiVO.adminNo}">${adminiVO.adminName}
			         </c:forEach>   
			       </select>
			       <input type="hidden" name="action" value="getOne_For_Display">
			       <input id="iibutton" type="button" value="送出">
			     </FORM>		  
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
		          <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		          		<b>新增員工</b>
		          </button>
		        </h2>
		     </div>
		  	<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
	      		<div class="card-body">
	      			<b>新增員工資料</b>
	      			<jsp:include page="/backend/admini/addAdmini.jsp" flush="true"/><br>
	      		</div>
	    	</div>
	  	  </div>
		</div>
	 </li>
 </ul>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>
<script>
$('#ibutton').on('click', function(){
	var number = $('#adminNO').val();
	$.ajax({
		 type: "POST",
		 url: "<%=request.getContextPath()%>/admini/admini.do",
		 data: {"adminNo": number,"action":"getOne_For_Display"},
		 dataType: "json",
		 success:function (data){
			 console.log(data);
			 let temp = "<tr><th>員工編號</th><th>員工帳號</th><th>員工密碼</th><th>員工姓名</th><th>員工電話</th></tr><tr><td>"+ data.adminNo + "</td><td>"+data.adminAccount+"</td><td>************</td><td>"+data.adminName+ "</td><td>"+data.adminPhone+ "</td></tr>"
			$('#result').html(temp);
	     },
	});
});

$('#iibutton').on('click', function(){
	var selectNO = $('#selectNo').val();
	$.ajax({
		 type: "POST",
		 url: "<%=request.getContextPath()%>/admini/admini.do",
		 data: {"adminNo": selectNO,"action":"getOne_For_Display"},
		 dataType: "json",
		 success:function (data){
			 console.log(data);
			 let temp = "<tr><th>員工編號</th><th>員工帳號</th><th>員工密碼</th><th>員工姓名</th><th>員工電話</th></tr><tr><td>"+ data.adminNo + "</td><td>"+data.adminAccount+"</td><td>************</td><td>"+data.adminName+ "</td><td>"+data.adminPhone+ "</td></tr>"
			$('#resultname').html(temp);
	     },
	});
});
</script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<html>
<head>

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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseEleven" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示全部檢舉文章</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseEleven" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href="<%=request.getContextPath()%>/backend/forumMsgReport/listAllForumMsgReport.jsp">列出所有檢舉文章</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTweleve" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>會員檢舉文章查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseTwelve" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	      <div class="card-body" id="searchbyid">
	      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forummsgreport/forummsgreport.do" >
		        <b>選擇會員編號 :</b>
		        <select size="1" name="memNo" id="memNO">
			         <c:forEach var="memberVO" items="${memberSvc.all}" > 
			          <option value="${memberVO.memno}">${memberVO.memname}
			         </c:forEach>   
			       </select>
		        <input class="oneMem" type="hidden" name="action" value="getOneMem_For_Display">
		        <input id="ibutton" type="submit" value="送出">
		    </FORM>
		    <table id="result">

		    </table>
	        	
	      </div>	
	      </div>
	    </div>
	  </div>
  </li>
 </ul>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>
<script>

$('#ibutton').on('click', function(){
	var memNO = $('#memNo').val();
	$.ajax({
		 type: "POST",
		 url: "<%=request.getContextPath()%>/admini/admini.do",
		 data: {"memNo": selectNO,"action":"getOne_For_Display"},
		 dataType: "json",
		 success:function (data){
			 console.log(data);
			 let temp = "<tr><th>文章檢舉編號</th><th>文章編號</th><th>會員編號</th><th>檢舉時間</th><th>檢舉原因</th><th>檢舉狀態</th></tr><tr><td>"+ data.forumMsgReportNo + "</td><td>"+data.forumMsgNo+"</td><td>"+data.memNo+ "</td><td>"+data.forumMsgReportTime+ "</td><td>"+data.forumMsgReportWhy+ "</td><td>"+data.forumMsgReportType+ "</td></tr>"
			$('#result').html(temp);
	     },
	});
});
</script>
</body>
</html>
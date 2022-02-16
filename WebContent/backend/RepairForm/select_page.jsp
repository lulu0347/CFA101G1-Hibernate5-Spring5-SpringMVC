<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台維修單首頁</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />

    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/template.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
      
<style>

.button{                               /*設定按鈕初始狀態*/
  background-color:#ff8738;      /*按鈕背景色*/
  border: 1px #ff6600 outset;     /*按鈕邊現顏色，並往外凸*/
  padding: 1px 2px 2px 2px;     /*按鈕內緣與文字的距離*/
  color: white;                         /*文字顏色*/
  text-decoration: none;           /*不要顯示下底線*/
  font: bold 30px Verdana;       /*設定文字：粗體  字體大小  字型*/
}

.button:hover{                    /*滑鼠經過時，按鈕的變化*/
  color: white;
  border-style: inset;               /*邊框變成內凹*/
  background-color:#FFE4CA;
  padding: 1px 1px 1px 3px;   /*改變按鈕內緣與文字的距離，做出往下按的視覺效果*/
}

</style>   
</head>
<body>
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
	        <b><a href='<%=request.getContextPath()%>/backend/RepairForm/listAllRepairForm.jsp' class="button">查詢全部</a></b><br>	      
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
	          	<b>單一維修查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <jsp:useBean id="repairformSvc" scope="page" class="com.repairform.model.RepairFormService" />
	       <div class="card-body" id="searchbyid">		 
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RepairForm/RepairForm.do" >
		       <b>選擇維修單編號:</b>
		       <select size="1" name="repairformno">
		         <c:forEach var="repairformVO" items="${repairformSvc.all}" > 
		          <option value="${repairformVO.repairformno}">${repairformVO.repairformno}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		    </FORM>
	       </div>
	       <div class="card-body" id="searchbyMemNo">		 
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RepairForm/RepairForm.do" >
		       <b>輸入會員編號:</b>
		       <select size="1" name="repairformno">
		         <c:forEach var="repairformVO" items="${repairformSvc.all}" > 
		          <option value="${repairformVO.repairformno}">${repairformVO.memno}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		     </FORM>
	       </div>
	       <div class="card-body" id="searchbyName">
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RepairForm/RepairForm.do" >
		        <b>輸入維修單編號 :</b>
		        <input type="text" name="repairformno">
		        <input type="hidden" name="action" value="getOne_For_Display">
		        <input type="submit" value="送出">
		    </FORM>		  
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
		          		<b>新增維修單</b>
		          </button>
		        </h2>
		     </div>
		  	<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
	      		<div class="card-body">
	      			<a href='<%=request.getContextPath()%>/backend/RepairForm/addRepairForm.jsp' class="button" >新增維修單</a><br>
	      		</div>
	    	</div>
	  	  </div>
		</div>
	 </li>
 </ul>
</body>

</html>
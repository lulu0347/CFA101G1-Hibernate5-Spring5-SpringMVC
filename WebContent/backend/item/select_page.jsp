<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>item : Home</title>

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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示所有商品</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/item/listAllItem.jsp'>列出所有商品</a></b><br>	      
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
	          	<b>單一商品查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/enterItemNo" >
	        	<b>輸入商品編號 (如21001):</b>
	        	<input type="text" name="itemNo">
	        	<input type="submit" value="送出">
	    	</FORM>

		  </div> 
	<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService"/>
	      <div class="card-body">
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectItemNo" >
		       <b>選擇商品編號:</b>
		       <select size="1" name="itemNo">
		         <c:forEach var="itemVO" items="${itemSvc.all}" > 
		          <option value="${itemVO.itemNo}">${itemVO.itemNo}
		         </c:forEach>   
		       </select>
		       <input type="submit" value="送出">
		    </FORM> 	      	
	      </div>
	      
	      <div class="card-body">
	          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectItemName" >
		       <b>選擇商品名稱:</b>
		       <select size="1" name="itemNo">
		         <c:forEach var="itemVO" items="${itemSvc.all}" > 
		          <option value="${itemVO.itemNo}">${itemVO.itemName}
		         </c:forEach>   
		       </select>
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
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseOne">
	          	<b>新增商品</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseThree" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/additemPreWork'>新增商品</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
 </ul>

</body>
</html>
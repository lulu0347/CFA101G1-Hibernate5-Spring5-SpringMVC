<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itemPic.model.*"%>

<html>
<head>
<title>圖片管理 : 首頁</title>
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示所有商品圖片</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseThree" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href="<%=request.getContextPath()%>/backend/itemPic/listAll_ItemPic.jsp">查詢所有圖片</a></b><br>	      
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
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一商品照片查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapseFour" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
			<h3>依商品照片編號查詢</h3>
			<form method="post" action="<%=request.getContextPath()%>/findByItemPicNo">
				<input type="text" name="itemPicNo" value="" placeholder="請輸入商品照片編號">
				<input type="hidden" name="action" value="findByItemPicNo">
				<input type="submit" value="送出查詢">
			</form>

		  </div> 
	      <div class="card-body" id="searchbyName">
			 <h3>依商品編號查詢</h3>
			<form method="post" action="<%=request.getContextPath()%>/findByItemNo">
				<input type="text" name="itemNo" value="" placeholder="請輸入商品編號">
				<input type="hidden" name="action" value="findByItemNo">
				<input type="submit" value="送出查詢">
			</form>	  	      	
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
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="true" aria-controls="collapseOne">
	          	<b>新增商品圖片</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapseFive" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b>新增圖片</b><br>
	        <jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService"/>
	        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectItemNoToAddPic" >
		       <b>選擇商品編號:</b>
		       <select size="1" name="itemNo">
		         <c:forEach var="itemVO" items="${itemSvc.all}" > 
		          <option value="${itemVO.itemNo}">${itemVO.itemNo}
		         </c:forEach>   
		       </select>
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




















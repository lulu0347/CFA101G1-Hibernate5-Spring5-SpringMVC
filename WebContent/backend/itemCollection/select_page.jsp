<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>itemCollection : Home</title>

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

<table id="table-1">
   <tr><td><h3>itemCollection : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for itemCollection : Home</p>

<h3>資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/itemCollection/listAllItemCollection.jsp'>列出</a>所有會員的收藏列表<br></li>
	
	<li>
    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itemCollection/itemCollection.do" >
        	<b>輸入會員編號(以會員編號查詢此會員所存取的收藏列表):</b>
        	<input type="text" name="memNo">
        	<input type="hidden" name="action" value="listByMemNo">
        	<input type="submit" value="送出">
    	</FORM>
 	</li>
 	
 	<jsp:useBean id="itemCollectionSvc" scope="page" class="com.itemCollection.model.ItemCollectionService"/>


</ul>
  
<h3>商品管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/itemCollection/addItemCollection.jsp'>後台新增商品收藏列表資資料</a></li>
</ul>


  
</body>
</html>
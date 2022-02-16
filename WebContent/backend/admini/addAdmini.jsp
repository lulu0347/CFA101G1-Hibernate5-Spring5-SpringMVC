<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admini.model.*"%>

<%
	AdminiVO adminiVO = (AdminiVO) request.getAttribute("adminiVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addAdmini.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admini/admini.do" name="form1">
<table>
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="adminAccount" size="20" id="account"
			 value="<%= (adminiVO==null)? "" : adminiVO.getAdminAccount()%>" pattern="^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$" required="required" oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的帳號格式:含英數6~20個字元');"/>
			 <br><span id="error" style="color:red;"></span></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input type="Password" name="adminPassword" size="20" id="password"
			 value="<%= (adminiVO==null)? "" : adminiVO.getAdminPassword()%>" pattern="^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$" required="required" oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的密碼格式:含英數6~20個字元');"/></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="adminName" size="20" id="name"
			 value="<%= (adminiVO==null)? "" : adminiVO.getAdminName()%>" required="required" pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$" oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的格式:中、英文字母、數字,且長度必需在2到10之間');"/></td>
	</tr>
	<tr>
		<td>員工電話:</td>
		<td><input type="TEXT" name="adminPhone" size="20" id="phone"
			 value="<%= (adminiVO==null)? "" : adminiVO.getAdminPhone()%>" pattern="^(?=.*[0-9]).{10,10}$" required="required" oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的電話格式:10個字元');"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input id="demo1" type="submit" value="送出新增"></FORM>
<script src="<%=request.getContextPath() %>/backend/assets/js/jquery.min.js"></script>
<script>
$("#account").on("blur", function(){
	var Account = $('#account').val();
	$.ajax({
		 type: "POST",
		 url: "<%=request.getContextPath()%>/admini/admini.do",
		 data: {"action":"check","adminAccount":Account},
		 dataType: "json",
		 success:function(data) {
			 console.log(data);
			 $("#error").text(data);
		 }	 
	 });
});
</script>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itemOrder.model.*"%>

<%
  ItemOrderVO itemOrderVO = (ItemOrderVO) request.getAttribute("itemOrderVO");
%>  

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料修改 - update_order_input.jsp</title>

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
	width: 600px;
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

<table id="table-1">
	<tr><td>
		 <h3>訂單資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/item.jsp">回商品管理</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/selectOneOrderToUpdate" name="form1">

<table>
	<tr>
		<td>一般商城訂單編號:</td>
		<td><%=itemOrderVO.getOrderNo()%></td>
	</tr>
	
	<tr>
		<td>購買會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="memNo" size="45" 
			 value="<%= (itemOrderVO==null)? "" : itemOrderVO.getMemberVO().getMemNo()%>" /></td>
	</tr>
	
	<tr>
		<td>商品售出日期:<font color=red><b>*</b></font></td>
		<td><input name="tranTime" id="f_date1" type="text"></td>
	</tr>

	<tr>
		<td>訂單金額:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="orderTotal" size="45" 
			 value="<%= (itemOrderVO==null)? "" : itemOrderVO.getOrderTotal()%>" /></td>
	</tr>
		
	<tr>
		<td>訂單狀態:<font color=red><b>*</b></font></td>
		<td>
		<select name="orderState" id="orderState">
			<option>請選擇</option>
			<option value="0" <c:if test="${itemOrderVO.getOrderState() == 0}"><c:out value="selected"></c:out></c:if>>未出貨</option>
			<option value="1" <c:if test="${itemOrderVO.getOrderState() == 1}"><c:out value="selected"></c:out></c:if>>已出貨</option>
			<option value="2" <c:if test="${itemOrderVO.getOrderState() == 2}"><c:out value="selected"></c:out></c:if>>已收貨</option>
			<option value="3" <c:if test="${itemOrderVO.getOrderState() == 3}"><c:out value="selected"></c:out></c:if>>退貨</option>
			<option value="4" <c:if test="${itemOrderVO.getOrderState() == 4}"><c:out value="selected"></c:out></c:if>>取消</option>
		</select>
		</td>
	</tr>	
	
	<tr>
		<td>收件人姓名:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="receiverName" size="45" 
			 value="<%= (itemOrderVO==null)? "" : itemOrderVO.getReceiverName()%>" /></td>
	</tr>		

	<tr>
		<td>收件人地址:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="receiverAddress" size="45" 
			 value="<%= (itemOrderVO==null)? "" : itemOrderVO.getReceiverAddress()%>" /></td>
	</tr>	
	
	<tr>
		<td>收件人電話:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="receiverPhone" size="45" 
			 value="<%= (itemOrderVO==null)? "" : itemOrderVO.getReceiverPhone()%>" /></td>
	</tr>	
	
</table>

<br>
<input type="hidden" name="action" value="updateByOrderNo">
<input type="hidden" name="orderNo" value="<%=itemOrderVO.getOrderNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#f_date1').datetimepicker({
           theme: '',          //theme: 'dark',
           timepicker:true,   //timepicker: false,
           step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
	       format: 'Y-m-d H:i:s',
	       value: '<%=itemOrderVO.getTranTime()%>'
           //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	        '2021/07',  // 起始日
//            minDate:           '-1970-01-01', // 去除今日(不含)之前
           //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        });
</script>

</html>
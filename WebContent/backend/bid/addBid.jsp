<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.productKind.model.*"%>
<%@ page import="java.util.*"%>

<%
  BidVO bidVO = (BidVO) request.getAttribute("bidVO");
  ProductKindService kindSvc = new ProductKindService();
  List<ProductKindVO> list = kindSvc.getAllProductKind();
  pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBid.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/bid/css/addBid.css"/>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>競標資料新增 - addBid.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/backend/bid.jsp"><img src="<%=request.getContextPath()%>/resources/icon/logo.png" width="32px" height="32px" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>競標商品名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="bidProdName" size="45"
			 value="<%= (bidVO==null)? "" : bidVO.getBidProdName()%>" /></td>
	</tr>
	<tr>
		<td>商品類別:<font color=red><b>*</b></font></td>
		<td>
			<select name="kindNo" id="kindNo">
			<c:forEach var="kindVO" items="${list}">
				<option value="${kindVO.getKindNo()}" ${(bidVO.kindNo==kindVO.kindNo) ? 'selected':''}>${kindVO.kindName}
			 </c:forEach>
			 </select>
		</td>
	</tr>
  
	<tr>
		<td>競標商品敘述:</td>
		<td><textArea name="bidProdDescription" id="bidProdDescription" rows="6" cols="42"><%=(bidVO==null)? "" : bidVO.getBidProdDescription()%></textArea></td>
	</tr>
	<tr>
		<td>起標價:<font color=red><b>*</b></font></td>
		<td><input type="NUMBER" name="bidProdStartPrice" size="30" min="0"
			 value="<%= (bidVO==null)? "0" : bidVO.getBidProdStartPrice()%>" /></td>
	</tr>
	<tr>
		<td>最低增額:<font color=red><b>*</b></font></td>
		<td><input type="NUMBER" name="bidPriceIncrement" size="30" min="0"
			 value="<%= (bidVO==null)? "0" : bidVO.getBidPriceIncrement()%>" /></td>
	</tr>
	<tr>
		<td>起標時間:<font color=red><b>*</b></font></td>
		<td><input name="bidProdStartTime" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>截標時間:<font color=red><b>*</b></font></td>
		<td><input name="bidProdEndTime" id="f_date2" type="text"></td>
	</tr>

<!-- ---------------------------測試同時上傳圖片----------------------------------------------- -->
<tr>
<td>

	        <input type="file" name="upfile1" onclick="previewImage()" multiple id="upfile">
	        <input type="hidden" name="action" value="insert_with_pics">
<!-- 	        <input type="submit" value="上傳圖片" class="button"> -->
</td>
</tr>

<!-- ---------------------------測試同時上傳圖片----------------------------------------------- -->
</table>
<br>
<!-- <input type="hidden" name="action" value="insert"> -->
<input type="submit" value="送出新增">
<input type="reset" value="重設"></FORM>

<div id="holder"></div>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp bidProdStartTime = null;
	try {
		bidProdStartTime = bidVO.getBidProdStartTime();
	} catch (Exception e) {
		bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
   }
	
	java.sql.Timestamp bidProdEndTime = null;
	try {
		bidProdEndTime = bidVO.getBidProdEndTime();
	} catch (Exception e) {
		bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
   }
%>

<script src="<%=request.getContextPath()%>/resources/css/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/css/jquery.datetimepicker.full.js"></script>

<script type="text/javascript">
var filereader_support = typeof FileReader != 'undefined';

if (!filereader_support) {
	alert("No FileReader support");
}

acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
};

function previewImage() {
	var upfile = document.getElementById("upfile");
	upfile.addEventListener("change", function(event) {
		var files = event.target.files || event.dataTransfer.files;
		for (var i = 0; i < files.length; i++) {
			previewfile(files[i])
		}
	}, false);
}


function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = new Image();
			image.src = event.target.result;
			image.width = 100;
			holder.appendChild(image);
		};
		reader.readAsDataURL(file);
	} else {
		holder.innerHTML += "<p>" + "filename: <strong>" + file.name
				+ "</strong><br>" + "ContentTyp: <strong>" + file.type
				+ "</strong><br>" + "size: <strong>" + file.size
				+ "</strong> bytes</p>";
	}
}
</script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=bidProdStartTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=bidProdEndTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
//              var somedate1 = new Date('2017-06-15');
//              $('#f_date2').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate1.getYear() || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</body>
</html>
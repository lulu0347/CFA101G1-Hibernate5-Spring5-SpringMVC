<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%@ page import="java.util.*"%>

<%
	BidVO bidVO = (BidVO) request.getAttribute("bidVO"); //BidServlet.java (Controller) 存入req的bidVO物件 (包括幫忙取出的bidVO, 也包括輸入資料錯誤時的bidVO物件)
	//
	request.setAttribute("bidVO", bidVO); //BidServlet.java (Controller) 存入req的bidVO物件 (包括幫忙取出的bidVO, 也包括輸入資料錯誤時的bidVO物件)
	//
	BidPicService bidPicSvc = new BidPicService();
	List<BidPicVO> list = bidPicSvc.getAllBidPic_bidProdNo(bidVO.getBidProdNo());
	pageContext.setAttribute("list",list);
%>
<%-- <%= bidVO==null %>--${bidVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>競標商品資料修改 - update_bid_input.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css"/>
<style>
  table {
    background-color: white;
    margin-bottom: 5px;
    font-size: 13px;
  }
  table, th, td {
    border-bottom: 1px solid #CCC;
  }
  th, td {
    padding: 5px;
    text-align: left;
  }
  .showPanel tr:nth-child(even){
      background-color:#e8e8e8;
  }
  .showPanel tr:nth-child(odd){
      background-color:#ccc;
  }
  .showPanel tr:nth-child(1){
      background-color:#7788aa;color:#ffffff;
  }
    table#table-1 {
    width: 100%;
     background-color: #bfbfbf; 
     margin-bottom: 10px;
     border-bottom: 1px ridge Gray;
    height: 90px;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }
  table#table-1 h4 {
    color: black;
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
	<tr><td>
		 <h3>競標商品資料修改 - update_bid_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/bid.jsp"><img src="<%=request.getContextPath()%>/resources/icon/logo.png" width="32px" height="32px" border="0">回首頁</a> || <a href="<%=request.getContextPath()%>/backend/bid/listAllBid.jsp">回競標商品列表</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" name="form1">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=bidVO.getBidProdNo()%></td>
	</tr>
	<tr>
		<td>商品名稱:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="bidProdName" size="45" value="<%=bidVO.getBidProdName()%>" /></td>
	</tr>
	<tr>
		<td>商品類別:<font color=red><b>*</b></font></td>
		<td>
			<select name="kindNo" id="kindNo">

				 <option value="1" <c:if test="${bidVO.getKindNo() == 1}"><c:out value="selected"></c:out></c:if>>手機</option>
				 <option value="2" <c:if test="${bidVO.getKindNo() == 2}"><c:out value="selected"></c:out></c:if>>電腦</option>
				 <option value="3" <c:if test="${bidVO.getKindNo() == 3}"><c:out value="selected"></c:out></c:if>>手錶</option>
				 <option value="4" <c:if test="${bidVO.getKindNo() == 4}"><c:out value="selected"></c:out></c:if>>相機</option>
				 <option value="5" <c:if test="${bidVO.getKindNo() == 5}"><c:out value="selected"></c:out></c:if>>配件</option>
			 </select>
		</td>
<%-- 			 <input type="TEXT" name="kindNo" size="45" value="<%=bidVO.getKindNo()%>" /></td> --%>
<!-- 		<td><select name="kindNo" id="kindNo"> -->
<!-- 			 <option>請選擇</option> -->
<!-- 			 <option>手機</option> -->
<!-- 			 <option>電腦</option> -->
<!-- 			 <option>手錶</option> -->
<!-- 			 <option>相機</option> -->
<!-- 			 <option>配件</option> -->
<!-- 			 </select> -->
<!-- 		</td> -->
	</tr>
	<tr>
		<td>商品敘述:</td>
		<td><textArea name="bidProdDescription" id="bidProdDescription" rows="6" cols="42" ><%=bidVO.getBidProdDescription()%></textArea></td>
	</tr>
	<tr>
		<td>起標價:<font color=red><b>*</b></font></td>
		<td><input type="NUMBER" name="bidProdStartPrice" size="45" min="0"
			 value="<%=bidVO.getBidProdStartPrice()%>" /></td>
	</tr>
	<tr>
		<td>最低增額:<font color=red><b>*</b></font></td>
		<td><input type="NUMBER" name="bidPriceIncrement" size="45" min="0"
			 value="<%=bidVO.getBidPriceIncrement()%>" /></td>
	</tr>
	<tr>
		<td>競標狀態:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="bidState">
				<option value="0" <c:if test="${bidVO.getBidState() == 0}"><c:out value="selected"></c:out></c:if>>未結束</option>
				<option value="1" <c:if test="${bidVO.getBidState() == 1}"><c:out value="selected"></c:out></c:if>>截標</option>
				<option value="2" <c:if test="${bidVO.getBidState() == 2}"><c:out value="selected"></c:out></c:if>>流標</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>起標時間:<font color=red><b>*</b></font></td>
		<td><input name="bidProdStartTime" id="f_date1" type="text" value="<%=bidVO.getBidProdStartTime()%>"></td>
	</tr>
	<tr>
		<td>截標時間:<font color=red><b>*</b></font></td>
		<td><input name="bidProdEndTime" id="f_date2" type="text" value="<%=bidVO.getBidProdEndTime()%>"></td>
	</tr>
	<tr>
		<td>得標價:</td>
		<td><%=bidVO.getBidWinnerPrice()%></td>
	</tr>
	<tr>
		<td>得標會員編號:</td>
		<td><%=bidVO.getBidWinnerNo()%></td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><input type="TEXT" name="receiverName" size="45" value="${bidVO.receiverName}" /></td>
	</tr>
	<tr>
		<td>收件人地址:</td>
		<td><input type="TEXT" name="receiverAddress" size="45" value="${bidVO.receiverAddress}" /></td>
	</tr>
	<tr>
		<td>收件人電話:</td>
		<td><input type="TEXT" name="receiverPhone" size="45" value="${bidVO.receiverPhone}" /></td>
	</tr>
	<tr>
		<td>錢包交易編號:</td>
		<td><%=bidVO.getTransRecNo()%></td>
	</tr>
	<tr>
		<td>競標商品狀態:</td>
		<td>
			<select size="1" name="bidProdState">
				<option value="0" <c:if test="${bidVO.getBidProdState() == 0}"><c:out value="selected"></c:out></c:if>>未結帳</option>
				<option value="1" <c:if test="${bidVO.getBidProdState() == 1}"><c:out value="selected"></c:out></c:if>>訂單處理中</option>
				<option value="2" <c:if test="${bidVO.getBidProdState() == 2}"><c:out value="selected"></c:out></c:if>>已出貨</option>
				<option value="3" <c:if test="${bidVO.getBidProdState() == 3}"><c:out value="selected"></c:out></c:if>>已收貨</option>
				<option value="4" <c:if test="${bidVO.getBidProdState() == 4}"><c:out value="selected"></c:out></c:if>>退貨</option>
				<option value="4" <c:if test="${bidVO.getBidProdState() == 5}"><c:out value="selected"></c:out></c:if>>棄標</option>
			</select>
		</td>
<%-- 		<td><input type="TEXT" name="bidProdState" value="<%=bidVO.getBidProdState()%>"></td> --%>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>圖片:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="bidPicVO" items="${bidPicSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>

<input type="hidden" name="action" value="update">
<input type="hidden" name="bidProdNo" value="<%=bidVO.getBidProdNo()%>">
<input type="hidden" name="bidState" value="<%=bidVO.getBidState()%>">

<input type="hidden" name="transRecNo" value="<%=bidVO.getTransRecNo()%>">
<input type="hidden" name="bidWinnerNo" value="<%=bidVO.getBidWinnerNo()%>">
<input type="hidden" name="bidWinnerPrice" value="<%=bidVO.getBidWinnerPrice()%>">
<input type="hidden" name="receiverName" value="<%=bidVO.getReceiverName()%>">
<input type="hidden" name="receiverAddress" value="<%=bidVO.getReceiverAddress()%>">
<input type="hidden" name="receiverPhone" value="<%=bidVO.getReceiverPhone()%>">
<input type="submit" value="送出修改">
<input type="reset" value="重設">

</FORM>
		<form id="upload" action="<%=request.getContextPath()%>/bidpic/bidpic.do" method="POST" enctype="multipart/form-data" name="form1" onsubmit="return ">
			<jsp:useBean id="bidSvc" scope="page" class="com.bid.model.BidService" />
	        <input type="file" name="upfile1" onclick="previewImage()" multiple id="upfile">
	    
	    <input type="hidden" name="bidProdNo" value="${bidVO.getBidProdNo()}">    
	    <input type="hidden" name="bidProdName" value="${bidVO.getBidProdName()}">
		<input type="hidden" name="kindNo" value="${bidVO.getKindNo()}">
		<input type="hidden" name="bidProdDescription" value="${bidVO.getBidProdDescription()}">
		<input type="hidden" name="bidProdStartPrice" value="${bidVO.getBidProdStartPrice()}">
		<input type="hidden" name="bidPriceIncrement" value="${bidVO.getBidPriceIncrement()}">
		<input type="hidden" name="bidProdStartTime" value="${bidVO.getBidProdStartTime()}">
		<input type="hidden" name="bidProdEndTime" value="${bidVO.getBidProdEndTime()}">
		<input type="hidden" name="bidState" value="${bidVO.getBidState()}">
		<input type="hidden" name="transRecNo" value="${bidVO.getTransRecNo()}">
		<input type="hidden" name="bidWinnerNo" value="${bidVO.getBidWinnerNo()}">
		<input type="hidden" name="bidWinnerPrice" value="${bidVO.getBidWinnerPrice()}">
		<input type="hidden" name="receiverName" value="${bidVO.getReceiverName()}">
		<input type="hidden" name="receiverAddress" value="${bidVO.getReceiverAddress()}">
		<input type="hidden" name="receiverPhone" value="${bidVO.getReceiverPhone()}">
		<input type="hidden" name="bidProdState" value="${bidVO.getBidProdState()}">
	        
	        <input type="hidden" name="action" value="insert_multi_bid">
	        <input type="submit" value="上傳圖片" class="button">
		</form>
		
		 <div id="holder"></div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidpic/bidpic.do" onsubmit="return checkConfirm();">
<br>
	<c:if test="${list.size() != 0}">

<%-- 	<jsp:useBean id="bidPicSvc" scope="page" class="com.bidpic.model.BidPicService" /> --%>
	<c:forEach var="bidPicVO" items="${list}">
<%-- 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidpic/bidpic.do"> --%>
		<img src="<%=request.getContextPath()%>/bidpic/DBGifReader4?bidProdPicNo=${bidPicVO.getBidProdPicNo()}" height="64px" width="64px">
		<input type="hidden" name="bidProdPicNo" value="${bidPicVO.getBidProdPicNo()}">
		<input type="checkbox" name="bidProdPicNos" value="${bidPicVO.getBidProdPicNo()}" class="delete_checkbox">
		<input type="hidden" name="bidProdNo" value="${bidPicVO.getBidProdNo()}">
	</c:forEach>

		<input type="hidden" name="bidProdName" value="${bidVO.getBidProdName()}">
		<input type="hidden" name="kindNo" value="${bidVO.getKindNo()}">
		<input type="hidden" name="bidProdDescription" value="${bidVO.getBidProdDescription()}">
		<input type="hidden" name="bidProdStartPrice" value="${bidVO.getBidProdStartPrice()}">
		<input type="hidden" name="bidPriceIncrement" value="${bidVO.getBidPriceIncrement()}">
		<input type="hidden" name="bidProdStartTime" value="${bidVO.getBidProdStartTime()}">
		<input type="hidden" name="bidProdEndTime" value="${bidVO.getBidProdEndTime()}">
		<input type="hidden" name="bidState" value="${bidVO.getBidState()}">
		<input type="hidden" name="transRecNo" value="${bidVO.getTransRecNo()}">
		<input type="hidden" name="bidWinnerNo" value="${bidVO.getBidWinnerNo()}">
		<input type="hidden" name="bidWinnerPrice" value="${bidVO.getBidWinnerPrice()}">
		<input type="hidden" name="receiverName" value="${bidVO.getReceiverName()}">
		<input type="hidden" name="receiverAddress" value="${bidVO.getReceiverAddress()}">
		<input type="hidden" name="receiverPhone" value="${bidVO.getReceiverPhone()}">
		<input type="hidden" name="bidProdState" value="${bidVO.getBidProdState()}">

		<input type="hidden" name="action" value="delete">
<!-- 		<button type="button" id="toggle">全選</button> -->
		<input type="submit" value="刪除圖片">
		
<!-- 		</FORM> -->
	</c:if>
<br>
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Timestamp bidProdStartTime = null;
	try {
		bidProdStartTime = bidVO.getBidProdStartTime();
	} catch (Exception e) {
		bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis());
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
<script>
// document.getElementById("toggle").addEventListener("click", checkboxSelectAll);
// 	function checkboxSelectAll() {
// 		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
// 		for (checkbox in checkboxes) {
// 			checkbox.checked = !checked;
// 		}
// 	}

	function checkConfirm() {
		if (document.querySelectorAll("input[type='checkbox']:checked").length == 0) {
			alert("未勾選任何圖片");
			return false;
		} else {
			return true;
		}
	}
	
	function checkUpload() {
		if (document.querySelectorAll("input[type='file']").value == 0) {
			alert('未選擇任何檔案');
		}
	}
	
	
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

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

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
//            minDate:               '-1970-01-01', // 去除今日(不含)之前
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
//            minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
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
</html>
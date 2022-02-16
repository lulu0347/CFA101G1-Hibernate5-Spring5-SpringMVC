<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bid.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%@ page import="com.bidrecord.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberVO memVO = (MemberVO) session.getAttribute("user");
	Integer bidWinnerNo = memVO.getMemNo();
	Integer bidProdState = new Integer(4);

    BidService bidSvc = new BidService();
    List<BidVO> list = bidSvc.findByBidWinnerNoANDBidProdState(bidWinnerNo, bidProdState);
    pageContext.setAttribute("list",list);

%>


<html>
<head>
<title>競標出價資料 - listAllBidOrder.jsp</title>

<style>
*{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
}
h2{
    text-align: center;
    font-size: 18px;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: white;
    padding: 30px 0;
}

/* Table Styles */

.table-wrapper{
margin: 0 auto;
/*     margin: 10px 70px 70px; */
    box-shadow: 0px 35px 50px rgba( 0, 0, 0, 0.2 );
}

.fl-table {
    border-radius: 5px;
    font-size: 16px;
    font-weight: normal;
    border: none;
    border-collapse: collapse;
    width: 100%;
    max-width: 100%;
    white-space: nowrap;
    background-color: white;
}

.fl-table td, .fl-table th {
    text-align: center;
    padding: 8px;
}

.fl-table td {
    border-right: 1px solid #f8f8f8;
    font-size: 16px;
}

.fl-table thead th {
    color: #ffffff;
    background: #4FC3A1;
}


.fl-table thead th:nth-child(odd) {
    color: #ffffff;
    background: #324960;
}

.fl-table tr:nth-child(even) {
    background: #F8F8F8;
}

/* Responsive */

@media (max-width: 767px) {
    .fl-table {
        display: block;
        width: 100%;
    }
    .table-wrapper:before{
        content: "Scroll horizontally >";
        display: block;
        text-align: right;
        font-size: 11px;
        color: white;
        padding: 0 0 10px;
    }
    .fl-table thead, .fl-table tbody, .fl-table thead th {
        display: block;
    }
    .fl-table thead th:last-child{
        border-bottom: none;
    }
    .fl-table thead {
        float: left;
    }
    .fl-table tbody {
        width: auto;
        position: relative;
        overflow-x: auto;
    }
    .fl-table td, .fl-table th {
        padding: 20px .625em .625em .625em;
        height: 60px;
        vertical-align: middle;
        box-sizing: border-box;
        overflow-x: hidden;
        overflow-y: auto;
        width: 120px;
        font-size: 13px;
        text-overflow: ellipsis;
    }
    .fl-table thead th {
        text-align: left;
        border-bottom: 1px solid #f7f7f9;
    }
    .fl-table tbody tr {
        display: table-cell;
    }
    .fl-table tbody tr:nth-child(odd) {
        background: none;
    }
    .fl-table tr:nth-child(even) {
        background: transparent;
    }
    .fl-table tr td:nth-child(odd) {
        background: #F8F8F8;
        border-right: 1px solid #E6E4E4;
    }
    .fl-table tr td:nth-child(even) {
        border-right: 1px solid #E6E4E4;
    }
    .fl-table tbody td {
        display: block;
        text-align: center;
    }
}
</style>

<style>
/*   table { */
/* 	width: 900px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/* 	font-size: 24px; */
/*   } */
/*   table, th, td { */
/*     border: 1px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 5px; */
/*     text-align: center; */
/*   } */
/*   .showPanel tr:nth-child(even){ */
/*   	background-color:#e8e8e8; */
/*   } */
/*   .showPanel tr:nth-child(odd){ */
/*   	background-color:#ccc; */
/*   } */
/*   .showPanel tr:nth-child(1){ */
/*   	background-color:#7788aa;color:#ffffff; */
/*   } */
</style>
</head>

<div class="table-wrapper">
<table class="showPanel fl-table table table-striped table-hover">
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品類別</th>
		<th>得標會員</th>
		<th>得標價</th>
		<th>收件人</th>
		<th>收件地址</th>
		<th>收件人電話</th>
		<th>截標時間</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
	</tr>

	<c:if test="${list == null}">
	<tr>
		<td colspan="9">
		目前尚無資料!
		</td>
	</tr>
	</c:if>
	<c:forEach var="bidVO" items="${list}">
		
		<tr>
			<td>${bidVO.bidProdNo}</td>
			<td><a href="<%=request.getContextPath()%>/frontend/bid/listOneBid.html?bidProdNo=${bidVO.bidProdNo}">${bidVO.bidProdName}</a></td>
			<td>
				<c:if test="${bidVO.kindNo == 1}" var="kind">
					<c:out value="手機" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 2}" var="kind">
					<c:out value="電腦" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 3}" var="kind">
					<c:out value="手錶" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 4}" var="kind">
					<c:out value="相機" escapeXml="false"></c:out>
				</c:if>
				<c:if test="${bidVO.kindNo == 5}" var="kind">
					<c:out value="其他" escapeXml="false"></c:out>
				</c:if>
			</td>
			<td>${bidVO.bidWinnerNo}</td>
			<td>${bidVO.bidWinnerPrice}</td>
			<td>${bidVO.receiverName}</td>
			<td>${bidVO.receiverAddress}</td>
			<td>${bidVO.receiverPhone}</td>
			<td><fmt:formatDate value="${bidVO.bidProdEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bid/bid.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="bidProdNo"  value="${bidVO.bidProdNo}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>
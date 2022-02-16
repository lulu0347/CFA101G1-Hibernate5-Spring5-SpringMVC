<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.used.model.*"%>

<%
	UsedService usedSrv = new UsedService();
	List<UsedVO> list = usedSrv.getAllUsed();
	pageContext.setAttribute("list", list);
%>
<%
	UsedVO usedVO = (UsedVO) request.getAttribute("usedVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addUsed.jsp</title>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front-end/used/UsedServlet"
		style="margin-bottom: 0px;">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="usedName"
					value="<%=(usedVO == null) ? "測試商品" : usedVO.getUsedName()%>"></td>
			</tr>
			<tr>
				<td>買家編號:</td>
				<td><input type="text" name="buyerNo"
					value="<%=(usedVO == null) ? "1000" : usedVO.getBuyerNo()%>"></td>
			</tr>
			<tr>
				<td>賣家編號:</td>
				<td><input type="text" name="sellerNo"
					value="<%=(usedVO == null) ? "1000" : usedVO.getSellerNo()%>"></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="text" name="usedPrice"
					value="<%=(usedVO == null) ? "1000" : usedVO.getUsedPrice()%>"></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="text" name="usedState"
					value="<%=(usedVO == null) ? "1" : usedVO.getUsedState()%>"></td>
			</tr>
			<tr>
				<td>收件人姓名:</td>
				<td><input type="text" name="receiverName"
					value="<%=(usedVO == null) ? "測試收件人" : usedVO.getReceiverName()%>"></td>
			</tr>
			<tr>
				<td>收件地址:</td>
				<td><input type="text" name="receiverAddress"
					value="<%=(usedVO == null) ? "測試地址" : usedVO.getReceiverAddress()%>"></td>
			</tr>
			<tr>
				<td>收件人電話:</td>
				<td><input type="text" name="receiverPhone"
					value="<%=(usedVO == null) ? "0900000000" : usedVO.getReceiverPhone()%>"></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="text" name="usedProdDescription"
					value="<%=(usedVO == null) ? "測試商品描述" : usedVO.getUsedProdDescription()%>"></td>
			</tr>
			<tr>
				<td>商品種類:</td>
				<td><input type="text" name="kindNo"
					value="<%=(usedVO == null) ? "1" : usedVO.getKindNo()%>"></td>
			</tr>
			<tr>
				<td>交易紀錄:</td>
				<td><input type="text" name="transRecNo"
					value="<%=(usedVO == null) ? "1000" : usedVO.getTransRecNo()%>"></td>
			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input type="text" name="usedLaunchedTime"
					value="<%=(usedVO == null) ? "2020-01-01" : usedVO.getUsedLaunchedTime()%>"></td>
			</tr>
			<tr>
				<td>下架時間:</td>
				<td><input type="text" name="soldTime"
					value="<%=(usedVO == null) ? "2021-01-01" : usedVO.getSoldTime()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="送出"> <input
					type="hidden" name="action" value="addUsed"></td>
			</tr>
		</table>
	</FORM>
	<%
		if (request.getAttribute("addUsed") != null) {
	%>
	<jsp:include page="/frontend/used/listAllProd.jsp" />
	<%
		}
	%>
</body>
</html>
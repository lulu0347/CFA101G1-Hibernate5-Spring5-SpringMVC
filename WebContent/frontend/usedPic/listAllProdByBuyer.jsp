<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.used.model.*"%>

<%	UsedService usedSvc = new UsedService();
	String str = request.getParameter("buyerNo");
	Integer buyerNo = null;
	buyerNo = new Integer(str);
	List<UsedVO> list = usedSvc.getAllProdByBuyer(buyerNo);
	pageContext.setAttribute("list", list);%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAllprod.jsp</title>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>產品編號</th>
		<th>種類編號</th>
		<th>賣家編號</th>
		<th>交易紀錄編號</th>
		<th>產品名稱</th>
		<th>產品價格</th>
		<th>產品狀態</th>
		<th>上架時間</th>
		<th>下架時間</th>
		<th>收件人</th>
		<th>收件地址</th>
		<th>收件人電話</th>
		<th>商品描述</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="usedVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(usedVO.usedNo==param.usedNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${usedVO.usedNo}</td>
			<td>${usedVO.kindNo}</td>
			<td>${usedVO.sellerNo}</td>
			<td>${usedVO.transRecNo}</td>
			<td>${usedVO.usedName}</td>
			<td>${usedVO.usedPrice}</td>
			<td>${usedVO.usedState}</td>
			<td>${usedVO.usedLaunchedTime}</td>
			<td>${usedVO.soldTime}</td>
			<td>${usedVO.receiverName}</td>
			<td>${usedVO.receiverAddress}</td>
			<td>${usedVO.receiverPhone}</td>
			<td>${usedVO.usedProdDescription}</td>
						
			<td><c:forEach var="usedVO" items="${usedSvc.all}">
                    <c:if test="${usedVO.kindNo==productKindVO.kindNo}">
	                    ${productKindVO.kindNo}【${productKindVO.kindName}】
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/used/UsedServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="usedNo"     value="${usedVO.usedNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
</body>
</html>
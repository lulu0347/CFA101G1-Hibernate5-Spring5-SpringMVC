<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.auctAct.model.*"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>拍賣活動清單</title>
<%@ include file="../component/headerCDN.txt"%>
<%@ include file="../css/auctAct.css"%>
<style>
a {
	text-decoration: none
}

a:hover {
	text-decoration: none
}
</style>
</head>
<body>
	<%@ include file="../component/header.txt"%>
	<session>
	<div class="container">
		<div class="row">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red"></font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<div class="col-1"></div>
			<div class="col-10">
				<br>
				<table class="table caption-top">
					<caption>
						<h4>拍賣活動清單</h4>
					</caption>
					<thead>
						<tr>
							<th scope="col">活動編號</th>
							<th scope="col">活動名稱</th>
							<th scope="col">活動說明</th>
							<th scope="col">活動狀態</th>
							<th scope="col">活動起始日期</th>
							<th scope="col">活動結束時間</th>
							<th scope="col">修改活動</th>
							<th scope="col">刪除活動</th>

						</tr>
					</thead>

					<%
						AuctActService actSvc = new AuctActService();
						List<AuctActVO> actList = actSvc.getAll();
						pageContext.setAttribute("actList", actList);
					%>
					<tbody>
						<c:forEach var="auctActVO" items="${actList}">
							<tr>
								<th scope="row">${auctActVO.auctActNo}</th>
								<td>${auctActVO.auctActName}</td>
								<td>${auctActVO.auctActDesc}</td>
								<td>${auctActVO.auctActState}</td>
								<td><fmt:formatDate value="${auctActVO.auctStartTime}"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${auctActVO.auctEndTime}"
										pattern="yyyy-MM-dd" /></td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/auctAct/auctact.do"
										name="auctActform" enctype="multipart/form-data">
										<button type="submit" class="btn btn-outline-success btn-sm">修改
										</button>
								</td>
								<input type="hidden" name="auctActNo"
									value="${auctActVO.auctActNo}">
								<input type="hidden" name="action" value="get_One_update">
								</form>
								</td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/auctAct/auctact.do">
										<button type="submit" class="btn btn-outline-danger btn-sm">
											刪除</button>
								</td>
								<input type="hidden" name="auctActNo"
									value="${auctActVO.auctActNo}">
								<input type="hidden" name="action" value="delete">
								</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<a
			href="<%=request.getContextPath()%>/backend/auct/auctAct/select_page.jsp">
			<div class="d-grid gap-2 col-4 mx-auto  text-decoration:none;">
				<button class="btn btn-outline-info" type="button"
					style="font-size: 1.2rem">
					返回活動列表<i class="bi bi-arrow-right-circle" style="font-size: 1.3rem"></i>
				</button>
			</div>
		</a>
	</div>
	</session>
	<div class="row" style="height: 50px;"></div>
	<%@include file="../component/footer.txt"%>
</body>
</html>

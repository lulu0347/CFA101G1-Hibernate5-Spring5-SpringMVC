<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.json.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.bid.model.*"%>
<%@page import="com.bidpic.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="com.transRec.model.*"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("user");
	TransRecService transRecSvc = new TransRecService();
	Integer memNo = null;
	try {
		memNo = memberVO.getMemNo();
	} catch (Exception e) {
		memNo = -1;
	}
	Integer ECash = null;
	try {
		ECash = transRecSvc.getDeposit(memNo);
	} catch (Exception e) {
		e.getMessage();
		ECash = -1;
	}
	BidVO bidVO = (BidVO) request.getAttribute("bidVO");
	BidPicService bidPicSvc = new BidPicService();
	BidPicVO bidPicVO = (BidPicVO) bidPicSvc.getOneBidPicByBidProdNo(bidVO.getBidProdNo());
	Integer bidProdPicNo = null;
	try {
		bidProdPicNo = bidPicVO.getBidProdPicNo();
	} catch (Exception e) {
		e.getMessage();
	}
	Integer bidWinnerPrice = null;
	try {
		bidWinnerPrice = bidVO.getBidWinnerPrice();
	} catch (Exception e) {
		e.getMessage();
	}
	pageContext.setAttribute("bidProdPicNo", bidProdPicNo);
	pageContext.setAttribute("ECash", ECash);
	pageContext.setAttribute("bidWinnerPrice", bidWinnerPrice);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Page</title>
<!-- Bootstrap core CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
#bidProdPic {
	max-width: 100%;
}

#ECash {
	color: #5bc2c9;
	float: right;
}

.calculate {
	color: #5bc2c9;
	float: right;
	text-align: right;
}

#checkoutPrice {
	color: red;
}

.redText {
	color: red;
}

.greenText {
	color: green;
}
.info {
	font-size: 18px;
	font-weight: bold;
}
</style>
</head>
<body class="bg-light">

	<div class="container">
		<div class="py-5 text-center"><a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html">
			<img class="d-block mx-auto mb-4" src="../resources/icon/logo.png"
				alt="" width="72" height="72"></a>
			<h2>Checkout form</h2>
			<p class="lead"></p>
		</div>

		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">商品資訊</span> <span
						class="badge badge-secondary badge-pill"></span>
				</h4>
				<ul class="list-group mb-3">
					<li
						class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<h6 class="my-0"><%=bidVO.getBidProdName()%></h6>
							<small class="text-muted" id="bidProdDescription"><%=bidVO.getBidProdDescription()%></small>
						</div> <span class="text-muted" id="bidWinnerPrice">NTD <%=bidVO.getBidWinnerPrice()%></span>
					</li>
					<li
						class="list-group-item d-flex justify-content-between lh-condensed">
						<div>
							<small class="text-muted"><img
								src="../bidpic/readpic.do?bidProdPicNo=${bidProdPicNo}"
								id="bidProdPic"></small>
						</div>
					</li>
				</ul>

			</div>
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">結帳資訊</h4>
				<form class="needs-validation" novalidate method="post"
					action="<%=request.getContextPath()%>/bid/bidCheckout.do">

					<div class="col-md-12 mb-3">
						<label for="receiverName"><span class="info">收件人姓名</span></label> <input type="text"
							class="form-control" id="receiverName" name="receiverName"
							placeholder="" value="" required>
						<div class="invalid-feedback">請填入收件人姓名</div>
					</div>

					<div class="mb-3">
						<label for="receiverAddress"><span class="info">收件地址</span></label><br>縣市<br>
						<!--               <input type="text" class="form-control" id="receiverAddress" name="receiverAddress" placeholder="" required> -->
						<select id="county" onchange="changeCounty(event.target.value)"
							name="receiverAddressCounty">
							<option></option>
						</select><br>鄉鎮市區<br> <select id="city" name="receiverAddressCity">
							<option></option>
						</select> <input type="text" class="form-control" placeholder="請填入地址"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-lg" name="receiverAddressDetail" size="50">
						<input type="hidden" name="receiverAddress"
							value="">
						<div class="invalid-feedback">請輸入收件地址</div>
					</div>
					<div class="mb-3">
						<label for="receiverPhone"><span class="info">收件人電話</span></label> <input type="text"
							class="form-control" id="receiverPhone" name="receiverPhone"
							placeholder="" maxlength="10" required>
						<div class="invalid-feedback">請輸入收件電話</div>
					</div>
					<hr class="mb-4">
					<span class="calculate" id="ECash">NTD ${ECash}</span><br> <span
						class="calculate" id="checkoutPrice">- <%=bidVO.getBidWinnerPrice()%></span><br>
					<hr class="mb-4">
					<span class="calculate" id="calculateResult">NTD ${ECash - bidWinnerPrice}</span>
					<input type="hidden" name="action" value="CHECKOUT"> <input
						type="hidden" name="bidProdNo" value="<%=bidVO.getBidProdNo()%>">
					<button class="btn btn-primary btn-lg btn-block" type="submit">確認結帳</button>
				</form>
			</div>
		</div>

		<footer class="my-5 pt-5 text-muted text-center text-small">
			<p class="mb-1">&copy; Count Me In</p>
			<ul class="list-inline">
				<li class="list-inline-item"><a href="#">Privacy</a></li>
				<li class="list-inline-item"><a href="#">Terms</a></li>
				<li class="list-inline-item"><a href="#">Support</a></li>
			</ul>
		</footer>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/holder/2.9.8/holder.min.js"></script>
	<script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
        
        var calculateResult;
        calculateResult = parseInt(${ECash - bidWinnerPrice});
        if (calculateResult < 0) {
      	  document.getElementById('calculateResult').classList.add('redText');
      	  let appendSpan = document.createElement('span');
      	  document.getElementById('calculateResult').appendChild(appendSpan);
      	  appendSpan.innerHTML = "<br><br>餘額不足，請先進行<a href='../frontend/transRec/saveMoney.jsp' target=''>儲值</a>，以完成結帳。";
        } else {
      	  document.getElementById('calculateResult').classList.add('greenText');
        }
      })();
      

    </script>

	<script src="<%=request.getContextPath()%>/frontend/bid/js/database.js"></script>
	<script>
function init(){
    let options = "";
    const template =`<option></option>`;
    Object.keys(database).forEach(county=>{
        options+=("<option value='" + county + "'>" + county + "</option>");
    })
   
    generateCity(Object.keys(database)[0])
    document.getElementById("county").innerHTML = options;
    
}

function generateCity(county){
    let cityOptions = "";
//     console.log(database[county]);
    console.log(Object.keys(database[county]));
    Object.keys(database[county]).forEach(city=>{
        cityOptions+=("<option value='" + city + "'>" + city + "</option>");
    })
    document.getElementById("city").innerHTML = cityOptions;
}


function changeCounty(county){
    generateCity(county);
}
//執行函式
init();
</script>
</body>
</html>
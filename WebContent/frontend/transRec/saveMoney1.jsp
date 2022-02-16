<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="component/headComponent.txt"%>
<%@ include file="css/index.css"%>
</head>

<body>
	<header>
		<%@ include file="component/navComponent.txt"%>
		<%@ include file="component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container">
		<div class="row" style="height: 80px"></div>
		<img
			src="<%=request.getContextPath()%>/frontend/transRec/images/savemoney.png"
			style="height: 200px" class="rounded mx-auto d-block" alt="...">
	</div>
	<br>
	<h3 style="text-align: center">＄ 儲值</h3>
	<br>
	<br>
	<form name="depositform"
		action="<%=request.getContextPath()%>/saveMoney"
		method="POST" id="myForm">
		<div class="d-grid gap-2 col-3 mx-auto">
			<div class="input-group mb-3">
				<input type="number" class="form-control" placeholder="請輸入儲值金額"
					aria-label="請輸入儲值金額" aria-describedby="button-addon2"
					name="transAmount" id="saveMoney">
				<button type="button" class="btn btn-warning" onclick="whenSave()">確定儲值</button>
				<input type="hidden" name="action" value="saveMoney">

			</div>
		</div>
	</form>
	<c:if test="${not empty cartSum}">
		<c:choose>
			<c:when test="${not empty nowMoney}">
				<div class="d-grid gap-2 col-3 mx-auto  text-decoration:none;">
					<button class="btn btn-outline-primary" type="button" style="font-size: 1.2rem" disabled >
						$ 購物總額: ${cartSum}元
					</button>
				</div>
			<br>
				<div class="d-grid gap-2 col-3 mx-auto  text-decoration:none;">
					<button class="btn btn-outline-success" type="button" style="font-size: 1.2rem" disabled  id="nowMoney">
						$ 錢包餘額: ${nowMoney}元 
					</button>
				</div>
			</c:when>
		</c:choose>
	</c:if>
	<div class="modal fade show" style="display: none; top: 35%"
		id="saving">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">儲值服務</h5>
				</div>
				<div class="modal-body">儲值中...</div>

			</div>
		</div>
	</div>

	<div class="modal fade show" style="display: none; top: 35%"
		id="savingDone" aria-labelledby="exampleModalLabel"
		aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">儲值服務</h5>
				</div>
				<div class="modal-body">儲值完成</div>
				<form name="depositform" action="<%=request.getContextPath()%>/auctOrder/auctOrder.do" method="POST" id="myMoney">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal" onclick="closingPop()">Close</button>
					</div>
				<input type="hidden" name="action" value="checkAgain">
				</form>
			</div>
		</div>
	</div>

	</session>

	<%@ include file="component/footer.txt"%>
</body>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
// 	let myBtn = document.getElementById('myBtn');
// 	myBtn.addEventListener('click', function(e){
		
// 		if(isNaN(saveMoney)){
// 			document.getElementById(add).value;
// 			Swal.fire(getConfig ('error', '資料格式錯誤'));
// 		}else{
			
// 			Swal.fire(getConfig ('success', '錢包儲值'))
// 				.then(function(result){
// 					let myForm = document.getElementById("myForm");
// 					myForm.submit(); 
// 				});
// 		}
// 	});
// 	$.ajax{
		
		
// 		success:function(){
// 			let str = "";
// 			str = `$ 錢包餘額: ${nowMoney}元 `
			
// 			document.getElementById("nowMoney").innerText(str);
// 		}
// 	}

</script>
<script>
		const saving = document.getElementById("saving");
		const savingDone = document.getElementById("savingDone");
		function whenSave() {
		    const value = document.getElementById("saveMoney").value;
		    //一般儲值時的顯示   value=輸入的儲值金額
		    if (value === ""||value <=0) {
		    	Swal.fire({
	        		icon: 'warning',
	        		title: '請輸入金額',
	        		showConfirmButton: false,
	        		timer: 1500
	        		})
		        return;
		    //交易時不夠錢來存錢的提醒  
		    } else {
		    	open(saving);
			    setTimeout(()=>ajax(),1500);
		    }

		}
		
		function open(element) {
		    element.style.display = "block";
		}
		
		function close(element) {
		    element.style.display = "none";
		}
		
		function closingPop(){
		    close(savingDone);
		}
		
		
		 function ajax(){
	    	  	const cartSum = ${cartSum};
			 	const xhttp = new XMLHttpRequest();
	    	    // Define a callback function
	    	    //定義SERVLET 回傳的物件或回應
	    	    xhttp.onload = function () {
	    	        const returnStr = this.responseText;
	    	        //document.getElementById("transAmount").value = transAmount;
	    	        //三種不同情況
	    	        const moneyObj = JSON.parse(returnStr);
	    	        const status = moneyObj["status"];
	    	        const nowMoney = moneyObj["nowMoney"];
	    	        if (status === "success") {
	    	        	document.getElementById("nowMoney").innerHTML = moneyObj["nowMoney"];
	    	        	const div=document.getElementById("nowMoney")
	    	        	div.innerHTML = "$ 錢包餘額:" + nowMoney+"元";
	    	        	if(nowMoney<cartSum){
	    	        		document.getElementById("nowMoney").style.color= "red";
	    	        	}else{
	    	        		submit();
	    	        	}
	    	        	close(saving);
	    	        	open(savingDone);
	    	        	
	    	        }else if(status==="noMember"){
	    	        	close(saving);
	    	        	Swal.fire({
	    	        		icon: 'warning',
	    	        		title: '連線逾時,請重新登入',
	    	        		showConfirmButton: false,
	    	        		timer: 1500
	    	        		})
	    	        }else if(status==="fail"){
	    	        	Swal.fire({
	    	        		icon: 'warning',
	    	        		title: '請輸入金額',
	    	        		showConfirmButton: false,
	    	        		timer: 1500
	    	        		})
	    	        }
	    	        
	    	        
	    	    }
	    	    //畫面中所接收到的物件
	    	    const value = document.getElementById("saveMoney").value;
	    	    const data = {
	        	        "action": "ajaxSaveMoney1",
	        	        "transAmount":value
	        	};

	        	const sendingStr = generateDataString(data);

	        	// Send a request
	        	// xhttp.open("post", "http://localhost:8081/TestServer/TestServlet");
	        	xhttp.open("post", "<%=request.getContextPath()%>/ajaxSaveMoney");
	        	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        	xhttp.send(sendingStr);
	    }
		 
	    function generateDataString(data) {
	            console.log(JSON.stringify(data))

	            let str = "";
	            const keys = Object.keys(data);
	            keys.forEach(key => {
	                str += key + "=" + data[key] + "&";
	            })
	            return str;
	    }
	    function submit(){
	    	document.getElementById("myMoney").submit();
	    }

</script>
</html>
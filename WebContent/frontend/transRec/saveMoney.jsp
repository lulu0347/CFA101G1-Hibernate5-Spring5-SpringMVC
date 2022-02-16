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
	<div class="d-grid gap-2 col-3 mx-auto  text-decoration:none;">
		<button class="btn btn-outline-info" type="button" id="Submit"
			style="font-size: 1.2rem" name="Submit"
			onClick="javascript:history.go(-1)">
			<i class="bi bi-arrow-left-circle" style="font-size: 1.3rem"></i>
			返回前頁
		</button>
	</div>
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
		id="savingOver" aria-labelledby="exampleModalLabel"
		aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">儲值服務</h5>
				</div>
				<div class="modal-body">儲值完成</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal" onclick="closingPop()">Close</button>
				</div>
			</div>
		</div>
	</div>

	</session>

	<%@ include file="component/footer.txt"%>
</body>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
		const saving = document.getElementById("saving");
		const savingOver = document.getElementById("savingOver");
		function whenSave() {
		    const value = document.getElementById("saveMoney").value;
		    if (value === ""||value <=0) {
		    	Swal.fire({
	        		icon: 'warning',
	        		title: '請輸入金額',
	        		showConfirmButton: false,
	        		timer: 1500
	        		})
		        return;
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
		    close(savingOver);
		}
		
		 function ajax(){
	    	  	const xhttp = new XMLHttpRequest();
	    	   
	    	    xhttp.onload = function () {
	    	        const result = this.responseText;
	    	        if (result === "success") {
	    	        	close(saving);
	    	        	open(savingOver);
	    	        }else if(result==="noMember"){
	    	        	close(saving);
	    	        	Swal.fire({
	    	        		icon: 'warning',
	    	        		title: '連線逾時,請重新登入',
	    	        		showConfirmButton: false,
	    	        		timer: 1500
	    	        		})
	    	        }else if(result==="fail"){
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
	        	        "action": "ajaxSaveMoney",
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


</script>
</html>
function changeValue(auctActProdNo, value) {
	// 以加加減減的方式改變數值
	const num = getCartNum(auctActProdNo);
	const finalValue = num + value;
	if (finalValue >= 1 && finalValue<=30) {
		const element = document.getElementById(auctActProdNo + "Value");
		element.value = finalValue;
		updateCartNum(auctActProdNo, finalValue);
		setTotalPrice();
		setSinglueSum(auctActProdNo);
		// finalValue 為最後數字
		ajaxProdNum(auctActProdNo, finalValue);
	}
}

function setValue(auctActProdNo) {
	// 以輸入的方式改變值
	const element = document.getElementById(auctActProdNo + "Value");
	const oriValue = getCartNum(auctActProdNo);
	let num = element.value;

	if (!isNaN(num) && num >= 1 && num<=30 ) {
		element.value = num;
		// 瀏覽器畫面的值改變
		updateCartNum(auctActProdNo, num);
		setTotalPrice();
		setSinglueSum(auctActProdNo);
		// 後端的資料
		ajaxProdNum(auctActProdNo, num);
	} else {
		element.value = oriValue;
	}
}

function setSinglueSum(auctActProdNo) {
	const sum = cart[auctActProdNo].prodPurQty * cart[auctActProdNo].price;
	document.getElementById(auctActProdNo + "Sum").innerHTML = sum;
}

function updateCartNum(auctActProdNo, num) {
	cart[auctActProdNo].prodPurQty = num;
}

function getCartNum(auctActProdNo, num) {
	return cart[auctActProdNo].prodPurQty;
}
function setTotalPrice() {
	let totalPrice = 0;
	Object.values(cart).forEach((object) => {
		totalPrice += object["prodPurQty"] * object["price"];
	});
	const div = document.getElementById("sum");
	div.innerHTML = "總額: " + totalPrice;
}

function ajaxProdNum(auctActProdNo, prodPurQty) {
	// Create an XMLHttpRequest object
	const xhr = new XMLHttpRequest();
	// Define a callback function
	xhr.onload = function () { };
	const data = {
		action: "ajaxNum",
		auctActProdNo: auctActProdNo,
		prodPurQty: prodPurQty,
	};

	const sendingStr = generateDataString(data);

	xhr.open("post", projectName + "/auctOrder/auctOrder.do", true); // 先open
	// a
	// connection
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded"); // 再設定requestHeader為urlencoded
	// (POST
	// method)
	xhr.send(sendingStr);
}

function generateDataString(data) {
	console.log(data);
	let str = "";
	const keys = Object.keys(data);
	keys.forEach((key) => {
		str += key + "=" + data[key] + "&";
	});
	return str;
}

// 回傳AJAX結果跑出提示畫面
const askSaveMoney = document.getElementById("askSaveMoney"); // 問要不要儲值的頁面
// 確認送出的表單
let creOrd = document.getElementById("creOrd");


// 當點選確認結帳按鈕時判斷
function checkMoney() {
	
	// AJAX的物件
	const xhttp = new XMLHttpRequest();
	// 購物車物品總價
	const totalPrice = getTotalPrice();
	// 設定回傳值所使用的方法
	xhttp.onload = function () {
		// 收到SERVLET所回傳的錢包餘額的值==字串
		const value = this.responseText;
		const money = JSON.parse(value);
		const nowMoney=money["nowMoney"];
		// 如果購買商品錢大於錢包餘額時
		if (totalPrice > nowMoney) {
			open(askSaveMoney);
		} else {
			submit();
		}
	}
	//準備送進SERVLET要的資料
	const data = {
		"action": "ajaxCheckMoney"
	}

	const sendingStr = generateDataString(data);

	xhttp.open("post", projectName +"/auctOrder/auctOrder.do");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(sendingStr);
}





// 當點選取消按鈕時關掉視窗
function closeAsk() {
	close(askSaveMoney);
}


// 錢包物件改字串的方法
function generateDataString(data) {
	console.log(JSON.stringify(data));

	let str = "";
	const keys = Object.keys(data);
	keys.forEach((key) => {
		str += key + "=" + data[key] + "&";
	});
	return str;
}

// 取得購物車目前的錢
function getTotalPrice() {
	let totalPrice = 0;
	for(let key in cart){
		const auctProd = cart[key];
		
		totalPrice += auctProd["prodPurQty"] *auctProd["price"];
	}
//	Object.values(cart).forEach((object) => {
//		totalPrice += object["prodPurQty"] * object["price"];
//	});
	return totalPrice;
}

// 打開頁面的方法
function open(element) {
	element.style.display = "block";
}
// 關閉頁面的方法
function close(element) {
	element.style.display = "none";
}


function submit(){
	document.getElementById("creOrd").submit();
}
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const bidProdNo = urlParams.get('bidProdNo');
// console.log(bidProdNo);

	var bidProdObj;
	var bidProdName;
	var xhr = null;
	var nowUnix;
	var startTimeUnix;
	var endTimeUnix;
	var leftTimeUnix;
	var memNo;
	var memAccount;
	var memName;
	var ECash;
	var showCheckoutButtonTimer;
	var bidProdState;
	var currentWinnerNo;
	var currentPrice;
	var bidProdStartPrice;
	var bidPriceIncrement;
	var leastPrice;
	var bidProdPics;
	var bidProdPicHTML;
	var bidProdStartPriceHTML;
	var leastPriceHTML;
	
	function showBidProd(json) {
		bidProdObj = JSON.parse(json);
//		console.log(bidProdObj);
		if (JSONPath.JSONPath({path: '$.bidProduct.bidProdNo', json: bidProdObj}).length == 0) {
			alert("查無商品資料");
			window.location="../../frontend/bid/listAllBid.html";
			return;
		}
		document.title = JSONPath.JSONPath({path: '$.bidProduct.bidProdName', json: bidProdObj});
		nowUnix = new Date().getTime();
		bidProdStartPrice = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidProdStartPrice', json: bidProdObj}));
		bidPriceIncrement = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidPriceIncrement', json: bidProdObj}));
		bidProdState = JSONPath.JSONPath({path: '$.bidProduct.bidProdState', json: bidProdObj});
		bidProdPics = JSONPath.JSONPath({path: '$.bidProdPicNo[*]', json: bidProdObj});
		startTimeUnix = JSONPath.JSONPath({path: '$.bidProduct.bidProdStartTimeUnix', json: bidProdObj});
		endTimeUnix = JSONPath.JSONPath({path: '$.bidProduct.bidProdEndTimeUnix', json: bidProdObj});
		leftTimeUnix = endTimeUnix - nowUnix;
		memNo = parseInt(JSONPath.JSONPath({path: '$.member.memNo', json: bidProdObj}));
		memAccount = JSONPath.JSONPath({path: '$.member.memAccount', json: bidProdObj});
		memName = JSONPath.JSONPath({path: '$.member.memName', json: bidProdObj});
		ECash = JSONPath.JSONPath({path: '$.member.ECash', json: bidProdObj});
		
		if (memNo != undefined && memNo != -1) {
			document.getElementById("nav-left").innerHTML = `<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
			查看訂單
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="../../frontend/listAllOrderByMemNo.jsp">商城訂單頁面</a></li>
                <li><a class="dropdown-item" href="../../frontend/used/listAllProdByBuyer.jsp">二手商城買家訂單頁面</a></li>
                <li><a class="dropdown-item" href="../../frontend/used/listAllProdBySeller.jsp">二手商城賣家訂單頁面</a></li>
                <li><a class="dropdown-item" href="../../auctOrder/auctOrder.do?action=orderRec">拍賣商城訂單頁面</a></li>
                <li><a class="dropdown-item" href="../../frontend/bid/order_Index1.jsp">競標商城訂單頁面</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="../../frontend/listAllCollectionByMemNo.jsp">查看收藏商品</a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../../frontend/member/memberCenter.jsp">會員中心</a>
        </li>
		<li class="nav-item"><a class="nav-link"
			href="../../chat/insta.jsp">聊天室</a></li>`;
			document.getElementById("memberSection1").innerHTML = `<a href="../../frontend/member/memberCenter.jsp" id="memberName"><button class="btn btn-outline-success btn-sm me-md-4">${memName}</button></a>`;
			document.getElementById("memberSection2").innerHTML = `<form class="d-flex" action="../../member/LoginServlet">
                <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">登出</button>
                </form>`;
		}
		if (JSONPath.JSONPath({path: '$.bidRecord.length', json: bidProdObj}) != 0) {
			currentPrice = parseInt(JSONPath.JSONPath({path: '$.bidRecord[(@.length - 1)].bidPrice', json: bidProdObj}));
			currentWinnerNo = JSONPath.JSONPath({path: '$.bidRecord[(@.length - 1)].memNo', json: bidProdObj});
			leastPrice = (bidProdStartPrice > currentPrice) ? (bidProdStartPrice+bidPriceIncrement) : (currentPrice+bidPriceIncrement);
			if (memNo == currentWinnerNo) {
				bidCurrentPriceHTML = `
					目前價格: ${currentPrice} (您已為目前最高價者)
				`;
			} else {
				bidCurrentPriceHTML = `
					目前價格: ${currentPrice}
				`;
			}
			
		} else {
			bidCurrentPriceHTML = "";
			leastPrice = bidProdStartPrice + bidPriceIncrement;
		}
		console.log(leftTimeUnix);
		const bidTimer = setTimeout(bidEndAlert, leftTimeUnix);
		initializeClock('clockdiv', JSONPath.JSONPath({path: '$.bidProduct.bidProdEndTimeUnix', json: bidProdObj}));

		bidProdNameHTML = `
			<h2>${bidProdObj.bidProduct.bidProdName}</h2>
		`;
		bidProdDescriptionHTML = `
			Feature: ${bidProdObj.bidProduct.bidProdDescription || "此商品尚無敘述"}
		`;
		bidProdStartPriceHTML = `
			起標價: ${bidProdObj.bidProduct.bidProdStartPrice}
		`;
		bidProdStartTimeHTML = `
			開始時間: ${bidProdObj.bidProduct.bidProdStartTime.slice(0,-2)}
		`;
		bidProdEndTimeHTML = `
			結束時間: ${bidProdObj.bidProduct.bidProdEndTime.slice(0,-2)}
		`;
		bidProdPicHTML="";
		for (let i = 0; i < bidProdPics.length; i++) {
			bidProdPicHTML += `
				<img src="../../bidpic/readpic.do?bidProdPicNo=${bidProdPics[i]}" onclick="moveToMain(event)" onmouseover="style='cursor:pointer'">
			`;
		}
		mainBidProdPicHTML = `<img src="../../bidpic/readpic.do?bidProdPicNo=${bidProdPics[0]}" class="main-img"><div id="bidProdPic" class="thumbs"></div>
		`;
		
		bidPriceIncrementHTML = `
			最低增額: ${bidProdObj.bidProduct.bidPriceIncrement}
		`;
		leastPriceHTML = `
			請輸入最少 ${leastPrice} 元以完成出價
		`;

// 		document.getElementById("showPanel").innerHTML = html;
		document.getElementById("prodPageURI").value = window.location.href;
		document.getElementById("bidProdNo").value = JSONPath.JSONPath({path: '$.bidProduct.bidProdNo', json: bidProdObj});
		document.getElementById("bidProdName").innerHTML = bidProdNameHTML;
		document.getElementById("bidProdDescription").innerHTML = bidProdDescriptionHTML;
		document.getElementById("bidProdStartPrice").innerHTML = bidProdStartPriceHTML;
		document.getElementById("mainBidProdPic").innerHTML = mainBidProdPicHTML;
		document.getElementById("bidProdPic").innerHTML = bidProdPicHTML;
		document.getElementById("bidProdStartTime").innerHTML = bidProdStartTimeHTML;
		document.getElementById("bidProdEndTime").innerHTML = bidProdEndTimeHTML;
		document.getElementById("bidProdCurrentPrice").innerHTML = bidCurrentPriceHTML;
		document.getElementById("bidPriceIncrement").innerHTML = bidPriceIncrementHTML;
		document.getElementById("leastPrice").innerHTML = leastPriceHTML;
	}
	
	
// 	測試回傳訊息
	
	function submitCheck(action, bidProdNo, memNo, bidPrice) {
		let xhrA = new XMLHttpRequest();
		xhrA.onload = function() {
			if (xhrA.status == 200) {
				errorMessage = xhrA.responseText;
				if (errorMessage.length != 0) {
					const errorMsg = JSON.parse(errorMessage);
					console.log(errorMsg);
					if (errorMsg.includes('未登入會員')) {
						alert('請先登入會員');
// 						window.location.href = '../../front_end/member/login.html';
					}
					let showErrorHtml = "";
					for (let i = 0; i < errorMsg.length; i++) {
						showErrorHtml += `${errorMsg[i]}<br>`
					}
					
					document.getElementById("showError").innerHTML = showErrorHtml;
				} else {
					location.reload();
				}

			} else {
				alert(xhrA.status);
			}
		}
		let url = '../../bidrecord/bidrecord.do';
		var data_info = {
				"action": action.value,
				"bidProdNo": bidProdNo.value,
				"memNo": memNo.value,
				"bidPrice": bidPrice.value,
		};
		xhrA.open("POST", url, true);
		xhrA.setRequestHeader("Content-Type", "application/json");
		xhrA.send(JSON.stringify(data_info));
	}

	
	function bidEndAlert() {
		document.getElementById("bidButton").disabled = true;
		document.getElementById("bidButton").innerText = "已截標";
		document.getElementById("bidButton").style = "background-color: gray";
//		bidWinnerNo = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidWinnerNo', json: bidProdJSON}));
//		preventEnter();

		showCheckoutButtonTimer = setInterval(showCheckoutButton, 1000);
	}
	document.getElementById('bidPrice').addEventListener('keydown', function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
		};
		
	})
	
	function showCheckoutButton() {
//		setTimeout(() => {  console.log("World!"); }, 1000);
		bidWinnerNo = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidWinnerNo', json: bidProdJSON}));
		
		if (memNo == bidWinnerNo && memNo != undefined && bidWinnerNo != undefined && bidProdState == 0) {

			document.getElementById("showCheckout").innerHTML = `
				<div id="checkoutZone">恭喜 ${memName} 得標 ${bidProdObj.bidProduct.bidProdName}，請點選結帳鈕已完成結帳。
				<FORM action="../../bid/bidCheckout.do" method="POST">
				<input type="hidden" name="memNo" value="${memNo}">
				<input type="hidden" name="bidProdNo" value="${bidProdNo}">
				<input type="hidden" name="action" value="beforeCHECKOUT">
				<button type="submit" id="checkoutButton"><span>結帳</span></button>
				</FORM></div>
			`;
			clearInterval(showCheckoutButtonTimer);
		}
	}
	
//	var thumbs = document.querySelectorAll('.thumbs img');
//	console.log(thumbs.length);
//	for (let i = 0; i < 2; i++) {
//		var url = thumbs[i].getAttribute("src");
//		console.log(thumbs[i]);
//	}
	
	function moveToMain(e) {
		var url = e.target.src;
		document.getElementById('mainBidProdPic').getElementsByTagName('img')[0].src = url;
	}
	
	function initializeClock(id, endtime) {

		  const clock = document.getElementById(id);
		  const daysSpan = clock.querySelector('.days');
		  const hoursSpan = clock.querySelector('.hours');
		  const minutesSpan = clock.querySelector('.minutes');
		  const secondsSpan = clock.querySelector('.seconds');

		  function updateClock() {
		    const t = getTimeRemaining(endtime);

		    daysSpan.innerHTML = t.days + " d ";
		    hoursSpan.innerHTML = t.hours + " h ";
		    minutesSpan.innerHTML = t.minutes + " m ";
		    secondsSpan.innerHTML = t.seconds + " s ";

		    if (t.total <= 0) {
		    	try {
		    		clearInterval(timeinterval);
		    	} catch (e) {

		    	}
		    }
		  }

		  updateClock();
		  const timeinterval = setInterval(updateClock, 1000);
		}
	
	function getTimeRemaining(endtime) {
		var endTime = parseInt(endtime);
		const total = endTime - new Date();
		var seconds;
		var minutes;
		var hours;
		var days;
		if(total <= 0) {
			seconds = 0;
			minutes = 0;
			hours = 0;
			days = 0;
		} else {
			seconds = Math.floor( (total/1000) % 60 );
			minutes = Math.floor( (total/1000/60) % 60 );
			hours = Math.floor( (total/(1000*60*60)) % 24 );
			days = Math.floor( total/(1000*60*60*24) );
		}

		return {
			total, days, hours, minutes, seconds
		};
	}
	
setInterval(loadingWithoutPics, 1000);

function loadingWithoutPics() {
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.status == 200) {
			showBidProdWithoutPics(xhr.responseText);
		} else {
			alert('查無商品資料');
			window.location="../../frontend/bid/listAllBid.html";
		}
	}
	let url = `listOneBid.jsp?bidProdNo=${bidProdNo}`;
	xhr.open("Get", url, true);
	xhr.send(null);
}

function showBidProdWithoutPics(json) {
	bidProdJSON = JSON.parse(json);
	if (JSONPath.JSONPath({path: '$.bidProduct.bidProdNo', json: bidProdJSON}).length == 0) {
		alert("查無商品資料");
		window.location="../../frontend/bid/listAllBid.html";
		return;
	}
	document.title = JSONPath.JSONPath({path: '$.bidProduct.bidProdName', json: bidProdJSON});
	nowUnix = new Date().getTime();
	bidProdStartPrice = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidProdStartPrice', json: bidProdJSON}));
	bidPriceIncrement = parseInt(JSONPath.JSONPath({path: '$.bidProduct.bidPriceIncrement', json: bidProdJSON}));
	bidProdState = JSONPath.JSONPath({path: '$.bidProduct.bidProdState', json: bidProdJSON});
	bidProdPics = JSONPath.JSONPath({path: '$.bidProdPicNo[*]', json: bidProdJSON});
	startTimeUnix = JSONPath.JSONPath({path: '$.bidProduct.bidProdStartTimeUnix', json: bidProdJSON});
	endTimeUnix = JSONPath.JSONPath({path: '$.bidProduct.bidProdEndTimeUnix', json: bidProdJSON});
	leftTimeUnix = endTimeUnix - nowUnix;
	memNo = parseInt(JSONPath.JSONPath({path: '$.member.memNo', json: bidProdJSON}));
	memAccount = JSONPath.JSONPath({path: '$.member.memAccount', json: bidProdJSON});
	memName = JSONPath.JSONPath({path: '$.member.memName', json: bidProdJSON});
	ECash = JSONPath.JSONPath({path: '$.member.ECash', json: bidProdJSON});
	
	if (JSONPath.JSONPath({path: '$.bidRecord.length', json: bidProdJSON}) != 0) {
		currentPrice = parseInt(JSONPath.JSONPath({path: '$.bidRecord[(@.length - 1)].bidPrice', json: bidProdJSON}));
		currentWinnerNo = JSONPath.JSONPath({path: '$.bidRecord[(@.length - 1)].memNo', json: bidProdJSON});
		leastPrice = (bidProdStartPrice > currentPrice) ? (bidProdStartPrice+bidPriceIncrement) : (currentPrice+bidPriceIncrement);
		console.log(currentPrice);
		if (memNo == currentWinnerNo) {
			bidCurrentPriceHTML = `
				目前價格: ${currentPrice} (您已為目前最高價者)
			`;
		} else {
			bidCurrentPriceHTML = `
				目前價格: ${currentPrice} (請輸入最少 ${leastPrice} 元以完成出價)
			`;
		}
		
	} else {
		bidCurrentPriceHTML = "";
		leastPrice = bidProdStartPrice + bidPriceIncrement;
	}
	leastPriceHTML = `
		請輸入最少 ${leastPrice} 元以完成出價
	`;
	
	document.getElementById("bidProdStartPrice").innerHTML = bidProdStartPriceHTML;
	document.getElementById("bidProdCurrentPrice").innerHTML = bidCurrentPriceHTML;
	document.getElementById("leastPrice").innerHTML = leastPriceHTML;
//	console.log(leastPrice);
}

window.onload = function getBidProd() {
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.status == 200) {
			showBidProd(xhr.responseText);
		} else {
			alert('查無商品資料');
			window.location="../../frontend/bid/listAllBid.html";
		}
	}
		let url = `listOneBid.jsp?bidProdNo=${bidProdNo}`;
		xhr.open("Get", url, true);
		xhr.send(null);
	}
//寫在jsp上的方法
function addProductToCart(auctActProdNo){
	
	const xhttp = new XMLHttpRequest();
    // Define a callback function
    xhttp.onload = function () {
        const result = this.responseText;
        if(result==="true"){
//        	alert("商品已新增");
        	Swal.fire({
        		icon: 'success',
        		title: '已加入購物車',
        		showConfirmButton: false,
        		timer: 1500
        		})
        	
        }else{
        	console.log(result);
        }
    }
  
    
    //新增的商品物件從servlet發出
    const data = {
        "action": "ajaxAddProd",
        "auctActProdNo": auctActProdNo
    };
   
    const sendingStr = generateDataString(data);

    // Send a request
    // xhttp.open("post", "http://localhost:8081/TestServer/TestServlet");
    //應用程式和應用程式之間的橋樑 API 文件及規格書
    xhttp.open("post", projectName+"/auctOrder/auctOrder.do");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(sendingStr);
	
}

function generateDataString(data) {
    let str = "";
    const keys = Object.keys(data);
    keys.forEach(key => {
        str += key + "=" + data[key] + "&";
    })
    return str;
}



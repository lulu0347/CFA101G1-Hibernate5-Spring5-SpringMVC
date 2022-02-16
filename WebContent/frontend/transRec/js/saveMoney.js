function  addManey(){
    const xhttp = new XMLHttpRequest();
    const result = document.getElementById("money").value;
    // Define a callback function
    xhttp.onload = function () {
        const result = this.responseText;
        //document.getElementById("result").value = result;
        if (addMoney === "false") {
            Swal.fire({
                icon: 'error',
                title: '儲值失敗',
                showConfirmButton: false,
                timer:1500
            })
        } else { 
            console.log(addMoney);
            const obj=JSON.parse(addMoney);
            console.log(obj.transAmount);
        }
    }


const data = {
    "action": "ajaxSaveMoney",
    "transAmount": addManey
};
data["action"] = "ajaxSaveMoney";
data["transAmount"] = addManey;
const sendingStr = generateDataString(data);

// // Send a request
// xhttp.open("post", "http://localhost:8081/TestServer/TestServlet");
xhttp.open("post", "<%=request.getContextPath()%>/transRec/transrec.do");
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhttp.send(sendingStr);

}







let myBtn = document.getElementById('myBtn');
myBtn.addEventListener('click', function(e){
	e.preventDefault(); // stop 阻擋瀏覽器預設行為，FORM A HREF 導轉元素
	Swal.fire({
	icon: 'success',
	title: '儲值成功',
	showConfirmButton: false,timer:1500
	}).then(function(result){
		let myForm = document.getElementById("myForm");
		myForm.submit(); 
	});
});
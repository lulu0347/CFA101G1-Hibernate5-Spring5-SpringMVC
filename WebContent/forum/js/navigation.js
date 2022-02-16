
//確認是否為登入狀態
$(document).ready(function(){
    $.ajax({
        type:"POST",
        dataType:"json",   
        url:"/CFA101G1/member/LoginServlet",
        data:{
            "action":"check",
        },
        success: function (response) {
            
            if(response != -1){
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    async:false,
                    url:"/CFA101G1/notify/notify.do",
                    data:{
                        "action":"getZeroStatus",
                        "receiver": response.memno
                    },
                    success: function (data) {
                        let len = data.length;
                        let str = '';
                        str +=	`<li><a href="publish.html" id="publish">發文</a></li>
                        <li><a href="profile.html?memno=${response.memno}" id="replies">通知<span class="badge">${len}</span></a></li>
                        <li class="dropdown"><a
                        href="#" class="dropdown-toggle" data-toggle="dropdown"
                        role="button" aria-haspopup="true" aria-expanded="false">
                        <span>${response.memname}</span> <span class="caret"></span></a>
                        <ul class="dropdown-menu" style="position:absolute;z-index:1000;">
                        <li><a href="/CFA101G1/forum/profile.html?memno=${response.memno}">訊息中心</a></li>
                        <li><a href="" id="logout">退出登入</a></li>
                        </ul></li>`
                        document.getElementById('loginornot').innerHTML = str;
                    
                    }
                });
                
                            
                

                document.getElementById("logout").addEventListener('click',function(){
                    $.ajax({
                        type:"POST",
                        dataType:"json",
                        url:"/CFA101G1/member/LoginServlet",
                        data:{
                            "action":"logout"
                        },
                        success: function (response) {
                            console.log("登出成功");
                        }
                
                    })
                });
            }else{
                let str = '';
                str +=  `<li><a
                    href="/CFA101G1/frontend/member/register.jsp" id="signin">註冊</a></li>
                <li><a
                    href="/CFA101G1/frontend/member/login.html" id="login">登入</a></li>`
                document.getElementById('loginornot').innerHTML = str;
            }
        }
    })
});




function datetimeFormat(longTypeDate){  
    var dateTypeDate = "";  
    var date = new Date();  
    date.setTime(longTypeDate);  
    dateTypeDate += date.getFullYear(); //年  
    dateTypeDate += "-" + getMonth(date); //月  
    dateTypeDate += "-" + getDay(date); //日  
    dateTypeDate += " " + getHours(date); //时  
    dateTypeDate += ":" + getMinutes(date);  //分 
    dateTypeDate += ":" + getSeconds(date);  //分 
    return dateTypeDate; 
};  

//返回 01-12 的月份值  
    function getMonth(date){  
    var month = "";  
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
    if(month<10){  
    month = "0" + month;  
    }  
    return month;  
}  
//返回01-30的日期  
    function getDay(date){  
    var day = "";  
    day = date.getDate();  
    if(day<10){  
    day = "0" + day;  
    }  
    return day;  
} 
//小时 
    function getHours(date){ 
    var hours = ""; 
    hours = date.getHours(); 
    if(hours<10){  
    hours = "0" + hours;  
    }  
    return hours;  
} 
//分 
    function getMinutes(date){ 
    var minute = ""; 
    minute = date.getMinutes(); 
    if(minute<10){  
    minute = "0" + minute;  
    }  
    return minute;  
} 
//秒 
    function getSeconds(date){ 
    var second = ""; 
    second = date.getSeconds(); 
    if(second<10){  
    second = "0" + second;  
    }  
    return second;  
}


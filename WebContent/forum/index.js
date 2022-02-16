const jsonUrl = 'http://localhost:8081/CFA101G1/forumpost/forumpost.do';
const content = document.getElementById('question');
const pageid = document.getElementById('pageid');
let jsonData = {};

fetch(jsonUrl, {method: "post",action:"getAll"})
  .then((response) => {
  return response.json();
}).then((data) => {
  jsonData = data;
  pagination(jsonData, 1);
})

function pagination(jsonData, nowPage) {
  console.log(nowPage);
  // 取得全部資料長度
  const dataTotal = jsonData.length;
  
  // 設定要顯示在畫面上的資料數量
  // 預設每一頁只顯示 5 筆資料。
  const perpage = 3;
  
  // page 按鈕總數量公式 總資料數量 / 每一頁要顯示的資料
  // 這邊要注意，因為有可能會出現餘數，所以要無條件進位。
  const pageTotal = Math.ceil(dataTotal / perpage);
  
  // 當前頁數，對應現在當前頁數
  let currentPage = nowPage;
  
  // 因為要避免當前頁數筆總頁數還要多，假設今天總頁數是 3 筆，就不可能是 4 或 5
  // 所以要在寫入一個判斷避免這種狀況。
  // 當"當前頁數"比"總頁數"大的時候，"當前頁數"就等於"總頁數"
  // 注意這一行在最前面並不是透過 nowPage 傳入賦予與 currentPage，所以才會寫這一個判斷式，但主要是預防一些無法預期的狀況，例如：nowPage 突然發神經？！
  if (currentPage > pageTotal) {
    currentPage = pageTotal;
  }
  
  // 由前面可知 最小數字為 6 ，所以用答案來回推公式。
  const minData = (currentPage * perpage) - perpage + 1 ;
  const maxData = (currentPage * perpage) ;
  
  // 先建立新陣列
  const data = [];
  // 這邊將會使用 ES6 forEach 做資料處理
  // 首先必須使用索引來判斷資料位子，所以要使用 index
  jsonData.forEach((item, index) => {
    // 獲取陣列索引，但因為索引是從 0 開始所以要 +1。
    const num = index + 1;
    // 這邊判斷式會稍微複雜一點
    // 當 num 比 minData 大且又小於 maxData 就push進去新陣列。
    if ( num >= minData && num <= maxData) {
      data.push(item);
    }
  })
  // 用物件方式來傳遞資料
  const page = {
    pageTotal,
    currentPage,
    hasPage: currentPage > 1,
    hasNext: currentPage < pageTotal,
  }
  displayData(data);
  pageBtn(page);
}

function displayData(data) {
  let str = '';
  data.forEach((item) => {
    str += `<div class="media-left">
                <a href="#"> <img class="media-object img-rounded"
                    src="logo1.png">
                </a>
                </div>
                
                <div class="media-body">
                <h4 class="media-heading">
                    <a href="">${element.title}</a>
                </h4>
                <span>${element.description}</span><br> 
                <span class="text-desc">
                    <span>${element.commentCount}</span>個回覆
                    <span>${element.viewCount}</span>次瀏覽，
                    <span>${datetimeFormat(element.gmtCreate)}</span>
                </span>
            </div><br>`
  });
  content.innerHTML = str;
}

function pageBtn (page){
  let str = '';
  const total = page.pageTotal;
  
  if(page.hasPage) {
    str += `<li class="page-item"><a class="page-link" href="#" data-page="${Number(page.currentPage) - 1}">Previous</a></li>`;
  } else {
    str += `<li class="page-item disabled"><span class="page-link">Previous</span></li>`;
  }
  

  for(let i = 1; i <= total; i++){
    if(Number(page.currentPage) === i) {
      str +=`<li class="page-item active"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
    } else {
      str +=`<li class="page-item"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
    }
  };

  if(page.hasNext) {
    str += `<li class="page-item"><a class="page-link" href="#" data-page="${Number(page.currentPage) + 1}">Next</a></li>`;
  } else {
    str += `<li class="page-item disabled"><span class="page-link">Next</span></li>`;
  }

  pageid.innerHTML = str;
}

function switchPage(e){
  e.preventDefault();
  if(e.target.nodeName !== 'A') return;
  const page = e.target.dataset.page;
  pagination(jsonData, page);
}

pageid.addEventListener('click', switchPage);



$(document).ready(function(){
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"http://localhost:8081/CFA101G1/forumpost/forumpost.do",
        data:{
            "action":"getAll"
        },
        success: function (response) {
            $.each(response, function(index, element){
                $('.question').append(
                    `<div class="media-left">
                    <a href="#"> <img class="media-object img-rounded"
                        src="logo1.png">
                    </a>
                    </div>
                    
                    <div class="media-body">
                    <h4 class="media-heading">
                        <a href="">${element.title}</a>
                    </h4>
                    <span>${element.description}</span><br> 
                    <span class="text-desc">
                        <span>${element.commentCount}</span>個回覆
                        <span>${element.viewCount}</span>次瀏覽，
                        <span>${datetimeFormat(element.gmtCreate)}</span>
                    </span>
                </div><br>`
                );
            })
        }
    });
});





`<!--回覆-->
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h4 class="comment">
						<span th:text="${question.commentCount}"></span>個回覆
					</h4>
					<hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"  th:each="comment : ${comments}">
						<div class="media">
							<div class="media-left">
								<a href="#"> <img class="media-object img-rounded"
									th:src="${comment.userAccount.avatarUrl}">
								</a>
							</div>
							<div class="media-body" th:id="${'comment-body-'+comment.id}">
								<h5 class="media-heading">
									<span th:text="${comment.userAccount.name}"></span>
								</h5>
								<div th:text="${comment.content}"></div>
								<div class="menu">
									<span class="glyphicon glyphicon-thumbs-up icon"></span>
									<span th:data-id="${comment.id}"
	                                      onclick="collapseComments(this)" class="comment-icon">
	                                    <span class="glyphicon glyphicon-comment"></span>
	                                    <span th:text="${comment.commentCount}"></span>
	                                </span>
									<span class="pull-right" th:text="${format(comment.gmtCreate,'yyyy-MM-dd')}"></span>
								</div>
								<!-- 回覆的回覆 -->
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments"
                                 th:id="${'comment-'+comment.id}">
	                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                                    <input type="text" class="form-control" placeholder="回覆……"
	                                           th:id="${'input-'+comment.id}">
	                                    <button type="button" class="btn btn-success pull-right" onclick="comment(this)"
	                                            th:data-id="${comment.id}">回覆
	                                    </button>
	                                </div>
	                            </div>
							</div>
						</div>
					</div>`
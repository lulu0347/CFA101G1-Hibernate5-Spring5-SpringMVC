<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上傳圖片.jsp</title>
</head>
<body>
<div>

</div>
<fieldset>
	<legend>Test Upload</legend>
		<form id="upload" action="<%=request.getContextPath()%>/bidpic/bidpic.do" method="POST" enctype="multipart/form-data" name="form1">
			<jsp:useBean id="bidSvc" scope="page" class="com.bid.model.BidService" />
			競標商品:
			<select size="1" name="bidProdNo">
         		<c:forEach var="bidVO" items="${bidSvc.all}" > 
          			<option value="${bidVO.bidProdNo}" ${(bidPicVO.bidProdNo==bidVO.bidProdNo)? 'selected':'' }>${bidVO.bidProdNo} - ${bidVO.bidProdName}
        		 </c:forEach>   
       		</select><p>
	        <input type="file" name="upfile1" onclick="previewImage()" multiple id="upfile">
	        <input type="hidden" name="action" value="insert_multi">
	        <input type="submit" value="上傳圖片" class="button">
		</form>
		
		 <div id="holder"></div>
</fieldset>
<script type="text/javascript">
var filereader_support = typeof FileReader != 'undefined';

if (!filereader_support) {
	alert("No FileReader support");
}

acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
};

function previewImage() {
	var upfile = document.getElementById("upfile");
	upfile.addEventListener("change", function(event) {
		var files = event.target.files || event.dataTransfer.files;
		for (var i = 0; i < files.length; i++) {
			previewfile(files[i])
		}
	}, false);
}


function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = new Image();
			image.src = event.target.result;
			image.width = 100;
			holder.appendChild(image);
		};
		reader.readAsDataURL(file);
	} else {
		holder.innerHTML += "<p>" + "filename: <strong>" + file.name
				+ "</strong><br>" + "ContentTyp: <strong>" + file.type
				+ "</strong><br>" + "size: <strong>" + file.size
				+ "</strong> bytes</p>";
	}
}
</script>
</body>
</html>
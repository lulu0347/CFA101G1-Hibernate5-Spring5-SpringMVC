<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="../component/headComponent.txt"%>
<%@ include file="../css/index.css"%>
</head>

<body>
	<header>
		<%@ include file="../component/navComponent.txt"%>
		<%@ include file="../component/searchComponent.txt"%>
	</header>
	<session>
	<div class="container">
		<div class="prod">
			<div class="row">
				<div class="col-3">
					<div class="d-flex flex-column flex-shrink-0 p-3 bg-light"
						style="width: 220px;">
						<a href=""
							class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
							<i class="bi bi-house fs-3 mb-3"></i> &nbsp;
							<h4>&nbsp;Category</h4>
						</a>
						<hr>
						<ul class="nav nav-pills flex-column mb-auto ">
							<li class="nav-item"><a href="#" class="nav-link link-dark">
									<i class="bi bi-phone fs-2 mb-3"> &nbsp;</i>Phone</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-laptop fs-2 mb-3"> &nbsp;</i>Computer</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-camera fs-2 mb-3"> &nbsp;</i>Camera</i><br>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-smartwatch fs-2 mb-3"> &nbsp;</i>Watch</i>
							</a></li>
							<li><a href="#" class="nav-link link-dark"> <i
									class="bi bi-headset fs-2 mb-3"> &nbsp;</i>Others</i>
							</a></li>
						</ul>
						<hr>
						<div class="dropdown">
							<a href="#" class="d-flex align-items-center link-dark"> </a>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-9">XXXXX填入內容</div>
			</div>
		</div>
	</div>
	</div>
	</session>
	
	<%@ include file="../component/footer.txt"%>
</body>

</html>
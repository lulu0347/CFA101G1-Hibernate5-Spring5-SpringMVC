<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- site metas -->
<title>CountMEIn首頁</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">

<!-- bootstrap css -->
<link rel="stylesheet" href="style1.css">
<link rel="stylesheet" href="css/bootstrap1.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" href="css/style.css">
<!-- Tweaks for older IEs -->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">
	
	
	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/template.css" /> --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	
	
<style>
/* .box{ */
/*      margin: 1px auto; */
/*      width: 280px; */
/*      height: 40px; */
/*      margin-right:-2%; */
/* } */

.MallTop{
background-color:#003D79;
}

.foot{
    background-color: rgb(37, 68, 96);
}
</style>	
</head>


<!-- body -->
<body class="app_version" data-spy="scroll" data-target="#navbarApp"
	data-offset="98">
<body class="main-layout ">
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="images/loading.gif" alt="#" />
		</div>
	</div>
<!-- end loader -->
	
	
	
<!-- header -->
<header>
       <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/Frontpage.jsp">CountMEIn</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
						 <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">會員中心</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/chat/insta.jsp">聊天室</a>
                        </li>
                    </ul>
	                    <form class="d-flex">
	                    	<a href="<%=request.getContextPath()%>/deposit">	
	                     	 <button class="btn btn-outline-success btn-sm me-md-3" type="button">我的錢包</button>
	                     	 <input type="hidden" name="action" value="deposit">
	                     	</a> 
	                     	<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-outline-success btn-sm me-md-3" type="submit">註冊</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-outline-success btn-sm" type="submit">登入會員</button>
	                        </a>
	                    </form>
	                     <form class="d-flex" action="<%=request.getContextPath()%>/member/LoginServlet">
                        	<button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="PDlogout" name="action">登出</button>
                  		 </form>
                </div>
            </div>
        </nav>
        <div class="MallTop">
            <div style="margin-top:56px" >
                <div class="container">
                    <div class="row">
                        <div class="col" style="height:50px;">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <a href="http://34.81.72.90/CFA101G1/frontend/Frontpage.jsp">
                                <img src="images/logo.png" alt="logo" style="height:100px;" margin-bottom=10px;>
                            </a>
                        </div>
                        <div class="col-6">
                            <div class="row ">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search Product"
                                        aria-label="Search Product" aria-describedby="button-addon2">
                                    <button class="btn btn-outline-warning" type="button" id="button-addon2">
                                        <span class="material-icons-outlined">search</span>
                                    </button>
                                </div>
                            </div>
                            <div class="row align-items-end" style="height:80px;">
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/EShop.jsp">	
                                    <button type="button" class="btn btn-outline-warning btn-md">主要商城</button>
                                  </a>  
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/frontend/used/listAllProd.jsp">
                                    <button type="button" class="btn btn-outline-warning btn-md">二手商城</button>
                                 </a>
                                </div>
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/auctAct/auctActIndex.jsp">	
                                   <button type="button" class="btn btn-outline-warning btn-md">拍賣商城</button>
                                   </a>
                                </div>
                                 <div class="col">
                                 	<a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html">
                                    <button type="button" class="btn btn-outline-warning btn-md">競標商城</button>
                                	</a>
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/forum/index.html">	
                                    <button type="button" class="btn btn-outline-warning btn-md">討論交流</button>
                                  </a>  
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="row" style="height:20px"></div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    
</header>
<!-- end header -->
	
	<section class="slider_section">
        <div id="myCarousel" class="carousel slide banner-main" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="first-slide" src="images/1.jpg" style=width:100%;height:550px; alt="First slide">
                    <div class="container">
                        <div class="carousel-caption relative">
                            <span>超越顛峰</span>
                            <h1>LPHONE X</h1>
                            <p>COMING SOON
                                <br></p>
                            <a class="buynow" href="#">Buy Now</a>
                            <!-- <ul class="social_icon">
                                <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                            </ul> -->
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="second-slide" src="images/2.jpg" style=width:100%;height:550px; alt="Second slide">
                    <div class="container">
                        <div class="carousel-caption relative">
                            <span>看見新視界</span>
                            <h1>GALAXY S7</h1>
                            <p>
                                <br></p>
                            <a class="buynow" href="#">Buy Now</a>
                            <!-- <ul class="social_icon">
                                <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                            </ul> -->
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="third-slide" src="images/33.png" style=width:100%;height:550px; alt="Third slide">
                    <div class="container">
                        <div class="carousel-caption relative">
                            <span>繽紛你的生活</span>
                            <h1>CANDY PHONE</h1>
                            <p>
                                <br></p>
                            <a class="buynow" href="#">Buy Now</a>
                            <!-- <ul class="social_icon">
                                <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                            </ul> -->
                        </div>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                <i class='fa fa-angle-left'></i>
            </a>
            <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                <i class='fa fa-angle-right'></i>
            </a>
        </div>
    </section>

	<!-- about -->
	<div class="about">
		<div class="container">
			<div class="row">
				<div class="col-xl-5 col-lg-5 col-md-5 co-sm-l2">
					<div class="about_img">
						<figure>
							<img src="images/55.jpg" style="width: 700px; height: 450px"
								alt="img" />
						</figure>
					</div>
				</div>
				<div class="col-xl-7 col-lg-7 col-md-7 co-sm-l2">
					<div class="about_box">
						<h3>About Us</h3>
						<span>CountMEIn</span>
						<p>
							CountMEIn為⾃營品牌， <br> 為了建⽴多樣且循環的產品系統，<br>
							提供⼀個購買、交流商品的線上平台。<br> 讓使⽤者能在此平台上選定⾃⾝喜愛的商品，<br>
							加深CountMeIn社群的深度及廣度
						</p>

					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- end about -->

	<!-- brand -->
	<div class="brand">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>最新產品</h2>
					</div>
				</div>
			</div>
		</div>
		<div class="brand-bg">
			<div class="container">
				<div class="row">
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 margin">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/1.png"
								alt="img" /></a>

							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 margin">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/2.png"
								alt="img" /></a>
							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 margin">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/3.png"
								alt="img" /></a>
							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/4.png"
								alt="img" /></a>
							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 mrgn">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/5.png"
								alt="img" /></a>
							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 mrgn">
						<div class="brand_box">
							<a href="https://www.google.com/"><img src="images/6.png"
								alt="img" /></a>
							<!-- <h3>$<strong class="red">100</strong></h3> -->
							<span>Mobile Phone</span> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i> <i><img src="images/star.png" /></i> <i><img
								src="images/star.png" /></i>
						</div>
					</div>
					<div class="col-md-12">
						<a class="read-more">See More</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="screenshots" class="section wb">
		<div class="container">
			<div class="section-title text-center">
				<h3>經典熱銷</h3>
			</div>
			<!-- end title -->
			<div class="owl-screenshots swiper-container">

				<div class="mobilescreen-image"></div>

				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<div class="service-widget">
							<div class="post-media entry wow fadeIn">

								<img src="uploads/55.jpg" alt="" class="img-fluid img-rounded">
								<div class="magnifier"></div>
							</div>
						</div>
						<!-- end service -->
					</div>
					<div class="swiper-slide">
						<div class="service-widget">
							<div class="post-media entry wow fadeIn">

								<img src="uploads/66.jpg" alt="" class="img-fluid img-rounded">
								<div class="magnifier"></div>
							</div>
						</div>
						<!-- end service -->
					</div>
					<div class="swiper-slide">
						<div class="service-widget">
							<div class="post-media entry wow fadeIn">

								<img src="uploads/77.jpg" alt="" class="img-fluid img-rounded">
								<div class="magnifier"></div>
							</div>
						</div>
						<!-- end service -->
					</div>
					<div class="swiper-slide">
						<div class="service-widget">
							<div class="post-media entry wow fadeIn">

								<img src="uploads/88.jpg" alt="" class="img-fluid img-rounded">
								<div class="magnifier"></div>
							</div>
						</div>
						<!-- end service -->
				</div>
				</div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-next">
					<i class="fa fa-angle-right" aria-hidden="true"></i>
				</div>
				<div class="swiper-button-prev">
					<i class="fa fa-angle-left" aria-hidden="true"></i>
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</div>
	<!-- end section -->



	<!-- footer -->
	 <footer>
        <div class="foot">
            <div class="container">
                <div class="row" style="height:200px;">
                    <div class="col-3"></div>
                    <div class="col-9"></div>
                </div>
            </div>
        </div>
    </footer>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>




		<script src="js/swiper.min.js"></script>
		<script>
			var swiper = new Swiper('.swiper-container', {
				loop : true,
				effect : 'coverflow',
				centeredSlides : true,
				loopFillGroupWithBlank : true,
				slidesPerView : 3,
				initialSlide : 3,
				keyboardControl : true,
				mousewheelControl : false,
				lazyLoading : true,
				pagination : {
					el : '.swiper-pagination',
					clickable : true,
				},
				navigation : {
					nextEl : '.swiper-button-next',
					prevEl : '.swiper-button-prev',
				},
				breakpoints : {
					1199 : {
						slidesPerView : 3,
						spaceBetween : 30,
					},
					991 : {
						slidesPerView : 3,
						spaceBetween : 10,
					},
					767 : {
						slidesPerView : 2,
						spaceBetween : 10,
					},
					575 : {
						slidesPerView : 1,
						spaceBetween : 3,
					}
				}
			});
		</script>
		<script src="js/swiper.min.js"></script>

		</script>
</footer>
<!-- end footer -->
	
	
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
	<!-- sidebar -->
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/custom.js"></script>
	<!-- javascript -->
	<script src="js/owl.carousel.js"></script>
	<script
		src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".fancybox").fancybox({
				openEffect : "none",
				closeEffect : "none"
			});

			$(".zoom").hover(function() {

				$(this).addClass('transition');
			}, function() {

				$(this).removeClass('transition');
			});
		});
	</script>
</body>

</html>
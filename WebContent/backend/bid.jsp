<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>競標管理</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-file-signature"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>CFA101G1</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"></li>
                    <li class="nav-item"></li>
                    <li class="nav-item"><a class="nav-link" href="item.jsp">
                        <i class="fas fa-table"></i><span>商城管理系統</span></a><a class="nav-link" href="used.jsp">
                        <i class="fas fa-table"></i><span>二手商城管理系統</span></a><a class="nav-link active" href="bid.jsp">
                        <i class="fas fa-tachometer-alt"></i><span>競標後台管理</span></a><a class="nav-link" href="auct.jsp">
                        <i class="fas fa-tachometer-alt"></i><span>拍賣後台管理</span></a><a class="nav-link"  href="money.jsp">
                        <i class="fas fa-money-bill"></i><span>錢包管理</span></a><a class="nav-link" href="repair.jsp">
                        <i class="fas fa-user"></i><span>檢測維修管理</span></a><a class="nav-link" href="frontend.jsp">
                        <i class="fas fa-magic"></i><span>會員管理系統</span></a><a class="nav-link" href="admini.jsp">
                        <i class="fas fa-user-circle"></i><span>員工管理</span></a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="login.jsp"><i
                                class="far fa-user-circle"></i><span>Login</span></a></li>
                    <li class="nav-item"></li>
                </ul>
                <div class="text-center d-none d-md-inline"></div><button class="btn rounded-circle border-0"
                    id="sidebarToggle" type="button"></button>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3"
                            id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <div class="input-group"><h2>競標後台管理</h2> </div>
                        <ul class="nav navbar-nav flex-nowrap ml-auto">
                            <li class="nav-item dropdown no-arrow mx-1">
                                <div><a class="dropdown-toggle nav-link" target="_blank"
                                         aria-expanded="false" href="<%=request.getContextPath() %>/chat/insta.jsp"><i class="far fa-comments"></i><span
                                            class="badge badge-danger badge-counter"></span></a>
                                </div>
                            </li>
                            <div class="d-none d-sm-block topbar-divider"></div>
                            <li class="nav-item dropdown no-arrow">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link"
                                        data-toggle="dropdown" aria-expanded="false" href="#"><span
                                            class="d-none d-lg-inline mr-2 text-gray-600 small">${adminiVO.adminName}</span><img
                                            class="border rounded-circle img-profile"
                                            src="images/logo.png"></a>
                                    <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
                                        <a class="dropdown-item" href="<%=request.getContextPath() %>/backend/Logout"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="container-fluid">
                    <div class="d-sm-flex justify-content-between align-items-center mb-4">
                        <h3 class="text-dark mb-0">競標商品管理</h3>
                    </div>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 font-weight-bold">競標商品管理</p>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <jsp:include page="/backend/bid/select_page.jsp" flush="true"/>
                            </div>
                            <div class="row">
                            </div>
                        </div>
                    </div>
                </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © CMI 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/chart.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="assets/js/theme.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>

</html>
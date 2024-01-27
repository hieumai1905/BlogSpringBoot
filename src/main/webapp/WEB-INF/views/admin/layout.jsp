<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="keywords"
        content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
  <meta name="description"
        content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
  <meta name="robots" content="noindex,nofollow">
  <title>Admin</title>
  <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
  <!-- Favicon icon -->
  <link rel="icon" type="image/png" sizes="16x16" href="../static/admin/plugins/images/favicon.png">
  <!-- Custom CSS -->
  <link href="../static/admin/plugins/bower_components/chartist/dist/chartist.min.css" rel="stylesheet">
  <link rel="stylesheet" href="../static/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.css">
  <!-- Custom CSS -->
  <link href="../static/admin/css/style.min.css" rel="stylesheet">

  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

  <!-- All Jquery -->
  <!-- ============================================================== -->
  <script src="../static/admin/plugins/bower_components/jquery/dist/jquery.min.js"></script>
  <!-- Bootstrap tether Core JavaScript -->
  <script src="../static/admin/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../static/admin/js/app-style-switcher.js"></script>
  <script src="../static/admin/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js"></script>
  <!--Wave Effects -->
  <script src="../static/admin/js/waves.js"></script>
  <!--Menu sidebar -->
  <script src="../static/admin/js/sidebarmenu.js"></script>
  <!--Custom JavaScript -->
  <script src="../static/admin/js/custom.js"></script>
  <!--This page JavaScript -->
  <!--chartis chart-->
  <script src="../static/admin/plugins/bower_components/chartist/dist/chartist.min.js"></script>
  <script src="../static/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
  <script src="../static/admin/js/pages/dashboards/dashboard1.js"></script>
</head>

<body>
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
  <div class="lds-ripple">
    <div class="lds-pos"></div>
    <div class="lds-pos"></div>
  </div>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
     data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
  <!-- ============================================================== -->
  <!-- Topbar header - style you can find in pages.scss -->
  <!-- ============================================================== -->
  <header class="topbar" data-navbarbg="skin5">
    <nav class="navbar top-navbar navbar-expand-md navbar-dark">
      <div class="navbar-header" data-logobg="skin6">
        <!-- ============================================================== -->
        <!-- Logo -->
        <!-- ============================================================== -->
        <a class="navbar-brand" href="/admin">
          <!-- Logo icon -->
          <b class="logo-icon">
            <!-- Dark Logo icon -->
            <img src="../static/admin/plugins/images/logo-icon.png" alt="homepage" />
          </b>
          <!--End Logo icon -->
          <!-- Logo text -->
          <span class="logo-text">
                            <!-- dark Logo text -->
                            <img src="../static/admin/plugins/images/logo-text.png" alt="homepage" />
                        </span>
        </a>
        <!-- ============================================================== -->
        <!-- End Logo -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- toggle and nav items -->
        <!-- ============================================================== -->
        <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
           href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
      </div>
      <!-- ============================================================== -->
      <!-- End Logo -->
      <!-- ============================================================== -->
      <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">

        <!-- ============================================================== -->
        <!-- Right side toggle and nav items -->
        <!-- ============================================================== -->
        <ul class="navbar-nav ms-auto d-flex align-items-center">

          <!-- ============================================================== -->
          <!-- Search -->
          <!-- ============================================================== -->
<%--          <li class=" in">--%>
<%--            <form role="search" class="app-search d-none d-md-block me-3">--%>
<%--              <input type="text" placeholder="Search..." class="form-control mt-0">--%>
<%--              <a href="" class="active">--%>
<%--                <i class="fa fa-search"></i>--%>
<%--              </a>--%>
<%--            </form>--%>
<%--          </li>--%>
          <!-- ============================================================== -->
          <!-- User profile and search -->
          <!-- ============================================================== -->
          <li>
            <a class="profile-pic" href="/info">
              <img src="../static/admin/plugins/images/users/varun.jpg" alt="user-img" width="36"
                   class="img-circle"><span class="text-white font-medium">${sessionScope['account-login'].fullname}</span></a>
          </li>
          <!-- ============================================================== -->
          <!-- User profile and search -->
          <!-- ============================================================== -->
        </ul>
      </div>
    </nav>
  </header>
  <!-- ============================================================== -->
  <!-- End Topbar header -->
  <!-- ============================================================== -->
  <!-- ============================================================== -->
  <!-- Left Sidebar - style you can find in sidebar.scss  -->
  <!-- ============================================================== -->
  <aside class="left-sidebar" data-sidebarbg="skin6">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
      <!-- Sidebar navigation-->
      <nav class="sidebar-nav">
        <ul id="sidebarnav">
          <li class="sidebar-item">
            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/blog"
               aria-expanded="false">
              <i class="fa fa-home" aria-hidden="true"></i>
              <span class="hide-menu">Blog</span>
            </a>
          </li>
          <c:if test="${sessionScope['user-role'] eq 'ROLE_ADMIN'}">
            <li class="sidebar-item pt-2">
              <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin"
                 aria-expanded="false">
                <i class="far fa-clock" aria-hidden="true"></i>
                <span class="hide-menu">Dashboard</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin/categories"
                 aria-expanded="false">
                <i class="fa fa-folder" aria-hidden="true"></i>
                <span class="hide-menu">Categories</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin/types"
                 aria-expanded="false">
                <i class="fa fa-tags" aria-hidden="true"></i>
                <span class="hide-menu">Type</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin/users" aria-expanded="false">
                <i class="fa fa-users" aria-hidden="true"></i>
                <span class="hide-menu">Users</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin/image-slides" aria-expanded="false">
                <i class="fa fa-image" aria-hidden="true"></i>
                <span class="hide-menu">Image Slide</span>
              </a>
            </li>
          </c:if>
          <li class="sidebar-item">
            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/admin/posts"
               aria-expanded="false">
              <i class="fa fa-newspaper" aria-hidden="true"></i>
              <span class="hide-menu">Posts</span>
            </a>
          </li>
        </ul>

      </nav>
      <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
  </aside>
  <!-- ============================================================== -->
  <!-- End Left Sidebar - style you can find in sidebar.scss  -->
  <!-- ============================================================== -->
  <!-- ============================================================== -->
  <!-- Page wrapper  -->
  <!-- ============================================================== -->
  <div class="page-wrapper">

    <c:if test="${requestScope['content-in-layout'] eq 'dash-board'}">
      <jsp:include page="dash-board.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'category'}">
      <jsp:include page="category.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'type'}">
      <jsp:include page="type.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'profile'}">
      <jsp:include page="profile.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'user'}">
      <jsp:include page="user.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'post'}">
      <jsp:include page="post.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'image-slide'}">
      <jsp:include page="image-slide.jsp"/>
    </c:if>
    <c:if test="${requestScope['content-in-layout'] eq 'create-post' || requestScope['content-in-layout'] eq 'edit-post'}">
      <jsp:include page="form-post.jsp"/>
    </c:if>

  </div>
  <!-- ============================================================== -->
  <!-- End Page wrapper  -->
  <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<!-- ============================================================== -->

</body>

</html>

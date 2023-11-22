<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Foodeiblog Template">
    <meta name="keywords" content="Foodeiblog, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Foodeiblog | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300,400,600,700,800,900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Unna:400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Css Styles -->
    <!-- Css Styles -->
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/dropdown.css" type="text/css">
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="./index.html"><img src="../../static/img/humberger/humberger-logo.png" alt=""></a>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li><a href="/blog">Home</a></li>
            <li><a href="/admin/posts">My Posts</a></li>
            <li><a href="#">Dinner</a></li>
            <li><a href="#">Desserts</a></li>
            <li class="dropdown"><a href="#">Pages</a>
                <ul class="dropdown__menu">
                    <li><a href="./categories-grid.html">Categories Grid</a></li>
                </ul>
            </li>
            <li><a href="./about.html">About</a></li>
            <li><a href="./contact.html">Contact</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="humberger__menu__about">
        <div class="humberger__menu__title sidebar__item__title">
            <h6>About me</h6>
        </div>
        <img src="../../static/img/humberger/humberger-about.jpg" alt="">
        <h6>Hi every one! I,m Lena Mollein.</h6>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua.</p>
        <div class="humberger__menu__about__social sidebar__item__follow__links">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-youtube-play"></i></a>
            <a href="#"><i class="fa fa-instagram"></i></a>
            <a href="#"><i class="fa fa-envelope-o"></i></a>
        </div>
    </div>
    <div class="humberger__menu__subscribe">
        <div class="humberger__menu__title sidebar__item__title">
            <h6>Subscribe</h6>
        </div>
        <p>Subscribe to our newsletter and get our newest updates right on your inbox.</p>
        <form action="#">
            <input type="text" class="email-input" placeholder="Your email">
            <label for="agree-check">
                I agree to the terms & conditions
                <input type="checkbox" id="agree-check">
                <span class="checkmark"></span>
            </label>
            <button type="submit" class="site-btn">Subscribe</button
        </form>
    </div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-1 col-6 order-md-1 order-1">
                    <!--          <div class="header__humberger">-->
                    <!--            <i class="fa fa-bars humberger__open"></i>-->
                    <!--          </div>-->
                </div>
                <div class="col-lg-8 col-md-10 order-md-2 order-3">
                    <nav class="header__menu">
                        <ul>
                            <li><a href="/blog">Home</a></li>
                            <li class="dropdown-custom"><a>
                                Category
                            </a>
                                <div id="category-ul" class="dropdown-content">
                                    <a href="#" style="text-align: left; padding: 10px;">Doanh muc 1</a>
                                </div></li>
                            <li class="dropdown-custom"><a>
                                Type
                            </a>
                                <div id="type-ul" class="dropdown-content">
                                    <a href="#" style="text-align: left; padding: 10px;">Doanh muc 1</a>
                                </div></li>

                            <c:set var="userRole" value="${sessionScope['user-role']}"/>
                            <c:if test="${userRole == 'ROLE_ADMIN'}">
                                <li><a href="/admin">Admin</a></li>
                            </c:if>
                            <c:if test="${userRole == 'ROLE_USER'}">
                                <li><a href="/admin/posts">My Posts</a></li>
                            </c:if>
                            <li><a href="/logout">Logout</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-2 col-md-1 col-6 order-md-3 order-2">
                    <div class="header__search">
                        <input id="searchText" type="text" placeholder="Search...">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="carouselExampleIndicators" class="carousel slide container" data-ride="carousel">
            <ol class="carousel-indicators">
<%--                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>--%>
<%--                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
                <c:set var="i" value="0"/>
                <c:forEach items="${requestScope['imageSlides']}" var="img">
                    <li data-target="#carouselExampleIndicators" data-slide-to="${i}" class="${i == 0 ? 'active' : ''}"></li>
                    <c:set var="i" value="${i + 1}" />
                </c:forEach>
            </ol>
            <div class="carousel-inner">
                <c:set var="i" value="0"/>
                <c:forEach items="${requestScope['imageSlides']}" var="img">
                    <div style="position: relative;" class="carousel-item ${i == 0 ? 'active' : ''}">
                        <span style="position: absolute; top: 80%;
                            left: 15%; transform: translate(-50%, -50%);
                            color: white; font-size: 35px; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);">${img.description}</span>
                        <img src="../static${img.link}" height="400" class="d-block w-100" alt="Slide ${i + 1}">
                    </div>
                    <c:set var="i" value="${i + 1}" />
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

</header>
<!-- Header Section End -->

<!-- Categories Section Begin -->
<section class="categories categories-grid spad">
    <div class="categories__post">
        <div class="container">
            <div class="categories__grid__post">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="breadcrumb__text">
                            <h2>HOME<span></span></h2>
                            <div class="breadcrumb__option">
                                <a href="#">Home</a>
                                <!--                <span>Recipes</span>-->
                            </div>
                        </div>
                        <div class="row">
                            <div id="data" class="row w-100"></div>
                            <div class="col-lg-12 text-center">
                                <div id="btnLoad" class="load__more__btn">
                                    <a>Load more</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->

<!-- Footer Section Begin -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="footer__text">
                    <div class="footer__logo">
                        <a href="#"><img src="../../static/img/logo.png" alt=""></a>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                        ut<br /> labore et dolore magna aliqua. Quis ipsum suspendisse ultrices gravida. Risus
                        commodo viverra<br /> maecenas accumsan lacus vel facilisis. </p>
                    <div class="footer__social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-youtube-play"></i></a>
                        <a href="#"><i class="fa fa-envelope-o"></i></a>
                    </div>
                </div>
                <div class="footer__copyright">
                    <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Js Plugins -->
<script src="../../static/js/jquery-3.3.1.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/jquery.slicknav.js"></script>
<script src="../../static/js/owl.carousel.min.js"></script>
<script src="../../static/js/main.js"></script>
<script src="../../static/js/home.js"></script>
</body>

</html>
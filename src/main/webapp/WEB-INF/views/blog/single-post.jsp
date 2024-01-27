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
  <title>Foodeiblog</title>

  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300,400,600,700,800,900&display=swap"
        rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Unna:400,700&display=swap" rel="stylesheet">

  <!-- Css Styles -->
  <link rel="stylesheet" href="../../static/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="../../static/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../../static/css/elegant-icons.css" type="text/css">
  <link rel="stylesheet" href="../../static/css/owl.carousel.min.css" type="text/css">
  <link rel="stylesheet" href="../../static/css/slicknav.min.css" type="text/css">
  <link rel="stylesheet" href="../../static/css/style.css" type="text/css">
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
      <li><a href="#">Recipes</a></li>
      <li><a href="#">Dinner</a></li>
      <li><a href="#">Desserts</a></li>
      <li class="dropdown"><a href="#">Pages</a>
        <ul class="dropdown__menu">
          <li><a href="./categories-grid.html">Categories Grid</a></li>
          <li><a href="./categories-list.html">Categories List</a></li>
          <li><a href="./single-post.html">Single Post</a></li>
          <li><a href="./signin.html">Sign In</a></li>
          <li><a href="./404.html">404</a></li>
          <li><a href="./typography.html">Typography</a></li>
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
      <button type="submit" class="site-btn">Subscribe</button>
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
          <div class="header__humberger">
            <i class="fa fa-bars humberger__open"></i>
          </div>
        </div>
        <div class="col-lg-8 col-md-10 order-md-2 order-3">
          <nav class="header__menu">
            <ul>
              <li><a href="/blog">Home</a></li>
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
            <i class="fa fa-search search-switch"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-3">
        <div class="header__btn">
          <a href="./signin.html" class="primary-btn">Subscribe</a>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="header__logo">
          <a href="./index.html"><img src="../../static/img/logo.png" alt=""></a>
        </div>
      </div>
      <div class="col-lg-3 col-md-3 col-md-2">
        <div class="header__social">
          <a href="#"><i class="fa fa-facebook"></i></a>
          <a href="#"><i class="fa fa-twitter"></i></a>
          <a href="#"><i class="fa fa-youtube-play"></i></a>
          <a href="#"><i class="fa fa-instagram"></i></a>
          <a href="#"><i class="fa fa-envelope-o"></i></a>
        </div>
      </div>
    </div>
  </div>
</header>
<!-- Header Section End -->

<!-- Single Post Section Begin -->
<section class="single-post spad">
  <input id="postId" value="${requestScope['postId']}" type="hidden"/>
  <input id="userId" value="${sessionScope['account-login'].accountId}" type="hidden"/>
  <div id="picture" class="single-post__hero set-bg" data-setbg="../../static/img/categories/single-post/single-post-hero.jpg" style="max-height: 700px"></div>
  <div class="container">
    <div class="row d-flex justify-content-center">
      <div class="col-lg-8">
        <div class="single-post__title">
          <div class="single-post__title__meta">
            <h2 id="day">08</h2>
            <span id="month">Aug</span>
          </div>
          <div class="single-post__title__text">
            <ul class="label">
              <li>Vegan</li>
              <li>Desserts</li>
            </ul>
            <h4 id="title"></h4>
            <ul class="widget">
              <li>by <span id="full-name"></span></li>
              <li>3 min read</li>
              <li><span class="numberOfComments"></span> Comment</li>
            </ul>
          </div>
        </div>
        <div class="single-post__social__item">
          <ul>
            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
            <li><a href="#"><i class="fa fa-instagram"></i></a></li>
            <li><a href="#"><i class="fa fa-youtube-play"></i></a></li>
          </ul>
        </div>
        <div id="brief-content" class="single-post__top__text">
        </div>
        <div id="content" class="single-post__more__details">
        </div>
        <div class="single-post__tags">
          <hr>
        </div>
        <div class="single-post__comment">
          <div>
            <div class="form-group" id="rating-ability-wrapper">
              <input type="hidden" id="selected_rating" name="selected_rating" value="" required="required">
              <div class="widget__title">
                <h4>Rating <span id="ratePost"></span></h4><br/>
                <span>Your Rate: </span><span class="selected-rating">0</span><small> / 5</small>
              </div>
              <button type="button" class="btnrating btn btn-default btn-lg" data-attr="1" id="rating-star-1">
                <i class="fa fa-star" aria-hidden="true"></i>
              </button>
              <button type="button" class="btnrating btn btn-default btn-lg" data-attr="2" id="rating-star-2">
                <i class="fa fa-star" aria-hidden="true"></i>
              </button>
              <button type="button" class="btnrating btn btn-default btn-lg" data-attr="3" id="rating-star-3">
                <i class="fa fa-star" aria-hidden="true"></i>
              </button>
              <button type="button" class="btnrating btn btn-default btn-lg" data-attr="4" id="rating-star-4">
                <i class="fa fa-star" aria-hidden="true"></i>
              </button>
              <button type="button" class="btnrating btn btn-default btn-lg" data-attr="5" id="rating-star-5">
                <i class="fa fa-star" aria-hidden="true"></i>
              </button>
            </div>
          </div>
          <div class="widget__title">
            <h4><span class="numberOfComments"></span> Comment</h4>
          </div>
          <div id="comments"></div>
        </div>
        <div class="single-post__leave__comment">
          <div class="widget__title">
            <h4>Leave a comment</h4>
          </div>
          <form action="#">
            <textarea id="comment" placeholder="Comment"></textarea>
            <button type="button" id="btnComment" class="site-btn">Submit</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Single Post Section End -->

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

<!-- Search Begin -->
<div class="search-model">
  <div class="h-100 d-flex align-items-center justify-content-center">
    <div class="search-close-switch">+</div>
    <form class="search-model-form">
      <input type="text" id="search-input" placeholder="Search here.....">
    </form>
  </div>
</div>
<!-- Search End -->

<!-- Js Plugins -->
<script src="../../static/js/jquery-3.3.1.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/jquery.slicknav.js"></script>
<script src="../../static/js/owl.carousel.min.js"></script>
<script src="../../static/js/main.js"></script>
<script src="../../static/js/single_post.js"></script>
</body>

</html>
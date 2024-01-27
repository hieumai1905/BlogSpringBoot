<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Foodeiblog Template">
    <meta name="keywords" content="Foodeiblog, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300,400,600,700,800,900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Unna:400,700&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../static/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../static/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css/style.css" type="text/css">
</head>

<body class="fixed-position">
<!-- Sign In Section Begin -->
<div class="signin">
    <div class="signin__warp">
        <div class="signin__content">
            <div class="signin__logo">
                <a href="#"><img src="../static/img/siign-in-logo.png" alt=""></a>
            </div>
            <div class="signin__form">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link" href="/register" aria-selected="false">
                            Sign up
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/login" aria-selected="false">
                            Sign in
                        </a>
                    </li>
                    <li class="nav-item">
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tabs-2" role="tabpanel">
                        <div class="signin__form__text">
                            <form action="/login" method="post" onsubmit="return checkLogin()">
                                <input type="email" placeholder="Input email" name="email" id="email-login">
                                <input type="password" placeholder="Input password" name="password" id="password-login">
                                <button type="submit" class="site-btn">Sign In</button>
                                <p style="color: red; margin-top: 10px;" id="login-error">
                                    <c:if test="${not empty error}">
                                        <c:out value="${error}"/>
                                </c:if>
                                </p>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Sign In Section End -->
<!-- Js Plugins -->
<script src="../static/js/jquery-3.3.1.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/jquery.slicknav.js"></script>
<script src="../static/js/owl.carousel.min.js"></script>
<script src="../static/js/main.js"></script>
<script src="../static/assets/js/login.js"></script>
<script src="../static/assets/js/validation.js"></script>
</body>
</html>
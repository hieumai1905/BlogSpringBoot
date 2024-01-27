<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Information</h4>
        </div>
    </div>
</div>

<div class="container-fluid">
    <!-- ============================================================== -->
    <!-- Start Page Content -->
    <!-- ============================================================== -->
    <!-- Row -->
    <div class="row">
        <!-- Column -->
        <div class="col-lg-4 col-xlg-3 col-md-12">
            <div class="white-box">
                <div class="user-bg">
                    <div class="overlay-box">
                        <div class="user-content">
                            <h4 class="text-white mt-2" id="showFullName">${account.fullname}</h4>
                            <h5 class="text-white mt-2" id="showEmail">${account.email}</h5>
                            <h3 class="text-white mt-2" id="showCountPost">${postCount} posts</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Column -->
        <!-- Column -->
        <div class="col-lg-8 col-xlg-9 col-md-12">
            <div class="card">
                <div class="card-body">
                    <form class="form-horizontal form-material" action="/account/update" method="post"
                          onsubmit="return validateUpdateAccount()">
                        <div class="form-group mb-4">
                            <label class="col-md-12 p-0">Full Name</label>
                            <div class="col-md-12 border-bottom p-0">
                                <input type="text" placeholder="Input your name" id="fullname-update" name="fullname"
                                       value="${account.fullname}"
                                       class="form-control p-0 border-0"></div>
                        </div>
                        <input type="hidden" placeholder="Input your email"
                               value="${account.email}"
                               class="form-control p-0 border-0" name="email"
                               id="example-email">
                        <div class="form-group mb-4">
                            <label class="col-md-12 p-0">Password</label>
                            <div class="col-md-12 border-bottom p-0">
                                <input type="password" placeholder="Input new password" value="${account.password}" name="password"
                                       class="form-control p-0 border-0" id="password-update">
                            </div>
                        </div>
                        <div class="form-group mb-4"><label class="col-md-12 p-0">Gender</label>
                            <div class="col-md-12 border-bottom p-0"><select class="form-control p-0 border-0" name="gender"
                                                                             id="gender-update">
                                <option value="0" ${account.gender == 0 ? 'selected' : ''}>Male</option>
                                <option value="1" ${account.gender == 1 ? 'selected' : ''}>Female</option>
                            </select></div>
                        </div>
                        <div class="form-group mb-4">
                            <label class="col-md-12 p-0">BirthDay</label>
                            <div class="col-md-12 border-bottom p-0">
                                <input type="hidden" id="birthdayData" value="${account.birthday}">
                                <input type="date" class="form-control p-0 border-0" name="birthday"
                                       id="birthdayCurrent">
                            </div>
                        </div>
                        <div class="form-group mb-4">
                            <div class="col-sm-12">
                                <button type="submit" role="button" class="btn btn-success">Update</button>
                            </div>
                        </div>
                        <p><i style="color: red; display: none;" id="update-error"></i></p>
                            <p id="error-server"><i style="color: red">${message}</i></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../static/admin/js/profile.js"></script>
<script src="../static/assets/js/validation.js"></script>
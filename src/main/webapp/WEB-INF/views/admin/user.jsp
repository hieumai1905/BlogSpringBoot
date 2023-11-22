<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Users</h4>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="row form-group ml-2">
                    <div class="col-md-5">
                        <label for="searchUsers">Search Users: </label>
                        <input class="form-control" type="text"
                               placeholder="enter full name or email..."
                               id="searchUsers"
                        />
                    </div>
                    <div class="col-md-4"></div>
<%--                    <div class="col-md-3">--%>
<%--                        <button type="button" class="btn btn-primary mt-4" id="btnAddNewUser">--%>
<%--                            Create New--%>
<%--                        </button>--%>
<%--                    </div>--%>
                </div>
                <div class="table-responsive">
                    <table id="myTable" class="table text-nowrap">
                        <thead>
                        <tr>
                            <th class="border-top-0">ID</th>
                            <th class="border-top-0">Full Name</th>
                            <th class="border-top-0">Email</th>
                            <th class="border-top-0">Password</th>
                            <th class="border-top-0">Birthday</th>
                            <th class="border-top-0">Gender</th>
                            <th class="border-top-0">Role</th>
                            <th class="border-top-0">Status</th>
                            <th class="border-top-0">Create At</th>
                            <th class="border-top-0">Action</th>
                        </tr>
                        </thead>
                        <tbody id="data">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="userId"/>
                <input type="hidden" id="createAt"/>
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" class="form-control" placeholder="enter type name" id="fullName"/>
                    <span id="fullName-message" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" placeholder="enter email" id="email"/>
                    <span id="email-message" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" placeholder="enter password" id="password"/>
                    <span id="password-message" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label for="birthday">Birthday</label>
                    <input type="date" class="form-control" placeholder="enter birthday" id="birthday"/>
                    <span id="birthday-message" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <select class="form-control" id="gender">
                        <option value="1">Male</option>
                        <option value="0">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="role">Role</label>
                    <select class="form-control" id="role">
                        <option value="1">Admin</option>
                        <option value="0">User</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" id="btnSave" class="btn btn-primary"></button>
            </div>
        </div>
    </div>
</div>

<script src="../static/assets/js/validation.js"></script>
<script src="../static/admin/js/user.js"></script>
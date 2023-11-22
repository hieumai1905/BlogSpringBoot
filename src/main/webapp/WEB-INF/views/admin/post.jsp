<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <input type="hidden" value="${sessionScope["account-login"].accountId}" id="accId"/>
            <input type="hidden" value="${sessionScope["user-role"]}" id="roleAcc"/>
            <h4 class="page-title">Posts</h4>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="row form-group ml-2">
                    <div class="col-md-3">
                        <label for="searchPosts">Search Posts: </label>
                        <input class="form-control" type="text"
                           placeholder="enter title..."
                           value=""
                           id="searchPosts"/>
                    </div>
                    <div class="col-md-3">
                        <label for="categoryId">Category: </label>
                        <select class="form-control" id="categoryId">
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="typeId2">Type: </label>
                        <select class="form-control" id="typeId2">
                        </select>
                    </div>
                    <div class="col-md-3">
                        <a href="/admin/create-post" type="button" class="btn btn-primary" style="margin-top: 30px">
                            Create New
                        </a>
                    </div>
                </div>
                <div class="table-responsive">
                    <table id="myTable" class="table text-nowrap">
                        <thead>
                        <tr>
                            <th class="border-top-0">Title</th>
                            <th class="border-top-0">Picture</th>
                            <th class="border-top-0">Create At</th>
                            <th class="border-top-0">Status</th>
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

<script src="https://cdn.ckeditor.com/4.22.1/full/ckeditor.js"></script>
<script src="../static/admin/js/post.js"></script>
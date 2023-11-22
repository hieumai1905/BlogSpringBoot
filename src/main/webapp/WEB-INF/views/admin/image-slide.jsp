<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <input type="hidden" value="${sessionScope["account-login"].accountId}" id="accId"/>
            <input type="hidden" value="${sessionScope["user-role"]}" id="roleAcc"/>
            <h4 class="page-title">Image Slide</h4>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="row form-group ml-2">
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                        <a type="button" id="btnCreate" class="btn btn-primary" style="margin-top: 30px">
                            Create New
                        </a>
                    </div>
                </div>
                <div class="table-responsive">
                    <table id="myTable" class="table text-nowrap">
                        <thead>
                        <tr>
                            <th class="border-top-0">ID</th>
                            <th class="border-top-0">Link</th>
                            <th class="border-top-0">Description</th>
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
                <input type="hidden" id="imgId"/>
                <div class="form-group">
                    <label for="imgFile">Image</label>
                    <input id="old-img" type="hidden" />
                    <input type="file" class="form-control" id="imgFile" accept="image/*"/>
                    <img id="preview-img" alt="preview img" width="50" height="50" src=""/>
                    <span id="message" class="text-danger"></span>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" id="btnSave" class="btn btn-primary"></button>
            </div>
        </div>
    </div>
</div>


<script src="../static/admin/js/img_slide.js"></script>

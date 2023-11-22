<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">Categories</h4>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="row form-group ml-2">
                    <div class="col-md-5">
                        <label for="searchCategoryName">Search Categories By Name: </label>
                        <input class="form-control" type="text"
                               placeholder="enter category name..."
                               id="searchCategoryName"
                        />
                    </div>
                    <div class="col-md-4"></div>
                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary mt-4" id="btnAddNewCategory">
                            Create New
                        </button>
                    </div>
                </div>
                <div class="table-responsive">
                    <table id="myTable" class="table text-nowrap">
                        <thead>
                        <tr>
                            <th class="border-top-0">ID</th>
                            <th class="border-top-0">Category Name</th>
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
                <input type="hidden" id="categoryId"/>
                <div class="form-group">
                    <label for="categoryName">Category Name</label>
                    <input type="text" class="form-control" placeholder="enter category name" id="categoryName"/>
                    <span id="message" class="text-danger"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" id="btnSave" class="btn btn-primary"></button>
            </div>
        </div>
    </div>
</div>


<script src="../static/admin/js/category.js"></script>
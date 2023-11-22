<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-breadcrumb bg-white">
    <div class="row align-items-center">
        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
            <h4 class="page-title">
                <c:choose>
                    <c:when test="${requestScope['content-in-layout'] eq 'create-post'}">
                        Create Post
                    </c:when>
                    <c:otherwise>
                        Edit Post
                    </c:otherwise>
                </c:choose>
            </h4>
        </div>
    </div>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="m-2">
                    <input type="hidden" id="postId" value="${requestScope['postId']}"/>
                    <input type="hidden" id="createAt"/>
                    <input type="hidden" id="old-picture"/>
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" placeholder="enter title" id="title"/>
                        <span id="title-message" class="text-danger"></span>
                    </div>
                    <div class="form-group">
                        <label for="brief-content">Brief Content</label>
                        <textarea type="text" class="form-control" placeholder="enter brief content" id="brief-content"></textarea>
                        <span id="brief-content-message" class="text-danger"></span>
                    </div>
                    <div class="form-group">
                        <label for="content">Content</label>
                        <textarea id="content"></textarea>
                        <span id="content-message" class="text-danger"></span>
                    </div>
                    <div class="form-group">
                        <label for="picture">Picture</label>
                        <input type="file" class="form-control" id="picture" accept="image/*"/>
                        <div id="imagePreview">
                            <img id="preview-img" width="50" height="50" src="" alt="Preview Image">
                        </div>
                        <span id="picture-message" class="text-danger"></span>
                    </div>
                    <div class="row">
                        <div class="col-md-6 form-group">
                            <label for="categoryId2">Category</label>
                            <select class="form-control" id="categoryId2">
                            </select>
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="typeId">Type</label>
                            <select class="form-control" id="typeId">
                            </select>
                        </div>
                    </div>
<%--                    <div class="row">--%>
<%--                        <div class="col-md-6 form-group">--%>
<%--                            <label for="status">Status</label>--%>
<%--                            <select class="form-control" id="status">--%>
<%--                                <option value="1">Active</option>--%>
<%--                                <option value="0">InActive</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                        <div class="col-md-6 form-group">--%>
<%--                            <label for="rate">Rate <span id="rate-value">0</span></label>--%>
<%--                            <input class="form-control" id="rate" type="range" min="0" max="5" value="0"/>--%>

<%--                        </div>--%>
<%--                    </div>--%>

                    <button type="button" id="btnSave" class="btn btn-primary">
                        <c:choose>
                            <c:when test="${requestScope['content-in-layout'] eq 'create-post'}">
                                Create Post
                            </c:when>
                            <c:otherwise>
                                Edit Post
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.ckeditor.com/4.22.1/full/ckeditor.js"></script>
<script src="../static/admin/js/form-post.js">
</script>
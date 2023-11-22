
$(document).ready(function() {
    loadCategories('#categoryId');
    loadType();
    loadPosts();

    $('#searchPosts').on('input', searchPosts);
    $('#categoryId').change(searchPosts);
    $('#typeId2').change(searchPostsByType);

    $(document).on('click', '.btn-delete', function() {
        const postId = parseInt($(this).data('id'), 10);
        deletePost(postId);
    });

    $(document).on('click', '.btn-active',function() {
        const postId = $(this).data('id');
        console.log('aa', postId);
        activePost(postId);
    });

});

function loadPosts() {
    const categoryId = parseInt($('#categoryId').val(), 10);
    const typeId = parseInt($('#typeId2').val(), 10);

    $.ajax({
        url: '/api/posts',
        type: 'GET',
        dataType: 'json',
        success: function(data) {

            if(!isNaN(categoryId) && categoryId !== -1){
                data = data.filter(item => item.categoryId === categoryId);
            }

            if(!isNaN(typeId) && typeId !== -1){
                data = data.filter(item => item.typeId === typeId);
            }

            showPostsToScreen(data);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function showPostsToScreen(data){
    let title = $('#searchPosts').val().toLowerCase().trim();
    $('#data').empty();
    const accId = parseInt($('#accId').val().trim(), 10);
    const roleAcc = $('#roleAcc').val().trim();

    if(data.length > 0)
        data.sort((a, b) => a.status - b.status);

    data.forEach(function(post) {
        const formattedDate = formatDate(post.createAt);
        if(post.title.toLowerCase().includes(title)
            && (accId === post.accountId || roleAcc === "ROLE_ADMIN")){

            let btnActive = `<button class="btn btn-success btn-active text-white" data-id="${post.postId}">Active</button>`;

            if(post.status === 1 || roleAcc === "ROLE_USER")
                btnActive = ``;

            $('#data').append(
                `<tr>
                    <td>${post.title}</td>
                    <td>${post.picture}</td>
                    <td>${formattedDate}</td>
                    <td>${post.status === 1 ? 'Active' : 'InActive'}</td>
                    <td>
                        <a class="btn btn-warning btn-edit" href="/admin/edit-post?id=${post.postId}">Edit</a>
                        <button class="btn btn-danger btn-delete text-white" data-id="${post.postId}">Delete</button>
                        ${btnActive}
                    </td>
                </tr>`
            );
        }
    });
}

function activePost(id){
    $.ajax({
        url: '/api/posts/activePost/' + id,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log('Active post:', response);
            window.location.href = '/admin/posts';
        },
        error: function(xhr, status, error) {
            console.error('Lỗi khi kích hoạt bài viết:', error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "Post Not Found",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function loadType(){
    $.ajax({
        url: '/api/types',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let selectTypes = $('#typeId2');
            selectTypes.empty();

            selectTypes.append(
                `<option value="-1">All Types</option>`
            );

            data.forEach(function(type) {
                selectTypes.append(
                    `<option value="${type.typeId}">${type.typeName}</option>`
                );
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}
function loadCategories(id) {
    $.ajax({
        url: '/api/categories',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let selectCategories = $(id);
            selectCategories.empty();
            if(id === '#categoryId'){
                selectCategories.append(
                    `<option value="-1">All Categories</option>`
                );
            }

            data.forEach(function(category) {
                selectCategories.append(
                    `<option value="${category.categoryId}">${category.nameCategory}</option>`
                );
            });
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function formatDate(dateString) {
    const date = new Date(dateString);
    const formattedDate = `${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}`;
    return formattedDate;
}

function searchPosts(){
    const categoryId = parseInt($('#categoryId').val(), 10);
    const typeId = parseInt($('#typeId2').val(), 10);

    if(categoryId !== -1){
        $.ajax({
            url: `/api/posts/categories/${categoryId}`,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                if(typeId !== -1){
                    data = data.filter(item => item.typeId === typeId);
                }
                showPostsToScreen(data);
            },
            error: function(xhr, status, error) {
                console.error(error);
                $('#data').empty();
            }
        });
    }else{
        loadPosts();
    }
}

function searchPostsByType(){
    const categoryId = parseInt($('#categoryId').val(), 10);
    const typeId = parseInt($('#typeId2').val(), 10);

    if(typeId !== -1){
        $.ajax({
            url: `/api/posts/types/${typeId}`,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                if(categoryId !== -1){
                    data = data.filter(item => item.categoryId === categoryId);
                }
                showPostsToScreen(data);
            },
            error: function(xhr, status, error) {
                console.error(error);
                $('#data').empty();
            }
        });
    }else{
        loadPosts();
    }
}

function deletePost(id){
    if(confirm("Do you want to delete this post?") === true){
        $.ajax({
            url: '/api/posts/' + id,
            type: 'DELETE',
            success: function(response) {
                console.log('post deleted:', response);
                window.location.href = '/admin/posts';
            },
            error: function(xhr, status, error) {
                console.error('Error deleting user:', error);
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Post Not Found",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
}
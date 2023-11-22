$(window).ready(function() {
    CKEDITOR.replace( 'content', {
        language: 'en'
    } );

    loadType();
    loadCategories('#categoryId2');
    let postId = $('#postId').val();
    if(postId !== ''){
        getPostDetails(postId);
    }

    $(document).on('click', '#btnSave', function () {
        if($('#btnSave').text().trim() === 'Create Post'){
            addNewPost();
        }else{
            editPost();
        }
    });

    $('#picture').change(previewImg);

    $('#rate').on('input', function() {
        $('#rate-value').text($(this).val());
    });
});
let isValidData = true;
function loadType(){
    $.ajax({
        url: '/api/types',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let selectTypes = $('#typeId');
            selectTypes.empty();

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

function getPostDetails(postId){
    $.ajax({
        url: '/api/posts/' + postId,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log('Post data:', response);
            $('#createAt').val(response.createdAt);
            $('#title').val(response.title);
            $('#brief-content').val(response.briefContent);
            $('#rate').val(response.rate);
            $('#rate-value').text(response.rate);
            $('#old-picture').val(response.picture);
            $('#preview-img').attr('src','../static/' + response.picture);
            $('#categoryId2').val(response.categoryId);
            $('#typeId').val(response.typeId);
            $('#status').val(response.status);
            CKEDITOR.instances['content'].setData(response.content);
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "Post Not Found!",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

}

function previewImg(){
    const file = this.files[0];
    if (file){
        let reader = new FileReader();
        reader.onload = function(event){
            $('#preview-img').attr('src', event.target.result);
        }
        reader.readAsDataURL(file);
    }else{
        $('#preview-img').attr('src', '../static/' + $('#old-picture').val());
    }
}

function addNewPost(){
    isValidData = true;
    let post = getDataInput(false);
    if(isValidData){
        const formData = new FormData();
        formData.append('content', post.content);
        // formData.append('status', post.status);
        formData.append('title', post.title);
        formData.append('briefContent', post.briefContent);
        // formData.append('rate', post.rate);
        formData.append('categoryId', post.category);
        formData.append('typeId', post.type);
        formData.append('pictureFile', $('#picture')[0].files[0]);

        $.ajax({
            url: '/api/posts',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                window.location.href = "/admin/posts";
            },
            error: function(error) {
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Something went wrong!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });

    }
}

function editPost(){
    isValidData = true;
    let post = getDataInput(true);
    const postId = $('#postId').val();
    if(isValidData){
        const formData = new FormData();
        formData.append('content', post.content);
        // formData.append('status', post.status);
        formData.append('title', post.title);
        formData.append('briefContent', post.briefContent);
        // formData.append('rate', post.rate);
        formData.append('categoryId', post.category);
        formData.append('typeId', post.type);
        const picture = $('#picture')[0].files[0]
        formData.append('pictureFile', picture);
        let url = '/api/posts/' + postId;
        if(picture === undefined || picture === null){
            url = '/api/posts/update-no-picture/' + postId;
        }

        $.ajax({
            url: url,
            type: 'PUT',
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                window.location.href = "/admin/posts";
            },
            error: function(error) {
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Something went wrong!",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });

    }
}

function getDataInput(isUpdate) {
    let contentValue = CKEDITOR.instances['content'].getData();

    const post = {
        content: contentValue,
        // status: parseInt($('#status').val(), 10),
        title: $('#title').val(),
        picture: $('#picture').val(),
        briefContent: $('#brief-content').val(),
        // rate: $('#rate').val(),
        category: parseInt($('#categoryId2').val(), 10),
        type: parseInt($('#typeId').val(), 10)
    };

    if(post.title === ''){
        $('#title-message').text("Title is required");
        isValidData = false;
    }else{
        $('#title-message').text("");
    }

    if(post.briefContent === ''){
        $('#brief-content-message').text("Brief Content is required");
        isValidData = false;
    }else{
        $('#brief-content-message').text("");
    }

    if(post.picture === '' && !isUpdate){
        $('#picture-message').text("Picture is required");
        isValidData = false;
    }else{
        $('#picture-message').text("");
    }

    if(post.content === ''){
        $('#content-message').text("Content is required");
        isValidData = false;
    }else{
        if(post.content.length > 5000){
            $('#content-message').text("Content Length must be <= 5000");
            isValidData = false;
        }else{
            $('#content-message').text("");
        }
    }

    return post;
}
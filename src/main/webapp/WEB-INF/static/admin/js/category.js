
$(document).ready(function() {
    loadCategories();
    $('#searchCategoryName').on('input', searchCategoryByName);
    // Bắt sự kiện click cho nút Edit
    $(document).on('click', '.btn-edit', function() {
        const categoryId = $(this).data('id');
        $('#message').text("");
        getCategoryById(categoryId); // Gọi hàm editCategory với categoryId
    });

    // Bắt sự kiện click cho nút Delete
    $(document).on('click', '.btn-delete', function() {
        const categoryId = parseInt($(this).data('id'), 10);
        deleteCategory(categoryId); // Gọi hàm deleteCategory với categoryId
    });

    $(document).on('click', '#btnSave', function() {
        if($(this).text() === "Add New Category"){
            addNewCategory();
        }
        else{
            editCategory();
        }
    });

    $(document).on('click', '#btnAddNewCategory', function() {
        $('#exampleModalLabel').text("Add New Category");
        $('#btnSave').text("Add New Category");
        $('#categoryName').val("");
        $('#message').text("");
        $('#myModal').modal('show');
    });

});

function loadCategories() {
    $.ajax({
        url: '/api/categories',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#data').empty();

            data.forEach(function(category) {
                const formattedDate = formatDate(category.createAt);
                $('#data').append(
                    `<tr>
                        <td>${category.categoryId}</td>
                        <td>${category.nameCategory}</td>
                        <td>${formattedDate}</td>
                        <td>
                            <button class="btn btn-warning btn-edit" data-id="${category.categoryId}">Edit</button>
                            <button class="btn btn-danger btn-delete text-white" data-id="${category.categoryId}">Delete</button>
                        </td>
                    </tr>`
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

function searchCategoryByName(){
    let searchText = $('#searchCategoryName').val().toLowerCase().trim();

    $('#data tr').each(function() {
        let categoryText = $(this).find('td:eq(1)').text().toLowerCase();

        if (categoryText.includes(searchText)) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}

function addNewCategory(){
    // Dữ liệu category bạn muốn gửi đi, có thể là dữ liệu nhập từ người dùng hoặc dữ liệu từ các trường khác
    var categoryData = {
        nameCategory: $('#categoryName').val().trim(),
    };

    // Gọi Ajax để gửi dữ liệu lên server
    $.ajax({
        url: '/api/categories',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(categoryData),
        dataType: 'json',
        success: function(response) {
            console.log('Category created:', response);
            const formattedDate = formatDate(response.createAt);
            $('#data').append(
                `<tr>
                    <td>${response.categoryId}</td>
                    <td>${response.nameCategory}</td>
                    <td>${formattedDate}</td>
                    <td>
                        <button class="btn btn-warning btn-edit" data-id="${response.categoryId}">Edit</button>
                        <button class="btn btn-danger btn-delete text-white" data-id="${response.categoryId}">Delete</button>
                    </td>
                </tr>`
            );
            $('#myModal').modal('hide');
        },
        error: function(xhr, status, error) {
            console.error('Error creating category:', error);
            $('#message').text("Category Name is already exists");
        }
    });

}

function  editCategory(){
    var categoryData = {
        categoryId: $("#categoryId").val(),
        nameCategory: $("#categoryName").val()
    };

    // Gọi Ajax để gửi dữ liệu lên server
    $.ajax({
        url: '/api/categories/' + categoryData.categoryId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(categoryData),
        dataType: 'json',
        success: function(response) {
            console.log('Category updated:', response);
            $('#data tr').each(function() {
                let categoryId = parseInt($(this).find('td:eq(0)').text(), 10);
                if (response.categoryId === categoryId) {
                    $(this).find('td:eq(1)').text(response.nameCategory);
                    return false;
                }
            });
            $('#myModal').modal('hide');
        },
        error: function(xhr, status, error) {
            console.error('Error updating category:', error);
            $('#message').text("Category Name is already exists");
        }
    });

}

function getCategoryById(id){
    $.ajax({
        url: `/api/categories/${id}`, // Thay '123' bằng categoryId cụ thể bạn muốn lấy thông tin
        type: 'GET',
        dataType: 'json',
        success: function(category) {
            console.log('Category details:', category);
            $('#exampleModalLabel').text("Edit Category");
            $('#btnSave').text("Edit Category");
            $('#categoryName').val(`${category.nameCategory}`);
            $('#categoryId').val(`${category.categoryId}`);
            $('#myModal').modal('show');
        },
        error: function(xhr, status, error) {
            console.log(error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "Category Not Found",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

}

function deleteCategory(id){
    if(confirm("Do you want to delete this category?") === true){
        $.ajax({
            url: '/api/categories/' + id,
            type: 'DELETE',
            success: function(response) {
                console.log('Category deleted:', response);
                $('#data tr').each(function() {
                    let categoryId = parseInt($(this).find('td:eq(0)').text(), 10);
                    if (id === categoryId) {
                        $(this).remove();
                        return false;
                    }
                });
                $('#myModal').modal('hide');
            },
            error: function(xhr, status, error) {
                console.error('Error deleting category:', error);
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Category Not Found",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
}
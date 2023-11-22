
$(document).ready(function() {
    loadTypes();
    $('#searchTypeName').on('input', searchTypeByName);

    $(document).on('click', '.btn-edit', function() {
        const TypeId = $(this).data('id');
        $('#message').text("");
        getTypeById(TypeId);
    });

    $(document).on('click', '.btn-delete', function() {
        const typeId = parseInt($(this).data('id'), 10);
        deleteType(typeId);
    });

    $(document).on('click', '#btnSave', function() {
        if($(this).text() === "Add New Type"){
            addNewType();
        }
        else{
            editType();
        }
    });

    $(document).on('click', '#btnAddNewType', function() {
        $('#exampleModalLabel').text("Add New Type");
        $('#btnSave').text("Add New Type");
        $('#typeName').val("");
        $('#message').text("");
        $('#myModal').modal('show');
    });


});

function loadTypes() {
    $.ajax({
        url: '/api/types',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#data').empty();

            data.forEach(function(type) {
                const formattedDate = formatDate(type.createAt);
                $('#data').append(
                    `<tr>
                        <td>${type.typeId}</td>
                        <td>${type.typeName}</td>
                        <td>${formattedDate}</td>
                        <td>
                            <button class="btn btn-warning btn-edit" data-id="${type.typeId}">Edit</button>
                            <button class="btn btn-danger btn-delete text-white" data-id="${type.typeId}">Delete</button>
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

function searchTypeByName(){
    let searchText = $('#searchTypeName').val().toLowerCase().trim();

    $('#data tr').each(function() {
        let typeName = $(this).find('td:eq(1)').text().toLowerCase();

        if (typeName.includes(searchText)) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}

function addNewType(){
    var typeData = {
        typeName: $('#typeName').val().trim(),
    };

    // Gọi Ajax để gửi dữ liệu lên server
    $.ajax({
        url: '/api/types',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(typeData),
        dataType: 'json',
        success: function(response) {
            console.log('Type created:', response);
            const formattedDate = formatDate(response.createAt);
            $('#data').append(
                `<tr>
                    <td>${response.typeId}</td>
                    <td>${response.typeName}</td>
                    <td>${formattedDate}</td>
                    <td>
                        <button class="btn btn-warning btn-edit" data-id="${response.typeId}">Edit</button>
                        <button class="btn btn-danger btn-delete text-white" data-id="${response.typeId}">Delete</button>
                    </td>
                </tr>`
            );
            $('#myModal').modal('hide');
        },
        error: function(xhr, status, error) {
            console.error('Error creating type:', error);
            $('#message').text("Type Name is already exists");
        }
    });

}

function  editType(){
    var typeData = {
        typeId: $("#typeId").val(),
        typeName: $("#typeName").val()
    };

    // Gọi Ajax để gửi dữ liệu lên server
    $.ajax({
        url: '/api/types/' + typeData.typeId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(typeData),
        dataType: 'json',
        success: function(response) {
            console.log('Type updated:', response);
            $('#data tr').each(function() {
                let typeId = parseInt($(this).find('td:eq(0)').text(), 10);
                if (response.typeId === typeId) {
                    $(this).find('td:eq(1)').text(response.typeName);
                    return false;
                }
            });
            $('#myModal').modal('hide');
        },
        error: function(xhr, status, error) {
            console.error('Error updating type:', error);
            $('#message').text("Type Name is already exists");
        }
    });

}

function getTypeById(id){
    $.ajax({
        url: `/api/types/${id}`,
        type: 'GET',
        dataType: 'json',
        success: function(type) {
            console.log('type details:', type);
            $('#exampleModalLabel').text("Edit Type");
            $('#btnSave').text("Edit Type");
            $('#typeName').val(`${type.typeName}`);
            $('#typeId').val(`${type.typeId}`);
            $('#myModal').modal('show');
        },
        error: function(xhr, status, error) {
            console.log(error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "Type Not Found",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

}

function deleteType(id){
    if(confirm("Do you want to delete this type?") === true){
        $.ajax({
            url: '/api/types/' + id,
            type: 'DELETE',
            success: function(response) {
                console.log('Type deleted:', response);
                $('#data tr').each(function() {
                    let typeId = parseInt($(this).find('td:eq(0)').text(), 10);
                    if (id === typeId) {
                        $(this).remove();
                        return false;
                    }
                });
                $('#myModal').modal('hide');
            },
            error: function(xhr, status, error) {
                console.error('Error deleting type:', error);
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "Type Not Found",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
}
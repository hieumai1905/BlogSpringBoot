
$(document).ready(function() {
    loadUsers();
    $('#searchUsers').on('input', searchUsers);

    $(document).on('click', '.btn-edit', function() {
        const userId = $(this).data('id');
        $('#fullName-message').text('');
        $('#email-message').text('');
        $('#password-message').text('');
        $('#birthday-message').text('');
        getUserById(userId);
    });

    $(document).on('click', '.btn-delete', function() {
        const userId = parseInt($(this).data('id'), 10);
        deleteUser(userId);
    });

    $(document).on('click', '#btnSave', editUser);

});

function loadUsers() {
    $.ajax({
        url: '/api/accounts',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#data').empty();

            data.forEach(function(account) {
                const formattedDate = formatDate(account.createAt);
                const dob = formatDate(account.birthday);
                const password = '****' + account.password.slice(-2);
                $('#data').append(
                    `<tr>
                        <td>${account.accountId}</td>
                        <td>${account.fullname}</td>
                        <td>${account.email}</td>
                        <td>${password}</td>
                        <td>${dob}</td>
                        <td>${account.gender == 1 ? 'Male' : 'Female'}</td>
                        <td>${account.role == 1 ? 'Admin' : 'User'}</td>
                        <td>${account.status == 1 ? 'Active' : 'InActive'}</td>
                        <td>${formattedDate}</td>
                        <td>
                            <button class="btn btn-warning btn-edit" data-id="${account.accountId}">Edit</button>
                            <button class="btn btn-danger btn-delete text-white" data-id="${account.accountId}">Delete</button>
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

function searchUsers(){
    let searchText = $('#searchUsers').val().toLowerCase().trim();

    $('#data tr').each(function() {
        let fullName = $(this).find('td:eq(1)').text().toLowerCase();
        let email = $(this).find('td:eq(2)').text().toLowerCase();

        if (fullName.includes(searchText) || email.includes(searchText)) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}

function editUser(){

    let account = {
        accountId: $('#userId').val(),
        fullname: $('#fullName').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        birthday: $('#birthday').val(),
        gender: $('#gender').val(),
        role: $('#role').val(),
        createAt: $('#createAt').val()
    };
    let isValid = true;
    if(!validateEmail(account.email)){
        $('#email-message').text('Email is not valid');
        isValid = false;
    }else {
        $('#email-message').text('');
    }

    if(account.fullname === ''){
        $('#fullName-message').text('Full name is required');
        isValid = false;
    }else{
        $('#fullName-message').text('');
    }

    if (account.password.length < 6){
        $('#password-message').text('Length of password must be at least 6 characters');
        isValid = false;
    }else{
        $('#password-message').text('');
    }

    if(account.birthday === ''){
        $('#birthday-message').text('Birthday is required');
        isValid = false;
    }else {
        let currentDate = moment(new Date());
        let selectedDate = new Date(account.birthday);

        if ((moment(selectedDate)).format('YYYY-MM-DD') >= currentDate.format('YYYY-MM-DD')) {
            $('#birthday-message').text('Birthday must be before today');
            isValid = false;
        } else {
            $('#birthday-message').text('');
        }
    }

    if(!isValid){
        return;
    }

    // Gọi Ajax để gửi dữ liệu lên server
    $.ajax({
        url: '/api/accounts/' + account.accountId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(account),
        dataType: 'json',
        success: function(response) {
            console.log('account updated:', response);
            $('#data tr').each(function() {
                let userId = parseInt($(this).find('td:eq(0)').text(), 10);
                if (response.accountId === userId) {
                    const dob = formatDate(response.birthday);
                    const password = '****' + response.password.slice(-2);

                    $(this).find('td:eq(1)').text(response.fullname);
                    $(this).find('td:eq(2)').text(response.email);
                    $(this).find('td:eq(3)').text(`${password}`);
                    $(this).find('td:eq(4)').text(`${dob}`);
                    $(this).find('td:eq(5)').text(response.gender === 1 ? 'Male' : 'Female');
                    $(this).find('td:eq(6)').text(response.role === 1 ? 'Admin' : 'User');

                    return false;
                }
            });
            $('#myModal').modal('hide');
        },
        error: function(xhr, status, error) {
            console.error('Error updating user:', error);
        }
    });

}

function getUserById(id){
    $.ajax({
        url: `/api/accounts/${id}`,
        type: 'GET',
        dataType: 'json',
        success: function(account) {
            console.log('account details:', account);
            $('#exampleModalLabel').text("Edit User");
            $('#btnSave').text("Edit User");

            $('#userId').val(`${account.accountId}`);
            $('#fullName').val(`${account.fullname}`);
            $('#email').val(`${account.email}`);
            $('#password').val(`${account.password}`);
            let dob = moment(account.birthday);
            console.log(dob.format('YYYY-MM-DD'));
            $('#birthday').val(dob.format('YYYY-MM-DD'));
            $('#role').val(`${account.role}`);
            $('#gender').val(`${account.gender}`);
            $('#createAt').val(`${account.createAt}`);

            $('#myModal').modal('show');
        },
        error: function(xhr, status, error) {
            console.log(error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "User Not Found",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });

}

function deleteUser(id){
    if(confirm("Do you want to delete this user?") === true){
        $.ajax({
            url: '/api/accounts/' + id,
            type: 'DELETE',
            success: function(response) {
                console.log('user deleted:', response);
                $('#data tr').each(function() {
                    let userId = parseInt($(this).find('td:eq(0)').text(), 10);
                    if (id === userId) {
                        $(this).remove();
                        return false;
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('Error deleting user:', error);
                Swal.fire({
                    position: "top-end",
                    icon: "error",
                    title: "User Not Found",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
}
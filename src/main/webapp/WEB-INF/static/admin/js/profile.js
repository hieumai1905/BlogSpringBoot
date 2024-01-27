let birthday = $('#birthdayData').val();
const dob = formatDate(birthday);

let birthdayCurrent = $('#birthdayCurrent');
birthdayCurrent.val(dob);

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate;
}

function validateUpdateAccount(){
    $("#error-server").remove();
    let fullname = $('#fullname-update');
    let password = $('#password-update');
    let error = $('#update-error');
    let birthday = $('#birthdayCurrent');
    error.show();

    if (!validateName(fullname.val())) {
        error.text('Name is not empty');
        fullname.focus();
        return false;
    }

    if (birthday.val() === '') {
        error.text('Birthday is not empty');
        birthday.focus();
        return false;
    }

    if (!validateName(password.val())) {
        error.text('Password is not empty');
        password.focus();
        return false;
    }
    return true;

}
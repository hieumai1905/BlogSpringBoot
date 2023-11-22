function checkRegister() {
    let fullname = $('#fullname-register');
    let email = $('#email-register');
    let password = $('#password-register');
    let repassword = $('#repassword-register');
    let error = $('#register-error');
    let birthday = $('#birthday-register');
    let gender = $('#gender-register');

    if (!validateName(fullname.val())) {
        error.text('Name is not empty');
        fullname.focus();
        return false;
    }
    if (!validateEmail(email.val())) {
        error.text('Email is not valid');
        email.val('');
        email.focus();
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

    if (!validateName(repassword.val())) {
        error.text('Confirm password is not empty');
        repassword.focus();
        return false;
    }

    if (!validatePassword(repassword.val()) || !validatePassword(password.val())) {
        error.text('Password must be at least 6 characters long.');
        password.val('');
        repassword.val('');
        password.focus();
        return false;
    }
    if (password.val() !== repassword.val()) {
        error.text('Confirm password is not match');
        password.val('');
        repassword.val('');
        password.focus();
        return false;
    }
    return true;
}
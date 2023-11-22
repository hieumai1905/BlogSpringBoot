function checkLogin(){
    let email = $('#email-login');
    let password = $('#password-login');
    let error = $('#login-error');

    if(!validateName(email.val())){
        error.text('Email is not empty');
        email.focus();
        return false;
    }
    if(!validateName(password.val())){
        error.text('Password is not empty');
        password.focus();
        return false;
    }
    if(!validateEmail(email.val())){
        error.text('Email is not valid');
        email.val('');
        password.val('');
        email.focus();
        return false;
    }
    if(!validatePassword(password.val())){
        error.text('Password must be at least 6 characters long.');
        password.val('');
        password.focus();
        return false;
    }
    return true;
}
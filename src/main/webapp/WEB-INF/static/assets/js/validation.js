function validateName(name) {
    if (name === '') {

        return false;
    }

    return true;
}

function validateEmail(email) {
    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!emailRegex.test(email)) {
        // alert('Please enter a valid email address.');
        return false;
    }

    return true;
}

function validatePhone(phone) {
    var phoneRegex = /^\d{10}$/;

    if (phone === '') {
        alert('Please enter your phone number.');
        return false;
    }

    if (!phoneRegex.test(phone)) {
        alert('Please enter a valid 10-digit phone number.');
        return false;
    }

    return true;
}

function validateCode(code) {
    var phoneRegex = /^\d{6}$/;

    if (!phoneRegex.test(code)) {
        // alert('Please enter a valid 10-digit code number.');
        return false;
    }

    return true;
}

function validateImage(image) {
    var allowedExtensions = ['jpg', 'jpeg', 'png', 'gif'];

    if (image === '') {
        alert('Please select an image file.');
        return false;
    }

    var fileExtension = image.substr(image.lastIndexOf('.') + 1).toLowerCase();
    if (!allowedExtensions.includes(fileExtension)) {
        alert('Please select a valid image file (jpg, jpeg, png, gif).');
        return false;
    }

    return true;
}

function validateVideo(video) {
    var allowedExtensions = ['mp4', 'avi', 'mov'];

    if (video === '') {
        alert('Please select a video file.');
        return false;
    }

    var fileExtension = video.substr(video.lastIndexOf('.') + 1).toLowerCase();
    if (!allowedExtensions.includes(fileExtension)) {
        alert('Please select a valid video file (mp4, avi, mov).');
        return false;
    }

    return true;
}

function validatePassword(password) {
    if (password.length < 6) {
        // alert('Password must be at least 6 characters long.');
        return false;
    }

    return true;
}

function validateDOB(dob) {
    if (dob === '') {
        alert('Please select your date of birth.');
        return false;
    }

    var selectedDate = new Date(dob);
    var currentDate = new Date();

    if (selectedDate > currentDate) {
        alert('Please select a valid date of birth.');
        return false;
    }

    return true;
}
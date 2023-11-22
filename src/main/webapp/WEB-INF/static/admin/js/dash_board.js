$(document).ready(function() {
    getNumbersOfAccount();
    getNumbersOfPost();
    getNumbersOfCategory();
    getNumbersOfType();
});


function getNumbersOfAccount(){
    $.ajax({
        url: '/api/accounts',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#numberOfAccounts').text(data.length);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function getNumbersOfPost(){
    $.ajax({
        url: '/api/posts',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#numberOfPosts').text(data.length);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function getNumbersOfCategory(){
    $.ajax({
        url: '/api/categories',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#numberOfCategories').text(data.length);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function getNumbersOfType(){
    $.ajax({
        url: '/api/types',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            console.log(data);
            $('#numberOfTypes').text(data.length);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}


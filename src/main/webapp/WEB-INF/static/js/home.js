let pageIndex = 1;
let pageSize = 9;
let totalElements = 0;
let loadMore = true;
let accounts = [];
$(window).ready(function () {
    loadUsers();
    loadCategories();
    loadTypes();
    loadPosts();


    $('#btnLoad').on('click', loadPostByPageIndex);

    $(document).on('click', '.category-li', function () {
        const categoryId = parseInt($(this).attr('id'), 10);
        getPostsByCategoryId(categoryId);
    });

    $(document).on('click', '.type-li', function () {
        const typeId = parseInt($(this).attr('id'), 10);
        getPostsByTypeId(typeId);
    });

    $('#searchText').on('input', getPostByTitle);

});

function loadUsers() {
    $.ajax({
        url: '/api/accounts',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            accounts = data;
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

function loadPostByPageIndex() {
    if (loadMore) {
        $(this).text('Load More');
        pageIndex++;
    } else {
        $(this).text('Load Less');
        pageIndex--;
    }
    var data = $('#data').children();
    if (data.length <= 9)
        return;
    data.each(function (index) {
        if (index < pageIndex * pageSize) {
            if ($(this).hasClass('d-none'))
                $(this).removeClass('d-none');
        } else {
            if (!$(this).hasClass('d-none'))
                $(this).addClass('d-none');
        }
    });

    if (pageIndex * pageSize >= totalElements || pageIndex === 1) {
        loadMore = !loadMore;
        $(this).text(loadMore ? 'Load More' : 'Load Less');
    }
}

function loadPosts() {
    $.ajax({
        url: '/api/posts',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            showPostsToScreen(data);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

function getPostsByCategoryId(categoryId) {
    $.ajax({
        url: `/api/posts/categories/${categoryId}`,
        type: 'GET',
        success: function (response) {
            // Xử lý dữ liệu trả về tại đây
            console.log(response);
            showPostsToScreen(response);
        },
        error: function (error) {
            console.log(error);
            $('#data').empty();
        }
    });
}

function getPostsByTypeId(typeId) {
    $.ajax({
        url: `/api/posts/types/${typeId}`,
        type: 'GET',
        success: function (response) {
            // Xử lý dữ liệu trả về tại đây
            console.log(response);
            showPostsToScreen(response);
        },
        error: function (error) {
            console.log(error);
            $('#data').empty();
        }
    });
}

function getPostByTitle() {
    const text = $('#searchText').val();
    if (text === '') {
        loadPosts();
        return;
    }
    $.ajax({
        url: '/api/posts/search/' + encodeURIComponent(text),
        type: 'GET',
        success: function (response) {
            // Xử lý dữ liệu trả về ở đây
            console.log(response);
            showPostsToScreen(response);
        },
        error: function (error) {
            // Xử lý lỗi ở đây
            console.error(error);
            $('#data').empty();
        }
    });

}

function showPostsToScreen(data) {
    $('#data').empty();
    totalElements = 0;
    pageIndex = 1;

    if (data.length > 0)
        data = data.filter(p => p.status === 1);

    data.forEach(function (post) {
        totalElements++;
        const formattedDate = formatDate(new Date(post.createAt));
        const picture = '../static' + post.picture;
        const hiddenClass = totalElements > pageSize ? 'd-none' : '';
        let author = '';
        let link = '';
        for (let i = 0; i < accounts.length; i++) {
            if (accounts[i].accountId === post.accountId) {
                author = accounts[i].fullname;
                link = '/users?id=' + accounts[i].accountId;
                break;
            }
        }
        $('#data').append(
            `<div id="p-${totalElements}" class="col-lg-4 col-md-4 col-sm-4 ${hiddenClass}">
                <div class="categories__post__item">
                    <div class="categories__post__item__pic small__item set-bg"
                         style="background-image: url('${picture}');">
                        <div class="post__meta">
                            <h4>${formattedDate.day}</h4>
                            <span>${formattedDate.month}</span>
                        </div>
                    </div>
                    <div class="categories__post__item__text">
                        <span class="post__label">Recipe</span>
                        <h3><a href="/blog/single-post?id=${post.postId}">${post.title}</a></h3>
                        <ul class="post__widget">
<!--                            <li>by <span>${author}</span></li>-->
                            <li>by <a href="${link}">${author}</a></li>
                        </ul>
                        <p>${post.briefContent}</p>
                    </div>
                </div>
            </div>`
        );
    });
}

function formatDate(date) {
    const months = [
        'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
        'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
    ];

    const day = ('0' + date.getDate()).slice(-2); // Lấy ngày với định dạng 2 chữ số
    const monthIndex = date.getMonth(); // Lấy chỉ số của tháng
    const monthName = months[monthIndex]; // Lấy tên tháng từ mảng months

    const formattedDate = {
        day: day,
        month: monthName
    };

    return formattedDate;
}

function loadCategories() {
    $.ajax({
        url: '/api/categories',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#category-ul').empty();

            data.forEach(function (category) {
                $('#category-ul').append(
                    `<a class="category-li" id="${category.categoryId}" style="text-align: left; padding: 10px;">${category.nameCategory}</a>`
                );
            });
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

function loadTypes() {
    $.ajax({
        url: '/api/types',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#type-ul').empty();

            data.forEach(function (type) {
                $('#type-ul').append(
                    `<a class="type-li" id="${type.typeId}" style="text-align: left; padding: 10px;">${type.typeName}</a>`
                );
            });
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

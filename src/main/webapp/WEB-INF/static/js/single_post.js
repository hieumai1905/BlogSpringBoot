let oldComment = '';
$(window).ready(function() {
   const postId = $('#postId').val();
   getPostDetails(postId);
   getComments(postId);
   getRatesByPostId();

   $('#btnComment').on('click', function () {
      saveComment(false, null, null);
   });

   // Bắt sự kiện click trên nút Cancel để chuyển trở lại từ textarea sang phần tử p ban đầu
   $(document).on('click', '.edit-cancel', function() {
      let commentId = $(this).data('comment-id');

      // Thay thế textarea bằng phần tử p với nội dung ban đầu
      $(`#edit-comment-textarea-${commentId}`)
          .replaceWith(`<p id="comment-content-${commentId}">
                ${oldComment}
            </p>`);

      // Xóa nút Edit và nút Cancel
      $(this).remove();
      $(`.edit-save[data-comment-id="${commentId}"]`).remove();
      $('#edit-icon-'+commentId).show();
   });

   // Bắt sự kiện click trên nút Edit để lưu nội dung mới từ textarea
   $(document).on('click', '.edit-save', function() {
      let commentId = $(this).data('comment-id');
      let textareaContent = $(`#edit-comment-textarea-${commentId}`).val().trim();

      saveComment(true, parseInt(commentId, 10), textareaContent);

      // Thay thế textarea bằng phần tử p với nội dung mới từ textarea
      $(`#edit-comment-textarea-${commentId}`)
          .replaceWith(
              `<p id="comment-content-${commentId}">${textareaContent === '' ? oldComment : textareaContent}</p>`
          );

      // Xóa nút Edit và nút Cancel
      $(this).remove();
      $(`.edit-cancel[data-comment-id="${commentId}"]`).remove();
      $('#edit-icon-'+commentId).show();
   });

   $(".btnrating").on('click',(function(e) {

      var selected_value = $(this).attr("data-attr");
      showRating(selected_value);
      createOrUpdateRate(parseInt(selected_value, 10));
   }));

   $(document).on('click', '.delete-comment', function(e) {
      let commentId = $(this).attr('id').split('-')[2];
      deleteComment(commentId);
   });

});

function showRating(selected_value) {
   var previous_value = $("#selected_rating").val();

   $("#selected_rating").val(selected_value);
   console.log(selected_value);
   $(".selected-rating").empty();
   $(".selected-rating").html(selected_value);

   for (i = 1; i <= selected_value; ++i) {
      $("#rating-star-"+i).toggleClass('btn-warning');
      $("#rating-star-"+i).toggleClass('btn-default');
   }

   for (ix = 1; ix <= previous_value; ++ix) {
      $("#rating-star-"+ix).toggleClass('btn-warning');
      $("#rating-star-"+ix).toggleClass('btn-default');
   }
}

function getPostDetails(postId){
   $.ajax({
      url: '/api/posts/' + postId,
      type: 'GET',
      dataType: 'json',
      success: function(response) {
         console.log('Post data:', response);
         const formattedDate = formatDate(new Date(response.createAt));
         const imageUrl = '../static' + response.picture;
         getUserById(response.accountId, '#full-name');

         $('#picture').css('background-image', 'url("' + imageUrl + '")');
         $('#day').text(formattedDate.day);
         $('#month').text(formattedDate.month);
         $('#title').text(response.title);
         $('#brief-content').text(response.briefContent);
         $('#content').html(response.content);
      },
      error: function(xhr, status, error) {
         console.error('Error:', error);
         // Swal.fire({
         //    position: "top-end",
         //    icon: "error",
         //    title: "Post Not Found!",
         //    showConfirmButton: false,
         //    timer: 1500
         // });
      }
   });

}

function saveComment(isUpdate, commentId, content){
   const postId = $('#postId').val();
   const commentRequest = {
      content: $('#comment').val().trim(),
      postId: postId,
      commentId: null
   }
   let url = '/api/comments/posts/' + postId;
   if(isUpdate){
      commentRequest.content = content;
      commentRequest.commentId = commentId;
      url = '/api/comments/' + commentId;
   }

   if(commentRequest.content === ''){
      return;
   }

   $.ajax({
      url: url,
      type: isUpdate ? 'PUT' : 'POST',
      contentType: 'application/json',
      data: JSON.stringify(commentRequest),
      success: function(response) {
         getComments(postId);
         $('#comment').val('');
      },
      error: function(xhr, status, error) {
         console.error('Error creating comment:', error);
      }
   });

}

function deleteComment(commentId){
   if(confirm("Do you want to delete this comment?") === true){
      $.ajax({
         url: `/api/comments/${commentId}`,
         type: 'DELETE',
         success: function(response) {
            console.log('Comment deleted successfully');
            getComments($('#postId').val());
         },
         error: function(xhr, status, error) {
            console.error('Error deleting comment:', error);
         }
      });
   }
}

function getComments(postId){
   $.ajax({
      type: "GET",
      url: "/api/comments/posts/" + postId,
      success: function(response) {
         if(response !== undefined && response !== null){
            response.sort(function(a, b) {
               return new Date(b.commentAt) - new Date(a.commentAt);
            });

            console.log(response);
            $('.numberOfComments').text(response.length);
            loadCommentToScreen(response);
         }
      },
      error: function(xhr, status, error) {
         console.error(error);
      }
   });
}
function loadCommentToScreen(data){
   $('#comments').empty();

   data.forEach(function (comment){
      getUserById(comment.accountId, `#u-${comment.commentId}`);

      let editIcon =
          `<ul>
             <li><a id="edit-icon-${comment.commentId}" class="edit-comment"><i class="fa fa-edit"></i></a></li>
             <li><a id="delete-icon-${comment.commentId}" class="delete-comment"><i class="fa fa-trash"></i></a></li>
           </ul>`;

      if(parseInt($('#userId').val(), 10) !== comment.accountId){
         editIcon = ``;
      }

      $('#comments').append(`<div class="single-post__comment__item">
           <div class="single-post__comment__item__pic">
              <img src="https://icons.iconarchive.com/icons/papirus-team/papirus-status/512/avatar-default-icon.png" alt="">
            </div>
            <div class="single-post__comment__item__text">
              <h5 id="u-${comment.commentId}"></h5>
              <span>${comment.commentAt}</span>
              <p id="comment-content-${comment.commentId}">${comment.content}</p>
              ${editIcon}
            </div>
          </div>`
      );
   });
   $('.edit-comment').on('click', function() {
      // Lấy ID của comment từ ID của phần tử click
      let commentId = $(this).attr('id').split('-')[2];

      // Lấy nội dung của phần tử p
      let commentContent = $(`#comment-content-${commentId}`).text().trim();

      // Tạo textarea với nội dung của phần tử p
      let textarea = `<textarea class="form-control" id="edit-comment-textarea-${commentId}">${commentContent}</textarea>`;
      oldComment = commentContent;
      // Tạo nút Edit và nút Cancel
      let editButton = `<button class="btn btn-warning edit-save ml-2 mr-2" data-comment-id="${commentId}">Edit</button>`;
      let cancelButton = `<button class="btn btn-danger edit-cancel" data-comment-id="${commentId}">Cancel</button>`;

      // Thay thế phần tử p bằng textarea và thêm nút Edit và nút Cancel
      $(`#comment-content-${commentId}`).replaceWith(textarea);
      $(`#edit-icon-${commentId}`).closest('li').append(editButton + cancelButton);
      $(this).hide();
   });
}

function getUserById(id, tagId){
   $.ajax({
      url: '/api/accounts/' + id,
      type: 'GET',
      dataType: 'json',
      success: function(response) {
         $(tagId).text(response.fullname);
      },
      error: function(xhr, status, error) {
         console.error(error);
      }
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

function createOrUpdateRate(rateValue) {
   const rateData = {
      postId: parseInt($('#postId').val(), 10),
      value: rateValue
   };

   $.ajax({
      url: '/api/rates/posts/' + rateData.postId,
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(rateData),
      success: function(response) {
         console.log(response);
         getRatesByPostId();
      },
      error: function(xhr, status, error) {
         console.error(error);
      }
   });
}

function getRatesByPostId() {
   const postId = parseInt($('#postId').val(), 10);
   $.ajax({
      url: '/api/rates/posts/' + postId,
      type: 'GET',
      success: function(response) {
         console.log(response);
         if (response && response.length > 0) {
            let totalValue = 0;

            for (let i = 0; i < response.length; i++) {
               totalValue += response[i].value;
               if(response[i].accountId === parseInt($('#userId').val(), 10)){
                  showRating(response[i].value);
               }
            }

            const averageValue = totalValue / response.length;
            $('#ratePost').text(averageValue + '/ 5');
            return false;
         }
      },
      error: function(xhr, status, error) {
         console.error(error);
      }
   });
}
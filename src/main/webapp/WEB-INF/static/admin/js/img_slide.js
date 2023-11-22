

$(window).ready(function() {
   loadImages();

   $('#btnCreate').on('click', function(){
       $('#exampleModalLabel').text("Create Image Slide");
       $('#message').text("");
       $('#preview-img').attr('src', '');
       $('#description').val('');
       $('#btnSave').text("Create Image Slide");
       $('#myModal').modal('show');
   });

   $('#imgFile').change(previewImg);

    $(document).on('click', '.btn-edit', function() {
        const imgId = $(this).data('id');
        $('#message').text("");
        $('#imgFile').val('');
        $('#description').val('');
        $('#exampleModalLabel').text("Edit Image Slide");
        $('#btnSave').text("Edit Image Slide");
        $('#myModal').modal('show');
        getImageById(imgId);
    });

    // Bắt sự kiện click cho nút Delete
    $(document).on('click', '.btn-delete', function() {
        const imgId = parseInt($(this).data('id'), 10);
        deleteImage(imgId);
    });

   $('#btnSave').on('click', function(){
      const text = $(this).text().trim();
      if(text === 'Create Image Slide'){
          createImgSlide();
      }else{
          editImgSlide();
      }
   });
});

function loadImages() {

    $.ajax({
        url: '/api/image-slides',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            showImagesToScreen(data);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}

function showImagesToScreen(data){

    $('#data').empty();

    data.forEach(function(image) {
        $('#data').append(
            `<tr>
                <td>${image.imageId}</td>
                <td>${image.link}</td>
                <td>${image.description}</td>
                <td>
                    <a class="btn btn-warning btn-edit" data-id="${image.imageId}">Edit</a>
                    <button class="btn btn-danger btn-delete text-white" data-id="${image.imageId}">Delete</button>
                </td>
            </tr>`
        );
    });
}

function previewImg(){
    const file = this.files[0];
    if (file){
        let reader = new FileReader();
        reader.onload = function(event){
            $('#preview-img').attr('src', event.target.result);
        }
        reader.readAsDataURL(file);
    }else{
        $('#preview-img').attr('src', '../static/' + $('#old-img').val());
    }
}

function createImgSlide(){
    const img = $('#imgFile');
    if(img.val() === ''){
        $('#message').text("image slide is required");
        return false;
    }
    const formData = new FormData();
    formData.append('link', '');
    formData.append('url', '');
    formData.append('description', $('#description').val());
    formData.append('imgFile', img[0].files[0]);


    $.ajax({
        url: '/api/image-slides',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            console.log(response);
            $('#data').append(
                `<tr>
                <td>${response.imageId}</td>
                <td>${response.link}</td>
                <td>${response.description}</td>
                <td>
                    <a class="btn btn-warning btn-edit" data-id="${response.imageId}">Edit</a>
                    <button class="btn btn-danger btn-delete text-white" data-id="${response.imageId}">Delete</button>
                </td>
            </tr>`
            );
            $('#myModal').modal('hide');
        },
        error: function(error) {
            console.error(error);
        }
    });

}

function editImgSlide(){
    const img = $('#imgFile');
    const imgId = $('#imgId').val().trim();
    let url = '/api/image-slides/update-no-img/'+imgId;
    const formData = new FormData();
    formData.append('imageId', imgId);
    formData.append('link', '');
    formData.append('url', '');
    formData.append('description', $('#description').val());

    if(img.val() !== '') {
        formData.append('imgFile', img[0].files[0]);
        url = '/api/image-slides/' + imgId;
    }


    $.ajax({
        url: url,
        type: 'PUT',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            console.log(response);
            $('#data tr').each(function() {
                let imgId = parseInt($(this).find('td:eq(0)').text(), 10);
                if (response.imageId === imgId) {
                    $(this).find('td:eq(1)').text(response.link);
                    $(this).find('td:eq(2)').text(response.description);
                    return false;
                }
            });
            $('#myModal').modal('hide');
        },
        error: function(error) {
            console.error(error);
        }
    });

}

function getImageById(imgId){
    $.ajax({
        url: '/api/image-slides/' + imgId,
        type: 'GET',
        success: function(response) {
            console.log(response);
            $('#imgId').val(response.imageId);
            $('#description').val(response.description);
            $('#preview-img').attr('src', '../static' + response.link);
            $('#old-img').val('../static' + response.link);
        },
        error: function(xhr, status, error) {
            console.error(error);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: "Image Not Found",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

function deleteImage(imgId){
    if(confirm('Do you want to delete this image slide?') === true){
        $.ajax({
            url: '/api/image-slides/' + imgId,
            type: 'DELETE',
            success: function(response) {
                console.log('Image deleted:', response);
                $('#data tr').each(function() {
                    let imageId = parseInt($(this).find('td:eq(0)').text(), 10);
                    if (imgId === imageId) {
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
                    title: "Image Not Found",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        });
    }
}
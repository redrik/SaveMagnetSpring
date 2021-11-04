$(document).ready(function() {
    $("#add_new_magnet").submit(function(evt) {
        evt.preventDefault();

        // PREPARE FORM DATA
        let formData = {
            title : $("#title").val(),
            magnet :  $("#magnet_link").val()
        }

        $.ajax({
            url: '/api/magnet/create',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
                let magnet = response.magnets[0];
                let magnetString = "{ Title: " + magnet.title + " }"
                let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message + '</strong> magnet\'s Info = ' + magnetString;
                                    '</div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong>' + response.message + '</strong>' + ' ,Error: ' + message.error + 
                                '</div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            }
        });
    });

    function resetUploadForm(){
        $("#title").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/magnets.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});

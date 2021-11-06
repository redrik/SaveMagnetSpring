$(document).ready(function () {
    $("#update_magnet_form").submit(function (evt) {
        evt.preventDefault();
        try {
            let magnetId = $("#magnet_id").val();

            // PREPARE FORM DATA
            let formData = {
                title: $("#magnet_title").val(),
                magnet: $("#magnet_magnet_link").val()
            }

            $.ajax({
                url: '/api/magnet/updatebyid/' + magnetId + "/",
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(formData),
                dataType: 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let magnet = response.magnets[0];
                    let magnetString = "{title:" + magnet.title + "}"
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong>' + response.message + '</strong> magnet\'s Info = ' + magnetString;
                    '</div>'

                    // change the updated data for magnet table record
                    $("#tr_" + magnetId + " td.td_title").text(magnet.title.toUpperCase());

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong>' + response.message + '</strong>' + ' ,Error: ' + message.error +
                        '</div>';

                    $("#response").empty();
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch (error) {
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function () {
        let id_of_button = (event.srcElement.id);
        let magnetId = id_of_button.split("_")[2];

        $.ajax({
            url: '/api/magnet/findone/' + magnetId,
            type: 'GET',
            success: function (response) {
                let magnet = response.magnets[0];
                $("#magnet_id").val(magnet.id);
                $("#magnet_title").val(magnet.title);
                $("#div_magnet_updating").css({"display": "block"});
            },
            error: function (error) {
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });
});
$(document).ready(function(){
    let magnetId = 0;

    $(document).on("click", "#div_magnet_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        magnetId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Do you want delete a magnet with id = " + magnetId + " ?");
        $("#model-delete-btn").css({"display": "inline"});
        $("#model-delete-all-btn").css({"display": "none"});
    });

    $(document).on("click", "button.btn_all_delete", function() {
        $("div.modal-body")
            .text("Do you want delete all magnets ?");
        $("#model-delete-btn").css({"display": "none"});
        $("#model-delete-all-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: '/api/magnet/deletebyid/' + magnetId,
            type: 'DELETE',
            success: function(response) {
                $("div.modal-body")
                    .text("Delete successfully a magnet with id = " + magnetId + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the magnet row on html page
                let row_id = "tr_" + magnetId;
                $("#" + row_id).remove();
                $("#div_magnet_updating").css({"display": "none"});
            },
            error: function(error){
                console.log(error);
                $("#div_magnet_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
    $(document).on("click", "#model-delete-all-btn", function() {
        $.ajax({
            url: '/api/magnet/deleteAllMagnets/',
            type: 'DELETE',
            success: function(response) {
                $("div.modal-body")
                    .text("Delete successfully all magnets!");

                $("#model-delete-all-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                location.reload();
            },
            error: function(error){
                console.log(error);
                $("#div_magnet_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
});
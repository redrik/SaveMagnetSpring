$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/api/magnet/retrieveinfos",
            success: function(response){
              $.each(response.magnets, (i, magnet) => {  

              /*  <button type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#myModal">
                Open modal
              </button>*/

                let deleteButton = '<button ' +
                                        'id=' +
                                        '\"' + 'btn_delete_' + magnet.id + '\"'+
                                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                                        '>&times</button>';

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + magnet.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            magnet.id +
                                            '</button>';
                
                let tr_id = 'tr_' + magnet.id;
                  const t = decodeURI(magnet.title.match("(?<=(&dn=))(.*?)(?=&|$)")[0]);
                  let magnetRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_title\">' + '<a href="'+ magnet.title +'">' + t + '</a>' + '</td>' +
                          '<td>' + deleteButton + '</td>' +
                          '</tr>';                
                $('#magnetTable tbody').append(magnetRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/magnets.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});
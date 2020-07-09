function shownew1(data){
    $('#p_new').html('<i class="fa fa-check-circle text-success mr-1" aria-hidden="true"></i>'+data);
    $('#myModal').modal('show');
    setTimeout(function(){
        $("#myModal").modal("hide")
    },1200);
}

function shownew2(data){
    $('#p_new').html('<i class="fa fa-minus-square text-warning mr-1" aria-hidden="true"></i>'+data);
    $('#myModal').modal('show');
    setTimeout(function(){
        $("#myModal").modal("hide")
    },1200);
}

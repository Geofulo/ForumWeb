$(document).ready(function(){            
    
    $(".btnEliminar").click(function(){
        var correo= $(this).attr("correo");
        var trUsuario = $(this).parent().parent();
        $.ajax({
            type: "post",
            url : "ServletEliminarUsuario",
            data : {
                correo:correo
            },
            success : function(data){ 
                $(trUsuario).fadeOut(500, function(){
                    $(this).remove();
                    alert("Usuario eliminado exitosamente");
                });                
            },
            error: function(xhr, status){
                alert("No se pudo borrar el usuario ");
            }
        });
    });               
});

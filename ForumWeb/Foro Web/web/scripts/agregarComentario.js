$(document).ready(function(){            
    
    $("#btnAgregarCom").click(function(){
        
        var correo= $("#formComCorreo").val();
        var titulo= $("#formComTitulo").val();
        var comen= $("#message").val();
        var fecha = new Date();
        
        $.ajax({
            type: "post",
            url : "ServletAgregarComentario2",
            data : {
                correo: correo,
                titulo: titulo,
                comentario: comen
            },
            success : function(data){                                 
                $("#ComentariosDiv").append("<div class=\"post\">\n" +
                        "<p class=\"meta\"><span class=\"date\">" + 
                        fecha + " hecho por <b>" + 
                        correo + "</b></p>" +
                        "<h4>" + comen + "</h4>");
                $("#message").val("");
                alert("Tu comentario fue agregado");
            },
            error: function(xhr, status){
                alert("No se pudo borrar el usuario ");
            }
        });
    });               
});




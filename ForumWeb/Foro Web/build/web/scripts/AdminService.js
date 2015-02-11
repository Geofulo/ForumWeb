jQuery(document).ready(function(){
    $('#botonEliminar').click(function(){
        
        event.preventDefaut();
        var correo = this.input.slice(this.href.lastIndexOf(''));
    });
});



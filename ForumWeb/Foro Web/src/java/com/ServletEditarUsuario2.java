package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mod.Usuario;
import org.jdom2.JDOMException;

/**
 *
 * @author Giovani
 */
public class ServletEditarUsuario2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String pswd = request.getParameter("password");
        String mat1 = request.getParameter("mat1");
        String mat2 = request.getParameter("mat2");
        String mat3 = request.getParameter("mat3");
        String mat4 = request.getParameter("mat4");
        String mat5 = request.getParameter("mat5");
        String biografia = request.getParameter("biografia");
        
        Usuario usuario = new Usuario();
        String[] correos, nombres, tipos;
        
        PrintWriter out = response.getWriter();
        try {            
                if(usuario.EditaUsuario(nombre, correo, pswd, biografia, mat1, mat2, mat3, mat4, mat5)){                                
                    correos = usuario.getCorreos();
                    nombres = usuario.getNombres();
                    tipos = usuario.getTipos();
                    out.println("<!DOCTYPE html><html><head>\n" +
                            "<title>Foro de Comunicación</title>" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />" +
                            "<script type=\"text/javascript\" src=\"scripts/jquery.js\"></script>" +
                            "<script type=\"text/javascript\" src='scripts/eliminarUsuario.js'></script>" +
                            "</head><body><div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\">" +
                            "<ul>" +                            
                            "<li><a href=\"agregarusuarios.html\">Agregar Usuarios</a></li></ul>" +
                            "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li></ul>" +
                            "</div></div></div><div id=\"logo\">" +
                            "<h1><a href=\"#\">Foro de Comunicación</a></h1>" +
                            "<p>Tecnologias para la Web</p>" +
                            "</div>" +
                            "<div id=\"page\"><div id=\"page-bgtop\"><div id=\"content\"><div class=\"post\">" +
                            "<h1>Gestión de Usuarios</h1>"+
                            "<table id='tableusuarios'><tr>" +
                            "<th>Correo</th>" +
                            "<th>Nombre</th>" +
                            "<th>Tipo de Usuario</th>" +
                            "<th>Editar</th>" +
                            "<th>Eliminar</th></tr>");
                        
                            for(int j=0; j<correos.length; j++){
                                out.println(                                
                                "<tr><td>" + correos[j] + "</td>" +
                                "<td>" + nombres[j] + "</td>" +
                                "<td>" + tipos[j] + "</td>" +
                                "<td>"+
                                "<form action='ServletEditarUsuario' method='post'>" +
                                "<input type='image' name='btnEditar' value='" + correos[j] +"' src='images/editar.png' style='width: 2em;'></form></td>" +
                                "<td><input type='image' class='btnEliminar' correo='" + correos[j] +"' src='images/eliminar.png' style='width: 2em;'></td></tr>");
                            }
                            out.println(
                            "</table></div></div><div style=\"clear:both\">&nbsp;" +
                            "</div></div></body></html>");
                }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

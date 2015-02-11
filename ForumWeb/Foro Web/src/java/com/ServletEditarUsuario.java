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
public class ServletEditarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        String correo = request.getParameter("btnEditar");
        
        Usuario usuario = new Usuario();
        
        PrintWriter out = response.getWriter();
        try {
            if(usuario.buscarUsuario(correo)){                
                    out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />\n" +
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul>"+
                        "<li><a href=\"agregarusuarios.html\" class=\"first\">Agregar Usuarios</a></li>\n" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Editar Usuario</h1><br><br><br>" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletEditarUsuario2\" method=\"POST\">");
                if(!usuario.getNombre(correo).equals("")){
                    out.println("<input type=\"text\" name=\"nombre\" id=\"name\" value='" + usuario.getNombre(correo) + "' placeholder=\"Nombre\" data-required style=\"width:250px\"><br><br>");
                } else{
                    out.println("<input type=\"text\" name=\"nombre\" id=\"name\" placeholder=\"Nombre\" data-required style=\"width:250px\"><br><br>");
                }
                    out.println(                        
                        "<select name=\"tipousuario\" id=\"sex\" data-selected data-required placeholder=\"Roll\" style=\"width:250px\">\n" +
                        "<option value=\"profesor\" class=\"select_prof\">profesor</option>\n" +
                        "<option value=\"alumno\" class=\"select_al\">alumno</option>	\n" +
                        "</select><br><br>" +                                        
                        "<input type=\"text\" name=\"correo\" value='" + correo + "' id=\"email\" placeholder=\"Correo Electrónico\" data-required data-validate=\"email\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"password\" name=\"password\" value='" + usuario.getContraseña(correo) + "' id=\"subject\" value=\"\" placeholder=\"Clave\" data-required style=\"width:250px\" /><br><br>" +
                        "<input type=\"password\" name=\"subject\" value='" + usuario.getContraseña(correo) + "' id=\"subject2\" value=\"\" placeholder=\"Repite Clave\" data-required style=\"width:250px\" /><br><br>");
                    
                    for(int i=0; i<5; i++){
                        if(!usuario.getNombreMateria(correo, i).equals("mayor")){
                            out.println("<input type=\"text\" name=\"mat" + (i+1) + "\" value='" + 
                                    usuario.getNombreMateria(correo, i) + "' placeholder=\"Materia " + (i+1) + "\" style=\"width:250px\" /><br><br>");
                        } else{
                            out.println("<input type=\"text\" name=\"mat" + (i+1) + "\" placeholder=\"Materia " + (i+1) + "\" style=\"width:250px\" /><br><br>");
                        }                        
                    }
                                            
                if(!usuario.getBiografia(correo).equals("")){
                    out.println("<textarea rows=\"6\" name=\"biografia\" id=\"message\" data-required placeholder=\"Biografía\" style=\"width:250px\">"+
                            usuario.getBiografia(correo) + "</textarea><br><br>");
                } else{
                    out.println("<textarea rows=\"6\" name=\"biografia\" id=\"message\" data-required placeholder=\"Biografía\" style=\"width:250px\"></textarea><br><br>");
                } 
                    out.println(                        
                        "<input type=\"submit\" name='botonaccion' class=\"button_green\" style=\"width:262px\" value=\"Editar\" /></form>" +
                        "<form action='ServletIngresar' method='post'>" +
                        "<input type=\"hidden\" name='correo' value=\"admin@web.com\" />" +
                        "<input type=\"hidden\" name='contrasenia' value=\"admin\" />" +
                        "<input type=\"submit\" name='botonaccion' class=\"button_gray\" style=\"width:262px\" value=\"Regresar\" /></form>" +
                        "</div></div></div>" +
                        "<div style=\"clear:both\">&nbsp;</div></div></div></body></html>");               
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }     
}

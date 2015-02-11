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
public class ServletAgregarUsuarios extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String tipoUsuario = request.getParameter("tipousuario");
        String correo = request.getParameter("correo");
        String pswd = request.getParameter("password");
        String mat1 = request.getParameter("mat1");
        String mat2 = request.getParameter("mat2");
        String mat3 = request.getParameter("mat3");
        String mat4 = request.getParameter("mat4");
        String mat5 = request.getParameter("mat5");
        String biografia = request.getParameter("biografia");
        
        Usuario usuario = new Usuario();
        String[] mats, correos, nombres, tipos;
        
        PrintWriter out = response.getWriter();
        try {            
            if(!usuario.buscarUsuario(correo)){
                if(usuario.AltaUsuario(nombre, tipoUsuario, correo, pswd, biografia, mat1, mat2, mat3, mat4, mat5)){
                    mats = usuario.getMateria(correo);                
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
                } else{
                    out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />\n" +
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\"first\">Agregar Usuarios</a></li>\n" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Alta de Usuarios</h1><br><br><br>               \n" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletAgregarUsuarios\" method=\"POST\">" +
                        "<input type=\"text\" name=\"nombre\" id=\"name\" placeholder=\"Nombre\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<select name=\"tipousuario\" id=\"sex\" data-selected data-required placeholder=\"Roll\" style=\"width:250px\">\n" +
                        "<option value=\"profesor\" class=\"select_prof\">profesor</option>\n" +
                        "<option value=\"alumno\" class=\"select_al\">alumno</option>	\n" +
                        "</select><br><br>\n" +
                        "<input type=\"text\" name=\"correo\" id=\"email\" placeholder=\"Correo Electrónico\" data-required data-validate=\"email\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"password\" name=\"password\" id=\"subject\" value=\"\" placeholder=\"Clave\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"password\" name=\"subject\" id=\"subject2\" value=\"\" placeholder=\"Repite Clave\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat1\" placeholder=\"Materia 1\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat2\" placeholder=\"Materia 2\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat3\" placeholder=\"Materia 3\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat4\" placeholder=\"Materia 4\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat5\" placeholder=\"Materia 5\" style=\"width:250px\" /><br><br>\n" +
                        "<textarea rows=\"6\" name=\"biografia\" id=\"message\" data-required placeholder=\"Biografía\" style=\"width:250px\"></textarea><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Agregar\" />\n" +
                        "</form></div></div></div>" +
                        "<div style=\"clear:both\">&nbsp;</div></div></div></body>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "alert('Ocurrió un problema, intentalo otra vez');\n" +
                        "</script></html>");
                }
            } else{
                out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />\n" +
                        "<script type=\"text/javascript\" src=\"scripts/mootools-core.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"scripts/mootools-more.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Core.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Element.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"srcipts/mForm.Element.Selected.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Submit.js\"></script>\n" +
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\"first\">Agregar Usuarios</a></li>\n" +
                        "<li><a href=\"editarusuarios.html\">Editar Usuarios</a></li></ul>\n" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +    
                        "</div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Alta de Usuarios</h1><br><br><br>               \n" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletAgregarUsuarios\" method=\"POST\">" +
                        "<input type=\"text\" name=\"nombre\" id=\"name\" placeholder=\"Nombre\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<select name=\"tipousuario\" id=\"sex\" data-selected data-required placeholder=\"Roll\" style=\"width:250px\">\n" +
                        "<option value=\"profesor\" class=\"select_prof\">profesor</option>\n" +
                        "<option value=\"alumno\" class=\"select_al\">alumno</option>	\n" +
                        "</select><br><br>\n" +
                        "<input type=\"text\" name=\"correo\" id=\"email\" placeholder=\"Correo Electrónico\" data-required data-validate=\"email\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"password\" name=\"password\" id=\"subject\" value=\"\" placeholder=\"Clave\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"password\" name=\"subject\" id=\"subject2\" value=\"\" placeholder=\"Repite Clave\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat1\" placeholder=\"Materia 1\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat2\" placeholder=\"Materia 2\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat3\" placeholder=\"Materia 3\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat4\" placeholder=\"Materia 4\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"mat5\" placeholder=\"Materia 5\" style=\"width:250px\" /><br><br>\n" +
                        "<textarea rows=\"6\" name=\"biografia\" id=\"message\" data-required placeholder=\"Biografía\" style=\"width:250px\"></textarea><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Agregar\" />\n" +
                        "</form></div></div></div>" +
                        "<div style=\"clear:both\">&nbsp;</div></div></div></body>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "alert('ERROR: El correo  " + correo + " ya fue registrado');\n" +
                        "</script></html>");
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }      
    
}

package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import mod.Usuario;
import org.jdom2.JDOMException;

/**
 *
 * @author Giovani
 */
public class ServletIngresar extends HttpServlet {    
        
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        String correo = (String)request.getParameter("correo");
        String contrasenia = (String)request.getParameter("contrasenia");
        
        Usuario usuario = new Usuario();        
        String[] mats, correos, nombres, tipos;
       
        PrintWriter out = response.getWriter();                  
        try {                     
            //String bio = usuario.getBiografia(correo);
            //String nombre = usuario.getNombre(correo);
            //String tipo = usuario.tipoUsuario(correo);
            
            mats = usuario.getMateria(correo);                
            correos = usuario.getCorreos();
            nombres = usuario.getNombres();
            tipos = usuario.getTipos();
            
              /*
            HttpSession user = request.getSession();        
            user.setAttribute("correo", correo);
            user.setAttribute("nombre", nombre);
            user.setAttribute("tipo", tipo);
            user.setAttribute("contrasenia", contrasenia);
            user.setAttribute("biografia", bio);
            */
            if(usuario.ingresarUsuario(correo, contrasenia)){                
                switch (usuario.tipoUsuario(correo)){                    
                    case "administrador":                        
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
                        break;
                        
                    case "profesor":
                        out.println("<!DOCTYPE html><html><head>" +
                            "<title>Foro de Comunicacion</title>" +
                            "<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />" +
                            "</head><body>" +
                            "<div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\"><ul>" +
                            "<li><a href=\"#\" class=\"first\">Home</a></li>" +
                            "<li><a href=\"agregartema.html\">Agregar Tema</a></li>" +
                            "<li><a href=\"ServletVerGraficas?correo=" + correo + "\">Ver Gráficas</a></li>" +
                            "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                            "</ul></div><div id=\"search\">" +
                            "<form method=\"get\" action=\"#\">" +
                            "<fieldset>" +
                            "<input type=\"text\" name=\"s\" id=\"search-text\" size=\"15\" />" +
                            "<input type=\"submit\" id=\"search-submit\" value=\"GO\" />" +
                            "</fieldset>" +
                            "</form></div></div>" +
                            "</div><div id=\"logo\">" +
                            "<h1><a href=\"#\">Foro de Comunicación</a></h1>" +
                            "<p>Tecnologias para la Web</p>" +
                            "</div><hr />" +
                            "<div id=\"page\"><div class=\"inner_copy\"></div>" +
                            "<div id=\"page-bgtop\"><div id=\"content\">" +
                            "<div class=\"post\">" +
                            "<h2 class=\"title\"><a href=\"#\">Bienvenido " + usuario.tipoUsuario(correo) + "</a></h2>" +
                            "<div class=\"entry\">" +
                            "<p>Selecciona una de tus materias para ver los temas relacionadas</p>" +
                            "</div></div></div>" +
                            "<div id=\"sidebar\"><ul><li>" +
                            "<h2>" + usuario.getNombre(correo) + "</h2>" +
                            "<b><p>" + usuario.getBiografia(correo) + "</p></b>" +
                            "</li><li>" +
                            "<h2>Materias</h2><form><ul>");
                        for(int m=0; m<mats.length; m++){
                            if(!mats[m].toString().equals("")){
                                out.println("<li><a href=\"ServletVerTemas?materia=" + 
                                        mats[m].toString() + "&correo=" + correo + "\">" + 
                                        mats[m].toString() + "</a></li>");
                            }
                        }
                        out.println(
                            "</ul></form></li><li></li></ul>" +
                            "</div><div style=\"clear:both\">&nbsp;</div></div>" +
                            "</div></body></html>");
                        break;
                        
                    case "alumno":                        
                        out.println("<!DOCTYPE html><html><head>" +
                            "<title>Foro de Comunicacion</title>" +
                            "<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />" +
                            "</head><body>" +
                            "<div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\"><ul>" +
                            "<li><a href=\"#\" class=\"first\">Home</a></li>" +
                            "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                            "</ul></div><div id=\"search\">" +
                            "<form method=\"POST\" action=\"#\">" +
                            "<fieldset>" +
                            "<input type=\"text\" name=\"s\" id=\"search-text\" size=\"15\" />" +
                            "<input type=\"submit\" id=\"search-submit\" value=\"GO\" />" +
                            "</fieldset>" +
                            "</form></div></div>" +
                            "</div><div id=\"logo\">" +
                            "<h1><a href=\"#\">Foro de Comunicación</a></h1>" +
                            "<p>Tecnologias para la Web</p>" +
                            "</div><hr />" +
                            "<div id=\"page\"><div class=\"inner_copy\"></div>" +
                            "<div id=\"page-bgtop\"><div id=\"content\">" +
                            "<div class=\"post\">" +
                            "<h2 class=\"title\"><a href=\"#\">Bienvenido " + usuario.tipoUsuario(correo) + "</a></h2>" +
                            "<div class=\"entry\">" +
                            "<p>Selecciona una de tus materias para ver los temas relacionadas</p>" +
                            "</div></div></div>" +
                            "<div id=\"sidebar\"><ul><li>" +
                            "<h2>" + usuario.getNombre(correo) + "</h2>" +
                            "<b><p>" + usuario.getBiografia(correo) + "</p></b>" +
                            "</li><li>" +
                            "<h2>Materias</h2><form><ul>");
                        for(int k=0; k<mats.length; k++){
                            if(!mats[k].toString().equals("")){
                                out.println("<li><a href=\"ServletVerTemas?materia=" + 
                                    mats[k].toString() + "&correo=" + correo + "\">" + 
                                    mats[k].toString() + "</a></li>");
                            }
                        }
                        out.println(
                            "</ul></form></li><li></li></ul>" +
                            "</div><div style=\"clear:both\">&nbsp;</div></div>" +
                            "</div></body></html>");
                        break;
                    case "ninguno":
                        out.println("<!DOCTYPE html><html><head></head>");
                        out.println("<body>Bienvenido ninguno</body></html>");
                        break;
                } 
            }else {                                
                out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />" +
                        "<script type=\"text/javascript\" src=\"scripts/mootools-core.js\"></script>" +
                        "<script type=\"text/javascript\" src=\"scripts/mootools-more.js\"></script>" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Core.js\"></script>" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Element.js\"></script>" +
                        "<script type=\"text/javascript\" src=\"srcipts/mForm.Element.Selected.js\"></script>" +
                        "<script type=\"text/javascript\" src=\"scripts/mForm.Submit.js\"></script>" +
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\"><ul>\n" +
                        "<li><a href=\"#\" class=\"first\">Home</a></li>\n" +
                        "<li><a href=\"#\">Login</a></li>\n" +
                        "</ul></div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div>\n" +
                        "<div id=\"page\"><div id=\"page-bgtop\"><div id=\"content\">\n" +
                        "<div class=\"post\"><h1>Login</h1><br><br><br>" +
                        "<form action=\"ServletIngresar\" method=\"POST\">\n" +
                        "<input type=\"text\" name=\"correo\" id=\"email\" placeholder=\"Correo Electrónico\" data-validate=\"email\" style=\"width:250px\" /><br><br>                        \n" +
                        "<input type=\"password\" name=\"contrasenia\" id=\"subject\" placeholder=\"Contraseña\" style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" />\n" +
                        "</form></div></div><div style=\"clear:both\">&nbsp;</div>\n" +
                        "</div></div></body>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "alert('No tienes una cuenta aún');\n" +
                        "</script></html>");
            }                                   
        } catch (JDOMException ex) {
           Logger.getLogger(ServletIngresar.class.getName()).log(Level.SEVERE, null, ex);
       } finally{
            out.close();
        }
   }   
      
}

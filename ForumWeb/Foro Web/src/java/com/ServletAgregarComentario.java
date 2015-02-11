package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mod.Tema;
import mod.Usuario;
import org.jdom2.JDOMException;

/**
 *
 * @author Giovani
 */

public class ServletAgregarComentario extends HttpServlet {    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String titulo = request.getParameter("titulo");
        String correo = request.getParameter("correo");
        String comentario = request.getParameter("comentario");
        Usuario usuario = new Usuario();
        Tema tema = new Tema();
        String[] mats, txtCom, autorCom, fechasCom;
        
        PrintWriter out = response.getWriter();
        try {
            mats = usuario.getMateria(correo);
            txtCom = tema.getComentarios(titulo);
            autorCom = tema.getAutoresCom(titulo);
            fechasCom = tema.getFechasCom(titulo);
            
            if(tema.agregarComentario(titulo, correo, comentario)){                            
                out.println("<!DOCTYPE html>\n" +
                    "<title>Foro de Comunicacion</title>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />" +
                    "<script type=\"text/javascript\" src=\"scripts/mootools-core.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mootools-more.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Core.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Element.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"srcipts/mForm.Element.Selected.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Submit.js\"></script>" +
                    "</head><body>" +
                    "<div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\"><ul>" +
                    "<li><a href=\"#\" class=\"first\">Home</a></li>");
                if(usuario.tipoUsuario(correo).equals("profesor")){
                    out.println("<li><a href=\"agregartema.html\">Agregar  Tema</a></li>");
                }
                out.println(
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
                    "<p class=\"meta\">Sunday, April 26, 2009 7:27 AM Posted by " + 
                    tema.getAutorByTitulo(titulo) + "</p>" +
                    "<h2 class=\"title\"><a href=\"#\">" + 
                    titulo + "</a></h2>" +
                    "<div class=\"entry\">" +
                    "<p>" + tema.getTextoByTitulo(titulo) + "</p>" +
                    "</div></div>" + 
                    "<div class=\"entry\">\n" +
                    "<h2>Comentarios</h2>\n" +
                    "</div><br>");
            
                for(int j=0; j<txtCom.length; j++){
                    out.println("<div class=\"post\">\n" +
                        "<p class=\"meta\"><span class=\"date\">" + 
                        fechasCom[j] + " hecho por " + 
                        autorCom[j] + "</p>" +
                        "<p>" + txtCom[j] + "</p>" +
                        "</div>");
                }
            
                out.println(
                    "<div class=\"post\">\n" +
                    "<form action='ServletAgregarComentario' method='POST'>" +
                    "<textarea rows=\"6\" name=\"comentario\" id=\"message\" placeholder=\"Escribe tu comentario\" style=\"width:500px\"></textarea><br><br>" +
                    "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Comentar\" />" +
                    "</form></div></div>" +
                    "<div id=\"sidebar\"><ul><li>" +
                    "<h2>" + usuario.getNombre(correo) + "</h2>" +
                    "<b><p>" + usuario.getBiografia(correo) + "</p></b>" +
                    "</li><li>" +
                    "<h2>Materias</h2><form><ul>");
            
                for(int k=0; k<mats.length; k++){
                    out.println("<li><a href=\"ServletVerTemas?materia=" + 
                        mats[k].toString() + "&correo=" + correo + "\">" + 
                        mats[k].toString() + "</a></li>");
                }
                    
                out.println(
                    "</ul></form></li></ul>" +
                    "</div><div style=\"clear:both\">&nbsp;</div></div>" +
                    "</div></body>" + 
                    "<script type=\"text/javascript\">\n" +
                    "alert('¡Tu comentario fue agregado!');\n" +
                    "</script></html>");
            } else{
                out.println("<!DOCTYPE html>\n" +
                    "<title>Foro de Comunicacion</title>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />" +
                    "<script type=\"text/javascript\" src=\"scripts/mootools-core.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mootools-more.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Core.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Element.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"srcipts/mForm.Element.Selected.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/mForm.Submit.js\"></script>" +
                    "</head><body>" +
                    "<div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\"><ul>" +
                    "<li><a href=\"#\" class=\"first\">Home</a></li>");
                if(usuario.tipoUsuario(correo).equals("profesor")){
                    out.println("<li><a href=\"agregartema.html\">Agregar  Tema</a></li>");
                }
                out.println(
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
                    "<p class=\"meta\">Sunday, April 26, 2009 7:27 AM Posted by " + 
                    tema.getAutorByTitulo(titulo) + "</p>" +
                    "<h2 class=\"title\"><a href=\"#\">" + 
                    titulo + "</a></h2>" +
                    "<div class=\"entry\">" +
                    "<p>" + tema.getTextoByTitulo(titulo) + "</p>" +
                    "</div></div>" + 
                    "<div class=\"entry\">\n" +
                    "<h2>Comentarios</h2>\n" +
                    "</div><br>");
            
                for(int j=0; j<txtCom.length; j++){
                    out.println("<div class=\"post\">\n" +
                        "<p class=\"meta\"><span class=\"date\">" + 
                        fechasCom[j] + " hecho por " + 
                        autorCom[j] + "</p>" +
                        "<p>" + txtCom[j] + "</p>" +
                        "</div>");
                }
            
                out.println(
                    "<div class=\"post\">\n" +
                    "<form action='ServletAgregarComentario' method='POST'>" +
                    "<textarea rows=\"6\" name=\"comentario\" id=\"message\" placeholder=\"Escribe tu comentario\" style=\"width:500px\"></textarea><br><br>" +
                    "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Comentar\" />" +
                    "</form></div></div>" +
                    "<div id=\"sidebar\"><ul><li>" +
                    "<h2>" + usuario.getNombre(correo) + "</h2>" +
                    "<b><p>" + usuario.getBiografia(correo) + "</p></b>" +
                    "</li><li>" +
                    "<h2>Materias</h2><form><ul>");
            
                for(int k=0; k<mats.length; k++){
                    out.println("<li><a href=\"ServletVerTemas?materia=" + 
                        mats[k].toString() + "&correo=" + correo + "\">" + 
                        mats[k].toString() + "</a></li>");
                }
                    
                out.println(
                    "</ul></form></li></ul>" +
                    "</div><div style=\"clear:both\">&nbsp;</div></div>" +
                    "</div></body>" + 
                    "<script type=\"text/javascript\">\n" +
                    "alert('ERROR: Tu comentario no se pudo agregar, intentalo más tarde');\n" +
                    "</script></html>");
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletVerTemas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
}

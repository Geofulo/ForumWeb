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
public class ServletEliminarTema extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titulo = request.getParameter("tema");
        String correo = request.getParameter("correo");
        Usuario usuario = new Usuario();
        Tema tema = new Tema();        
        String[] ids;
        String[] mats, txtCom, autorCom, fechasCom;
        
        PrintWriter out = response.getWriter();
        try {
            String materia = tema.getMateriaByTitulo(titulo);
            if(tema.eliminarTema(titulo)){                       
                    mats = usuario.getMateria(correo); 
                    ids = tema.getIds(materia);            

                    out.println("<!DOCTYPE html>\n" +
                        "<title>Foro de Comunicacion</title>" +
                        "<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />" +
                        "</head><body>" +
                        "<div id=\"header-wrapper\"><div id=\"header\"><div id=\"menu\"><ul>" +
                        "<li><a href=\"#\" class=\"first\">Home</a></li>");
                    if(usuario.tipoUsuario(correo).equals("profesor")){
                        out.println("<li><a href=\"agregartema.html\">Agregar  Tema</a></li>");
                        out.println("<li><a href=\"vergraficas.html\">Ver Gráficas</a></li>");
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
                        "<h1>" + materia + "</h1>");            
                if(tema.numeroTemas(materia) == 0){
                    out.println(
                            "<div class=\"post\">" +
                            "<div class=\"entry\">" +
                            "<p>No hay ningún tema aún</p>" +
                            "</div></div>");  
                } else{
                    for(int j=0; j<tema.numeroTemas(materia); j++){
                        int id = Integer.parseInt(ids[j]);
                        out.println(            
                            "<div class=\"post\">" +
                            "<p class=\"meta\">Sunday, April 26, 2009 7:27 AM Posted by " + 
                                tema.getAutor(materia, id) + "</p>" +
                            "<h2 class=\"title\"><a href=\"ServletVisualizarTema?titulo=" + 
                                tema.getTitulo(materia, id) + "&correo=" + correo + "\">" + 
                                tema.getTitulo(materia, id) + "</a></h2>" +
                            "<div class=\"entry\">" +
                            "<p>" + tema.getTexto(materia, id) + "</p>" +
                            "</div></div>");                   
                    }
                }
                out.println(
                        "</div>" +
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
                    "</div></body></html>");
    } else{
                mats = usuario.getMateria(correo);
            txtCom = tema.getComentarios(titulo);
            autorCom = tema.getAutoresCom(titulo);
            fechasCom = tema.getFechasCom(titulo);
            
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
                    out.println("<li><a href=\"vergraficas.html\">Ver Gráficas</a></li>");
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
                        fechasCom[j] + " hecho por <b>" + 
                        autorCom[j] + "</b></p>" +
                        "<h4>" + txtCom[j] + "</h4>");
                if(usuario.tipoUsuario(correo).equals("profesor")){
                    if(usuario.tipoUsuarioByNombre(autorCom[j]).equals("alumno")){
                        out.println("<form action='ServletCalificar' method='POST'><div id=\"setrating\">Puntuación:  "); 
                        
                        for(int m=1; m<6; m++){
                            int calif = Integer.parseInt(tema.getCalificacion(titulo, usuario.getCorreo(autorCom[j]), j));
                            if(calif >= (m*2)){
                                out.println("<input type='image' name='cal' value='" + (m*2) + "' " +
                                        "src=\"images/rate1.png\" id=\"R" + m + "\" alt='" + (m-1) + "'>");
                            } else{
                                out.println("<input type='image' name='cal' value='" + (m*2) + "' " +
                                        "src=\"images/rate0.png\" id=\"R" + m + "\" alt='" + (m-1) + "'>");
                            }
                        }
                        out.println(
                            "<input name=\"correo\" type='hidden' value='" + correo + "' />" +
                            "<input name=\"autorcom\" type='hidden' value='" + usuario.getCorreo(autorCom[j]) + "' />" +
                            "<input name=\"titulo\" type='hidden' value='" + titulo + "' />" +
                            "</div></form>" 
                            );
                    }
                 }
                 out.println("</div>");
            }
            
            out.println(
                    "<div class=\"post\">\n" +
                    "<form action='ServletAgregarComentario' method='POST'>" +
                    "<textarea rows=\"6\" name=\"comentario\" id=\"message\" placeholder=\"Escribe tu comentario\" style=\"width:500px\"></textarea><br><br>" +
                    "<input name=\"correo\" type='hidden' value='" + correo + "' />" +
                    "<input name=\"titulo\" type='hidden' value='" + titulo + "' />" +
                    "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Comentar\" />" +
                    "</form></div>" + 
                    "</form action='ServletEliminarTema' method='POST'>");
                    if(usuario.tipoUsuario(correo).equals("profesor")){
                        out.println(
                                "<input type='hidden' value='" + titulo + "' name='tema'>" +
                                "<input type='hidden' value='" + correo + "' name='correo'>" +
                                "<input type=\"submit\" name='botonaccion' class=\"button_red\" style=\"width:262px\" value=\"Eliminar Tema\" />");
                    }
                    out.println("</form></div>" +
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
                "<script type=\"text/javascript\" src=\"scripts/puntuar.js\"></script>" +
                "<script type=\"text/javascript\">\n" +
                "alert('Hubo un error, intentelo más tarde');\n" +
                "</script></html>");
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }     
}

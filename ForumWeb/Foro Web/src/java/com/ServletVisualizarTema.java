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

public class ServletVisualizarTema extends HttpServlet {    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String titulo = request.getParameter("titulo");
        String correo = request.getParameter("correo");
        Usuario usuario = new Usuario();
        Tema tema = new Tema();
        String[] mats, txtCom, autorCom, fechasCom;
        
        PrintWriter out = response.getWriter();
        try {
            mats = usuario.getMateria(correo);
            txtCom = tema.getComentarios(titulo);
            autorCom = tema.getAutoresCom(titulo);
            fechasCom = tema.getFechasCom(titulo);
            
            out.println("<!DOCTYPE html>\n" +
                    "<title>Foro de Comunicacion</title>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mForm.css\" />" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/mFormElementSelected.css\" />" +
                    "<script type=\"text/javascript\" src=\"scripts/jquery.js\"></script>" +
                    "<script type=\"text/javascript\" src=\"scripts/agregarComentario.js\"></script>" +
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
                    "</div><br><div id='ComentariosDiv'>");
            
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
                            //"<input type='image' name='cal' value='2' src=\"images/rate0.png\" id=\"R1\" alt=\"0\" title=\"Not at All\"/>" +
                            //"<input type='image' name='cal' value='4' src=\"images/rate0.png\" id=\"R2\" alt=\"1\" title=\"Somewhat\" />" +
                            //"<input type='image' name='cal' value='6' src=\"images/rate0.png\" id=\"R3\" alt=\"2\" title=\"Average\" />" +
                            //"<input type='image' name='cal' value='8' src=\"images/rate0.png\" id=\"R4\" alt=\"3\" title=\"Good\" />" +
                            //"<input type='image' name='cal' value='10' src=\"images/rate0.png\" id=\"R5\" alt=\"4\" title=\"Very Good\"/>" +
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
                    "</div><div class=\"post\">" +
                    //"<form id='formComentario'>" +
                    "<textarea rows=\"6\" name=\"comentario\" id=\"message\" placeholder=\"Escribe tu comentario\" style=\"width:500px\"></textarea><br><br>" +
                    "<input name=\"correo\" id='formComCorreo' type='hidden' value='" + correo + "' />" +
                    "<input name=\"titulo\" id='formComTitulo' type='hidden' value='" + titulo + "' />" +
                    //"<input type=\"submit\" id='btnAgregarCom' class=\"button_green\" style=\"width:262px\" value=\"Comentar\" />" +
                    "<button type=\"submit\" id='btnAgregarCom' class=\"button_green\" style=\"width:262px\">Comentar</button>" +
                    //"</form>"+
                    "</div>" + 
                    "<form action='ServletEliminarTema' method='POST'>");
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
                "<script type=\"text/javascript\" src=\"scripts/puntuar.js\"></script></html>");
            
        } catch (JDOMException ex) {
            Logger.getLogger(ServletVerTemas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
}

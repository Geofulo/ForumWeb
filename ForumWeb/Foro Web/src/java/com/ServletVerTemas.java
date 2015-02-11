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

public class ServletVerTemas extends HttpServlet {    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String materia = request.getParameter("materia");
        String correo = request.getParameter("correo");
        Usuario usuario = new Usuario();
        Tema tema = new Tema();
        String[] mats;
        String[] ids;
        
        PrintWriter out = response.getWriter();
        try {
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
            
        } catch (JDOMException ex) {
            Logger.getLogger(ServletVerTemas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
}

package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

public class ServletAgregarComentario2 extends HttpServlet {    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String titulo = request.getParameter("titulo");
        String correo = request.getParameter("correo");
        String comentario = request.getParameter("comentario");
        Tema tema = new Tema();
        Usuario usuario = new Usuario();
        Date fecha = new Date();
                
        PrintWriter out = response.getWriter();
        try {
            
            if(tema.agregarComentario(titulo, correo, comentario)){                            
                out.print("<div class=\"post\">\n" +
                        "<p class=\"meta\"><span class=\"date\">" + 
                        fecha + " hecho por " + 
                        usuario.getNombre(correo) + "</p>" +
                        "<p>" + comentario + "</p>" +
                        "</div>");                        
                        
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletVerTemas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
}

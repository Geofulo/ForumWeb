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
import org.jdom2.JDOMException;

/**
 *
 * @author Giovani
 */
public class ServletAgregarTemas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titulo = request.getParameter("titulo");
        String profesor = request.getParameter("profesor");
        String materia = request.getParameter("materia");
        String contenido = request.getParameter("contenido");        
        
        Tema tema = new Tema();
        Date fechaactual = new Date();
        String fecha = fechaactual.toString();
        
        PrintWriter out = response.getWriter();
        try {
            if(!tema.buscarTema(titulo)){
                if(tema.crearTema(titulo, profesor, materia, contenido, fecha)){
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
                        "</head><body><div id=\"header-wrapper\">" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\\\"first\\\">Home</a></li>" +
                        "<li><a href=\"agregartema.html\">Agregar  Tema</a></li>" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</ul></div></div></div><div id=\"logo\">\n" +
                        "<h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p>\n" +
                        "</div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Agregar Tema</h1><br><br>" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletAgregarTemas\" method=\"POST\">                \n" +
                        "<input type=\"text\" name=\"titulo\" placeholder=\"Titulo\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"profesor\" placeholder=\"Profesor\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"materia\" placeholder=\"Materia\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<textarea rows=\"6\" name=\"contenido\" data-required placeholder=\"Contenido\" style=\"width:250px\"></textarea><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Agregar\" /><br><br>\n" +
                        "<input type=\"reset\" class=\"button_yellow button\" style=\"width:262px\" value=\"Limpiar\" />\n" +
                        "</form></div></div></div>\n" +
                        "<div style=\"clear:both\">&nbsp;\n" +
                        "</div></div></div></body>" + 
                        "<script type=\"text/javascript\">\n" +
                        "alert('El tema \"" + titulo + "\" fue dado de alta exitosamente');\n" +
                        "</script></html>");
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
                        "</head><body><div id=\"header-wrapper\">" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\\\"first\\\">Home</a></li>" +
                        "<li><a href=\"agregartema.html\">Agregar  Tema</a></li>" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</ul></div></div></div><div id=\"logo\">\n" +
                        "<h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p>\n" +
                        "</div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Agregar Tema</h1><br><br>" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletAgregarTemas\" method=\"POST\">                \n" +
                        "<input type=\"text\" name=\"titulo\" placeholder=\"Titulo\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"profesor\" placeholder=\"Profesor\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"materia\" placeholder=\"Materia\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<textarea rows=\"6\" name=\"contenido\" data-required placeholder=\"Contenido\" style=\"width:250px\"></textarea><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Agregar\" /><br><br>\n" +
                        "<input type=\"reset\" class=\"button_yellow button\" style=\"width:262px\" value=\"Limpiar\" />\n" +
                        "</form></div></div></div>\n" +
                        "<div style=\"clear:both\">&nbsp;\n" +
                        "</div></div></div></body>" + 
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
                        "</head><body><div id=\"header-wrapper\">" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\\\"first\\\">Home</a></li>" +
                        "<li><a href=\"agregartema.html\">Agregar  Tema</a></li>" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</ul></div></div></div><div id=\"logo\">\n" +
                        "<h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p>\n" +
                        "</div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +
                        "<div style=\"width:700px; margin:auto\">\n" +
                        "<h1>Agregar Tema</h1><br><br>" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletAgregarTemas\" method=\"POST\">                \n" +
                        "<input type=\"text\" name=\"titulo\" placeholder=\"Titulo\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"profesor\" placeholder=\"Profesor\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<input type=\"text\" name=\"materia\" placeholder=\"Materia\" data-required style=\"width:250px\" /><br><br>\n" +
                        "<textarea rows=\"6\" name=\"contenido\" data-required placeholder=\"Contenido\" style=\"width:250px\"></textarea><br><br>\n" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Agregar\" /><br><br>\n" +
                        "<input type=\"reset\" class=\"button_yellow button\" style=\"width:262px\" value=\"Limpiar\" />\n" +
                        "</form></div></div></div>\n" +
                        "<div style=\"clear:both\">&nbsp;\n" +
                        "</div></div></div></body>" + 
                        "<script type=\"text/javascript\">\n" +
                        "alert('ERROR: El tema con el titulo \"" + titulo + "\" ya fue registrado');\n" +
                        "</script></html>");
            }
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }      
    
}

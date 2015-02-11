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
public class ServletVerGraficas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        
        Usuario usuario = new Usuario();
        Tema tema = new Tema();
        
        String[] materias;
        
        PrintWriter out = response.getWriter();
        try {
            materias = usuario.getMateria(correo);
            
            out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n" +
                        "<script type=\"text/javascript\" src=\"scripts/googleapi.js\"></script>\n" +                        
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\"first\">Agregar Usuarios</a></li>\n" +
                        "<li><a href=\"ServletEditarUsuarios\">Editar Usuarios</a></li></ul>\n" +
                        "<li><a href=\"vergraficas.html\">Ver Gráficas</a></li>" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div>" +
                        "<div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">" +
                        "<div style=\"width:700px; margin:auto\">" +
                        "<h1>Selecciona una de tus materias: </h1>" +
                        "<form name=\"example_form\" id=\"example_form\" action=\"ServletVerGraficas2\" method=\"POST\">" +
                        "<select name=\"materia\" id=\"sex\" data-selected data-required placeholder=\"Roll\" style=\"width:250px\">");
                    for(int i=0; i<materias.length; i++){
                        if(!materias[i].toString().equals("")){
                            out.println(
                            "<option value='" + materias[i] + "' class=\"select_prof\">" + materias[i] + "</option>");
                        }                        
                    }
                    out.println(
                        "</select><br><br>" +
                        "<input type=\"submit\" class=\"button_green\" style=\"width:262px\" value=\"Ver Gráfica\" /><br><br>\n" +
                        "</form></div></div></div>" + 
                        "<div style=\"clear:both\">&nbsp;</div></div></div></body></html>");
            
            /*
            if(usuario.buscarUsuario(correo)){   
                out.println("<!DOCTYPE html><html><head>\n" +
                        "<title>Foro de Comunicación</title>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" media=\"screen\" />\n" +
                        "<script type=\"text/javascript\" src=\"scripts/googleapi.js\"></script>\n" +
                        "<style type=\"text/css\">\n" +
                        "#eventsExample {" +
                        "position: relative;}" +
                        "#details0, #details1, #details2, #details3 {\n" +
                        "visibility:hidden;\n" +
                        "background: #FFFF7F;\n" +
                        "border: solid 1px;\n" +
                        "width: 350px;\n" +
                        "padding: 5px;\n" +
                        "font-size:smaller;\n" +
                        "position:absolute;\n" +
                        "top: 250px;}\n" +
                        "</style>" +

                        "<script type=\"text/javascript\">\n" +
                        "google.load('visualization', '1', {'packages':['columnchart','piechart']});\n" +
                        "google.setOnLoadCallback (createChart);\n" +
                        "function createChart() {" +
                        "var dataTable = new google.visualization.DataTable();" +
                        "dataTable.addColumn('string','Quarters 2009');\n" +
                        "dataTable.addColumn('number', 'Comentarios');\n" +                        
                        "dataTable.addRows([['0 Estrellas'," + tema.numeroEstrellas(correo, 0) +"], " +
                                "['2 Estrellas'," + tema.numeroEstrellas(correo, 2) +"]," + 
                                "['4 Estrellas'," + tema.numeroEstrellas(correo, 4) +"]," + 
                                "['6 Estrellas'," + tema.numeroEstrellas(correo, 6) +"]," + 
                                "['8 Estrellas'," + tema.numeroEstrellas(correo, 8) +"]," + 
                                "['10 Estrellas'," + tema.numeroEstrellas(correo, 10) +"]]);" +
                        "var chart = new google.visualization.ColumnChart (document.getElementById('Chart'));\n" +
                        "var options = {width: 400, height: 240, is3D: true, title: '¿Cuántos comentarios he hecho con cierto número de estrellas?'};\n" +
                        "chart.draw(dataTable, options);}" +
                        "</script>" +
                        "</head><body><div id=\"header-wrapper\">\n" +
                        "<div id=\"header\"><div id=\"menu\">\n" +
                        "<ul><li><a href=\"#\" class=\"first\">Agregar Usuarios</a></li>\n" +
                        "<li><a href=\"ServletEditarUsuarios\">Editar Usuarios</a></li></ul>\n" +
                        "<li><a href=\"vergraficas.html\">Ver Gráficas</a></li>" +
                        "<li><a href=\"ingresar.html\">Cerrar Sesión</a></li>" +
                        "</div></div></div>\n" +
                        "<div id=\"logo\"><h1><a href=\"#\">Foro de Comunicación</a></h1>\n" +
                        "<p>Tecnologias para la Web</p></div><div id=\"page\"><div id=\"page-bgtop\">\n" +
                        "<div id=\"content\"><div class=\"post\">\n" +                        
                        "<h1>Gráfica de " + usuario.getNombre(correo) + " </h1>\n" +
                        "<div id=\"Chart\"></div>\n" +
                        "</div></div>" +
                        "<div style=\"clear:both\">&nbsp;</div></div></div></body></html>");                    
            } */
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAgregarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }     
}

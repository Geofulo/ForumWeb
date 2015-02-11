package mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Giovani
 */
public class Tema {    
    
    private SAXBuilder builder = new SAXBuilder();
    //private File xml_file = new File("C:\\xml\\Temas.xml");     
    private File xml_file = new File("C:\\Users\\Giovani\\Desktop\\Temas.xml");         
    
    public Tema(){
        super();
    }
    
    public boolean buscarTema(String titulo) throws JDOMException{              
           try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       return true;
                   }
               }
               return false;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return false;
           }          
   }    
    
    public boolean crearTema(String titulo, String usuario, String materia, String contenido, String fecha) throws JDOMException{
        try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();
               Element post = new Element("Post");
               //user.setAttribute("tipo", tipoU);
               post.addContent(new Element("Titulo").setText(titulo));
               post.addContent(new Element("Usuario").setText(usuario));
               post.addContent(new Element("Materia").setText(materia));
               post.addContent(new Element("Fecha").setText(fecha));
               post.addContent(new Element("Contenido").setText(contenido));
               post.addContent(new Element("Comentarios").setText(""));
               raiz.addContent(post);
               XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
               //outxml.output(doc, new FileWriter("C:\\xml\\Temas.xml")); 
               outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Temas.xml"));
               return true;
           } catch (IOException io) {
               return false;
           }        
    }
    
    public int numeroTemas(String materia) throws JDOMException{
        int count = 0;
        try {            
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();
               List listaTemas = raiz.getChildren("Post");
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String mat = nodo.getChild("Materia").getValue();
                   if(materia.equals(mat)){
                       count = count + 1;
                   }
               }
               return count;               
           } catch (IOException io) {
               return 0;
           }
    }
    
    public int numeroEstrellas(String correo, int id) throws JDOMException{
        int count = 0;
        try {            
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();
               List listaTemas = raiz.getChildren("Post");
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   Element comens = nodo.getChild("Comentarios");
                   List listaComen = comens.getChildren("Comentario");
                   for(int j=0; j<listaComen.size(); j++){
                       Element comentario = (Element) listaComen.get(j);
                       String autor = comentario.getAttributeValue("autor");
                       if(autor.equals(correo)){
                           String cal = comentario.getChild("Calificacion").getValue();
                           int cal2 = Integer.parseInt(cal);
                           if(cal2 == id){
                               count = count + 1;
                           }
                       }
                   }
               }
               return count;               
           } catch (IOException io) {
               return 0;
           }
    }
    
    public String getTitulo(String materia, int id) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Post");
               Element nodo = (Element) listaUsuarios.get(id);
               String mat = nodo.getChild("Materia").getValue();
               if(materia.equals(mat)){
                   String titulo = nodo.getChild("Titulo").getValue();
                   return titulo;
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public String getCalificacion(String titulo, String correo, int id) throws JDOMException{
        try {               
               Document doc = builder.build(xml_file);
               Element raiz = doc.getRootElement(); 
               List listaUsuarios = raiz.getChildren("Post");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element nodo2 = nodo.getChild("Comentarios");
                       List listaComens = nodo2.getChildren("Comentario");
                       Element elemCom = (Element) listaComens.get(id);
                       String autor = elemCom.getAttributeValue("autor");
                       if(correo.equals(autor)){
                           String calificacion = elemCom.getChild("Calificacion").getValue();                               
                           return calificacion;
                       }
                   }
               }    
               return null;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
    }
    
    public String getTexto(String materia, int id) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Post");
               Element nodo = (Element) listaUsuarios.get(id);
               String mat = nodo.getChild("Materia").getValue();
               if(materia.equals(mat)){
                   String contenido = nodo.getChild("Contenido").getValue();
                   return contenido;
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public String getAutor(String materia, int id) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Post");
               Element nodo = (Element) listaUsuarios.get(id);
               String mat = nodo.getChild("Materia").getValue();
               if(materia.equals(mat)){
                   String autor = nodo.getChild("Usuario").getValue();
                   return autor;
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return "error";
    }
    
    public String[] getIds(String materia) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");
               int k = 0;
               String ids[] = new String[numeroTemas(materia)];
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String mat = nodo.getChild("Materia").getValue();
                   if(materia.equals(mat)){                       
                       String id = nodo.getChild("Id").getValue();
                       ids[k++] = id;
                   }
               } return ids;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
    }
    
    public String[] getMateria(String correo) throws JDOMException {
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");                       
               //String[] mat = {"",""};
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){                       
                       List listaMaterias = nodo.getChildren("Materia");     
                       String mat[] = new String[listaMaterias.size()];
                       for(int j=0; j<listaMaterias.size(); j++){
                           Element materia = (Element)listaMaterias.get(j);
                           mat[j] = materia.getValue();                           
                       }
                       return mat;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public String getAutorByTitulo(String titulo) throws JDOMException{              
           try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       String autor = nodo.getChild("Usuario").getValue();
                       return autor;
                   }
               }
               return null;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }          
    }
    
    public String getTextoByTitulo(String titulo) throws JDOMException{              
           try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       String texto = nodo.getChild("Contenido").getValue();
                       return texto;
                   }
               }
               return null;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }          
    }
    
    public String[] getComentarios(String titulo) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");                       
               //String[] mat = {"",""};
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element comentarios = nodo.getChild("Comentarios");
                       List listaCom = comentarios.getChildren("Comentario");
                       String texto[] = new String[listaCom.size()];
                       for(int j=0; j<listaCom.size(); j++){
                           Element txtComentario = (Element)listaCom.get(j);
                           String com = txtComentario.getChild("Texto").getValue();
                           texto[j] = com;
                       }
                       return texto;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;        
    }
    
    public String[] getFechasCom(String titulo) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");                       
               //String[] mat = {"",""};
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element comentarios = nodo.getChild("Comentarios");
                       List listaCom = comentarios.getChildren("Comentario");
                       String texto[] = new String[listaCom.size()];
                       for(int j=0; j<listaCom.size(); j++){
                           Element txtComentario = (Element)listaCom.get(j);
                           String com = txtComentario.getChild("Fecha").getValue();
                           texto[j] = com;
                       }
                       return texto;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;        
    }
    
    public String[] getAutoresCom(String titulo) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");    
               Usuario usuario = new Usuario();
               //String[] mat = {"",""};
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element comentarios = nodo.getChild("Comentarios");
                       List listaCom = comentarios.getChildren("Comentario");
                       String texto[] = new String[listaCom.size()];
                       for(int j=0; j<listaCom.size(); j++){
                           Element txtComentario = (Element)listaCom.get(j);
                           String com = txtComentario.getAttributeValue("autor");
                           String autor = usuario.getNombre(com);
                           texto[j] = autor;
                       }
                       return texto;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;        
    }
    
    public List getAutoresByMateria(String materia) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");    
               List texto = new ArrayList();
               //Usuario usuario = new Usuario();
               //String[] mat = {"",""};
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String clase = nodo.getChild("Materia").getValue();
                   if(materia.equals(clase)){
                       Element comentarios = nodo.getChild("Comentarios");
                       List listaCom = comentarios.getChildren("Comentario");
                       //String texto[] = new String[listaCom.size()];                       
                       for(int j=0; j<listaCom.size(); j++){
                           Element txtComentario = (Element)listaCom.get(j);
                           String autor = txtComentario.getAttributeValue("autor");
                           for(int k=0; k<texto.size(); k++){
                               if(texto.get(k).equals(autor)){
                                   texto.add(autor);
                               }                               
                           }                           
                       }                       
                   }
               } return texto;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }    
    }
    
    public String[] getCalsByMateria(String materia) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");    
               Usuario usuario = new Usuario();
               //String[] mat = {"",""};
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String clase = nodo.getChild("Materia").getValue();
                   if(materia.equals(clase)){
                       Element comentarios = nodo.getChild("Comentarios");
                       List listaCom = comentarios.getChildren("Comentario");
                       String texto[] = new String[listaCom.size()];
                       for(int j=0; j<listaCom.size(); j++){
                           Element txtComentario = (Element)listaCom.get(j);                           
                           String cal = txtComentario.getChild("Calificacion").getValue();
                           texto[j] = cal;
                       }
                       return texto;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;        
    }
    
    public boolean agregarComentario(String titulo, String correo, String texto) throws JDOMException{
        try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaTemas = raiz.getChildren("Post");    
               Usuario usuario = new Usuario();           
               Date date = new Date();
               for(int i=0; i<listaTemas.size(); i++){
                   Element nodo = (Element) listaTemas.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element comentarios = nodo.getChild("Comentarios");
                       Element com = new Element("Comentario");
                       com.setAttribute("autor", correo);
                       com.setAttribute("tipousuario", usuario.tipoUsuario(correo));
                       com.addContent(new Element("Texto").setText(texto));
                       com.addContent(new Element("Fecha").setText(date.toString()));
                       com.addContent(new Element("Calificacion").setText("0"));
                       comentarios.addContent(com);     
                       XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
                       //outxml.output(doc, new FileWriter("C:\\xml\\Temas.xml"));
                       outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Temas.xml"));
                       return true;
                   }
               }
           } catch (IOException io) {
               return false;
           }
        return false;
    }
    
    public boolean calificarCom(String titulo, String correo, String cal) throws JDOMException{              
           try {               
               Document doc = builder.build(xml_file);
               Element raiz = doc.getRootElement(); 
               List listaUsuarios = raiz.getChildren("Post");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String title = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(title)){
                       Element nodo2 = nodo.getChild("Comentarios");
                       List listaComens = nodo2.getChildren("Comentario");
                       for(int j=0; j<listaComens.size(); j++){
                           Element elemCom = (Element) listaComens.get(j);
                           String autor = elemCom.getAttributeValue("autor");
                           if(correo.equals(autor)){
                               Element elemCal = elemCom.getChild("Calificacion");
                               elemCal.setText(cal);
                               XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
                               //outxml.output(doc, new FileWriter("C:\\xml\\Temas.xml"));
                               outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Temas.xml"));
                               return true;
                           }
                       }
                   }
               }    
               return false;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return false;
           }          
    }
    
    public boolean eliminarTema(String tema) throws JDOMException{
        try {               
               Document doc = builder.build(xml_file);
               Element raiz = doc.getRootElement(); 
               List listaUsuarios = raiz.getChildren("Post");               
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String titulo = nodo.getChild("Titulo").getValue();
                   if(titulo.equals(tema)){
                       if(raiz.removeContent(nodo))
                       {
                           XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
                           //outxml.output(doc, new FileWriter("C:\\xml\\Temas.xml"));
                           outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Temas.xml"));
                           return true;
                       }
                       else return false;
                   }
               }    
               return false;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return false;
           }
    }

    public String getMateriaByTitulo(String tema) throws JDOMException{
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Post");
               for(int i=0; i<listaUsuarios.size(); i++){
                    Element nodo = (Element) listaUsuarios.get(i);
                    String titulo = nodo.getChild("Titulo").getValue();
                    if(titulo.equals(tema)){
                        String materia = nodo.getChild("Materia").getValue();
                        return materia;
                    }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
}

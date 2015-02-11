/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class Usuario {
    
    private String nombre;
    private String correo;
    private String contraseña;
    private String biografia; 
    
    private SAXBuilder builder = new SAXBuilder();
    //private File xml_file = new File("C:\\xml\\Usuarios.xml");         
    private File xml_file = new File("C:\\Users\\Giovani\\Desktop\\Usuarios.xml");         
    
    public Usuario(){
        super();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    public String[] getCorreos() throws JDOMException{
        try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               String[] correos = new String[listaUsuarios.size()];
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String correoaux = nodo.getChild("Correo").getValue();
                   correos[i] = correoaux;
               }
               return correos;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
    }
    
    public String[] getTipos() throws JDOMException{
        try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               String[] correos = new String[listaUsuarios.size()];
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String tipo = nodo.getAttributeValue("tipo");
                   correos[i] = tipo;
               }
               return correos;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
    }
    
    public String[] getNombres() throws JDOMException{
        try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               String[] nombres = new String[listaUsuarios.size()];
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String nombreaux = nodo.getChild("Nombre_de_usuario").getValue();
                   nombres[i] = nombreaux;
               }
               return nombres;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
    }
    
    public String getCorreo(String nombre) throws JDOMException{              
           try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String name = nodo.getChild("Nombre_de_usuario").getValue();                   
                   if(nombre.equals(name)){
                       String email = nodo.getChild("Correo").getValue();
                       return email;
                   }
               }
               return "ninguno";
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return "ninguno";
           }          
    }
    
    public String getNombre(String correo) throws JDOMException {
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       String nombre = nodo.getChild("Nombre_de_usuario").getValue();
                       return nombre;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }

    public String getBiografia(String correo) throws JDOMException {
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       String bio = nodo.getChild("Biografia").getValue();
                       return bio;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public String getContraseña(String correo) throws JDOMException {
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       String pswd = nodo.getChild("Contrasenia").getValue();
                       return pswd;
                   }
               }
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public String getNombreMateria(String correo, int id) throws JDOMException {
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       List listaMaterias = nodo.getChildren("Materia");
                       if(listaMaterias.size() < (id+1)){
                           return "mayor";
                       } else{
                           Element mat = (Element) listaMaterias.get(id);
                           String materia = mat.getText();
                           return materia;
                       }
                   }
               } 
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return null;
           }
        return null;
    }
    
    public int numeroMaterias(String correo) throws JDOMException {        
        try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       List listaMaterias = nodo.getChildren("Materia");
                       return listaMaterias.size();
                   }
               }    
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return 0;
           }
        return 0;
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
    
    public boolean buscarUsuario(String correo) throws JDOMException{              
           try {               
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(correo.equals(email)){
                       return true;
                   }
               }
               return false;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return false;
           }          
   }    
    
    public boolean AltaUsuario(String nom, String tipoU, String correo, String pswd, String bio, 
           String mat1, String mat2, String mat3, String mat4, String mat5){
       String[] mats = {mat1, mat2, mat3, mat4, mat5};
       try{
           try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();
               Element user = new Element("Usuario");
               user.setAttribute("tipo", tipoU);
               user.addContent(new Element("Nombre_de_usuario").setText(nom));
               user.addContent(new Element("Correo").setText(correo));
               user.addContent(new Element("Contrasenia").setText(pswd));
               user.addContent(new Element("Biografia").setText(bio));
               for(int i=0; i<5; i++){
                   Element materia = new Element("Materia");
                   user.addContent(materia.setText(mats[i]));
                   materia.setAttribute("idmat", "m" + (i+1));
               }               
               raiz.addContent(user);
               XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
               //outxml.output(doc, new FileWriter("C:\\xml\\Usuarios.xml"));
               outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Usuarios.xml"));
               return true;
           } catch (IOException io) {
               return false;
           }           
       }catch(JDOMException e){
           e.getMessage();
           return false;
       }              
   }
    
    public boolean EditaUsuario(String nom, String correo, String pswd, String bio, 
           String mat1, String mat2, String mat3, String mat4, String mat5){
       String[] mats = {mat1, mat2, mat3, mat4, mat5};
       try{
           try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();                   
                   if(correo.equals(email)){                       
                       List listaMaterias = nodo.getChildren("Materia");
                       nodo.getChild("Nombre_de_usuario").setText(nom);
                       nodo.getChild("Correo").setText(correo);
                       nodo.getChild("Contrasenia").setText(pswd);
                       nodo.getChild("Biografia").setText(bio);
                       for(int j=0; j<5; j++){
                           Element mat = (Element) listaMaterias.get(j);
                           mat.setText(mats[j]);
                       }
                       XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
                       //outxml.output(doc, new FileWriter("C:\\xml\\Usuarios.xml"));
                       outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Usuarios.xml"));
                       return true;
                   }
               }               
           } catch (IOException io) {
               return false;
           }           
       }catch(JDOMException e){
           e.getMessage();
           return false;
       }              
        return false;
   }
    
    public String tipoUsuario(String correo) throws JDOMException{              
           try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();                   
                   if(correo.equals(email)){
                       String tipoUsuario = nodo.getAttributeValue("tipo");
                       return tipoUsuario;
                   }
               }
               return "ninguno";
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return "ninguno";
           }          
    }       
    
    public String tipoUsuarioByNombre(String nombre) throws JDOMException{              
           try {
               Document doc = (Document)builder.build(xml_file);
               Element raiz = doc.getRootElement();               
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String name = nodo.getChild("Nombre_de_usuario").getValue();                   
                   if(nombre.equals(name)){
                       String tipoUsuario = nodo.getAttributeValue("tipo");
                       return tipoUsuario;
                   }
               }
               return "ninguno";
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return "ninguno";
           }          
    }
    
    public boolean ingresarUsuario(String correo, String contra) throws JDOMException{              
           try {               
               Document doc = builder.build(xml_file);
               Element raiz = doc.getRootElement(); 
               List listaUsuarios = raiz.getChildren("Usuario");
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   String password = nodo.getChild("Contrasenia").getValue();
                   if(email.equals(correo) && password.equals(contra)){
                       return true;
                   }
               }    
               return false;
           } catch (IOException io) {
               System.out.println(io.getMessage());
               return false;
           }          
   }        
    
    public boolean eliminarUsuario(String correo) throws JDOMException{
        try {               
               Document doc = builder.build(xml_file);
               Element raiz = doc.getRootElement(); 
               List listaUsuarios = raiz.getChildren("Usuario");
               
               for(int i=0; i<listaUsuarios.size(); i++){
                   Element nodo = (Element) listaUsuarios.get(i);
                   String email = nodo.getChild("Correo").getValue();
                   if(email.equals(correo)){
                       if(raiz.removeContent(nodo))
                       {
                           XMLOutputter outxml = new XMLOutputter(Format.getPrettyFormat());
                           //outxml.output(doc, new FileWriter("C:\\xml\\Usuarios.xml"));
                           outxml.output(doc, new FileWriter("C:\\Users\\Giovani\\Desktop\\Usuarios.xml"));
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
}

package SaapDInterfaces;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.*;

/**
 * 
 * @author lil0
 */
public class Prueba {
    
    /**
     * 
     * @param args
     * @throws Exception
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
     public static void main (String [ ] args) throws Exception, SQLException, ClassNotFoundException,InstantiationException, IllegalAccessException
    { 
        Demo frame= new Demo();
        frame.show();
       
       /*try {
        Class.forName("org.postgresql.Driver").newInstance();
        Connection conexion= DriverManager.getConnection("jdbc:postgresql://192.168.10.151:5432/prueba");
               System.out.println("Entro conexion exitosa");
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
       
        Nodo1 nodo= new Nodo1("Cadena 1", true, "cadena 2", 1, "cadena 3");
        Nodo2 nodo2= new Nodo2("cadena 1", "cadena 2", "cadena 3", 2);
        List datos= new ArrayList();
        datos.add(nodo);
        //datos.add(nodo2);
        
        AdquisicionDatos adDatos= new getData();
        System.out.println("Conversion de Datos a XML");
        System.out.println(adDatos.convertirDatosXML(datos)+"\n");
        
        System.out.println("Conversion de Datos a JSON");
        System.out.println(adDatos.convertirDatosJSON(datos)+"\n");
        
        
//        System.out.println("Obtencion de datos a travez de URL");
//        URL ruta = new URL("http://contosocookbookibm.azurewebsites.net/api/recipes");
//        System.out.println(adDatos.obtenerDatos(ruta)+"\n");
//        
//        List datosURL=adDatos.obtenerDatos(ruta);
        
        System.out.println("Conversion a Datos de XML");
        List mixml= adDatos.convertirDatosXML(datos);
       // System.out.println(adDatos.convertirDatosFromXML(mixml)+"\n");
        
        System.out.println("Conversion a Datos de JSON");
        List misJSON= adDatos.convertirDatosJSON(datos);
        System.out.println(adDatos.convertirDatosFromJSON(misJSON));
       
        //Se sugiere que para el parse de XML a JSOn de utilice una API que se adapte a la aplicacion que se este desarrollando.
        System.out.println("Conversion de JSON  a XML");
        System.out.println(adDatos.convertirDatosJSONtoXML(misJSON));
        
        System.out.println("Obtencion de datos y retornar HASHMAP");
        Iterator iter = adDatos.obtenerDatos(datos).entrySet().iterator();
        while (iter.hasNext()) {
            System.out.println("-"+iter.next());
        }
        
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conexion= DriverManager.getConnection("jdbc:mysql://127.0.0.1/postal","root","cariño");
        List sentencias= new ArrayList();
        sentencias.add("Select * from nodo;");
        sentencias.add("Select * from nodo where bandera=true;");
        
        ProcesamientoDatos proc= new ProcessData();
        System.out.println("Envio de una consulta");
        System.out.println(proc.consulta(conexion, "Describe libro;"));
        System.out.println(proc.consulta(conexion, "Select * from nodo;")+"\n");
        
        //Convierte datos de JSON
        System.out.println(adDatos.convertirDatosFromJSON(adDatos.convertirDatosJSON(proc.consulta(conexion, "Select * from nodo;")))+"\n");
        
        System.out.println("Envio de dos consultas");
        System.out.println(proc.consulta(conexion, "Select * from libro;", "Select * from student")+"\n");
        
        System.out.println("Envio de una lista de consultas");
        System.out.println(proc.consulta(conexion, sentencias)+"\n");
        
        AlmacenamientoDatos almDatos= new SaveData();
        
        String[] columnNames = {"cadena1","bandera","cadena2","id","cadena3"};
        
        List datos3= new ArrayList();
        Object[] uno= new Object[columnNames.length];
        uno[0]= new String("Valor cadena1");
        uno[1]= new Boolean(true);
        uno[2]= new String("Valor cadena2");
        uno[3]= new Integer(23);
        uno[4]= new String("Valor cadena3");
        datos3.add(uno);
        
        Object[] dos= new Object[columnNames.length];
        dos[0]= new String("Valor cadena1.1");
        dos[1]= new Boolean(true);
        dos[2]= new String("valor cadena2.2");
        dos[3]= new Integer(23);
        dos[4]= new String("valor cadena 3.3");
        datos3.add(dos);
               
        almDatos.local(conexion,columnNames, datos3,"nodo");
        //almDatos.local(conexion, 5, datos3, "nodo");

        //Almacenamiento externo prueba con sockets;
        
        final String HOST = "localhost";
        final int PUERTO=5000;
        Socket sc= new Socket( HOST , PUERTO );
        save.externo(sc, datos);
        */
     }
}

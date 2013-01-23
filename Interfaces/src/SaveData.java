import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SaveData implements AlmacenamientoDatos{
    
    //Almacenamiento local es aquel en el mismo servidor, tomando las siguientes consideraciones:
    // -Ordenar los datos en el orden de los valores de la tabla.
    //  -Indicar el nombre de la tabla.
    //Posiblemente devolver un boolean para saber si se realizo el almacenamiento sin ningun problema.
     
    public void local(Connection conexion, String[] nombresColumnas, List<Object[]> listaDatos, String nombreTabla)
    {
        try {
            
            for (Object[] singleObject: listaDatos) {
                String sentencia="INSERT INTO "+nombreTabla; 
                String columnas="",values="";               
               
                if (nombresColumnas.length>1) {
                    for(int i=0; i< nombresColumnas.length-1;i++)
                    {
                        columnas+=nombresColumnas[i]+",";
                        values+="?,";   
                    }
                    values+="?)";
                    columnas+=nombresColumnas[nombresColumnas.length-1];
                }
                else
                {
                    columnas+=nombresColumnas[0];
                    values+="?)";
                }
                
                sentencia+="("+columnas +") Values("+values;
                
                //System.out.println(sentencia);
                PreparedStatement s = conexion.prepareStatement(sentencia);
                
                for (int i = 0; i < nombresColumnas.length; i++) {
                    s.setObject(i+1, singleObject[i]);
                }
                //System.out.println(s.toString());
                s.executeUpdate();
            }  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //Este es en caso de que sea para todas las columnas de la tabla, en caso de ser solo unas se
    //hace uso del metodo anterior.
   public void local(Connection conexion, int numColumnas, List<Object[]> listaDatos, String nombreTabla)
    {
        try {
            for (Object[] singleObject: listaDatos) {
                String sentencia="INSERT INTO "+nombreTabla+ " Values(";                
               
                if (numColumnas>1) {
                    for(int i=0; i< numColumnas-1;i++)
                    {
                        sentencia+="?,";   
                    }
                    sentencia+="?)";
                }
                else    sentencia+="?)";
                
                //System.out.println(sentencia);
                PreparedStatement s = conexion.prepareStatement(sentencia);
                
                for (int i = 0; i < numColumnas; i++) {
                    s.setObject(i+1, singleObject[i]);
                }
                //System.out.println(s.toString());
                s.executeUpdate();
            }  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //Este almacenamiento se denomina externo ya que es fuera del servidor local, este
    //puede recibir cualquier tipo de conexión Socket.
    public void externo(Socket _socket,List<Object> listaDatos )
    {
        DataOutputStream mensaje;        
        try {
                      
           for (Object object : listaDatos) {
            mensaje = new DataOutputStream(_socket.getOutputStream());
            //Recorrer la lista objeto por objeto y lo convierte a byte[] enviandolo
            //por el socket al servidor. 
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(object);
            mensaje.write(out.toByteArray());
            System.out.println(object);
            os.flush();
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error.");
        }
    }
    //Este almacenamiento se denomina externo ya que es fuera del servidor local,esto es para 
    //un servidor php
    public void externo(URL ruta,List<Object> listaDatos )
    {      
        try {
            //Se crea una conexion con un servidor PHP
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "multipart/form-data");

            //Creacion de puente para el envio de datos
            OutputStream stream = con.getOutputStream();
            DataOutputStream dos = new DataOutputStream(stream);
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Recorrer la lista objeto por objeto y lo convierte a byte[] enviandolo por el puente al servidor
            for (Object object : listaDatos) {
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(object);
            dos.write(out.toByteArray());
            } 
            //Cerramos conexion al servidor.
            dos.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error.");
        }
    }
    
    public ArrayList centradoDatos(String nombAtributo, Blob valorAtributo)
    {
        return null;
    }
    
    public List validarDatos(List <Blob> lista)
    {
        return null;
    }
    
}

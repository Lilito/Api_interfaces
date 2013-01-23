import java.net.Socket;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface AlmacenamientoDatos {
    
    //Almacenamiento local primer caso: Indicando nombre de las columnas de la tabla cuando se enviaran datos especificos a ciertas columnas.
    // -Ordenar los datos en el orden de los valores de la tabla.
    //  -Se recibe una conexion de tipo JDBC para mas informacion revisar la documentacion de JDBC Driver
    //  que permite realizar la conexion con diferentes Gestores de Base de Datos
    //  -Indicar en un Arreglo el nombre de las columnas de la siguiente manera: String[] columnNames = {"columna1","columna2","columna3"};
    //  -Enviar la lista de datos de tipo Object[].
    //  -Indicar el nombre de la tabla.
    void local(Connection conexion, String[] nombresColumnas, List<Object[]> listaDatos, String nombreTabla);
    
    //Almacenamiento local segundo caso: Indicando el total de columnas de la tabla cuando se enviaran los datos completos, 
    // y no datos especificos a ciertas columnas.
    // -Ordenar los datos en el orden de los valores de la tabla.
    //  -Se recibe una conexion de tipo JDBC para mas informacion revisar la documentacion de JDBC Driver
    //  que permite realizar la conexion con diferentes Gestores de Base de Datos
    //  -Indicar el numero de columnas de la tabla.
    //  -Enviar la lista de datos de tipo Object[].
    //  -Indicar el nombre de la tabla.
    void local(Connection conexion, int numColumnas, List<Object[]> listaDatos, String nombreTabla);
    
   //Almacenamiento externo primer caso: 
    // -Se recibe un Socket que el programador ha configurado previamente y ha extablecido una conexion con el.
    // -Se recibe una Lista de Datos de tipo Object.
    void externo(Socket _socket,List<Object> listaDatos );
    
    //Almacenamiento externo segundo caso: Este almacenamiento se denomina externo ya que es fuera del servidor local,esto es para 
    //un servidor php
    // -Se recibe una URL para realizar la conexion al servidor externo.
    // -Se recibe una Lista de Datos de tipo Object.
    void externo(URL ruta,List<Object> listaDatos );
    
    ArrayList centradoDatos(String nombAtributo, Blob valorAtributo);
    List validarDatos(List <Blob> lista);
    
              
}

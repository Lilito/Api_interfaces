package SaapDInterfaces;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/* @author rguzman */
public interface AdquisicionDatos {
    
    //Obtener Datos caso 1:
    //Es un HashMap dentro de otro HashMap donde 1HashMap obtiene el nodo asociado a la variable, 
    //que es la clave del siguiente HashMap que incluye el tipo de datos y el valor asociado.
    /*
     * @param 
     * 
     */
    HashMap obtenerDatos(List <Object> listaDatos);
    
    //Obtener Datos caso 2:
    //Esta funcion permite obtener una Lista de Objectos con los Datos adquiridos a partir de un archivo
    // -Se recibe un archivo File, que el programador debe crear previamente, dentro se accesa a la informacion.
    List obtenerDatos(File archivo);
    
    //Obtener Datos caso 3:
    //Esta funcion permite obtener una Lista de Objectos con los Datos adquiridos a partir de una URL
    // -Se recibe un objecto URL, con la ruta que el programador debe indicar previamente, dentro se accesa a la informacion.
    List obtenerDatos(URL ruta);
    
    //Esta funcion permite obtener una Lista de Objectos con los Datos adquiridos a partir de un flujo de entrada ObjectInputStream 
    //es el que procesa los datos y se ha de vincular a un objeto fileIn de la clase FileInputStream. El programador puede hacer uso
    // de las funciones incluidas en esta interface listaDatosSalida(String nombreArchivo).
    // Previamente el Programador debe crear un fujo de entrada a disco, pasándole el nombre del archivo en disco o un objeto de la clase File.
    List LeerDatos(ObjectInputStream entrada);
    
    //Esta funcion permite almacenar una Lista de Objectos a partir de un flujo de salida ObjectOutputStream el cual procesa los datos y se vincula a un objeto fileOut
    //de la clase FileOutputStream. El programador puede hacer uso de las funciones incluidas en esta interface listaDatosEntrada(String nombreArchivo).
    // -Previamente el Programador debe crear un fujo de entrada a disco, pasándole el nombre del archivo en disco o un objeto de la clase File.
    // -Se recibe la Lista de Objectos que contiene los datos a almacenar.
    void escribirDatos(ObjectOutputStream salida, List <Object> datos);
    
    //Esta funcion permite obtener un flujo de salida ObjectOutputStream es el que procesa los datos y se ha de vincular a un objeto fileOut de la clase FileOutputStream.
    //  -Se recibe el nombre del archivo al cual se desea acceder.
    ObjectOutputStream listaDatosEntrada(String nombreArchivo);
    
    //Esta funcion permite obtener un flujo de entrada ObjectInputStream es el que procesa los datos y se ha de vincular a un objeto fileIn de la clase FileInputStream.
    //  -Se recibe el nombre del archivo al cual se desea acceder.
    ObjectInputStream listaDatosSalida(String nombreArchivo);
    
    //En la siguiente funcion te permite adquirir una Lista de Objetos con Datos que se convierten a bytes para poder escribirlos en un archivo OuputStream o similar, y 
    //para leerlos cada datos debe ser un Object.
    // -Se recibe una Lista de datos a los cuales se les aplicara la conversion a Bytes.
    List convertirDatosToByte(List <Object> formatoDatos);
    
    //En la siguiente funcion te permite adquirir una Lista de Objetos con Datos convertidos de Bytes. 
    // -Se recibe una Lista de Objectos Bytes a los cuales se les aplicara la conversion y se volveran a Datos Serializados.
        List convertirDatosFromByte(List <byte[]> formatoDatos);

    //Para la lectura de datos se necesitan formatos estandares entre los que se encuentra XML, la siguiente funcion devuelve una lista de objectos
    //con items de XML.
    //  -Se recibe una lista de Objects a los cuales se les aplicara el formato XML.
    List convertirDatosXML(List <Object> formatoDatos);
    
    //Para la lectura de datos se necesitan formatos estandares entre los que se encuentra XML, la siguiente funcion devuelve una lista de objectos
    //con Datos extraidos de un formato XML.
    //  -Se recibe una lista de Objects a los cuales se les aplicara la conversion de XML a Datos sin formato.
    List convertirDatosFromXML(List <Object> formatoDatos);
    
    //Este metodo recibe una lista de objectos, estos objectos son preovenientes de una clase en comun para devolver
    //un objeto JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //Puede recibir un objeto que no provenga de ninguna clase pero lo devolvera en el siguiente formato:
    //["Hola nodo","1","esta es cadena2","23","fin del nodo"]
    List convertirDatosJSON(List <Object> formatoDatos);
    
    //Este metodo recibe una lista de objectos JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //o de este tipo ["Hola nodo","1","esta es cadena2","23","fin del nodo"], ya que los convierte a Datos normales 
    //basandose en una clase Object, devolviendo [{parametro1=Este, parametro2=true, parametro3=nodo, parametro4=1.0, parametro5=es Grande}] y de este tipo
    //basandose en el segun ejemplo [Hola nodo, 1, esta es cadena2, 23, fin del nodo].
    List convertirDatosFromJSON(List formatoDatos);
    
    //Este metodo recibe una lista de objectos JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //En caso de conocer la clase de donde son provenientes, se puede usar esta funcion para obtener la lista de objetos
    //del tipo correspodiente.
    List convertirDatosFromJSON(List formatoDatos, Class nombreClase);
    
    //Esta funcion Permite obtener un Archivo File el cual contendra los datos recibidos.
    File convertirDatosIO(List <Object> formatoDatos);
    
    //Esta funcion Permite obtener una lista de Archivos File el cual contendra los datos recibidos.
    //Cada File contendra un objeto de la Lista recibida. Por lo tanto la Lista de Files sera del mismo tamaño que la Lista de Objetos recibida.
    List <File> convertirDatos(List <Object> formatoDatos);

    
    //Este metodo recibe una lista de Objectos que contiene codigo JSON, para convertirlo a un item XML
        List convertirDatosJSONtoXML(List formatoDatos);
        
        List convertirDatosXMLtoJSON(List formatoDatos);
}

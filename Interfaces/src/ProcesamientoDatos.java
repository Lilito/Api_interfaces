import java.sql.Connection;
import java.util.List;
/**
 * Interface de almacenamiento de datos, donde se han definido tres tipos de almacenamiento los cuales pueden ser implementados por cualquier aplicacion de datos, ademas de incluir un metodo para
la validacion de informacion.
 * @author lil0
 */

public interface ProcesamientoDatos {

    //Modo conectado, se , para mas informacion revisar la documentacion de JDBC Driver.
    //Retornara una Lista la cual, contiene un conjunto de Listas perteneciente a cada fila de la
    //consulta que es devuelta, contiendo objetos que son cada uno de los valores de cada fila.
    /**
     *Función consulta caso 1.
     * @param conexion Se utilizara una Conexión de tipo Connection JDBC, para mas información revisar la documentación de JDBC Driver.
     * @param consulta Se recibe una sentencia a ejecutar en la base de datos indicada.
     * @return retornara una Lista de objetos la cual, contiene un conjunto de Listas perteneciente a cada fila de la consulta realizada, contiendo objetos que son cada uno de los valores de cada fila.
     */
    List consulta (Connection conexion, String consulta);
    
    //Retornara una Lista la cual, contiene un conjunto de Listas y cada conjunto de Listas corresponde
    //a cada uno de los resultados de cada sentencia, es decir que ahora estas listas contienen una lista por
    //cada fila que le es regresada, y dentro contiene objetos de cada fila.
    List consulta (Connection conexion, String consultaA, String consultaB);
    
    //Retornara una Lista la cual, contiene un conjunto de Listas y cada conjunto de Listas corresponde
    //a cada uno de los resultados de cada sentencia, es decir que ahora estas listas contienen una lista por
    //cada fila que le es regresada, y dentro contiene objetos de cada fila.
    List consulta (Connection conexion, List <String> sentencias);
    //List consulta (Connection conexion, List sentencias);
    
    //Modo no conectado (pendiente)
}

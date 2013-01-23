import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessData implements ProcesamientoDatos{
    
    //Retornara una Lista la cual, contiene un conjunto de Listas perteneciente a cada fila de la
    //consulta que es devuelta, contiendo objetos que son cada uno de los valores de cada fila.
    public List consulta (Connection conexion, String consulta){ 
        List data = new ArrayList();
        try {
            Statement s = conexion.createStatement();
            ResultSet rst= s.executeQuery(consulta);
            ResultSetMetaData rd = rst.getMetaData(); // Obtenemos el metadata 
            int columnas= rd.getColumnCount(); // devuelve el numero de campos
            
            while(rst.next())
            {
                
               List row = new ArrayList();               
                for (int i = 1; i <= columnas; i++) {
                 row.add(rst.getString(i));     
                }
                data.add(row);
            }
        } catch (Exception e) {
            data.add(e.getMessage());
        }
        
        return data; 
    }
    
    //Retornara una Lista la cual, contiene un conjunto de Listas y cada conjunto de Listas corresponde
    //a cada uno de los resultados de cada sentencia, es decir que ahora estas listas contienen una lista por
    //cada fila que le es regresada, y dentro contiene objetos de cada fila.
    
    public List consulta (Connection conexion, String consultaA, String consultaB){
        List data = new ArrayList();
        try {
            
            Statement s = conexion.createStatement();
            
            //Operaciones sobre la segunda consulta.
            
            ResultSet rstA= s.executeQuery(consultaA);
            ResultSetMetaData rd = rstA.getMetaData(); // Obtenemos el metadata de ResulsetA
            int columnas= rd.getColumnCount(); // devuelve el numero de campos  
            List _statementA= new ArrayList();
           
            while(rstA.next())
            {
               List row = new ArrayList();
                    for (int i = 1; i <= columnas; i++) {
                    row.add(rstA.getString(i));     
                    }  
               _statementA.add(row);
            }
            
            //Operaciones sobre la segunda consulta.
            
            ResultSet rstB=s.executeQuery(consultaB);
            ResultSetMetaData rdB = rstB.getMetaData(); // Obtenemos el metadata de ResulsetB
            int columnasB= rdB.getColumnCount(); // devuelve el numero de campos
            List _statementB= new ArrayList();
            
            while(rstB.next())
            {
                List row = new ArrayList();
                    for (int i = 1; i <= columnasB; i++) {
                    row.add(rstB.getString(i));     
                        }
                _statementB.add(row);
            }
            
            data.add(_statementA);
            data.add(_statementB);
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return data;
    }
    
    //Retornara una Lista la cual, contiene un conjunto de Listas y cada conjunto de Listas corresponde
    //a cada uno de los resultados de cada sentencia, es decir que ahora estas listas contienen una lista por
    //cada fila que le es regresada, y dentro contiene objetos de cada fila.
    
    public List consulta (Connection conexion, List <String> sentencias){
        List data = new ArrayList();
        try {
            
            Statement s = conexion.createStatement();
            for (Object singleObject: sentencias) {
                ResultSet rst= s.executeQuery(singleObject.toString());
                ResultSetMetaData rd = rst.getMetaData(); // Obtenemos el metadata 
                int columnas= rd.getColumnCount(); // devuelve el numero de campos
                List _statement= new ArrayList();
                while(rst.next())
                {
                    List row = new ArrayList();
                    for (int i = 1; i <= columnas; i++) {
                    row.add(rst.getString(i));     
                        }
                    _statement.add(row);
                }
                data.add(_statement);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return data;
    }
    
}

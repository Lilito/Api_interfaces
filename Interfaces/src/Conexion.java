import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;

public interface Conexion {
    
    List detectarDispositivo();
    List detectarDispositivo(FileInputStream listaDispositivos);
    Connection abrirConexion(int idRed, char idDispositivo, int idPuerto);
    void cerrarConexion(int idRed);
    void cancelarConexion();
    
    String driver(String driver);
    String url(String url);
    String usuario(String usuario);
    String password(String password);
    
    Connection abrirConexion();
    Connection abrirConexion(int idDispositivo, int idPuerto);
    Connection abrirConexion(FileInputStream archivoConexion);
    
    void cerrarConexion(String direccion);
}

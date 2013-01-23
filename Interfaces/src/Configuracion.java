import java.io.FileInputStream;
import java.util.List;

public interface Configuracion {
    
    void puerto(String nombrePuerto);
    void puerto(int numeroPuerto);
    void datosPuerto(List <String> datosConfiguracion);
    void datosConfiguracion(FileInputStream archivoConfiguracion);
    
}

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.List;


public class getData implements AdquisicionDatos {

    private static final String TAG_DOCUMENTO = "Documento";
    private static final String TAG_NODO = "item";
    private static final String TAG_ID = "id";
    private static final String TAG_PROP = "prop";
    // Codificacion
    private static final String XML_VERSION = "1.0";
    //private static final String XML_ENCODING = "UTF-8";
    private static final String XML_ENCODING = "ISO-8859-1";
    // Objetos
    private Document documentoXML;
    private Element descarga = null;
    Element elemento;
    //Para cualquier objeto generico de la clase

    //Es un HashMap dentro de otro HashMap donde 1HashMap obtiene el nodo asociado a la variable, 
    //que es la clave del siguiente HashMap que incluye el tipo de datos y el valor asociado.
    public HashMap obtenerDatos(List<Object> listaDatos) {
        HashMap mapa = new HashMap();
        try {
            int id = 1;
            for (Object singleObject : listaDatos) {
                Class clase = Class.forName(singleObject.getClass().getName());
                Field[] propieties = clase.getDeclaredFields();
                for (int i = 0; i < propieties.length; i++) {
                    HashMap submapa = new HashMap();
                    Field field = propieties[i];
                    submapa.put(field.getType(), field.get(singleObject).toString());
                    mapa.put("nodo" + id + field.getName(), submapa);
                }
                id++;
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error" + e);
        }
        return mapa;
    }

    public List obtenerDatos(File archivo) {
        List data = new ArrayList();
        if (archivo.exists()) {
            if (archivo.isFile()) {
                // anexar el contenido del archivo a areaSalida
                try {
                    BufferedReader entrada = new BufferedReader(new FileReader(archivo));
                    String texto;
                    while ((texto = entrada.readLine()) != null) {
                        data.add(texto + "\n");
                    }
                } catch (Exception e) {
                    data.add(e);
                }
            } else {
                data.add("No es un archivo");
            }
        } // no es archivo ni directorio, mostrar mensaje de error
        else {
            data.add("no existe el archivo\n");
        }
        return data;
    }

    public List obtenerDatos(URL ruta) {
        List data = new ArrayList();
        try {
            URLConnection _Connection = ruta.openConnection();
            DataInputStream dis = new DataInputStream(_Connection.getInputStream());
            String inputLine;
            while ((inputLine = dis.readLine()) != null) {
                data.add(inputLine);
            }
            dis.close();
        } catch (MalformedURLException me) {
            data.add("MalformedURLException: " + me.getMessage());
        } catch (IOException ioe) {
            data.add("IOException: " + ioe.getMessage());
        }
        return data;
    }

    public List LeerDatos(ObjectInputStream entrada) {
        List data = new ArrayList();
        try {
            if (entrada != null) {
                while (true) {
                    data.add(entrada.readObject());
                }
            }
        } catch (Exception e) {
            data.add("Ocurrio un error: " + e.getMessage());
        }
        return data;
    }

    public void escribirDatos(ObjectOutputStream salida, List<Object> datos) {
        try {
            if (salida != null) {
                for (Object singleObject : datos) {
                    salida.writeObject(singleObject);
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

    }

    public ObjectOutputStream listaDatosEntrada(String nombreArchivo) {
        FileOutputStream fi;
        ObjectOutputStream salida = null;
        try {
            fi = new FileOutputStream(nombreArchivo);
            salida = new ObjectOutputStream(fi);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return salida;
    }

    public ObjectInputStream listaDatosSalida(String nombreArchivo) {
        FileInputStream fi;
        ObjectInputStream entrada = null;
        try {
            fi = new FileInputStream(nombreArchivo);
            entrada = new ObjectInputStream(fi);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return entrada;
    }

    //Se convierten a bytes para poder escribirlos en un archivo OuputStream o similar, y 
    //para leerlos debe ser en un objeto
    public List convertirDatosToByte(List<Object> formatoDatos) {
        List data = new ArrayList();
        try {
            for (Object singleObject : formatoDatos) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(out);
                os.writeObject(singleObject);
                data.add(out.toByteArray());
            }
        } catch (Exception e) {
            data.add(e.getMessage());
        }
        return data;
    }

    public List convertirDatosFromByte(List<byte[]> formatoDatos) {
        List data = new ArrayList();
        try {
            for (byte[] singleObject : formatoDatos) {
                ByteArrayInputStream in = new ByteArrayInputStream(singleObject);
                ObjectInputStream is = new ObjectInputStream(in);
                data.add(is.readObject());
            }
        } catch (Exception e) {
            data.add(e.getMessage());
        }
        return data;
    }

    //Es necesario que sean objectos provenientes de una clase, para su mejor transformacion. Esta no incluye cabecera, debe ser creada por el programador.
    public List convertirDatosXML(List formatoDatos) {
        List convertidos = new ArrayList();
        try{
            for (Object singleObject : formatoDatos) {
            XStream xstream = new XStream();
            convertidos.add(xstream.toXML(singleObject));
        }
        }catch (Exception e) {
            convertidos.add("Invalid format. "+e.getMessage());
        }
       
        return convertidos;
        /*generaDocumentoXML();
        int id = 1;
        for (Object singleObject : formatoDatos) {
            // 1. Crear elemento
            elemento = documentoXML.createElement(TAG_NODO);
            // 2. Asignar un atributo
            elemento.setAttribute(TAG_ID, "" + id);
            // 3. Aniadir elemento al documento
            descarga.appendChild(elemento);
            generarXMLItem(singleObject);
            id++;
        }
        return generaTextoXML();*/
    }
    //Pendiente... marca un error...

    public List convertirDatosFromXML(List formatoDatos) {
        List convertidos = new ArrayList();
        try {
                XStream xstream = new XStream(new DomDriver());
                for (Object singleObject : formatoDatos) {
                convertidos.add(xstream.fromXML(singleObject.toString()));
                }
        
        } catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
         return convertidos;
    }

    //Este metodo recibe una lista de objectos, estos objectos son preovenientes de una clase en comun para devolver
    //un objeto JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //Puede recibir un objeto que no provenga de ninguna clase pero lo devolvera en el siguiente formato:
    //["Hola nodo","1","esta es cadena2","23","fin del nodo"]
    public List convertirDatosJSON(List formatoDatos) {
        //Usamos la libreria de Google GSON
        Gson gson = new Gson();
        List convertidos = new ArrayList();
        try {
                for (Object singleObject : formatoDatos) {
            convertidos.add(gson.toJson(singleObject));
            }
        } catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
        
        return convertidos;
    }

    //Este metodo recibe una lista de objectos JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //o de este tipo ["Hola nodo","1","esta es cadena2","23","fin del nodo"], ya que los convierte a Datos normales 
    //basandose en una clase Object, devolviendo [{parametro1=Este, parametro2=true, parametro3=nodo, parametro4=1.0, parametro5=es Grande}] y de este tipo
    //basandose en el segun ejemplo [Hola nodo, 1, esta es cadena2, 23, fin del nodo].
    public List convertirDatosFromJSON(List formatoDatos) {
        //Usamos la libreria de Google GSON
        Gson gson = new Gson();
        List convertidos = new ArrayList();
        try{
        for (Object singleObject : formatoDatos) {
            convertidos.add(gson.fromJson(singleObject.toString(), Object.class));
        }
        }catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
        return convertidos;
    }

    //Este metodo recibe una lista de objectos JSON de este tipo {"parametro1":"Este","parametro2":true,"parametro3":"nodo","parametro4":1,"parametro5":"es Grande"}
    //En caso de conocer la clase de donde son provenientes, se puede usar esta funcion para obtener la lista de objetos
    //del tipo correspodiente.
    public List convertirDatosFromJSON(List formatoDatos, Class nombreClase) {
        //Usamos la libreria de Google GSON
        Gson gson = new Gson();
        List convertidos = new ArrayList();
        try{
        for (Object singleObject : formatoDatos) {
            convertidos.add(gson.fromJson(singleObject.toString(), nombreClase));

        }
        }catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
        return convertidos;
    }

    //Este metodo recibe una lista de Objectos que contiene un item de XML, para convertirlo a un Objeto JSON
    public List convertirDatosXMLtoJSON(List formatoDatos) {
        List convertidos = new ArrayList();
         Gson gson = new Gson();
         try {
            List misXML=convertirDatosFromXML(formatoDatos);
            for (Object singleObject : misXML) {
                convertidos.add(gson.toJson(singleObject));
            }
         }catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
        return convertidos;
    }

    //Este metodo recibe una lista de Objectos que contiene codigo JSON, para convertirlo a un item XML
    public List convertirDatosJSONtoXML(List formatoDatos) {
        List convertidos = new ArrayList();
        try {
        List misJSON=convertirDatosFromJSON(formatoDatos);
        for (Object singleObject : misJSON) {
            XStream xstream = new XStream();
            convertidos.add(xstream.toXML(singleObject));
        }
        }catch (Exception e) {
            convertidos.add("Invalid format."+e.getMessage());
        }
        return convertidos;
    }

    public File convertirDatosIO(List<Object> formatoDatos) {
        File _myfile = new File("media.txt");
        FileOutputStream fileOut;
        ObjectOutputStream is;
        try {
            fileOut = new FileOutputStream(_myfile);
            is = new ObjectOutputStream(fileOut);
            for (Object singleObject : formatoDatos) {
                is.writeObject(singleObject);
            }
            is.flush();
            is.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return _myfile;
    }

    public List<File> convertirDatos(List<Object> formatoDatos) {
        List data = new ArrayList();
        int id = 1;

        try {
            for (Object singleObject : formatoDatos) {
                File _myfile = new File("media" + id + ".data");
                //Es posible reutilizar los metodos de la misma interface para
                //los buffers de lectura y escritura de archivos
                FileOutputStream fileOut = new FileOutputStream(_myfile);
                ObjectOutputStream is = new ObjectOutputStream(fileOut);
                is.writeObject(singleObject);
                is.close();
                data.add(_myfile);
                id++;
            }
        } catch (Exception e) {
            data.add("Error. " + e.getMessage());
        }
        return data;
    }

    //Metodos para la conversion a XML
    public void generaDocumentoXML() {
        try {
            // 1. Crear objeto DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 2. A partir del objeto DocumentBuilderFactory crear un objeto DocumentBuilder 
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            // 3. Generar el documento XML
            documentoXML = docBuilder.newDocument();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        // 4. Crear el elemento "descargas"
        descarga = documentoXML.createElement(TAG_DOCUMENTO);
        // 5. Agregar al documento principal
        documentoXML.appendChild(descarga);
    }

    private List generaTextoXML() {
        StringWriter strWriter = null;
        com.sun.org.apache.xml.internal.serialize.XMLSerializer seliarizadorXML;
        OutputFormat formatoSalida;
        List items = new ArrayList();
        try {
            seliarizadorXML = new com.sun.org.apache.xml.internal.serialize.XMLSerializer();
            strWriter = new StringWriter();
            formatoSalida = new OutputFormat();
            // 1. Establecer el formato
            formatoSalida.setEncoding(XML_ENCODING);
            formatoSalida.setVersion(XML_VERSION);
            formatoSalida.setIndenting(true);
            formatoSalida.setIndent(4);
            // 2. Definir un objeto donde se generara el codigo
            seliarizadorXML.setOutputCharStream(strWriter);
            // 3. Aplicar el formato
            seliarizadorXML.setOutputFormat(formatoSalida);
            // 4. Serializar documento XML
            seliarizadorXML.serialize(documentoXML);
            strWriter.close();
        } catch (IOException ioEx) {
            System.out.println("Error : " + ioEx);
        }

        //Tenemos la cadena con todo el XML y los items, 
        //ahora es momento de integrar cada item en un objeto
        //la cabecera de XML se incluye en el primer objeto de la lista

        String[] temp = strWriter.toString().split("</item>");
        for (int i = 0; i < temp.length; i++) {
            if (i < temp.length - 1) {
                items.add(temp[i] + "</item>");
            } else {
                items.add(temp[i]);
            }
        }
        return items;
    }

    public void generarXMLItem(Object Datos) {
        try {

            Element item;
            Class clase = Class.forName(Datos.getClass().getName());
            Field[] propieties = clase.getDeclaredFields();

            for (int i = 0; i < propieties.length; i++) {
                Field field = propieties[i];
                // a. Crear item
                item = documentoXML.createElement(TAG_PROP + i);
                // b. Asignar un dato al item
                item.appendChild(documentoXML.createTextNode("" + field.get(Datos).toString()));
                // c. Aniadir el item
                elemento.appendChild(item);
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error" + e);
        }
    }
    
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Clase para leer y obtener la informaci&oacute;n de un archivo xml. 
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class ParserXML {

	private List<Arista> aristas;
	private List<Vertice> vertices;
	private String nombre_archivo;

    /**
     * Constructor que recibe un nombre de archivo.
     * @param nombre_archivo Nombre del archivo xml del que se va a obtener la informaci&oacute;n.
     */
	public ParserXML(String nombre_archivo) {
		aristas = new ArrayList<Arista>();
		vertices = new ArrayList<Vertice>();
		this.nombre_archivo = nombre_archivo;
		System.out.println("Abriendo archivo " + this.nombre_archivo + ".");
	}

    /**
     * Regresa las aristas que contiene el archivo xml.
     * @return Arista Arreglo de aristas que contiene el archivo.
     */
	public Arista [] obtener_Aristas() {
		return aristas.toArray(new Arista[0]);
	}

    /**
     * Regresa los v&eacute;rtices que contiene el archivo xml.
     * @return Vertice Arreglo de v&eacute;rtices que contiene el archivo.
     */
	public Vertice [] obtener_Vertices() {
		return vertices.toArray(new Vertice[0]);
	}

    /**
     * Obtiene toda la información del archivo xml.
     */
    public void obtener_Informacion() throws FileNotFoundException, XMLStreamException {
        Vertice vertice = null;

        Arista arista = null;
        Vertice vertice_u = null;
        Vertice vertice_v = null;
        int ancho_banda = 0;

        String informacion = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader lector = factory.createXMLStreamReader(new FileInputStream(new File(nombre_archivo)));

        System.out.println("Leyendo datos ...");
        while (lector.hasNext()) {
        	int Event = lector.next();

            switch (Event) {
                case XMLStreamConstants.START_ELEMENT: {

                    if ("Sucursales".equals(lector.getLocalName()))
                    	vertices = new ArrayList<Vertice>();

                    if ("Canales".equals(lector.getLocalName()))
                    	aristas = new ArrayList<Arista>();

                    break;
                }

                case XMLStreamConstants.CHARACTERS: {
                     informacion = lector.getText().trim();
                     break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    switch (lector.getLocalName()) {
                        case "Sucursal": {
                        	vertice = new Vertice(informacion);
                           	vertice.asignar_Padre(vertice);
                            vertices.add(vertice);
                            break;
                        }

                        case "Sucursal_a": {
                            vertice_u = new Vertice(informacion);
                            vertice_u.asignar_Padre(vertice_u);
                            break;
                        }
                        
                        case "Sucursal_b": {
                            vertice_v = new Vertice(informacion);
                            vertice_v.asignar_Padre(vertice_v);
                            break;
                        }
                        
                        case "Ancho_Banda": {
                            ancho_banda = Integer.parseInt(informacion);
                            break;
                        }
                        
                        case "Canal": {
                            arista = new Arista(vertice_u, vertice_v, ancho_banda);
                            aristas.add(arista);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("Fin de lectura del archivo.");
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase para generar un archivo en formato .DOT para generar visualmente la gr&aacute;fica.
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class Generar_Grafica {

	/**
	 * Constructor de la clase, crea un archivo con nombre Grafica.dot donde guarda la informaci&oacute;n para representar el &aacute;rbol
	 * generador de peso m&aacute;ximo.
	 * @param aristas Lista de aristas que conforman al &aacute;rbol generador de peso m&aacute;ximo.
	 * @param nombre_archivo Nombre del archivo donde se va a guardar la informaci&oacute;n.
	 * @param nombre_imagen Nombre de la imagen donde se va a guardar la gr&aacute;fica.
	 */
	public Generar_Grafica(ArrayList<Arista> aristas, String nombre_archivo, String nombre_imagen) {
		PrintWriter escritor = null;
		try {
			System.out.println("Generando archivo " + nombre_archivo + " ...");
			escritor = new PrintWriter(new BufferedWriter(new FileWriter(new File("archivosExtra/odt/" + nombre_archivo))));
			escritor.append("graph G {\n");
			String linea, u, v;
			for (Arista arista : aristas) {
				u = arista.obtener_u().obtener_Nombre().replaceAll(" ", "_");
				v = arista.obtener_v().obtener_Nombre().replaceAll(" ", "_");
				linea = "\t" + u + " -- " + v;
				linea += " [label=\"" + arista.obtener_Peso() + "\"];\n";
				escritor.append(linea);
			}
			escritor.append("}");
			System.out.println("Archivo generado.");

			System.out.println("Generando imagen " + nombre_imagen + " ...");
			final Process proceso = Runtime.getRuntime().exec("dot -Tjpg -o archivosExtra/graficas/" + nombre_imagen + ".jpg archivosExtra/odt/" + nombre_archivo);
			System.out.println("Imagen generada.");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			escritor.close();
		}
	}

		/**
	 * Constructor de la clase, crea un archivo con nombre Grafica.dot donde guarda la informaci&oacute;n para representar el &aacute;rbol
	 * generador de peso m&aacute;ximo.
	 * @param aristas Arreglo de aristas que conforman al &aacute;rbol generador de peso m&aacute;ximo.
	 * @param nombre_archivo Nombre del archivo donde se va a guardar la informaci&oacute;n.
	 * @param nombre_imagen Nombre de la imagen donde se va a guardar la gr&aacute;fica.
	 */
	public Generar_Grafica(Arista [] aristas, String nombre_archivo, String nombre_imagen) {
		PrintWriter escritor = null;
		try {
			escritor = new PrintWriter(new BufferedWriter(new FileWriter(new File("archivosExtra/odt/" + nombre_archivo))));
			escritor.append("graph G {\n");
			String linea, u, v;
			for (Arista arista : aristas) {
				u = arista.obtener_u().obtener_Nombre().replaceAll(" ", "_");
				v = arista.obtener_v().obtener_Nombre().replaceAll(" ", "_");
				linea = "\t" + u + " -- " + v;
				linea += " [label=\"" + arista.obtener_Peso() + "\"];\n";
				escritor.append(linea);
			}
			escritor.append("}");

			final Process proceso = Runtime.getRuntime().exec("dot -Tjpg -o archivosExtra/graficas/" + nombre_imagen + ".jpg archivosExtra/odt/" + nombre_archivo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			escritor.close();
		}
	}
}
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.stream.XMLStreamException;

/**
 * Implementaci&oacute;n del algoritmo de Kruskal modificado para ocupar los pesos mayores primero.
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class Kruskal_Modificado {

	static class Ordenamiento implements java.util.Comparator<Arista> {
 
 		/**
 		 * Ocupado para MaxHeap (decreciente).
 		 * @return 0 si son iguales, > 1 si v es mayor, < 1 si u es mayor.
 		 */
		public int compare(Arista u, Arista v) {
			return v.obtener_Peso() - u.obtener_Peso();
		}
	}

	private PriorityQueue<Arista> cola_prioridad;
	private ArrayList<Arista> aristas_finales;
	private Conjuntos_Ajenos conjunto_ajeno;

	/**
	 * Constructor que recibe las partes de una gr&aacute;fica, conjunto de v&eacute;rtices y conjunto de aristas.
	 * @param V Arreglo de v&eacute;rtices de la gr&aacute;fica.
	 * @param A Arreglo de aristas de la gr&aacute;fica.
	 */
	public Kruskal_Modificado(Vertice [] V, Arista [] A) {

		this.aristas_finales = new ArrayList<Arista>();
		this.cola_prioridad = new PriorityQueue<Arista>(A.length, new Ordenamiento());

		for (Arista a : A) {
			this.cola_prioridad.add(a);
		}

		this.conjunto_ajeno = new Conjuntos_Ajenos(V);
	}

	/**
	 * Aplica el algorimto de Kruskal modificado para peso m&aacute;ximo.
	 */
	public void Kruskal() {
		Arista a;
		Vertice u, v;
		Vertice r, s;
		while(!cola_prioridad.isEmpty()) {
			a = cola_prioridad.poll();

			u = a.obtener_u();
			v = a.obtener_v();

			System.out.println("\nAnalizando canal entre las sucursales de " + u + " y " + v + " con ancho de banda " + a.obtener_Peso());
			
			r = conjunto_ajeno.find(u);
			s = conjunto_ajeno.find(v);
			if (!r.equals(s)) {
				System.out.println("Agregando a la red el canal entre las sucursales de " + u + " y " + v + " con ancho de banda " + a.obtener_Peso());

				aristas_finales.add(a);
				conjunto_ajeno.union(r, s);
			} else {
				System.out.println("Descartando canal entre las sucursales de " + u + " y " + v + " con ancho de banda " + a.obtener_Peso());
				System.out.println("Las sucursales se encuentran conectadas por la sucursal de " + r);
			}
		}
	}

	/**
	 * Muestra el proceso para obtener la soluci&oacute;n as&iacute; como la soluci&oacute;n despu&eacute;s de aplicar de algoritmo de Kruskal.
	 */
	public void mostrar_Solucion() {
		this.Kruskal();
		System.out.println("\nIniciando proceso para obtener solución ...");
		ArrayList<Arista> arbol_generador_peso_maximo = this.aristas_finales;
		System.out.println("Proceso terminado.\n");

		int ancho_banda_total = 0;
		System.out.println("Canales que maximizan el ancho de banda total.");
		for (Arista arista : arbol_generador_peso_maximo) {
			System.out.println("Canal entre las sucursales de " + arista.obtener_u() + " y " + arista.obtener_v() + " con ancho de banda " + arista.obtener_Peso());
			ancho_banda_total += arista.obtener_Peso();
		}
		System.out.println("Ancho de Banda Total: " + ancho_banda_total);
		System.out.println();
	}

	/**
	 * Obtiene las aristas del &aacute;rbol generador de peso m&aacute;ximo.
	 * @return Una lista de las aristas del &aacute;rbol generador de peso m&aacute;ximo.
	 */
	public ArrayList<Arista> aristas_finales() {
		return this.aristas_finales;
	}
	/**
	 * Main
	 */
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException, IOException {

		ParserXML parser = new ParserXML("archivosExtra/Sucursales.xml");
		parser.obtener_Informacion();
		Arista [] aristas = parser.obtener_Aristas();
		Vertice [] vertices = parser.obtener_Vertices();
		Generar_Grafica generador;

		Kruskal_Modificado kruskal = new Kruskal_Modificado(vertices, aristas);

		Scanner in = new Scanner(System.in);
		System.out.println("¿Tienes instalado graphviz?[S/n]");
		String respuesta = in.next();

		if (respuesta.equalsIgnoreCase("n")) {
			kruskal.mostrar_Solucion();
		} else {

			System.out.println("¿Deseas graficar la red original?[S/n]");
			respuesta = in.next();
			Process proceso;

			if (respuesta.equalsIgnoreCase("s")) {
				generador = new Generar_Grafica(aristas, "Red_Original.dot", "Red_Original");
			} else if (respuesta.equalsIgnoreCase("n")) {	
			} else {
				System.out.println("Orden inválida");
			}

			kruskal.mostrar_Solucion();

			System.out.println("¿Deseas graficar la nueva red?[S/n]");
			respuesta = in.next();

			if (respuesta.equalsIgnoreCase("s")) {
				generador = new Generar_Grafica(kruskal.aristas_finales(), "Red_Nueva.dot", "Red_Nueva");
			} else if (respuesta.equalsIgnoreCase("n")) {	
			} else {
				System.out.println("Orden inválida");
			}
		}	
	}
}
/**
 * Calse para representar un v&eacute;rtice con rango y padre.
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class Vertice {

	private String nombre;
	private Vertice padre;
	private int rango;

	/**
	 * Constructor que recibe un nombre y asigna null a padre y 0 a rango.
	 * @param nombre Nombre del v&eacute;rtice.
	 */
	public Vertice(String nombre) {
		this(nombre, null, 0);
	}

	/**
	 * Constructor que recibe el nombre, padre y rango del v&eacute;rtice.
	 * @param nombre Nombre del v&eacute;rtice.
	 * @param padre V&eacute;rtice padre del v&eacute;rtice.
	 * @param rango Rango del v&eacute;rtice.
	 */
	public Vertice(String nombre, Vertice padre, int rango) {
		this.nombre = nombre;
		this.padre = padre;
		this.rango = rango;
	}

	/**
	 * Asigna el v&eacute;rtice padre del v&eacute;rtice.
	 * @param padre V&eacute;rtice con rango mayor.
	 */
	public void asignar_Padre(Vertice padre) {
		this.padre = padre;
	}

	/**
	 * Aumenta en uno el rango del v&eacute;rtice.
	 */
	public void aumentar_Rango() {
		this.rango++;
	}

	/**
	 * Obtiene el nombre del v&eacute;rtice.
	 * @return Nombre del v&eacute;rtice.
	 */
	public String obtener_Nombre() {
		return this.nombre;
	}

	/**
	 * Obtiene el v&eacute;rtice padre del v&eacute;rtice.
	 * @return V&eacute;rtice con rango mayor y del que proviene.
	 */
	public Vertice obtener_Padre() {
		return this.padre;
	}

	/**
	 * Obtiene el rango del v&eacute;rtice.
	 * @return El rango actual del v&eacute;rtice.
	 */
	public int obtener_Rango() {
		return this.rango;
	}

	/**
	 * Verifica que el nombre del v&eacute;rtice pasado sea igual al de &eacute;ste v&eacute;rtice.
	 * @param vertice V&eacute;rtice que ser&aacute; comparado. 
	 * @return true Si los nombres son iguales, false en otro caso.
	 */
	public boolean equals(Vertice vertice) {
		if (this.nombre.equals(vertice.nombre)) {
			return true;
		}
		return false;
	}

	/**
	 * Representaci&oacute;n de un v&eacute;rtice.
	 * @return Cadena que representa a un v&eacute;rtice.
	 */
	public String toString() {
		return this.nombre;
	}
}
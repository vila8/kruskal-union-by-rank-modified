/**
 * Clase para reprensentar una arista con peso. 
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class Arista {

	private Vertice u;
	private Vertice v;
	private int peso;

	/**
	 * Constructor de una arista.
	 * @param u Primer v&eacute;rtice de la arista.
	 * @param v Segundo v&eacute;rtice de la arista.
	 * @param peso Peso de la arista.
	 */
	public Arista(Vertice u, Vertice v, int peso) {
		this.u = u;
		this.v = v;
		this.peso = peso;
	}

	/**
	 * Obtiene el primer v&eacute;rtice de la arista.
	 * @return V&eacute;rtice "u" de la arisita.
	 */
	public Vertice obtener_u() {
		return this.u;
	}

	/**
	 * Obtiene el segundo v&eacute;rtice de la arista.
	 * @return V&eacute;rtice "v" de la arisita.
	 */
	public Vertice obtener_v() {
		return this.v;
	}

	/**
	 * Obtiene el valor del peso de la arista.
	 * @return El peso de la arista.
	 */
	public int obtener_Peso() {
		return this.peso;
	}

	/**
	 * Verifica que los v&eacute;rtices de la arista pasada sean iguales a los de &eacute;sta arista y que tengan los mismos pesos.
	 * @param arista Arista que ser&aacute; comparada. 
	 * @return true Si los dos v&eacute;rtices son iguales y tienen el mismo peso, false en otro caso.
	 */
	public boolean equals(Arista arista) {
		if (this.u.equals(arista.u) && this.v.equals(arista.v) && this.peso == arista.peso) {
			return true;
		} else if (this.u.equals(arista.v) && this.v.equals(arista.u) && this.peso == arista.peso) {
			return true;
		}
		return false;
	}

	/**
	 * Representaci&oacute;n de una arista.
	 * @return Cadena que representa a una arista.
	 */
	public String toString() {
		return "Sucursal a " + this.u + "\nSucursal b " + this.v + "\nAncho Banda " + this.peso;
	}
}
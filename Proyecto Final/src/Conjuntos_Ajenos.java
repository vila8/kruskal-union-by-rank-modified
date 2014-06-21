/**
 * Clase para reprensentar un conjunto ajeno. 
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @author Aldo Adrian Espinosa Serrano
 * @version Junio de 2014
 */
public class Conjuntos_Ajenos {
	
	private Vertice [] vertices;
	private int n;

	/**
	 * Crea un conjunto ajeno con los v&eacute;rtices pasados como par&aacute;metro.
	 * @param V Arreglo de v&eacute;rtices.
	 */
	public Conjuntos_Ajenos(Vertice [] V) {
		n = V.length;
		vertices = new Vertice[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = V[i];
		}
	}

	/**
	 * Busca al padre del v&eacute;rtice.
	 * @param v V&eacute;rtice del que se quiere obtener el padre.
	 */
	public Vertice find(Vertice v) {
		int i;
		for (i = 0; i < n; i++)
			if (vertices[i].equals(v))
				break;
		Vertice padre = vertices[i].obtener_Padre();
		if (!vertices[i].equals(padre)) {
			vertices[i].asignar_Padre(this.find(padre));
		}
		return vertices[i].obtener_Padre();
	}

	/**
	 * Une la ra&iacute;z del v&eacute;rtice con el menor a la ra&iacute;z del v&eacute;rtice con el rango mayor.
	 * Si son iguales incrementa el rango de v en 1.
	 * @param u Primer v&eacute;rtice
	 * @param v Segundo v&eacute;rtice
	 */
	public void union(Vertice u, Vertice v) {
		int i, j;

		for (i = 0; i < n; i++)
			if (vertices[i].equals(u))
				break;

		for (j = 0; j < n; j++)
			if (vertices[j].equals(v))
				break;

		if (u.obtener_Rango() > v.obtener_Rango()) {
			vertices[j].asignar_Padre(u);
		} else if (u.obtener_Rango() < v.obtener_Rango()) {
			vertices[i].asignar_Padre(v);
		} else {
			vertices[i].asignar_Padre(v);
			vertices[j].aumentar_Rango();
		}
	}
}
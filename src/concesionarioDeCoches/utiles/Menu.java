package concesionarioDeCoches.utiles;
/**
 * 
 * @author mlmc
 * @version 1.0
 */

public class Menu {

	/**
	 * Título del menú
	 */
	private String titulo;
	/**
	 * Opciones del menú
	 */
	private String[] opciones;
	/**
	 * El número de opciones en total, incluyendo la opción Salir añadida.
	 */
	private int numOpciones;
	/**
	 * La opción Salir añadida.
	 */
	private final int salir;

	public Menu(String titulo, String[] opciones) {
		setNumOpciones(opciones.length + 1);
		setTitulo(titulo);
		setOpciones(opciones);
		this.salir = getNumOpciones();
	}

	private int getNumOpciones() {
		return numOpciones;
	}

	private void setNumOpciones(int numOpciones) {
		this.numOpciones = numOpciones;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private void setOpciones(String[] opciones) {
		this.opciones = new String[numOpciones];

		for (int i = 0; i < numOpciones - 1; i++)
			this.opciones[i] = (i + 1) + ".- " + opciones[i];

		this.opciones[numOpciones - 1] = numOpciones + ".- Salir";
	}

	private void mostrar() {
		System.out.println("\n****************************************");
		System.out.println(titulo);
		System.out.println("****************************************");
		for (int i = 0; i < numOpciones; i++) {
			System.out.println(opciones[i]);
		}
	}

	private int recogerOpcion() {
		int opcion;
		do {
			opcion = Teclado.leerEntero("Dame una opción válida: ");
		} while (!opcionValida(opcion));
		return opcion;
	}

	public int gestionar() {
		mostrar();
		return recogerOpcion();
	}

	private boolean opcionValida(int opcion) {
		return (opcion > 0 && opcion <= numOpciones);// ?true:false;
	}

	public int getSalir() {//
		return salir;
	}

}
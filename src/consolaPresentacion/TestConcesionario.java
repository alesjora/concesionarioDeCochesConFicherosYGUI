package consolaPresentacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import concesionarioDeCoches.Color;
import concesionarioDeCoches.Concesionario;
import concesionarioDeCoches.Entorno;
import concesionarioDeCoches.Fichero;
import concesionarioDeCoches.Modelo;
//import excepciones.MatriculaRepetidaExcepcion;
import utiles.Menu;
import utiles.Teclado;

public class TestConcesionario{

	private static Menu menuConcesionario;
	private static Menu menuModelos;
	private static Menu menuColores;
	private static Menu menuFicheros;

	public static void main(String[] args) {
		inicializarEstado();
		iniciarConcesionario();

	}

	private static void inicializarEstado() {
		menuConcesionario = new Menu("Concesionario de coches",
				new String[] { "Alta de un coche", "Baja de  un coche", "Mostrar un coche", "Mostrar concesionario",
						"Contar el número de coches en el concesionario", "Mostrar coches de un color", "Ficheros" });
		menuModelos = new Menu("Modelos de coches", Modelo.menuModelos());
		menuColores = new Menu("Colores", Color.menuColores());
		menuFicheros = new Menu("Ficheros", new String[] { "Nuevo", "Abrir", "Guardar", "Guardar como" });
	}

	private static void iniciarConcesionario() {
		int opcion;
		do {
			opcion = menuConcesionario.gestionar();
			gestionarConcesionario(opcion);
		} while (opcion != menuConcesionario.getSalir());
	}

	private static void gestionarConcesionario(int opcion) {
		if (opcion == 8) {
			comprobarCambios();
			System.out.println("Hasta luego!");
			return;
		}

		else if (opcion != 1 && opcion != 7 && Entorno.concesionario.isEmpty()) {
			System.err.println("La lista está vacía,añade un coche antes");
			return;
		}

		switch (opcion) {
		case 1:
			agregar();
			break;
		case 2:
			eliminar();
			break;
		case 3:
			buscar();
			break;
		case 4:
			System.out.println(Entorno.concesionario.toString());
			break;
		case 5:
			System.out.println("Numero de coches en el concesionario: " + Entorno.concesionario.size());
			break;
		case 6:
			mostrarPorColor();
		case 7:
			iniciarMenuFicheros();
		}
	}

	private static void mostrarPorColor() {
		try {
			System.out.println(Entorno.concesionario.buscarCocheColor(elegirColor()));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No se ha introducido ningún color");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void agregar() {
		try {
			Entorno.concesionario.add(elegirModelo(), elegirColor(), Teclado.leerCadena("Introduce una matrícula"));
			System.out.println("El coche se ha añadido correctamente");
		} catch (Exception e) {
			System.err.println(e.getMessage() + " No se ha podido añadir el coche");
		}
	}

	private static void eliminar() {
		try {
			if (!Entorno.concesionario.remove(Teclado.leerCadena("Introduce una matrícula")))
				System.err.println("El coche no existe en el concesionario");
			else {
				System.out.println("El coche se ha eliminado correctamente");
				Entorno.concesionario.setModificado(true);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void buscar() {
		try {
			System.out.println(Entorno.concesionario.buscarCoche(Teclado.leerCadena("Introduce la matricula")));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void iniciarMenuFicheros() {
		int opcion;
		do {
			opcion = menuFicheros.gestionar();
			gestionarMenuFicheros(opcion);
		} while (opcion != menuFicheros.getSalir());
	}

	private static void gestionarMenuFicheros(int opcion) {
		switch (opcion) {
		case 1:
			nuevo();
			break;
		case 2:
			abrir();
			break;
		case 3:
			guardar();
			break;
		case 4:
			guardarComo();
			break;
		}
	}

	public static void abrir() {

		try {
			comprobarCambios();
			Entorno.concesionario = (Concesionario) Fichero
					.abrirConcesionario(Teclado.leerCadena("Introduce el nombre del archivo a abrir con su extensión"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void guardar() {
		try {
			if (Entorno.getNombreFichero() != null) {
				Fichero.guardar(Entorno.concesionario);
				System.out.println("Concesionario guardado correctamente");
			} else
				guardarComo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void nuevo() {
		comprobarCambios();
		Fichero.nuevoConcesionario();
		System.out.println("Nuevo concesionario creado");
	}

	/**
	 * 
	 */
	private static void comprobarCambios() {
		if (Entorno.concesionario.getModificado()) {
			System.out.println("ATENCION!! El concesionario no ha sido guardado aún");
			if (Teclado.deseaContinuar("Desea guardar el concesionario? (S/N)"))
				guardar();
		}
	}

	public static void guardarComo() {
		try {
			Fichero.guardarComo(Teclado.leerCadena("Introduce un nombre para el fichero"), Entorno.concesionario);
			System.out.println("Concesionario guardado correctamente");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Color elegirColor() {
		int opcion = menuColores.gestionar();
		Color[] color = Color.values();
		return color[opcion - 1];
	}

	private static Modelo elegirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] modelo = Modelo.values();
		return modelo[opcion - 1];
	}
}

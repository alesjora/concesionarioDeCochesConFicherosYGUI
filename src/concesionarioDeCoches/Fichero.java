package concesionarioDeCoches;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import consolaPresentacion.TestConcesionario;
import utiles.Teclado;

public class Fichero {

	public static Concesionario abrirConcesionario(String nombreFichero)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(nombreFichero)))) {
			return (Concesionario) in.readObject();
		}
	}

	public static void guardar(Concesionario concesionario) throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Entorno.getNombreFichero())))) {
			out.writeObject(concesionario);
			Entorno.concesionario.setModificado(false);
		}
	}
	private static void guardar(Concesionario concesionario, String nombreFichero) throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nombreFichero)))) {
			out.writeObject(concesionario);
		}
	}

	public static void nuevoConcesionario() {
		Entorno.concesionario.clear();
		Entorno.setNombreFichero(null);
	}

	public static void guardarComo(String nombreFichero, Concesionario concesionario)
			throws FileNotFoundException, IOException {
		guardar(concesionario,nombreFichero);
		Entorno.setNombreFichero(nombreFichero);
	}

}

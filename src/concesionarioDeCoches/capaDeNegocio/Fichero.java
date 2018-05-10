package concesionarioDeCoches.capaDeNegocio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import concesionarioDeCoches.utiles.Teclado;

public class Fichero {

	public static Concesionario abrirConcesionario(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			Concesionario concesionario = (Concesionario) in.readObject();
			Entorno.setFile(file);
			return concesionario;
		}
	}

	public static void guardar(Concesionario concesionario) throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(Entorno.getFile())))) {
			out.writeObject(concesionario);
			Entorno.setModificado(false);
		}
	}

	public static void guardarComo(File file, Concesionario concesionario)
			throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(concesionario);
			Entorno.setFile(file);
		}
	}

	public static void nuevoConcesionario() {
		Entorno.concesionario.clear();
		Entorno.setModificado(false);
		Entorno.setFile(null);
	}

//	public static void guardarComo(File file, Concesionario concesionario)
//			throws FileNotFoundException, IOException {
//		guardar(concesionario, file);
//		Entorno.setNombreFichero(file);
//	}

}

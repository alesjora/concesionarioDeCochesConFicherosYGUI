package concesionarioDeCoches.capaDeNegocio;

import java.io.File;

public class Entorno {
	public static Concesionario concesionario = new Concesionario();
	private static File file;
	private static boolean modificado = false;
	
	
	public static File getFile() {
		return file;
	}
	public static void setFile(File file) {
		Entorno.file = file;
	}
	public static boolean isModificado() {
		return modificado;
	}
	public static void setModificado(boolean modificado) {
		Entorno.modificado = modificado;
	}
	
}

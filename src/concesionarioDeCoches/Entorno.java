package concesionarioDeCoches;

public class Entorno {
	public static Concesionario concesionario = new Concesionario();
	public static String nombreFichero = null;
	
	public static String getNombreFichero() {
		return nombreFichero;
	}
	public static void setNombreFichero(String nombreFichero) {
		Entorno.nombreFichero = nombreFichero;
	}
	
	
}

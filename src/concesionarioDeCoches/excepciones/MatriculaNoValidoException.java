package concesionarioDeCoches.excepciones;

public class MatriculaNoValidoException extends Exception {
	private String matricula;

	public MatriculaNoValidoException(String string) {
		super(string);
		this.matricula=matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	

}

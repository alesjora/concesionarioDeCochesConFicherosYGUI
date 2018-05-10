package concesionarioDeCoches.capaDeNegocio;

import java.io.Serializable;
import java.util.regex.Pattern;

import concesionarioDeCoches.capaDeNegocio.excepciones.ColorNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.ModeloNoValidoException;

public class Coche implements Serializable {
	private Modelo modelo;
	private Color color;
	private String matricula;
	private static final Pattern pattern = Pattern.compile("^\\d{4}([-, ])?[B-Z&&[^AEIOULÑQ]]{3}$");

	public Coche(Modelo modelo, Color color, String matricula)
			throws ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidoException {
		super();
		setModelo(modelo);
		setColor(color);
		setMatricula(matricula);
	}
	Coche(String matricula) throws MatriculaNoValidoException{
		setMatricula(matricula);
	}

	public Modelo getModelo() {
		return modelo;
	}

	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo == null)
			throw new ModeloNoValidoException("El modelo del coche no es válido.");
		this.modelo = modelo;
	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) throws ColorNoValidoException {
		if (color == null)
			throw new ColorNoValidoException("El color del coche no es válido.");
		this.color = color;
	}
	
	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) throws MatriculaNoValidoException {
		if (!isValida(matricula))
			throw new MatriculaNoValidoException("La matrícula "+matricula +" no es válida.");
		this.matricula = estadarizarMatricula(matricula);
	}
	private String estadarizarMatricula(String matricula) {
		return matricula.replaceAll("[ -]","");
	}

	public static boolean isValida(String matricula) {
		return pattern.matcher(matricula).matches();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Matricula: "+matricula +" modelo: "+modelo+" marca: "+modelo.getMarca()+" color: "+getColor();
	}
}

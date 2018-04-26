package concesionarioDeCoches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import concesionarioDeCoches.excepciones.CocheNoExisteException;
import concesionarioDeCoches.excepciones.ColorNoValidoException;
import concesionarioDeCoches.excepciones.MatriculaNoValidoException;
import concesionarioDeCoches.excepciones.MatriculaRepetidaExcepcion;
import concesionarioDeCoches.excepciones.ModeloNoValidoException;
import utiles.Teclado;

public class Concesionario implements Serializable {

	private ArrayList<Coche> concesionario = new ArrayList<Coche>();
	private boolean modificado = false;

	public void add(Modelo modelo, Color color, String matricula) throws MatriculaRepetidaExcepcion,
			ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidoException {
		Coche coche = new Coche(modelo, color, matricula);
		if (isMatriculaRepetida(coche))
			throw new MatriculaRepetidaExcepcion("La matr�cula est� repetida en el concesionario");
		concesionario.add(coche);
		setModificado(true);
	}

	public boolean remove(String matricula) throws MatriculaNoValidoException {
		boolean borrado = concesionario.remove(new Coche(matricula));
		if (borrado)
			setModificado(true);
		return borrado;
	}

	public Coche buscarCoche(String matricula) throws MatriculaNoValidoException, CocheNoExisteException {
		try {
			return concesionario.get(concesionario.indexOf(new Coche(matricula)));
		} catch (IndexOutOfBoundsException e) {
			throw new CocheNoExisteException("El coche no existe en el concesionario");
		}
	}

	public ArrayList<Coche> buscarCocheColor(Color color) throws ConcesionarioSinColor {
		ArrayList<Coche> coches = new ArrayList<Coche>();
		for (Coche coche : concesionario) {
			if (coche.getColor() == color)
				coches.add(coche);
		}
		if (coches.isEmpty())
			throw new ConcesionarioSinColor("No hay coches de ese color");
		return coches;
	}

	public boolean isMatriculaRepetida(Coche coche) {
		return concesionario.contains(coche);
	}

	public boolean isEmpty() {
		return concesionario.isEmpty();
	}

	public int size() {
		return concesionario.size();
	}

	public Iterator<Coche> iterator() {
		return concesionario.iterator();
	}

	public void clear() {
		concesionario.clear();
	//	setModificado(true);
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	public boolean getModificado() {
		return modificado;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("");
		int i = 1;
		for (Coche coche : concesionario)
			stringBuilder.append((i++) + ". " + coche + " \n");
		return stringBuilder.toString();
	}
}

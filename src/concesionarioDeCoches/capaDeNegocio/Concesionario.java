package concesionarioDeCoches.capaDeNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import concesionarioDeCoches.capaDeNegocio.excepciones.CocheNoExisteException;
import concesionarioDeCoches.capaDeNegocio.excepciones.ColorNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.ConcesionarioSinColorException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaRepetidaExcepcion;
import concesionarioDeCoches.capaDeNegocio.excepciones.ModeloNoValidoException;
import concesionarioDeCoches.utiles.Teclado;

public class Concesionario implements Serializable {

	private ArrayList<Coche> concesionario = new ArrayList<Coche>();
	

	public void add(Modelo modelo, Color color, String matricula) throws MatriculaRepetidaExcepcion,
			ModeloNoValidoException, ColorNoValidoException, MatriculaNoValidoException {
		Coche coche = new Coche(modelo, color, matricula);
		if (isMatriculaRepetida(coche))
			throw new MatriculaRepetidaExcepcion("La matrícula está repetida en el concesionario");
		concesionario.add(coche);
		Entorno.setModificado(true);
	}

	public boolean remove(String matricula) throws MatriculaNoValidoException {
		boolean borrado = concesionario.remove(new Coche(matricula));
		if (borrado)
			Entorno.setModificado(true);
		return borrado;
	}

	public Coche buscarCoche(String matricula) throws MatriculaNoValidoException, CocheNoExisteException {
		try {
			return concesionario.get(concesionario.indexOf(new Coche(matricula)));
		} catch (IndexOutOfBoundsException e) {
			throw new CocheNoExisteException("El coche no existe en el concesionario");
		}
	}

	public ArrayList<Coche> buscarCocheColor(Color color) throws ConcesionarioSinColorException {
		ArrayList<Coche> coches = new ArrayList<Coche>();
		for (Coche coche : concesionario) {
			if (coche.getColor() == color)
				coches.add(coche);
		}
		if (coches.isEmpty())
			throw new ConcesionarioSinColorException("No hay coches de ese color");
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

	public ListIterator<Coche> listIterator() {
		return concesionario.listIterator();
	}

	public void clear() {
		concesionario.clear();
	//	setModificado(true);
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

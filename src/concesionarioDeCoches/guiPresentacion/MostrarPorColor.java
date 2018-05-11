package concesionarioDeCoches.guiPresentacion;

import java.util.ListIterator;


import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.excepciones.ConcesionarioSinColorException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarPorColor extends Mostrar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the dialog.
	 */
	public MostrarPorColor() {
		setModal(true);
		radioButtonAzul.setEnabled(true);
		radioButtonRojo.setEnabled(true);
		radioButtonPlata.setEnabled(true);
		exitButton.setLocation(261, 197);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					iterator = buscar();
					cocheSiguiente();
				} catch (ConcesionarioSinColorException e) {
					JOptionPane.showMessageDialog(rootPane, "No hay ningún coche de ese color");
				}
			}
		});
		radioButtonRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		radioButtonPlata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		radioButtonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		btnMostrar.setBounds(168, 197, 84, 23);
		getContentPane().add(btnMostrar);
		setBounds(100, 100, 352, 261);
		actualizarBotones();
		
	}
	private ListIterator<Coche> buscar() throws ConcesionarioSinColorException{
		return Entorno.concesionario.buscarCocheColor(getColor()).listIterator();
	}
	
	protected void actualizarBotones() {
		textFieldMatricula.setText("");
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
		radioButtonAzul.setEnabled(true);
		radioButtonPlata.setEnabled(true);
		radioButtonRojo.setEnabled(true);
		btnNext.setEnabled(false);
		btnPrevious.setEnabled(false);
		
	}
	@Override
	void limpiarPantalla() {
		textFieldMatricula.setText("");
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
		btnNext.setEnabled(false);
		btnPrevious.setEnabled(false);
	}
}

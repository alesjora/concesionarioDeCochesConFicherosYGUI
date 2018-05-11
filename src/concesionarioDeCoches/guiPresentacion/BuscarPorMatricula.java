package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Color;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.excepciones.CocheNoExisteException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BuscarPorMatricula extends CocheGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the dialog.
	 */
	public BuscarPorMatricula() {
		textFieldMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				limpiarPantalla();
			}
		});
		exitButton.setBounds(248, 198, 80, 23);
		okButton.setBounds(154, 197, 84, 23);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Coche coche = buscar();
					asignarInformacionAComponentes(coche);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		okButton.setText("Mostrar");
		setBounds(100, 100, 344, 259);
		deshabilitarComponentes();
		
	}
	
	private Coche buscar() throws MatriculaNoValidoException, CocheNoExisteException {
		return Entorno.concesionario.buscarCoche(textFieldMatricula.getText());
	}

	private void asignarInformacionAComponentes(Coche coche) {
		if(coche.getColor() == Color.ROJO)
			radioButtonRojo.setSelected(true);
		else if(coche.getColor() == Color.AZUL)
			radioButtonAzul.setSelected(true);
		else
			radioButtonPlata.setSelected(true);
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.setSelectedItem(coche.getModelo());
	}
	private void deshabilitarComponentes() {
		radioButtonAzul.setEnabled(false);
		radioButtonPlata.setEnabled(false);
		radioButtonRojo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setEnabled(false);
		comboBoxModelo.setSelectedItem(null);
		
	}
}

package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Color;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.excepciones.CocheNoExisteException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Baja extends CocheGUI {

	/**
	 * Create the dialog.
	 */
	public Baja() {
		okButton.setSize(84, 23);
		exitButton.setLocation(252, 197);
		okButton.setLocation(158, 197);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baja();
			}
		});
		okButton.setText("Eliminar");
		setTitle("Baja");
		setBounds(100, 100, 341, 262);
		deshabilitarComponentes();
		
		

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
	private void baja() {
		try {
			Coche coche = buscar();
			if(coche != null) {
				asignarInformacionAComponentes(coche);
				int n = JOptionPane.showOptionDialog(contentPanel,
						"Vas a eliminar el coche, ¿Estás seguro?", "Confirmar",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,null,null);
				
				switch (n) {
				case JOptionPane.YES_OPTION:
					Entorno.concesionario.remove(textFieldMatricula.getText());
					JOptionPane.showMessageDialog(contentPanel, "Coche eliminado con éxito");
					break;
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage());
		}
		limpiarPantalla();
	}
	
	/**
	 * @return
	 * @throws MatriculaNoValidoException
	 * @throws CocheNoExisteException
	 */
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
}

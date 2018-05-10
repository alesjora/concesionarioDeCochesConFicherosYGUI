package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import concesionarioDeCoches.capaDeNegocio.Color;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.Marca;
import concesionarioDeCoches.capaDeNegocio.Modelo;
import concesionarioDeCoches.capaDeNegocio.excepciones.ColorNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaRepetidaExcepcion;
import concesionarioDeCoches.capaDeNegocio.excepciones.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alta extends CocheGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// Alta dialog = new Alta();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the dialog.
	 */
	public Alta() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();
			}
		});
		setTitle("Alta");
		setBounds(100, 100, 344, 264);
		radioButtonPlata.setSelected(true);
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelos((Marca) comboBoxMarca.getSelectedItem())));
			}
		});
	}

	private Color getColor() {
		if (radioButtonAzul.isSelected())
			return Color.AZUL;
		if (radioButtonPlata.isSelected())
			return Color.PLATA;
		if (radioButtonRojo.isSelected())
			return Color.ROJO;
		return null;
	}

	private void alta() {
		try {
			Entorno.concesionario.add((Modelo) comboBoxModelo.getSelectedItem(), getColor(),
					textFieldMatricula.getText());
//			JOptionPane.showMessageDialog(contentPanel, "Coche añadido con éxito", "Coche añadido",
//					JOptionPane.INFORMATION_MESSAGE);
			limpiarPantalla();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error al añadir el coche",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

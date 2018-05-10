package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;

import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Color;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.Modelo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

public class Mostrar extends CocheGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private ListIterator<Coche> iterator = Entorno.concesionario.listIterator();
	private Coche coche;
	private JButton btnNext;
	private JButton btnPrevious;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mostrar dialog = new Mostrar();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Mostrar() {
		setBounds(100, 100, 349, 262);

		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheSiguiente();
			}
		});
		btnNext.setBounds(87, 197, 47, 23);
		getContentPane().add(btnNext);

		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAnterior();
			}
		});
		btnPrevious.setBounds(30, 197, 47, 23);
		getContentPane().add(btnPrevious);
		deshabilitarComponentes();
		coche = iterator.next();
		mostrarCoche();

	}

	private void deshabilitarComponentes() {
		textFieldMatricula.setEnabled(false);
		radioButtonAzul.setEnabled(false);
		radioButtonPlata.setEnabled(false);
		radioButtonRojo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		okButton.setVisible(false);
		btnPrevious.setEnabled(false);
	}

	protected void mostrarCoche() {
		
		textFieldMatricula.setText(coche.getMatricula());
		seleccionarColor();
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.setSelectedItem(coche.getModelo());
		comprobarBotones();
	}

	protected void comprobarBotones() {
		if(!iterator.hasNext()) {
			coche = iterator.previous();
			btnNext.setEnabled(false);
		}
		else 
			btnNext.setEnabled(true);
		
		if(!iterator.hasPrevious()) {
			coche = iterator.next();
			btnPrevious.setEnabled(false);
		}
		else
			btnPrevious.setEnabled(true);
		
	}

	/**
	 * 
	 */
	private void seleccionarColor() {
		if(coche.getColor() == Color.ROJO)
			radioButtonRojo.setSelected(true);
		else if(coche.getColor() == Color.AZUL)
			radioButtonAzul.setSelected(true);
		else
			radioButtonPlata.setSelected(true);
	}
	
	void cocheSiguiente() {
		if(iterator.hasNext()) {
			coche = iterator.next();
			mostrarCoche();
		}
	}
	void cocheAnterior() {
		if(iterator.hasPrevious()) {
			coche = iterator.previous();
			mostrarCoche();
		}
	}
}

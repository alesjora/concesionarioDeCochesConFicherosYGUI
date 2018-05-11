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
	protected ListIterator<Coche> iterator = Entorno.concesionario.listIterator();
	protected Coche coche;
	protected JButton btnNext;
	protected JButton btnPrevious;
	protected int controlIterador=0;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Mostrar dialog = new Mostrar();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the dialog.
	 */
	public Mostrar() {
		setResizable(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		radioButtonPlata.setEnabled(false);
		radioButtonRojo.setEnabled(false);
		radioButtonAzul.setEnabled(false);
		setResizable(true);
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
		cocheSiguiente();

	}

	private void deshabilitarComponentes() {
		textFieldMatricula.setEnabled(false);
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
		btnNext.setEnabled(true);
		btnPrevious.setEnabled(true);
		if(!iterator.hasNext()) 
			btnNext.setEnabled(false);
			
		if(!iterator.hasPrevious()) 
			btnPrevious.setEnabled(false);
			
		
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
	
	protected void cocheSiguiente() {
		if (iterator.hasNext()) {
			coche = iterator.next();
			mostrarCoche();
		}
		if(controlIterador==1) {
			if (iterator.hasNext()) {
				coche = iterator.next();
				mostrarCoche();
			}
		}
		controlIterador=0;
		comprobarBotones();
	}

	protected void cocheAnterior() {
		if (iterator.hasPrevious()) {
			coche = iterator.previous();
			mostrarCoche();
		}
		if(controlIterador==0) {
			if (iterator.hasPrevious()) {
				coche = iterator.previous();
				mostrarCoche();
			}
		}
		controlIterador=1;
		comprobarBotones();
	}
}

package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Color;
import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.excepciones.CocheNoExisteException;
import concesionarioDeCoches.capaDeNegocio.excepciones.ConcesionarioSinColorException;
import concesionarioDeCoches.capaDeNegocio.excepciones.MatriculaNoValidoException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

public class BuscarPorColor extends CocheGUI {

	private JButton btnPrevious;
	private JButton btnNext;
	private ListIterator<Coche> iterator;
	private Coche coche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPorColor dialog = new BuscarPorColor();
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
	public BuscarPorColor() {
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
		setTitle("Mostrar por color");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iterator = buscar().listIterator();
					coche = iterator.next();
					mostrarCoche();
					//btnNext.setEnabled(true);
				} catch (ConcesionarioSinColorException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		exitButton.setBounds(238, 197, 80, 23);
		okButton.setBounds(145, 197, 83, 23);
		okButton.setText("Buscar");

		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAnterior();
			}
		});
		btnPrevious.setEnabled(false);
		btnPrevious.setBounds(20, 197, 47, 23);
		getContentPane().add(btnPrevious);

		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheSiguiente();
			}
		});
		btnNext.setBounds(77, 197, 47, 23);
		getContentPane().add(btnNext);
		setBounds(100, 100, 358, 277);

		deshabilitarComponentes();

	}

	private void deshabilitarComponentes() {
		textFieldMatricula.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setEnabled(false);
		comboBoxModelo.setSelectedItem(null);
		btnNext.setEnabled(false);
		btnPrevious.setEnabled(false);
		
		
	}
	
	private Color getColor() {
		if(radioButtonAzul.isSelected())
			return Color.AZUL;
		else if(radioButtonPlata.isSelected())
			return Color.PLATA;
		else
			return Color.ROJO;
	}
	
	private ArrayList<Coche> buscar() throws ConcesionarioSinColorException{
		return Entorno.concesionario.buscarCocheColor(getColor());
	}
	private void cocheSiguiente() {
		if(iterator.hasNext()) {
			coche = iterator.next();
			mostrarCoche();
		}
	}
	private void cocheAnterior() {
		if(iterator.hasPrevious()) {
			coche = iterator.previous();
			mostrarCoche();
		}
	}
	private void mostrarCoche() {
		textFieldMatricula.setText(coche.getMatricula());
		seleccionarColor();
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.setSelectedItem(coche.getModelo());
		comprobarSiguienteYAnterior();
	}
	private void seleccionarColor() {
		if(coche.getColor() == Color.ROJO)
			radioButtonRojo.setSelected(true);
		else if(coche.getColor() == Color.AZUL)
			radioButtonAzul.setSelected(true);
		else
			radioButtonPlata.setSelected(true);
	}
	private void comprobarSiguienteYAnterior() {
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
	@Override
	void limpiarPantalla() {
		textFieldMatricula.setText("");
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
	}
}

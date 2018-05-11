package concesionarioDeCoches.guiPresentacion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionarioDeCoches.capaDeNegocio.Coche;
import concesionarioDeCoches.capaDeNegocio.Marca;
import concesionarioDeCoches.capaDeNegocio.Modelo;
import concesionarioDeCoches.capaDeNegocio.Color;

public class CocheGUI extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textFieldMatricula;
	protected JComboBox<Marca> comboBoxMarca;
	protected JLabel lblMatricula = new JLabel("Matr\u00EDcula");
	protected JLabel lblColor = new JLabel("Color");
	protected JRadioButton radioButtonPlata = new JRadioButton("Plata");
	protected JRadioButton radioButtonRojo = new JRadioButton("Rojo");
	protected JRadioButton radioButtonAzul = new JRadioButton("Azul");
	protected JLabel lblMarca = new JLabel("Marca");
	protected JComboBox comboBoxModelo;
	protected JLabel lblModelo = new JLabel("Modelo");
	protected JButton okButton;
	protected JButton exitButton;
	protected ButtonGroup grupoColor;

	/**
	 * Create the dialog.
	 */
	public CocheGUI() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 339, 259);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 328, 186);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblMatricula = new JLabel("Matr\u00EDcula");
		lblMatricula.setBounds(29, 27, 69, 17);
		contentPanel.add(lblMatricula);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textFieldMatricula.setText(textFieldMatricula.getText().toUpperCase());
				if(!Coche.isValida(textFieldMatricula.getText()))
					textFieldMatricula.setForeground(java.awt.Color.RED);
			}
			@Override
			public void focusGained(FocusEvent e) {
				textFieldMatricula.setForeground(java.awt.Color.BLACK);
			}
		});
		textFieldMatricula.setBounds(97, 25, 113, 20);
		contentPanel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		lblColor = new JLabel("Color");
		lblColor.setBounds(29, 67, 46, 14);
		contentPanel.add(lblColor);
		
		radioButtonPlata = new JRadioButton("Plata");
		radioButtonPlata.setBounds(97, 63, 61, 23);
		contentPanel.add(radioButtonPlata);
		
		radioButtonRojo = new JRadioButton("Rojo");
		radioButtonRojo.setBounds(160, 63, 61, 23);
		contentPanel.add(radioButtonRojo);
		
		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.setBounds(97, 106, 92, 20);
		contentPanel.add(comboBoxMarca);
		
		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(97, 152, 92, 20);
		
		comboBoxMarca.setModel(new DefaultComboBoxModel<Marca>(Marca.values()));
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelos((Marca)comboBoxMarca.getSelectedItem())));
		
		radioButtonAzul = new JRadioButton("Azul");
		radioButtonAzul.setBounds(223, 63, 61, 23);
		contentPanel.add(radioButtonAzul);
		grupoColor = new ButtonGroup();
		grupoColor.add(radioButtonAzul);
		grupoColor.add(radioButtonPlata);
		grupoColor.add(radioButtonRojo);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(29, 109, 46, 14);
		contentPanel.add(lblMarca);
		
		contentPanel.add(comboBoxModelo);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(29, 155, 46, 14);
		contentPanel.add(lblModelo);
		{
			exitButton = new JButton("Salir");
			exitButton.setBounds(248, 198, 73, 23);
			getContentPane().add(exitButton);
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			exitButton.setActionCommand("Cancel");
		}
		{
			okButton = new JButton("A\u00F1adir");
			okButton.setBounds(161, 197, 77, 23);
			getContentPane().add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}
	void limpiarPantalla() {
		textFieldMatricula.setText("");
		grupoColor.clearSelection();
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
	}
	
	protected Color getColor() {
		if (radioButtonAzul.isSelected())
			return Color.AZUL;
		if (radioButtonPlata.isSelected())
			return Color.PLATA;
		if (radioButtonRojo.isSelected())
			return Color.ROJO;
		return null;
	}
	
	public Object[] getModelos(Marca selectedItem) {
		ArrayList<Modelo> arrayListModelos = new ArrayList<Modelo>();
		for (Modelo modelo : Modelo.values()) {
			if(modelo.getMarca() == selectedItem)
				arrayListModelos.add(modelo);
		}
		return (Object [])arrayListModelos.toArray();
	}
}

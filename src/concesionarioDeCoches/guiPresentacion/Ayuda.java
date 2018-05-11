package concesionarioDeCoches.guiPresentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ayuda extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setResizable(false);
		setBounds(100, 100, 437, 295);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Ayuda del concesionario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(10, 11, 424, 198);
		contentPanel.add(jScrollPane);
		{
			JTextPane jTextPaneAyuda = new JTextPane();
			jScrollPane.setViewportView(jTextPaneAyuda);
			jTextPaneAyuda.setEditable(false);
			jTextPaneAyuda.setText("Concesionario de coches 2018:\r\n" + 
					"\r\n" + 
					"-Archivo:\r\n" + 
					" -Nuevo concesionario: Crea nuevo concesionario.\r\n" + 
					" -Abrir concesionario: Carga un concesionario desde un archivo.\r\n"+
					" -Guardar: Guarda concesionario actual.\r\n" + 
					" -Guardar como: Guarda concesionario actual con un nombre especificado.\r\n" + 
					" -Salir: Salir del concesionario.\r\n" + 
					"\r\n" + 
					"-Coche:\r\n" + 
					" -Alta: Alta de un coche en el concesionario.\r\n" + 
					" -Baja: Baja de un coche en el concesionario.\r\n" + 
					" -Mostrar concesionario: Muestra todo el concesionario.\r\n" + 
					"\r\n" + 
					"-Buscar:\r\n" + 
					" -Por matrícula: Busca un coche por una matrícula proporcionada.\r\n" + 
					" -Por color: Busca todos los coches de un color proporcionado.\r\n" + 
					"\r\n" + 
					"-Ayuda:\r\n" + 
					" -Ver ayuda: Muestra esta ayuda.\r\n" + 
					" -Acerca de: Muestra derechos acerca del programa.");
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton salirButton = new JButton("Salir");
				salirButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				salirButton.setActionCommand("Cancel");
				buttonPane.add(salirButton);
			}
		}
	}
}

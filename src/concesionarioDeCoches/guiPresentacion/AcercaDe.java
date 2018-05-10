package concesionarioDeCoches.guiPresentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcercaDe extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setResizable(false);
		setTitle("Acerca de");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelAcercaDe = new JLabel("<html>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"	<h1 text-align:'center'>CONCESIONARIO DE COCHES</h1>\r\n" + 
				"	<h2 text-align:'center'>IES GRAN CAPITÁN</h2>\r\n" + 
				"	\r\n" + 
				"	<ul>\r\n" + 
				"		<li>Desarrollado por: José Rafael Álvarez Espino</li>\r\n" + 
				"		<li>Curso: 1ºDAW</li>\r\n" + 
				"		<li>Año:2018</li>\r\n" + 
				"		<li>Módulo: Programación</li>\r\n" + 
				"	</ul>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"");
		labelAcercaDe.setBounds(10, 11, 414, 206);
		contentPanel.add(labelAcercaDe);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton salirButton = new JButton("Salir");
				salirButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				salirButton.setActionCommand("Cancel");
				buttonPane.add(salirButton);
			}
		}
	}
}

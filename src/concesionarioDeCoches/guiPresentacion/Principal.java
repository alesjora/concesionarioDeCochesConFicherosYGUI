package concesionarioDeCoches.guiPresentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import concesionarioDeCoches.capaDeNegocio.Entorno;
import concesionarioDeCoches.capaDeNegocio.Fichero;
import concesionarioDeCoches.utiles.Teclado;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal {

	private static JFrame frame;
	private Alta alta = new Alta();
	private Baja baja = new Baja();
	private Mostrar mostrar;
	private BuscarPorMatricula buscarPorMatricula = new BuscarPorMatricula();
	private MostrarPorColor mostrarPorColor;
	private Ayuda ayuda = new Ayuda();
	private AcercaDe acercaDe = new AcercaDe();
	private static JFileChooser jFileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int respuesta = comprobarModificado();
				if (respuesta == JOptionPane.YES_OPTION) {
					guardar();
					System.exit(0);
				} else if (respuesta == JOptionPane.NO_OPTION)
					System.exit(0);
				else {

				}
			}
		});
		frame.setTitle("Sin t\u00EDtulo");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo Concesionario");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});

		JMenuItem mntmAbrir = new JMenuItem("Abrir concesionario");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnArchivo.add(mntmAbrir);
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mntmGuardarComo
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int respuesta = comprobarModificado();
				if (respuesta == JOptionPane.YES_OPTION) {
					guardar();
					System.exit(0);
				} else if (respuesta == JOptionPane.NO_OPTION) {
					System.exit(0);
				} else {
				}

			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);

		JMenu mnCoche = new JMenu("Coche");
		menuBar.add(mnCoche);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta.setVisible(true);
				alta.setModal(true);

			}
		});
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnCoche.add(mntmAlta);

		JSeparator separator_2 = new JSeparator();
		mnCoche.add(separator_2);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baja.setVisible(true);
				baja.setModal(true);
			}
		});
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnCoche.add(mntmBaja);

		JSeparator separator_3 = new JSeparator();
		mnCoche.add(separator_3);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comprobarConcesionarioVacio()) {
					mostrar = new Mostrar();
					mostrar.setVisible(true);
					mostrar.setModal(true);
				} else
					JOptionPane.showMessageDialog(frame, "El concesionario está vacío.");
			}

		});
		mntmMostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mnCoche.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		menuBar.add(mnBuscar);

		JMenuItem mntmPorMatricula = new JMenuItem("Por matr\u00EDcula");
		mntmPorMatricula
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comprobarConcesionarioVacio()) {
					buscarPorMatricula.setVisible(true);
					buscarPorMatricula.setModal(true);
				} else
					JOptionPane.showMessageDialog(frame, "El concesionario está vacío.");
			}
		});
		mnBuscar.add(mntmPorMatricula);

		JMenuItem mntmPorColor = new JMenuItem("Por color");
		mntmPorColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comprobarConcesionarioVacio()) {
					mostrarPorColor = new MostrarPorColor();
					mostrarPorColor.setVisible(true);
					mostrarPorColor.setModal(true);
				} else
					JOptionPane.showMessageDialog(frame, "El concesionario está vacío.");
			}
		});
		mnBuscar.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmVerAyuda = new JMenuItem("Ver ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda.setVisible(true);
				ayuda.setModal(true);
			}
		});
		mnAyuda.add(mntmVerAyuda);

		JMenuItem mntmAcerdaDe = new JMenuItem("Acerda de");
		mntmAcerdaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmAcerdaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercaDe.setVisible(true);

			}
		});
		mnAyuda.add(mntmAcerdaDe);
		jFileChooser.setFileFilter(new concesionarioDeCoches.capaDeNegocio.Filtro());
		jFileChooser.setAcceptAllFileFilterUsed(false);
	}

	private void nuevo() {
		int respuesta = comprobarModificado();
		if (respuesta == JOptionPane.YES_OPTION) {
			guardar();
			Entorno.concesionario = new concesionarioDeCoches.capaDeNegocio.Concesionario();
			frame.setTitle("Sin Titulo");
			Entorno.setModificado(false);
		} else if (respuesta == JOptionPane.NO_OPTION) {
			Fichero.nuevoConcesionario();
			frame.setTitle("Sin título");
		} else {
		}
	}

	private int comprobarModificado() {
		int respuesta = 1;
		if (Entorno.isModificado())
			respuesta = JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar, ¿Desea guardarlos?", "Importante",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		return respuesta;
	}

	public static void guardar() {
		try {
			if (Entorno.getFile() != null) {
				concesionarioDeCoches.capaDeNegocio.Fichero.guardar(Entorno.concesionario);
				JOptionPane.showMessageDialog(null, "El archivo ha sido guardado correctamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			} else
				guardarComo();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error, archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error en la salida de datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void guardarComo() {
		int returnVal = jFileChooser.showSaveDialog(frame);
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile();
			String filePath = file.getAbsolutePath();

			if (!filePath.endsWith(".obj")) {
				file = new File(filePath + ".obj");
				try {
					Fichero.guardarComo(file, Entorno.concesionario);
					JOptionPane.showMessageDialog(frame, "Archivo guardado correctamente", "Guardado con éxito",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(frame, "Error al guardar el archivo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			frame.setTitle(jFileChooser.getName(file));
		}

	}

	private void abrir() {
		int returnVal = jFileChooser.showOpenDialog(frame);
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile();
			try {
				Entorno.concesionario = Fichero.abrirConcesionario(file);
				JOptionPane.showMessageDialog(frame, "Archivo abierto correctamente", "Abierto con éxito",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(frame, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		frame.setTitle(jFileChooser.getName(file));
	}

	/**
	 * @return
	 */
	private boolean comprobarConcesionarioVacio() {
		return Entorno.concesionario.isEmpty();
	}
}

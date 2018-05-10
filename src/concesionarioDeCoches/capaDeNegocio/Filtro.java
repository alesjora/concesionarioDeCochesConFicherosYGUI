package concesionarioDeCoches.capaDeNegocio;

import java.io.File;

public class Filtro extends javax.swing.filechooser.FileFilter {
	    @Override
	    public boolean accept(File file) {
	        return file.isDirectory() || file.getAbsolutePath().endsWith(".obj");
	    }
	    @Override
	    public String getDescription() {
	        return "Objeto concesionario (*.obj)";
	    }
}

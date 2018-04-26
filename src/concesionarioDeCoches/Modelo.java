package concesionarioDeCoches;

public enum Modelo {
	
	CORDOBA(Marca.SEAT),
	TOLEDO(Marca.SEAT),
	IBIZA(Marca.SEAT),
	SERIE_1(Marca.BMW),
	SERIE_2(Marca.BMW),
	SERIE_3(Marca.BMW),
	SERIE_5(Marca.BMW);
	
	private Marca marca;
	
	private Modelo(Marca marca) {
		this.setMarca(marca);
	}
	public static String[] menuModelos(){
		String[] modelos = new String[Modelo.values().length];
		int i = 0;
		for (Modelo modelo : Modelo.values())
			modelos[i++] = modelo.toString();
		return modelos;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}


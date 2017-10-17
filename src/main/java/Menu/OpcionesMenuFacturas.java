package Menu;

public enum OpcionesMenuFacturas {
	VOLVER("Volver al menu anterior"),
	EMITIR_FACTURA("Emitir una factura para un cliente"),
	RECUPERAR_FACTURA("Recuperar los datos de una factura"),
	RECUPERAR_FACTURAS_CLIENTE("Recupera todas las facturas de un cliente"),
	FACTURAS_ENTRE_DOS_FECHAS("Recupera las facturas comprendidas entre dos fechas");
	
	private String descripcion;

	private OpcionesMenuFacturas(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static OpcionesMenuFacturas getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (OpcionesMenuFacturas opcion : OpcionesMenuFacturas.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}


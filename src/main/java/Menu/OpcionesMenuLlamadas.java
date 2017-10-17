package Menu;

public enum OpcionesMenuLlamadas {
	VOLVER("Volver al menu anterior"),
	NUEVA_LLAMADA("Dar de alta una llamada"),
	LISTAR_LLAMADAS("Listar todas las llamadas de un cliente"),
	LLAMADAS_ENTRE_DOS_FECHAS("Listar llamadas comprendidas entre dos fechas");
	
	private String descripcion;

	private OpcionesMenuLlamadas(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static OpcionesMenuLlamadas getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (OpcionesMenuLlamadas opcion : OpcionesMenuLlamadas.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}


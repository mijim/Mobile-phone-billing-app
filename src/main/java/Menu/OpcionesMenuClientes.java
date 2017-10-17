package Menu;

public enum OpcionesMenuClientes {
	VOLVER("Volver al menu anterior"),
	NUEVO_CLIENTE("Dar de alta un nuevo cliente"),
	BORRAR_CLIENTE("Borrar un cliente"),
	CAMBIAR_TARIFA("Cambiar la tarifa basica de un cliente"),
	RECUPERAR_DATOS("Recuperar los datos de un cliente (con su NIF)"),
	RECUPERAR_LISTADO("Recuperar el listado de todos los clientes"),
	CLIENTES_ENTRE_DOS_FECHAS("Recuperar listado de clientes comprendidos entre dos fechas"),
	AÑADIR_TARIFA_EXTRA("Añade una tarifa extra");

	private String descripcion;

	private OpcionesMenuClientes(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static OpcionesMenuClientes getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (OpcionesMenuClientes opcion : OpcionesMenuClientes.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}


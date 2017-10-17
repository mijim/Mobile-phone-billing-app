package Menu;

public enum OpcionesMenu {
	SALIR("Salir del programa"),
	EDITAR_CLIENTES("Editar clientes"),
	EDITAR_LLAMADAS("Editar llamadas"),
	EDITAR_FACTURAS("Editar facturas");

	private String descripcion;

	private OpcionesMenu(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static OpcionesMenu getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (OpcionesMenu opcion : OpcionesMenu.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}


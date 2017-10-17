package Internal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public abstract class Cliente extends ClaseConFecha{
	private static final long serialVersionUID = 4019787677590609724L;
	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String correoElectronico;
	private Tarifa tarifa;
	private	List<Llamada> listaLlamadas;
	private List<Integer> listaFacturas;
	
	public Cliente(){
		super(null);
		nombre = "";
		NIF = "";
		direccion = null;
		correoElectronico = "";
		tarifa = new TarifaBasica();
		listaLlamadas = new LinkedList<Llamada>();
		listaFacturas = new LinkedList<Integer>();
	}
	
	public Cliente(String nombre, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
		super(fechaDeAlta);
		this.nombre = nombre;
		this.NIF = NIF;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.tarifa = tarifa;
		listaLlamadas = new LinkedList<Llamada>();
		listaFacturas = new LinkedList<Integer>();
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getNIF(){
		return NIF;
	}
	
	public String getCorreo() {
		return correoElectronico;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public List<Integer> getFacturas() {
		return listaFacturas;
	}

	public List<Llamada> getLlamadas() {
		// TODO Auto-generated method stub
		return listaLlamadas;
	}

	public void setCorreo(String correo) {
		correoElectronico = correo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public void setDireccion(Direccion dir) {
		this.direccion = dir;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
		
	}


	public void setFacturas(List<Integer> facturas) {
		this.listaFacturas = facturas;
	}


	public void setLlamadas(List<Llamada> llamadas) {
		this.listaLlamadas = llamadas;
	}

	public void addFactura(int factura) {
		this.listaFacturas.add(factura);
	}

	public void addLlamada(Llamada llamada) {
		this.listaLlamadas.add(llamada);
		
	}
	
@Override
	
	public boolean equals(Object o){
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Factura))
			return false;
		Cliente cliente = (Cliente) o;
		if (!this.NIF.equals(cliente.NIF))
			return false;
		return true;
	}
@Override

	public String toString(){
		StringBuilder datos = new StringBuilder();
		datos.append("Nombre: ");
		datos.append(nombre);
		datos.append("\n");
		datos.append("Direccion: \n");
		datos.append("\tCodigo postal -> ");
		datos.append(direccion.getCodigoPostal());
		datos.append("\n");
		datos.append("\tPoblacion -> ");
		datos.append(direccion.getPoblacion());
		datos.append("\n");
		datos.append("\tProvincia -> ");
		datos.append(direccion.getProvincia());
		datos.append("\n");
		datos.append("Correo electronico: ");
		datos.append(correoElectronico);
		datos.append("\n");
		datos.append("Fecha de alta: ");
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy ' a las ' HH:mm:ss");
		datos.append(dateformatter.format(super.getFecha().getTime()));
		datos.append("\n");
		datos.append(tarifa.toString());
		return datos.toString();
		
	}

}

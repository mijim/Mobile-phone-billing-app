package Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import Data.Data;
import EntradaSalida.Salida;
import EntradaSalida.SalidaConsola;
import Excepciones.FechaIncorrectaException;
import Fabrica.FabricaClientes;
import Fabrica.FabricaTarifas;
import Internal.ClaseConFecha;
import Internal.Cliente;
import Internal.Direccion;
import Internal.Factura;
import Internal.Llamada;

public class FuncionesMenu {
	
	private static Salida salida = new SalidaConsola();
	private static Data datos = new Data();
	
	
	//Clientes
	
	public static String recuperarListadoCliente() {
		String ret = "";
		for(Cliente cl : datos.listaClientes()){
			ret+="NIF: " + cl.getNIF() + " Nombre: " + cl.getNombre() + "\n";
		}
		return ret;
	}

	public static String recuperarDatosCliente(String NIF) {
		return datos.getCliente(NIF).toString();
	}

	public static void cambiarTarifaCliente(String NIF, int tarifa) {
		datos.getCliente(NIF).setTarifa(FabricaTarifas.getTarifaBasica(tarifa));
	}

	public static void borrarCliente(String NIF) {
		datos.removeCliente(NIF);
	}

	public static void nuevoParticular(String NIF, String nombre, String apellidos, String correoElectronico, int tarifa, Direccion dir) {
		Calendar actual = Calendar.getInstance();
		datos.addCliente(NIF, FabricaClientes.getClienteParticular(nombre,apellidos, NIF, dir, correoElectronico, actual,FabricaTarifas.getTarifaBasica(tarifa)));
	}
	
	public static void nuevoEmpresa(String NIF, String nombre, String correoElectronico, int tarifa, Direccion dir){
		Calendar actual = Calendar.getInstance();
		datos.addCliente(NIF, FabricaClientes.getClienteEmpresa(nombre, NIF, dir, correoElectronico, actual,FabricaTarifas.getTarifaBasica(tarifa)));
	}
	
	public static String clientesEntreDosFechas(Calendar fechaInicio, Calendar fechaFinal){
		String ret= "";
		try{
			List<Cliente> ls = datos.listaClientes();
			List<Cliente> subconjunto = subconjuntoEntreDosFechas(ls,fechaInicio,fechaFinal);
			for(Cliente cl : subconjunto){
				ret += "NIF" + cl.getNIF() + " Nombre" + cl.getNombre() + "\n";
			}
		}catch(FechaIncorrectaException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void añadirTarifaExtraDia(String NIF, int dia, int precio){
		datos.getCliente(NIF).setTarifa(FabricaTarifas.getTarifaDia(datos.getCliente(NIF).getTarifa(), precio, dia));
	}
	
	public static void añadirTarifaExtraFranja(String NIF,int precio,  int inicio, int fin){
		datos.getCliente(NIF).setTarifa(FabricaTarifas.getTarifaFranjaHoraria(datos.getCliente(NIF).getTarifa(), precio, inicio, fin));
	}
	
	//Llamadas
	
	public static String listarLlamadas(String NIF) {
		String ret = "";
		List<Llamada> llamadas = datos.getCliente(NIF).getLlamadas();
		for(Llamada l : llamadas){
			ret+=l.toString();
		}
		return ret;
	}

	public static void nuevaLlamada(String NIF, Llamada nueva) {
		datos.getCliente(NIF).addLlamada(nueva);
	}
	
	public static String llamadasEntreDosFechas(String NIF, Calendar fechaInicio, Calendar fechaFinal){
		String ret = "";
		try{
			List<Llamada> llamadas = datos.getCliente(NIF).getLlamadas();
			List<Llamada> subconjunto = subconjuntoEntreDosFechas(llamadas,fechaInicio,fechaFinal);
			for(Llamada llamada : subconjunto){
				ret+=llamada.toString();
			}
		}catch(FechaIncorrectaException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	//Facturas
	
	public static String facturasEntreDosFechas(String NIF, Calendar fechaInicio, Calendar fechaFinal) {
		String ret = "";
		try{
			Cliente cl = datos.getCliente(NIF);
			List<Integer> listaKey = cl.getFacturas();
			List<Factura>  listaFacturas = new LinkedList<Factura>();
			for(int i : listaKey){
				listaFacturas.add(datos.getFactura(i));
			}
			listaFacturas = subconjuntoEntreDosFechas(listaFacturas, fechaInicio, fechaFinal);
			for(Factura f: listaFacturas){
				ret+=f.toString();
			}
		}catch(FechaIncorrectaException e){
			e.printStackTrace();
		}
		return ret;
		
	}

	public static String recuperarFacturasCliente(String NIF) {
		String ret="";
		for(int cod : datos.getCliente(NIF).getFacturas()){
			ret+=datos.getFactura(cod).toString() + "\n";
		}
		return ret;
	}

	public static String recuperarFactura(int codfac) {
		return datos.getFactura(codfac).toString();
	}

	public static int nuevaFactura(String NIF) {
		Cliente cliente = datos.getCliente(NIF);
		List<Integer> facturasCliente = cliente.getFacturas();
		List<Llamada> llamadas = cliente.getLlamadas();
		if(!llamadas.isEmpty()){
			int importe = 0;
			Calendar fechaEmision = Calendar.getInstance();
			SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy ' a las ' HH:mm:ss");
			String periodo="";
			if(facturasCliente.isEmpty()){
				for(Llamada llamada : llamadas){
					importe += cliente.getTarifa().getTarifaMenor(llamada.getFecha()) * (llamada.getDuracion()*1.0)/60;
				}
				periodo = "De " + dateformatter.format(cliente.getFecha().getTime()) + " a " + dateformatter.format(fechaEmision.getTime());
			}else{
				Calendar fechaUltimaFactura = datos.getFactura(facturasCliente.get(facturasCliente.size()-1)).getFecha();
				for(Llamada llamada : llamadas){
					if(llamada.getFecha().compareTo(fechaUltimaFactura) > 0){
						importe += cliente.getTarifa().getTarifaMenor(llamada.getFecha()) * (llamada.getDuracion()*1.0)/60;
					}
				}
				periodo = "De " + dateformatter.format(fechaUltimaFactura.getTime()) + " a " + dateformatter.format(fechaEmision.getTime());
			}
			Factura fac = new Factura(cliente.getTarifa(), fechaEmision, periodo, importe);
			int codfac = datos.addFactura(fac);
			cliente.addFactura(codfac);
			return codfac;
		}
		return -1;
		
	}
	
	//Extractor de fechas
	
	private static <T extends ClaseConFecha> List<T> subconjuntoEntreDosFechas(List<T> ls, Calendar fechaInicio, Calendar fechaFinal) throws FechaIncorrectaException{
		if(fechaInicio.compareTo(fechaFinal) > 0) throw new FechaIncorrectaException();
		List<T> subconjuntoLista = new LinkedList<T>();
		for(T item : ls){
			if(item.getFecha().compareTo(fechaInicio) >= 0 && item.getFecha().compareTo(fechaFinal) <= 0){
				subconjuntoLista.add(item);
			}
		}
		return subconjuntoLista;
	}
	
	//Salida del programa

	public static void salir() {
		almacenaDatos();
		salida.imprimir("Adios!!\n");
		System.exit(0);
	}
	
	//Grabacion de datos
	
	private static void almacenaDatos() {
		ObjectOutputStream oos=null;
		try {
			try {
				FileOutputStream fos = new FileOutputStream("Datos.bin");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(datos);
			}
			finally {
				oos.close();
			}
		}
		catch(FileNotFoundException exc) {
			System.out.println("El fichero no existe.");
			exc.printStackTrace();
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		
	}
	
	public static void recuperaDatos() {
		ObjectInputStream ois = null;
		try{
			try {
				FileInputStream fis = new FileInputStream("Datos.bin");
				ois = new ObjectInputStream(fis);
				datos = (Data)ois.readObject();
			}
			finally {
				if(ois != null) ois.close();
			}
		}
		catch(FileNotFoundException exc) {
			System.err.println("Fichero de datos no existe. Se crea Datos.bin");
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		catch(ClassNotFoundException exc) {
			exc.printStackTrace();
		}
		
			
	}

}

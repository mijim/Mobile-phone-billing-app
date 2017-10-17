package EntradaSalida;

import java.util.Calendar;
import java.util.Scanner;

import Excepciones.NumeroNegativoException;
import Internal.Direccion;

public class LecturaTeclado implements Lectura {
	
	private Scanner sc;
	private Salida salida;
	
	public LecturaTeclado(){
		sc =  new Scanner(System.in);
		salida = new SalidaConsola();
	}
	
	
	public int leerOpcion(){
		salida.imprimir("Introduce una opcion:");
		return sc.nextInt();
	}

	public String leerNombre(){
		sc.useDelimiter("\n");
		salida.imprimir("Introduce el nombre: ");
		return sc.next();
	}
	
	public String leerApellidos(){
		sc.useDelimiter("\n");
		salida.imprimir("Introduce los apellidos: ");
		return  sc.next();
	}
	
	public String leerNIF(){
		salida.imprimir("Introduce el NIF: ");
		return sc.next();
	}
	
	public Direccion leerDireccion(){
		salida.imprimir("Introduce el CP: ");
		int cp = sc.nextInt();
		sc.useDelimiter("\n");
		salida.imprimir("Introduce la provincia: ");
		String prov = sc.next();
		salida.imprimir("Introduce la poblacion: ");
		String pobl = sc.next();
		return new Direccion(cp, prov, pobl);
	}
	
	public String leerCorreoElectronico(){
		salida.imprimir("Introduce el correo electronico: ");
		return sc.next();
	}
	
	public int leerTarifa(){
		salida.imprimir("Introduce una tarifa: ");
		return sc.nextInt();
	}
	
	public boolean esParticular(){
		salida.imprimir("El cliente es particular? (s/n)\n");
		if(sc.next().equals("s")){
			return true;
		}
		return false;
			
	}
	
	public int leerNumeroTelefono(){
		salida.imprimir("Introduce el numero de telefono: ");
		return sc.nextInt();
	}
	
	public Calendar leerFecha(){
		salida.imprimir("Fecha:\n");
		salida.imprimir("\tIntroduce el año: ");
		int año = sc.nextInt();
		salida.imprimir("\tIntroduce el mes: ");
		int mes = sc.nextInt();
		salida.imprimir("\tIntroduce el dia: ");
		int dia = sc.nextInt();	
		salida.imprimir("\tIntroduce la hora: ");
		int hora = sc.nextInt();
		Calendar fecha = Calendar.getInstance();
		fecha.set(año, mes-1, dia, hora, 0);
		return fecha;
	}
	
	public int leerDuracion() throws NumeroNegativoException{
		salida.imprimir("Introduce la duración de la llamada (en segundos): ");
		int duracion = sc.nextInt();
		if(duracion < 0) throw new NumeroNegativoException();
		return duracion;
	}
	
	public int leerCodigoFactura(){
		salida.imprimir("Introduce el codigo de la factura\n");
		return sc.nextInt();
	}
	
	public boolean esTarifaDia(){
		salida.imprimir("Tipos de tarifa:\n\t -Tarifa de un Dia\n\t -Tarifa de Franja Horaria\n Introduce el tipo de tarifa(d/f): ");
		if(sc.next().equals("d"))
			return true;
		return false;
	}
	
	public int leerDia(){
		salida.imprimir("Introduce un dia de la semana: ");
		return sc.nextInt();
	}
	
	public int leerHora(){
		salida.imprimir("Introduce la hora: ");
		return sc.nextInt();
	}
}

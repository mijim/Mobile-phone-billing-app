package Internal;

import java.util.Calendar;

public class Particular extends Cliente{
	private static final long serialVersionUID = 508454151290546412L;
	private String apellidos;
	
	public Particular(){
		 super();
		 apellidos = "";
	}
	
	public Particular(String nombre, String apellidos, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
		super(nombre, NIF, direccion, correoElectronico, fechaDeAlta, tarifa);
		this.apellidos = apellidos;
	}
	
	@Override
	public String getNombre(){
		return super.getNombre() + " " + apellidos;
	}
	
	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	
	@Override 
	
	public String toString(){
		StringBuilder datos = new StringBuilder();
		datos.append("Apellidos: ");
		datos.append(apellidos);
		datos.append("\n");
		datos.append(super.toString());
		return datos.toString();
	}

}

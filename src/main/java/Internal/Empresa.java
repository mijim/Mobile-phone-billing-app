package Internal;

import java.util.Calendar;

public class Empresa extends Cliente{
	private static final long serialVersionUID = 6286175962258993811L;

	public Empresa(){
		super();
	}
	
	public Empresa(String nombre, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
		super(nombre, NIF, direccion, correoElectronico, fechaDeAlta, tarifa);
	}
}

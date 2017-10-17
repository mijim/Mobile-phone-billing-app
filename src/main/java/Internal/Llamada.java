package Internal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Llamada extends ClaseConFecha{
	private static final long serialVersionUID = 2807276040310116273L;
	private int numeroTelefono;
	private int duracion; //en segundos
	
	public Llamada(){
		super();
		numeroTelefono = 0;
		duracion = 0;
	}
	
	public Llamada(int nTel, Calendar fhLlamada, int dur){
		super(fhLlamada);
		numeroTelefono = nTel;
		duracion = dur;
	}
	
	//Metodo get
	
	public int getNumeroTelefono(){
		return numeroTelefono;
	}
	
	
	public int getDuracion(){
		return duracion;
	}
	
	//Metodos set
	
	public void setNumeroTelefono(int num){
		numeroTelefono  = num;
	}
	
	public void setDuracion(int dur){
		duracion = dur;
	}
	

@Override 

	public String toString(){
		StringBuilder datos = new StringBuilder();
		datos.append("Numero de telefono ");
		datos.append(numeroTelefono);
		datos.append("\n");
		datos.append("Fecha y hora de la llamada: ");
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy ' a las ' HH ' horas'");
		datos.append(dateformatter.format(super.getFecha().getTime()));
		datos.append("\n");
		datos.append("Duración (en segundos): ");
		datos.append(duracion);
		datos.append("\n\n");
		return datos.toString();
	}

}

package Internal;

import java.util.Calendar;

public class TarifaFranjaHoraria extends TarifaExtra{

	/**
	 *
	 */
	private static final long serialVersionUID = -3674390586293196970L;
	int inicioFranja;
	int finFranja;

	public TarifaFranjaHoraria(Tarifa tar, int precio, int inicio, int fin) {
		super(tar, precio);
		inicioFranja = inicio;
		finFranja = fin;
	}

	@Override

	public int getTarifaMenor(Calendar fecha){
		if((fecha.getTime().getHours()<inicioFranja || fecha.getTime().getHours()>finFranja)){
			return super.getTarifa(fecha, -1);
		}
		return super.getTarifa(fecha, super.getTarifa());
	}

	@Override

	public int getTarifa(Calendar fecha,int precio){
		if((fecha.getTime().getHours()>=inicioFranja && fecha.getTime().getHours()<=finFranja) && (precio >= super.getTarifa() || precio == -1)){
			return super.getTarifa(fecha, super.getTarifa());
		}
		return super.getTarifa(fecha, precio);
	}

	public void setInicioFranja(int inicio){
		inicioFranja = inicio;
	}

	public void setFinalFranja(int fin){
		finFranja = fin;
	}

	public int getInicioFranja(){
		return inicioFranja;
	}

	public int getFinalFranja(){
		return finFranja;
	}

	@Override

	public String toString(){
		return super.toString() + " cent/min. Inicio: " + Integer.toString(inicioFranja) + " horas. Fin: " + Integer.toString(finFranja) + " horas";
	}

}

package Internal;

import java.util.Calendar;

public class TarifaDia extends TarifaExtra{

	/**
	 *
	 */
	private static final long serialVersionUID = 6664337388979083841L;
	private static String[] dias = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
	private int diaSemana;
	public TarifaDia(Tarifa tar, int precio, int dia) {
		super(tar, precio);
		diaSemana = dia;
	}

	public void setDia(int dia){
		diaSemana = dia;
	}

	public int getDia(){
		return diaSemana;
	}

	@Override

	public int getTarifaMenor(Calendar fecha){
		if((fecha.get(Calendar.DAY_OF_WEEK) != diaSemana)){
			return super.getTarifa(fecha, -1);
		}
		return super.getTarifa(fecha, super.getTarifa());
	}

	public int getTarifa(Calendar fecha, int precio){
		if((fecha.get(Calendar.DAY_OF_WEEK) == diaSemana) && (precio >= super.getTarifa() || precio == -1)){
			return super.getTarifa(fecha,super.getTarifa());
		}
		return super.getTarifa(fecha,precio);
	}

	@Override

	public String toString(){
		return super.toString() + " cent/min. Para el dia " + dias[diaSemana-1];
	}


}

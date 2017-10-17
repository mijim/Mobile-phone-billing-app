package Internal;

import java.util.Calendar;

public abstract class TarifaExtra extends Tarifa{
	/**
	 *
	 */
	private static final long serialVersionUID = -4499814768927944336L;
	private Tarifa tarifa;


	public TarifaExtra(Tarifa tar, int precio){
		super(precio);
		tarifa = tar;
	}

	public int getTarifa(Calendar fecha,int precio){
		return tarifa.getTarifa(fecha,precio);
	}


	@Override

	public void setTarifa(int precio){
		tarifa.setTarifa(precio);
	}

	@Override

	public String toString(){
		return tarifa.toString() + "\nTarifa extra: \n" + super.toString();
	}
}

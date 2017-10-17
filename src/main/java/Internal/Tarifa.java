package Internal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.TreeSet;

public abstract class Tarifa implements Serializable{
	private static final long serialVersionUID = 4137215118354044356L;
	private int tarifa;

	public Tarifa(){
		super();
	}

	public Tarifa(int precio){
		tarifa = precio;
	}

	public void setTarifa(int precio){
		tarifa = precio;
	}

	public int getTarifa(){
		return tarifa;
	}

	public int getTarifa(Calendar fecha,int precio){
		if(precio < tarifa && precio != -1){
			return precio;
		}
		return tarifa;
	}

	public int getTarifaMenor(Calendar fecha){
		return tarifa;
	}
	@Override

	public String toString(){
		return Integer.toString(tarifa);
	}
}

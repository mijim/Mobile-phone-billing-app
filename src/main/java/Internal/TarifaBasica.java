package Internal;



public class TarifaBasica extends Tarifa{
	private static final long serialVersionUID = -3273409829670078165L;

	public TarifaBasica(){
		super();
	}
	public TarifaBasica(int precio){
		super(precio);
	}
	@Override
	public String toString(){
		return "\nTarifa basica: " + super.toString() + " cent/min";
	}
}

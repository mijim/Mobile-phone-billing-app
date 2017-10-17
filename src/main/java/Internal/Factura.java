package Internal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Factura extends ClaseConFecha{
	private static final long serialVersionUID = 5012244470981657684L;
	private Tarifa tarifa; // Unidades en $/seg
	private String periodoFacturacion; // En dias
	private int importe;
	private int cod;
	
	public Factura(){
		super();
		tarifa = new TarifaBasica();
		periodoFacturacion = "";
		importe = 0;
	}
	
	public Factura(Tarifa tarifa, Calendar fechaEmision, String periodo, int importe){
		super(fechaEmision);
		this.tarifa = tarifa;
		this.periodoFacturacion = periodo;
		this.importe = importe;
	}
	
	//Metodos get
	
	public Tarifa getTarifa(){
		return tarifa;
	}
	
	public String getPeriodoFacturacion(){
		return periodoFacturacion;
	}
	
	public int getImporte(){
		return importe;
	}

    public int getCod(){
        return cod;
    }
	
	//Metodos set
	
	public void setTarifa(Tarifa tar){
		tarifa = tar;
	}
	
	public void setPeriodoFacturacion(String periodo){
		periodoFacturacion = periodo;
	}
	
	public void setImporte(int imp){
		importe = imp;
	}

	public void setCod(int cod1){
		cod = cod1;
	}

	@Override
	public String toString(){
		StringBuilder datos = new StringBuilder();
		datos.append("Fecha: ");
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy ' a las ' HH:mm:ss");
		datos.append(dateformatter.format(super.getFecha().getTime()));
		datos.append(tarifa);
		datos.append("\n");
		datos.append("Periodo de facturacion : ");
		datos.append(periodoFacturacion);
		datos.append("\n");
		datos.append("Importe (en centimos): ");
		datos.append(importe);
		return datos.toString();
	}
	
}

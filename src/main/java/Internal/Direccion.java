package Internal;

import java.io.Serializable;

public class Direccion implements Serializable{
	private static final long serialVersionUID = 1078599963905126748L;
	private int codigoPostal;
	private String provincia;
	private String poblacion;
	
	public Direccion(){
		codigoPostal = 00000;
		provincia = "";
		poblacion = "";
	}
	
	public Direccion(int cp, String prov, String pobl){
		codigoPostal = cp;
		provincia = prov;
		poblacion = pobl;
	}
	
	//Metodos get
	
	public int getCodigoPostal(){
		return codigoPostal;
	}
	
	public String getProvincia(){
		return provincia;
	}
	
	public String getPoblacion(){
		return poblacion;
	}
	
	//Metodos set
	
	
	public void setCodigoPostal(int cp){
		codigoPostal = cp;
	}
	
	public void setProvincia(String prov){
		provincia = prov;
	}
	
	public void setPoblacion(String pobl){
		poblacion = pobl;
	}
	
	@Override
	
	public boolean equals(Object o){
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Direccion))
			return false;
		Direccion dir = (Direccion) o;
		if (this.codigoPostal != dir.codigoPostal)
			return false;
		if (!this.poblacion.equals(dir.poblacion))
			return false;
		if (this.provincia.equals(dir.provincia))
			return false;
		return true;
	}
}
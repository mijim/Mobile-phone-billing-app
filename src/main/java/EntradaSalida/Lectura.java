package EntradaSalida;

import java.util.Calendar;

import Excepciones.NumeroNegativoException;
import Internal.Direccion;

public interface Lectura {
    public int leerOpcion();
    public String leerNombre();
    public String leerApellidos() ;
    public String leerNIF() ;
    public Direccion leerDireccion() ;
    public String leerCorreoElectronico() ;
    public int leerTarifa() ;
    public boolean esParticular();
    public int leerNumeroTelefono();
    public Calendar leerFecha();
    public int leerDuracion() throws NumeroNegativoException;
    public int leerCodigoFactura();
    public boolean esTarifaDia();
    public int leerDia();
    public int leerHora();
}
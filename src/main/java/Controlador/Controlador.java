package Controlador;
import Excepciones.FechaIncorrectaException;
import Modelo.Modelo;
import Vista.Vista;

import java.util.Calendar;

/**
 * Created by miguel on 10/05/16.
 */
public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public Controlador(){
        super();
    }

    public void setModelo(Modelo m){
        modelo = m;
    }

    public void setVista(Vista v){
        vista = v;
    }


    public void filtrarTablaFecha() throws FechaIncorrectaException {
        Calendar fechaInicio = vista.getFechaInicioVentana();
        Calendar fechaFin = vista.getFechaFinalVentana();
        modelo.filtrarTablaFecha(fechaInicio,fechaFin);
    }

    public void actualizarTabla() {
        modelo.actualizarClientes();
    }

    public void nuevoParticular() {
        modelo.addParticular(vista.getNIFNuevoCliente(),vista.getNombreNuevoCliente(),vista.getApellidosNuevoCliente(),vista.getCodigoPostalNuevoCliente(),vista.getProvinciaNuevoCliente(),vista.getPoblacionNuevoCliente(),vista.getCorreoElectronicoNuevoCliente(),vista.getFechaDeAltaNuevoCliente(),vista.getTarifaBasicaNuevoCliente());
    }

    public void nuevoEmpresa() {
        modelo.addEmpresa(vista.getNIFNuevoCliente(),vista.getNombreNuevoCliente(),vista.getCodigoPostalNuevoCliente(),vista.getProvinciaNuevoCliente(),vista.getPoblacionNuevoCliente(),vista.getCorreoElectronicoNuevoCliente(),vista.getFechaDeAltaNuevoCliente(),vista.getTarifaBasicaNuevoCliente());
    }

    public void eliminar(String valueAt) {
        modelo.delCliente(valueAt);
    }

    public void addTarifaDia() {
        modelo.addTarifaDia(vista.getSelectedClient(),vista.getPrecioTarifaExtra(), vista.getDiaSemanaTarifaExtra());
    }

    public void addTarifaFranja() {
        modelo.addTarifaFranja(vista.getSelectedClient(),vista.getPrecioTarifaExtra(),vista.getInicioFranjaTarifaExtra(),vista.getFinFranjaTarifaExtra());
    }

    public void nuevaTarifaBasica() {
        modelo.nuevaTarifaBasica(vista.getSelectedClient(),vista.getCambioDeTarifaBasica());
    }

    public void grabarDatos() {
        modelo.almacenaDatos();
    }

    public void addLlamada() {
        modelo.nuevaLlamada(vista.getSelectedClient(),vista.getNumeroLlamada(),vista.getFechaLlamada(),vista.getDuracionLlamada());
    }

    public void filtrarTablaFechaLlamadas() throws FechaIncorrectaException {
        Calendar inicio = vista.getFechaInicioVentanaLlamadas();
        Calendar fin = vista.getFechaFinalVentanaLlamadas();
        modelo.filtrarTablaFechaLlamadas(vista.getSelectedClient(), inicio, fin);
    }

    public void actualizarTablaLlamadas() {
        modelo.actualizarModeloTablaLlamadas(vista.getSelectedClient());
    }

    public void actualizarListaFacturas() {
        modelo.actualizarListaFacturas(vista.getSelectedClient());
    }

    public int emitirFactura() {
        return modelo.emitirFactura(vista.getSelectedClient());
    }

    public void filtrarListaFechaFacturas() throws FechaIncorrectaException{
        Calendar inicio = vista.getFechaInicioVentanaFacturas();
        Calendar fin = vista.getFechaFinalVentanaFacturas();
        modelo.filtrarFechaListaLlamadas(vista.getSelectedClient(), inicio, fin);
    }
}

package Modelo;

import Data.Data;
import Excepciones.FechaIncorrectaException;
import Fabrica.FabricaClientes;
import Fabrica.FabricaTarifas;
import Generico.EntreFechasGenerico;
import Internal.*;
import Vista.Vista;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by miguel on 10/05/16.
 */
public class Modelo {

    private static Object[] nombreColumnasClientes = {"DNI", "Nombre", "Fecha de alta"};
    private static Object[] nombreColumnasLlamadas = {"Telefono", "Fecha ", "Duracion"};
    private static Data datos = new Data();
    private Vista vista;
    private DefaultTableModel tmClientes;
    private DefaultTableModel tmLlamadas;
    private Object listaFacturas[];

    public Modelo(){
        super();
        recuperaDatos();
    }

    public void setVista(Vista v){
        vista = v;
    }

    public void actualizarClientes(){
        actualizarClientesLista(datos.listaClientes());
    }

    private void actualizarClientesLista(List<Cliente> ls){
        Object array[][] = new Object[ls.size()][3];
        SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
        for(int a=0;a<ls.size();a++){
            array[a][0] = ls.get(a).getNIF();
            array[a][1] = ls.get(a).getNombre();
            array[a][2] = dateformatter.format(ls.get(a).getFecha().getTime());
        }
        tmClientes = new ModeloTabla(array, nombreColumnasClientes);
        vista.actualizarTablaClientes();
    }

    public String getInfoCliente(String dni) {
        return datos.getCliente(dni).toString();
    }


    public void filtrarTablaFecha(Calendar fechaInicio, Calendar fechaFin) throws FechaIncorrectaException {
        actualizarClientesLista(EntreFechasGenerico.subconjuntoEntreDosFechas(datos.listaClientes(),fechaInicio,fechaFin));
    }

    public void addParticular(String dni, String nombre, String apellidos,int cp,String prov,String pobl, String correo, Calendar fecha, int tarifa) {
        datos.addCliente(dni, FabricaClientes.getClienteParticular(nombre,apellidos,dni,new Direccion(cp,prov,pobl),correo,fecha,FabricaTarifas.getTarifaBasica(tarifa)));
        actualizarClientes();
    }

    public void addEmpresa(String dni, String nombre,int cp,String prov,String pobl, String correo, Calendar fecha, int tarifa) {
        datos.addCliente(dni,FabricaClientes.getClienteEmpresa(nombre,dni,new Direccion(cp,prov,pobl),correo,fecha,FabricaTarifas.getTarifaBasica(tarifa)));
        actualizarClientes();
    }

    public void delCliente(String valueAt) {
        datos.removeCliente(valueAt);
        actualizarClientes();
    }

    public void addTarifaDia(String valueAt, int v, int i) {
        datos.getCliente(valueAt).setTarifa(FabricaTarifas.getTarifaDia(datos.getCliente(valueAt).getTarifa(),v,i));
        actualizarClientes();
    }

    public void addTarifaFranja(String valueAt, int i, int i1, int i2) {
        datos.getCliente(valueAt).setTarifa(FabricaTarifas.getTarifaFranjaHoraria(datos.getCliente(valueAt).getTarifa(),i,i1,i2));
        actualizarClientes();
    }

    public void nuevaTarifaBasica(String selectedClient, int cambioDeTarifaBasica) {
        datos.getCliente(selectedClient).setTarifa(FabricaTarifas.getTarifaBasica(cambioDeTarifaBasica));
        actualizarClientes();
    }

    public TableModel getModeloTablaClientes() {
        return tmClientes;
    }


    public void actualizarModeloTablaLlamadasLista(List<Llamada> ls){
        Object array[][] = new Object[ls.size()][3];
        SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
        for(int a=0;a<ls.size();a++){
            array[a][0] = ls.get(a).getNumeroTelefono();
            array[a][1] = dateformatter.format(ls.get(a).getFecha().getTime());
            array[a][2] = ls.get(a).getDuracion();
        }
        tmLlamadas = new ModeloTabla(array, nombreColumnasLlamadas);
        vista.actualizarTablaLlamadas();
    }

    public void actualizarModeloTablaLlamadas(String nif){
        actualizarModeloTablaLlamadasLista(datos.getCliente(nif).getLlamadas());
    }

    public TableModel getModeloTablaLlamadas(){
        return tmLlamadas;
    }

    public void nuevaLlamada(String selectedClient, int numeroLlamada, Calendar fechaLlamada, int duracionLlamada) {
        datos.getCliente(selectedClient).addLlamada(new Llamada(numeroLlamada,fechaLlamada,duracionLlamada));
        actualizarModeloTablaLlamadas(selectedClient);
    }

    public void filtrarTablaFechaLlamadas(String selectedClient, Calendar inicio, Calendar fin) throws FechaIncorrectaException {
        List<Llamada> lsL = datos.getCliente(selectedClient).getLlamadas();
        List<Llamada> ls = EntreFechasGenerico.subconjuntoEntreDosFechas(lsL,inicio,fin);
        actualizarModeloTablaLlamadasLista(ls);
    }
    //Grabacion de datos

    public void almacenaDatos() {
        ObjectOutputStream oos=null;
        try {
            try {
                FileOutputStream fos = new FileOutputStream("Datos.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(datos);
            }
            finally {
                oos.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.out.println("El fichero no existe.");
            exc.printStackTrace();
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }

    }

    private void recuperaDatos() {
        ObjectInputStream ois = null;
        try{
            try {
                FileInputStream fis = new FileInputStream("Datos.bin");
                ois = new ObjectInputStream(fis);
                datos = (Data)ois.readObject();
            }
            finally {
                if(ois != null) ois.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.err.println("Fichero de datos no existe. Se crea Datos.bin");
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            exc.printStackTrace();
        }


    }


    public String getInfoFactura(int i) {
        return datos.getFactura(i).toString();
    }

    public void actualizarListaFacturas(String selectedClient) {
        listaFacturas = datos.getCliente(selectedClient).getFacturas().toArray();
        vista.actualizarListaFacturas();
    }

    public Object[] getListaFacturas(){
        if(listaFacturas.length == 0)
            return new String[]{" "};
        return listaFacturas;
    }

    public int emitirFactura(String NIF) {
        Cliente cliente = datos.getCliente(NIF);
        List<Integer> facturasCliente = cliente.getFacturas();
        List<Llamada> llamadas = cliente.getLlamadas();
        if(!llamadas.isEmpty()){
            int importe = 0;
            Calendar fechaEmision = Calendar.getInstance();
            SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy ' a las ' HH:mm:ss");
            String periodo="";
            if(facturasCliente.isEmpty()){
                for(Llamada llamada : llamadas){
                    importe += cliente.getTarifa().getTarifaMenor(llamada.getFecha()) * (llamada.getDuracion()*1.0)/60;
                }
                periodo = "De " + dateformatter.format(cliente.getFecha().getTime()) + " a " + dateformatter.format(fechaEmision.getTime());
            }else{
                Calendar fechaUltimaFactura = datos.getFactura(facturasCliente.get(facturasCliente.size()-1)).getFecha();
                for(Llamada llamada : llamadas){
                    if(llamada.getFecha().compareTo(fechaUltimaFactura) > 0){
                        importe += cliente.getTarifa().getTarifaMenor(llamada.getFecha()) * (llamada.getDuracion()*1.0)/60;
                    }
                }
                periodo = "De " + dateformatter.format(fechaUltimaFactura.getTime()) + " a " + dateformatter.format(fechaEmision.getTime());
            }
            Factura fac = new Factura(cliente.getTarifa(), fechaEmision, periodo, importe);
            int codfac = datos.addFactura(fac);
            cliente.addFactura(codfac);
            return codfac;
        }
        return -1;
    }

    public void filtrarFechaListaLlamadas(String selectedClient, Calendar inicio, Calendar fin) throws FechaIncorrectaException {
        Cliente cl = datos.getCliente(selectedClient);
        List<Integer> listaKey = cl.getFacturas();
        List<Factura> ls = new LinkedList<Factura>();
        for(int i : listaKey){
            ls.add(datos.getFactura(i));
        }
        ls= EntreFechasGenerico.subconjuntoEntreDosFechas(ls, inicio, fin);
        List<Integer> ret = new LinkedList<Integer>();
        for(Factura f: ls){
            ret.add(f.getCod());
        }
        listaFacturas = ret.toArray();
        vista.actualizarListaFacturas();
    }
}

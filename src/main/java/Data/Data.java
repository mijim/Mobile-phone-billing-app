package Data;

import Internal.Cliente;
import Internal.Factura;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Data implements Serializable{
    private static final long serialVersionUID = 4890724711358611373L;
    private TreeMap<String, Cliente> clientes;
    private TreeMap<Integer, Factura> facturas;

    public Data(){
        clientes = new TreeMap<String, Cliente>();
        facturas = new TreeMap<Integer, Factura>();
    }

    //Clientes

    public void addCliente(String NIF,Cliente cliente){
        clientes.put(NIF, cliente);
    }

    public void removeCliente(String NIF){
        clientes.remove(NIF);
    }

    public Cliente getCliente(String NIF){
        return clientes.get(NIF);
    }

    public List<Cliente> listaClientes(){
        List<Cliente> lista = new LinkedList<Cliente>();
        Set<String> listaNIF = clientes.keySet();
        for(String line : listaNIF){
            lista.add(clientes.get(line));
        }
        return lista;
    }

    //Facturas
    private int codigo=0;


    public int addFactura(Factura fac){
        codigo++;
        facturas.put(codigo, fac);
        facturas.get(codigo).setCod(codigo);
        return codigo;
    }

    public Factura getFactura(int i){
        return facturas.get(i);
    }
}

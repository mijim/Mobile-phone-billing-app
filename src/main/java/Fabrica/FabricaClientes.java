package Fabrica;

import java.util.Calendar;

import Internal.Cliente;
import Internal.Direccion;
import Internal.Empresa;
import Internal.Particular;
import Internal.Tarifa;

public class FabricaClientes {

    public static Cliente getClienteParticular(String nombre, String apellidos, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
        return new Particular(nombre,apellidos,NIF,direccion,correoElectronico,fechaDeAlta,tarifa);
    }

    public static Cliente getClienteEmpresa(String nombre, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
        return new Empresa(nombre,NIF,direccion,correoElectronico,fechaDeAlta,tarifa);
    }
}

package Fabrica;


import Internal.Tarifa;
import Internal.TarifaBasica;
import Internal.TarifaDia;
import Internal.TarifaFranjaHoraria;

public class FabricaTarifas {
    public static Tarifa getTarifaBasica(int precio){
        return new TarifaBasica(precio);
    }

    public static Tarifa getTarifaDia(Tarifa tar, int precio, int dia){
        return new TarifaDia(tar,precio,dia);
    }

    public static Tarifa getTarifaFranjaHoraria(Tarifa tar, int precio, int inicio, int fin){
        return new TarifaFranjaHoraria(tar,precio,inicio,fin);
    }
}

import Internal.Tarifa;
import Internal.TarifaBasica;
import Internal.TarifaDia;
import Internal.TarifaFranjaHoraria;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TarifaTest {
    private Tarifa tarifaBasica = new TarifaBasica(5);           //Tarifa basica de 5
    private Tarifa tarifaDia = new TarifaDia(tarifaBasica,2,1);  //Tarifa de domingos por 2
    private Tarifa tarifaMañanas = new TarifaFranjaHoraria(tarifaDia,4,8,14); //Tarifa de 8 a 14 de la mañana por 4
    private Calendar fecha;

    @Test

    public void testTarifa1() throws Exception{
        fecha = Calendar.getInstance();
        fecha.set(2016,3,24,15,0);//Domingo a las 15
        assertEquals(2,tarifaMañanas.getTarifaMenor(fecha),0);
    }

    @Test

    public void testTarifa2() throws Exception{
        fecha = new GregorianCalendar(2016,3,24,12,0); //Domingo a las 12
        assertEquals(2,tarifaMañanas.getTarifaMenor(fecha),0);
    }

    @Test

    public void testTarifa3() throws Exception{
        fecha = new GregorianCalendar(2016,3,23,16,0); //sabado las 12
        assertEquals(5,tarifaMañanas.getTarifaMenor(fecha),0);
    }

    @Test

    public void testTarifa4() throws Exception{
        fecha = new GregorianCalendar(2016,3,23,12,0); //sabado a las 12
        assertEquals(4,tarifaMañanas.getTarifaMenor(fecha),0);
    }

}

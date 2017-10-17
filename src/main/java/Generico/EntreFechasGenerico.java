package Generico;

import Excepciones.FechaIncorrectaException;
import Internal.ClaseConFecha;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by miguel on 10/05/16.
 */
public class EntreFechasGenerico {
    public static <T extends ClaseConFecha> List<T> subconjuntoEntreDosFechas(List<T> ls, Calendar fechaInicio, Calendar fechaFinal) throws FechaIncorrectaException {
        if(fechaInicio.compareTo(fechaFinal) > 0) throw new FechaIncorrectaException();
        List<T> subconjuntoLista = new LinkedList<T>();
        for(T item : ls){
            if(item.getFecha().compareTo(fechaInicio) >= 0 && item.getFecha().compareTo(fechaFinal) <= 0){
                subconjuntoLista.add(item);
            }
        }
        return subconjuntoLista;
    }
}

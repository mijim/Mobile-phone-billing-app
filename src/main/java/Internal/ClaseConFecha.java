package Internal;

import java.io.Serializable;
import java.util.Calendar;

public class ClaseConFecha implements Serializable{
    private static final long serialVersionUID = 3271397819134324294L;
    private Calendar fecha;

    public ClaseConFecha(){
        fecha = null;
    }

    public ClaseConFecha(Calendar f){
        fecha = f;
    }

    public void setFecha(Calendar f){
        fecha = f;
    }

    public Calendar getFecha(){
        return fecha;
    }

}

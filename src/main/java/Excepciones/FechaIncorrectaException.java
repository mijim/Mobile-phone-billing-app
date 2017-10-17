package Excepciones;

public class FechaIncorrectaException extends Exception{
    private static final long serialVersionUID = -1578606571725507965L;

    public FechaIncorrectaException(){
        super("Las fechas introducidas no son correctas");
    }
}

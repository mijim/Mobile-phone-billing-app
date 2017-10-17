package Excepciones;

public class NumeroNegativoException extends Exception{
    private static final long serialVersionUID = 8901785827936267655L;

    public NumeroNegativoException() {
        super("La cifra introducida no puede ser negativa.");
    }
}

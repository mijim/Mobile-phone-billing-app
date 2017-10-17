package Principal;

import Controlador.Controlador;
import Modelo.Modelo;
import Vista.Vista;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by miguel on 10/05/16.
 */
public class Principal {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, ParseException {
        SwingUtilities.invokeLater(new Runnable() {
            Controlador controlador = new Controlador();
            Vista vista = new Vista();
            Modelo modelo = new Modelo();
            public void run() {
                modelo.setVista(vista);
                controlador.setVista(vista);
                controlador.setModelo(modelo);
                vista.setControlador(controlador);
                vista.setModelo(modelo);
                try {
                        vista.crearGUI();

                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (ParseException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                modelo.actualizarClientes();
            }
        });

    }
}

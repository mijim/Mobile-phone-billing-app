package Vista;

import Controlador.Controlador;
import Excepciones.FechaIncorrectaException;
import Modelo.Modelo;
import Vista.DatePicker.JDatePicker;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by miguel on 3/05/16.
 */
public class Vista {
    private static Object[] nombreColumnasClientes = {"DNI", "Nombre", "Fecha de alta"};
    private static Object[] nombreColumnasLlamadas = {"Telefono", "Fecha ", "Duracion"};

    //MVC
    private Controlador controlador;
    private Modelo modelo;

    //Vantanas
    private JFrame ventana;
    private JDialog ventanaNuevoCliente;
    private JDialog ventanaTarifaExtra;
    private JDialog ventanaNuevaTarifaBasica;
    private JDialog ventanaLlamadas;
    private JDialog ventanaFacturas;


    //ELEMENTOS
    //Elementos de ventana principal
    private JDatePicker pickerFechaVentana;
    private JDatePicker pickerFecha2Ventana;
    private JToggleButton filtrarEntreDosFechasBotonVentana;
    private JTextArea textAreaInfoClientesVentana;
    private JTable clientesTabla;

    //Elementos de ventana nuevo cliente
    private JFormattedTextField textoNIFNuevoCliente;
    private JTextField textoNombreNuevoCliente;
    private JTextField textoApellidosNuevoCliente;
    private JFormattedTextField textoCodigoPostalNuevoCliente;
    private JTextField textoProvinciaNuevoCliente;
    private JTextField textoPoblacionNuevoCliente;
    private JTextField textoCorreoElectronicoNuevoCliente;
    private JDatePicker datePickerFechaDeAltaNuevoCliente;
    private JFormattedTextField textoTarifaBasicaNuevoCliente;

    //Elementos de ventana añadir tarifa extra
    private JFormattedTextField textoPrecioTarifaExtra;
    private JComboBox comboBoxDiasSemanaTarifaExtra;
    private JComboBox comboBoxHoraInicioTarifaExtra;
    private JComboBox comboBoxHoraFinTarifaExtra;

    //Elementos de ventana nueva tarifa basica

    private JFormattedTextField textoNuevaTarifaBasica;

    //Elementos de ventana llamadas

    private JTable tablaLlamadas;
    private JFormattedTextField textoTelefonoLlamadas;
    private JDatePicker datePickerLlamadas;
    private JFormattedTextField textoDuracionLlamadas;
    private JDatePicker pickerFechaVentanaLlamadas;
    private JDatePicker pickerFecha2VentanaLlamadas;

    //Elementos de ventana facturas

    private JList listaFacturas;
    private JDatePicker pickerFechaVentanaFacturas;
    private JDatePicker pickerFecha2VentanaFacturas;
    //Constructor, controlador y modelo

    public Vista() {
        super();
    }

    public void setControlador(Controlador c) {
        controlador = c;
    }

    public void setModelo(Modelo m){
        modelo = m;
    }


    //Getters

    //Getters Ventana Principal

    public String getSelectedClient(){
        if(clientesTabla.getSelectedRow() > -1){
            return (String) clientesTabla.getValueAt(clientesTabla.getSelectedRow(), 0);
        }
        return null;
    }

    public Calendar getFechaInicioVentana() {
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(pickerFechaVentana.getModel().getYear(), pickerFechaVentana.getModel().getMonth(), pickerFechaVentana.getModel().getDay(), 0, 0, 0);
        return fechaInicio;
    }

    public Calendar getFechaFinalVentana() {
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(pickerFecha2Ventana.getModel().getYear(), pickerFecha2Ventana.getModel().getMonth(), pickerFecha2Ventana.getModel().getDay(), 24, 60, 60);
        return fechaFin;
    }

    //Getter de tarifa basica

    public int getCambioDeTarifaBasica(){
        return (Integer)textoNuevaTarifaBasica.getValue();
    }

    //Getters de nuevo cliente

    public String getNIFNuevoCliente(){
        return (String)textoNIFNuevoCliente.getValue();
    }

    public String getNombreNuevoCliente(){
        return textoNombreNuevoCliente.getText();
    }

    public String getApellidosNuevoCliente(){
        return textoApellidosNuevoCliente.getText();
    }

    public int getCodigoPostalNuevoCliente(){
        return (Integer)textoCodigoPostalNuevoCliente.getValue();
    }

    public String getProvinciaNuevoCliente(){
        return textoProvinciaNuevoCliente.getText();
    }

    public String getPoblacionNuevoCliente(){
        return textoPoblacionNuevoCliente.getText();
    }

    public String getCorreoElectronicoNuevoCliente(){
        return textoCorreoElectronicoNuevoCliente.getText();
    }

    public Calendar getFechaDeAltaNuevoCliente(){
        Calendar fecha = Calendar.getInstance();
        fecha.set(datePickerFechaDeAltaNuevoCliente.getModel().getYear(), datePickerFechaDeAltaNuevoCliente.getModel().getMonth(), datePickerFechaDeAltaNuevoCliente.getModel().getDay());
        return fecha;
    }

    public int getTarifaBasicaNuevoCliente(){
        return (Integer)textoTarifaBasicaNuevoCliente.getValue();
    }

    //Getters tarifa extra

    public int getPrecioTarifaExtra(){
        return (Integer)textoPrecioTarifaExtra.getValue();
    }

    public int getDiaSemanaTarifaExtra(){
        return (Integer)comboBoxDiasSemanaTarifaExtra.getSelectedItem();
    }

    public int getInicioFranjaTarifaExtra(){
        return (Integer)comboBoxHoraInicioTarifaExtra.getSelectedItem();
    }

    public int getFinFranjaTarifaExtra(){
        return (Integer)comboBoxHoraFinTarifaExtra.getSelectedItem();
    }


    //Getters Llamadas

    public Calendar getFechaInicioVentanaLlamadas() {
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(pickerFechaVentanaLlamadas.getModel().getYear(), pickerFechaVentanaLlamadas.getModel().getMonth(), pickerFechaVentanaLlamadas.getModel().getDay(), 0, 0, 0);
        return fechaInicio;
    }

    public Calendar getFechaFinalVentanaLlamadas() {
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(pickerFecha2VentanaLlamadas.getModel().getYear(), pickerFecha2VentanaLlamadas.getModel().getMonth(), pickerFecha2VentanaLlamadas.getModel().getDay(), 24, 60, 60);
        return fechaFin;
    }

    public int getNumeroLlamada(){
        return (Integer)textoTelefonoLlamadas.getValue();
    }

    public Calendar getFechaLlamada(){
        Calendar fecha = Calendar.getInstance();
        fecha.set(datePickerLlamadas.getModel().getYear(), datePickerLlamadas.getModel().getMonth(), datePickerLlamadas.getModel().getDay());
        return fecha;
    }

    public int getDuracionLlamada(){
        return (Integer)textoDuracionLlamadas.getValue();
    }


    //Actualizar datos

    public void actualizarTablaClientes() {
        clientesTabla.setModel(modelo.getModeloTablaClientes());
        deseleccionCliente();
    }

    public void actualizarTablaLlamadas(){
        tablaLlamadas.setModel(modelo.getModeloTablaLlamadas());
    }

    private void deseleccionCliente(){
        textAreaInfoClientesVentana.setText("Selecciona un cliente                                 ");

    }

    //Crear GUI

    public void crearGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, ParseException, IOException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ventana = new JFrame("Gestor Clientes Telefonico");
        ventana.setResizable(false);
        final Container contenedor = ventana.getContentPane();

        //Elementos del panel sur
        JLabel fecha1Label = new JLabel("Inicio: ");
        pickerFechaVentana = new JDatePicker();
        JLabel fecha2Label = new JLabel("Fin: ");
        pickerFecha2Ventana = new JDatePicker();
        filtrarEntreDosFechasBotonVentana = new JToggleButton("Filtrar entre dos fechas");
        filtrarEntreDosFechasBotonVentana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton boton = (JToggleButton) e.getSource();
                if (boton.isSelected()) {
                    try {
                        controlador.filtrarTablaFecha();
                    } catch (FechaIncorrectaException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "¡Fechas Incompatibles!");
                    }
                } else {
                    controlador.actualizarTabla();
                }
            }
        });





        //Añadimos al Jpanel y al contenedor
        JPanel jPanelNorte = new JPanel();
        jPanelNorte.add(fecha1Label);
        jPanelNorte.add(pickerFechaVentana);
        jPanelNorte.add(fecha2Label);
        jPanelNorte.add(pickerFecha2Ventana);
        jPanelNorte.add(filtrarEntreDosFechasBotonVentana);
        contenedor.add(jPanelNorte, BorderLayout.SOUTH);



        textAreaInfoClientesVentana = new JTextArea();
        textAreaInfoClientesVentana.setEnabled(false);
        textAreaInfoClientesVentana.setDisabledTextColor(Color.black);
        textAreaInfoClientesVentana.setBackground(Color.CYAN);
        //Elemento del panerl centro

        Object o[][] = {{"", "", ""}};
        clientesTabla = new JTable(o, nombreColumnasClientes);
        clientesTabla.setPreferredScrollableViewportSize(new Dimension(800, 300));
        clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientesTabla.getTableHeader().setReorderingAllowed(false);

        //Cambio de fila seleccionada
        clientesTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (clientesTabla.getSelectedRow() > -1) {
                    textAreaInfoClientesVentana.setText(modelo.getInfoCliente((String) clientesTabla.getValueAt(clientesTabla.getSelectedRow(), 0)));
                }
            }
        });


        JScrollPane jsp = new JScrollPane(clientesTabla);
        jsp.setVisible(true);
        JPanel panelCentroClientes = new JPanel();
        panelCentroClientes.setLayout(new BoxLayout(panelCentroClientes, BoxLayout.X_AXIS));
        panelCentroClientes.add(jsp);
        panelCentroClientes.add(textAreaInfoClientesVentana);
        contenedor.add(panelCentroClientes, BorderLayout.CENTER);




        //Botones superiores

        EscuchadorBotones escuchadorBotones = new EscuchadorBotones();
        JPanel jPanelSur = new JPanel();
        contenedor.add(jPanelSur, BorderLayout.NORTH);
        jPanelSur.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton botonNuevoCliente = new JButton();
        botonNuevoCliente.addActionListener(escuchadorBotones);
        botonNuevoCliente.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/add.png")));
        botonNuevoCliente.setContentAreaFilled(true);
        botonNuevoCliente.setName("AñadirCliente");
        botonNuevoCliente.setHorizontalAlignment(0);
        botonNuevoCliente.setToolTipText("Añadir un nuevo cliente");
        jPanelSur.add(botonNuevoCliente);

        JButton botonEliminarCliente = new JButton();
        botonEliminarCliente.addActionListener(escuchadorBotones);
        botonEliminarCliente.setName("EliminarCliente");
        botonEliminarCliente.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/remove.png")));
        botonEliminarCliente.setContentAreaFilled(true);
        botonEliminarCliente.setToolTipText("Eliminar el cliente seleccionado");
        jPanelSur.add(botonEliminarCliente);

        JButton botonLlamadasCliente = new JButton();
        botonLlamadasCliente.addActionListener(escuchadorBotones);
        botonLlamadasCliente.setName("LlamadasCliente");
        botonLlamadasCliente.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/llamadas.png")));
        botonLlamadasCliente.setContentAreaFilled(true);
        botonLlamadasCliente.setToolTipText("Llamadas del cliente seleccionado");
        jPanelSur.add(botonLlamadasCliente);

        JButton botonFacturasCliente = new JButton();
        botonFacturasCliente.addActionListener(escuchadorBotones);
        botonFacturasCliente.setName("FacturasCliente");
        botonFacturasCliente.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/facturas.png")));
        botonFacturasCliente.setContentAreaFilled(true);
        botonFacturasCliente.setToolTipText("Facturas del cliente seleccionado");
        jPanelSur.add(botonFacturasCliente);

        JButton botonEmitirFacturaCliente = new JButton();
        botonEmitirFacturaCliente.addActionListener(escuchadorBotones);
        botonEmitirFacturaCliente.setName("EmitirFactura");
        botonEmitirFacturaCliente.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/facturas_add.png")));
        botonEmitirFacturaCliente.setContentAreaFilled(true);
        botonEmitirFacturaCliente.setToolTipText("Emitir factura para el cliente seleccionado");
        jPanelSur.add(botonEmitirFacturaCliente);

        JButton botonAñadirTarifa = new JButton("Añadir Tarifa");
        botonAñadirTarifa.setName("TarifaExtra");
        botonAñadirTarifa.addActionListener(escuchadorBotones);
        botonAñadirTarifa.setPreferredSize(new Dimension(120,36));
        jPanelSur.add(botonAñadirTarifa);

        JButton botonNuevaTarifaBasica = new JButton("Cambiar tarifa basica");
        botonNuevaTarifaBasica.setName("NuevaTarifaBasica");
        botonNuevaTarifaBasica.addActionListener(escuchadorBotones);
        botonNuevaTarifaBasica.setPreferredSize(new Dimension(170,36));
        jPanelSur.add(botonNuevaTarifaBasica);


        //Ventana principal
        ventana.pack();
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.grabarDatos();
                System.exit(0);

            }
        });
        ventana.setVisible(true);


        //Dialogo ventana nuevo cliente
        ventanaNuevoCliente = new JDialog();
        ventanaNuevoCliente.setTitle("Nuevo Cliente");
        ventanaNuevoCliente.setSize(new Dimension(500, 350));
        ventanaNuevoCliente.setResizable(false);
        ventanaNuevoCliente.setLayout(new GridLayout(11, 2));
        ventanaNuevoCliente.addWindowListener(new WindowAdapter() {
                                                  @Override
                                                  public void windowClosing(WindowEvent e) {
                                                      ventana.setEnabled(true);
                                                  }
                                              });


        Container contenedorVentanaNuevoCLiente = ventanaNuevoCliente.getContentPane();

        contenedorVentanaNuevoCLiente.add(new JLabel("  NIF: "));
        MaskFormatter mascaraDNI = new MaskFormatter("########U");
        textoNIFNuevoCliente = new JFormattedTextField(mascaraDNI);
        textoNIFNuevoCliente.setValue("00000000A");
        contenedorVentanaNuevoCLiente.add(textoNIFNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Nombre: "));
        textoNombreNuevoCliente = new JTextField();
        contenedorVentanaNuevoCLiente.add(textoNombreNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Apellidos: "));
        textoApellidosNuevoCliente = new JTextField();
        contenedorVentanaNuevoCLiente.add(textoApellidosNuevoCliente);

        MaskFormatter mascaraCodigoPostal = new MaskFormatter("#####");
        contenedorVentanaNuevoCLiente.add(new JLabel("  Codigo Postal: "));
        textoCodigoPostalNuevoCliente = new JFormattedTextField(mascaraCodigoPostal);
        textoCodigoPostalNuevoCliente.setValue(99999);
        contenedorVentanaNuevoCLiente.add(textoCodigoPostalNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Provincia: "));
        textoProvinciaNuevoCliente = new JTextField();
        contenedorVentanaNuevoCLiente.add(textoProvinciaNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Poblacion: "));
        textoPoblacionNuevoCliente = new JTextField();
        contenedorVentanaNuevoCLiente.add(textoPoblacionNuevoCliente);


        contenedorVentanaNuevoCLiente.add(new JLabel("  Correo electronico: "));
        textoCorreoElectronicoNuevoCliente = new JTextField();
        contenedorVentanaNuevoCLiente.add(textoCorreoElectronicoNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Fecha de alta: "));
        datePickerFechaDeAltaNuevoCliente = new JDatePicker();
        contenedorVentanaNuevoCLiente.add(datePickerFechaDeAltaNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Tarifa basica (cent/min): "));
        textoTarifaBasicaNuevoCliente = new JFormattedTextField();
        textoTarifaBasicaNuevoCliente.setValue(5);
        contenedorVentanaNuevoCLiente.add(textoTarifaBasicaNuevoCliente);

        contenedorVentanaNuevoCLiente.add(new JLabel("  Tipo de cliente: "));
        JPanel panelEsParticular = new JPanel();
        final JRadioButton botonEsParticular = new JRadioButton("Particular");
        botonEsParticular.setSelected(true);
        final JRadioButton botonEsEmpresa = new JRadioButton("Empresa");
        ButtonGroup grupoEsParticular = new ButtonGroup();
        grupoEsParticular.add(botonEsEmpresa);
        grupoEsParticular.add(botonEsParticular);
        panelEsParticular.add(botonEsParticular);
        panelEsParticular.add(botonEsEmpresa);
        botonEsEmpresa.getModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (botonEsEmpresa.isSelected()) {
                    textoApellidosNuevoCliente.setEnabled(false);
                } else {
                    textoApellidosNuevoCliente.setEnabled(true);
                }
            }
        });
        contenedorVentanaNuevoCLiente.add(panelEsParticular);

        contenedorVentanaNuevoCLiente.add(new JLabel());
        JButton añadirCliente = new JButton("Añadir");
        contenedorVentanaNuevoCLiente.add(añadirCliente);
        añadirCliente.getModel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (botonEsParticular.isSelected()) {
                    controlador.nuevoParticular();

                } else {
                    controlador.nuevoEmpresa();
                }
                ventanaNuevoCliente.setVisible(false);
                ventana.setEnabled(true);
                textoNIFNuevoCliente.setText("");
                textoNombreNuevoCliente.setText("");
                textoApellidosNuevoCliente.setText("");
                textoCodigoPostalNuevoCliente.setValue(99999);
                textoProvinciaNuevoCliente.setText("");
                textoPoblacionNuevoCliente.setText("");
                textoCorreoElectronicoNuevoCliente.setText("");
                textoTarifaBasicaNuevoCliente.setValue(5);
                botonEsParticular.setSelected(true);
            }
        });

        //Dialogo ventana añadir tarifa extra

        ventanaTarifaExtra = new JDialog();
        ventanaTarifaExtra.setSize(new Dimension(200, 130));
        ventanaTarifaExtra.setTitle("Añadir tarifa extra");
        ventanaTarifaExtra.setResizable(false);
        final JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        JButton añadirTarifaExtra = new JButton("Añadir");
        JPanel panelInferiorSuperior = new JPanel();
        MaskFormatter mascaraPrecioTarifaExtra = new MaskFormatter("####");
        textoPrecioTarifaExtra = new JFormattedTextField(mascaraPrecioTarifaExtra);
        textoPrecioTarifaExtra.setValue(9999);
        panelInferiorSuperior.add(new JLabel("Precio: "));
        panelInferiorSuperior.add(textoPrecioTarifaExtra);
        panelInferior.add(panelInferiorSuperior, BorderLayout.NORTH);
        panelInferior.add(añadirTarifaExtra, BorderLayout.SOUTH);
        ventanaTarifaExtra.add(panelInferior, BorderLayout.SOUTH);

        JPanel panelTarifaDia = new JPanel();
        panelTarifaDia.add(new JLabel("Dia: "));
        Object array[] = {1, 2, 3, 4, 5, 6, 7};
        comboBoxDiasSemanaTarifaExtra = new JComboBox(array);
        panelTarifaDia.add(comboBoxDiasSemanaTarifaExtra);

        final JPanel panelTarifaFranjaHoraria = new JPanel();
        Object array2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        comboBoxHoraInicioTarifaExtra = new JComboBox(array2);
        comboBoxHoraFinTarifaExtra = new JComboBox(array2);
        panelTarifaFranjaHoraria.add(new JLabel("Inicio: "));
        panelTarifaFranjaHoraria.add(comboBoxHoraInicioTarifaExtra);
        panelTarifaFranjaHoraria.add(new JLabel("Fin: "));
        panelTarifaFranjaHoraria.add(comboBoxHoraFinTarifaExtra);


        final JTabbedPane panelPestañas = new JTabbedPane();
        panelPestañas.addTab("Dia", panelTarifaDia);
        panelPestañas.addTab("Franja Horaria", panelTarifaFranjaHoraria);
        ventanaTarifaExtra.add(panelPestañas);
        ventanaTarifaExtra.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventana.setEnabled(true);
            }
        });

        añadirTarifaExtra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (panelPestañas.getSelectedIndex() == 0) {
                    controlador.addTarifaDia();
                    ventanaTarifaExtra.setVisible(false);
                    textoPrecioTarifaExtra.setValue(9999);
                    ventana.setEnabled(true);
                } else {
                    if ((Integer) comboBoxHoraInicioTarifaExtra.getSelectedItem() <= (Integer) comboBoxHoraFinTarifaExtra.getSelectedItem()) {
                        controlador.addTarifaFranja();
                        ventanaTarifaExtra.setVisible(false);
                        textoPrecioTarifaExtra.setValue(9999);
                        ventana.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Franja Incompatible!");
                    }
                }
            }
        });


        //Dialogo ventana nueva tarifa basica

        ventanaNuevaTarifaBasica = new JDialog();
        ventanaNuevaTarifaBasica.setSize(new Dimension(200, 60));
        ventanaNuevaTarifaBasica.setTitle("Cambiar tarifa basica");
        ventanaNuevaTarifaBasica.setResizable(false);

        JPanel panelNuevaTarifaBasica = new JPanel();

        textoNuevaTarifaBasica = new JFormattedTextField(mascaraPrecioTarifaExtra);
        textoNuevaTarifaBasica.setValue(9999);
        panelNuevaTarifaBasica.add(new JLabel("Precio: "));
        panelNuevaTarifaBasica.add(textoNuevaTarifaBasica);
        JButton añadirNuevaTarifaBasica = new JButton("Cambiar");
        ventanaNuevaTarifaBasica.add(añadirNuevaTarifaBasica, BorderLayout.SOUTH);
        ventanaNuevaTarifaBasica.add(panelNuevaTarifaBasica, BorderLayout.NORTH);
        ventanaNuevaTarifaBasica.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventana.setEnabled(true);
            }
        });

        añadirNuevaTarifaBasica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.nuevaTarifaBasica();
                ventana.setEnabled(true);
                ventanaNuevaTarifaBasica.setVisible(false);
                textoNuevaTarifaBasica.setValue(9999);
            }
        });

        //Dialogo ventana llamadas

        ventanaLlamadas = new JDialog();
        ventanaLlamadas.setTitle("Llamadas");
        ventanaLlamadas.setResizable(false);

        //Parte norte
        JLabel fecha1LabelLlamadas = new JLabel("Inicio: ");
        pickerFechaVentanaLlamadas = new JDatePicker();
        JLabel fecha2LabelLlamadas = new JLabel("Fin: ");
        pickerFecha2VentanaLlamadas = new JDatePicker();
        JToggleButton filtrarEntreDosFechasBotonVentanaLlamadas = new JToggleButton("Filtrar entre dos fechas");
        filtrarEntreDosFechasBotonVentanaLlamadas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton boton = (JToggleButton) e.getSource();
                if (boton.isSelected()) {
                    try {
                        controlador.filtrarTablaFechaLlamadas();
                    } catch (FechaIncorrectaException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "¡Fechas Incompatibles!");
                    }
                } else {
                    controlador.actualizarTablaLlamadas();
                }
            }
        });

        JPanel panelNorteLlamadas = new JPanel();
        panelNorteLlamadas.add(fecha1LabelLlamadas);
        panelNorteLlamadas.add(pickerFechaVentanaLlamadas);
        panelNorteLlamadas.add(fecha2LabelLlamadas);
        panelNorteLlamadas.add(pickerFecha2VentanaLlamadas);
        panelNorteLlamadas.add(filtrarEntreDosFechasBotonVentanaLlamadas);

        ventanaLlamadas.add(panelNorteLlamadas, BorderLayout.NORTH);

        //Parte centro

        tablaLlamadas = new JTable(o, nombreColumnasLlamadas);
        tablaLlamadas.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tablaLlamadas.setRowSelectionAllowed(false);
        tablaLlamadas.getTableHeader().setReorderingAllowed(false);
        JScrollPane jspLlamadas = new JScrollPane(tablaLlamadas);
        ventanaLlamadas.add(jspLlamadas);

        //Parte sud
        JPanel panelSudPrincipal = new JPanel();
        panelSudPrincipal.setLayout(new BorderLayout());
        JPanel panelSudSecundario = new JPanel();
        panelSudSecundario.setLayout(new GridLayout(2,3));

        JLabel labelTelefonoLlamadas = new JLabel(" Telefono");
        JLabel labelFechaLlamadas = new JLabel(" Fecha");
        JLabel labelDuracionLlamadas = new JLabel(" Duracion (en segundos)");

        MaskFormatter mascaraTelefonoLlamadas = new MaskFormatter("#########");
        MaskFormatter mascaraDuracionLlamadas = new MaskFormatter("######");
        textoTelefonoLlamadas = new JFormattedTextField(mascaraTelefonoLlamadas);
        textoTelefonoLlamadas.setValue(999999999);
        datePickerLlamadas = new JDatePicker();
        textoDuracionLlamadas = new JFormattedTextField(mascaraDuracionLlamadas);
        textoDuracionLlamadas.setValue(999999);
        JButton botonAñadirLlamada = new JButton("Añadir");

        panelSudSecundario.add(labelTelefonoLlamadas);
        panelSudSecundario.add(labelFechaLlamadas);
        panelSudSecundario.add(labelDuracionLlamadas);
        panelSudSecundario.add(textoTelefonoLlamadas);
        panelSudSecundario.add(datePickerLlamadas);
        panelSudSecundario.add(textoDuracionLlamadas);
        panelSudPrincipal.add(panelSudSecundario);
        panelSudPrincipal.add(botonAñadirLlamada, BorderLayout.SOUTH);
        ventanaLlamadas.add(panelSudPrincipal, BorderLayout.SOUTH);

        botonAñadirLlamada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.addLlamada();
                textoTelefonoLlamadas.setValue(999999999);
                textoDuracionLlamadas.setValue(999999);
            }
        });



        //Ventana Llamadas

        ventanaLlamadas.pack();
        ventanaLlamadas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventana.setEnabled(true);
            }
        });

        //Dialogo Ventana Facturas

        ventanaFacturas = new JDialog();
        ventanaFacturas.setTitle("Facturas");
        ventanaFacturas.setResizable(false);
        ventanaFacturas.setSize(new Dimension(700,200));

        JPanel panelSuperiorFacturas = new JPanel();
        final JPanel panelInferiorFacturas = new JPanel();

        ventanaFacturas.add(panelSuperiorFacturas, BorderLayout.NORTH);
        ventanaFacturas.add(panelInferiorFacturas, BorderLayout.SOUTH);
        final JTextArea infoFacturas = new JTextArea("Selecciona una factura");

        //Panel Superior

        JLabel fecha1LabelFacturas= new JLabel("Inicio: ");
        pickerFechaVentanaFacturas = new JDatePicker();
        JLabel fecha2LabelFacturas = new JLabel("Fin: ");
        pickerFecha2VentanaFacturas = new JDatePicker();
        JToggleButton filtrarEntreDosFechasBotonVentanaFacturas = new JToggleButton("Filtrar entre dos fechas");
        filtrarEntreDosFechasBotonVentanaFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton boton = (JToggleButton) e.getSource();
                if (boton.isSelected()) {
                    try {
                        controlador.filtrarListaFechaFacturas();
                    } catch (FechaIncorrectaException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "¡Fechas Incompatibles!");
                    }
                } else {
                    controlador.actualizarListaFacturas();
                    infoFacturas.setText("Selecciona una factura");
                }
            }
        });

        panelSuperiorFacturas.add(fecha1LabelFacturas);
        panelSuperiorFacturas.add(pickerFechaVentanaFacturas);
        panelSuperiorFacturas.add(fecha2LabelFacturas);
        panelSuperiorFacturas.add(pickerFecha2VentanaFacturas);
        panelSuperiorFacturas.add(filtrarEntreDosFechasBotonVentanaFacturas);

        //Panel Centro

        infoFacturas.setEnabled(false);
        infoFacturas.setDisabledTextColor(Color.black);
        infoFacturas.setBackground(Color.CYAN);
        Object ls[] = {1,2,3,4,5};
        listaFacturas = new JList(ls);
        listaFacturas.setVisibleRowCount(6);

        JScrollPane scrollListaFacturas = new JScrollPane(listaFacturas);


        ventanaFacturas.add(scrollListaFacturas, BorderLayout.WEST);
        ventanaFacturas.add(infoFacturas);

        //Panel Inferior

        final JTextField textoNumeroFactura = new JTextField(5);
        JButton botonVerInfoFactura = new JButton("Mostrar informacion");
        panelInferiorFacturas.add(textoNumeroFactura);
        panelInferiorFacturas.add(botonVerInfoFactura);

        botonVerInfoFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    infoFacturas.setText(modelo.getInfoFactura(Integer.parseInt(textoNumeroFactura.getText())));
                }catch (NullPointerException a){
                    JOptionPane.showMessageDialog(null, "No existe la factura con codigo: "+ textoNumeroFactura.getText());
                    infoFacturas.setText("No existe la factura seleccionada   ");
                }
            }
        });

        listaFacturas.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                try {
                    textoNumeroFactura.setText(Integer.toString((Integer) listaFacturas.getSelectedValue()));
                }catch (NullPointerException a){}
            }
        });

        ventanaFacturas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventana.setEnabled(true);
                infoFacturas.setText("Selecciona una factura");
            }
        });


    }

    public void actualizarListaFacturas() {
        listaFacturas.setListData(modelo.getListaFacturas());
    }

    public Calendar getFechaInicioVentanaFacturas() {
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(pickerFechaVentanaFacturas.getModel().getYear(), pickerFechaVentanaFacturas.getModel().getMonth(), pickerFechaVentanaFacturas.getModel().getDay(), 0, 0, 0);
        return fechaInicio;
    }

    public Calendar getFechaFinalVentanaFacturas() {
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(pickerFecha2VentanaFacturas.getModel().getYear(), pickerFecha2VentanaFacturas.getModel().getMonth(), pickerFecha2VentanaFacturas.getModel().getDay(), 24, 60, 60);
        return fechaFin;
    }

    //Escuchadores que atienden a mas de un elemento

    private class EscuchadorBotones implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            if (boton.getName().equals("AñadirCliente")) {
                ventanaNuevoCliente.setVisible(true);
                ventana.setEnabled(false);
            } else if (boton.getName().equals("EliminarCliente")) {
                if (clientesTabla.getSelectedRow() > -1) {
                    controlador.eliminar((String) clientesTabla.getValueAt(clientesTabla.getSelectedRow(), 0));
                    filtrarEntreDosFechasBotonVentana.setSelected(false);
                }else{
                    clienteNoSeleccionado();
                }
            } else if (boton.getName().equals("TarifaExtra")) {
                if (clientesTabla.getSelectedRow() > -1) {
                    ventanaTarifaExtra.setVisible(true);
                    ventana.setEnabled(false);
                }else{
                    clienteNoSeleccionado();
                }
            } else if(boton.getName().equals("NuevaTarifaBasica")){
                if(clientesTabla.getSelectedRow() > -1){
                    ventanaNuevaTarifaBasica.setVisible(true);
                    ventana.setEnabled(false);
                }else{
                    clienteNoSeleccionado();
                }
            } else if(boton.getName().equals("LlamadasCliente")){
                if(clientesTabla.getSelectedRow() > -1){
                    controlador.actualizarTablaLlamadas();
                    ventanaLlamadas.setVisible(true);
                    ventana.setEnabled(false);
                }else{
                    clienteNoSeleccionado();
                }
            } else if(boton.getName().equals("FacturasCliente")){
                if(clientesTabla.getSelectedRow() > -1){
                    controlador.actualizarListaFacturas();
                    ventanaFacturas.setVisible(true);
                    ventana.setEnabled(false);
                }else{
                    clienteNoSeleccionado();
                }
            } else if(boton.getName().equals("EmitirFactura")){
                if(clientesTabla.getSelectedRow() > -1){
                    int facnum =  controlador.emitirFactura();
                    if(facnum == -1){
                        JOptionPane.showMessageDialog(null, "No se ha emitido la factura porque no hay llamadas del cliente seleccionado");
                    }else{
                        JOptionPane.showMessageDialog(null, "Factura emitida con numero: " + facnum);
                    }

                }else{
                    clienteNoSeleccionado();
                }
            }

        }

        private void clienteNoSeleccionado(){
            JOptionPane.showMessageDialog(null, "Selecciona un cliente");
        }
    }

}
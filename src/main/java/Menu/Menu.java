package Menu;

import java.util.Calendar;

import EntradaSalida.Lectura;
import EntradaSalida.LecturaTeclado;
import EntradaSalida.Salida;
import EntradaSalida.SalidaConsola;
import Excepciones.NumeroNegativoException;
import Internal.Llamada;
import Modelo.Modelo;

public class Menu {
	
	private Lectura lectura;
	private Salida salida;
	//Metodo main
	
	public Menu(){
		FuncionesMenu.recuperaDatos();
		lectura = new LecturaTeclado();
		salida = new SalidaConsola();
	}
	
	public static void main(String[] args) throws NumeroNegativoException{
		//new Menu().ejecuta();
		Modelo modelo = new Modelo();
	}
	
	//Menu
	
	private void ejecuta() throws NumeroNegativoException{
		OpcionesMenu op;
		do {
			salida.imprimir(OpcionesMenu.getMenu() + "\n");
			op = OpcionesMenu.getOpcion(lectura.leerOpcion());
			int opcion = filtraOpcionMenu(op);
			switch(opcion){
				case 1:
					do{
						salida.imprimir(OpcionesMenuClientes.getMenu() + "\n");
						}while(!filtraOpcionClientes(OpcionesMenuClientes.getOpcion(lectura.leerOpcion())));
					break;
				case 2:
					do{
						salida.imprimir(OpcionesMenuLlamadas.getMenu() + "\n");
						}while(!filtraOpcionLlamadas(OpcionesMenuLlamadas.getOpcion(lectura.leerOpcion())));
					break;
				case 3:
					do{
						salida.imprimir(OpcionesMenuFacturas.getMenu() + "\n");
						}while(!filtraOpcionFacturas(OpcionesMenuFacturas.getOpcion(lectura.leerOpcion())));
					break;
			}
		} while (op != OpcionesMenu.SALIR);
	}
	
	
	private int filtraOpcionMenu(OpcionesMenu opcion){
		switch (opcion) {
		case EDITAR_CLIENTES:
			return 1;
		case EDITAR_LLAMADAS:
			return 2;
		case EDITAR_FACTURAS:
			return 3;
		case SALIR:
			FuncionesMenu.salir();
			break;
		}
		return 0;
	}
	
	private boolean filtraOpcionClientes(OpcionesMenuClientes opcion){
		switch (opcion) {
		case NUEVO_CLIENTE:
			if(lectura.esParticular()){
				FuncionesMenu.nuevoParticular(lectura.leerNIF(), lectura.leerNombre(), lectura.leerApellidos(), lectura.leerCorreoElectronico(), lectura.leerTarifa(), lectura.leerDireccion());
			}else{
				FuncionesMenu.nuevoEmpresa(lectura.leerNIF(), lectura.leerNombre(), lectura.leerCorreoElectronico(), lectura.leerTarifa(), lectura.leerDireccion());
			}
			break;
		case BORRAR_CLIENTE:
			FuncionesMenu.borrarCliente(lectura.leerNIF());
			break;
		case CAMBIAR_TARIFA:
			FuncionesMenu.cambiarTarifaCliente(lectura.leerNIF(),lectura.leerTarifa());
			break;
		case RECUPERAR_DATOS:
			salida.imprimir(FuncionesMenu.recuperarDatosCliente(lectura.leerNIF()));
			break;
		case RECUPERAR_LISTADO:
			salida.imprimir(FuncionesMenu.recuperarListadoCliente());
			break;
		case CLIENTES_ENTRE_DOS_FECHAS:
			salida.imprimir("Introduce la primera fecha: \n");
			Calendar fecha1 = lectura.leerFecha();
			salida.imprimir("Introduce la segunda fecha: \n");
			Calendar fecha2 = lectura.leerFecha();
			salida.imprimir(FuncionesMenu.clientesEntreDosFechas(fecha1,fecha2));
			break;
		case AÑADIR_TARIFA_EXTRA:
			if(lectura.esTarifaDia()){
				FuncionesMenu.añadirTarifaExtraDia(lectura.leerNIF(),lectura.leerDia(),lectura.leerTarifa());
			}else{
				salida.imprimir("Introduce el inicio de la franja:\n");
				int inicio = lectura.leerHora();
				salida.imprimir("Introduce el final de la franja:\n");
				int fin = lectura.leerHora();
				FuncionesMenu.añadirTarifaExtraFranja(lectura.leerNIF(), lectura.leerTarifa(), inicio, fin);
			}
			break;
		case VOLVER:
			return true;
		}
		return false;
	}
	
	private boolean filtraOpcionLlamadas(OpcionesMenuLlamadas opcion) throws NumeroNegativoException{
		switch (opcion) {
		case NUEVA_LLAMADA:
			FuncionesMenu.nuevaLlamada(lectura.leerNIF(), new Llamada(lectura.leerNumeroTelefono(), lectura.leerFecha(), lectura.leerDuracion()));
			break;
		case LISTAR_LLAMADAS:
			salida.imprimir(FuncionesMenu.listarLlamadas(lectura.leerNIF()));
			break;
		case LLAMADAS_ENTRE_DOS_FECHAS:
			salida.imprimir("Introduce la primera fecha: \n");
			Calendar fecha1 = lectura.leerFecha();
			salida.imprimir("Introduce la segunda fecha: \n");
			Calendar fecha2 = lectura.leerFecha();
			salida.imprimir(FuncionesMenu.llamadasEntreDosFechas(lectura.leerNIF(), fecha1, fecha2));
			break;
		case VOLVER:
			return true;
		}
		return false;
	}

	private boolean filtraOpcionFacturas(OpcionesMenuFacturas opcion){
		switch (opcion) {
		case EMITIR_FACTURA:
			int cod = FuncionesMenu.nuevaFactura(lectura.leerNIF());
			salida.imprimir("Se ha añadido la factura con codigo " + cod + "\n");
			break;
		case RECUPERAR_FACTURA:
			salida.imprimir(FuncionesMenu.recuperarFactura(lectura.leerCodigoFactura()) + "\n");
			break;
		case RECUPERAR_FACTURAS_CLIENTE:
			salida.imprimir(FuncionesMenu.recuperarFacturasCliente(lectura.leerNIF()));
			break;
		case FACTURAS_ENTRE_DOS_FECHAS:
			salida.imprimir("Introduce la primera fecha: \n");
			Calendar fecha1 = lectura.leerFecha();
			salida.imprimir("Introduce la segunda fecha: \n");
			Calendar fecha2 = lectura.leerFecha();
			salida.imprimir(FuncionesMenu.facturasEntreDosFechas(lectura.leerNIF(), fecha1, fecha2));
			break;
		case VOLVER:
			return true;
		}
		return false;
	}
	
}

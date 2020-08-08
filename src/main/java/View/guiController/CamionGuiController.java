package View.guiController;

import Model.Camion;
import View.gui.camiones.AltaCamionesPanel;
import View.gui.camiones.BusquedaCamionesPanel;
import View.gui.camiones.ModificacionCamionesPopUp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CamionGuiController {
	
	private static CamionGuiController controller;
	private Camion nuevoCamion;
	private List<Camion> listaCamionesActual;

	//TODO Agregar Service
	//private Controller.CamionController service;


	//Constructor privado
	private CamionGuiController(){

		this.listaCamionesActual = new ArrayList<Camion>();
		this.nuevoCamion = new Camion();
		//TODO Inicializar Service
		//this.service = new CamionService();
	}

	//Retorna una instancia de CamionController. Evita las multiples instancias.
	public static CamionGuiController getCamionController(){

		if(controller==null){

			controller = new CamionGuiController();

		}

		return controller;

	}



	//ALTA DE CAMIONES:

	//Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
	public Camion guardar(AltaCamionesPanel panel) throws Exception{

		this.nuevoCamion=new Camion();
		//Validación de datos
		this.validarDatos(panel);
		//TODO Llamar al service
		//camionService.crearCamion(c);
		//this.listaCamionesActual.clear();
		//this.listaCamionesActual.addAll(camionService.buscarTodos());

		//this.nuevoCamion.setId(11);
		this.listaCamionesActual.add(nuevoCamion);

		return null;
	}

	//Actualiza tabla si se da de alta un camión
	private void validarDatos(AltaCamionesPanel panel) throws Exception {
		ArrayList<Integer> camposVacios = new ArrayList<Integer>();
		Boolean[] camposValidos = {false,false,false,false,false,false,false};

		try {
			if(panel.getTxtPatente()!=null && !panel.getTxtPatente().getText().equals("")) {
				nuevoCamion.setPatente(panel.getTxtPatente().getText());
				camposValidos[0] = true;

			} else {
				//throw new Exception("Error en el campo: Patente. El dato debe ser numerico. Campo Obligatorio");
				camposVacios.add(0);
			}
			if(panel.getTxtModelo()!=null && !panel.getTxtModelo().getText().equals("")) {
				nuevoCamion.setModelo(panel.getTxtModelo().getText());
				camposValidos[1] = true;
			}else{
				camposVacios.add(1);
			}
			if(panel.getTxtMarca()!=null && !panel.getTxtMarca().getText().equals("")){
				nuevoCamion.setMarca(panel.getTxtMarca().getText());
				camposValidos[2] = true;
			}else{
				camposVacios.add(2);
			}
			if(panel.getTxtKm()!=null && !panel.getTxtKm().getText().equals("")) {
				nuevoCamion.setKmRecorridos(Float.valueOf(panel.getTxtKm().getText()));
				camposValidos[3] = true;
			}else{
				camposVacios.add(3);
			}

			if(panel.getTxtFechaCompra()!=null && !panel.getTxtFechaCompra().getText().equals("")) {

				LocalDate fecha = LocalDate.parse(panel.getTxtFechaCompra().getText(), panel.getDf());
				nuevoCamion.setFechaCompra(fecha);
				camposValidos[4] = true;

			}else{
				camposVacios.add(4);
			}
			if(panel.getTxtCostoKm()!=null && !panel.getTxtCostoKm().getText().equals("")) {
				nuevoCamion.setCostoKm(Float.valueOf(panel.getTxtCostoKm().getText()));
				camposValidos[5] = true;
			}else{
				camposVacios.add(5);
			}

			if(panel.getTxtCostoHs()!=null && !panel.getTxtCostoHs().getText().equals("")) {
				nuevoCamion.setCostoHora(Float.valueOf(panel.getTxtCostoHs().getText()));
				camposValidos[6] = true;
			}else{
				camposVacios.add(6);
			}

			if(camposVacios.size()>0){

				panel.mostrarErrores(camposVacios);
				throw new Exception();

			}

		} catch(NumberFormatException nfe) {

			panel.mostrarErrores(camposValidos);
			throw new Exception("Uno de los campos no cumple el formato esperado.");

		}catch(Exception e){
			throw new Exception("Hubo un error al procesar los Datos");

		}
	}



	//BUSQUEDA:
	private void validarDatos(BusquedaCamionesPanel panel) throws Exception{

		//Esto es para verificar que el tipo de dato sea el correcto.
		//Si no ocurre una excepción, todos los datos son del tipo correcto. Sino, quiere decir que hubo un campo no valido
		Boolean[] camposValidos = {false,false,false,false,false,false,false};

		try {
			//TODO Ver como el service va a recibir los atributos.
			if(panel.getTxtPatente()!=null && !panel.getTxtPatente().getText().equals("")) {
				nuevoCamion.setPatente(panel.getTxtPatente().getText());
			}
			camposValidos[0] = true;

			if(panel.getTxtModelo()!=null && !panel.getTxtModelo().getText().equals("")) {
				nuevoCamion.setModelo(panel.getTxtModelo().getText());
			}
			camposValidos[1] = true;

			if(panel.getTxtMarca()!=null && !panel.getTxtMarca().getText().equals("")){
				nuevoCamion.setMarca(panel.getTxtMarca().getText());
			}
			camposValidos[2] = true;

			if(panel.getTxtKm()!=null && !panel.getTxtKm().getText().equals("")) {
				nuevoCamion.setKmRecorridos(Float.valueOf(panel.getTxtKm().getText()));
			}

			camposValidos[3] = true;

			if(panel.getTxtFechaCompra()!=null && !panel.getTxtFechaCompra().getText().equals("")) {

				LocalDate fecha = LocalDate.parse(panel.getTxtFechaCompra().getText(), panel.getDf());
				nuevoCamion.setFechaCompra(fecha);

			}
			camposValidos[4] = true;

			if(panel.getTxtCostoKm()!=null && !panel.getTxtCostoKm().getText().equals("")) {
				nuevoCamion.setCostoKm(Float.valueOf(panel.getTxtCostoKm().getText()));
			}
			camposValidos[5] = true;

			if(panel.getTxtCostoHs()!=null && !panel.getTxtCostoHs().getText().equals("")) {
				nuevoCamion.setCostoHora(Float.valueOf(panel.getTxtCostoHs().getText()));
			}
			camposValidos[6] = true;

		} catch(NumberFormatException nfe) {

			panel.mostrarErrores(camposValidos);
			throw new Exception("Uno de los campos no cumple el formato esperado.");

		}catch(Exception e){
			panel.mostrarErrores(camposValidos);
			throw new Exception("Hubo un error al procesar los datos");

		}
	}

	public void buscar(BusquedaCamionesPanel panel) throws Exception{

		//Validación de datos
		this.validarDatos(panel);

		//TODO Llamar al service con los argumentos de búsqueda
		//this.listaCamionesActual.clear();
		//this.listaCamionesActual.addAll(camionService.buscarCamion(filstros));

		//TODO Eliminar esto.
			Camion ejemplo = new Camion();
			ejemplo.setId(11);
			ejemplo.setCostoKm(12.0F);
			ejemplo.setCostoHora(30.0F);
			ejemplo.setKmRecorridos(40000F);
			ejemplo.setFechaCompra(LocalDate.now());
			ejemplo.setMarca("Marca ejemplo");
			ejemplo.setModelo("Modelo ejemplo");
			ejemplo.setPatente("xxx-xxx-x");

		this.listaCamionesActual.clear();
		this.listaCamionesActual.add(ejemplo);

	}



	//MODIFICACIÓN:
	//Validación de datos:
	private void validarDatos(ModificacionCamionesPopUp panel) throws Exception {

		ArrayList<Integer> camposVacios = new ArrayList<Integer>();

		try {
			if(panel.getTxtPatente()!=null && !panel.getTxtPatente().getText().equals("")) {
				this.nuevoCamion.setPatente(panel.getTxtPatente().getText());
				System.out.println(nuevoCamion.getPatente());

			} else {
				//throw new Exception("Error en el campo: Patente. El dato debe ser numerico. Campo Obligatorio");
				camposVacios.add(0);
			}
			if(panel.getTxtModelo()!=null && !panel.getTxtModelo().getText().equals("")) {
				this.nuevoCamion.setModelo(panel.getTxtModelo().getText());
				System.out.println(nuevoCamion.getModelo());

			}else{
				camposVacios.add(1);
			}
			if(panel.getTxtMarca()!=null && !panel.getTxtMarca().getText().equals("")){
				this.nuevoCamion.setMarca(panel.getTxtMarca().getText());
				System.out.println(nuevoCamion.getMarca());

			}else{
				camposVacios.add(2);
			}
			if(panel.getTxtKm()!=null && !panel.getTxtKm().getText().equals("")) {
				this.nuevoCamion.setKmRecorridos(Float.valueOf(panel.getTxtKm().getText()));
				System.out.println(nuevoCamion.getKmRecorridos());
			}else{
				camposVacios.add(3);
			}

			if(panel.getTxtFechaCompra()!=null && !panel.getTxtFechaCompra().getText().equals("")) {

				LocalDate fecha = LocalDate.parse(panel.getTxtFechaCompra().getText(), panel.getDf());
				this.nuevoCamion.setFechaCompra(fecha);
				System.out.println(nuevoCamion.getFechaCompra());

			}else{
				camposVacios.add(4);
			}
			if(panel.getTxtCostoKm()!=null && !panel.getTxtCostoKm().getText().equals("")) {
				this.nuevoCamion.setCostoKm(Float.valueOf(panel.getTxtCostoKm().getText()));
				System.out.println(nuevoCamion.getCostoKm());
			}else{
				camposVacios.add(5);
			}

			if(panel.getTxtCostoHs()!=null && !panel.getTxtCostoHs().getText().equals("")) {
				this.nuevoCamion.setCostoHora(Float.valueOf(panel.getTxtCostoHs().getText()));
				System.out.println(nuevoCamion.getCostoHora());
			}else{
				camposVacios.add(6);
			}

			for(Integer num: camposVacios) System.out.print(camposVacios);
			if(camposVacios.size()>0){

				System.out.println("Entra al if de camposVacios");
				panel.mostrarErrores(camposVacios);
				throw new Exception();

			}

		} catch(NumberFormatException nfe) {
			throw new Exception("Uno de los campos no cumple el formato esperado.");

		}catch (DateTimeParseException df){

			camposVacios.add(4);
			panel.mostrarErrores(camposVacios);
			throw new Exception("Hubo un error al procesar los Datos");

		} catch(Exception e){

			System.out.println("Llama a mostrar Errores");
			panel.mostrarErrores(camposVacios);
			throw new Exception("Hubo un error al procesar los Datos");

		}

	}

	//Metodo que llama a validar datos y llama al service para almacenar la modificación
	public void modificar(ModificacionCamionesPopUp panel, int elemento) throws Exception{

		//Ya tendría un ID
		this.nuevoCamion = this.listaCamionesActual.get(elemento);
		System.out.println(this.nuevoCamion.getPatente());

		validarDatos(panel);

		//TODO Invocar al service con camión modificado
		//camionService.crearCamion(camion);
		//this.listaCamionesActual.clear();
		//this.lista.addAll(camionService.buscarTodos());

	}

	//Método para mostrar los valores iniciales del elemento a modificar
	public void inicializarPanelModificacion(ModificacionCamionesPopUp panel, int elemento) {

		Camion cam = this.listaCamionesActual.get(elemento);

		panel.setTxtCostoHs(cam.getCostoHora().toString());
		panel.setTxtCostoKm(cam.getCostoKm().toString());
		panel.setTxtFechaCompra(cam.getFechaCompra().format(panel.getDf()));
		panel.setTxtKm(cam.getKmRecorridos().toString());
		panel.setTxtMarca(cam.getMarca().toString());
		panel.setTxtModelo(cam.getModelo().toString());
		panel.setTxtPatente(cam.getPatente().toString());
		panel.setFila(elemento);

	}


	//ELIMINACIÓN:
	public void eliminar(Integer elemento){

		Camion cam = this.listaCamionesActual.get(elemento);
		this.listaCamionesActual.remove(cam);

		//TODO Invocar a Service con elemento seleccionado
		//this.listaCamionesActual.clear();
		//this.service.buscarTodos();


	}

	public void eliminar(int[] elementos){

		int i= elementos.length-1;

		while(i>=0){
			this.listaCamionesActual.remove(elementos[i]);
			i--;
		}


		//TODO Invocar a Service con elemento seleccionado
		//this.listaCamionesActual.clear();
		//this.service.buscarTodos();

	}

	//Genéricos:
	public void restaurarTabla(){

		this.listaCamionesActual = this.listarTodos();

	}

	//Retorna la lista con todos los camiones.
	public List<Camion> listarTodos(){

		System.out.println(this.listaCamionesActual);
		this.listaCamionesActual.clear();
		//TODO Llamar al service que obtiene la lista de todos los camiones
		//this.listaCamionesActual.addAll(service.buscarTodos());
		return this.listaCamionesActual;

	}


}

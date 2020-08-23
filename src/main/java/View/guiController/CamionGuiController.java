package View.guiController;

import Service.CamionService;
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

	private CamionService service;


	//Constructor privado
	private CamionGuiController(){

		this.listaCamionesActual = new ArrayList<>();
		this.nuevoCamion = new Camion();
		this.service = new CamionService();

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

		service.altaCamion(this.nuevoCamion.getPatente(), this.nuevoCamion.getMarca(), this.nuevoCamion.getModelo(), this.nuevoCamion.getKmRecorridos()
		, this.nuevoCamion.getCostoKm(), this.nuevoCamion.getCostoHora(), this.nuevoCamion.getFechaCompra());
		this.listaCamionesActual.clear();
		this.listaCamionesActual.addAll(service.getListaCamion());

		//this.listaCamionesActual.add(nuevoCamion);

		return null;
	}

	//Actualiza tabla si se da de alta un camión
	private void validarDatos(AltaCamionesPanel panel) throws Exception {
		ArrayList<Integer> camposVacios = new ArrayList<>();
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
		this.nuevoCamion = new Camion();

		try {

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

		this.listaCamionesActual.clear();
		this.listaCamionesActual.addAll(service.buscarCamiones(this.nuevoCamion));


	}



	//MODIFICACIÓN:
	//Validación de datos:
	private void validarDatos(ModificacionCamionesPopUp panel) throws Exception {

		ArrayList<Integer> camposVacios = new ArrayList<>();

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

		validarDatos(panel);

		service.modificarCamion(this.nuevoCamion);
		this.listaCamionesActual.clear();
		this.listaCamionesActual.addAll(service.getListaCamion());

	}

	//Método para mostrar los valores iniciales del elemento a modificar
	public void inicializarPanelModificacion(ModificacionCamionesPopUp panel, int elemento) {

		Camion cam = this.listaCamionesActual.get(elemento);

		panel.setTxtCostoHs(cam.getCostoHora().toString());
		panel.setTxtCostoKm(cam.getCostoKm().toString());
		panel.setTxtFechaCompra(cam.getFechaCompra().format(panel.getDf()));
		panel.setTxtKm(cam.getKmRecorridos().toString());
		panel.setTxtMarca(cam.getMarca());
		panel.setTxtModelo(cam.getModelo());
		panel.setTxtPatente(cam.getPatente());
		panel.setFila(elemento);

	}


	//ELIMINACIÓN:
	public void eliminar(int[] elementos){

		int i= elementos.length-1;

		while(i>=0){
			//Llama a service para eliminar el camión
			this.service.bajaCamion(this.listaCamionesActual.get(elementos[i]));

			//Lo elimina de la lista actual.
			this.listaCamionesActual.remove(elementos[i]);
			i--;
		}

		//Por las dudas, actualiza la lista de elementos
		this.listaCamionesActual.clear();

		try {
			this.listaCamionesActual.addAll(this.service.getListaCamion());
		} catch (Exception e) {
			this.listaCamionesActual = new ArrayList<>();
			e.printStackTrace();
		}


	}

	//Genéricos:

	public void restaurarTabla(){

		this.listaCamionesActual = this.listarTodos();

	}


	//Retorna la lista con todos los camiones.
	public List<Camion> listarTodos(){

		this.listaCamionesActual.clear();
		try {
			this.listaCamionesActual.addAll(service.getListaCamion());
		} catch (Exception e) {
			this.listaCamionesActual = new ArrayList<>();
			e.printStackTrace();
			return this.listaCamionesActual;

		}
		return this.listaCamionesActual;

	}


}

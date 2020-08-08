package View.controller;

import Model.Planta;
import Model.Ruta;
import View.gui.ordenes.AgregarOrdenPanel;
import View.gui.planta.AgregarPlantaPanel;
import View.gui.planta.FlujoMaximoPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlantaController {

    public static PlantaController controller;
    private List<Planta> listaPlantasActual;
    private Map<Planta, Integer> pageRank;
    private Integer[][] matrizCaminos;
    private Planta nuevaPlantaOrigen;
    private Planta nuevaPlantaDestino;
    private Ruta nuevaRuta;

    //TODO Agregar service

    //Constructor privado
    private PlantaController(){

        this.listaPlantasActual = new ArrayList<Planta>();
        this.nuevaPlantaOrigen = new Planta();
        this.nuevaPlantaDestino = new Planta();
        this.pageRank = new HashMap<Planta, Integer>();
        //TODO Agregar service
        //this.service = new CamionService();
    }

    //Retorna una instancia de CamionService. Evita las multiples instancias.
    public static PlantaController getPlantaController(){

        if(controller==null){

            controller = new PlantaController();

        }

        return controller;

    }

    //ALTA DE PLANTAS:
    //ALTA DE INSUMO:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public void guardar(AgregarPlantaPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        //TODO Llamar al service para almacenar el Insumo
        //camionService.crearCamion(c);
        //this.listaCamionesActual.clear();
        //this.listaCamionesActual.addAll(camionService.buscarTodos());

        //TODO Eliminar estas lineas
        this.listaPlantasActual.add(this.nuevaPlantaOrigen);


    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(AgregarPlantaPanel panel) throws Exception {
        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false};

        try {
            if(panel.getTxtPlanta()!=null && !panel.getTxtPlanta().getText().equals("")) {
                this.nuevaPlantaOrigen.setNombre(panel.getTxtPlanta().getText());
                camposValidos[0] = true;
            } else {
                camposVacios.add(0);
            }

            if(panel.getTxtCaminos()!=null) {

                //TODO Invocar al service para obtener las matrices
                calcularMatriz(panel.getTxtCaminos().getSelectedIndex());

            } else{
                camposVacios.add(2);
            }

            if(camposVacios.size()>0){
                panel.mostrarErrores(camposVacios);
                throw new Exception();

            }

            //this.pageRank = new HashMap<Planta, Integer>(); //TODO Llamar al service para obtener con la nueva planta, los page rank

        } catch(NumberFormatException nfe) {

            panel.mostrarErrores(camposValidos);
            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){
            throw new Exception("Hubo un error al procesar los Datos");
        }
    }

    //MATRIZ:
    public void calcularMatriz(Integer i){

        switch (i){
            case 0: //Por hora
                this.matrizCaminos = null;
                break;

            case 1:
                this.matrizCaminos = null;
                break;
        }

    }

    //FLUJO MÁXIMO:
    //Calculo flujo maximo
    public void calcularFlujoMax(FlujoMaximoPanel panel) throws Exception{

         validarDatos(panel);;

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(FlujoMaximoPanel panel) throws Exception {
        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        try {

            if(panel.getTxtOrigen()!=null) {

                this.nuevaPlantaOrigen = this.listaPlantasActual.get(panel.getTxtOrigen().getSelectedIndex());

            } else{
                camposVacios.add(2);
            }

            if(panel.getTxtDestino()!=null) {

                this.nuevaPlantaDestino = this.listaPlantasActual.get(panel.getTxtDestino().getSelectedIndex());

            } else{
                camposVacios.add(2);
            }

            if(camposVacios.size()>0){
                throw new Exception();
            }

            //return service.calcularFlujoMaximo(plantaOrigen, plantaDestino)
            panel.getTxtValorFlujo().setText("2.0"); //TODO Invocar al service para obtener el valor

        } catch(NumberFormatException nfe) {

            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){

            throw new Exception("Hubo un error al procesar los Datos");
        }
    }

    //AGREGAR RUTA:
    //Agregar ruta:
    public void agregarRuta(FlujoMaximoPanel panel) throws Exception{

        validarDatosRuta(panel);
        this.nuevaRuta.setPlantaOrigen(this.nuevaPlantaOrigen);
        this.nuevaRuta.setPlantaOrigen(this.nuevaPlantaDestino);

        //TODO Invocar al service para almacenar la nueva ruta.

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatosRuta(FlujoMaximoPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false, false, false};
        try {

            if(panel.getTxtOrigen()!=null) {

                this.nuevaPlantaOrigen = this.listaPlantasActual.get(panel.getTxtOrigen().getSelectedIndex());

            } else{
                camposVacios.add(4);
            }

            if(panel.getTxtDestino()!=null) {

                this.nuevaPlantaDestino = this.listaPlantasActual.get(panel.getTxtDestino().getSelectedIndex());

            } else{
                camposVacios.add(4);
            }


            if(panel.getTxtDistancia()!=null && !panel.getTxtDistancia().getText().equals("")) {
                this.nuevaRuta.setDistanciaKm(Float.valueOf(panel.getTxtDistancia().getText()));
                camposValidos[0] = true;

            } else {
                camposVacios.add(0);
            }

            if(panel.getTxtHoras()!=null && !panel.getTxtHoras().getText().equals("")) {
                this.nuevaRuta.setDuracionHora(Float.valueOf(panel.getTxtHoras().getText()));
                camposValidos[1] = true;

            } else {
                camposVacios.add(1);
            }

            if(panel.getTxtPeso()!=null && !panel.getTxtPeso().getText().equals("")) {
                this.nuevaRuta.setPesoMaximo(Float.valueOf(panel.getTxtPeso().getText()));
                camposValidos[2] = true;
            } else {
                camposVacios.add(2);
            }


            if(camposVacios.size()>0){
                panel.mostrarErrores(camposVacios);
                throw new Exception();
            }

            //return service.calcularFlujoMaximo(plantaOrigen, plantaDestino)
            panel.getTxtValorFlujo().setText("2.0"); //TODO Invocar al service para obtener el valor

        } catch(NumberFormatException nfe) {
            panel.mostrarErrores(camposValidos);
            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){

            e.printStackTrace();
            if(camposVacios.contains(4)){

                throw new Exception("Hubo un error al procesar los Datos: No ha sido seleccionada una planta");

            }
            throw new Exception("Hubo un error al procesar los Datos: ");
        }
    }


    //Obtener plantas
    public String[] getPlantas(){

        ArrayList<String> plantas = new ArrayList<String>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        return plantas.toArray(new String[0]);
    }

    public List<Planta> getListaPlantas(){

        //return this.listaPlantasActual;
        return new ArrayList<>();
    }
    //Obtener Page Rank
    public Map<Planta, Integer> getPageRank(){
        //TODO Llamar al service y obtener un hashmap (puede ser otra lista)
        //return this.pageRank;
        return new HashMap<Planta, Integer>();
    }
}

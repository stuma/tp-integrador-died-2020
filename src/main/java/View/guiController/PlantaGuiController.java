package View.guiController;

import Service.ElementoNoEncontradoException;
import Service.GrafoService;
import Service.PlantaService;
import Model.Planta;
import Model.Ruta;
import View.gui.planta.AgregarPlantaPanel;
import View.gui.planta.FlujoMaximoPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlantaGuiController {

    public static PlantaGuiController controller;
    private List<Planta> listaPlantasActual;
    //private List<Planta> listaPlantasOrdenadas;
    private Map<Planta, Double> pageRank;
    private Float[][] matrizCaminos;
    private Planta nuevaPlantaOrigen;
    private Planta nuevaPlantaDestino;
    private Ruta nuevaRuta;
    private PlantaService servicePlanta;
    private GrafoService serviceGrafo;

    //Constructor privado
    private PlantaGuiController(){
        super();
        this.nuevaRuta = new Ruta();
        this.serviceGrafo = GrafoService.getGrafoService();

        this.servicePlanta = new PlantaService();

        try {
            this.listaPlantasActual = this.servicePlanta.getListaPlantas();
        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }
        this.nuevaPlantaOrigen = new Planta();
        this.nuevaPlantaDestino = new Planta();

        this.pageRank = this.serviceGrafo.calcularPageRank(0.5);
        this.matrizCaminos = new Float[this.listaPlantasActual.size()][this.listaPlantasActual.size()];

    }

    //Retorna una instancia de CamionController. Evita las multiples instancias.
    public static PlantaGuiController getPlantaController(){

        if(controller==null){

            controller = new PlantaGuiController();

        }

        return controller;

    }

    //ALTA DE PLANTAS:
    //ALTA DE INSUMO:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public void guardar(AgregarPlantaPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        this.serviceGrafo.agregarPlanta(this.nuevaPlantaOrigen.getNombre());
        this.listaPlantasActual.clear();
        this.listaPlantasActual.addAll(this.servicePlanta.getListaPlantas());


        //this.listaPlantasActual.add(this.nuevaPlantaOrigen);


    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(AgregarPlantaPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<>();
        Boolean[] camposValidos = {false};

        try {
            if(panel.getTxtPlanta()!=null && !panel.getTxtPlanta().getText().equals("")) {
                this.nuevaPlantaOrigen.setNombre(panel.getTxtPlanta().getText());
                camposValidos[0] = true;
            } else {
                camposVacios.add(0);
            }

            if(panel.getTxtCaminos()!=null) {

                calcularMatriz(panel.getTxtCaminos().getSelectedIndex());

            } else{
                camposVacios.add(2);
            }

            if(camposVacios.size()>0){
                panel.mostrarErrores(camposVacios);
                throw new Exception();

            }

            this.pageRank =this.serviceGrafo.calcularPageRank(0.5);

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
                this.matrizCaminos = this.serviceGrafo.matrizCaminoMinimoHs();
                break;

            case 1: //Por km
                this.matrizCaminos = this.serviceGrafo.matrizCaminoMinimoKm();
                break;
        }

    }


    //FLUJO MÁXIMO:
    //Calculo flujo maximo
    public void calcularFlujoMax(FlujoMaximoPanel panel) throws Exception{

         validarDatos(panel);

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(FlujoMaximoPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<>();
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
                System.out.println(camposVacios);
                throw new Exception();
            }

            //return service.calcularFlujoMaximo(plantaOrigen, plantaDestino)
            panel.getTxtValorFlujo().setText(this.serviceGrafo.calcularFlujoMaximo(this.nuevaPlantaOrigen, this.nuevaPlantaDestino).toString());


        } catch(NumberFormatException nfe) {

            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Hubo un error al procesar los Datos");
        }
    }

    //AGREGAR RUTA:
    //Agregar ruta:
    public void agregarRuta(FlujoMaximoPanel panel) throws Exception{

        validarDatosRuta(panel);
        this.nuevaRuta.setPlantaOrigen(this.nuevaPlantaOrigen);
        this.nuevaRuta.setPlantaOrigen(this.nuevaPlantaDestino);

        this.serviceGrafo.conectarPlanta(this.nuevaPlantaOrigen.getNombre(), this.nuevaPlantaDestino.getNombre(),
               this.nuevaRuta.getDistanciaKm(), this.nuevaRuta.getDuracionHora(), this.nuevaRuta.getPesoMaximo());

    }

    private void validarDatosRuta(FlujoMaximoPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<>();
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

            //No se si se calcula
            //panel.getTxtValorFlujo().setText(this.serviceGrafo.calcularFlujoMaximo(this.nuevaPlantaOrigen, this.nuevaPlantaDestino).toString());


        } catch(NumberFormatException nfe) {
            panel.mostrarErrores(camposValidos);
            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){

            if(camposVacios.contains(4)){

                throw new Exception("Hubo un error al procesar los Datos: No ha sido seleccionada una planta");

            }
            throw new Exception("Hubo un error al procesar los Datos: ");
        }
    }


    //Obtener plantas
    public String[] getPlantas(){

        ArrayList<String> plantas = new ArrayList<>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        return plantas.toArray(new String[0]);
    }

    public List<Planta> getListaPlantas(){

        return this.listaPlantasActual;
       // return new ArrayList<>();
    }

    //Obtener Page Rank
    public Map<Planta, Double> getPageRank(){

        this.pageRank = this.serviceGrafo.calcularPageRank(0.5);
        System.out.println("PlantaGuiController");
        for (Map.Entry<Planta, Double> en : this.pageRank.entrySet()){
            System.out.println(en.getKey());
            System.out.println(en.getValue());
        }
        return this.pageRank;

    }



    public Float[][] getMatrizCaminos() {

        return matrizCaminos;

/*        Double[][] matriz = {{0D,0D,0D,0D}, {0D,1D,1D,1D}, {0D,1D,1D,1D}, {0D,1D,1D,1D}};
        return matriz;*/

    }
}

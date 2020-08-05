package View.controller;

import Model.Planta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlantaController {

    public static PlantaController controller;
    private List<Planta> listaPlantasActual;
    private Map<Planta, Integer> pageRank;
    private Planta nuevaPlanta;


    //TODO Agregar service

    //Constructor privado
    private PlantaController(){

        this.listaPlantasActual = new ArrayList<Planta>();
        this.nuevaPlanta = new Planta();
        //TODO Agregar service
        //this.service = new CamionService();
    }

    //Retorna una instancia de CamionController. Evita las multiples instancias.
    public static PlantaController getPlantaController(){

        if(controller==null){

            controller = new PlantaController();

        }

        return controller;

    }




    //Obtener plantas
    public String[] getPlantas(){

        ArrayList<String> plantas = new ArrayList<String>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        plantas.add(0, "Todas las plantas");
        return plantas.toArray(new String[0]);
    }
}

package Controller;

import DAO.DAOgrafo;
import Model.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GrafoController {
    Grafo grafo = new Grafo();


public void gfInit() throws ElementoNoEncontradoException {
    //Cracion de plantas
    this.agregarPlanta( "1");
    this.agregarPlanta("2");
    this.agregarPlanta("3");
    this.agregarPlanta("4");
    this.agregarPlanta("5");
    this.agregarPlanta("Final");
    this.agregarPlanta("Puerto");

    //Creacion rutas

    this.conectarPlanta("Puerto","1",(float)56,(float)3,(float)10);
    this.conectarPlanta("Puerto","2",(float)56,(float)3,(float)15);

    this.conectarPlanta("2","5",(float)56,(float)3,(float)60);
    this.conectarPlanta("2","3",(float)56,(float)3,(float)39);

    this.conectarPlanta("1","4",(float)56,(float)3,(float)200);
    this.conectarPlanta("3","4",(float)56,(float)3,(float)5);

    this.conectarPlanta("4","5",(float)56,(float)3,(float)300);
    this.conectarPlanta("4","Final",(float)56,(float)3,(float)50);

    this.conectarPlanta("5","Final",(float)56,(float)3,(float)45);
    DAOgrafo.crearGrafo(grafo);
    this.listarGrafo();
}


    public void agregarPlanta(String nombre){

        Planta nuevaPlanta =new Planta(nombre);
        grafo.addPlanta(nuevaPlanta);
        DAOgrafo.addPlanta(nuevaPlanta);
    }

    public void conectarPlanta(Planta plantaOrigen, Planta plantaDestino, Float distanciaKm, Float duracionHora, Float pesoMaximo){

        Ruta nuevaRuta= new Ruta( plantaOrigen,  plantaDestino,  distanciaKm,  duracionHora,  pesoMaximo);
        grafo.addRuta(nuevaRuta);

    }

    public void conectarPlanta(String plantaOrigenName, String plantaDestinoName, Float distanciaKm, Float duracionHora, Float pesoMaximo) throws ElementoNoEncontradoException {

    try {
        Planta origen = grafo.getPlantas().stream().
                filter(t -> t.getNombre().equals(plantaOrigenName)).
                findFirst().orElseThrow();
        //get();

        Planta destino = grafo.getPlantas().stream().
                filter(t -> t.getNombre().equals(plantaDestinoName)).
                findFirst().orElseThrow();
        //   get();

        Ruta nuevaRuta = new Ruta(origen, destino, distanciaKm, duracionHora, pesoMaximo);
        grafo.addRuta(nuevaRuta);

    }
    catch (Exception e){throw new ElementoNoEncontradoException(" no existe esa planta "+ e.getMessage());
    }
    }

    public ArrayList<Planta> getAdyacentes(Planta planta){
        planta.getAdyacente();


        return null;
    }

    public void listarGrafo(){
        grafo.getPlantas().stream().forEach(t-> System.out.println(t.getNombre()+" Ruta entrada: " +t.getRutaEntrada().stream().map(p->p.getPesoMaximo()).collect(Collectors.toList()) +
                                                                                " Ruta Salida: "+t.getRutaSalida()));

    }

    public List<Planta> caminoMinimoKm(Planta origen, Planta destino){
    List<Planta> caminoInicial = new LinkedList<Planta>();
    caminoInicial.add(origen);
    return this.caminoMinimokmAux(origen,destino,caminoInicial,0);

        // TODO deberiamos crear una lista de camino posibles sin descartar ninguno, para lugo ordenarlos con stream(.sort(t1,t2)t1.compareTo(t2)) por el valor que quisieramos y asi deolver el mas optimo


    }
    public List<Planta> caminoMinimokmAux(Planta plantaOrigen, Planta plantaDestino, List<Planta> caminoTemp, float kmAcumulados){
    List<Planta> adyacentesOrigen = this.getAdyacentes(plantaOrigen);
        for (Planta unaPlanta : adyacentesOrigen) {
            if(unaPlanta.equals(plantaDestino)){
                kmAcumulados+= this.distanciakm(plantaOrigen,unaPlanta);
                caminoTemp.add(unaPlanta);
                return caminoTemp;
             }
            else{//que la ciudad sea parte del camino
                caminoTemp.add(unaPlanta);
                kmAcumulados+= this.distanciakm(plantaOrigen,unaPlanta);
                List<Planta> resultado= caminoMinimokmAux(unaPlanta,plantaDestino,caminoTemp,kmAcumulados);
                if(resultado== null) caminoTemp.remove(unaPlanta);
                else return resultado;
            }

        }
        return null; //TODO resultado correcto
    }

    

    public List<Planta> caminoMinimoHora(Planta origen, Planta destino){
        List<Planta> caminoInicial = new LinkedList<Planta>();
        caminoInicial.add(origen);
        return this.caminoMinimoHoraAux(origen,destino,caminoInicial,0);
    }
    public List<Planta> caminoMinimoHoraAux(Planta plantaOrigen, Planta plantaDestino, List<Planta> caminoTemp, float horaAcumulada){
        List<Planta> adyacentesOrigen = this.getAdyacentes(plantaOrigen);
        for (Planta unaPlanta : adyacentesOrigen) {
            if(unaPlanta.equals(plantaDestino)){
                horaAcumulada+= this.distanciaHora(plantaOrigen,unaPlanta);
                caminoTemp.add(unaPlanta);
                return caminoTemp;
            }
            else{//que la ciudad sea parte del camino
                caminoTemp.add(unaPlanta);
                horaAcumulada+= this.distanciaHora(plantaOrigen,unaPlanta);
                List<Planta> resultado= caminoMinimoHoraAux(unaPlanta,plantaDestino,caminoTemp,horaAcumulada);
                if(resultado== null) caminoTemp.remove(unaPlanta);
                else return resultado;
            }

        }
        return null; //TODO resultado correcto
    }

    public ArrayList<Planta> flujoMaximoPlantas(Planta origen, Planta destino, Float peso){
        return null;
    }

    public Map<Planta, Integer> pageRank(){
        return null;
    }

    public float distanciakm(Planta p1, Planta p2){

        return  (p1.getRutaSalida().stream().filter(t->t.getPlantaDestino().equals(p2)).findFirst().get()).getDistanciaKm();
    }
    public float distanciaHora(Planta p1, Planta p2){
        return  (p1.getRutaSalida().stream().filter(t->t.getPlantaDestino().equals(p2)).findFirst().get()).getDuracionHora();
    }


    public Integer stockTotal(Insumo insumo){
        Integer sumaAux =0;

        for (Planta unaPlanta: grafo.getPlantas()) {
             sumaAux+=   unaPlanta.getListaStockInsumos().stream().
                                                    filter(t->t.getInsumo().equals(insumo)).
                                                    mapToInt(t->t.getCantidad()).
                                                    sum();
        }
        return sumaAux;
    }




}

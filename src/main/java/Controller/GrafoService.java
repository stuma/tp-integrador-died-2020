package Controller;

import DAO.DAOgrafo;
import Model.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GrafoService {
    Grafo grafo = new Grafo();


public Grafo gfInit() throws ElementoNoEncontradoException {
//Cracion de plantas
    this.agregarPlanta("Puerto");
    this.agregarPlanta( "1");
    this.agregarPlanta("2");
    this.agregarPlanta("3");
    this.agregarPlanta("4");
    this.agregarPlanta("5");
    this.agregarPlanta("Final");

    //Creacion rutas

    this.conectarPlanta("Puerto","1",(float)200,(float)200,(float)10);
    this.conectarPlanta("Puerto","2",(float)56,(float)3,(float)15);

    this.conectarPlanta("2","5",(float)56,(float)3,(float)60);
    this.conectarPlanta("2","3",(float)56,(float)3,(float)39);

    this.conectarPlanta("1","4",(float)56,(float)3,(float)200);
    this.conectarPlanta("3","4",(float)56,(float)3,(float)5);

    this.conectarPlanta("4","5",(float)56,(float)3,(float)300);
    this.conectarPlanta("4","Final",(float)56,(float)3,(float)50);

    this.conectarPlanta("5","Final",(float)200,(float)3,(float)45);
    DAOgrafo.crearGrafo(grafo);
    this.listarGrafo();
    return grafo;
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
           return  planta.getAdyacente();
    }

    public void listarGrafo(){
        grafo.getPlantas().stream().forEach(t-> System.out.println(t.getNombre()+" Ruta entrada: " +t.getRutaEntrada().stream().map(p->p.getPesoMaximo()).collect(Collectors.toList()) +
                                                                                " Ruta Salida: "+t.getRutaSalida()));

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
                                                    mapToInt(Stock::getCantidad).
                                                    sum();
        }
        return sumaAux;
    }

    /**
     * <blockquote><pre>
     *CALCULO CaminoMinimo por KM
     *  </pre></blockquote>
     * Primero realizamos el calculo total de las distancias minimas de todas las plantas a l aplanta inicio, para esto utilizamos 2 Map,
     * uno donde almacenamos la distancia minima, y en el otro la planta anterior a una para luego construir el cmaino desde esta estructura
     * @param plantaInicio
     * @param plantaDestino
     * @return List<Planta> Camino minimo x km
     * @author Juan
     *
     * */

    public List<Planta> dijkstraKm(Planta plantaInicio, Planta plantaDestino){
        List<Planta> resultado = new ArrayList<Planta>();

        //Creamos el Map de distancia minima de todos os nodos al nodo que pasamos x parametro
        Map<Planta,Float>distancias = new HashMap<>();

        //Map de planta anterior a cada una
        Map<Planta,Planta> plantaAnterior = new HashMap<>(); // el primer valos es la planta y el segundo su anterior

        //setear min distancia de todos a infinito
        grafo.getPlantas().forEach(p->distancias.put(p,Float.MAX_VALUE));

        //setear en 0 distancia al nodo origen(planta inicio)
        distancias.put(plantaInicio,(float)0.0);

        //estructuras auxiliares
        Queue<Planta> pendientes = new LinkedList<Planta>();
        //HashSet<Planta> marcados = new HashSet<Planta>();
        //marcados.add(plantaInicio);
        pendientes.add(plantaInicio);

        while(!pendientes.isEmpty()){

            Planta actual = pendientes.poll();                      //Sacamos una platana de la cola
            List<Ruta> adyacentes = actual.getRutaSalida();         //obtenemos todos las rutas salientes de esa planta y las iteramos

            for(Ruta r : adyacentes){

                Planta pDestino = r.getPlantaDestino();          //Planta destino de la ruta

                Float kmRuta = r.getDistanciaKm();            //Distancia de la ruta actual

                Float kmAnterior = distancias.get(actual);     //distacia de plantaInicio a la planta actual

                Float minkm= kmAnterior +kmRuta;              //Nueva distancia calculada de plantaInicio a la Planta desitno de la ruta pasando por ACTUAL

                if(minkm<distancias.get(pDestino) ){
                    pendientes.remove(actual);
                    //setiar planta anterior a pdestino( //TODO pdestino.setplantaanterior(actual) o hacer otro map en paralelo UPDATE: Resuelto con el map<planta,planta>
                    plantaAnterior.put(pDestino,actual);    //Actualizo el Map de planta anterior
                    distancias.put(pDestino,minkm);         //Actualizo el Map de distancias
                    pendientes.add(pDestino);

                }
            }
        }
        // HASTA ACA SOLO CALCULAMOS LAS DISTANCIAS MINIMAS DE plantaInicio AL RESTO DE LAS PLANTAS
        // Aca deberiamos motrar y obtener el camino para llegas a plantaDestino
        for (Planta p = plantaDestino; p!= null; p= plantaAnterior.get(p)){
            resultado.add(p);
        }
        Collections.reverse(resultado);
        System.out.println(resultado);
        return resultado;
    }
    /**<blockquote><pre>
     *CALCULO CaminoMinimo por HORA
     *  </pre></blockquote>
     * Primero realizamos el calculo total de las distancias minimas de todas las plantas a l aplanta inicio, para esto utilizamos 2 Map,
     * uno donde almacenamos la distancia minima, y en el otro la planta anterior a una para luego construir el cmaino desde esta estructura
     * @param plantaInicio
     * @param plantaDestino
     * @return List<Planta> Camino minimo x hora
     * @author Juan
     *
     * */
    public List<Planta> dijkstraHora(Planta plantaInicio, Planta plantaDestino){
        List<Planta> resultado = new ArrayList<Planta>();

        //Creamos el Map de distancia minima de todos os nodos al nodo que pasamos x parametro
        Map<Planta,Float>distancias = new HashMap<>();

        //Map de planta anterior a cada una
        Map<Planta,Planta> plantaAnterior = new HashMap<>(); // el primer valos es la planta y el segundo su anterior

        //setear min distancia de todos a infinito
        grafo.getPlantas().forEach(p->distancias.put(p,Float.MAX_VALUE));

        //setear en 0 distancia al nodo origen(planta inicio)
        distancias.put(plantaInicio,(float)0.0);

        //estructuras auxiliares
        Queue<Planta> pendientes = new LinkedList<Planta>();
        //HashSet<Planta> marcados = new HashSet<Planta>();
       // marcados.add(plantaInicio);
        pendientes.add(plantaInicio);

        while(!pendientes.isEmpty()){

            Planta actual = pendientes.poll();                      //Sacamos una platana de la cola
            List<Ruta> adyacentes = actual.getRutaSalida();         //obtenemos todos las rutas salientes de esa planta y las iteramos

            for(Ruta r : adyacentes){

                Planta pDestino = r.getPlantaDestino();          //Planta destino de la ruta

                Float horaRuta = r.getDuracionHora();            //Distancia de la ruta actual

                Float horaAnterior = distancias.get(actual);     //distacia de plantaInicio a la planta actual

                Float minHora= horaAnterior +horaRuta;              //Nueva distancia calculada de plantaInicio a la Planta desitno de la ruta pasando por ACTUAL

                if(minHora<distancias.get(pDestino) ){
                    pendientes.remove(actual);
                    //setiar planta anterior a pdestino( //TODO pdestino.setplantaanterior(actual) o hacer otro map en paralelo UPDATE: Resuelto con el map<planta,planta>
                    plantaAnterior.put(pDestino,actual);    //Actualizo el Map de planta anterior
                    distancias.put(pDestino,minHora);         //Actualizo el Map de distancias
                    pendientes.add(pDestino);

                }
            }
        }
        // HASTA ACA SOLO CALCULAMOS LAS DISTANCIAS MINIMAS DE plantaInicio AL RESTO DE LAS PLANTAS
        // Aca deberiamos motrar y obtener el camino para llegas a plantaDestino
        for (Planta p = plantaDestino; p!= null; p= plantaAnterior.get(p)){
            resultado.add(p);
        }
        Collections.reverse(resultado);
        System.out.println(resultado);
        return resultado;
    }


}

package Controller;

import DAO.DAOgrafo;
import Model.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GrafoController {
    Grafo grafo = new Grafo();


public Grafo gfInit() throws ElementoNoEncontradoException {
//Cracion de plantas
   this.agregarPlanta( "1");
    this.agregarPlanta("2");
    this.agregarPlanta("3");
    this.agregarPlanta("4");
    this.agregarPlanta("5");
    this.agregarPlanta("Final");
    this.agregarPlanta("Puerto");

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




    //PAGE RANK:
    public Map<Planta, Double> calcularPageRank(double d) {

        //Valor actualizado de PR -> tiempo N
        Map<Planta, Double> nuevoPageRank = new HashMap<>();

        //Valor anterior de PG -> tiempo N-1. Arranca con un valor igual para todos
        Map<Planta, Double> viejoPageRank = new HashMap<>();
        for (Planta p : this.grafo.getPlantas()) {

            viejoPageRank.put(p, (double) (1));

        }

        //Lista de nodos incidentes a una planta P -> Se va actualizando en cada iteración sobre Planta
        List<Planta> nodosIncidentes = new ArrayList<>();

        //Lista de nodos adyacentes a una planta P -> Para obtener el numero de enlaces del nodo incidente a una planta
        List<Planta> nodosAdyacentes = new ArrayList<>();

        //Variables auxiliares para manejar valores individuales
        double viejoValorPageRank = 0.0;
        double nuevoValorPageRank = 0.0;

        //Para verificar si los valores se van acercando entre si.
        boolean convergencia = true;


        //Arranca las iteraciones: Si no converge antes de llegar a las 100 iteraciones, se detiene ahi
        for (int i = 0; i < 100; i++) {

            //Por cada planta del grafo, calculo el nuevo Pg:
            for (Planta p : this.grafo.getPlantas()) {

                nodosIncidentes.clear();


                //Obtengo la lista de nodos incidentes sobre p
                for (Ruta in : this.grafo.getRutas()) {

                    if (in.getPlantaDestino().getNombre().equals(p.getNombre())) {
                        nodosIncidentes.add(in.getPlantaOrigen());
                    }
                }

                nuevoValorPageRank = 0.0;

                //Calculo el nuevo Page Rank de p en base a sus nodos incidentes
                for (Planta in : nodosIncidentes) {

                    int enlaces = getAdyacentes(in).size();
                    nuevoValorPageRank += (viejoPageRank.get(in) / (enlaces == 0 ? 1 : enlaces));


                }

                //Ajusto con el factor de amortiguación:
                nuevoValorPageRank = (1 - d) + d * nuevoValorPageRank;

                //Agrego el valor del nuevo Page Rank a la planta
                nuevoPageRank.put(p, nuevoValorPageRank);

            }

            //Verifico si converge
            for (Planta planta : nuevoPageRank.keySet()) {

                if (Math.abs(viejoPageRank.get(planta) - nuevoPageRank.get(planta)) >= 0.0001) {
                    convergencia = false;
                }

            }

            if (convergencia) {

                return viejoPageRank;

            } else {

                //Si no converge, vuelve a realizar otra iteración.
                convergencia = true;

                //El viejoPageRank es el nuevoPageRank, y el nuevoPageRank será actualizado en la proxima iteración
                for (Planta p : nuevoPageRank.keySet()) {
                    viejoPageRank.put(p, nuevoPageRank.get(p));
                    nuevoPageRank.put(p, 0.0);
                }
            }

        }

        //Si en ningún momento converge, retorna el ultimo pageRank calculado en las 100 iteraciones
        return viejoPageRank;

    }

    //FLUJO MÁXIMO:

    public double[][] matrizAdyacenciaPeso() {

        double[][] matriz = new double[this.grafo.getPlantas().size()][this.grafo.getPlantas().size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Ruta r : this.grafo.getRutas()) {

            matriz[this.grafo.getPlantas().indexOf(r.getPlantaOrigen())][this.grafo.getPlantas().indexOf(r.getPlantaDestino())] = r.getPesoMaximo();

        }

        return matriz;
    }

    //Fuente: https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
    public boolean existeCamino(double[][] matrizAdyacencia, Planta origen, Planta destino, int camino[]) {

        int s = this.grafo.getPlantas().indexOf(origen);
        int d = this.grafo.getPlantas().indexOf(destino);

        boolean[] visitados = new boolean[this.grafo.getPlantas().size()];

        //Utilizo una cola
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        //Arranca desde el origen. Marca como visitado
        visitados[s] = true;
        camino[s] = -1;


        while (queue.size() != 0) {

            //Toma el primer elemento de la cola
            int u = queue.poll();

            for (int v = 0; v < this.grafo.getPlantas().size(); v++) {

                //Existe camino si: existe arista (>0) y además, si el peso máximo es >0 (por eso la misma condición)
                if (!visitados[v] && matrizAdyacencia[u][v] > 0) {
                    queue.add(v);
                    camino[v] = u;
                    visitados[v] = true;
                }
            }
        }

        //Si destino está en visitados, retornará true ya que existe un camino, sino, false.
        return (visitados[d]);
    }

    public Double calcularFlujoMaximo(Planta origen, Planta destino) {

        int s = this.grafo.getPlantas().indexOf(origen);
        int d = this.grafo.getPlantas().indexOf(destino);

        int u, v;

        //Se mantiene una matriz que va cambiando su valor de peso -> un auxiliar. Inicialmente arranca con mismo valor que matrizAdyacenciaPeso
        double[][] grafoAux = this.matrizAdyacenciaPeso();

        int[] camino = new int[this.grafo.getPlantas().size()];

        double flujoMax = 0;

        //Solo va a iterar mientras exista un camino entre origen y destino. Habra camino mientras exista una ruta entre ellas (intermedia o no)
        //y si el peso máximo que soporta esa ruta es mayor a 0
        while (existeCamino(grafoAux, origen, destino, camino)) {

            //Se calcula el flujo máximo del camino encontrado. Es decir, el valor máximo que puede transportar
            double flujoCamino = Integer.MAX_VALUE;

            for (v = d; v != s; v = camino[v]) {

                u = camino[v];
                flujoCamino = Math.min(flujoCamino, grafoAux[u][v]);
            }

            //Una vez encontrado el máximo residuo, le resta al grafoAux dicho valor.
            for (v = d; v != s; v = camino[v]) {
                u = camino[v];
                grafoAux[u][v] -= flujoCamino;
                grafoAux[v][u] += flujoCamino;

            }

            //Le suma al flujo máximo permitido el valor del flujo maximo encontrado en el camino
            flujoMax += flujoCamino;

        }

        return flujoMax;

    }

    //MATRIZ DE CAMINOS MINIMOS - HORA.
    //Fuente: https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
    public double[][] matrizAdyacenciaHs() {

        double[][] matriz = new double[this.grafo.getPlantas().size()][this.grafo.getPlantas().size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = Double.MAX_VALUE;
            }
        }

        for (Ruta r : this.grafo.getRutas()) {

            matriz[this.grafo.getPlantas().indexOf(r.getPlantaOrigen())][this.grafo.getPlantas().indexOf(r.getPlantaDestino())] = r.getDuracionHora();

        }

        return matriz;
    }

    public double[][] matrizCaminoMinimoHs() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        double[][] distanciaHs = matrizAdyacenciaHs();

        for (int k = 0; k < this.grafo.getPlantas().size(); k++) {
            for (int i = 0; i < this.grafo.getPlantas().size(); i++) {
                for (int j = 0; j < this.grafo.getPlantas().size(); j++) {
                    if (distanciaHs[i][k] + distanciaHs[k][j] < distanciaHs[i][j])
                        distanciaHs[i][j] = distanciaHs[i][k] + distanciaHs[k][j];
                }
            }
        }

        return distanciaHs;
    }

    //MATRIZ DE CAMINOS MÍNIMOS - KM
    public double[][] matrizAdyacenciaKm() {

        double[][] matriz = new double[this.grafo.getPlantas().size()][this.grafo.getPlantas().size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Ruta r : this.grafo.getRutas()) {

            matriz[this.grafo.getPlantas().indexOf(r.getPlantaOrigen())][this.grafo.getPlantas().indexOf(r.getPlantaDestino())] = r.getDistanciaKm();

        }

        return matriz;
    }

    public double[][] matrizCaminoMinimoKm() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        double[][] distanciaHs = matrizAdyacenciaKm();

        for (int k = 0; k < this.grafo.getPlantas().size(); k++) {
            for (int i = 0; i < this.grafo.getPlantas().size(); i++) {
                for (int j = 0; j < this.grafo.getPlantas().size(); j++) {
                    if (distanciaHs[i][k] + distanciaHs[k][j] < distanciaHs[i][j])
                        distanciaHs[i][j] = distanciaHs[i][k] + distanciaHs[k][j];
                }
            }
        }

        return distanciaHs;
    }

}

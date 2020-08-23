package Service;

import DAO.DAOGrafo;
import DAO.DAOPlanta;
import DAO.DAORuta;
import Model.Grafo;
import Model.Planta;
import Model.Ruta;

import java.util.*;
import java.util.stream.Collectors;

public class GrafoService {

    private static GrafoService service;

    private DAOPlanta daoPlanta = DAOPlanta.getDaoPlanta();
    private DAOGrafo daoGrafo = DAOGrafo.getDaoGrafo();
    private DAORuta daoRuta = DAORuta.getDaoRuta();
    private Grafo grafo = new Grafo();

    private GrafoService(){

    }

    public static GrafoService getGrafoService(){

        if(service==null){

            service = new GrafoService();
            service.setGrafo();
        }

        return service;

    }


    public Grafo gfInit() throws ElementoNoEncontradoException {
        //Cracion de plantas
        try {

            this.agregarPlanta("Puerto");
            this.agregarPlanta("1");
            this.agregarPlanta("2");
            this.agregarPlanta("3");
            this.agregarPlanta("4");
            this.agregarPlanta("5");
            this.agregarPlanta("Final");

            //Creacion rutas
            //     distanciaKm,  duracionHora, pesoMaximo)
            this.conectarPlanta("Puerto", "1", 100F,  1.6f,  25000f);
            this.conectarPlanta("Puerto", "2",  110f,  0.9f,  35000f);

            this.conectarPlanta("2", "5",  200f, 2.3f,  35000f);
            this.conectarPlanta("2", "3",  150f,  2.3f,  25000f);

            this.conectarPlanta("1", "4",  210f,  3f,  25000f);
            this.conectarPlanta("3", "4",  60f,  0.5f,  30000f);

            this.conectarPlanta("4", "5",  60f,  0.3f,  35000f);
            this.conectarPlanta("4", "Final",  130f,  1.4f,  50000f);

            this.conectarPlanta("5", "Final",  170f,  2.6f,  45000f);
            DAOGrafo.getDaoGrafo().save(grafo);
            //this.listarGrafo();

        }catch (Exception e){
            e.printStackTrace();
            throw new ElementoNoEncontradoException("Problemas al crear el grafo "+ e.getMessage());
        }
        return grafo;
    }


    public void inicializarGrafoService() throws ElementoNoEncontradoException {
        this.grafo = this.gfInit();
    }


    public Grafo getGrafo() {
        return this.grafo;
    }

    public void setGrafo(){

        Optional<Grafo> opt = DAOGrafo.getDaoGrafo().getAll().stream().findFirst();

        if(opt.isPresent()){

            this.grafo = opt.get();

        }else{
            this.grafo = new Grafo();
            this.daoGrafo.save(this.grafo);
            opt = DAOGrafo.getDaoGrafo().getAll().stream().findFirst();
            this.grafo = opt.get();
        }


/*        this.grafo.setRutas((ArrayList<Ruta>) this.daoRuta.getAll());
        this.grafo.setPlantas((ArrayList<Planta>) this.daoPlanta.getAll());
        DAOGrafo.getDaoGrafo().update(this.grafo);*/

    }

    public void agregarPlanta(String nombre){

        Planta nuevaPlanta =new Planta(nombre);
        nuevaPlanta.setGrafo(this.grafo);
        System.out.println(nuevaPlanta);
        daoPlanta.save(nuevaPlanta);
        this.grafo.setPlantas((ArrayList<Planta>) daoPlanta.getAll());

    }

    public void conectarPlanta(Planta plantaOrigen, Planta plantaDestino, Float distanciaKm, Float duracionHora, Float pesoMaximo){

        Ruta nuevaRuta= new Ruta( plantaOrigen,  plantaDestino,  distanciaKm,  duracionHora,  pesoMaximo);
        plantaOrigen.addRutaSalida(nuevaRuta);
        plantaDestino.addRutaEntrada(nuevaRuta);
        grafo.addRuta(nuevaRuta);
        daoGrafo.update(grafo);


    }

    public void conectarPlanta(String plantaOrigenName, String plantaDestinoName, Float distanciaKm, Float duracionHora, Float pesoMaximo) throws ElementoNoEncontradoException {

        try {
            Planta origen = this.grafo.getPlantas().stream().
                    filter(t -> t.getNombre().equals(plantaOrigenName)).
                    findFirst().orElseThrow();
            //get();
            System.out.println(origen.getNombre());

            Planta destino = this.grafo.getPlantas().stream().
                    filter(t -> t.getNombre().equals(plantaDestinoName)).
                    findFirst().orElseThrow();
            //   get();

            Ruta nuevaRuta = new Ruta(origen, destino, distanciaKm, duracionHora, pesoMaximo);
            origen.addRutaSalida(nuevaRuta);
            destino.addRutaEntrada(nuevaRuta);
            this.grafo.addRuta(nuevaRuta);
            nuevaRuta.setGrafo(this.grafo);
            this.daoRuta.save(nuevaRuta);

            //daoPlanta.save(nuevaPlanta);
            //this.daoGrafo.update(this.grafo);


        } catch (Exception e){
            e.printStackTrace();
            throw new ElementoNoEncontradoException("No existe esa planta "+ e.getMessage());
        }
    }


    public List<Planta> getAdyacentes(Planta p){

/*        ArrayList<Planta> ady = new ArrayList<>();

        //System.out.print("Origen: " + p.getNombre());
        for (Ruta r : this.grafo.getRutas()) {

            if (r.getPlantaOrigen().getNombre().equals(p.getNombre())) {
                ady.add(r.getPlantaDestino());
                //      System.out.println(" Destino: " + r.getPlantaDestino().getNombre());
            }
        }

        return ady;*/
        return  p.getAdyacente();
    }

    public void listarGrafo(){
        grafo.getPlantas().stream().forEach(t-> System.out.println(t.getNombre()+" Ruta entrada: " +t.getRutaEntrada().stream().map(p->p.getPesoMaximo()).collect(Collectors.toList()) +
                                                                                " Ruta Salida: "+t.getRutaSalida().stream().map(p->p.getPesoMaximo()).collect(Collectors.toList())));
   }


    public float distanciakm(Planta p1, Planta p2){
        return  (p1.getRutaSalida().stream().filter(t->t.getPlantaDestino().equals(p2)).findFirst().get()).getDistanciaKm();
    }
    public float distanciaHora(Planta p1, Planta p2){
        return  (p1.getRutaSalida().stream().filter(t->t.getPlantaDestino().equals(p2)).findFirst().get()).getDuracionHora();
    }

    public Float calcularKmCamino(List<Planta> camino){

        Float aux=0F;
           for(int i=0, j=1; j<camino.size(); i++, j++){
                aux+=distanciakm(camino.get(i),camino.get(j));

            }
        return aux;
    }

    public Float calcularHoraCamino(List<Planta> camino){

        Float aux=0F;
        for(int i=0, j=1; j<camino.size(); i++, j++){
            aux+=distanciaHora(camino.get(i),camino.get(j));

        }
        return aux;
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

        this.setGrafo();
        plantaInicio = this.grafo.getPlantas().get(this.grafo.getPlantas().indexOf(plantaInicio));
        plantaDestino = this.grafo.getPlantas().get(this.grafo.getPlantas().indexOf(plantaDestino));

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
                Float dis = distancias.get(pDestino);

                if(dis!=null && minkm<dis){
                    pendientes.remove(actual);
                    //setiar planta anterior a pdestino(
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
        //System.out.println(resultado);
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

        this.setGrafo();
        plantaInicio = this.grafo.getPlantas().get(this.grafo.getPlantas().indexOf(plantaInicio));
        plantaDestino = this.grafo.getPlantas().get(this.grafo.getPlantas().indexOf(plantaDestino));

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

                Float dis = distancias.get(pDestino);
                System.out.println(dis);

                if(dis!=null && minHora<dis){
                    System.out.println("entra al if");
                    pendientes.remove(actual);
                    //setiar planta anterior a pdestino(
                    plantaAnterior.put(pDestino,actual);    //Actualizo el Map de planta anterior

                    System.out.println(plantaAnterior.get(pDestino));

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
        return resultado;
    }


    //PAGE RANK:
    public Map<Planta, Double> calcularPageRank(double d) {

/*        if(this.grafo.getPlantas()==null){
            return new HashMap<Planta, Double>();
        }*/
        //Valor actualizado de PR -> tiempo N
        Map<Planta, Double> nuevoPageRank = new HashMap<>();

        //Valor anterior de PG -> tiempo N-1. Arranca con un valor igual para todos
        Map<Planta, Double> viejoPageRank = new HashMap<>();

        System.out.println(viejoPageRank);
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
                    nuevoValorPageRank += (viejoPageRank.get(in)==null? 0 : viejoPageRank.get(in))/ (enlaces == 0 ? 1 : enlaces);


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
        if(visitados.length==0){
            return false;
        }
        //Utilizo una cola
        LinkedList<Integer> queue = new LinkedList<>();
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
    public Float[][] matrizAdyacenciaHs() {

        System.out.println(this.grafo.getPlantas().size());
        Float[][] matriz = new Float[this.grafo.getPlantas().size()][this.grafo.getPlantas().size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = Float.MAX_VALUE;
            }
        }

        for (Ruta r : this.grafo.getRutas()) {

            matriz[this.grafo.getPlantas().indexOf(r.getPlantaOrigen())][this.grafo.getPlantas().indexOf(r.getPlantaDestino())] = r.getDuracionHora();

        }

        return matriz;
    }

    public Float[][] matrizCaminoMinimoHs() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        Float[][] distanciaHs = matrizAdyacenciaHs();

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
    public Float[][] matrizAdyacenciaKm() {

        Float[][] matriz = new Float[this.grafo.getPlantas().size()][this.grafo.getPlantas().size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = Float.MAX_VALUE;
            }
        }

        for (Ruta r : this.grafo.getRutas()) {

            matriz[this.grafo.getPlantas().indexOf(r.getPlantaOrigen())][this.grafo.getPlantas().indexOf(r.getPlantaDestino())] = r.getDistanciaKm();

        }

        return matriz;
    }

    public Float[][] matrizCaminoMinimoKm() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        Float[][] distanciaKm = matrizAdyacenciaKm();

        for (int k = 0; k < this.grafo.getPlantas().size(); k++) {
            for (int i = 0; i < this.grafo.getPlantas().size(); i++) {
                for (int j = 0; j < this.grafo.getPlantas().size(); j++) {
                    if (distanciaKm[i][k] + distanciaKm[k][j] < distanciaKm[i][j])
                        distanciaKm[i][j] = distanciaKm[i][k] + distanciaKm[k][j];
                }
            }
        }

        return distanciaKm;
    }
}

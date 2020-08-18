package Model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "grafo")
public class Grafo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="grafo_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Ruta> listaRutas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="grafo_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Planta> listaPlantas;

    public Grafo() {
        this.listaRutas = new ArrayList<>();
        this.listaPlantas = new ArrayList<>();
    }

    public Grafo(Integer id, ArrayList<Ruta> listaRutas, ArrayList<Planta> listaPlantas) {
        this.id = id;
        this.listaRutas = listaRutas;
        this.listaPlantas = listaPlantas;
    }

    public List<Ruta> getRutas() {
        return listaRutas;

    }

    public void addRuta(Ruta r) {
        this.listaRutas.add(r);
    }

    public void addPlanta(Planta p) {
        this.listaPlantas.add(p);
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        listaRutas = rutas;
    }

    public List<Planta> getPlantas() {
        return listaPlantas;
    }

    public void setPlantas(ArrayList<Planta> plantas) {
        listaPlantas = plantas;
    }

    public ArrayList<Planta> caminoMinimoKm(Planta origen, Planta destino) {
        return null;
    }

    public ArrayList<Planta> caminoMinimoHora(Planta origen, Planta destino) {
        return null;
    }

    public ArrayList<Planta> flujoMaximoPlantas(Planta origen, Planta destino, Float peso) {
        return null;
    }

/*    public List<Planta> getAdyacentes(Planta p) {

        ArrayList<Planta> ady = new ArrayList<>();

        //System.out.print("Origen: " + p.getNombre());
        for (Ruta r : this.listaRutas) {

            if (r.getPlantaOrigen().getNombre().equals(p.getNombre())) {
                ady.add(r.getPlantaDestino());
                //      System.out.println(" Destino: " + r.getPlantaDestino().getNombre());
            }
        }

        return ady;
    }*/

    public void conectarPlanta(Planta plantaOrigen, Planta plantaDestino) {

        Ruta nuevaRuta = new Ruta(plantaOrigen, plantaDestino, 0F, 0F, 0F);
        this.listaRutas.add(nuevaRuta);

    }

    public void conectarPlanta(Planta plantaOrigen, Planta plantaDestino, float km, float hs, float pesoMax) {

        Ruta nuevaRuta = new Ruta(plantaOrigen, plantaDestino, km, hs, pesoMax);
        this.listaRutas.add(nuevaRuta);

    }

/*

    //PAGE RANK:
    public Map<Planta, Double> calcularPageRank(double d) {

        //Valor actualizado de PR -> tiempo N
        Map<Planta, Double> nuevoPageRank = new HashMap<>();

        //Valor anterior de PG -> tiempo N-1. Arranca con un valor igual para todos
        Map<Planta, Double> viejoPageRank = new HashMap<>();
        for (Planta p : this.listaPlantas) {

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
            for (Planta p : this.listaPlantas) {

                nodosIncidentes.clear();


                //Obtengo la lista de nodos incidentes sobre p
                for (Ruta in : this.listaRutas) {

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

        double[][] matriz = new double[this.listaPlantas.size()][this.listaPlantas.size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Ruta r : this.listaRutas) {

            matriz[this.listaPlantas.indexOf(r.getPlantaOrigen())][this.listaPlantas.indexOf(r.getPlantaDestino())] = r.getPesoMaximo();

        }

        return matriz;
    }

    //Fuente: https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
    public boolean existeCamino(double[][] matrizAdyacencia, Planta origen, Planta destino, int camino[]) {

        int s = this.listaPlantas.indexOf(origen);
        int d = this.listaPlantas.indexOf(destino);

        boolean[] visitados = new boolean[this.listaPlantas.size()];

        //Utilizo una cola
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        //Arranca desde el origen. Marca como visitado
        visitados[s] = true;
        camino[s] = -1;


        while (queue.size() != 0) {

            //Toma el primer elemento de la cola
            int u = queue.poll();

            for (int v = 0; v < this.listaPlantas.size(); v++) {

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

        int s = this.listaPlantas.indexOf(origen);
        int d = this.listaPlantas.indexOf(destino);

        int u, v;

        //Se mantiene una matriz que va cambiando su valor de peso -> un auxiliar. Inicialmente arranca con mismo valor que matrizAdyacenciaPeso
        double[][] grafoAux = this.matrizAdyacenciaPeso();

        int[] camino = new int[this.listaPlantas.size()];

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

        double[][] matriz = new double[this.listaPlantas.size()][this.listaPlantas.size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = Double.MAX_VALUE;
            }
        }

        for (Ruta r : this.listaRutas) {

            matriz[this.listaPlantas.indexOf(r.getPlantaOrigen())][this.listaPlantas.indexOf(r.getPlantaDestino())] = r.getDuracionHora();

        }

        return matriz;
    }

    public double[][] matrizCaminoMinimoHs() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        double[][] distanciaHs = matrizAdyacenciaHs();

        for (int k = 0; k < this.listaPlantas.size(); k++) {
            for (int i = 0; i < this.listaPlantas.size(); i++) {
                for (int j = 0; j < this.listaPlantas.size(); j++) {
                    if (distanciaHs[i][k] + distanciaHs[k][j] < distanciaHs[i][j])
                        distanciaHs[i][j] = distanciaHs[i][k] + distanciaHs[k][j];
                }
            }
        }

        return distanciaHs;
    }

    //MATRIZ DE CAMINOS MÍNIMOS - KM
    public double[][] matrizAdyacenciaKm() {

        double[][] matriz = new double[this.listaPlantas.size()][this.listaPlantas.size()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Ruta r : this.listaRutas) {

            matriz[this.listaPlantas.indexOf(r.getPlantaOrigen())][this.listaPlantas.indexOf(r.getPlantaDestino())] = r.getDistanciaKm();

        }

        return matriz;
    }

    public double[][] matrizCaminoMinimoKm() {

        //Inicializa la matriz de distancias pero con las distancias iniciales en hs. (la matriz de adyacencia
        double[][] distanciaHs = matrizAdyacenciaKm();

        for (int k = 0; k < this.listaPlantas.size(); k++) {
            for (int i = 0; i < this.listaPlantas.size(); i++) {
                for (int j = 0; j < this.listaPlantas.size(); j++) {
                    if (distanciaHs[i][k] + distanciaHs[k][j] < distanciaHs[i][j])
                        distanciaHs[i][j] = distanciaHs[i][k] + distanciaHs[k][j];
                }
            }
        }

        return distanciaHs;
    }
*/


}




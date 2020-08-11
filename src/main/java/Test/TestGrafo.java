package Test;

import Model.Planta;
import Model.Ruta;
import Service.ElementoNoEncontradoException;
import Service.GrafoService;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestGrafo {

    private GrafoService grafoService = new GrafoService();

    @Test
    public void pruebaGrafoTest() throws ElementoNoEncontradoException {

        GrafoService grafoService= new GrafoService();
        grafoService.setGrafo(this.grafoService.gfInit());

        System.out.println("Grafo: ");
        System.out.println("Plantas: ");
        for (Planta p : this.grafoService.getGrafo().getPlantas()){
            System.out.print(p.getNombre() + "  ");
        }
        System.out.println();
        System.out.println("Rutas:");
        for (Ruta r : this.grafoService.getGrafo().getRutas()){
            System.out.println("Origen: " + r.getPlantaOrigen().getNombre() + " Destino: " + r.getPlantaDestino().getNombre());
        }
        System.out.println();

       //Prueba de flujo máximo:
        System.out.println("Flujo Máximo entre Primer nodo y Ultimo nodo en Kg: " +
                this.grafoService.calcularFlujoMaximo(this.grafoService.getGrafo().getPlantas().get(0),
                this.grafoService.getGrafo().getPlantas().get(this.grafoService.getGrafo().getPlantas().size()-1)));


        //Page Rank:
        System.out.println();
        System.out.println("Page Rank:");
        for(Map.Entry<Planta, Double> p : this.grafoService.calcularPageRank(0.5).entrySet()){
            System.out.println("Planta: " + p.getKey().getNombre() + " - Page Rank: " + p.getValue());
        }

        System.out.println();
        //Matriz de camino minimo
        System.out.println("Matriz de Caminos Mínimos");
        double[][] matrizHs = this.grafoService.matrizCaminoMinimoHs();
        double[][] matrizKm = this.grafoService.matrizCaminoMinimoKm();

        System.out.println("Matriz de Caminos Mínimos Hs: ");
        for (int i = 0; i < matrizHs.length; i++) {
            for (int j = 0; j <matrizHs.length ; j++) {
                System.out.printf("%.6f   ", (float)matrizHs[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Matriz de Caminos Mínimos Km: ");
        for (int i = 0; i < matrizKm.length; i++) {
            for (int j = 0; j <matrizKm.length ; j++) {
                System.out.printf("%.4f   ", (float)matrizKm[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        //Camino entre dos plantas
        System.out.println("Camino mínimo entre dos plantas: Hs");
        for(Planta p : this.grafoService.dijkstraHora(this.grafoService.getGrafo().getPlantas().get(0), this.grafoService.getGrafo().getPlantas().get(this.grafoService.getGrafo().getPlantas().size()-1))){
            System.out.print(p.getNombre() + "  ");
        }

        System.out.println();
        System.out.println("Camino mínimo entre dos plantas: Km");
        for(Planta p : this.grafoService.dijkstraKm(this.grafoService.getGrafo().getPlantas().get(0), this.grafoService.getGrafo().getPlantas().get(this.grafoService.getGrafo().getPlantas().size()-1))){
            System.out.print(p.getNombre() + "  ");
        }


    }

}

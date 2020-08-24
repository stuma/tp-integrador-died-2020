package Test;

import Model.Planta;
import Model.Ruta;
import Service.ElementoNoEncontradoException;
import Service.GrafoService;


import java.util.Map;

public class TestGrafo {

    private GrafoService grafoService = GrafoService.getGrafoService();

    public static void main(String[] args) {

        TestGrafo app = new TestGrafo();

        try {
            //app.crearGrafoTest();
            app.grafoTest();
        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }

    }

    public void crearGrafoTest() throws ElementoNoEncontradoException {

        this.grafoService.inicializarGrafoService();

    }
    public void grafoTest() throws ElementoNoEncontradoException {

        this.grafoService.setGrafo();

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
        Float[][] matrizHs = this.grafoService.matrizCaminoMinimoHs();
        Float[][] matrizKm = this.grafoService.matrizCaminoMinimoKm();

        System.out.println("Matriz de Caminos Mínimos Hs: ");
        for (int i = 0; i < matrizHs.length; i++) {
            for (int j = 0; j <matrizHs.length ; j++) {
                if(matrizHs[i][j].equals(Float.MAX_VALUE)){
                    System.out.print("   ---------   ");
                }else{

                    System.out.printf("%.4f   ", matrizHs[i][j]);
                }

            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Matriz de Caminos Mínimos Km: ");
        for (int i = 0; i < matrizKm.length; i++) {
            for (int j = 0; j <matrizKm.length ; j++) {
                if(matrizKm[i][j].equals(Float.MAX_VALUE)){
                    System.out.print("   ------   ");
                }else{
                    System.out.printf("   %.4f   ", matrizKm[i][j]);
                }

            }
            System.out.println();
        }


        System.out.println();
        //Camino entre dos plantas
        System.out.println("Camino mínimo entre dos plantas: Hs");
        Planta origen = this.grafoService.getGrafo().getPlantas().get(1);
        Planta destino = this.grafoService.getGrafo().getPlantas().get(this.grafoService.getGrafo().getPlantas().size()-1);
        System.out.println(origen);
        System.out.println(destino);


        for(Planta p : this.grafoService.dijkstraHora(origen,destino)){
            System.out.print(p.getNombre() + "  ");
        }

        System.out.println();
        System.out.println("Camino mínimo entre dos plantas: Km");
        for(Planta p : this.grafoService.dijkstraKm(origen, destino)){
            System.out.print(p.getNombre() + "  ");
        }


    }

}

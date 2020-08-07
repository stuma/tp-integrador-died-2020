package Test;

import Controller.GrafoController;
import Model.Grafo;
import Model.Planta;

import java.util.Map;

public class TestGrafo {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();
        GrafoController controller = new GrafoController();

        Planta p1 = new Planta("Planta 1");
        Planta p2 = new Planta("Planta 2");
        Planta p3 = new Planta("Planta 3");
        Planta p4 = new Planta("Planta 4");
        Planta p5 = new Planta("Planta 5");
        Planta p6 = new Planta("Planta 6");
        Planta p7 = new Planta("Planta 7");

/*        grafo.addPlanta(p1);
        grafo.addPlanta(p2);
        grafo.addPlanta(p3);
        grafo.addPlanta(p4);

        grafo.conectarPlanta(p1, p2);
        grafo.conectarPlanta(p1, p3);

        grafo.conectarPlanta(p3, p4);
        grafo.conectarPlanta(p3, p2);

        grafo.conectarPlanta(p4, p3);

        System.out.println("P1: " + grafo.calcularPageRank(p1, 0.5, 4) );
        System.out.println("P2: " + grafo.calcularPageRank(p2, 0.5, 4) );
        System.out.println("P3: " + grafo.calcularPageRank(p3, 0.5, 4) );
        System.out.println("P4: " + grafo.calcularPageRank(p4, 0.5, 4) );*/

        grafo.addPlanta(p1);
        grafo.addPlanta(p2);
        grafo.addPlanta(p3);
        grafo.addPlanta(p4);
        grafo.conectarPlanta(p1, p2);
        grafo.conectarPlanta(p1, p3);
        grafo.conectarPlanta(p2, p3);
        grafo.conectarPlanta(p3, p1);
        grafo.conectarPlanta(p3, p4);


        Grafo grafo2 = new Grafo();
        grafo2.addPlanta(p1);
        grafo2.addPlanta(p2);
        grafo2.addPlanta(p3);
        grafo2.addPlanta(p4);
        grafo2.addPlanta(p5);
        grafo2.addPlanta(p6);
        grafo2.addPlanta(p7);

        grafo2.conectarPlanta(p1, p2, 0F, 0F, 5);
        grafo2.conectarPlanta(p1, p3, 0F, 0F, 6);
        grafo2.conectarPlanta(p1, p4, 0F, 0F, 5);

        grafo2.conectarPlanta(p2, p3, 0F, 0F, 2);
        grafo2.conectarPlanta(p2, p5, 0F, 0F, 3);

        grafo2.conectarPlanta(p3, p2, 0F, 0F, 2);
        grafo2.conectarPlanta(p3, p4, 0F, 0F, 3);
        grafo2.conectarPlanta(p3, p5, 0F, 0F, 3);
        grafo2.conectarPlanta(p3, p6, 0F, 0F, 7);

        grafo2.conectarPlanta(p4, p6, 0F, 0F, 5);

        grafo2.conectarPlanta(p5, p7, 0F, 0F, 8);
        grafo2.conectarPlanta(p5, p6, 0F, 0F, 1);

        grafo2.conectarPlanta(p6, p5, 0F, 0F, 1);
        grafo2.conectarPlanta(p6, p7, 0F, 0F, 7);

        //pruebaPageRank(grafo);
        pruebaFlujoMaximo(grafo2, p1, p7);

    }


    private static void pruebaPageRank(Grafo grafo){

        for(Map.Entry<Planta, Double> pr : grafo.calcularPageRank(0.5).entrySet()){

            System.out.println(pr.getKey().getNombre() + " - " +pr.getValue());

        }
    }

    private static void pruebaFlujoMaximo(Grafo g, Planta origen, Planta destino){

        System.out.println(g.calcularFlujoMaximo(origen, destino));

    }
}
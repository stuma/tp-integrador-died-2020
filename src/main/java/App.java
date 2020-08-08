import Model.Grafo;
import Model.Planta;
import java.util.ArrayList;
import java.util.List;

import Controller.*;
//import jdk.swing.interop.SwingInterOpUtils;

public class App {
    public static void main(String[] args) throws ElementoNoEncontradoException {

        GrafoService gfComtroller = new GrafoService();

        Grafo gf = gfComtroller.gfInit();
        ArrayList<Planta> pgrafo= gf.getPlantas();

        System.out.println("ACA IMPRIME CAMINO MINIMO POR KM");
        List<Planta> plantitas= gfComtroller.dijkstraKm(pgrafo.get(6),pgrafo.get(5));

        System.out.println("ACA IMPRIME CAMINO CON NOMBRES SAFAÑSJFGHAPJSLFHÑLJDH");
        System.out.print("CAMINO: ");
        plantitas.forEach(t-> System.out.print(t.getNombre()+"-"));
        System.out.println("");

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        System.out.println("ACA IMPRIME CAMINO MINIMO POR HORA");
        List<Planta> plantas= gfComtroller.dijkstraHora(pgrafo.get(6),pgrafo.get(5));

        System.out.println("ACA IMPRIME CAMINO CON NOMBRES SAFAÑSJFGHAPJSLFHÑLJDH");
        plantas.forEach(t-> System.out.println(t.getNombre()+"-"));


    }
}

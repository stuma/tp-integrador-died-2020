import Model.Grafo;
import Model.Planta;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Grafo gf = new Grafo();
       gf.agregarPlanta( "1");
       gf.agregarPlanta("2");
        gf.agregarPlanta("3");
        gf.agregarPlanta("4");
        gf.agregarPlanta("5");
        gf.agregarPlanta("Final");
        gf.agregarPlanta("Puerto");


       ArrayList<Planta> aux = gf.getPlantas();



      /*  gf.conectarPlanta(aux.get(0),aux.get(1),(float)33,(float)2,(float)3500);
        gf.conectarPlanta(aux.get(1),aux.get(0),(float)60,(float)500,(float)8500);

        gf.conectarPlanta(aux.get(1),aux.get(5),(float)60,(float)500,(float)8500);

        gf.conectarPlanta(aux.get(3),aux.get(4),(float)60,(float)500,(float)8500);
       */
        gf.conectarPlanta("2","1",(float)56,(float)3,(float)1300);

        gf.conectarPlanta("1","2",(float)56,(float)3,(float)1300);
        gf.conectarPlanta("2","1",(float)56,(float)3,(float)1300);
        gf.listarGrafo();


    }
}

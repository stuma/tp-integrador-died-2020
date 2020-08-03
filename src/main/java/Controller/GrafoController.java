package Controller;

import Model.Grafo;
import Model.Planta;

import java.util.ArrayList;

public class GrafoController {


public void gfInit(){
    Grafo gf = new Grafo();
    gf.agregarPlanta( "1");
    gf.agregarPlanta("2");
    gf.agregarPlanta("3");
    gf.agregarPlanta("4");
    gf.agregarPlanta("5");
    gf.agregarPlanta("Final");
    gf.agregarPlanta("Puerto");


    ArrayList<Planta> aux = gf.getPlantas();

    gf.conectarPlanta("Puerto","1",(float)56,(float)3,(float)1300);
    gf.conectarPlanta("Puerto","2",(float)56,(float)3,(float)1300);

    gf.conectarPlanta("2","5",(float)56,(float)3,(float)1300);
    gf.conectarPlanta("2","3",(float)56,(float)3,(float)1300);

    gf.conectarPlanta("1","4",(float)56,(float)3,(float)1300);
    gf.conectarPlanta("3","4",(float)56,(float)3,(float)1300);

    gf.conectarPlanta("4","5",(float)56,(float)3,(float)1300);
    gf.conectarPlanta("4","Final",(float)56,(float)3,(float)1300);

    gf.conectarPlanta("5","Final",(float)56,(float)3,(float)1300);
    gf.listarGrafo();
}



}

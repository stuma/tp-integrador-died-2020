package Model;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Ruta> Rutas;
    private ArrayList<Planta> Plantas;

    public ArrayList<Ruta> getRutas() {
        return Rutas;
    }

    public void setRutas(ArrayList<Ruta> rutas) {
        Rutas = rutas;
    }

    public ArrayList<Planta> getPlantas() {
        return Plantas;
    }

    public void setPlantas(ArrayList<Planta> plantas) {
        Plantas = plantas;
    }
}


package Model;

import java.util.ArrayList;
import java.util.Map;

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

    void agregarPlanta(){

    }

    void conectarPlanta(){

    }

    public ArrayList<Planta> getAdyacentes(Planta planta){
        return null;
    }

    public ArrayList<Planta> caminoMinimoKm(Planta origen, Planta destino){
        return null;
    }

    public ArrayList<Planta> caminoMinimoHora(Planta origen, Planta destino){
        return null;
    }

    public ArrayList<Planta> flujoMaximoPlantas(Planta origen, Planta destino, Float peso){
        return null;
    }

    public Map<Planta, Integer> pageRank(){
        return null;
    }


}


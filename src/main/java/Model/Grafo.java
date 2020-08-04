package Model;

import java.util.ArrayList;
import java.util.Map;

public class Grafo {

    private ArrayList<Ruta> listaRutas;
    private ArrayList<Planta> listaPlantas;

    public Grafo() {
        this.listaRutas = new ArrayList<>();
        this.listaPlantas = new ArrayList<>();
    }


    public ArrayList<Ruta> getRutas() {
        return listaRutas;

    }
    public void addRuta(Ruta r){
        this.listaRutas.add(r);
    }
    public void addPlanta(Planta p){
        this.listaPlantas.add(p);
    }


    public void setRutas(ArrayList<Ruta> rutas) {
        listaRutas = rutas;
    }

    public ArrayList<Planta> getPlantas() {
        return listaPlantas;
    }

    public void setPlantas(ArrayList<Planta> plantas) {
        listaPlantas = plantas;
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


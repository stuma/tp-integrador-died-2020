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

    public void setRutas(ArrayList<Ruta> rutas) {
        listaRutas = rutas;
    }

    public ArrayList<Planta> getPlantas() {
        return listaPlantas;
    }

    public void setPlantas(ArrayList<Planta> plantas) {
        listaPlantas = plantas;
    }

    public void agregarPlanta(String nombre){
        Planta nuevaPlanta =new Planta(nombre);
        this.listaPlantas.add(nuevaPlanta);
    }

     public void conectarPlanta(Planta plantaOrigen, Planta plantaDestino, Float distanciaKm, Float duracionHora, Float pesoMaximo){

         //listaPlantas.stream().forEach(t-> System.out.println(t.getNombre()));

        Ruta nuevaRuta= new Ruta( plantaOrigen,  plantaDestino,  distanciaKm,  duracionHora,  pesoMaximo);
        this.listaRutas.add(nuevaRuta);
    }

    public void conectarPlanta(String plantaOrigenName, String plantaDestinoName, Float distanciaKm, Float duracionHora, Float pesoMaximo){

        listaPlantas.stream().forEach(t-> System.out.println(t.getNombre()));


        Planta origen = listaPlantas.stream().
                                     filter(t-> t.getNombre().equals(plantaOrigenName)).
                                     findFirst().
                                     get();

        Planta destino = this.listaPlantas.stream().filter(t-> t.getNombre().equals(plantaDestinoName)).findFirst().get();

        Ruta nuevaRuta= new Ruta( origen,  destino,  distanciaKm,  duracionHora,  pesoMaximo);
        this.listaRutas.add(nuevaRuta);
    }

    public ArrayList<Planta> getAdyacentes(Planta planta){
        planta.getAdyacente();


        return null;
    }

    public void listarGrafo(){
        this.listaPlantas.stream().forEach(t-> System.out.println(t.getNombre()+" Ruta entrada: " +t.getRutaEntrada() +"+ Ruta Salida: "+t.getRutaSalida()));

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


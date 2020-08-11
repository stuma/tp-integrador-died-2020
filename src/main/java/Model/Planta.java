package Model;

import java.util.ArrayList;

public class Planta {
    private Integer id;
    private String nombre;
    private ArrayList<Ruta> rutaEntrada;
    private ArrayList<Ruta> rutaSalida;
    private ArrayList<Stock> listaStockInsumos;




    public  Planta(String nombre) {

        this.rutaSalida = new ArrayList<>();
        this.rutaEntrada = new ArrayList<>();
        this.listaStockInsumos = new ArrayList<>();
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ruta> getRutaEntrada() {
        return rutaEntrada;
    }

    public void addRutaEntrada(Ruta rutaEntrada) {
        this.rutaEntrada.add(rutaEntrada);
    }

    public ArrayList<Ruta> getRutaSalida() {
        return rutaSalida;
    }

    public void addRutaSalida(Ruta rutaSalida) {
        this.rutaSalida.add(rutaSalida);
    }

    public ArrayList<Stock> getListaStockInsumos() {
        return listaStockInsumos;
    }

    public void setListaStockInsumos(ArrayList<Stock> listaStockInsumos) {
        this.listaStockInsumos = listaStockInsumos;
    }
    public void addStockListaStock(Stock stock){
        this.listaStockInsumos.add(stock);
    }

    public ArrayList<Planta> getAdyacente(){

        ArrayList<Planta> auxPlantas= new ArrayList<>();

        for (Ruta r :this.rutaSalida ) {
            auxPlantas.add(r.getPlantaDestino());
        }
        return auxPlantas;
    }
}

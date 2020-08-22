package View.guiController;

import Service.ElementoNoEncontradoException;
import Service.InsumosService;
import Service.PlantaService;
import Model.Insumo;
import Model.Planta;
import Model.Stock;
import View.gui.stock.AgregarStockPanel;
import View.gui.stock.BuscarStockPanel;
import View.gui.stock.ModificacionStockPopUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockGuiController {

    private static StockGuiController controller;
    private Stock nuevoStock;
    private Planta p;
    private Insumo i;
    private List<Stock> listaStockActual;
    private List<Planta> listaPlantasActual;
    private List<Insumo> listaInsumosActual;
    private List<Stock> listaStockPuntoPedido;

    private PlantaService servicePlanta;
    private InsumosService serviceInsumo;


    //Constructor privado
    private StockGuiController(){

        this.servicePlanta = new PlantaService();
        this.serviceInsumo = new InsumosService();

        this.listaStockActual = this.servicePlanta.getListaStock();
        this.listaStockPuntoPedido =  this.listaStockActual.stream()
                .filter(s-> s.getCantidad()<=s.getPuntoPedido()).collect(Collectors.toList());

        try {
            this.listaPlantasActual = this.servicePlanta.getListaPlantas();
        } catch (ElementoNoEncontradoException e) {
           this.listaPlantasActual = new ArrayList<>();
        }
        try {
            this.listaInsumosActual = this.serviceInsumo.getListaInsumos();
        } catch (ElementoNoEncontradoException e) {
            this.listaInsumosActual = new ArrayList<>();
        }
        this.nuevoStock = new Stock();

    }

    //Retorna una instancia de CamionController. Evita las multiples instancias.
    public static StockGuiController getStockController(){

        if(controller==null){

            controller = new StockGuiController();

        }

        return controller;
    }


    //ALTA DE STOCK:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public Insumo guardar(AgregarStockPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        Optional<Stock> optStock = this.listaStockActual.stream().
                filter(s -> s.getPlanta().getNombre().equals(this.p.getNombre()) && s.getInsumo().getDescripcion().equals(this.i.getDescripcion()))
                .findFirst();
        if(optStock.isPresent()){
            throw new Exception("Ya existe un stock para la planta y el insumo seleccionado. Para modificar los valores, busque en la tabla y oprima Modificar");
        }

        this.nuevoStock.setInsumo(i);
        this.nuevoStock.setPlanta(p); //El service debería actualizar la lista de stock de p. Lo hace en PlantaController

        this.servicePlanta.crearStock(this.nuevoStock.getPlanta(),this.nuevoStock.getInsumo(), this.nuevoStock.getCantidad(),this.nuevoStock.getPuntoPedido());
        this.listaStockActual.clear();
        this.listaStockActual.addAll(this.servicePlanta.getListaStock());


/*
        this.p.addStockListaStock(this.nuevoStock);
        this.listaStockActual.add(this.nuevoStock);
*/
        return null;
    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(AgregarStockPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<>();
        Boolean[] camposValidos = {false, false};
        this.nuevoStock = new Stock();

        try {

            //Obtiene la planta correspondiente al item seleccionado
            if(panel.getTxtPlanta()!=null) {

               this.p = this.listaPlantasActual.get(panel.getTxtPlanta().getSelectedIndex());

            } else{
                camposVacios.add(2);
            }

            //Obtiene Insumo correspondiente al item seleccionado
            if(panel.getTxtInsumo()!=null) {

                this.i = this.listaInsumosActual.get(panel.getTxtInsumo().getSelectedIndex());

            } else{
                camposVacios.add(3);
            }

            //Cantidad de Insumos
            if(panel.getTxtCantidad()!=null && !panel.getTxtCantidad().getText().equals("")) {
                this.nuevoStock.setCantidad(Integer.valueOf(panel.getTxtCantidad().getText()));
                camposValidos[0] = true;

            } else {
                camposVacios.add(0);
            }

            //Punto de Pedido
            if(panel.getTxtPuntoPedido()!=null && !panel.getTxtPuntoPedido().getText().equals("")) {
                this.nuevoStock.setPuntoPedido(Integer.valueOf(panel.getTxtPuntoPedido().getText()));
                camposValidos[1] = true;

            }else{
                camposVacios.add(1);
            }

            if(camposVacios.size()>0){
                panel.mostrarErrores(camposVacios);
                throw new Exception();

            }

        } catch(NumberFormatException nfe) {

            panel.mostrarErrores(camposValidos);
            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){
            throw new Exception("Hubo un error al procesar los Datos");
        }

    }



    //MODIFICACIÓN:
    //Metodo que llama a validar datos y llama al service para almacenar la modificación. Invocado desde ModificacionPopup
    public void modificar(ModificacionStockPopUp panel, int elemento) throws Exception{

        //Ya tendría un ID
        this.nuevoStock = this.listaStockActual.get(elemento);

        validarDatos(panel);
        //this.listaStockActual.remove(elemento);

        //this.servicePlanta.actualizarStock(this.nuevoStock.getPlanta(), this.nuevoStock.getInsumo(), this.nuevoStock.getCantidad(), this.nuevoStock.getPuntoPedido());
        this.servicePlanta.modificarStock(this.nuevoStock);
        this.listaStockActual.clear();
        this.listaStockActual.addAll(this.servicePlanta.getListaStock());


        //this.listaStockActual.add(elemento, this.nuevoStock);

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(ModificacionStockPopUp panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<>();
        Boolean[] camposValidos = {false, false};
        //this.nuevoStock = new Stock();

        try {
            //Planta e Insumo no son editables
            //Cantidad de Insumos
            if(panel.getTxtCantidad()!=null && !panel.getTxtCantidad().getText().equals("")) {
                this.nuevoStock.setCantidad(Integer.valueOf(panel.getTxtCantidad().getText()));
                camposValidos[0] = true;

            } else {
                camposVacios.add(0);
            }

            //Punto de Pedido
            if(panel.getTxtPuntoPedido()!=null && !panel.getTxtPuntoPedido().getText().equals("")) {
                this.nuevoStock.setPuntoPedido(Integer.valueOf(panel.getTxtPuntoPedido().getText()));
                camposValidos[1] = true;

            }else{
                camposVacios.add(1);
            }

            if(camposVacios.size()>0){
                panel.mostrarErrores(camposVacios);
                throw new Exception();

            }

        } catch(NumberFormatException nfe) {

            panel.mostrarErrores(camposValidos);
            throw new Exception("Uno de los campos no cumple el formato esperado.");

        }catch(Exception e){
            throw new Exception("Hubo un error al procesar los Datos");
        }
    }

    //Método para mostrar los valores iniciales del elemento a modificar. Invocado desde InsumoPanel, acción modificar
    public void inicializarPanelModificacion(ModificacionStockPopUp panel, int elemento) {

        Stock st = this.listaStockActual.get(elemento);

        panel.getTxtPlanta().setText(st.getPlanta().getNombre());
        panel.getTxtInsumo().setText(st.getInsumo().getDescripcion());

        panel.getTxtCantidad().setText((st.getCantidad().toString()));
        panel.getTxtPuntoPedido().setText(st.getPuntoPedido().toString());

        panel.setFila(elemento);

    }



    //ELIMINACIÓN:
    public void eliminar(int[] elementos){

        int i= elementos.length-1;

        while(i>=0){
            this.servicePlanta.eliminarStock(this.listaStockActual.get(elementos[i]));
            this.listaStockActual.remove(elementos[i]);
            i--;
        }

        this.listaStockActual.clear();
        this.listaStockActual.addAll(this.servicePlanta.getListaStock());

    }

    public void buscarPor(BuscarStockPanel panel) throws Exception{

        int planta;
        int insumo;

        if(panel.getTxtPlanta()!=null){
            planta = panel.getTxtPlanta().getSelectedIndex();
        }
        else{
            throw new Exception("Hubo un error al procesar los datos");
        }

        if(panel.getTxtInsumo()!= null){
            insumo = panel.getTxtInsumo().getSelectedIndex();
        }else{
            throw new Exception("Hubo un error al procesar los datos");
        }


        this.listaStockPuntoPedido.clear();
        this.listaStockPuntoPedido =  this.listarTodos().stream()
                .filter(s-> s.getCantidad()<=s.getPuntoPedido()).collect(Collectors.toList());

        if (planta == 0) {
            if (insumo != 0) {
                insumo--;
                Insumo in = this.listaInsumosActual.get(insumo); //Busca por insumo
                this.listaStockPuntoPedido = this.listaStockPuntoPedido.stream()
                        .filter(s -> s.getInsumo().getDescripcion().equals(in.getDescripcion()))
                        .collect(Collectors.toList());
            }
        }else{
            planta--;
            if (insumo == 0) {
                //Busco por planta
                Planta pl = this.listaPlantasActual.get(planta);
                System.out.println(pl);
                System.out.println(listaStockPuntoPedido);

                List<Stock> stock = this.listaStockPuntoPedido;
                this.listaStockPuntoPedido=new ArrayList<>();

                this.listaStockPuntoPedido = stock.stream()
                        .filter(s->s.getPlanta().getNombre().equals(pl.getNombre()))
                        .collect(Collectors.toList());
                System.out.println(listaStockPuntoPedido);

            } else {
                //Busco por ambos
                insumo--;
                Insumo in = this.listaInsumosActual.get(insumo);
                Planta pl = this.listaPlantasActual.get(planta);

                this.listaStockPuntoPedido = this.listaStockPuntoPedido.stream()
                        .filter(s->s.getPlanta().getNombre().equals(pl.getNombre()))
                        .filter(s -> s.getInsumo().getDescripcion().equals(in.getDescripcion()))
                        .collect(Collectors.toList());

            }
        }

    }


    //General:
    public String[] getPlantas(){

        this.listaPlantasActual.clear();
        try {
            this.listaPlantasActual.addAll(this.servicePlanta.getListaPlantas());
        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }

        ArrayList<String> plantas = new ArrayList<>();

        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        return plantas.toArray(new String[0]);
    }

    public String[] getInsumos() throws ElementoNoEncontradoException {

        ArrayList<String> insumo = new ArrayList<>();
        this.listaInsumosActual.clear();

        try {
            this.listaInsumosActual.addAll(serviceInsumo.getListaInsumos());
        } catch (ElementoNoEncontradoException e) {
            this.listaInsumosActual = new ArrayList<>();
            e.printStackTrace();
            return new String[]{""};

        }
        this.listaInsumosActual.stream()
                .map(Insumo::getDescripcion).forEach(insumo::add);

        return insumo.toArray(new String[0]);

    }

    public List<Stock> listarTodos(){

        this.listaStockActual.clear();
        this.listaStockActual.addAll(this.servicePlanta.getListaStock());

        return this.listaStockActual;
    }

    public String[] getPlantasBusq(){

        ArrayList<String> plantas = new ArrayList<>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        plantas.add(0, "Todas las plantas");
        return plantas.toArray(new String[0]);
    }

    public String[] getInsumosBusq(){

        ArrayList<String> insumo = new ArrayList<>();
        this.listaInsumosActual.stream()
                .map(Insumo::getDescripcion).forEach(insumo::add);

        insumo.add(0, "Todos los insumos");
        return insumo.toArray(new String[0]);
    }

    public Double calcularStockTotal(Insumo i){

        Optional<Double> op = this.listarTodos()
                .stream()
                .filter(s-> s.getInsumo().getDescripcion().equals(i.getDescripcion()))
                .map(s->Double.valueOf(s.getCantidad()))
                .reduce(Double::sum)
                ;

        return op.orElse(0.0);
    }

    public List<Stock> getListaStockPuntoPedido(){

        return this.listaStockPuntoPedido;
    }



}

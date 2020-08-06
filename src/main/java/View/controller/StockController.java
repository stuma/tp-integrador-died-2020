package View.controller;

import Model.Insumo;
import Model.InsumoLiquido;
import Model.Planta;
import Model.Stock;
import View.gui.stock.AgregarStockPanel;
import View.gui.stock.BuscarStockPanel;
import View.gui.stock.ModificacionStockPopUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockController {

    private static StockController controller;
    private Stock nuevoStock;
    private Planta p;
    private Insumo i;
    private List<Stock> listaStockActual;
    private List<Planta> listaPlantasActual;
    private List<Insumo> listaInsumosActual;

    private List<Stock> listaStockAux = new ArrayList<Stock>();
    //TODO Agregar variable de StockService
    //private StockService service;


    //Constructor privado
    private StockController(){

        this.listaStockActual = new ArrayList<Stock>();
        this.listaPlantasActual = new ArrayList<Planta>();
        this.listaInsumosActual = new ArrayList<Insumo>();

        this.nuevoStock = new Stock();
        //TODO Inicializar StockService
        //this.service = new StockService();
        //TODO Llamar desde el service para obtener las listas de plantas e insumos.

        //TODO Eliminar estas lineas
        Planta p1 = new Planta();
        p1.setId(1);
        p1.setNombre("Planta 1");

        Planta p2 = new Planta();
        p2.setId(2);
        p2.setNombre("Planta 2");
        this.listaPlantasActual.add(p1);
        this.listaPlantasActual.add(p2);

        Insumo i1 = new InsumoLiquido();
        i1.setId(1);
        i1.setDescripcion("Insumo1");
        i1.setUnidadMedida("Kg");
        i1.setCosto(20.1F);

        Insumo i2 = new InsumoLiquido();
        i2.setId(1);
        i2.setDescripcion("Insumo2");
        i2.setUnidadMedida("Kg");
        i2.setCosto(20.1F);
        this.listaInsumosActual.add(i1);
        this.listaInsumosActual.add(i2);

    }

    //Retorna una instancia de CamionController. Evita las multiples instancias.
    public static StockController getStockController(){

        if(controller==null){

            controller = new StockController();

        }

        return controller;
    }


    //ALTA DE STOCK:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public Insumo guardar(AgregarStockPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        //TODO Ver si ya existe un stock con la relación planta-stock-insumo. Agrego variable Planta en Stock.
        Optional<Stock> optStock = this.listaStockActual.stream().
                filter(s -> s.getPlanta().getNombre().equals(this.p.getNombre()) && s.getInsumo().getDescripcion().equals(this.i.getDescripcion()))
                .findFirst();
        if(optStock.isPresent()){
            throw new Exception("Ya existe un stock para la planta y el insumo seleccionado. Para modificar los valores, busque en la tabla y oprima Modificar");
        }

        this.nuevoStock.setInsumo(i);
        this.nuevoStock.setPlanta(p); //TODO El service debería actualizar la lista de stock de p

        //TODO Llamar al service para almacenar el Stock
        //camionService.crearCamion(c);
        //this.listaCamionesActual.clear();
        //this.listaCamionesActual.addAll(camionService.buscarTodos());

        //TODO Eliminar estas lineas
        this.p.getListastockInsumos().add(this.nuevoStock);
        this.listaStockActual.add(this.nuevoStock);

        return null;
    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(AgregarStockPanel panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false, false};
        this.nuevoStock = new Stock();

        try {

            //Obtiene la planta correspondiente al item seleccionado
            //TODO debe tener un equals en base al nombre de la planta.
            // Creo que no es necesario si en el getStringPlantas le hago un map a la listaActualPlantas
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



        System.out.println("Llama a validad datos");
        validarDatos(panel);
        System.out.println("Validación Exitosa");
        this.listaStockActual.remove(elemento);

        //TODO Invocar al service con stock modificado
        //camionService.crearCamion(camion);
        //this.listaCamionesActual.clear();
        //this.lista.addAll(camionService.buscarTodos());


        this.listaStockActual.add(elemento, this.nuevoStock);

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(ModificacionStockPopUp panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
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
            this.listaStockActual.remove(elementos[i]);
            i--;
        }


        //TODO Invocar a Service con elemento seleccionado
        //this.listaCamionesActual.clear();
        //this.service.buscarTodos();

    }


    //BUSQUEDA: (Filtro de tabla)
    public void buscarTodos(){

        //TODO Llamada al service para obtener todas las entidades de Stock existentes y actualizar el atributo listaStock

    }

    public void buscarPorPlanta(BuscarStockPanel panel) throws Exception{

        this.listaStockActual.addAll(this.listaStockAux);
        this.listaStockAux.clear();
        ArrayList<Stock> lista = new ArrayList<Stock>();
        String planta = this.listaPlantasActual.get(panel.getTxtPlanta().getSelectedIndex()-1).getNombre();
        System.out.println(planta);
        //TODO Llamar al Service para obtener todas las plantas.

        try{

            if(panel.getTxtPlanta()!=null){
               this.listaStockActual.stream()
                        .filter((Stock s) -> s.getPlanta().getNombre().equals(planta))
                        .forEach(lista::add);
            }else{

                throw new Exception();

            }
        } catch(Exception e){
            throw new Exception("Hubo un error al procesar los Datos");
        }

        this.listaStockAux.addAll(this.listaStockActual);
        this.listaStockActual.clear();
        this.listaStockActual.addAll(lista);
        System.out.println(listaStockActual);

    }

    public void buscarPorInsumo(BuscarStockPanel panel) throws Exception{

        //TODO Invocar al service para buscar por insumo
        List<Stock> lista;
        try{

            if(panel.getTxtInsumo()!=null){
                lista = this.listaStockActual.stream()
                        .filter(s -> s.getInsumo().getDescripcion().equals(this.listaInsumosActual.get(panel.getTxtInsumo().getSelectedIndex()).getDescripcion()))
                        .collect(Collectors.toList());
            }else{

                throw new Exception();

            }
        } catch(Exception e){
            throw new Exception("Hubo un error al procesar los Datos");
        }

        System.out.println(lista);
        this.listaStockActual = lista;

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

        if (planta == 0) {
            if (insumo == 0) {
                buscarTodos();
            } else {
                Insumo in = this.listaInsumosActual.get(insumo);
                //TODO service.buscarPorInsumo(in);
            }
        }else{

            if (insumo == 0) {
                //Busco por planta
                Planta pl = this.listaPlantasActual.get(planta);
                //TODO service.buscarPorPlanta(pl);

            } else {
                //Busco por ambos
                Insumo in = this.listaInsumosActual.get(insumo);
                Planta pl = this.listaPlantasActual.get(planta);
                //TODO service.buscarPor(pl, in);

            }
        }



    }


    //General:
    public String[] getPlantas(){

        ArrayList<String> plantas = new ArrayList<String>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        return plantas.toArray(new String[0]);
    }

    public String[] getInsumos(){

        ArrayList<String> insumo = new ArrayList<String>();
        this.listaInsumosActual.stream()
                .map(Insumo::getDescripcion).forEach(insumo::add);

        return insumo.toArray(new String[0]);

    }

    public List<Stock> listarTodos(){
        //TODO llamar desde el service para obtener todos los elementos
        return this.listaStockActual;
    }

    public String[] getPlantasBusq(){

        ArrayList<String> plantas = new ArrayList<String>();
        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        plantas.add(0, "Todas las plantas");
        return plantas.toArray(new String[0]);
    }

    public String[] getInsumosBusq(){

        ArrayList<String> insumo = new ArrayList<String>();
        this.listaInsumosActual.stream()
                .map(Insumo::getDescripcion).forEach(insumo::add);

        insumo.add(0, "Todos los insumos");
        return insumo.toArray(new String[0]);
    }

}

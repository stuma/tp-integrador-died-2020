package View.guiController;

import Model.*;
import Service.*;
import View.gui.ordenes.AgregarOrdenPanel;
import View.gui.ordenes.ProcesarOrdenPanel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrdenPedidoGuiController {

    private static OrdenPedidoGuiController controller;

    private OrdenPedidoService service;
    private InsumosService serviceInsumo;
    private PlantaService servicePlanta;
    private GrafoService serviceGrafo;

    //Alta de Orden de pedido
    private OrdenPedido nuevaOrden;
    private ArrayList<Item> listaItems;
    private Item nuevoItem;
    private List<Planta> listaPlantasActual;
    private List<Insumo> listaInsumosActual;

    //Procesar Orden de pedido
    private List<OrdenPedido> listaOrdenesCreadasActual;
    private List<OrdenPedido> listaOrdenesProcesadasActual;
    private List<List<Planta>> caminoCortoHs;
    private List<List<Planta>> caminoCortoKm;
    private List<Item> listaAuxItems;
    private List<Planta> caminoHs;
    private List<Planta> caminokm;
    private Planta plantaOrigen;

    //Constructor privado
    private OrdenPedidoGuiController() throws Exception{

        this.service = new OrdenPedidoService();
        this.serviceInsumo = new InsumosService();
        this.servicePlanta = new PlantaService();
        this.serviceGrafo = GrafoService.getGrafoService();

        this.listaItems = new ArrayList<>();
        this.listaOrdenesCreadasActual = (this.service.getListaOrdenPedido(0) == null)? new ArrayList<>() : this.service.getListaOrdenPedido(0);
        this.listaInsumosActual = (this.serviceInsumo.getListaInsumos()==null) ? new ArrayList<>() : this.serviceInsumo.getListaInsumos();
        this.listaPlantasActual = (this.servicePlanta.getListaPlantas()==null)? new ArrayList<>() : this.servicePlanta.getListaPlantas();
        this.listaOrdenesProcesadasActual = (this.service.getListaOrdenPedido(1)==null)? new ArrayList<>() : this.service.getListaOrdenPedido(1);


        this.nuevaOrden = new OrdenPedido();
        this.listaAuxItems = new ArrayList<>();

    }

    //Retorna una instancia de CamionController. Evita las multiples instancias.
    public static OrdenPedidoGuiController getOrdenPedidoController() throws Exception {

        if(controller==null){

            controller = new OrdenPedidoGuiController();

        }

        return controller;

    }

    private void inicializarCaminos(){

        this.caminoCortoHs = new ArrayList<>();
        this.caminoCortoKm = new ArrayList<>();

        //Inicializo caminos de ruta mas corta hs.
        for (int i = 0; i < this.listaPlantasActual.size(); i++) {

            Planta p = this.listaPlantasActual.get(i);

            //Llamo al service para obtener lista de rutas desde origen a destino:
            List<Planta> camino = this.serviceGrafo.dijkstraHora(p, this.nuevaOrden.getPlantaDestino());
            //Agrego en el índice correspondiente
            this.caminoCortoHs.add(i, camino);


        }

        //Caminos de ruta más corta km.
        for(int i = 0; i < this.listaPlantasActual.size(); i++){

            Planta p = this.listaPlantasActual.get(i);

            //Llamo al service para obtener lista de rutas desde origen a destino:
            List<Planta> camino = this.serviceGrafo.dijkstraKm(p, this.nuevaOrden.getPlantaDestino());

            //Agrego en el índice correspondiente
            this.caminoCortoKm.add(i, camino);

        }

    }


    //ALTA DE ORDEN:
    //Metodos para Agregar Item
        public void agregarItem(AgregarOrdenPanel panel) throws Exception{

            validarDatosItem(panel);

            Optional<Item> in = this.listaItems.stream()
                    .filter(item -> item.getInsumo().getDescripcion().equals(this.nuevoItem.getInsumo().getDescripcion()))
                    .findAny();

            if(in.isPresent()){
                in.get().setCantidad(this.nuevoItem.getCantidad()); //Modifica la cantidad ya existente.
                return;
            }

            //Si no, agrega un nuevo Item
            this.listaItems.add(this.nuevoItem);
        }

        private void validarDatosItem(AgregarOrdenPanel panel) throws Exception {

            ArrayList<Integer> camposVacios = new ArrayList<>();
            Boolean[] camposValidos = {true, false};

            this.nuevoItem = new Item();

            try {

                //Obtengo insumo basandome en el nombre del insumo
                if(panel.getTxtInsumo()!=null) {

                    this.nuevoItem.setInsumo(this.listaInsumosActual.get(panel.getTxtInsumo().getSelectedIndex()));

                }else{
                    camposVacios.add(2);
                }


                if(panel.getTxtCantidad()!=null && !panel.getTxtCantidad().getText().equals("")){

                    this.nuevoItem.setCantidad(Integer.valueOf(panel.getTxtCantidad().getText()));
                    camposValidos[1] = true;
                }else{
                    camposVacios.add(1);
                }

                if(camposVacios.size()>0){

                    System.out.println("Campos vacios");
                    panel.mostrarErrores(camposVacios);
                    throw new Exception("Hubo un error al procesar los Datos");

                }

            } catch(NumberFormatException nfe) {

                panel.mostrarErrores(camposValidos);
                System.out.println("Campo no válido");
                throw new Exception("Uno de los campos no cumple el formato esperado.");

            }catch(Exception e){
                System.out.println("Entra al catch");
                System.out.println(e.getMessage());
                throw new Exception("Hubo un error al procesar los Datos");
            }
        }

    //Metodos para Agregar Orden:
        public void guardar(AgregarOrdenPanel panel) throws Exception{

            validarDatosAlta(panel);

            this.service.generarOrdenPedido(this.nuevaOrden);

            this.listaOrdenesCreadasActual.clear();
            this.listaOrdenesCreadasActual.addAll(this.service.getListaOrdenPedido(0));

        }

        private void validarDatosAlta(AgregarOrdenPanel panel) throws Exception {

            ArrayList<Integer> camposVacios = new ArrayList<>();
            Boolean[] camposValidos = {false, true};

            this.nuevaOrden = new OrdenPedido();

            try {

                //Planta:
                if(panel.getTxtPlanta()!=null) {
                    this.nuevaOrden.setPlantaDestino(this.listaPlantasActual.get(panel.getTxtPlanta().getSelectedIndex()));
                }else{
                    camposVacios.add(2);
                }

                //Fecha de entrega:
                if(panel.getTxtFecha()!=null && !panel.getTxtFecha().getText().equals("")) {

                    LocalDate fecha = LocalDate.parse(panel.getTxtFecha().getText(), panel.getDf());
                    this.nuevaOrden.setFechaSolicitud(fecha);
                    camposValidos[0] = true;

                }else{
                    camposVacios.add(0);
                }

                //Lista de Items:
                if(this.listaItems.isEmpty()){
                    camposVacios.add(3);
                    throw new Exception();

                }else{

                    ArrayList<Item> items = new ArrayList<>(this.listaItems);
                    this.nuevaOrden.setListaItems(items);

                }

                if(camposVacios.size()>0){

                    panel.mostrarErrores(camposVacios);
                    throw new Exception("Hubo un error al procesar los Datos");

                }

            } catch(NumberFormatException nfe) {

                panel.mostrarErrores(camposValidos);
                throw new Exception("Uno de los campos no cumple el formato esperado.");

            }catch(Exception e){

                if(camposVacios.contains(3)){
                    throw new Exception("Lista de Items Vacía. Debe agregar al menos un item para crear la orden");
                }
                else{
                    panel.mostrarErrores(camposValidos);
                    throw new Exception("Hubo un error al procesar los Datos");
                }

            }
    }

    //General:
    public List<Item> getListaItems(){
        return this.listaItems;
    }

    public String[] getPlantas(){

        this.listaPlantasActual.clear();
        ArrayList<String> plantas = new ArrayList<>();

        try {
            this.listaPlantasActual.addAll(this.servicePlanta.getListaPlantas());

        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
/*            this.listaPlantasActual = new ArrayList<>();
            return plantas.toArray(new String[0]);*/
        }

        this.listaPlantasActual.stream()
                .map(Planta::getNombre).forEach(plantas::add);

        return plantas.toArray(new String[0]);

    }

    public String[] getInsumos(){

        this.listaInsumosActual.clear();
        ArrayList<String> insumo = new ArrayList<>();

        try {
            this.listaInsumosActual.addAll(this.serviceInsumo.getListaInsumos());
        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
            return insumo.toArray(new String[0]);
        }

        this.listaInsumosActual.stream()
                .map(Insumo::getDescripcion).forEach(insumo::add);

        return insumo.toArray(new String[0]);

    }

    public void vaciarTablaItems(){
        this.listaItems.clear();
    }



    //PROCESAR ORDEN:
    //Mostrar Detalle de pedido
    public void mostrarDetallePedido(ProcesarOrdenPanel panel, int fila){

        this.nuevaOrden = this.listaOrdenesCreadasActual.get(fila);
        panel.getTxtFecha().setText( this.nuevaOrden.getFechaSolicitud().toString());
        panel.getTxtPlantaDestino().setText( this.nuevaOrden.getPlantaDestino().getNombre());

        this.listaAuxItems.clear();
        this.listaAuxItems.addAll( this.nuevaOrden.getListaItems());

    }

    //Procesar pedido
    public void validarPantalla1(ProcesarOrdenPanel panel, int i) throws Exception {

        this.nuevaOrden = this.listaOrdenesCreadasActual.get(i);
        List<Planta> plantas = this.getListaPlantas();

        if(plantas.isEmpty()){

            this.service.cancelarPedido(this.nuevaOrden.getId());
            this.listaOrdenesProcesadasActual.clear();
            this.listaOrdenesProcesadasActual.addAll(this.service.getListaOrdenPedido(1));
            throw new Exception("No existe planta que pueda cumplir con la demanda de insumos. El pedido ha sido cancelado");

        }

        //Si existe, pasa a la pantalla 2.

    }

    //Para la validación de Procesar Pedido
    public List<Planta> getListaPlantas(){

        this.listaPlantasActual.clear();
        try {
            this.listaPlantasActual.addAll(this.servicePlanta.getListaPlantas());
        } catch (ElementoNoEncontradoException e) {
            this.listaPlantasActual = new ArrayList<>();
        }

        List<Planta> aux = this.listaPlantasActual.stream()
                .filter(p->{
                    int puede = 0;
                    if(p.getListaStockInsumos()==null || p.getListaStockInsumos().isEmpty()) return false;

                    for(Stock stock: p.getListaStockInsumos()){
                        for(Item i : this.nuevaOrden.getListaItems()){
                            if(i.getInsumo().getDescripcion().equals(stock.getInsumo().getDescripcion())){
                                if(i.getCantidad()>stock.getCantidad()){
                                    return false;
                                }else{
                                    puede++;
                                }
                            }
                        }

                    }
                    return puede>=this.nuevaOrden.getListaItems().size(); })
                .collect(Collectors.toList());

        this.listaPlantasActual.clear();
        this.listaPlantasActual.addAll(aux);
        inicializarCaminos();
        return this.listaPlantasActual;
    }

    public void mostrarDetallePlantas(ProcesarOrdenPanel panel, int fila){

        //inicializarCaminos();
        this.plantaOrigen = this.listaPlantasActual.get(fila);

        int des = 0;
        try {
            des = this.servicePlanta.getListaPlantas().indexOf(this.nuevaOrden.getPlantaDestino());

        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }

        Planta destino = new Planta();
        try {
            destino = this.servicePlanta.getListaPlantas().get(des);

        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }

        StringBuilder hs = new StringBuilder();
        this.caminoHs=new ArrayList<>();
        System.out.println(this.plantaOrigen);
        this.caminoHs.addAll(this.serviceGrafo.dijkstraHora(this.plantaOrigen, destino));

        System.out.println(this.caminoHs);


        for (int i=0; i<this.caminoHs.size()-1; i++){

            hs.append(this.caminoHs.get(i).getNombre()).append("-");

        }
        hs.append(this.caminoHs.get(this.caminoHs.size() - 1).getNombre());
        panel.getTxtRutaElegidaHs().setVisible(false);
        panel.getTxtRutaElegidaHs().setText(hs.toString());
        panel.getTxtRutaElegidaHs().setVisible(true);

        StringBuilder km = new StringBuilder();
        this.caminokm = this.caminoCortoKm.get(fila);
        for (int i=0; i<this.caminokm.size()-1; i++){

            km.append(this.caminokm.get(i).getNombre()).append("-");

        }
        km.append(this.caminokm.get(this.caminokm.size() - 1).getNombre());
        panel.getTxtRutaElegidaKm().setText(km.toString());
        panel.getTxtPlantaOrigen().setText(this.plantaOrigen.getNombre());

    }

    //Modificar orden: Asignar Ruta
    public void asignarRuta() throws Exception{

        if(this.caminoCortoKm==null || this.caminoCortoHs==null || this.plantaOrigen==null){

            throw new Exception("No se ha seleccionado una planta de origen para el pedido");

        }

        this.nuevaOrden.setPlantaOrigen(this.plantaOrigen);
        this.nuevaOrden.setCamino((ArrayList<Planta>) this.serviceGrafo.dijkstraHora(this.plantaOrigen, this.nuevaOrden.getPlantaDestino()));

        this.service.procesarOrden(this.nuevaOrden);

        //Actualizo ambas listas:
        this.listaOrdenesCreadasActual.clear();
        this.listaOrdenesProcesadasActual.clear();

        this.listaOrdenesCreadasActual.addAll(this.service.getListaOrdenPedido(0));
        this.listaOrdenesProcesadasActual.addAll(this.service.getListaOrdenPedido(1));

        this.listaItems.clear();
        this.listaAuxItems.clear();


    }


    //General
    //Retorna la lista con todos los pedidos en estado Creado para la pantalla PROCESAR ORDEN
    public List<OrdenPedido> getPedidosCreados(){

        this.listaOrdenesCreadasActual.clear();
        try {
            this.listaOrdenesCreadasActual.addAll((service.getListaOrdenPedido(0)==null)? new ArrayList<>() : service.getListaOrdenPedido(0));
        } catch (ElementoNoEncontradoException e) {
            e.printStackTrace();
        }

        return this.listaOrdenesCreadasActual;

    }

    public List<Item> getItems(){

        return this.listaAuxItems;

    }

    public void vaciarListaItems(){

        this.listaAuxItems.clear();

    }

    public List<List<Planta>> getCaminosHs(){

        return this.caminoCortoHs;
    }

    public List<List<Planta>> getCaminosKm(){

        return this.caminoCortoKm;
    }

    public float getHsCamino(Planta origen, Planta destino){

        return this.serviceGrafo.distanciaHora(origen, destino);

    }

    public float getKmCamino(Planta origen, Planta destino){

        return this.serviceGrafo.distanciakm(origen, destino);

    }

    //ENTREGAR PEDIDO
    public List<OrdenPedido> pedidosProcesados() throws ElementoNoEncontradoException {

        this.listaOrdenesProcesadasActual.clear();
        this.listaOrdenesProcesadasActual.addAll((this.service.getListaOrdenPedido(1)==null)? new ArrayList<>() : this.service.getListaOrdenPedido(1));

        return this.listaOrdenesProcesadasActual;
    }

    public void entregarPedido(int fila){

        this.nuevaOrden = this.listaOrdenesProcesadasActual.get (fila);

        this.service.entregarPedido(this.nuevaOrden.getId());

        //Elimino de la tabla dicho pedido, ya que fue entregado
        this.listaOrdenesProcesadasActual.remove(fila);

    }


}

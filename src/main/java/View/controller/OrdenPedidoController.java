package View.controller;

import Model.*;
import View.gui.ordenes.AgregarOrdenPanel;
import View.gui.ordenes.ProcesarOrdenPanel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenPedidoController {

    private static OrdenPedidoController controller;
    //TODO Agregar variable de CamionService
    //private OrdenPedido service;

    //Alta de Orden de pedido
    private OrdenPedido nuevaOrden;
    private ArrayList<Item> listaItems;
    private Item nuevoItem;
    private List<Planta> listaPlantasActual;
    private List<Insumo> listaInsumosActual;

    //Procesar Orden de pedido
    private List<OrdenPedido> listaOrdenesActual;
    private List<List<Planta>> caminoCortoHs;
    private List<List<Planta>> caminoCortoKm;
    private List<Item> listaAuxItems;
    private List<Planta> caminoHs;
    private List<Planta> caminokm;
    private Planta plantaOrigen;

    //Constructor privado
    private OrdenPedidoController(){

        //TODO Inicializar serice
        //this.service = new CamionService();

        this.listaItems = new ArrayList<Item>();
        this.listaOrdenesActual = new ArrayList<OrdenPedido>();
        this.listaInsumosActual = new ArrayList<Insumo>(); //TODO Invocar al service
        this.listaPlantasActual = new ArrayList<Planta>(); //TODO Invocar al service.
        inicializarCaminos();
        this.nuevaOrden = new OrdenPedido();
        this.listaAuxItems = new ArrayList<Item>();

        //TODO Eliminar esto
        InsumoGeneral in = new InsumoGeneral();
        in.setId(1);
        in.setUnidadMedida("m3");
        in.setCosto(25.0F);
        in.setDescripcion("Insumo General");
        in.setPeso(23F);

        InsumoLiquido in2 = new InsumoLiquido();
        in2.setId(1);
        in2.setUnidadMedida("Kg");
        in2.setCosto(25.0F);
        in2.setDescripcion("Insumo Liquido");
        in2.setDensidad(24F);

        this.listaInsumosActual.add(in);
        this.listaInsumosActual.add(in2);
        //TODO Eliminar estas lineas
        Planta p1 = new Planta();
        p1.setId(1);
        p1.setNombre("Planta 1");

        Planta p2 = new Planta();
        p2.setId(2);
        p2.setNombre("Planta 2");
        this.listaPlantasActual.add(p1);
        this.listaPlantasActual.add(p2);
    }

    //Retorna una instancia de CamionService. Evita las multiples instancias.
    public static OrdenPedidoController getOrdenPedidoController(){

        if(controller==null){

            controller = new OrdenPedidoController();

        }

        return controller;

    }

    private void inicializarCaminos(){

        this.caminoCortoHs = new ArrayList<List<Planta>>();
        this.caminoCortoKm = new ArrayList<List<Planta>>();
        //Inicializo caminos de ruta mas corta hs.
        //TODO Implementar esto
/*        for(Planta p: this.listaPlantasActual){

            //Llamo al service para obtener lista de rutas desde origen a destino:
            //List<Planta> camino = this.service.getRutaMasCortaHs(p, this.nuevaOrden.getPlantaDestino())
            //this.caminoCortoHs.add(camino);
            //La lista quedaría ordenada segun las plantas actuales.

        }

        for(Planta p: this.listaPlantasActual){

            //Llamo al service para obtener lista de rutas desde origen a destino:
            //List<Planta> camino = this.service.getRutaMasCortaKm(p, this.nuevaOrden.getPlantaDestino())
            //this.caminoCortoKm.add(camino);
            //La lista quedaría ordenada segun las plantas actuales.

        }*/

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

            this.listaItems.add(this.nuevoItem);
        }

        private void validarDatosItem(AgregarOrdenPanel panel) throws Exception {

            ArrayList<Integer> camposVacios = new ArrayList<Integer>();
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

            //TODO Llamar al service para ver si existe una orden con misma fecha, misma planta y mismo insumo.
                //Arrojar excepción en caso de que exista.
            //Eliminar esto
            this.nuevaOrden.setId(1);


            this.listaOrdenesActual.add(this.nuevaOrden);

            System.out.println(this.listaOrdenesActual);
        }

        private void validarDatosAlta(AgregarOrdenPanel panel) throws Exception {

            ArrayList<Integer> camposVacios = new ArrayList<Integer>();
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
                    //TODO Ver si es fecha de entrega o fecha de solicitud.
                    this.nuevaOrden.setFechaEntrega(fecha);
                    camposValidos[0] = true;

                }else{
                    camposVacios.add(0);
                }

                //Lista de Items:
                if(this.listaItems.isEmpty()){
                    camposVacios.add(3);
                    throw new Exception();

                }else{

                    ArrayList<Item> items = new ArrayList<Item>(this.listaItems);
                    this.nuevaOrden.setListaItems(items);

                }

                if(camposVacios.size()>0){

                    System.out.println("Campos vacios");
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

    //TODO llamar al service que obtenga todas las plantas disponibles. Asigne a la variable global y luego usar los filter
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

    public void vaciarTablaItems(){
        this.listaItems.clear();
    }



    //PROCESAR ORDEN:
    //Mostrar Detalle de pedido
    public void mostrarDetallePedido(ProcesarOrdenPanel panel, int fila){

        this.nuevaOrden = this.listaOrdenesActual.get(fila);
        panel.getTxtFecha().setText( this.nuevaOrden.getFechaEntrega().toString());
        panel.getTxtPlantaDestino().setText( this.nuevaOrden.getPlantaDestino().getNombre());
        this.listaAuxItems = new ArrayList<Item>( this.nuevaOrden.getListaItems());
        this.listaAuxItems.clear();
        this.listaAuxItems.addAll(this.nuevaOrden.getListaItems());

        System.out.println( this.nuevaOrden.getListaItems());
        System.out.println(this.listaAuxItems);
       // panel.actualizarTablaItem(0, this.listaAuxItems.size());

    }

    public void validarPantalla1(ProcesarOrdenPanel panel){

        //TODO Llamar a service para verificar que exista al menos una planta para cumplir el pedido.
        //Si no existe: arroja excepción. Service debería cancelar el pedido
        //Si existe, pasa a la pantalla 2.



    }

    public void mostrarDetallePlantas(ProcesarOrdenPanel panel, int fila){

        this.plantaOrigen = this.listaPlantasActual.get(fila);
        StringBuilder hs = new StringBuilder();
        this.caminoHs = this.caminoCortoHs.get(fila);
        for (int i=0; i<this.caminoHs.size()-1; i++){

            hs.append(this.caminoHs.get(i).getNombre()).append("-");

        }
        hs.append(this.caminoHs.get(this.caminoHs.size() - 1).getNombre());
        panel.getTxtRutaElegidaHs().setText(hs.toString());


        StringBuilder km = new StringBuilder();
        this.caminokm = this.caminoCortoKm.get(fila);
        for (int i=0; i<this.caminokm.size()-1; i++){

            km.append(this.caminokm.get(i).getNombre()).append("-");

        }
        km.append(this.caminokm.get(this.caminokm.size() - 1).getNombre());

        panel.getTxtRutaElegidaKm().setText(km.toString());


    }

    //Modificar orden: Asignar Ruta
    public void asignarRuta() throws Exception{

        if(this.caminoCortoKm==null || this.caminoCortoHs==null || this.plantaOrigen==null){

            throw new Exception("No se ha seleccionado una planta de origen para el pedido");

        }

        this.nuevaOrden.setPlantaOrigen(this.plantaOrigen);
        //Creo que no se asigna ningun camino
        //TODO Llamar al service para actualizar nuevaOrden
        //TODO Llamar al service para actualizar la lista de ordenes en estado pendiente
        //TODO Set: Camion, Calcular Costo de Envio (si no se hace al crear orden), set Fecha de solicitud (o de entrega)

    }


    //General
    //Retorna la lista con todos los pedidos
    public List<OrdenPedido> getPedidos(){

        System.out.println(this.listaOrdenesActual);

        //TODO Llamar al service que obtiene la lista de todas las ordenes
        //this.listaOrdenesActual.clear();
        //this.listaOrdenesActual.addAll(service.buscarTodos());


        return this.listaOrdenesActual;

    }

    public List<Item> getItems(){

        System.out.println(this.listaAuxItems);

        //TODO Llamar al service que obtiene la lista de todas las ordenes
        //this.listaOrdenesActual.clear();
        //this.listaOrdenesActual.addAll(service.buscarTodos());


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

    public List<Planta> getListaPlantas(){

        //TODO Solo las que pueden cumplir la demanda del pedido. Llamar al service de nuevo y volver a inicializar la lista.
        //return this.listaPlantasActual;
        return new ArrayList<>();
    }

    //ENTREGAR PEDIDO
    public List<OrdenPedido> pedidosProcesados(){

        //TODO Llamar al service para asignar pedidos procesados
        //Asignarselo a la variable this.listaOrdenesActual

        return this.listaOrdenesActual;
    }

    public void entregarPedido(int fila){

        this.nuevaOrden = this.listaOrdenesActual.get (fila);
        //TODO Invoco al service para modificar la orden

        //Elimino de la tabla dicho pedido, ya que fue entregado
        this.listaOrdenesActual.remove (fila);

    }

}

package View.controller;

import Model.Insumo;
import Model.InsumoGeneral;
import Model.InsumoLiquido;
import View.gui.insumos.InsumoPanel;
import View.gui.insumos.ModificacionInsumoPopUp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsumoController {

    private static InsumoController controller;
    private Insumo nuevoInsumo;
    private List<Insumo> listaInsumosActual;

    //TODO Agregar variable de InsumoService
    //private InsumoService service;


    //Constructor
    //Constructor privado
    private InsumoController(){

        this.listaInsumosActual = new ArrayList<Insumo>();
        this.nuevoInsumo = new Insumo();
        //TODO Inicializar InsumoService
        //this.service = new InsumoService();

    }

    //Retorna una instancia de InsumoController. Evita las multiples instancias.
    public static InsumoController getInsumoController(){

        if(controller==null){

            controller = new InsumoController();

        }

        return controller;

    }



    //ALTA DE INSUMO:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public Insumo guardar(InsumoPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        //TODO Llamar al service para almacenar el Insumo
        //camionService.crearCamion(c);
        //this.listaCamionesActual.clear();
        //this.listaCamionesActual.addAll(camionService.buscarTodos());

        //TODO Eliminar estas lineas
        this.listaInsumosActual.add(nuevoInsumo);

        return null;
    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(InsumoPanel panel) throws Exception {
        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false, false, false};

        try {
            //Verifica primero el tipo de insumo asi a nuevoInsumo se le asigna el tipo indicado
            if(panel.getTxtTipo()!=null) {
                System.out.println(panel.getTxtTipo().getSelectedIndex());

               switch (panel.getTxtTipo().getSelectedIndex()){
                   case 0: this.nuevoInsumo = new InsumoGeneral();break;
                   case 1: this.nuevoInsumo = new InsumoLiquido();break;
                   default: camposVacios.add(1);break;
               }
            }else{
                camposVacios.add(4);
            }

            if(panel.getTxtDescripcion()!=null && !panel.getTxtDescripcion().getText().equals("")) {
                this.nuevoInsumo.setDescripcion(panel.getTxtDescripcion().getText());
                camposValidos[0] = true;
            } else {
                camposVacios.add(0);
            }

            if(panel.getTxtUnidad()!=null){

                this.nuevoInsumo.setUnidadMedida((String)panel.getTxtUnidad().getSelectedItem());

            }else{
                camposVacios.add(2);
            }

            if(panel.getTxtCostoU()!=null && !panel.getTxtCostoU().getText().equals("")) {
                this.nuevoInsumo.setCosto(Float.valueOf(panel.getTxtCostoU().getText()));

                camposValidos[1] = true;
            }else{
                camposVacios.add(1);
            }

            //Densidad solo se valida si tipo de insumo es Liquido.
            if(this.nuevoInsumo instanceof InsumoLiquido){

                if(panel.getTxtDensidad()!=null && !panel.getTxtDensidad().getText().equals("")) {

                    ((InsumoLiquido) this.nuevoInsumo).setDensidad(Float.valueOf(panel.getTxtDensidad().getText()));
                    camposValidos[2] = true;

                    //TODO Agregar a InsumoLiquido un metodo que calcule su peso.

                }else{
                    camposVacios.add(2);
                }

            }/*else {
                if (this.nuevoInsumo instanceof InsumoGeneral) {

                    if (panel.getTxtPeso() != null && !panel.getTxtPeso().getText().equals("")) {

                        ((InsumoGeneral) this.nuevoInsumo).setPeso(Float.valueOf(panel.getTxtPeso().getText()));
                        camposValidos[2] = true;

                    } else {
                        camposVacios.add(2);
                    }

                }
            }*/
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
    public void modificar(ModificacionInsumoPopUp panel, int elemento) throws Exception{

        //Ya tendría un ID
        this.nuevoInsumo = this.listaInsumosActual.get(elemento);
        //

        System.out.println("Llama a validad datos");
        validarDatos(panel);
        System.out.println("Validación Exitosa");
        this.listaInsumosActual.remove(elemento);

        //TODO Invocar al service con camión modificado
        //camionService.crearCamion(camion);
        //this.listaCamionesActual.clear();
        //this.lista.addAll(camionService.buscarTodos());

        this.listaInsumosActual.add(elemento, nuevoInsumo);

    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(ModificacionInsumoPopUp panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false, false, false};

        Insumo aux = this.nuevoInsumo;

        try {
            //Verifica primero el tipo de insumo. Si cambia de tipo de dato, debe cambiar la instancia.
            if(panel.getTxtTipo()!=null) {
                switch (panel.getTxtTipo().getSelectedIndex()){
                    case 0:
                        if(this.nuevoInsumo instanceof InsumoLiquido){
                            //Cambia de Tipo de Dato. Migro a otro objeto el contenido
                            this.nuevoInsumo = new InsumoGeneral();
                            this.nuevoInsumo.setCosto(aux.getCosto());
                            this.nuevoInsumo.setUnidadMedida(aux.getUnidadMedida());
                            this.nuevoInsumo.setDescripcion(aux.getDescripcion());
                            this.nuevoInsumo.setId(aux.getId());
                        }
                        break;
                    case 1:
                        if(this.nuevoInsumo instanceof InsumoGeneral){
                            //Cambia de Tipo de Dato. Migro a otro objeto el contenido
                            this.nuevoInsumo = new InsumoLiquido();
                            this.nuevoInsumo.setCosto(aux.getCosto());
                            this.nuevoInsumo.setUnidadMedida(aux.getUnidadMedida());
                            this.nuevoInsumo.setDescripcion(aux.getDescripcion());
                            this.nuevoInsumo.setId(aux.getId());

                        }
                        break;

                    default: camposVacios.add(3);break;
                }
            }else{
                camposVacios.add(3);
            }

            //Asigno a aux la descripción.
            if(panel.getTxtDescripcion()!=null && !panel.getTxtDescripcion().getText().equals("")) {
                this.nuevoInsumo.setDescripcion(panel.getTxtDescripcion().getText());
                camposValidos[0] = true;
            } else {
                camposVacios.add(0);
            }

            if(panel.getTxtUnidad()!=null){

                this.nuevoInsumo.setUnidadMedida((String)panel.getTxtUnidad().getSelectedItem());

            }else{
                camposVacios.add(2);
            }

            if(panel.getTxtCostoU()!=null && !panel.getTxtCostoU().getText().equals("")) {
                this.nuevoInsumo.setCosto(Float.valueOf(panel.getTxtCostoU().getText()));

                camposValidos[1] = true;
            }else{
                camposVacios.add(1);
            }

            //Densidad solo se valida si tipo de insumo es Liquido.
            if(this.nuevoInsumo instanceof InsumoLiquido){

                if(panel.getTxtDensidad()!=null && !panel.getTxtDensidad().getText().equals("")) {

                    ((InsumoLiquido) this.nuevoInsumo).setDensidad(Float.valueOf(panel.getTxtDensidad().getText()));
                    camposValidos[2] = true;

                    //TODO Agregar a InsumoLiquido un metodo que calcule su peso.

                }else{
                    camposVacios.add(2);
                }

            }/*else {
                if (this.nuevoInsumo instanceof InsumoGeneral) {

                    if (panel.getTxtPeso() != null && !panel.getTxtPeso().getText().equals("")) {

                        ((InsumoGeneral) this.nuevoInsumo).setPeso(Float.valueOf(panel.getTxtPeso().getText()));
                        camposValidos[2] = true;

                    } else {
                        camposVacios.add(2);
                    }

                }
            }*/
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

    //Método para mostrar los valores iniciales del elemento a modificar. Invocado desde InsumoPanel, acción modificar
    public void inicializarPanelModificacion(ModificacionInsumoPopUp panel, int elemento) {

        Insumo ins = this.listaInsumosActual.get(elemento);

        panel.setTxtCostoU(ins.getCosto().toString());
        panel.setTxtDescripcion(ins.getDescripcion());

/*        if(ins instanceof InsumoGeneral){

            panel.setTxtTipo(0);
            panel.setTxtPeso(((InsumoGeneral)ins).getPeso().toString());

        }*/
        if(ins instanceof InsumoLiquido){
            panel.setTxtTipo(1);
            panel.setTxtDensidad(((InsumoLiquido)ins).getDensidad().toString());
        }

        int i=0; String[] valores = this.getUnidadesDeMedida();
        while(i<valores.length &&!ins.getUnidadMedida().equalsIgnoreCase((valores[i]))){
            i++;
        }
        panel.setTxtUnidad(i);

        panel.setFila(elemento);

    }



    //ELIMINACIÓN:
    public void eliminar(int elemento){

        this.listaInsumosActual.remove(elemento);
        //TODO Invocar a Service con elemento seleccionado
        //this.listaCamionesActual.clear();
        //this.service.buscarTodos();

    }

    //ELIMINACIÓN:
    public void eliminar(int[] elementos){

        int i= elementos.length-1;

        while(i>=0){
            this.listaInsumosActual.remove(elementos[i]);
            i--;
        }


        //TODO Invocar a Service con elemento seleccionado
        //this.listaCamionesActual.clear();
        //this.service.buscarTodos();

    }


    //General: //TODO Hacer lo mismo que con Stock Controller.
    public String[] getTiposDeInsumos(){

        return new String[]{"General", "Líquido"};

    }

    public String[] getUnidadesDeMedida(){

        return new String[]{"Kg", "m2", "m3", "unidad"};

    }

    public List<Insumo> listarTodos(){

        System.out.println(this.listaInsumosActual);
        this.listaInsumosActual.clear();
        //TODO Llamar al service que obtiene la lista de todos los camiones
        //this.listaInsumosActual.addAll(service.buscarTodos());

        //TODO Eliminar estas lineas
        Insumo in = new InsumoGeneral();
        in.setId(1);
        in.setUnidadMedida("m3");
        in.setCosto(25.0F);
        in.setDescripcion("Insumo General");
        ((InsumoGeneral)in).setPeso(23F);

        Insumo in2 = new InsumoLiquido();
        in2.setId(1);
        in2.setUnidadMedida("Kg");
        in2.setCosto(25.0F);
        in2.setDescripcion("Insumo Liquido");
        ((InsumoLiquido)in2).setDensidad(24F);

        this.listaInsumosActual.add(in);
        this.listaInsumosActual.add(in2);

        return this.listaInsumosActual;

    }

    public void restaurarTabla(){

        this.listaInsumosActual = this.listarTodos();

    }

}

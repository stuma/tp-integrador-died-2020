package View.guiController;

import Service.ElementoNoEncontradoException;
import Service.InsumosService;
import Service.PlantaService;
import Model.Insumo;
import View.gui.insumos.InsumoPanel;
import View.gui.insumos.ModificacionInsumoPopUp;

import java.util.ArrayList;
import java.util.List;

public class InsumoGuiController {

    private static InsumoGuiController controller;
    private Insumo nuevoInsumo;
    private List<Insumo> listaInsumosActual;

    private InsumosService service;
    private PlantaService serviceStock;

    //Constructor
    //Constructor privado
    private InsumoGuiController(){

        this.listaInsumosActual = new ArrayList<>();
        this.nuevoInsumo = new Insumo();
        this.service = new InsumosService();
        this.serviceStock = new PlantaService();

    }

    //Retorna una instancia de InsumoController. Evita las multiples instancias.
    public static InsumoGuiController getInsumoController(){

        if(controller==null){

            controller = new InsumoGuiController();

        }

        return controller;

    }



    //ALTA DE INSUMO:
    //Obtiene los datos de la interfaz, los valida y realiza la llamada al Service
    public void guardar(InsumoPanel panel) throws Exception{

        //Validación de datos
        this.validarDatos(panel);

        service.altaInsumo(this.nuevoInsumo);

        this.listaInsumosActual.clear();
        this.listaInsumosActual.addAll(service.getListaInsumos());

    }

    //Actualiza tabla si se da de alta un insumo
    private void validarDatos(InsumoPanel panel) throws Exception {
        ArrayList<Integer> camposVacios = new ArrayList<>();
        Boolean[] camposValidos = {false, false, false};

        try {

            this.nuevoInsumo = new Insumo();

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

            //Verifico tipo de dato y verifico que el txtDensidad (aplica para peso) no sea nulo o vacío
            if(panel.getTxtTipo()!=null  && panel.getTxtDensidad()!=null && !panel.getTxtDensidad().getText().equals("")) {

                //Si txtTipo==0 -> seteo peso en this.nuevoInsumo, y pongo en null a densidad
                if(panel.getTxtTipo().getSelectedIndex()==0){

                    this.nuevoInsumo.setPeso(Float.valueOf(panel.getTxtDensidad().getText()));
                    this.nuevoInsumo.setDensidad(null);

                }else{
                    this.nuevoInsumo.setDensidad(Float.valueOf(panel.getTxtDensidad().getText()));
                    this.nuevoInsumo.setPeso(null);
                }
               camposValidos[2] = true;

            }else{
               camposVacios.add(2);
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
    public void modificar(ModificacionInsumoPopUp panel, int elemento) throws Exception{

        //Ya tendría un ID
        this.nuevoInsumo = this.listaInsumosActual.get(elemento);
        validarDatos(panel);

        //this.listaInsumosActual.remove(elemento);

        this.service.modificarInsumo(this.nuevoInsumo);

        this.listaInsumosActual.clear();
        this.listaInsumosActual.addAll(this.service.getListaInsumos());


    }

    //Actualiza tabla si se da de alta un camión
    private void validarDatos(ModificacionInsumoPopUp panel) throws Exception {

        ArrayList<Integer> camposVacios = new ArrayList<Integer>();
        Boolean[] camposValidos = {false, false, false};

        //this.insumo fue inicializado en modificar()
        Insumo aux = this.nuevoInsumo;

        try {

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
                camposVacios.add(3);
            }

            if(panel.getTxtCostoU()!=null && !panel.getTxtCostoU().getText().equals("")) {
                this.nuevoInsumo.setCosto(Float.valueOf(panel.getTxtCostoU().getText()));

                camposValidos[1] = true;
            }else{
                camposVacios.add(1);
            }

            //Verifico tipo de dato y verifico que el txtDensidad (aplica para peso) no sea nulo o vacío
            if(panel.getTxtTipo()!=null  && panel.getTxtDensidad()!=null && !panel.getTxtDensidad().getText().equals("")) {

                //Si txtTipo==0 -> seteo peso en this.nuevoInsumo, y pongo en null a densidad
                if(panel.getTxtTipo().getSelectedIndex()==0){

                    this.nuevoInsumo.setPeso(Float.valueOf(panel.getTxtDensidad().getText()));
                    this.nuevoInsumo.setDensidad(null);

                }else{
                    this.nuevoInsumo.setDensidad(Float.valueOf(panel.getTxtDensidad().getText()));
                    this.nuevoInsumo.setPeso(null);
                }
                camposValidos[2] = true;

            }else{
                camposVacios.add(2);
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

    //Método para mostrar los valores iniciales del elemento a modificar. Invocado desde InsumoPanel, acción modificar
    public void inicializarPanelModificacion(ModificacionInsumoPopUp panel, int elemento) {

        Insumo ins = this.listaInsumosActual.get(elemento);

        panel.setTxtCostoU(ins.getCosto().toString());
        panel.setTxtDescripcion(ins.getDescripcion());

        if(ins.getDensidad()!=null){
            panel.setTxtTipo(1);
            panel.setTxtDensidad(ins.getDensidad().toString());
        }else{
            panel.setTxtTipo(0);
            panel.setTxtDensidad(ins.getPeso().toString());
        }

        int i=0; String[] valores = this.getUnidadesDeMedida();
        while(i<valores.length &&!ins.getUnidadMedida().equalsIgnoreCase((valores[i]))){
            i++;
        }
        panel.setTxtUnidad(i);

        panel.setFila(elemento);

    }


    //ELIMINACIÓN:
    public void eliminar(int[] elementos){

        int i= elementos.length-1;

        while(i>=0){

            //Llama a service para eliminar el insumo
            this.service.bajaInsumo(this.listaInsumosActual.get(elementos[i]).getId());

            //Lo elimina de la lista actual.
            this.listaInsumosActual.remove(elementos[i]);
            i--;
        }

        //Actualizo la lista de insumos por las dudas
        this.listaInsumosActual.clear();
        try {
            this.listaInsumosActual.addAll(this.service.getListaInsumos());
        } catch (ElementoNoEncontradoException e) {
            this.listaInsumosActual = new ArrayList<>();
        }

    }


    //General:
    public String[] getTiposDeInsumos(){

        return new String[]{"General", "Líquido"};

    }

    public String[] getUnidadesDeMedida(){

        return new String[]{"Kilogramo","Unidad", "Gramo", "Metro", "Litro",  "Metro Cuadrado", "Metro Cúbico"};

    }

    public List<Insumo> listarTodos(){

        this.listaInsumosActual.clear();

        try {
            this.listaInsumosActual.addAll(service.getListaInsumos());
        } catch (ElementoNoEncontradoException e) {
            this.listaInsumosActual = new ArrayList<>();
            e.printStackTrace();
            return this.listaInsumosActual;
        }
        return this.listaInsumosActual;

    }


}

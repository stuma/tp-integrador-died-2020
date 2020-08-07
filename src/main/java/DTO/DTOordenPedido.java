package DTO;

import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * atributos del DTO fechaSolicitud;
 *      fechaEntrega;
 *      costoEnvio;
 *      camion;
 *       estadoPedido;
 *       listaItems;
 *       plantaDestino;
 *       plantaOrigen;
 *       tipoCamino; //1 para el mas rapido , 0 para el mas corto
 *
**/
public class DTOordenPedido {

    public LocalDate fechaSolicitud;
    public LocalDate fechaEntrega;
    public Float costoEnvio;
    public Camion camion;
    public EstadoPedido estadoPedido;
    public ArrayList<Item> listaItems;
    public Planta plantaDestino;
    public Planta plantaOrigen;
    public boolean tipoCamino; //1 para el mas rapido , 0 para el mas corto
}

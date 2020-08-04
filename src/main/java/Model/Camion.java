package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Camion {
    private Integer id;
    private String patente;
    private String marca;
    private String modelo;
    private Float kmRecorridos;
    private Float costoKm;
    private Float costoHora;
    private LocalDate fechaCompra;

    public Camion() {
    }

    public Camion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.kmRecorridos = kmRecorridos;
        this.costoKm = costoKm;
        this.costoHora = costoHora;
        this.fechaCompra = fechaCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Float getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(Float kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public Float getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(Float costoKm) {
        this.costoKm = costoKm;
    }

    public Float getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Float costoHora) {
        this.costoHora = costoHora;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}

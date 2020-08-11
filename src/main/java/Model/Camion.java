package Model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.persistence.Entity;


@Entity
@Table(name= "camion")
public class Camion implements Comparable<Camion>{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String patente;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private Float kmRecorridos;

    @Column
    private Float costoKm;

    @Column
    private Float costoHora;

    @Column
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

    @Override
    public int compareTo(Camion c){
        return this.kmRecorridos.compareTo(c.kmRecorridos);
    }

    public void addKmRecorrido(Float km){
        this.kmRecorridos+=km;
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

package com.example.entidades;

import jakarta.persistence.*;


@Entity
@Table(name = "Transporte")
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTransporte;
    private String tipoTransporte;
    private String rutaTransporte;
    private String horarioTransporte;

    @Lob
    private byte[] fotoTransporte;

    public Transporte(){

    }
    public Transporte(String nombreTransporte, String tipoTransporte, String rutaTransporte, String horarioTransporte, byte[] fotoTransporte) {
        this.nombreTransporte = nombreTransporte;
        this.tipoTransporte = tipoTransporte;
        this.rutaTransporte = rutaTransporte;
        this.horarioTransporte = horarioTransporte;
        this.fotoTransporte = fotoTransporte;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTransporte() {
        return nombreTransporte;
    }

    public void setNombreTransporte(String nombreTransporte) {
        this.nombreTransporte = nombreTransporte;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getRutaTransporte() {
        return rutaTransporte;
    }

    public void setRutaTransporte(String rutaTransporte) {
        this.rutaTransporte = rutaTransporte;
    }

    public String getHorarioTransporte() {
        return horarioTransporte;
    }

    public void setHorarioTransporte(String horarioTransporte) {
        this.horarioTransporte = horarioTransporte;
    }

    public byte[] getFotoTransporte() {
        return fotoTransporte;
    }

    public void setFotoTransporte(byte[] fotoTransporte) {
        this.fotoTransporte = fotoTransporte;
    }

    public void setImagenTransporte(byte[] imagenBytes) {
    }
}



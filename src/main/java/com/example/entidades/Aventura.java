package com.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "aventuras")
public class Aventura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreAvent;
    private String tipoAvent;
    private String zonaAvent;
    private String horarioAvent;

    @Lob
    private byte[] fotoAvent;

    public Aventura() {
    }

    public Aventura(String nombreAvent, String tipoAvent, String zonaAvent, String horarioAvent, byte[] fotoAvent) {
        this.nombreAvent = nombreAvent;
        this.tipoAvent = tipoAvent;
        this.zonaAvent = zonaAvent;
        this.horarioAvent = horarioAvent;
        this.fotoAvent = fotoAvent;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAvent() {
        return nombreAvent;
    }

    public void setNombreAvent(String nombreAvent) {
        this.nombreAvent = nombreAvent;
    }

    public String getTipoAvent() {
        return tipoAvent;
    }

    public void setTipoAvent(String tipoAvent) {
        this.tipoAvent = tipoAvent;
    }

    public String getZonaAvent() {
        return zonaAvent;
    }

    public void setZonaAvent(String zonaAvent) {
        this.zonaAvent = zonaAvent;
    }

    public String getHorarioAvent() {
        return horarioAvent;
    }

    public void setHorarioAvent(String horarioAvent) {
        this.horarioAvent = horarioAvent;
    }

    public byte[] getFotoAvent() {
        return fotoAvent;
    }

    public void setFotoAvent(byte[] fotoAvent) {
        this.fotoAvent = fotoAvent;
    }
}

package com.example.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "aventuras")
public class Aventura {

    @Id
    @Column(nullable = false)
    private String nombreAvent;

    @Column(nullable = false)
    private String tipoAvent;

    @Column(nullable = false)
    private String zonaAvent;

    @Column(nullable = false)
    private String horarioAvent;

    @Lob
    @Column(name= "foto_avent",columnDefinition = "LONGBLOB", nullable = false)
    private byte[] fotoAvent;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_identificacion")
    private Usuario usuario;

    @ManyToMany(mappedBy = "aventuras")
    private List<Transporte> transportes;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Transporte> getTransportes() {
        return transportes;
    }

    public void setTransportes(List<Transporte> transportes) {
        this.transportes = transportes;
    }
}

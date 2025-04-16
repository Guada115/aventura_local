package com.example.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private String tipoComida;

    @Lob
    @Column(name = "foto", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_identificacion")
    private Usuario usuario;

    @ManyToMany(mappedBy = "restaurantes")
    private List<Transporte> transportes;

    public Restaurante() {
    }

    public Restaurante(String nombre, String horario, String sector, String tipoComida, byte[] foto) {
        this.nombre = nombre;
        this.horario = horario;
        this.sector = sector;
        this.tipoComida = tipoComida;
        this.foto = foto;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

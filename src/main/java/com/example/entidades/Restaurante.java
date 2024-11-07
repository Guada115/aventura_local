package com.example.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id // Esto indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombre; // Campo que será la clave primaria

    private String horario;
    private String sector;
    private String tipoComida;

    @Lob
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

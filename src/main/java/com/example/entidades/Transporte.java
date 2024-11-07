package com.example.entidades;

import jakarta.persistence.*;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "transporte")
public class Transporte {

    @Id // Esto indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombreTransporte; // Campo que será la clave primaria
    private String tipoTransporte;
    private String rutaTransporte;
    private String horarioTransporte;

    @Lob
    private byte[] fotoTransporte;

    @ManyToMany
    @JoinTable(
            name = "transporte_restaurante",
            joinColumns = @JoinColumn(name = "transporte_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurante_id")
    )
    private List<Restaurante> restaurantes;

    @ManyToMany
    @JoinTable(
            name = "transporte_aventura",
            joinColumns = @JoinColumn(name = "transporte_id"),
            inverseJoinColumns = @JoinColumn(name = "aventura_id")
    )
    private List<Aventura> aventuras;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_identificacion")
    private Usuario usuario;

    public Transporte() {
    }

    public Transporte(String nombreTransporte, String tipoTransporte, String rutaTransporte, String horarioTransporte, byte[] fotoTransporte) {
        this.nombreTransporte = nombreTransporte;
        this.tipoTransporte = tipoTransporte;
        this.rutaTransporte = rutaTransporte;
        this.horarioTransporte = horarioTransporte;
        this.fotoTransporte = fotoTransporte;
    }

    // Getters y Setters
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public List<Aventura> getAventuras() {
        return aventuras;
    }

    public void setAventuras(List<Aventura> aventuras) {
        this.aventuras = aventuras;
    }

    // Método para convertir la imagen a Base64
    public String getImagenBase64() {
        return fotoTransporte != null ? Base64.getEncoder().encodeToString(this.fotoTransporte) : null;
    }
}

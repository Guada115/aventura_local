package com.example.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String contenido;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_identificacion", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_aventura_nombre", nullable = false)
    private Aventura aventura;

    public Comentario() {
    }

    public Comentario(String contenido, LocalDateTime fecha, Usuario usuario, Aventura aventura) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.aventura = aventura;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }
}

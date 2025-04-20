package com.example.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comentarios_restaurantes")
public class ComentarioRestaurante {

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
    @JoinColumn(name = "fk_restaurante_nombre", nullable = false)
    private Restaurante restaurante;

    // Constructores, getters y setters
    public ComentarioRestaurante() {
    }

    public ComentarioRestaurante(String contenido, LocalDateTime fecha, Usuario usuario, Restaurante restaurante) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.restaurante = restaurante;
    }

    // Getters y setters...

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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<Comentario> findByRestauranteOrderByFechaDesc(Restaurante restaurante) {
        return List.of();
    }
}

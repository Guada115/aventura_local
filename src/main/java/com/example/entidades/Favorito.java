package com.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "FAVORITOS")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // aventura, restaurante, transporte

    @Column(name = "REFERENCIA_ID", nullable = false)
    private String idReferencia; // nombreAvent, nombreRestaurante, etc.
    private String nombre;
    @Lob
    @Column( name= "foto_favorito",columnDefinition = "LONGBLOB", nullable = false)
    private byte[] foto;

    private String zona;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    public Favorito() {
    }

    public Favorito(String tipo, String idReferencia, String nombre, byte[] foto, String zona, Usuario usuario) {
        this.tipo = tipo;
        this.idReferencia = idReferencia;
        this.nombre = nombre;
        this.foto = foto;
        this.zona = zona;
        this.usuario = usuario;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

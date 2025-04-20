package com.example.dto;


public class ComentarioRestauranteDTO {
    private String texto;
    private String nombreRestaurante;

    // Getters y setters obligatorios
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }
}
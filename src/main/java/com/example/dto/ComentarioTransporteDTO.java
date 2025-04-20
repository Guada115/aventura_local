package com.example.dto;

public class ComentarioTransporteDTO {
    private String texto;
    private String nombreTransporte;

    // Getters y setters obligatorios
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreTransporte() {
        return nombreTransporte;
    }

    public void setNombreTransporte(String nombreTransporte) {
        this.nombreTransporte = nombreTransporte;
    }
}

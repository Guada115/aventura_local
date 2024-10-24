package com.example.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Transporte")
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTransporte;
    private String tipoTransporte;
    private String rutaTransporte;
    private String horarioTransporte;

    @Lob
    private byte[] fotoTransporte;

    public Transporte() {
        public Transporte (String nombreTransporte, String tipoTransporte, String rutaTransporte, String horarioTransporte){
            this.nombreTransporte = nombreTransporte;
            this.tipoTransporte = tipoTransporte;
            this.rutaTransporte = rutaTransporte;
            this.horarioTransporte = horarioTransporte;
            this.fotoTransporte = fotoTransporte;

        }



    }

}

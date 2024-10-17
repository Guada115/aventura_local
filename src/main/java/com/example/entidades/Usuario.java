package com.example.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = true)
    private String nombre;

    @Column(name = "APELLIDO", nullable = true)
    private String apellido;

    @Column(name = "CORREO", nullable = true, unique = true)
    private String correo;

    @Column(name = "FECHA_NACIMIENTO",nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "USUARIO", nullable = true, unique = true)
    private String usuario;

    @Column(name = "CONTRASENA", nullable = true)
    private String contrasena;
}

package com.example.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id

    @Column(name = "IDENTIFICACION", nullable = false)
    private String identificacion;


    @Column(name = "NOMBRE", nullable = false)
    private String nombre;


    @Column(name = "APELLIDO", nullable = false)
    private String apellido;


    @Column(name = "CORREO", nullable = false, unique = true)
    private String correo;


    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "USUARIO", nullable = false, unique = true)
    private String usuario;


    @Column(name = "CONTRASENA", nullable = false)
    private String contrasena;


    // Getters y Setters
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}

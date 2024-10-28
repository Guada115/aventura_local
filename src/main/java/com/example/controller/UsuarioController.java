package com.example.controller;

import com.example.entidades.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam("identificacion") String identificacion,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("correo") String correo,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("usuario") String usuarioNombre,
            @RequestParam("contrasena") String contrasena,
            @RequestParam("verificarContrasena") String verificarContrasena) {

        // Verificar que las contraseñas coincidan
        if (!contrasena.equals(verificarContrasena)) {
            return "Registrar"; // Redirige a la vista de registro si las contraseñas no coinciden
        }

        // Crear y guardar el usuario
        Usuario usuario = new Usuario();
        usuario.setIdentificacion(identificacion);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
        usuario.setUsuario(usuarioNombre);
        usuario.setContrasena(contrasena);

        usuarioRepository.save(usuario);

        // Redirige a la vista de bienvenida sin mensaje
        return "Welcome";
    }
}

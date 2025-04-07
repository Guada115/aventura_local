package com.example.controller;

import com.example.entidades.Usuario;
import com.example.repository.UsuarioRepository;
import com.example.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

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

        // Redirige a la vista de bienvenida
        return "Welcome";
    }

    @PostMapping("/login")
    public String loginUsuario(
            @RequestParam("usuario") String usuarioNombre,
            @RequestParam("contrasena") String contrasena,
            Model model,
            HttpSession session) {

        // Login de administrador manual
        if ("admin".equals(usuarioNombre) && "123".equals(contrasena)) {
            session.setAttribute("usuarioLogueado", "admin");
            return "inicioAdmin";
        }

        // Buscar al usuario real en la base de datos
        Usuario usuario = usuarioService.obtenerUsuarioPorCredenciales(usuarioNombre, contrasena);

        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);
            return "Welcome";
        } else {
            model.addAttribute("error", "Usuario o contraseña son incorrectos");
            return "indice";
        }
    }

    @GetMapping("/cerrar-sesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // o simplemente "redirect:/"
    }

}

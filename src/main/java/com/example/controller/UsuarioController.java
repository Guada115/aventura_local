package com.example.controller;


import com.example.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario, Model model) {
        // Puedes agregar lógica para verificar que las contraseñas coincidan antes de guardar
        usuarioRepository.save(usuario);
        model.addAttribute("mensaje", "Usuario registrado con éxito");
        return "registro";
    }
}

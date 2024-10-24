package com.example.controller;

import com.example.entidades.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, String verificarContrasena,BindingResult bindingResult,
                                   Model model) {
        // Verifica que las contraseñas coincidan
        if (!usuario.getContrasena().equals(verificarContrasena)) {
           model.addAttribute("mensaje", "Las contraseñas no coinciden.");
            return "Registrar"; // Redirige a la vista crudRestaurantes si las contraseñas no coinciden
        }else {
            // Guarda el usuario en la base de datos
            usuarioRepository.save(usuario);
            model.addAttribute("mensaje", "Usuario registrado con éxito.");
            return "Welcome"; // Redirige a la vista de éxito
        }


    }
}

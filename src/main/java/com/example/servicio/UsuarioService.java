package com.example.servicio;

import com.example.entidades.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean login(String usuarioNombre, String contrasena) {
        return usuarioRepository.findByUsuarioAndContrasena(usuarioNombre, contrasena).isPresent();
    }
}

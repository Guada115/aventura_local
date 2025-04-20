package com.example.controller;


import com.example.entidades.ComentarioRestaurante;
import com.example.entidades.Restaurante;
import com.example.entidades.Usuario;
import com.example.repository.ComentarioRestauranteRepository;
import com.example.repository.RestauranteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comentariosrestaurante")
public class ComentarioRestauranteController {

    @Autowired
    private ComentarioRestauranteRepository comentarioRestauranteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping("/guardarrestaurante")
    public ResponseEntity<String> guardarRestauranteComentario(
            @RequestParam String texto,
            @RequestParam String nombreRestaurante,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return ResponseEntity.status(401).body("Error: Usuario no logueado");
        }

        Restaurante restaurante = restauranteRepository.findById(nombreRestaurante).orElse(null);
        if (restaurante == null) {
            return ResponseEntity.badRequest().body("Error: Restaurante no encontrada");
        }

        if (texto == null || texto.isBlank()) {
            return ResponseEntity.badRequest().body("Error: Texto del comentario vacío");
        }

        ComentarioRestaurante comentarioRestaurante = new ComentarioRestaurante();
        comentarioRestaurante.setContenido(texto);
        comentarioRestaurante.setFecha(LocalDateTime.now());
        comentarioRestaurante.setUsuario(usuario);
        comentarioRestaurante.setRestaurante(restaurante);

        comentarioRestauranteRepository.save(comentarioRestaurante);
        return ResponseEntity.ok("Comentario guardado");
    }

    @GetMapping("/{nombreRestaurante}")
    public ResponseEntity<List<ComentarioRestaurante>> obtenerComentarios(@PathVariable String nombreRestaurante) {
        Restaurante restaurante = restauranteRepository.findById(nombreRestaurante).orElse(null);
        if (restaurante == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(comentarioRestauranteRepository.findByRestauranteOrderByFechaDesc(restaurante));
    }
}
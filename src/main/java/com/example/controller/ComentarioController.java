package com.example.controller;

import com.example.entidades.Aventura;
import com.example.entidades.Comentario;
import com.example.entidades.Usuario;
import com.example.repository.AventuraRepository;
import com.example.repository.ComentarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private AventuraRepository aventuraRepository;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarComentario(
            @RequestParam String texto,
            @RequestParam String nombreAventura,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return ResponseEntity.status(401).body("Error: Usuario no logueado");
        }

        Aventura aventura = aventuraRepository.findById(nombreAventura).orElse(null);
        if (aventura == null) {
            return ResponseEntity.badRequest().body("Error: Aventura no encontrada");
        }

        if (texto == null || texto.isBlank()) {
            return ResponseEntity.badRequest().body("Error: Texto del comentario vacío");
        }

        Comentario comentario = new Comentario();
        comentario.setContenido(texto);
        comentario.setFecha(LocalDateTime.now());
        comentario.setUsuario(usuario);
        comentario.setAventura(aventura);

        comentarioRepository.save(comentario);
        return ResponseEntity.ok("Comentario guardado");
    }

    @GetMapping("/{nombreAventura}")
    public ResponseEntity<List<Comentario>> obtenerComentarios(@PathVariable String nombreAventura) {
        Aventura aventura = aventuraRepository.findById(nombreAventura).orElse(null);
        if (aventura == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(comentarioRepository.findByAventuraOrderByFechaDesc(aventura));
    }
}
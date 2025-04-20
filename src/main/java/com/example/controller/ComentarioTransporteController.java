package com.example.controller;

import com.example.entidades.ComentarioTransporte;
import com.example.entidades.Transporte;
import com.example.entidades.Usuario;
import com.example.repository.ComentarioTransporteRepository;
import com.example.repository.TransporteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comentariotransporte")
public class ComentarioTransporteController {

    @Autowired
    private ComentarioTransporteRepository comentarioTransporteRepository;

    @Autowired
    private TransporteRepository transporteRepository;

    @PostMapping("/guardartransporte")
    public ResponseEntity<String> guardarTransporteComentario(
            @RequestParam String texto,
            @RequestParam String nombreTransporte,
            HttpSession session) {

        // Validación de usuario logueado
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return ResponseEntity.status(401).body("Error: Usuario no logueado");
        }

        // Validación de transporte existente
        Transporte transporte = transporteRepository.findById(nombreTransporte).orElse(null);
        if (transporte == null) {
            return ResponseEntity.badRequest().body("Error: Transporte no encontrado");
        }

        // Validación del contenido del comentario
        if (texto == null || texto.isBlank()) {
            return ResponseEntity.badRequest().body("Error: Texto del comentario vacío");
        }

        // Validación de longitud máxima (opcional)
        if (texto.length() > 1000) {
            return ResponseEntity.badRequest().body("Error: El comentario no puede exceder los 1000 caracteres");
        }

        // Creación y guardado del comentario
        ComentarioTransporte comentarioTransporte = new ComentarioTransporte();
        comentarioTransporte.setContenido(texto);
        comentarioTransporte.setFecha(LocalDateTime.now());
        comentarioTransporte.setUsuario(usuario);
        comentarioTransporte.setTransporte(transporte);

        comentarioTransporteRepository.save(comentarioTransporte);
        return ResponseEntity.ok("Comentario guardado exitosamente");
    }

    @GetMapping("/{nombreTransporte}")
    public ResponseEntity<List<ComentarioTransporte>> obtenerComentarios(@PathVariable String nombreTransporte) {
        Transporte transporte = transporteRepository.findById(nombreTransporte).orElse(null);
        if (transporte == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(comentarioTransporteRepository.findByTransporteOrderByFechaDesc(transporte));
    }
}
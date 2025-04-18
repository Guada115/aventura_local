package com.example.controller;

import com.example.entidades.Aventura;
import com.example.entidades.Comentario;
import com.example.entidades.Usuario;
import com.example.repository.AventuraRepository;
import com.example.repository.ComentarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.dto.ComentarioDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private AventuraRepository aventuraRepository;

    @PostMapping
    public Comentario agregarComentario(@RequestBody ComentarioDTO comentarioDTO, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        Aventura aventura = aventuraRepository.findById(comentarioDTO.getNombreAventura()).orElse(null);

        if (usuario == null || aventura == null || comentarioDTO.getTexto().isBlank()) {
            throw new IllegalArgumentException("Datos inválidos");
        }

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getTexto());
        comentario.setFecha(LocalDateTime.now());
        comentario.setUsuario(usuario);
        comentario.setAventura(aventura);

        return comentarioRepository.save(comentario);
    }


    @GetMapping("/{nombreAventura}")
    public List<Comentario> obtenerComentarios(@PathVariable String nombreAventura) {
        Aventura aventura = aventuraRepository.findById(nombreAventura).orElse(null);
        return aventura != null
                ? comentarioRepository.findByAventuraOrderByFechaDesc(aventura)
                : List.of();
    }
}

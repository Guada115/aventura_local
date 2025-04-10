package com.example.controller;

import com.example.entidades.*;
import com.example.repository.*;
import com.example.servicio.FavoritoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepo;

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TransporteRepository transporteRepo;

    @Autowired
    private RestauranteRepository restauranteRepo;

    @Autowired
    private AventuraRepository aventuraRepo;

    // Endpoint para agregar favorito
    @PostMapping
    public ResponseEntity<?> agregarFavorito(@RequestBody FavoritoDTO favDTO, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return ResponseEntity.status(401).body("No autenticado");
        }

        if ("aventura".equals(favDTO.getTipo())) {
            Aventura aventura = aventuraRepo.findById(favDTO.getIdReferencia()).orElse(null);
            if (aventura == null) {
                return ResponseEntity.notFound().build();
            }

            Favorito favorito = new Favorito(
                    "aventura",
                    aventura.getNombreAvent(),
                    aventura.getNombreAvent(),
                    aventura.getFotoAvent(),
                    aventura.getZonaAvent(),
                    usuario
            );

            favoritoRepo.save(favorito);
            return ResponseEntity.ok("Aventura agregada a favoritos");
        }

        return ResponseEntity.badRequest().body("Tipo no soportado");
    }

    // Mostrar la vista con la lista de favoritos
    @GetMapping("/favoritos")
    public String mostrarFavoritos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // Debug
        System.out.println("Usuario en sesión: " + (usuario != null ? usuario.getIdentificacion() : "null"));

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Favorito> favoritos = favoritoService.obtenerFavoritosPorUsuario(usuario.getIdentificacion());

        // Debug importante
        System.out.println("Número de favoritos: " + favoritos.size());
        favoritos.forEach(f -> System.out.println(f.getNombre() + " - " + f.getTipo()));

        model.addAttribute("favoritos", favoritos);
        return "favoritos";
    }


    // Endpoint para devolver la imagen por ID de favorito
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> obtenerImagenFavorito(@PathVariable Long id) {
        Favorito favorito = favoritoRepo.findById(id).orElse(null);
        if (favorito == null || favorito.getFoto() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(favorito.getFoto());
    }

    // DTO para agregar favoritos (no lo usamos para mostrar)
    public static class FavoritoDTO {
        private String tipo;
        private String idReferencia;

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }

        public String getIdReferencia() { return idReferencia; }
        public void setIdReferencia(String idReferencia) { this.idReferencia = idReferencia; }
    }
}

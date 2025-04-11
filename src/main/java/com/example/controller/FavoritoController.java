package com.example.controller;

import com.example.entidades.*;
import com.example.repository.*;
import com.example.servicio.FavoritoService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/favoritos")
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
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Debes iniciar sesión para agregar favoritos");
            }

            if ("aventura".equalsIgnoreCase(favDTO.getTipo())) {
                Aventura aventura = aventuraRepo.findByNombreAvent(favDTO.getIdReferencia());

                if (aventura == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Aventura no encontrada: " + favDTO.getIdReferencia());
                }

                // Verifica si ya existe
                boolean existe = favoritoRepo.existsByTipoAndIdReferenciaAndUsuario(
                        "aventura",
                        aventura.getNombreAvent(),
                        usuario
                );

                if (existe) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Esta aventura ya está en tus favoritos");
                }

                Favorito favorito = new Favorito();
                favorito.setTipo("aventura");
                favorito.setNombre(aventura.getNombreAvent());
                favorito.setIdReferencia(aventura.getNombreAvent()); // ESTO ES CRUCIAL
                favorito.setFoto(aventura.getFotoAvent());
                favorito.setZona(aventura.getZonaAvent());
                favorito.setUsuario(usuario);

                favoritoRepo.save(favorito);
                return ResponseEntity.ok("Aventura agregada a favoritos correctamente");
            }

            return ResponseEntity.badRequest().body("Tipo de favorito no soportado: " + favDTO.getTipo());
        } catch (Exception e) {
            log.error("Error al guardar favorito", e);
            return ResponseEntity.internalServerError()
                    .body("Error interno al procesar la solicitud: " + e.getMessage());
        }
    }

    // Mostrar la vista con la lista de favoritos
    @GetMapping
    public String mostrarFavoritos(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Favorito> favoritos = favoritoService.obtenerFavoritosPorUsuario(usuario.getIdentificacion());

        // Corrección aquí - asegurar que el nombre coincida con el usado en Thymeleaf
        model.addAttribute("favoritos", favoritos);
        model.addAttribute("usuario", usuario);

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

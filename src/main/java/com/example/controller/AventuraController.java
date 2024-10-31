package com.example.controller;

import com.example.entidades.Aventura;
import com.example.repository.AventuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/aventuras")
public class AventuraController {

    @Autowired
    private AventuraRepository aventuraRepository;

    @GetMapping
    public List<Aventura> getAllAventuras() {
        return aventuraRepository.findAll();
    }

    @GetMapping("/{nombreAvent}")
    public ResponseEntity<Aventura> getAventuraByNombre(@PathVariable String nombreAvent) {
        Aventura aventura = aventuraRepository.findById(nombreAvent)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));
        return ResponseEntity.ok(aventura);
    }

    @PutMapping("/{nombreAvent}")
    public ResponseEntity<Aventura> updateAventura(
            @PathVariable String nombreAvent,
            @RequestParam String tipoAvent,
            @RequestParam String zonaAvent,
            @RequestParam String horarioAvent,
            @RequestParam(required = false) MultipartFile fotoAvent) throws IOException {

        Aventura aventura = aventuraRepository.findById(nombreAvent)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));

        aventura.setTipoAvent(tipoAvent);
        aventura.setZonaAvent(zonaAvent);
        aventura.setHorarioAvent(horarioAvent);

        if (fotoAvent != null && !fotoAvent.isEmpty()) {
            aventura.setFotoAvent(fotoAvent.getBytes());
        }

        Aventura updatedAventura = aventuraRepository.save(aventura);
        return ResponseEntity.ok(updatedAventura);
    }

    @DeleteMapping("/{nombreAvent}")
    public ResponseEntity<Void> deleteAventura(@PathVariable String nombreAvent) {
        Aventura aventura = aventuraRepository.findById(nombreAvent)
                .orElseThrow(() -> new RuntimeException("Aventura no encontrada"));
        aventuraRepository.delete(aventura);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Aventura> agregarAventura(
            @RequestParam String nombreAvent,
            @RequestParam String tipoAvent,
            @RequestParam String zonaAvent,
            @RequestParam String horarioAvent,
            @RequestParam(required = false) MultipartFile fotoAvent) throws IOException {

        Aventura nuevaAventura = new Aventura();
        nuevaAventura.setNombreAvent(nombreAvent);
        nuevaAventura.setTipoAvent(tipoAvent);
        nuevaAventura.setZonaAvent(zonaAvent);
        nuevaAventura.setHorarioAvent(horarioAvent);

        if (fotoAvent != null && !fotoAvent.isEmpty()) {
            nuevaAventura.setFotoAvent(fotoAvent.getBytes());
        }

        Aventura savedAventura = aventuraRepository.save(nuevaAventura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAventura);
    }


}

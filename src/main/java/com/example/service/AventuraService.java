package com.example.service;

import com.example.entidades.Aventura;
import com.example.repository.AventuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AventuraService {

    @Autowired
    private AventuraRepository aventuraRepository;

    // Método para guardar una nueva aventura en la base de datos
    public Aventura guardarAventura(Aventura aventura) {
        return aventuraRepository.save(aventura);
    }

    // Método para obtener todas las aventuras guardadas en la base de datos
    public List<Aventura> obtenerTodasLasAventuras() {
        return aventuraRepository.findAll();
    }

    // Método para obtener una aventura por su ID (opcional)
    public Aventura obtenerAventuraPorId(Long id) {
        return aventuraRepository.findById(id).orElse(null);
    }

    // Método para eliminar una aventura por su ID (opcional)
    public void eliminarAventura(Long id) {
        aventuraRepository.deleteById(id);
    }
}

package com.example.repository;

import com.example.entidades.ComentarioRestaurante;
import com.example.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRestauranteRepository extends JpaRepository<ComentarioRestaurante, Long> {
    List<ComentarioRestaurante> findByRestauranteOrderByFechaDesc(Restaurante restaurante);
}
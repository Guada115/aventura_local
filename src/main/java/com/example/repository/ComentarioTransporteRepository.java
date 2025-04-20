package com.example.repository;

import com.example.entidades.ComentarioTransporte;
import com.example.entidades.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioTransporteRepository extends JpaRepository<ComentarioTransporte, Long> {
    List<ComentarioTransporte> findByTransporteOrderByFechaDesc(Transporte transporte);
}
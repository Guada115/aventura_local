package com.example.repository;

import com.example.entidades.Restaurante;
import com.example.entidades.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, String> {
    Transporte findByNombreTransporte(String idReferencia);
}

package com.example.repository;

import com.example.entidades.Comentario;
import com.example.entidades.Aventura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByAventuraOrderByFechaDesc(Aventura aventura);
}

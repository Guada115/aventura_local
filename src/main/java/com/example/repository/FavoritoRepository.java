package com.example.repository;

import com.example.entidades.Favorito;
import com.example.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioIdentificacion(String identificacion);
    boolean existsByTipoAndIdReferenciaAndUsuario(String tipo, String idReferencia, Usuario usuario);
}
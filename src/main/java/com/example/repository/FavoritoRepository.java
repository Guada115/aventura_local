package com.example.repository;

import com.example.entidades.Favorito;
import com.example.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioIdentificacion(String identificacion);
    boolean existsByTipoAndIdReferenciaAndUsuario(String tipo, String idReferencia, Usuario usuario);

    @Query("SELECT f.idReferencia, COUNT(f) as total FROM Favorito f WHERE f.tipo = 'restaurante' GROUP BY f.idReferencia ORDER BY total DESC")
    List<Object[]> findRestauranteFavoritoMasPopular();

    @Query("SELECT f.idReferencia, COUNT(f) as total FROM Favorito f WHERE f.tipo = 'transporte' GROUP BY f.idReferencia ORDER BY total DESC")
    List<Object[]> findTransporteFavoritoMasPopular();

    @Query("SELECT f.idReferencia, COUNT(f) as total FROM Favorito f WHERE f.tipo = 'aventura' GROUP BY f.idReferencia ORDER BY total DESC")
    List<Object[]> findAventuraFavoritaMasPopular();
}
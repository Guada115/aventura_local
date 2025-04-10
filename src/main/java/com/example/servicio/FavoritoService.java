package com.example.servicio;

import com.example.entidades.Favorito;
import com.example.entidades.Usuario;
import com.example.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepo;

    public List<Favorito> obtenerFavoritosPorUsuario(String identificacion) {
        System.out.println("Buscando favoritos para: " + identificacion);
        List<Favorito> favoritos = favoritoRepo.findByUsuarioIdentificacion(identificacion);
        System.out.println("Favoritos encontrados: " + favoritos.size());
        return favoritos;
    }


    public void guardarFavorito(Favorito fav) {
        favoritoRepo.save(fav);
    }

    public void eliminarFavorito(Long id) {
        favoritoRepo.deleteById(id);
    }
}


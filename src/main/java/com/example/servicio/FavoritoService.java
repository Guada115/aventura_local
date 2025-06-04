package com.example.servicio;

import com.example.entidades.Favorito;
import com.example.entidades.Usuario;
import com.example.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavoritoService {
    @Autowired
    public FavoritoRepository favoritoRepo;

    public List<Favorito> obtenerFavoritosPorUsuario(String identificacion) {

        List<Favorito> favoritos = favoritoRepo.findByUsuarioIdentificacion(identificacion);
        return favoritos;
    }


    public void guardarFavorito(Favorito fav) {
        favoritoRepo.save(fav);
    }

    public void eliminarFavorito(Long id) {
        favoritoRepo.deleteById(id);
    }

    public Map<String, String> obtenerReporteGeneralFavoritos() {
        Map<String, String> reporte = new HashMap<>();

        // Restaurante
        List<Object[]> rest = favoritoRepo.findRestauranteFavoritoMasPopular();
        if (!rest.isEmpty()) {
            Object[] top = rest.get(0);
            reporte.put("restaurante", top[0] + " (" + top[1] + " favoritos)");
        } else {
            reporte.put("restaurante", "No hay datos");
        }

        // Transporte
        List<Object[]> trans = favoritoRepo.findTransporteFavoritoMasPopular();
        if (!trans.isEmpty()) {
            Object[] top = trans.get(0);
            reporte.put("transporte", top[0] + " (" + top[1] + " favoritos)");
        } else {
            reporte.put("transporte", "No hay datos");
        }

        // Aventura
        List<Object[]> avent = favoritoRepo.findAventuraFavoritaMasPopular();
        if (!avent.isEmpty()) {
            Object[] top = avent.get(0);
            reporte.put("aventura", top[0] + " (" + top[1] + " favoritos)");
        } else {
            reporte.put("aventura", "No hay datos");
        }

        return reporte;
    }
}


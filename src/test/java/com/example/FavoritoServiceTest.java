package com.example;

import com.example.entidades.Favorito;
import com.example.repository.FavoritoRepository;
import com.example.servicio.FavoritoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoritoServiceTest {

    @Mock
    private FavoritoRepository favoritoRepo;

    @InjectMocks
    private FavoritoService favoritoService;

    @Test
    void testObtenerFavoritosPorUsuario() {
        String identificacion = "123";
        Favorito favorito1 = new Favorito();
        Favorito favorito2 = new Favorito();
        when(favoritoRepo.findByUsuarioIdentificacion(identificacion))
                .thenReturn(Arrays.asList(favorito1, favorito2));

        List<Favorito> resultado = favoritoService.obtenerFavoritosPorUsuario(identificacion);

        assertEquals(2, resultado.size());
        verify(favoritoRepo).findByUsuarioIdentificacion(identificacion);
    }

    @Test
    void testGuardarFavorito() {
        Favorito favorito = new Favorito();

        favoritoService.guardarFavorito(favorito);

        verify(favoritoRepo).save(favorito);
    }

    @Test
    void testEliminarFavorito() {
        Long id = 1L;

        favoritoService.eliminarFavorito(id);

        verify(favoritoRepo).deleteById(id);
    }
}

package com.example.controller;

import com.example.entidades.Aventura;
import com.example.entidades.Favorito;
import com.example.entidades.Usuario;
import com.example.servicio.FavoritoService;
import com.example.repository.FavoritoRepository;
import com.example.repository.AventuraRepository;
import com.example.repository.RestauranteRepository;
import com.example.repository.TransporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FavoritoControllerTest {

    @Mock
    private FavoritoRepository favoritoRepo;

    @Mock
    private FavoritoService favoritoService;

    @Mock
    private AventuraRepository aventuraRepo;

    @Mock
    private RestauranteRepository restauranteRepo;

    @Mock
    private TransporteRepository transporteRepo;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private FavoritoController favoritoController;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(); // Suponiendo que ya tienes una entidad Usuario implementada
        usuario.setIdentificacion("usuario123");
    }

    @Test
    void testAgregarFavorito() {
        // Arrange
        FavoritoController.FavoritoDTO favoritoDTO = new FavoritoController.FavoritoDTO();
        favoritoDTO.setTipo("aventura");
        favoritoDTO.setIdReferencia("aventura1");

        Aventura aventura = new Aventura(); // Suponiendo que ya tienes la entidad Aventura
        aventura.setNombreAvent("aventura1");

        when(session.getAttribute("usuarioLogueado")).thenReturn(usuario);
        when(aventuraRepo.findByNombreAvent(favoritoDTO.getIdReferencia())).thenReturn(aventura);
        when(favoritoRepo.existsByTipoAndIdReferenciaAndUsuario("aventura", aventura.getNombreAvent(), usuario)).thenReturn(false);

        // Act
        ResponseEntity<?> response = favoritoController.agregarFavorito(favoritoDTO, session);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("aventura agregado a favoritos correctamente"));
        verify(favoritoRepo, times(1)).save(any(Favorito.class));
    }

    @Test
    void testMostrarFavoritos() {
        // Arrange
        Favorito favorito = new Favorito();
        favorito.setId(1L);
        favorito.setTipo("aventura");
        favorito.setIdReferencia("aventura1");

        when(session.getAttribute("usuarioLogueado")).thenReturn(usuario);
        when(favoritoService.obtenerFavoritosPorUsuario(usuario.getIdentificacion())).thenReturn(List.of(favorito));

        // Act
        String viewName = favoritoController.mostrarFavoritos(session, model);

        // Assert
        assertEquals("favoritos", viewName);

        // Usamos ArgumentCaptor para capturar los argumentos pasados al addAttribute
        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        verify(model, times(1)).addAttribute(eq("favoritos"), captor.capture());
        List<Favorito> capturados = (List<Favorito>) captor.getAllValues().get(0);

        // Verificamos que el valor del atributo "favoritos" sea igual al esperado
        assertNotNull(capturados);
        assertEquals(1, capturados.size());
        assertEquals(favorito.getId(), capturados.get(0).getId());
        assertEquals(favorito.getTipo(), capturados.get(0).getTipo());
        assertEquals(favorito.getIdReferencia(), capturados.get(0).getIdReferencia());
    }


    @Test
    void testObtenerImagenFavorito() {
        // Arrange
        Favorito favorito = new Favorito();
        favorito.setFoto(new byte[]{1, 2, 3});
        when(favoritoRepo.findById(1L)).thenReturn(java.util.Optional.of(favorito));

        // Act
        ResponseEntity<byte[]> response = favoritoController.obtenerImagenFavorito(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
    }
}

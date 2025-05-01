package com.example.servicio;

import com.example.entidades.Usuario;
import com.example.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testLogin_UsuarioValido() {
        // Arrange
        String usuario = "juan";
        String contraseña = "123";
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setContrasena(contraseña);

        when(usuarioRepository.findByUsuarioAndContrasena(usuario, contraseña))
                .thenReturn(Optional.of(user));

        // Act
        boolean resultado = usuarioService.login(usuario, contraseña);

        // Assert
        assertTrue(resultado);
        verify(usuarioRepository).findByUsuarioAndContrasena(usuario, contraseña);
    }

    @Test
    void testLogin_UsuarioInvalido() {
        // Arrange
        String usuario = "juan";
        String contrasena = "Incorrecta";

        when(usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena))
                .thenReturn(Optional.empty());

        // Act
        boolean resultado = usuarioService.login(usuario, contrasena);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void testObtenerUsuarioPorCredenciales_Existe() {
        // Arrange
        String usuario = "juan";
        String contrasena = "123";
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setContrasena(contrasena);

        when(usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena))
                .thenReturn(Optional.of(user));

        // Act
        Usuario resultado = usuarioService.obtenerUsuarioPorCredenciales(usuario, contrasena);

        // Assert
        assertNotNull(resultado);
        assertEquals(usuario, resultado.getUsuario());
    }

    @Test
    void testObtenerUsuarioPorCredenciales_NoExiste() {
        // Arrange
        String usuario = "juan";
        String contrasena = "123";

        when(usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena))
                .thenReturn(Optional.empty());

        // Act
        Usuario resultado = usuarioService.obtenerUsuarioPorCredenciales(usuario, contrasena);

        // Assert
        assertNull(resultado);
    }
}

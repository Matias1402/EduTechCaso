package com.edutech.cl.EduTech.service;

import com.edutech.cl.EduTech.model.Usuario;
import com.edutech.cl.EduTech.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombres("Juan");
        usuario.setApellidos("Perez");
        usuario.setRun("12345678-9");
        usuario.setRol("ESTUDIANTE");
    }

    @Test
    void findAll_ShouldReturnAllUsuarios() {
        // Arrange
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));


        List<Usuario> result = usuarioService.findAll();


        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnUsuario() {

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));


        Usuario result = usuarioService.findById(1);


        assertNotNull(result);
        assertEquals("Juan", result.getNombres());
    }

    @Test
    void save_ShouldReturnSavedUsuario() {

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);


        Usuario result = usuarioService.save(usuario);


        assertNotNull(result);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void findByRol_ShouldReturnUsuariosWithRol() {

        when(usuarioRepository.findByRol("ESTUDIANTE")).thenReturn(Arrays.asList(usuario));


        List<Usuario> result = usuarioService.findByRol("ESTUDIANTE");

        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findByRol("ESTUDIANTE");
    }
}
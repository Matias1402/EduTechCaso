package com.edutech.cl.EduTech.service;

import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.model.Inscripcion;
import com.edutech.cl.EduTech.model.Usuario;
import com.edutech.cl.EduTech.repository.CursosRepository;
import com.edutech.cl.EduTech.repository.InscripcionRepository;
import com.edutech.cl.EduTech.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscripcionServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CursosRepository cursosRepository;

    @InjectMocks
    private InscripcionService inscripcionService;

    private Usuario usuario;
    private Cursos curso;
    private Inscripcion inscripcion;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombres("Juan");
        usuario.setCursos("");

        curso = new Cursos();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas");
        curso.setCuposDisponibles(10);

        inscripcion = new Inscripcion();
        inscripcion.setId(1L);
        inscripcion.setUsuario(usuario);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDate.now());
    }

    @Test
    void obtenerTodas_ShouldReturnAllInscripciones() {

        when(inscripcionRepository.findAll()).thenReturn(Arrays.asList(inscripcion));


        List<Inscripcion> result = inscripcionService.obtenerTodas();


        assertEquals(1, result.size());
        verify(inscripcionRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorUsuario_ShouldReturnInscripcionesForUsuario() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(inscripcionRepository.findByUsuario(usuario)).thenReturn(Arrays.asList(inscripcion));


        List<Inscripcion> result = inscripcionService.obtenerPorUsuario(1);


        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findById(1);
        verify(inscripcionRepository, times(1)).findByUsuario(usuario);
    }

    @Test
    void inscribirUsuario_ShouldCreateInscripcion() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(cursosRepository.findById(1)).thenReturn(Optional.of(curso));
        when(inscripcionRepository.existsByUsuarioAndCurso(usuario, curso)).thenReturn(false);
        when(inscripcionRepository.save(any(Inscripcion.class))).thenReturn(inscripcion);


        Inscripcion result = inscripcionService.inscribirUsuario(1, 1);


        assertNotNull(result);
        assertEquals(9, curso.getCuposDisponibles()); // Verifica que se restó 1 cupo
        verify(usuarioRepository, times(1)).save(usuario);
        verify(cursosRepository, times(1)).save(curso);
        verify(inscripcionRepository, times(1)).save(any(Inscripcion.class));
    }

    @Test
    void inscribirUsuario_ShouldThrowWhenNoCupos() {

        curso.setCuposDisponibles(0);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(cursosRepository.findById(1)).thenReturn(Optional.of(curso));


        assertThrows(RuntimeException.class, () -> inscripcionService.inscribirUsuario(1, 1));
    }
}
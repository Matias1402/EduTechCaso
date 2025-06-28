package com.edutech.cl.EduTech.service;

import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.repository.CursosRepository;
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
class CursosServiceTest {

    @Mock
    private CursosRepository cursosRepository;

    @InjectMocks
    private CursosService cursosService;

    private Cursos curso;

    @BeforeEach
    void setUp() {
        curso = new Cursos();
        curso.setIdCurso(1);
        curso.setNombreCurso("Matemáticas");
        curso.setCuposDisponibles(10);
    }

    @Test
    void findAll_ShouldReturnAllCursos() {

        when(cursosRepository.findAll()).thenReturn(Arrays.asList(curso));


        List<Cursos> result = cursosService.findAll();


        assertEquals(1, result.size());
        verify(cursosRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnCurso() {

        when(cursosRepository.findById(1)).thenReturn(Optional.of(curso));


        Cursos result = cursosService.findById(1);


        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombreCurso());
    }

    @Test
    void save_ShouldReturnSavedCurso() {

        when(cursosRepository.save(any(Cursos.class))).thenReturn(curso);


        Cursos result = cursosService.save(curso);


        assertNotNull(result);
        verify(cursosRepository, times(1)).save(curso);
    }

    @Test
    void delete_ShouldDeleteCurso() {
        // Arrange
        doNothing().when(cursosRepository).deleteById(1);


        cursosService.delete(1);


        verify(cursosRepository, times(1)).deleteById(1);
    }
}

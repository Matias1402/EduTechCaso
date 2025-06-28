package com.edutech.cl.EduTech.controller;

import com.edutech.cl.EduTech.model.Inscripcion;
import com.edutech.cl.EduTech.service.InscripcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InscripcionControllerTest {

    @Mock
    private InscripcionService inscripcionService;

    @InjectMocks
    private InscripcionController inscripcionController;

    private Inscripcion inscripcion;

    @BeforeEach
    void setUp() {
        inscripcion = new Inscripcion();
        inscripcion.setId(1L);
    }

    @Test
    void listarTodas_ShouldReturnAllInscripciones() {

        when(inscripcionService.obtenerTodas()).thenReturn(Arrays.asList(inscripcion));


        ResponseEntity<List<Inscripcion>> response = inscripcionController.listarTodas();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void inscribir_ShouldReturnInscripcion() {

        when(inscripcionService.inscribirUsuario(1, 1)).thenReturn(inscripcion);


        ResponseEntity<?> response = inscripcionController.inscribir(1, 1);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inscripcion, response.getBody());
    }

    @Test
    void inscribir_ShouldReturnErrorWhenException() {

        when(inscripcionService.inscribirUsuario(1, 1)).thenThrow(new RuntimeException("Error"));


        ResponseEntity<?> response = inscripcionController.inscribir(1, 1);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Error"));
    }
}
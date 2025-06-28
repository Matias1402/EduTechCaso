package com.edutech.cl.EduTech.controller;

import com.edutech.cl.EduTech.model.Inscripcion;
import com.edutech.cl.EduTech.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listarTodas() {
        List<Inscripcion> inscripciones = inscripcionService.obtenerTodas();
        return ResponseEntity.ok(inscripciones);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Inscripcion>> listarPorUsuario(@PathVariable Integer usuarioId) {
        List<Inscripcion> inscripciones = inscripcionService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(inscripciones);
    }


    @PostMapping
    public ResponseEntity<?> inscribir(@RequestParam Integer usuarioId, @RequestParam Integer cursoId) {
        try {
            Inscripcion inscripcion = inscripcionService.inscribirUsuario(usuarioId, cursoId);
            return ResponseEntity.ok(inscripcion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al inscribir: " + e.getMessage());
        }
    }
}
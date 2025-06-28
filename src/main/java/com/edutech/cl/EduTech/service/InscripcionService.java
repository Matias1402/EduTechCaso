package com.edutech.cl.EduTech.service;

import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.model.Inscripcion;
import com.edutech.cl.EduTech.model.Usuario;
import com.edutech.cl.EduTech.repository.CursosRepository;
import com.edutech.cl.EduTech.repository.InscripcionRepository;
import com.edutech.cl.EduTech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursosRepository cursosRepository;

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }
    public List<Inscripcion> obtenerPorUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return inscripcionRepository.findByUsuario(usuario);
    }

    public Inscripcion inscribirUsuario(Integer usuarioId, Integer cursoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cursos curso = cursosRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (curso.getCuposDisponibles() <= 0) {
            throw new RuntimeException("No hay cupos disponibles");
        }

        if (inscripcionRepository.existsByUsuarioAndCurso(usuario, curso)) {
            throw new RuntimeException("El usuario ya está inscrito en este curso");
        }

        // Descontar cupo
        curso.setCuposDisponibles(curso.getCuposDisponibles() - 1);
        cursosRepository.save(curso);

        // Actualizar campo cursos en Usuario (como texto)
        String cursosActuales = usuario.getCursos() != null ? usuario.getCursos() : "";
        String nuevoCurso = curso.getNombreCurso();
        usuario.setCursos(cursosActuales.isEmpty() ? nuevoCurso : cursosActuales + ", " + nuevoCurso);
        usuarioRepository.save(usuario);


        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDate.now());

        return inscripcionRepository.save(inscripcion);
    }
}
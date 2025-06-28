package com.edutech.cl.EduTech.repository;

import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.model.Inscripcion;
import com.edutech.cl.EduTech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByUsuarioAndCurso(Usuario usuario, Cursos curso);
    List<Inscripcion> findByUsuario(Usuario usuario);
}

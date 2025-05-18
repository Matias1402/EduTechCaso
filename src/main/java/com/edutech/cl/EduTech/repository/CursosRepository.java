package com.edutech.cl.EduTech.repository;
import com.edutech.cl.EduTech.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CursosRepository extends JpaRepository<Cursos, Long> {
}

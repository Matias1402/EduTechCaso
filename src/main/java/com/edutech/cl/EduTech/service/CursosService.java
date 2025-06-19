package com.edutech.cl.EduTech.service;


import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.repository.CursosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class CursosService {
    @Autowired
    private CursosRepository cursosRepository;


    public List<Cursos> findAll() {
        return cursosRepository.findAll();
    }


    public Cursos findById(Integer idCurso) {
        return cursosRepository.findById(idCurso).get();
    }

    public Cursos save(Cursos cursos) {
        return cursosRepository.save(cursos);
    }

    public void delete(Integer idCurso) {
        cursosRepository.deleteById(idCurso);
    }
}
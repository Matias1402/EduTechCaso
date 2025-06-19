package com.edutech.cl.EduTech.controller;
import com.edutech.cl.EduTech.model.Cursos;
import com.edutech.cl.EduTech.model.Usuario;
import com.edutech.cl.EduTech.repository.CursosRepository;
import com.edutech.cl.EduTech.service.CursosService;
import com.edutech.cl.EduTech.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/cursos")
public class CursosController {
    @Autowired
    private CursosService cursosService;

    @GetMapping
    public ResponseEntity<List<Cursos>> listar(){
        List<Cursos> cursos = cursosService.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }
    @PostMapping
    public ResponseEntity<Cursos> guardar(@RequestBody Cursos cursos){
        Cursos nuevoCurso = cursosService.save(cursos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Cursos> buscar (@PathVariable Integer idCurso){
        try{
            Cursos cursos = cursosService.findById(idCurso);
            return ResponseEntity.ok(cursos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idCurso}")
    public ResponseEntity<Cursos> Actualizar(@PathVariable Integer idCurso, @RequestBody Cursos cursos){
        try{
            Cursos cursos1 = cursosService.findById(idCurso);
            cursos1.setIdCurso(idCurso);
            cursos1.setNombreCurso(cursos.getNombreCurso());

            cursosService.save(cursos1);
            return ResponseEntity.ok(cursos);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Cursos> Eliminar(@PathVariable Integer idCurso){
        try{
            cursosService.delete(idCurso);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

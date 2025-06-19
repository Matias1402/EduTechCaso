package com.edutech.cl.EduTech.controller;


import com.edutech.cl.EduTech.model.Usuario;
import com.edutech.cl.EduTech.repository.UsuarioRepository;
import com.edutech.cl.EduTech.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar (@PathVariable Integer id){
        try{
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> Actualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
        try{
            Usuario user = usuarioService.findById(id);
            user.setId(id);
            user.setRun(usuario.getRun());
            user.setNombres(usuario.getNombres());
            user.setApellidos(usuario.getApellidos());
            user.setFechaNacimiento(usuario.getFechaNacimiento());
            user.setCorreo(usuario.getCorreo());

            usuarioService.save(user);
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> Eliminar(@PathVariable Integer id){
        try{
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> buscarPorRol(@PathVariable String rol){
        List<Usuario> usuarios = usuarioService.findByRol(rol);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<Usuario> buscarPorRun(@PathVariable String run){
        try {
            Usuario usuario = usuarioService.findByRun(run);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

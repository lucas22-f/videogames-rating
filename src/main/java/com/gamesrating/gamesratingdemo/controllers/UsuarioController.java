package com.gamesrating.gamesratingdemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gamesrating.gamesratingdemo.models.Usuario;
import com.gamesrating.gamesratingdemo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioServicio;
    
    @PostMapping("/register")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        usuarioServicio.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
    @GetMapping("/get")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServicio.obtenerUsuarios());
    }

    @GetMapping("/get/{id}")
    public Usuario getMethodName(@PathVariable Long id) {
        return usuarioServicio.obtenerUsuario(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioServicio.editarUsuario(id,usuario));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioServicio.eliminarUsuario(id));
    }
    
}

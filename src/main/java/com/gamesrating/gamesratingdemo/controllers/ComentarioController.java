package com.gamesrating.gamesratingdemo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamesrating.gamesratingdemo.DTO.CommentDTO;
import com.gamesrating.gamesratingdemo.models.Comentario;
import com.gamesrating.gamesratingdemo.service.ComentarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/create")
    public ResponseEntity<String> crearComentario(@RequestBody CommentDTO comentarioDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioService.crearComentario(comentarioDTO));
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Comentario>> obtenerComentarios() {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioService.obtenerComentarios());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Comentario obtenerComentario(@PathVariable Long id) {
        return comentarioService.obtenerComentario(id);
    }

    @GetMapping("/get/videogame/{videoJuegoId}")
    public ResponseEntity<List<Comentario>> obtenerComentariosVideojuego(@PathVariable Long videoJuegoId) {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioService.obtenerComentarioVideojuego(videoJuegoId));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Comentario> editarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioService.editarComentario(id, comentario));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comentarioService.eliminarComentario(id));
    }

}

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

import com.gamesrating.gamesratingdemo.models.Videojuego;
import com.gamesrating.gamesratingdemo.service.VideoJuegoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/games")
public class VideojuegoController {

    private final VideoJuegoService videoJuegoService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> crearVideoJuego(@RequestBody Videojuego juego) {
        return ResponseEntity.status(HttpStatus.OK).body(videoJuegoService.crearVideojuego(juego));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Videojuego>> obtenerVideoJuegos() {
        return ResponseEntity.status(HttpStatus.OK).body(videoJuegoService.obtenerVideojuegos());
    }

    @GetMapping("/get/{id}")
    public Videojuego obtenerVideojuego(@PathVariable Long id) {
        return videoJuegoService.obtenerVideojuego(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Videojuego> editarVideoJuego(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        return ResponseEntity.status(HttpStatus.OK).body(videoJuegoService.editarVideojuego(id, videojuego));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarVideoJuego(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(videoJuegoService.eliminarVideojuego(id));
    }

}

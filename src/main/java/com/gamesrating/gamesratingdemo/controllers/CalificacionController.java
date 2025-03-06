package com.gamesrating.gamesratingdemo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamesrating.gamesratingdemo.DTO.CalificacionDTO;
import com.gamesrating.gamesratingdemo.models.Calificacion;
import com.gamesrating.gamesratingdemo.service.CalificacionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class CalificacionController {
    private final CalificacionService servicioCalificacion;
     @PostMapping("/create")
    public ResponseEntity<String> crearCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(servicioCalificacion.crearCalificacion(calificacionDTO));
    }
    @GetMapping("/get")
    public ResponseEntity<List<Calificacion>> obtenerCalificacions() {
        return ResponseEntity.status(HttpStatus.OK).body(servicioCalificacion.obtenerCalificaciones());
    }

     @GetMapping("/get/{id}")
    public Calificacion obtenerCalificacion(@PathVariable Long id) {
        return servicioCalificacion.obtenerCalificacion(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Calificacion> editarCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        return ResponseEntity.status(HttpStatus.OK).body(servicioCalificacion.editarCalificacion(id,calificacion));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCalificacion(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicioCalificacion.eliminarCalificacion(id));
    }

}

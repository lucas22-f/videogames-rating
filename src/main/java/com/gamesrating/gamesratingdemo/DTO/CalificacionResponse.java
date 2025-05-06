package com.gamesrating.gamesratingdemo.DTO;

import com.gamesrating.gamesratingdemo.models.Calificacion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalificacionResponse {
    private Calificacion calificacion;
    private String nombreVideojuego;
}

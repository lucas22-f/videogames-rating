package com.gamesrating.gamesratingdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalificacionDTO {
    private int puntuacion;
    private Long usuario;
    private Long videojuego;
}

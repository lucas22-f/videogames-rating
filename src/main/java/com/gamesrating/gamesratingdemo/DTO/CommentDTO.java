package com.gamesrating.gamesratingdemo.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentDTO {
    private String texto;
    private Date fecha=new Date();
    private Long usuario;
    private Long videojuego;
}

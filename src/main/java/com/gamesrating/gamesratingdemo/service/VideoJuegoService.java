package com.gamesrating.gamesratingdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gamesrating.gamesratingdemo.models.Videojuego;
import com.gamesrating.gamesratingdemo.repository.IVideoJuegoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VideoJuegoService {

    @Autowired
    private final IVideoJuegoRepository repositorioVideojuego;

    public String crearVideojuego(Videojuego juego){
        repositorioVideojuego.save(juego);
        return "Video Juego creado con exito";
    }
    public List<Videojuego> obtenerVideojuegos(){
        return repositorioVideojuego.findAll();
    }
    public Videojuego editarVideojuego(Long id, Videojuego videojuegoEditado){
        Videojuego videojuegoBD = repositorioVideojuego.findById(id).orElse(null);
        if(videojuegoBD!=null){
            return repositorioVideojuego.save(videojuegoEditado);
        }
        return null;
    }
    public Videojuego obtenerVideojuego(Long id){
        return repositorioVideojuego.findById(id).orElse(null);
    }
    public String eliminarVideojuego(Long id){
        repositorioVideojuego.deleteById(id);
        return "Videojuego eliminado con exito";
    }


}

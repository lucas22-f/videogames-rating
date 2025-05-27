package com.gamesrating.gamesratingdemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesrating.gamesratingdemo.DTO.CommentDTO;
import com.gamesrating.gamesratingdemo.models.Comentario;
import com.gamesrating.gamesratingdemo.models.Usuario;
import com.gamesrating.gamesratingdemo.models.Videojuego;
import com.gamesrating.gamesratingdemo.repository.IComentarioRepository;
import com.gamesrating.gamesratingdemo.repository.IUsuarioRepository;
import com.gamesrating.gamesratingdemo.repository.IVideoJuegoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComentarioService {

    @Autowired
    private final IComentarioRepository comentarioRepository;
    @Autowired
    private final IUsuarioRepository usuarioRepository;
    @Autowired
    private final IVideoJuegoRepository videoJuegoRepository;

    public String crearComentario(CommentDTO comentarioDto) {
        Videojuego videojuegoBD = videoJuegoRepository.findById(comentarioDto.getVideojuego()).orElseThrow(()
                -> new EntityNotFoundException("Videojuego No encontrado. id buscado : " + comentarioDto.getVideojuego()));
        Usuario usuarioBD = usuarioRepository.findById(comentarioDto.getUsuario()).orElseThrow(()
                -> new EntityNotFoundException("Usuario No Encontrado. id buscado :" + comentarioDto.getUsuario()));
        Comentario comentario = new Comentario();
        comentario.setFecha(new Date());
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setUsuario(usuarioBD);
        comentario.setVideojuego(videojuegoBD);
        comentarioRepository.save(comentario);

        return "Comentario creado con exito";
    }

    public List<Comentario> obtenerComentarioVideojuego(Long idVideojuego) {
        Videojuego videojuego = videoJuegoRepository.findById(idVideojuego).orElse(null);
        return videojuego != null ? videojuego.getListaComentarios() : new ArrayList<>();
    }

    public List<Comentario> obtenerComentarios() {
        return comentarioRepository.findAll();
    }

    public Comentario editarComentario(Long id, Comentario comentarioEditado) {
        Comentario comentarioBD = comentarioRepository.findById(id).orElse(null);
        if (comentarioBD != null) {
            return comentarioRepository.save(comentarioEditado);
        }
        return null;
    }

    public Comentario obtenerComentario(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    public String eliminarComentario(Long id) {
        comentarioRepository.deleteById(id);
        return "Comentario eliminado con exito";
    }
}

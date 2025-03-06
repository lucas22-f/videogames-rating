package com.gamesrating.gamesratingdemo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gamesrating.gamesratingdemo.DTO.CalificacionDTO;
import com.gamesrating.gamesratingdemo.models.Calificacion;
import com.gamesrating.gamesratingdemo.models.Usuario;
import com.gamesrating.gamesratingdemo.models.Videojuego;
import com.gamesrating.gamesratingdemo.repository.ICalificacionRepository;
import com.gamesrating.gamesratingdemo.repository.IUsuarioRepository;
import com.gamesrating.gamesratingdemo.repository.IVideoJuegoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalificacionService {
    @Autowired
    private final ICalificacionRepository repositorioCalificacion;
    @Autowired
    private final IUsuarioRepository usuarioRepository;
    @Autowired
    private final IVideoJuegoRepository videoJuegoRepository;

    public String crearCalificacion(CalificacionDTO calificacionDto){
        Videojuego videojuegoBD = videoJuegoRepository.findById(calificacionDto.getVideojuego()).orElse(null);
        Usuario usuarioBD = usuarioRepository.findById(calificacionDto.getUsuario()).orElse(null);
        Calificacion Calificacion = new Calificacion();
        
        Calificacion.setPuntuacion(calificacionDto.getPuntuacion());
        Calificacion.setUsuario(usuarioBD);
        Calificacion.setVideojuego(videojuegoBD);
        repositorioCalificacion.save(Calificacion);

        return "Calificacion creada con exito";
    }
    public List<Calificacion> obtenerCalificaciones(){
        return repositorioCalificacion.findAll();
    }
    public Calificacion editarCalificacion(Long id, Calificacion calificacionEditado){
        Calificacion calificacionBD = repositorioCalificacion.findById(id).orElse(null);
        if(calificacionBD!=null){
            return repositorioCalificacion.save(calificacionEditado);
        }
        return null;
    }
    public Calificacion obtenerCalificacion(Long id){
        return repositorioCalificacion.findById(id).orElse(null);
    }
    public String eliminarCalificacion(Long id){
        repositorioCalificacion.deleteById(id);
        return "Calificacion eliminada con exito";
    }

}

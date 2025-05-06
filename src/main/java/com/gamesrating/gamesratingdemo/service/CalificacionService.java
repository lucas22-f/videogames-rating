package com.gamesrating.gamesratingdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesrating.gamesratingdemo.DTO.CalificacionDTO;
import com.gamesrating.gamesratingdemo.DTO.CalificacionResponse;
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

    public String crearCalificacion(CalificacionDTO calificacionDto) {
        Videojuego videojuegoBD = videoJuegoRepository.findById(calificacionDto.getVideojuego()).orElse(null);
        Usuario usuarioBD = usuarioRepository.findById(calificacionDto.getUsuario()).orElse(null);
        Calificacion calificacion = new Calificacion();

        calificacion.setPuntuacion(calificacionDto.getPuntuacion());
        calificacion.setUsuario(usuarioBD);
        calificacion.setVideojuego(videojuegoBD);
        repositorioCalificacion.save(calificacion);

        return "Calificacion creada con exito";
    }

    public List<CalificacionResponse> obtenerCalificaciones() {
        // lista 1  = [Calificacion,Calificacion,Calificacion]
        // lista 2 = [(Calificacion,Nombre),(Calificacion,Nombre)]

        List<Calificacion> listaCalificaciones = repositorioCalificacion.findAll();
        List<CalificacionResponse> listaCalificacionesResponse = listaCalificaciones.stream().map(calificacion -> {
            Videojuego videojuego = calificacion.getVideojuego();
            String tituloVidejuego = videoJuegoRepository.findById(videojuego.getId())
                    .map(Videojuego::getTitulo)
                    .orElse("Titulo no encontrado");

            return new CalificacionResponse(calificacion, tituloVidejuego);

        }).collect(Collectors.toList());

        return listaCalificacionesResponse;
    }

    public Calificacion editarCalificacion(Long id, Calificacion calificacionEditado) {
        Calificacion calificacionBD = repositorioCalificacion.findById(id).orElse(null);
        if (calificacionBD != null) {
            return repositorioCalificacion.save(calificacionEditado);
        }
        return null;
    }

    public CalificacionResponse obtenerCalificacion(Long id) {
        String nombreVideojuego = repositorioCalificacion.findById(id).get().getVideojuego().getTitulo();
        Calificacion calificacion = repositorioCalificacion.findById(id).orElse(null);
        CalificacionResponse response = new CalificacionResponse(calificacion, nombreVideojuego);

        return response;
    }

    public String eliminarCalificacion(Long id) {
        repositorioCalificacion.deleteById(id);
        return "Calificacion eliminada con exito";
    }

}

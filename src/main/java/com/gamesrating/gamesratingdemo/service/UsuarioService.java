package com.gamesrating.gamesratingdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesrating.gamesratingdemo.models.Usuario;
import com.gamesrating.gamesratingdemo.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final IUsuarioRepository repositorioUsuario;

    public String crearUsuario(Usuario usuario){
        repositorioUsuario.save(usuario);
        return "usuario creado con exito";
    }
    public Usuario editarUsuario(Long id, Usuario usuarioEditado){
        Usuario usuarioBD = repositorioUsuario.findById(id).orElse(null);
        if(usuarioBD!=null){
            return repositorioUsuario.save(usuarioEditado);
        }
        return null;
    }
    public List<Usuario> obtenerUsuarios(){
        return repositorioUsuario.findAll();
    }
    public Usuario obtenerUsuario(Long id){
        return repositorioUsuario.findById(id).orElse(null);
    }
    public String eliminarUsuario(Long id){
        repositorioUsuario.deleteById(id);
        return "Usuario eliminado con exito";
    }
}

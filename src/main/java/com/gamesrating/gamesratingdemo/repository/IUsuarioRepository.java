package com.gamesrating.gamesratingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamesrating.gamesratingdemo.models.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {

}
